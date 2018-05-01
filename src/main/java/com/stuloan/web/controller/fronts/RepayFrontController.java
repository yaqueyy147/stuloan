package com.stuloan.web.controller.fronts;

import com.stuloan.web.alipay.AlipayTrade_loan;
import com.stuloan.web.alipay.AlipayTrade_repay;
import com.stuloan.web.mybatis.domain.*;
import com.stuloan.web.mybatis.domain.inte.*;
import com.stuloan.web.util.CommonUtil;
import com.stuloan.web.util.PageUtil;
import com.stuloan.web.util.Userutils;
import com.stuloan.web.util.Userutils4mybatis;
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
 * Created by suyx on 2018-04-22.
 *
 */
@Controller
@RequestMapping(value = "/loan")
public class RepayFrontController {
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
            model.addAttribute("user", sysuser);
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
            model.addAttribute("user", sysuser);
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
            for(int i=0;i<repayarr.length;i++){
                String repay = repayarr[i];
                String repaydetailid = repay.split("::")[1];
                Repaydetail repaydetail = repaydetailMapper.selectByPrimaryKey(repaydetailid);
                repayamount += repaydetail.getRepaymoney();
            }

            Repayorder order = new Repayorder();
            order.setId(CommonUtil.uuid());
            order.setOrderno("repay_" + order.getId());
            order.setCreatedate(new Date());
            order.setOrderdesc(studentinfo.getStuname() + "的还款,还款金额(" + repayamount + ")");
            order.setTotalamount(repayamount);
            order.setOrdertitle("XXX校园贷扫码放款");
//            order.setStoreid("loanstore001");
//            order.setOperatorid("operator001");
//            order.setSellerid("seller001");
            String qrcodeurl = AlipayTrade_repay.test_trade_precreate(order,null);
            order.setOrderqrimage(qrcodeurl);
            int i = repayorderMapper.insertSelective(order);
            result.put("qrcodeurl",qrcodeurl);
            result.put("code",1);
            result.put("message","订单创建成功!");
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
            String qrcodeurl = AlipayTrade_repay.test_trade_precreate(order,null);
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

}
