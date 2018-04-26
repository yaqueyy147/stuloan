package com.stuloan.web.task;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.stuloan.web.mybatis.domain.inte.LoanMapper;
import com.stuloan.web.mybatis.domain.inte.RepaydetailMapper;
import com.stuloan.web.sms.SmsDemo;
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
 * 根据参数设置的CATCH-REPRINT-DAY天数持续对bqauthmain表中的数据进行转载增量抓取
 * 对确权时间（authdate）在CATCH-REPRINT-DAY天内的数据进行增量抓取
 */
@Component
@Lazy(value=false)
public class WarnLoanExpireTask {

    private static final Logger logger = LoggerFactory.getLogger(WarnLoanExpireTask.class);

    private static int bq_timoutminute = 4320 ;

    @Autowired
    private LoanMapper loanMapper;
    @Autowired
    private RepaydetailMapper repaydetailMapper;

    @Scheduled(cron = "0 0 9 * * ?")
    public void dowarnsmssend(){
        try {
            String searchdate = DateUtil.getAddMinutes(new Date(),-7*24*60);
            List<Map<String,Object>> list = loanMapper.selectloanexpires(searchdate);

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

                Map<String,Object> params = new HashMap<>();
                params.put("phonenumbers",phonenumbers);
                params.put("expiretime",expiretime);

                SendSmsResponse response = SmsDemo.sendSms(params);
                System.out.println("短信接口返回的数据----------------");
                System.out.println("Code=" + response.getCode());
                System.out.println("Message=" + response.getMessage());
                System.out.println("RequestId=" + response.getRequestId());
                System.out.println("BizId=" + response.getBizId());

                if("OK".equalsIgnoreCase(response.getCode())){
                    repayids = repayids.replace(",","','");
                    repayids = "'" + repayids + "'";
                    int i = repaydetailMapper.updatesmsstate(repayids);
                }

            }

        }catch (Exception e){
            logger.info(e.getMessage());
        }
    }
}
