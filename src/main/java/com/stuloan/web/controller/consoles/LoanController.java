package com.stuloan.web.controller.consoles;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.stuloan.web.alipay.AlipayTrade_loan;
import com.stuloan.web.mybatis.domain.*;
import com.stuloan.web.mybatis.domain.inte.*;
import com.stuloan.web.sms.SmsDemo;
import com.stuloan.web.util.AlipayUtil;
import com.stuloan.web.util.CommonUtil;
import com.stuloan.web.util.Userutils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * Created by suyx on 2018-04-21.
 */
@Controller
@RequestMapping(value = "/consoles")
public class LoanController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoanController.class);

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
    @Autowired
    private BlacklistMapper blacklistMapper;

    @Autowired
    private UserphotoMapper userphotoMapper;

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

    @RequestMapping(value = "/overdue")
    public ModelAndView overdue(Model model, HttpServletRequest request){
        return new ModelAndView("/consoles/overdue");
    }

    @RequestMapping(value = "/blacklist")
    public ModelAndView blacklist(Model model, HttpServletRequest request){
        return new ModelAndView("/consoles/blacklist");
    }

    @RequestMapping(value = "/notrepay")
    public ModelAndView notrepay(Model model, HttpServletRequest request){
        return new ModelAndView("/consoles/notrepaylist");
    }

    @RequestMapping(value = "/photoaudit")
    public ModelAndView photoaudit(Model model, HttpServletRequest request){
        return new ModelAndView("/consoles/photoaudit");
    }

    @RequestMapping(value = "/loanlist")
    @ResponseBody
    public Object loanlist(HttpServletRequest request, @RequestParam Map<String,Object> params){
        Map<String,Object> result = new HashMap<>();
        int totalcount = loanMapper.selectCountByParams(params);

        int pageNumber = CommonUtil.parseInt(params.get("pageNumber"));
        int pageSize = CommonUtil.parseInt(params.get("pageSize"));
        int beginRow = (pageNumber - 1) * pageSize;
        params.put("beginRow",beginRow);

        List<Map<String,Object>> list = loanMapper.selectByParams02(params);
        result.put("rows",list);
        result.put("total",totalcount);
        return result;
    }

    @RequestMapping(value = "/overdueloanlist")
    @ResponseBody
    public Object overdueloanlist(HttpServletRequest request, @RequestParam Map<String,Object> params){
        Map<String,Object> result = new HashMap<>();
        int totalcount = loanMapper.selectCountByParams(params);
        result.put("total",totalcount);

        int pageNumber = CommonUtil.parseInt(params.get("pageNumber"));
        int pageSize = CommonUtil.parseInt(params.get("pageSize"));
        int beginRow = (pageNumber - 1) * pageSize;
        params.put("beginRow",beginRow);

        List<Map<String,Object>> list = loanMapper.selectByParams03(params);
        result.put("rows",list);
        return result;
    }

    @RequestMapping(value = "/blacklistdata")
    @ResponseBody
    public Object blacklistdata(HttpServletRequest request, @RequestParam Map<String,Object> params){
        Map<String,Object> result = new HashMap<>();
        int totalcount = blacklistMapper.selectCountByParams(params);
        result.put("total",totalcount);

        int pageNumber = CommonUtil.parseInt(params.get("pageNumber"));
        int pageSize = CommonUtil.parseInt(params.get("pageSize"));
        int beginRow = (pageNumber - 1) * pageSize;
        params.put("beginRow",beginRow);

        List<Map<String,Object>> list = blacklistMapper.selectByparams(params);
        result.put("rows",list);
        return result;
    }

    @RequestMapping(value = "/overduedetail")
    @ResponseBody
    public Object overduedetail(HttpServletRequest request, @RequestParam Map<String,Object> params){
        Map<String,Object> result = new HashMap<>();
        int totalcount = blacklistMapper.selectCountByParams4detail(params);
        result.put("total",totalcount);

        int pageNumber = CommonUtil.parseInt(params.get("pageNumber"));
        int pageSize = CommonUtil.parseInt(params.get("pageSize"));
        int beginRow = (pageNumber - 1) * pageSize;
        params.put("beginRow",beginRow);

        List<Map<String,Object>> list = blacklistMapper.selectoverduedetail(params);
        result.put("rows",list);
        return result;
    }

    @RequestMapping(value = "/notrepaylist")
    @ResponseBody
    public Object notrepaylist(HttpServletRequest request, @RequestParam Map<String,Object> params){
        Map<String,Object> result = new HashMap<>();
        int totalcount = repaydetailMapper.selectnotrepayCountByParams(params);
        result.put("total",totalcount);

        int pageNumber = CommonUtil.parseInt(params.get("pageNumber"));
        int pageSize = CommonUtil.parseInt(params.get("pageSize"));
        int beginRow = (pageNumber - 1) * pageSize;
        params.put("beginRow",beginRow);

        List<Map<String,Object>> list = repaydetailMapper.selectnotrepayByParams(params);
        result.put("rows",list);
        return result;
    }

    @RequestMapping(value = "/photolist")
    @ResponseBody
    public Object photolist(HttpServletRequest request, @RequestParam Map<String,Object> params){
        Map<String,Object> result = new HashMap<>();
        int totalcount = userphotoMapper.selectCountByParams(params);
        result.put("total",totalcount);

        int pageNumber = CommonUtil.parseInt(params.get("pageNumber"));
        int pageSize = CommonUtil.parseInt(params.get("pageSize"));
        int beginRow = (pageNumber - 1) * pageSize;
        params.put("beginRow",beginRow);

        List<Map<String,Object>> list = userphotoMapper.selectByParams(params);
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
            String orderid = "";
            Studentinfo studentinfo = studentinfoMapper.selectByuserid(loan.getUserid());
            String qrcodeurl = "";
            if(!CommonUtil.isBlank(orderno)){
                Loanorder loanorder = loanorderMapper.selectbyorderno(orderno);
                qrcodeurl = loanorder.getOrderqrimage();
                orderid = loanorder.getId();
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
                orderid = order.getId();
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
            //查询订单状态
//            this.getorderstatus(orderno,orderid);
        }catch (Exception e){
            e.printStackTrace();
            result.put("code",0);
            result.put("message","系统错误，请联系管理员");
        }

        return result;
    }

    @RequestMapping(value = "/transferpay")
    @ResponseBody
    public Object transferpay(HttpServletRequest request, String id){
        Map<String,Object> result = new HashMap<>();
        result.put("code",0);
        result.put("message","系统错误，请联系管理员");

        try {
            int ii = 0;
            String userphones = "";
            Loan loan = loanMapper.selectByPrimaryKey(id);
            String orderno = loan.getOrderno();
            String orderid = "";
            Studentinfo studentinfo = studentinfoMapper.selectByuserid(loan.getUserid());
            boolean bb = false;//生成订单状态
            boolean bb1 = false;//查询订单状态
            //通过orderno查询贷款订单
            Loanorder loanorder = loanorderMapper.selectbyorderno(orderno);
            //如果订单存在
            if(!CommonUtil.isBlank(orderno) && !CommonUtil.isBlank(loanorder) && !CommonUtil.isBlank(loanorder.getId())){
                orderid = loanorder.getId();
                //如果订单状态为1，则表示订单已完成，修改贷款表状态，直接返回
                if("1".equals(loanorder.getOrderstate())){
                    loan.setLoanoutdate(new Date());
                    loan.setState("1");
                    loanMapper.updateByPrimaryKeySelective(loan);
                    result.put("code",1);
                    result.put("message","已完成放款，请刷新页面!");
                }else{//如果订单状态不为1
                    //即时查询订单状态
                    bb1 = AlipayUtil.alipaytransferquery(loanorder);
                    //如果查询订单状态为成功，则修改贷款状态
                    if(bb1){
                        loan.setLoanoutdate(new Date());
                        loan.setState("1");
                        loanMapper.updateByPrimaryKeySelective(loan);
                        result.put("code",1);
                        result.put("message","已完成放款，请刷新页面!");
                    }else{//否则返回
                        result.put("code",3);
                        result.put("message","已放款,等待服务器处理中，请稍后刷新页面!");
                    }

                }
            }else{//订单不存在，则生成新订单，并发送短信
                Loanorder order = new Loanorder();
                order.setId(CommonUtil.uuid());
                order.setOrderno("loan_" + order.getId());
                order.setCreatedate(new Date());
                order.setOrderdesc(studentinfo.getStuname() + "的贷款,贷款金额(" + loan.getLoanamount() + "),用途:" + loan.getLoanpurpose());
                order.setTotalamount(loan.getLoanamount());
                order.setOrdertitle("XXX校园贷扫码放款");

                //创建新订单，并向支付宝发送订单信息
                bb = AlipayUtil.alipaytransfer(order,loan.getAlipayname());
                if(bb){
                    //如果创建成功，马上查询一次订单状态
                    bb1 = AlipayUtil.alipaytransferquery(order);
                    //根据查询到的订单状态，设置订单和贷款状态
                    if(bb1){
                        order.setOrderstate("1");
                        loan.setState("1");
                    }else{
                        order.setOrderstate("3");
                    }
                    ii += loanorderMapper.insertSelective(order);
                    orderno = order.getOrderno();
                    orderid = order.getId();

                    loan.setOrderno(orderno);
                    loan.setLoanoutdate(new Date());
                    ii += loanMapper.updateByPrimaryKeySelective(loan);

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
                        result.put("message","放款成功!");
                    }else{
                        result.put("message","放款成功,短信发送失败!");
                    }
                    result.put("code",1);

                }else{
                    result.put("code",0);
                    result.put("message","订单创建失败，请重试!");
                }

            }

            if(!bb1){
                //查询订单状态
                this.getorderstatus4transfer(orderno,orderid);
            }

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

//                    Studentinfo studentinfo = studentinfoMapper.selectByuserid(loan.getUserid());

//                    Loanorder order = new Loanorder();
//                    order.setId(CommonUtil.uuid());
//                    order.setOrderno("loan_" + order.getId());
//                    order.setCreatedate(new Date());
//                    order.setOrderdesc(studentinfo.getStuname() + "的贷款,贷款金额(" + loan.getLoanamount() + "),用途:" + loan.getLoanpurpose());
//                    order.setTotalamount(loan.getLoanamount());
//                    order.setOrdertitle("XXX校园贷扫码放款");
//                    order.setOrderqrimage(qrcodeurl);
//                    ii += loanorderMapper.insertSelective(order);
//                    loan.setOrderno(order.getOrderno());

                    Sysuser sysuser = sysuserMapper.selectByPrimaryKey(loan.getUserid());
                    sysuser.setLoanlimitleft(sysuser.getLoanlimit() - loan.getLoanamount());
                    sysuserMapper.updateByPrimaryKeySelective(sysuser);
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

        int pageNumber = CommonUtil.parseInt(params.get("pageNumber"));
        int pageSize = CommonUtil.parseInt(params.get("pageSize"));
        int beginRow = (pageNumber - 1) * pageSize;
        params.put("beginRow",beginRow);

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
            sysuser.setLoanlimitleft(ll);
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

    @RequestMapping(value = "frozenuser")
    @ResponseBody
    public Object frozenuser(@RequestParam Map<String,Object> params, HttpServletRequest request) throws UnsupportedEncodingException {
        Map<String,Object> result = new HashMap<>();
        result.put("code",0);
        result.put("message","设置失败");
        try {
            //冻结账号
            Sysuser sysuser = sysuserMapper.selectByPrimaryKey(params.get("userid") + "");
            sysuser.setIsfrozen("1");
            sysuserMapper.updateByPrimaryKeySelective(sysuser);

            //记录黑名单
            Blacklist blacklist = new Blacklist();
            blacklist.setId(CommonUtil.uuid());
            blacklist.setUserid(sysuser.getId());
            blacklist.setOverduecount(CommonUtil.parseInt(params.get("overduecount")));
            blacklist.setCreatedate(new Date());
            blacklist.setCreateid(Userutils.getuserid(request,Userutils.CONSOLE_COOKIE_NAME));
            blacklist.setCreatename(Userutils.getusername(request,Userutils.CONSOLE_COOKIE_NAME));
            blacklistMapper.insert(blacklist);

            result.put("code",1);
            result.put("message","设置成功");
        }catch (Exception e){
            e.printStackTrace();
            result.put("code",0);
            result.put("message","设置失败");
        }

        return result;
    }

    @RequestMapping(value = "auditphoto")
    @ResponseBody
    public Object auditphoto(@RequestParam Map<String,Object> params, HttpServletRequest request) throws UnsupportedEncodingException {
        Map<String,Object> result = new HashMap<>();
        result.put("code",0);
        result.put("message","操作失败");
        try {
            String photoid = params.get("photoid") + "";
            String state = params.get("state") + "";
            String type = params.get("type") + "";

            Userphoto userphoto = userphotoMapper.selectByPrimaryKey(photoid);
            if("1".equals(type)){
                userphoto.setHeadstate(state);
            }
            if("2".equals(type)){
                userphoto.setIdcardstate(state);
            }
            if("3".equals(type)){
                userphoto.setStuidcardstate(state);
            }
            if("0".equals(type)){
                userphoto.setHeadstate(state);
                userphoto.setIdcardstate(state);
                userphoto.setStuidcardstate(state);
            }
            userphotoMapper.updateByPrimaryKeySelective(userphoto);

            if("1".equals(userphoto.getHeadstate()) && "1".equals(userphoto.getIdcardstate()) && "1".equals(userphoto.getStuidcardstate())){
                //修改用户图片状态
                Sysuser sysuser = sysuserMapper.selectByPrimaryKey(userphoto.getUserid());
                sysuser.setPhotostate("1");
                sysuserMapper.updateByPrimaryKeySelective(sysuser);
            }

            result.put("code",1);
            result.put("message","操作成功");
        }catch (Exception e){
            e.printStackTrace();
            result.put("code",0);
            result.put("message","操作失败");
        }

        return result;
    }

//    /**
//     * 延时一分钟查询订单状态
//     * @param orderno
//     */
//    private void getorderstatus(String orderno,String orderid){
//        LOGGER.info("***执行了getorderstatus这个任务***");
//        Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                LOGGER.info("***进来了getorderstatus这个任务的task***");
//                Map<String,Object> params = new HashMap<>();
//                Loanorder loanorder = loanorderMapper.selectByPrimaryKey(orderid);
//                AlipayF2FQueryResult result = AlipayTrade_loan.test_trade_query(orderno);
//                //如果订单状态为成功，则遍历是该订单号的贷款数据，修改状态为已放款
//                if(result.isTradeSuccess()){
//                    params.put("orderno",orderno);
//                    List<Loan> loanlist = loanMapper.selectByParams(params);
//                    if(loanlist != null && loanlist.size() >0){
//                        for(Loan loan : loanlist){
//                            loan.setState("1");
//                            loan.setLoanoutdate(new Date());
//                            loan.setAuditmsg("已放款");
//                            loanMapper.updateByPrimaryKeySelective(loan);
//                            Repaydetail repaydetail = new Repaydetail();
//                            repaydetail.setLoanid(loan.getId());
//                            repaydetail.setState("1");
//                            repaydetailMapper.updatestateByLoanIdSelective(repaydetail);
//                        }
//                    }
//                    //修改当条订单为已还款
//                    loanorder.setOrderstate("1");
//                    loanorderMapper.updatebyorderno(loanorder);
//                }else{
//                    //设置订单状态为2，等待定时任务继续查询订单状态
//                    loanorder.setOrderstate("2");
//                    loanorderMapper.updatebyorderno(loanorder);
//                }
//            }
//        }, 10 * 1000);
//    }

    /**
     * 延时一分钟查询订单状态
     * @param orderno
     */
    private void getorderstatus4transfer(String orderno,String orderid){
        LOGGER.info("***执行了getorderstatus这个任务***");
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    LOGGER.info("***进来了getorderstatus这个任务的task***");
                    Map<String,Object> params = new HashMap<>();
                    Loanorder loanorder = loanorderMapper.selectByPrimaryKey(orderid);
                    boolean bb = AlipayUtil.alipaytransferquery(loanorder);
                    //如果订单状态为成功，则遍历是该订单号的贷款数据，修改状态为已放款
                    if(bb){
                        params.put("orderno",orderno);
                        List<Loan> loanlist = loanMapper.selectByParams(params);
                        if(loanlist != null && loanlist.size() >0){
                            for(Loan loan : loanlist){
                                loan.setState("1");
                                loan.setLoanoutdate(new Date());
                                loan.setAuditmsg("已放款");
                                loanMapper.updateByPrimaryKeySelective(loan);
                                Repaydetail repaydetail = new Repaydetail();
                                repaydetail.setLoanid(loan.getId());
                                repaydetail.setState("1");
                                repaydetailMapper.updatestateByLoanIdSelective(repaydetail);
                            }
                        }
                        //修改当条订单为已还款
                        loanorder.setOrderstate("1");
                        loanorderMapper.updatebyorderno(loanorder);
                    }else{
                        //设置订单状态为2，等待定时任务继续查询订单状态
                        loanorder.setOrderstate("2");
                        loanorderMapper.updatebyorderno(loanorder);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }, 10 * 1000);
    }

}
