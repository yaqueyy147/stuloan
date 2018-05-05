package com.stuloan.web.controller.consoles;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.stuloan.web.alipay.AlipayTrade_loan;
import com.stuloan.web.mybatis.domain.*;
import com.stuloan.web.mybatis.domain.inte.*;
import com.stuloan.web.sms.SmsDemo;
import com.stuloan.web.util.CommonUtil;
import com.stuloan.web.util.Userutils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by suyx on 2018-04-21.
 */
@Controller
@RequestMapping(value = "/consoles")
public class LoanController {

    private static final String SIGNNAME = "树丫丫的通知";
    private static final String TEMPLATECODE = "SMS_133973146";

    @Autowired
    private LoanMapper loanMapper;
    @Autowired
    private StudentinfoMapper studentinfoMapper;
    @Autowired
    private StagefeeMapper stagefeeMapper;

    @Autowired
    private SysuserMapper sysuserMapper;

    @Autowired
    private RepaydetailMapper repaydetailMapper;

    @Autowired
    private LoanorderMapper loanorderMapper;
    @Autowired
    private SmsMapper smsMapper;

    @RequestMapping(value = "/loan")
    public ModelAndView loan(Model model, HttpServletRequest request){
        return new ModelAndView("/consoles/loan");
    }

    @RequestMapping(value = "/loanaudit")
    public ModelAndView applyloan(Model model, HttpServletRequest request){
        return new ModelAndView("/consoles/loanaudit");
    }

    @RequestMapping(value = "/loanout")
    public ModelAndView loanout(Model model, HttpServletRequest request){
        return new ModelAndView("/consoles/loanout");
    }

    @RequestMapping(value = "/loanlist")
    @ResponseBody
    public Object loanlist(HttpServletRequest request, @RequestParam Map<String,Object> params){
        Map<String,Object> result = new HashMap<>();
        int totalcount = loanMapper.selectCountByParams(params);
        result.put("total",totalcount);
        List<Map<String,Object>> list = loanMapper.selectByParams02(params);
        result.put("rows",list);
        return result;
    }

    @RequestMapping(value = "/getloanqrcode")
    @ResponseBody
    public Object getloanqrcode(HttpServletRequest request, String id){
        Map<String,Object> result = new HashMap<>();
        result.put("code",0);
        result.put("message","系统错误，请联系管理员");

        try {
            int ii = 0;
            String userphones = "";
            Loan loan = loanMapper.selectByPrimaryKey(id);
            String orderno = loan.getOrderno();
            Studentinfo studentinfo = studentinfoMapper.selectByuserid(loan.getUserid());
            String qrcodeurl = "";
            if(!CommonUtil.isBlank(orderno)){
                Loanorder loanorder = loanorderMapper.selectbyorderno(orderno);
                qrcodeurl = loanorder.getOrderqrimage();
            }else{
                Loanorder order = new Loanorder();
                order.setId(CommonUtil.uuid());
                order.setOrderno("loan_" + order.getId());
                order.setCreatedate(new Date());
                order.setOrderdesc(studentinfo.getStuname() + "的贷款,贷款金额(" + loan.getLoanamount() + "),用途:" + loan.getLoanpurpose());
                order.setTotalamount(loan.getLoanamount());
                order.setOrdertitle("XXX校园贷扫码放款");
                qrcodeurl = AlipayTrade_loan.test_trade_precreate(order,null, request);
                order.setOrderqrimage(qrcodeurl);
                ii += loanorderMapper.insertSelective(order);
                orderno = order.getOrderno();
            }

            loan.setOrderno(orderno);
//            loan.setState("1");
            loan.setLoanoutdate(new Date());
            ii += loanMapper.updateByPrimaryKeySelective(loan);

            result.put("qrcodeurl",qrcodeurl);

            Sysuser sysuser = sysuserMapper.selectByPrimaryKey(loan.getUserid());
            userphones += "," + sysuser.getPhone();

            Sms sms = new Sms();
            sms.setId(CommonUtil.uuid());
            sms.setSignname(SIGNNAME);
            sms.setTemplatecode(TEMPLATECODE);
            sms.setSmsphone(userphones);
            sms.setSmsdesc("短信提醒已放款");
            sms.setSmstype("2");
            sms.setSmstime(new Date());
            sms.setTemplateparam("");

            SendSmsResponse response = SmsDemo.sendSms(sms);

            if("OK".equalsIgnoreCase(response.getCode())){
                int i = smsMapper.insertSelective(sms);
            }
            result.put("code",1);
            result.put("message","放款成功");
        }catch (Exception e){
            e.printStackTrace();
            result.put("code",0);
            result.put("message","系统错误，请联系管理员");
        }

        return result;
    }

    @RequestMapping(value = "/auditloan")
    @ResponseBody
    public Object auditloan(HttpServletRequest request, String ids, String state, String auditmsg){
        Map<String,Object> result = new HashMap<>();
        result.put("code",0);
        result.put("message","系统错误，请联系管理员");

        try {
            String[] idarr = ids.split(",");
            int ii = 0;
            for(int i=0;i<idarr.length;i++){
                String id = idarr[i];
                Loan loan = loanMapper.selectByPrimaryKey(id);
                loan.setAuditdate(new Date());
                loan.setAuditid(Userutils.getuserid(request,Userutils.CONSOLE_COOKIE_NAME));
                loan.setAuditman(Userutils.getusername(request,Userutils.CONSOLE_COOKIE_NAME));
                loan.setState(state);

                Repaydetail repaydetail = new Repaydetail();
                repaydetail.setLoanid(id);

                if("5".equals(state)){
                    loan.setAuditmsg("同意贷款,待放款");
                    repaydetail.setState("1");
                    ii += repaydetailMapper.updatestateByLoanIdSelective(repaydetail);

                    Studentinfo studentinfo = studentinfoMapper.selectByuserid(loan.getUserid());

                    Loanorder order = new Loanorder();
                    order.setId(CommonUtil.uuid());
                    order.setOrderno("loan_" + order.getId());
                    order.setCreatedate(new Date());
                    order.setOrderdesc(studentinfo.getStuname() + "的贷款,贷款金额(" + loan.getLoanamount() + "),用途:" + loan.getLoanpurpose());
                    order.setTotalamount(loan.getLoanamount());
                    order.setOrdertitle("XXX校园贷扫码放款");
                    String qrcodeurl = AlipayTrade_loan.test_trade_precreate(order,null, request);
                    result.put("qrcodeurl",qrcodeurl);
                    order.setOrderqrimage(qrcodeurl);
                    ii += loanorderMapper.insertSelective(order);
                    loan.setOrderno(order.getOrderno());
                }else{
                    loan.setAuditmsg(CommonUtil.isBlank(auditmsg)?"不同意贷款":auditmsg);
                    ii += repaydetailMapper.deleteByLoanid(id);
                }

                ii += loanMapper.updateByPrimaryKeySelective(loan);

            }
            result.put("code",1);
            result.put("message","已审核");
        }catch (Exception e){
            e.printStackTrace();
            result.put("code",0);
            result.put("message","系统错误，请联系管理员");
        }

        return result;
    }

    @RequestMapping(value = "/viewrepaydetail")
    @ResponseBody
    public Object viewrepaydetail(@RequestParam Map<String,Object> params) throws UnsupportedEncodingException {

        Map<String,Object> result = new HashMap<>();
        int totalcount = repaydetailMapper.selectCountByParams(params);
        result.put("total",totalcount);
        List<Repaydetail> list = repaydetailMapper.selectByParams(params);
        result.put("rows",list);
        return result;
    }

    @RequestMapping(value = "/moneystatistics")
    public ModelAndView moneystatistics(Model model, HttpServletRequest request){
        return new ModelAndView("/consoles/moneystatistics");
    }

    @RequestMapping(value = "moneystatisticslist")
    @ResponseBody
    public Object moneystatisticslist(@RequestParam Map<String,Object> params) throws UnsupportedEncodingException {
        Map<String,Object> result = new HashMap<>();
        List<Map<String,Object>> list = loanMapper.moneystatistics(params);
        result.put("rows",list);
        result.put("total",list.size());
        return result;
    }

    @RequestMapping(value = "loanlimitset")
    @ResponseBody
    public Object loanlimitset(@RequestParam Map<String,Object> params) throws UnsupportedEncodingException {
        Map<String,Object> result = new HashMap<>();
        result.put("code",0);
        result.put("message","设置失败");
        try {
            Sysuser sysuser = sysuserMapper.selectByPrimaryKey(params.get("userid") + "");
            String loanlimit = params.get("loanlimit") + "";
            double ll = CommonUtil.parseDouble(loanlimit);
            sysuser.setLoanlimit(ll);
            sysuserMapper.updateByPrimaryKeySelective(sysuser);
            result.put("code",1);
            result.put("message","设置成功");
        }catch (Exception e){
            e.printStackTrace();
            result.put("code",0);
            result.put("message","设置失败");
        }

        return result;
    }
}
