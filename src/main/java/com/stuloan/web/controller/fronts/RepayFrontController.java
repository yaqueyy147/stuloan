package com.stuloan.web.controller.fronts;

import com.stuloan.web.alipay.AlipayTrade_loan;
import com.stuloan.web.alipay.AlipayTrade_repay;
import com.stuloan.web.alipay.model.result.AlipayF2FQueryResult;
import com.stuloan.web.mybatis.domain.*;
import com.stuloan.web.mybatis.domain.inte.*;
import com.stuloan.web.util.*;
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
 * Created by suyx on 2018-04-22.
 *
 */
@Controller
@RequestMapping(value = "/loan")
public class RepayFrontController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RepayFrontController.class);

    private static final int PAGE_SIZE = 10;
    private static final int PAGE_NUM = 1;

    @Autowired
    private LoanMapper loanMapper;
    @Autowired
    private RepaydetailMapper repaydetailMapper;

    @Autowired
    private SysuserMapper sysuserMapper;

    @Autowired
    private StudentinfoMapper studentinfoMapper;

    @Autowired
    private RepayorderMapper repayorderMapper;

    @RequestMapping(value = "myloan")
    public ModelAndView myloan(Model model, HttpServletRequest request){
        try {
            Sysuser sysuser = Userutils4mybatis.getcookieuser(request, Userutils.FRONG_COOKIE_NAME);
            if(CommonUtil.isBlank(sysuser)){
                model.addAttribute("loginCode",-2);
                return new ModelAndView("/fronts/login");
            }
            sysuser = sysuserMapper.selectByPrimaryKey(sysuser.getId());
            model.addAttribute("sysuser", sysuser);
            model.addAttribute(Userutils.FRONG_COOKIE_NAME, sysuser);
        }catch (Exception e){
            ;
        }
        return new ModelAndView("/fronts/myloan");
    }

    @RequestMapping(value = "repaydetail")
    public ModelAndView repaydetail(Model model, HttpServletRequest request){
        try {
            Sysuser sysuser = Userutils4mybatis.getcookieuser(request, Userutils.FRONG_COOKIE_NAME);
            if(CommonUtil.isBlank(sysuser)){
                model.addAttribute("loginCode",-2);
                return new ModelAndView("/fronts/login");
            }
            sysuser = sysuserMapper.selectByPrimaryKey(sysuser.getId());
            model.addAttribute("sysuser", sysuser);
            model.addAttribute(Userutils.FRONG_COOKIE_NAME, sysuser);
        }catch (Exception e){
            ;
        }
        return new ModelAndView("/fronts/repaydetail");
    }

    @RequestMapping(value = "/myloanlist")
    @ResponseBody
    public Object myloanlist(@RequestParam Map<String,Object> params) throws UnsupportedEncodingException{
        Map<String,Object> result = new HashMap<>();
        //查询总条数
        int total = loanMapper.selectCountByParams(params);

        //获取/设置每页条数
        int pageSize = CommonUtil.parseInt(params.get("pageSize"));
        pageSize = pageSize == 0 ? PAGE_SIZE : pageSize;
        //计算总页数
        int totalPage = (int)Math.ceil((double)total / (double) pageSize);
        //获取/设置当前页面
        int pageNo = CommonUtil.parseInt(params.get("pageNo"));
        pageNo = pageNo == 0 ? 1 : pageNo;

        params.put("pageSize",pageSize);
        params.put("beginRow",(pageNo - 1)*pageSize);

        //设置翻页栏信息
        String pageChanger = PageUtil.getNumberPageChanger(pageNo,totalPage,PAGE_NUM,pageSize,params.get("tableId")+"");

        //查询
        params.put("orderby","createdate desc");
        List<Loan> list = loanMapper.selectByParams(params);
        result.put("loanList",list);

        result.put("totalPage",totalPage);
        result.put("pageNo",pageNo);
        result.put("pageChanger", pageChanger);

        return result;
    }

    @RequestMapping(value = "/myrepaydetaillist")
    @ResponseBody
    public Object myrepaydetaillist(@RequestParam Map<String,Object> params) throws UnsupportedEncodingException{
        Map<String,Object> result = new HashMap<>();
        //查询总条数
        params.put("state","1");
        int total = repaydetailMapper.selectCountByParams(params);

        //获取/设置每页条数
        int pageSize = CommonUtil.parseInt(params.get("pageSize"));
        pageSize = pageSize == 0 ? PAGE_SIZE : pageSize;
        //计算总页数
        int totalPage = (int)Math.ceil((double)total / (double) pageSize);
        //获取/设置当前页面
        int pageNo = CommonUtil.parseInt(params.get("pageNo"));
        pageNo = pageNo == 0 ? 1 : pageNo;

        params.put("pageSize",pageSize);
        params.put("beginRow",(pageNo - 1)*pageSize);

        //设置翻页栏信息
        String pageChanger = PageUtil.getNumberPageChanger(pageNo,totalPage,PAGE_NUM,pageSize,params.get("tableId")+"");

        //查询
        params.put("orderby","repaydateplan asc");

        List<Repaydetail> list = repaydetailMapper.selectByParams(params);
        result.put("repaydetailList",list);

        result.put("totalPage",totalPage);
        result.put("pageNo",pageNo);
        result.put("pageChanger", pageChanger);

        return result;
    }

    @RequestMapping(value = "/myrepaydetaillistall")
    @ResponseBody
    public Object myrepaydetaillistall(@RequestParam Map<String,Object> params) throws UnsupportedEncodingException{
        Map<String,Object> result = new HashMap<>();
        //查询总条数
        params.put("state","1");
        int total = repaydetailMapper.selectCountByParams(params);

        //获取/设置每页条数
        int pageSize = CommonUtil.parseInt(params.get("pageSize"));
        pageSize = pageSize == 0 ? PAGE_SIZE : pageSize;
        //计算总页数
        int totalPage = (int)Math.ceil((double)total / (double) pageSize);
        //获取/设置当前页面
        int pageNo = CommonUtil.parseInt(params.get("pageNo"));
        pageNo = pageNo == 0 ? 1 : pageNo;

        params.put("pageSize",pageSize);
        params.put("beginRow",(pageNo - 1)*pageSize);

        //设置翻页栏信息
        String pageChanger = PageUtil.getNumberPageChanger(pageNo,totalPage,PAGE_NUM,pageSize,params.get("tableId")+"");

        //查询
        params.put("orderby","a.repaydateplan asc");

        List<Map<String,Object>> list = repaydetailMapper.selectByParams01(params);
        result.put("repaydetailList",list);

        result.put("totalPage",totalPage);
        result.put("pageNo",pageNo);
        result.put("pageChanger", pageChanger);

        return result;
    }

    @RequestMapping(value = "/getrepyaqrcode")
    @ResponseBody
    public Object getrepyaqrcode(@RequestParam Map<String,Object> params, HttpServletRequest request){
        Map<String,Object> result = new HashMap<>();
        result.put("code",0);
        result.put("message","系统错误，请联系管理员");

        String repays = params.get("repays") + "";
        String[] repayarr = repays.split(",");

        try {
            String userid = Userutils4mybatis.getuserid(request, Userutils4mybatis.FRONG_COOKIE_NAME);
            Studentinfo studentinfo = studentinfoMapper.selectByuserid(userid);
            int ii = 0;
            double repayamount = 0.0;
            String orderid = CommonUtil.uuid();
            String orderno = "repay_" + orderid;
            for(int i=0;i<repayarr.length;i++){
                String repay = repayarr[i];
                String repaydetailid = repay.split("::")[1];
                Repaydetail repaydetail = repaydetailMapper.selectByPrimaryKey(repaydetailid);
                repayamount += repaydetail.getRepaymoney();
                repaydetail.setOrderno(orderno);
                repaydetailMapper.updateByPrimaryKeySelective(repaydetail);
            }
            repayamount = CommonUtil.parseDouble(CommonUtil.get2DotStrFrmDouble(repayamount));
            Repayorder order = new Repayorder();
            order.setId(orderid);
            order.setOrderno(orderno);
            order.setCreatedate(new Date());
            order.setOrderdesc(studentinfo.getStuname() + "的还款,还款金额(" + repayamount + ")");
            order.setTotalamount(repayamount);
            order.setOrdertitle("XXX校园贷扫码放款");
//            order.setStoreid("loanstore001");
//            order.setOperatorid("operator001");
//            order.setSellerid("seller001");
            String qrcodeurl = AlipayTrade_repay.test_trade_precreate(order,null, request);
            order.setOrderqrimage(qrcodeurl);
            int i = repayorderMapper.insertSelective(order);
            result.put("qrcodeurl",qrcodeurl);
            result.put("code",1);
            result.put("message","订单创建成功!");
            //查询订单状态
            this.getorderstatus(orderno,orderid);
        }catch (Exception e){
            e.printStackTrace();
            result.put("code",0);
            result.put("message","系统错误，请联系管理员");
        }

        return result;
    }

    @RequestMapping(value = "/transferrepay")
    @ResponseBody
    public Object transferrepay(@RequestParam Map<String,Object> params, HttpServletRequest request){
        Map<String,Object> result = new HashMap<>();
        result.put("code",0);
        result.put("message","系统错误，请联系管理员");

        String repays = params.get("repays") + "";
        String[] repayarr = repays.split(",");

        try {
            String userid = Userutils4mybatis.getuserid(request, Userutils4mybatis.FRONG_COOKIE_NAME);
            Studentinfo studentinfo = studentinfoMapper.selectByuserid(userid);
            int ii = 0;
            double repayamount = 0.0;
            boolean bb = false;
            boolean bb1 = false;
            String orderid = CommonUtil.uuid();
            String orderno = "repay_" + orderid;
            for(int i=0;i<repayarr.length;i++){
                String repay = repayarr[i];
                String repaydetailid = repay.split("::")[1];
                Repaydetail repaydetail = repaydetailMapper.selectByPrimaryKey(repaydetailid);
                repayamount += repaydetail.getRepaymoney();
                repaydetail.setOrderno(orderno);
                repaydetailMapper.updateByPrimaryKeySelective(repaydetail);
            }
            repayamount = CommonUtil.parseDouble(CommonUtil.get2DotStrFrmDouble(repayamount));
            Repayorder order = new Repayorder();
            order.setId(orderid);
            order.setOrderno(orderno);
            order.setCreatedate(new Date());
            order.setOrderdesc(studentinfo.getStuname() + "的还款,还款金额(" + repayamount + ")");
            order.setTotalamount(repayamount);
            order.setOrdertitle("XXX校园贷扫码放款");
            bb = AlipayUtil.alipaytransfer(order);
            if(bb){
                bb1 = AlipayUtil.alipaytransferquery(order);
                Map<String,String> repaydetail = new HashMap<>();
                repaydetail.put("isrepay","3");
                if(bb1){
                    order.setOrderstate("1");
                    repaydetail.put("isrepay","1");
                }
                int i = repayorderMapper.insertSelective(order);

                repaydetail.put("ordernonew",orderno);
                repaydetail.put("ordernoold",orderno);
                repaydetailMapper.updatebyorderno(repaydetail);

                result.put("code",1);
                result.put("message","订单创建成功!");
            }else{
                Map<String,String> repaydetail = new HashMap<>();
                repaydetail.put("isrepay","4");
                repaydetail.put("ordernonew","");
                repaydetail.put("ordernoold",orderno);
                repaydetailMapper.updatebyorderno(repaydetail);
                result.put("code",0);
                result.put("message","订单创建失败!");
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

    @RequestMapping(value = "/repay")
    @ResponseBody
    public Object repay(@RequestParam Map<String,Object> params, HttpServletRequest request){
        Map<String,Object> result = new HashMap<>();
        result.put("code",0);
        result.put("message","系统错误，请联系管理员");

        String repays = params.get("repays") + "";
        String[] repayarr = repays.split(",");

//        String repaydetailid = params.get("repaydetailid") + "";
//        String loanid = params.get("loanid") + "";

        try {
            String userid = Userutils4mybatis.getuserid(request, Userutils4mybatis.FRONG_COOKIE_NAME);
            Studentinfo studentinfo = studentinfoMapper.selectByuserid(userid);
            int ii = 0;
            double repayamount = 0.0;
            for(int i=0;i<repayarr.length;i++){
                String repay = repayarr[i];
                String repaydetailid = repay.split("::")[1];
                String loanid = repay.split("::")[0];
                Loan loan = loanMapper.selectByPrimaryKey(loanid);
                Repaydetail repaydetail = repaydetailMapper.selectByPrimaryKey(repaydetailid);

                repaydetail.setIsrepay("1");
                repaydetail.setRepaydatereal(new Date());
                ii += repaydetailMapper.updateByPrimaryKeySelective(repaydetail);

                repayamount += repaydetail.getRepaymoney();

                loan.setRepayyet(loan.getRepayyet() + repaydetail.getRepaymoney());
                loan.setStagenumyet(repaydetail.getStagenum());
                loan.setUpdatedate(new Date());
                ii += loanMapper.updateByPrimaryKeySelective(loan);
            }

            Repayorder order = new Repayorder();
            order.setId(CommonUtil.uuid());
            order.setOrderno("repay_" + order.getId());
            order.setCreatedate(new Date());
            order.setOrderdesc(studentinfo.getStuname() + "的还款,还款金额(" + repayamount + ")");
            order.setTotalamount(repayamount);
            order.setOrdertitle("XXX校园贷扫码放款");
            String qrcodeurl = AlipayTrade_repay.test_trade_precreate(order,null, request);
            order.setOrderqrimage(qrcodeurl);
            int i = repayorderMapper.insertSelective(order);
            result.put("qrcodeurl",qrcodeurl);
            result.put("code",1);
            result.put("message","还款成功!");
        }catch (Exception e){
            result.put("code",0);
            result.put("message","系统错误，请联系管理员");
        }

        return result;
    }

    /**
     * 延时一分钟查询订单状态
     * @param orderno
     */
    private void getorderstatus(String orderno,String orderid){
        LOGGER.info("***执行了getorderstatus这个任务***");
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                LOGGER.info("***进来了getorderstatus这个任务的task***");
                Map<String,Object> params = new HashMap<>();
                Repayorder repayorder = repayorderMapper.selectByPrimaryKey(orderid);
                AlipayF2FQueryResult result = AlipayTrade_loan.test_trade_query(orderno);
                //如果订单状态为成功，则遍历是该订单号的贷款数据，修改状态为已放款
                if(result.isTradeSuccess()){
                    params.put("orderno",orderno);
                    List<Repaydetail> repaydetaillist = repaydetailMapper.selectByParams(params);
                    if(repaydetaillist != null && repaydetaillist.size() >0){
                        for(Repaydetail repaydetail : repaydetaillist){
                            //修改当条还款为已还款
                            repaydetail.setIsrepay("1");
                            repaydetail.setRepaydatereal(new Date());
                            repaydetailMapper.updateByPrimaryKeySelective(repaydetail);
                            //修改贷款表的还款金额和期数和时间
                            Loan loan = loanMapper.selectByPrimaryKey(repaydetail.getLoanid());
                            loan.setRepayyet(loan.getRepayyet() + repaydetail.getRepaymoney());
                            loan.setStagenumyet(repaydetail.getStagenum());
                            loan.setUpdatedate(new Date());
                            loanMapper.updateByPrimaryKeySelective(loan);
                        }
                    }
                    //修改当条订单为已还款
                    repayorder.setOrderstate("1");
                    repayorderMapper.updatebyorderno(repayorder);
                }else{
                    //设置订单状态为2，等待定时任务继续查询订单状态
                    repayorder.setOrderstate("2");
                    repayorderMapper.updatebyorderno(repayorder);
                }
            }
        }, 10 * 1000);
    }

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
                    Repayorder repayorder = repayorderMapper.selectByPrimaryKey(orderid);
                    boolean bb = AlipayUtil.alipaytransferquery(repayorder);
                    //如果订单状态为成功，则遍历是该订单号的贷款数据，修改状态为已放款
                    if(bb){
                        params.put("orderno",orderno);
                        List<Repaydetail> repaydetaillist = repaydetailMapper.selectByParams(params);
                        if(repaydetaillist != null && repaydetaillist.size() >0){
                            for(Repaydetail repaydetail : repaydetaillist){
                                //修改当条还款为已还款
                                repaydetail.setIsrepay("1");
                                repaydetail.setRepaydatereal(new Date());
                                repaydetailMapper.updateByPrimaryKeySelective(repaydetail);
                                //修改贷款表的还款金额和期数和时间
                                Loan loan = loanMapper.selectByPrimaryKey(repaydetail.getLoanid());
                                loan.setRepayyet(loan.getRepayyet() + repaydetail.getRepaymoney());
                                loan.setStagenumyet(repaydetail.getStagenum());
                                loan.setUpdatedate(new Date());
                                loanMapper.updateByPrimaryKeySelective(loan);
                            }
                        }
                        //修改当条订单为已还款
                        repayorder.setOrderstate("1");
                        repayorderMapper.updatebyorderno(repayorder);
                    }else{
                        //设置订单状态为2，等待定时任务继续查询订单状态
                        repayorder.setOrderstate("2");
                        repayorderMapper.updatebyorderno(repayorder);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }, 10 * 1000);
    }

}
