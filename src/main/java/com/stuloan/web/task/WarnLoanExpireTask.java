package com.stuloan.web.task;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.stuloan.web.mybatis.domain.Sms;
import com.stuloan.web.mybatis.domain.inte.LoanMapper;
import com.stuloan.web.mybatis.domain.inte.RepaydetailMapper;
import com.stuloan.web.mybatis.domain.inte.SmsMapper;
import com.stuloan.web.sms.SmsDemo;
import com.stuloan.web.util.CommonUtil;
import com.stuloan.web.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by suyx on 2017/8/14 0014.
 */
@Component
@Lazy(value=false)
public class WarnLoanExpireTask {

    private static final Logger logger = LoggerFactory.getLogger(WarnLoanExpireTask.class);

    private static final String SIGNNAME = "树丫丫的通知";
    private static final String TEMPLATECODE = "SMS_133276806";

    private static int bq_timoutminute = -7*24*60 ;

    @Autowired
    private LoanMapper loanMapper;
    @Autowired
    private RepaydetailMapper repaydetailMapper;
    @Autowired
    private SmsMapper smsMapper;

    @Scheduled(cron = "0 0 10 * * ?")
    public void dowarnsmssend(){
        try {
            String searchdate = DateUtil.getAddMinutes(new Date(),bq_timoutminute);
            Map<String,Object> params = new HashMap<>();
            params.put("searchdate",searchdate);
            params.put("limit","0,30");
            List<Map<String,Object>> list = loanMapper.selectloanexpires(params);

            if(list != null && list.size() > 0){
                String phonenumbers = "";
                String expiretime = "";
                String repayids = "";
                for(Map<String,Object> map : list){
                    repayids += "," + map.get("repayids");
                    phonenumbers += "," + map.get("phone");
                    expiretime = map.get("expiretime") + "";
                }
                phonenumbers = phonenumbers.substring(1);
                repayids = repayids.substring(1);

                Map<String,Object> templateparam = new HashMap<>();
                templateparam.put("expiretime",expiretime);

                Sms sms = new Sms();
                sms.setId(CommonUtil.uuid());
                sms.setSignname(SIGNNAME);
                sms.setTemplatecode(TEMPLATECODE);
                sms.setSmsphone(phonenumbers);
                sms.setSmsdesc("短信提示还款时间即将到期");
                sms.setSmstype("2");
                sms.setSmstime(new Date());
                sms.setTemplateparam(JSONObject.toJSONString(templateparam));

                SendSmsResponse response = SmsDemo.sendSms(sms);
                System.out.println("短信接口返回的数据----------------");
                System.out.println("Code=" + response.getCode());
                System.out.println("Message=" + response.getMessage());
                System.out.println("RequestId=" + response.getRequestId());
                System.out.println("BizId=" + response.getBizId());

                if("OK".equalsIgnoreCase(response.getCode())){
                    repayids = repayids.replace(",","','");
                    repayids = "'" + repayids + "'";
                    int i = repaydetailMapper.updatesmsstate(repayids);
                    i += smsMapper.insertSelective(sms);
                }

            }

        }catch (Exception e){
            logger.info(e.getMessage());
        }
    }
}
