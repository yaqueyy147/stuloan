package com.stuloan.web.controller.consoles;

import com.stuloan.web.mybatis.domain.Studentinfo;
import com.stuloan.web.mybatis.domain.Sysuser;
import com.stuloan.web.mybatis.domain.inte.StudentinfoMapper;
import com.stuloan.web.mybatis.domain.inte.SysuserMapper;
import com.stuloan.web.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by suyx on 2018-04-24.
 */
@Controller
@RequestMapping(value = "/consoles")
public class StuidentityController {

    @Autowired
    private StudentinfoMapper studentinfoMapper;
    @Autowired
    private SysuserMapper sysuserMapper;

    @RequestMapping(value = "/stuinfo")
    public ModelAndView stuinfo(@RequestParam Map<String,Object> params){
        return new ModelAndView("/consoles/stuinfoidentity");
    }

    @RequestMapping(value = "/stuinfolist")
    @ResponseBody
    public Object stuinfolist(@RequestParam Map<String,Object> params){
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("code",0);

        int totalcount = studentinfoMapper.selectCountByParams(params);
        result.put("total",totalcount);

        int pageNumber = CommonUtil.parseInt(params.get("pageNumber"));
        int pageSize = CommonUtil.parseInt(params.get("pageSize"));
        int beginRow = (pageNumber - 1) * pageSize;
        params.put("beginRow",beginRow);

        List<Map<String,Object>> list = studentinfoMapper.selectByParams02(params);
        result.put("rows",list);
        return result;
    }

    @RequestMapping(value = "/toauditstuinfo")
    @ResponseBody
    public Object toauditidentity(@RequestParam Map<String,Object> params){
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("code",0);
        result.put("message","系统错误，请联系管理员!");
        String state = params.get("state") + "";
        String ids = params.get("ids") + "";
        String[] idarr = ids.split(",");

        try {
            int ii = 0;
            for(int i=0;i<idarr.length;i++){
                String id = idarr[i];

                Studentinfo studentinfo = studentinfoMapper.selectByPrimaryKey(id);
//                studentinfo.setIsstuidentity(state);
//                ii += studentinfoMapper.updateByPrimaryKeySelective(studentinfo);
                Sysuser sysuser = new Sysuser();
                sysuser.setId(studentinfo.getUserid());
                sysuser.setIsstuidentity(state);
                ii += sysuserMapper.updateByPrimaryKeySelective(sysuser);

                if("1".equals(state)){
                    studentinfo.setIsstuidentity(state);
                    ii += studentinfoMapper.updateByPrimaryKeySelective(studentinfo);
                }else{
                    studentinfoMapper.deleteByPrimaryKey(id);
                }

            }
            result.put("code",1);
            result.put("message","操作完成!");
        }catch (Exception e){
            result.put("code",0);
            result.put("message","系统错误，请联系管理员!");
        }
        return result;
    }

}
