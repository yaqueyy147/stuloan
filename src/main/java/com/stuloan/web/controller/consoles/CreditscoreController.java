package com.stuloan.web.controller.consoles;

import com.stuloan.web.mybatis.domain.Creditscore;
import com.stuloan.web.mybatis.domain.Sysuser;
import com.stuloan.web.mybatis.domain.inte.CreditmoneyMapper;
import com.stuloan.web.mybatis.domain.inte.CreditscoreMapper;
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
public class CreditscoreController {

    @Autowired
    private CreditscoreMapper creditscoreMapper;
    @Autowired
    private CreditmoneyMapper creditmoneyMapper;

    @Autowired
    private SysuserMapper sysuserMapper;

    @RequestMapping(value = "/creditscore")
    public ModelAndView creditscore(@RequestParam Map<String,Object> params){
        return new ModelAndView("/consoles/creditscore");
    }

    @RequestMapping(value = "/creditscorelist")
    @ResponseBody
    public Object stuinfolist(@RequestParam Map<String,Object> params){
        Map<String,Object> result = new HashMap<String,Object>();

        int totalcount = creditscoreMapper.selectCountByParams(params);
        result.put("total",totalcount);

        int pageNumber = CommonUtil.parseInt(params.get("pageNumber"));
        int pageSize = CommonUtil.parseInt(params.get("pageSize"));
        int beginRow = (pageNumber - 1) * pageSize;
        params.put("beginRow",beginRow);

        List<Map<String,Object>> list = creditscoreMapper.selectByParams02(params);
        result.put("rows",list);
        return result;
    }

    @RequestMapping(value = "/toauditcredit")
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
                Creditscore creditscore = creditscoreMapper.selectByPrimaryKey(id);

                if("1".equals(state)){
                    creditscore.setState(state);
                    creditscoreMapper.updateByPrimaryKeySelective(creditscore);

                    params.put("score",creditscore.getCreditscoretotal());
                    Double maxloanmoney = creditmoneyMapper.selectloanmoneyByParams(params);
                    Sysuser sysuser = new Sysuser();
                    sysuser.setId(creditscore.getUserid());
                    sysuser.setLoanlimit(maxloanmoney);
                    sysuser.setLoanlimitleft(maxloanmoney);
                    ii += sysuserMapper.updateByPrimaryKeySelective(sysuser);
                }else{
                    creditscoreMapper.deleteByPrimaryKey(id);

                    Sysuser sysuser = new Sysuser();
                    sysuser.setId(creditscore.getUserid());
                    sysuser.setIscreditidentity(state);
                    ii += sysuserMapper.updateByPrimaryKeySelective(sysuser);
                }
            }
            result.put("code",1);
            result.put("message","操作完成!");
        }catch (Exception e){
            e.printStackTrace();
            result.put("code",0);
            result.put("message","系统错误，请联系管理员!");
        }
        return result;
    }

}
