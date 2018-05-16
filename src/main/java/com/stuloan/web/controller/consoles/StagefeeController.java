package com.stuloan.web.controller.consoles;

import com.stuloan.web.mybatis.domain.Stagefee;
import com.stuloan.web.mybatis.domain.inte.StagefeeMapper;
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
public class StagefeeController {

    @Autowired
    private StagefeeMapper stagefeeMapper;

    @RequestMapping(value = "/stagefee")
    public ModelAndView statefee(Model model){
        return new ModelAndView("/consoles/stagefee");
    }

    @RequestMapping(value = "/stagefeelist")
    @ResponseBody
    public Object stagefeelist(HttpServletRequest request, @RequestParam Map<String,Object> params){
        Map<String,Object> result = new HashMap<>();
        int totalcount = stagefeeMapper.selectCountByParams(params);
        result.put("total",totalcount);

        int pageNumber = CommonUtil.parseInt(params.get("pageNumber"));
        int pageSize = CommonUtil.parseInt(params.get("pageSize"));
        int beginRow = (pageNumber - 1) * pageSize;
        params.put("beginRow",beginRow);

        List<Stagefee> list = stagefeeMapper.selectByParams(params);
        result.put("rows",list);
        return result;
    }

    @RequestMapping(value = "savestagefee")
    @ResponseBody
    public Object savestagefee(Stagefee stagefee){
        Map<String,Object> result = new HashMap<>();
        result.put("code",0);
        result.put("message","保存失败");

        try{
            int i = 0;
            //先查看改分期是否已存在
            Map<String,Object> params = new HashMap<>();
            params.put("stagenum",stagefee.getStagenum());
            List<Stagefee> ll = stagefeeMapper.selectByParams(params);
            if(ll != null && ll.size() > 0){
                stagefee.setId(ll.get(0).getId());
            }

            if(CommonUtil.isBlank(stagefee.getId()) || "0".equals(stagefee.getId())){
                stagefee.setId(CommonUtil.uuid());
                i += stagefeeMapper.insert(stagefee);
            }else{
                i = stagefeeMapper.updateByPrimaryKey(stagefee);
            }

            result.put("code",1);
            result.put("message","保存成功");
        }catch (Exception e){
            result.put("code",0);
            result.put("message","保存失败");
        }

        return result;
    }

    @RequestMapping(value = "deletestagefee")
    @ResponseBody
    public Object deletestagefee(String ids){
        Map<String,Object> result = new HashMap<>();
        result.put("code",0);
        result.put("message","删除失败");
        String[] idarr = ids.split(",");
        try{
            int ii = 0;
            for(int i=0;i<idarr.length;i++){
                String id = idarr[i];
                ii += stagefeeMapper.deleteByPrimaryKey(id);
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
