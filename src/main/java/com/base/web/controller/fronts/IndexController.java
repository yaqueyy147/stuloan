package com.base.web.controller.fronts;

import com.base.web.service.consoles.ConsoleService;
import com.base.web.service.fronts.UserService;
import com.base.web.util.CookieUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by suyx on 2016/12/18.
 */
@Controller
@RequestMapping(value = "/fronts")
public class IndexController {
    private static final int PAGE_SIZE = 20;//初始每页条数
    private static final int PAGE_NUM = 6;//初始显示页数
    @Autowired
    private ConsoleService consoleService;
    @Autowired
    private UserService userService;
    @Autowired
    private CartoonService cartoonService;

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
        JSONObject jsonUser = CookieUtil.cookieValueToJsonObject(request,"userInfo");
        model.addAttribute("userInfo",jsonUser);
//        if(CommonUtil.isBlank(jsonUser)){
//            return new ModelAndView("/fronts/login");
//        }

        List<Cartooninfo> list = cartoonService.getCartoonList(null);
        model.addAttribute("cartoonlist",list);
        return new ModelAndView("/fronts/index");
    }

    /**
     * 首页查询族谱
     * @param params
     * @return
     */
    @RequestMapping(value = "/querycartoon")
    @ResponseBody
    public Map<String,Object> queryFamily(@RequestParam Map<String,Object> params){
        String searchname = params.get("searchname") + "";

        Map<String,Object> result = new HashMap<String,Object>();


        return result;
    }


}
