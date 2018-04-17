package com.base.web.controller.fronts;

import com.base.web.domain.User;
import com.base.web.service.fronts.UserService;
import com.base.web.util.CommonUtil;
import com.base.web.util.CookieUtil;
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

    @Autowired
    private UserService userService;


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
     * @param user
     * @param ra
     * @param response
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/signIn")
    public RedirectView signIn(User user, RedirectAttributes ra, HttpServletResponse response, HttpServletRequest request) throws UnsupportedEncodingException{

        String contextPath = request.getContextPath();
        user.setPassword(CommonUtil.string2MD5(user.getPassword()));
        //个人用户
        user.setIsfront(1);
        List<User> listUser = userService.getUserInfo1(user);
        Map<String,Object> mapUserInfo = new HashMap<String,Object>();
        //如果用户存在则为个人用户，则登录，跳转首页
        if(listUser != null && listUser.size() > 0){
            //将用户信息添加到cookie
            CookieUtil.addCookie("userInfo", JSONObject.fromObject(listUser.get(0)).toString(),response);
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
     * @param user
     * @return
     */
    @RequestMapping(value = "/regesterPersonal")
    public RedirectView regesterPersonal(User user, HttpServletResponse response, HttpServletRequest request) throws UnsupportedEncodingException{
        String contextPath = request.getContextPath();
        int id = 0;
        JSONObject jsonObject = new JSONObject();
        //个人用户
        //检查用户名是否已经存在了
        List<User> list = userService.getUserInfo1(new User(user.getLoginname()));

        if(list != null && list.size() > 0){
            return new RedirectView(contextPath + "/sign/regeditPersonal?regCode=-2");
        }

        user.setId(CommonUtil.uuid());
        user.setIsfront(1);
        user.setState(1);
        user.setUserfrom(1);
        user.setCreateman(user.getLoginname());
        user.setCreatedate(CommonUtil.getDateLong());
        userService.createUser(user);

        jsonObject = JSONObject.fromObject(user);
        jsonObject.put("userType",1);
        //注册成功，自动登录，添加cookie
        CookieUtil.addCookie("userInfo", jsonObject.toString(),response);

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
     * @param user
     * @return
     */
    @RequestMapping(value = "/modifyPersonalInfo")
    @ResponseBody
    public Map<String,Object> modifyPersonalInfo(User user){
        Map<String,Object> map = new HashMap<String,Object>();

        User tUser11 = userService.getUserInfoFromId(user.getId());
        user.setIsfront(tUser11.getIsfront());
        user.setIsconsole(tUser11.getIsconsole());
        user.setUserfrom(tUser11.getUserfrom());
        user.setState(tUser11.getState());
        user.setCreatedate(tUser11.getCreatedate());

        userService.saveUser(user);


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
        JSONObject jsonUser = CookieUtil.cookieValueToJsonObject(request,"userInfo");

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

}
