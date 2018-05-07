package com.stuloan.web.controller.fronts;

import com.alibaba.fastjson.JSONObject;
import com.stuloan.web.mybatis.domain.*;
import com.stuloan.web.mybatis.domain.inte.*;
import com.stuloan.web.util.CommonUtil;
import com.stuloan.web.util.HttpRequest;
import com.stuloan.web.util.Userutils;
import com.stuloan.web.util.Userutils4mybatis;
import com.stuloan.web.vo.ResultBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by suyx on 2018-04-21.
 */
@Controller
@RequestMapping(value = "/loan")
public class LoanFrontController {

    private static final  String STU_IDENTITY_URL = "http://192.168.31.156:8082/student/api/identity/stuidentity";
    private static final  String CREDIT_IDENTITY_URL = "http://192.168.31.156:8082/student/api/identity/creditidentity";

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
    private CreditscoreMapper creditscoreMapper;

    @RequestMapping(value = "/applyloan")
    public ModelAndView applyloan(Model model, HttpServletRequest request){

        try {
            Sysuser sysuser = Userutils4mybatis.getcookieuser(request,Userutils.FRONG_COOKIE_NAME);
            if(sysuser == null || CommonUtil.isBlank(sysuser.getId())){
                model.addAttribute("loginCode",-2);
                return new ModelAndView("/fronts/login");
            }
            sysuser = sysuserMapper.selectByPrimaryKey(sysuser.getId());
            model.addAttribute("sysuser", sysuser);
            if(!"1".equals(sysuser.getIsstuidentity()) || !"1".equals(sysuser.getIscreditidentity())){
                return new ModelAndView("redirect:/fronts/personalInfo?loancode=-2");
            }

            Studentinfo studentinfo = new Studentinfo();
            studentinfo.setUserid(sysuser.getId());
            List<Studentinfo> list = studentinfoMapper.selectByParams(studentinfo);
            if(list != null && list.size() > 0){
                studentinfo = list.get(0);
            }
            model.addAttribute("studentinfo",studentinfo);
            Map<String,Object> params = new HashMap<>();
            params.put("orderby","stagenum asc");
            List<Stagefee> list1 = stagefeeMapper.selectByParams(params);
            model.addAttribute("stagefeelist",list1);
        }catch (Exception e){
            e.printStackTrace();
        }

        return new ModelAndView("/fronts/applyloan");
    }

    @RequestMapping(value = "/toapplyloan")
    public ModelAndView toapplyloan(Loan loan, HttpServletRequest request){
        Map<String,Object> result = new HashMap<>();
        result.put("code",0);
        result.put("message","提交失败");
        try{
            loan.setId(CommonUtil.uuid());
            loan.setIsstage("1");
            loan.setCreatedate(new Date());

            int loanage = loan.getLoanage();
            double loanamount = loan.getLoanamount();
            double fee = stagefeeMapper.selectfeeByage(loanage + "");
            double repaytotal = (loanamount * fee / 100) + loanamount;

            loan.setRepayyet(0.0);
            loan.setStagenum(loan.getLoanage());
            loan.setRepayreal(repaytotal);
            loan.setState("0");
            loan.setUserid(Userutils.getuserid(request,Userutils.FRONG_COOKIE_NAME));
            int i = loanMapper.insert(loan);

            double avgrepay = repaytotal / loanage;
            for(int k=0;k<loanage;k++){
                Repaydetail repaydetail = new Repaydetail();
                repaydetail.setId(CommonUtil.uuid());
                repaydetail.setLoanid(loan.getId());
                repaydetail.setState("0");
                repaydetail.setStagenum(k+1);
                repaydetail.setRepaymoney(avgrepay);
                repaydetail.setRepaydateplan(CommonUtil.ObjToDate(CommonUtil.subMonthfromamount(CommonUtil.getDateLong(),(k+1))));
                repaydetailMapper.insert(repaydetail);
            }

            result.put("code",1);
            result.put("message","提交成功");
        }catch (Exception e){
            e.printStackTrace();
            result.put("code",0);
            result.put("message","提交失败");
        }
        return new ModelAndView("redirect:/loan/myloan");
    }

    @RequestMapping(value = "/tostuidentity")
    @ResponseBody
    public Object tostuidentity(HttpServletRequest request, @RequestParam Map<String,String> params){
        Map<String,Object> result = new HashMap<>();
        result.put("code",0);
        result.put("message","保存失败");

        try {
            Sysuser sysuser = Userutils4mybatis.getcookieuser(request,Userutils4mybatis.FRONG_COOKIE_NAME);
            if(sysuser == null || CommonUtil.isBlank(sysuser.getId())){
                result.put("code",-2);
                result.put("message","请先登录再进行学生认证!");
                return result;
            }
            Studentinfo studentinfo = new Studentinfo();

            int i = 0;
            Map<String, String> headers = new HashMap<String, String>();
            headers.put("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            ResultBase resultBase = HttpRequest.doPost05(STU_IDENTITY_URL, headers, params, "utf-8", "utf-8");
            if(resultBase.isSuccess()){
                studentinfo = JSONObject.parseObject(resultBase.getResultmaps().get("studentinfo"),Studentinfo.class);
            }else{
                studentinfo.setRemark("查无此人");
            }

            Studentinfo studentinfo1 = studentinfoMapper.selectByuserid(Userutils.getuserid(request,Userutils.FRONG_COOKIE_NAME));
            if(studentinfo1 != null && !CommonUtil.isBlank(studentinfo1.getId()) ){
                studentinfo.setId(studentinfo1.getId());
                studentinfo.setUserid(Userutils.getuserid(request,Userutils.FRONG_COOKIE_NAME));
                studentinfo.setIsstuidentity("5");
                i += studentinfoMapper.updateByPrimaryKeySelective(studentinfo);
            }else{
                studentinfo.setId(CommonUtil.uuid());
                studentinfo.setUserid(Userutils.getuserid(request,Userutils.FRONG_COOKIE_NAME));
                studentinfo.setIsstuidentity("5");
                i += studentinfoMapper.insertSelective(studentinfo);
            }

            sysuser.setIsstuidentity("5");
            i += sysuserMapper.updateByPrimaryKeySelective(sysuser);
            result.put("code",1);
            result.put("message","已申请");

        }catch (Exception e){
            e.printStackTrace();
            result.put("code",0);
            result.put("message","保存失败");
        }

        return result;
    }

    @RequestMapping(value = "/tocreditidentity")
    @ResponseBody
    public Object tocreditidentity(HttpServletRequest request, @RequestParam Map<String,String> params){
        Map<String,Object> result = new HashMap<>();
        result.put("code",0);
        result.put("message","保存失败");

        try {
            Sysuser sysuser = Userutils4mybatis.getcookieuser(request,Userutils4mybatis.FRONG_COOKIE_NAME);
            if(sysuser == null || CommonUtil.isBlank(sysuser.getId())){
                result.put("code",-2);
                result.put("message","请先登录再进行信用认证!");
                return result;
            }
            Creditscore creditscore = new Creditscore();

            int i = 0;

            Map<String, String> headers = new HashMap<String, String>();
            headers.put("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            ResultBase resultBase = HttpRequest.doPost05(CREDIT_IDENTITY_URL, headers, params, "utf-8", "utf-8");
            if(resultBase.isSuccess()){
                creditscore = JSONObject.parseObject(resultBase.getResultmaps().get("creditscore"),Creditscore.class);
            }

            Creditscore creditscores1 = creditscoreMapper.selectByuserid(Userutils.getuserid(request,Userutils.FRONG_COOKIE_NAME));
            if(creditscores1 != null && !CommonUtil.isBlank(creditscores1.getId()) ){
                creditscore.setId(creditscores1.getId());
                creditscore.setUserid(Userutils.getuserid(request,Userutils.FRONG_COOKIE_NAME));
                creditscore.setState("5");
                i += creditscoreMapper.updateByPrimaryKeySelective(creditscore);
            }else{
                creditscore.setId(CommonUtil.uuid());
                creditscore.setUserid(Userutils.getuserid(request,Userutils.FRONG_COOKIE_NAME));
                creditscore.setState("5");
                i += creditscoreMapper.insertSelective(creditscore);
            }

            sysuser.setIscreditidentity("5");
            i += sysuserMapper.updateByPrimaryKeySelective(sysuser);
            result.put("code",1);
            result.put("message","已申请");
        }catch (Exception e){
            result.put("code",0);
            result.put("message","保存失败");
        }

        return result;
    }

    @RequestMapping(value = "getfeefromage")
    @ResponseBody
    public Object getfeefromage(@RequestParam Map<String,Object> params){
        Map<String,Object> result = new HashMap<>();
        result.put("code",0);

        try {
            List<Stagefee> list1 = stagefeeMapper.selectByParams(params);
            result.put("stagefee",list1.get(0));
            result.put("code",1);
        }catch (Exception e){
            e.printStackTrace();
            Stagefee stagefee = new Stagefee();
            stagefee.setFeepercent(0.0);
            result.put("stagefee",stagefee);
            result.put("code",0);
        }

        return result;
    }

}
