package com.stuloan.web.controller.tools;

import com.stuloan.web.mybatis.domain.Sysuser;
import com.stuloan.web.mybatis.domain.Userphoto;
import com.stuloan.web.mybatis.domain.inte.SysuserMapper;
import com.stuloan.web.mybatis.domain.inte.UserphotoMapper;
import com.stuloan.web.util.CommonUtil;
import com.stuloan.web.util.Userutils;
import cq.hlideal.jetty.main.util.FileUtil;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by suyx on 2017/1/7.
 */
@Controller
@RequestMapping(value = "/upload")
public class UploadController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UploadController.class);

    @Autowired
    private UserphotoMapper userphotoMapper;

    @Autowired
    private SysuserMapper sysuserMapper;

    /**
     * 上传图片
     * @param uploadFile
     * @param targetFile
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/uploadImg")
    @ResponseBody
    public String uploadImg(MultipartFile uploadFile, String targetFile, String id, String type, HttpServletRequest request, HttpServletResponse response) throws IOException {

        String resultStr = "";
        try {
            String path = request.getSession().getServletContext().getRealPath("");//项目根目录
            path = path.substring(0,path.indexOf("\\webapp") + 7) + "\\static" + targetFile;//上传图片的地址
            String filePath = CommonUtil.uploadFile(path, uploadFile);//上传图片
            filePath = filePath.replace("\\","/");//将路径中的\替换为/
            filePath = filePath.substring(filePath.indexOf("/static"));
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("code",1);
            map.put("filePath",filePath);

            Userphoto userphoto = new Userphoto();
            userphoto.setUserid(Userutils.getuserid(request,Userutils.FRONG_COOKIE_NAME));
            if("1".equals(type)){
                userphoto.setHeadphoto(filePath);
                userphoto.setHeadstate("5");
            }
            if("2".equals(type)){
                userphoto.setIdcardphoto(filePath);
                userphoto.setIdcardstate("5");
            }
            if("3".equals(type)){
                userphoto.setStuidcardphoto(filePath);
                userphoto.setStuidcardstate("5");
            }
            if(!CommonUtil.isBlank(id)){
                userphoto.setId(id);
                userphotoMapper.updateByPrimaryKeySelective(userphoto);
            }else{
                userphoto.setId(CommonUtil.uuid());
                userphotoMapper.insertSelective(userphoto);
            }

            resultStr = JSONObject.fromObject(map).toString();
        }catch (Exception e){
            e.printStackTrace();

        }

        return resultStr;
    }


    /**
     * 删除图片
     * @param request
     * @param response
     */
    @RequestMapping("deleteimg")
    @ResponseBody
    public Object deletecenoimg(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String,Object> params){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("code", 0);
        try{
            String path = request.getSession().getServletContext().getRealPath("");
            String imgpath = params.get("imgpath") + "";
            String type = params.get("type") + "";
            String id = params.get("id") + "";
            if(!CommonUtil.isBlank(id)){
                Userphoto userphoto = userphotoMapper.selectByPrimaryKey(id);
                if("1".equals(type)){
                    userphoto.setHeadphoto("");
                    userphoto.setHeadstate("0");
                }
                if("2".equals(type)){
                    userphoto.setIdcardphoto("");
                    userphoto.setIdcardstate("0");
                }
                if("3".equals(type)){
                    userphoto.setStuidcardphoto("");
                    userphoto.setStuidcardstate("0");
                }
                userphotoMapper.updateByPrimaryKeySelective(userphoto);

                if(!"1".equals(userphoto.getHeadstate()) && !"1".equals(userphoto.getIdcardstate()) && !"1".equals(userphoto.getStuidcardstate())){
                    //修改用户图片状态
                    Sysuser sysuser = sysuserMapper.selectByPrimaryKey(userphoto.getUserid());
                    sysuser.setPhotostate("0");
                    sysuserMapper.updateByPrimaryKeySelective(sysuser);
                }

            }

            boolean bb = FileUtil.delete(path + imgpath);
            if(!bb){
                LOGGER.info("文件(" + imgpath + ")删除失败!");
            }
            map.put("code", 1);
            map.put("message","图片删除成功!");
        }catch (Exception e){
            e.printStackTrace();
            map.put("message","修改失败，请稍后重试");
        }
        return map;
    }

}
