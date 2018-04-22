package com.stuloan.web.util;

import com.stuloan.web.domain.Sysuser;
import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2018/3/7 0007.
 */
public class Userutils {

    public static final String FRONG_COOKIE_NAME = "userInfo";
    public static final String CONSOLE_COOKIE_NAME = "consoleUserInfo";

    public static Sysuser getcookieuser(HttpServletRequest request, String cookiename) throws Exception{
        JSONObject jsonUser = CookieUtil.cookieValueToJsonObject(request,cookiename);
        Sysuser sysuser = (Sysuser)JSONObject.toBean(jsonUser,Sysuser.class);
        return sysuser;
    }

    public static String getuserid(HttpServletRequest request, String cookiename) throws Exception{
        return getcookieuser(request,cookiename).getId();
    }

    public static String getusername(HttpServletRequest request, String cookiename) throws Exception{
        return getcookieuser(request,cookiename).getUsername();
    }

    public static String getloginname(HttpServletRequest request, String cookiename) throws Exception{
        return getcookieuser(request,cookiename).getLoginname();
    }
}
