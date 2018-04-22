package com.stuloan.web.controller.consoles;

import com.stuloan.web.mybatis.domain.Creditmoney;
import com.stuloan.web.mybatis.domain.inte.CreditmoneyMapper;
import com.stuloan.web.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by suyx on 2018-04-21.
 */
@Controller
@RequestMapping(value = "/consoles")
public class CreditmoneyController {

    @Autowired
    private CreditmoneyMapper creditmoneyMapper;

    @RequestMapping(value = "/creditmoney")
    public ModelAndView statefee(Model model){
        return new ModelAndView("/consoles/creditmoney");
    }

    @RequestMapping(value = "/creditmoneylist")
    @ResponseBody
    public Object creditmoneylist(HttpServletRequest request, @RequestParam Map<String,Object> params){
        Map<String,Object> result = new HashMap<>();
        result.put("total",0);
        int totalcount = creditmoneyMapper.selectCountByParams(params);
        result.put("rows",totalcount);
        List<Creditmoney> list = creditmoneyMapper.selectByParams(params);
        result.put("rows",list);
        return result;
    }

    @RequestMapping(value = "savecreditmoney")
    @ResponseBody
    public Object savecreditmoney(Creditmoney creditmoney){
        Map<String,Object> result = new HashMap<>();
        result.put("code",0);
        result.put("message","保存失败");

        try{
            int i = 0;
            if(CommonUtil.isBlank(creditmoney.getId()) || "0".equals(creditmoney.getId())){
                creditmoney.setId(CommonUtil.uuid());
                i += creditmoneyMapper.insert(creditmoney);
            }else{
                i = creditmoneyMapper.updateByPrimaryKey(creditmoney);
            }

            result.put("code",1);
            result.put("message","保存成功");
        }catch (Exception e){
            result.put("code",0);
            result.put("message","保存失败");
        }

        return result;
    }

    @RequestMapping(value = "deletecreditmoney")
    @ResponseBody
    public Object deletecreditmoney(String ids){
        Map<String,Object> result = new HashMap<>();
        result.put("code",0);
        result.put("message","删除失败");
        String[] idarr = ids.split(",");
        try{
            int ii = 0;
            for(int i=0;i<idarr.length;i++){
                String id = idarr[i];
                ii += creditmoneyMapper.deleteByPrimaryKey(id);
            }
            result.put("code",1);
            result.put("message","删除成功");
        }catch (Exception e){
            result.put("code",0);
            result.put("message","删除失败");
        }

        return result;
    }


}
