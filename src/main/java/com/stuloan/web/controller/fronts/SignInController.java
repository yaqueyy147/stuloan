package com.stuloan.web.controller.fronts;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.stuloan.web.domain.Sysuser;
import com.stuloan.web.mybatis.domain.Sms;
import com.stuloan.web.mybatis.domain.inte.SmsMapper;
import com.stuloan.web.service.fronts.UserService;
import com.stuloan.web.sms.SmsDemo;
import com.stuloan.web.util.CommonUtil;
import com.stuloan.web.util.CookieUtil;
import com.stuloan.web.util.DateUtils;
import com.stuloan.web.util.Userutils;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by suyx on 2016/12/18.
 */
@Controller
@RequestMapping(value = "/sign")
public class SignInController {

    private static final Logger LOGGER = Logger.getLogger(SignInController.class);

    private static final String SIGNNAME = "树丫丫的通知";
    private static final String TEMPLATECODE = "SMS_134080286";

    @Autowired
    private UserService userService;
    @Autowired
    private SmsMapper smsMapper;

    /**
     * 前台登录页面
     * @param model
     * @return
     */
    @RequestMapping(value = {"","/","/login"})
    public ModelAndView frontLogin(Model model, @ModelAttribute("loginCode") String loginCode){

        model.addAttribute("loginCode", loginCode);

        return new ModelAndView("/fronts/login");
    }

    /**
     * 登录
     * @param sysuser
     * @param ra
     * @param response
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/signIn")
    public RedirectView signIn(Sysuser sysuser, RedirectAttributes ra, HttpServletResponse response, HttpServletRequest request) throws UnsupportedEncodingException{

        String contextPath = request.getContextPath();
        sysuser.setPassword(CommonUtil.string2MD5(sysuser.getPassword()));
        //个人用户
        sysuser.setIsfront(1);
        List<Sysuser> listSysuser = userService.getUserInfo1(sysuser);
        Map<String,Object> mapUserInfo = new HashMap<String,Object>();
        //如果用户存在则为个人用户，则登录，跳转首页
        if(listSysuser != null && listSysuser.size() > 0){
            //将用户信息添加到cookie
            CookieUtil.addCookie(Userutils.FRONG_COOKIE_NAME, JSONObject.fromObject(listSysuser.get(0)).toString(),response);
            return new RedirectView(contextPath + "/fronts/index");
        }
        //否则跳回登录页面
        ra.addFlashAttribute("loginCode",-1);
        return new RedirectView(contextPath + "/sign/login");
    }

    /**
     * 前台注册页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/regedit")
    public ModelAndView regedit(Model model, String regCode){
        model.addAttribute("regCode",regCode);
        return new ModelAndView("/fronts/regedit");
    }

    /**
     * 前台注册页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/regeditPersonal")
    public ModelAndView regeditPersonal(Model model, String regCode){
        model.addAttribute("regCode",regCode);
        return new ModelAndView("/fronts/regedit_personal");
    }

    /**
     * 个人用户注册
     * @param sysuser
     * @return
     */
    @RequestMapping(value = "/regesterPersonal")
    public RedirectView regesterPersonal(Sysuser sysuser, HttpServletResponse response, HttpServletRequest request) throws UnsupportedEncodingException{
        String contextPath = request.getContextPath();
        int id = 0;
        JSONObject jsonObject = new JSONObject();
        //个人用户
        //检查用户名是否已经存在了
        List<Sysuser> list = userService.getUserInfo1(new Sysuser(sysuser.getLoginname()));

        if(list != null && list.size() > 0){
            return new RedirectView(contextPath + "/sign/regeditPersonal?regCode=-2");
        }

        sysuser.setId(CommonUtil.uuid());
        sysuser.setIsfront(1);
        sysuser.setState(1);
        sysuser.setUserfrom(1);
        sysuser.setCreateman(sysuser.getLoginname());
        sysuser.setCreatedate(CommonUtil.getDateLong());
        userService.createUser(sysuser);

        jsonObject = JSONObject.fromObject(sysuser);
        jsonObject.put("userType",1);
        //注册成功，自动登录，添加cookie
        CookieUtil.addCookie(Userutils.FRONG_COOKIE_NAME, jsonObject.toString(),response);

        return new RedirectView(contextPath + "/fronts/index");
    }

    /**
     * 退出登录
     * @param model
     * @param response
     * @param request
     * @return
     */
    @RequestMapping(value = "/logout")
    public RedirectView logout(Model model, HttpServletResponse response, HttpServletRequest request){
        String contextPath = request.getContextPath();
        //销毁登录用户信息cookie
        CookieUtil.destroyCookies(response,request);
        model.addAttribute("userInfo",null);
        //返回登录页面
        return new RedirectView(contextPath + "/sign/");
    }

    /**
     * 修改用户信息
     * @param sysuser
     * @return
     */
    @RequestMapping(value = "/modifyPersonalInfo")
    @ResponseBody
    public Map<String,Object> modifyPersonalInfo(Sysuser sysuser){
        Map<String,Object> map = new HashMap<String,Object>();

        Sysuser tSysuser11 = userService.getUserInfoFromId(sysuser.getId());
        sysuser.setIsfront(tSysuser11.getIsfront());
        sysuser.setIsconsole(tSysuser11.getIsconsole());
        sysuser.setUserfrom(tSysuser11.getUserfrom());
        sysuser.setState(tSysuser11.getState());
        sysuser.setCreatedate(tSysuser11.getCreatedate());

        userService.saveUser(sysuser);


        map.put("code",1);
        map.put("msg","修改成功!");
        return map;
    }


    /**
     * 修改密码
     * @param request
     * @param params
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/modifyPassword")
    @ResponseBody
    public Map<String,Object> modifyPassword(HttpServletRequest request,@RequestParam Map<String,Object> params) throws UnsupportedEncodingException{
        Map<String,Object> map = new HashMap<String,Object>();
        JSONObject jsonUser = CookieUtil.cookieValueToJsonObject(request,Userutils.FRONG_COOKIE_NAME);

        if(!CommonUtil.string2MD5(params.get("oldPassword") + "").equals(jsonUser.get("password"))){
            map.put("code",2);
            map.put("msg","原密码输入有误!");
            return map;
        }
        params.put("userType",jsonUser.get("userType"));
        int i = userService.modifyPassword(params);

        map.put("code",i);
        map.put("msg","修改成功!");
        return map;
    }

    /**
     * 修改头像
     * @param request
     * @param params
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/modifyphoto")
    @ResponseBody
    public Map<String,Object> modifyphoto(HttpServletRequest request,@RequestParam Map<String,Object> params) throws UnsupportedEncodingException{
        Map<String,Object> map = new HashMap<String,Object>();
        try {
            Sysuser cartoonuser = Userutils.getcookieuser(request,Userutils.FRONG_COOKIE_NAME);

            String photoPath = params.get("photoPath") + "";

            int i = userService.modifyPhoto(cartoonuser.getId(),photoPath,"1");

            map.put("code",i);
            map.put("msg","修改成功!");
        }catch (Exception e){
            map.put("code",0);
            map.put("msg","修改失败!");
        }
        return map;
    }

    /**
     * 登录失效或者用户信息验证失败跳转页面
     * @param model
     * @param type
     * @return
     */
    @RequestMapping(value = "/out")
    public ModelAndView redirectOut(Model model,int type){
//        System.out.println("进来了。。。");
        model.addAttribute("type",type);
        return new ModelAndView("/out");
    }

    /**
     * 短信验证码
     * @param request
     * @param params
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/smscode")
    @ResponseBody
    public Map<String,Object> smscode(HttpServletRequest request,@RequestParam Map<String,Object> params) throws UnsupportedEncodingException{
        Map<String,Object> map = new HashMap<String,Object>();
        try {
            String chkcode = CommonUtil.getRandomNumString(4);
            String phone = params.get("phone") + "";
            Sms sms = new Sms();
            sms.setId(CommonUtil.uuid());
            sms.setSmstype("1");
            sms.setSmscode(chkcode);
            sms.setSmsdesc(phone + "的注册验证码");
            sms.setTemplatecode(TEMPLATECODE);
            sms.setSignname(SIGNNAME);
            sms.setSmsphone(phone);
            sms.setSmstime(new Date());
            sms.setValidtime(DateUtils.addMinutes(new Date(),5));
            Map<String,Object> templateparam = new HashMap<>();
            templateparam.put("code",chkcode);
            sms.setTemplateparam(com.alibaba.fastjson.JSONObject.toJSONString(templateparam));

            SendSmsResponse response = SmsDemo.sendSms(sms);
            System.out.println("短信接口返回的数据----------------");
            System.out.println("Code=" + response.getCode());
            System.out.println("Message=" + response.getMessage());
            System.out.println("RequestId=" + response.getRequestId());
            System.out.println("BizId=" + response.getBizId());

            int i = 0;
            if("OK".equalsIgnoreCase(response.getCode())){

                i += smsMapper.insertSelective(sms);
            }

            map.put("code",i);
            map.put("chkcode",chkcode);
        }catch (Exception e){
            map.put("code",0);
            map.put("message","获取失败!");
        }
        return map;
    }

}
