package com.stuloan.web.controller.fronts;

import com.stuloan.web.mybatis.domain.Creditscore;
import com.stuloan.web.mybatis.domain.Studentinfo;
import com.stuloan.web.mybatis.domain.Sysuser;
import com.stuloan.web.mybatis.domain.Userphoto;
import com.stuloan.web.mybatis.domain.inte.CreditscoreMapper;
import com.stuloan.web.mybatis.domain.inte.StudentinfoMapper;
import com.stuloan.web.mybatis.domain.inte.SysuserMapper;
import com.stuloan.web.mybatis.domain.inte.UserphotoMapper;
import com.stuloan.web.util.CommonUtil;
import com.stuloan.web.util.CookieUtil;
import com.stuloan.web.util.Userutils;
import com.stuloan.web.util.Userutils4mybatis;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * Created by suyx on 2016/12/18.
 */
@Controller
@RequestMapping(value = "/fronts")
public class IndexController {
    private static final int PAGE_SIZE = 20;//初始每页条数
    private static final int PAGE_NUM = 6;//初始显示页数
    @Autowired
    private CreditscoreMapper creditscoreMapper;

    @Autowired
    private SysuserMapper sysuserMapper;

    @Autowired
    private StudentinfoMapper studentinfoMapper;

    @Autowired
    private UserphotoMapper userphotoMapper;

    /**
     * 前台首页
     * @param model
     * @param request
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = {"","/","/index"})
    public ModelAndView index(Model model, HttpServletRequest request) throws UnsupportedEncodingException{

        //从cookie获取用户信息
        JSONObject jsonUser = CookieUtil.cookieValueToJsonObject(request,Userutils.FRONG_COOKIE_NAME);
//        model.addAttribute("userInfo",jsonUser);
        model.addAttribute("sysuser", jsonUser);
        return new ModelAndView("/fronts/index");
    }

    @RequestMapping(value = "personalInfo")
    public ModelAndView personalInfo(Model model, HttpServletRequest request){

        try {
            String loancode = CommonUtil.getParam(request,"loancode");
            model.addAttribute("loancode", loancode);
            Sysuser sysuser = Userutils4mybatis.getcookieuser(request,Userutils.FRONG_COOKIE_NAME);
            if(CommonUtil.isBlank(sysuser)){
                model.addAttribute("loginCode",-2);
                return new ModelAndView("/fronts/login");
            }
            sysuser = sysuserMapper.selectByPrimaryKey(sysuser.getId());
            model.addAttribute("sysuser", sysuser);
            model.addAttribute(Userutils.FRONG_COOKIE_NAME, sysuser);

            Studentinfo studentinfo = studentinfoMapper.selectByuserid(sysuser.getId());
            model.addAttribute("studentinfo",studentinfo);

            Creditscore creditscore = creditscoreMapper.selectByuserid(sysuser.getId());
            model.addAttribute("creditscore",creditscore);

            Userphoto userphoto = userphotoMapper.selectByuserid(sysuser.getId());
            model.addAttribute("userphoto",userphoto);

        }catch (Exception e){
            e.printStackTrace();
        }

        return new ModelAndView("/fronts/personalInfo");
    }


}
