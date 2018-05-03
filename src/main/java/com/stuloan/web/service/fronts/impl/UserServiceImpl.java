package com.stuloan.web.service.fronts.impl;

import com.stuloan.web.dao.SysuserDao;
import com.stuloan.web.domain.Sysuser;
import com.stuloan.web.service.fronts.UserService;
import com.stuloan.web.util.CommonUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by suyx on 2016/12/18.
 */
@Service("userService")
public class UserServiceImpl implements UserService {


    @Resource
    private SysuserDao sysuserDao;

    public void setSysuserDao(SysuserDao sysuserDao) {
        this.sysuserDao = sysuserDao;
    }

    @Resource
    private JdbcTemplate jdbcTemplate;

    /**
     * 用户注册
     * @param sysuser
     * @return
     */
    @Override
    public void createUser(Sysuser sysuser) {
        sysuser.setPassword(CommonUtil.string2MD5(sysuser.getPassword()));
        int id = 0;
        try {
            sysuserDao.save(sysuser);
        }catch (Exception da){
            da.printStackTrace();
        }
    }

    @Override
    public void saveUser(Sysuser sysuser) {
        int i = 0;
        try {
            sysuserDao.save(sysuser);
            i ++;
        }catch (Exception e){

        }
    }

    /**
     * 用户登录
     * @param sysuser
     * @return
     */
    @Override
    public List<Map<String, Object>> signIn(Sysuser sysuser) {
        String sql = "select * from sysuser where loginname=? and password=?";
        //将密码加密
        String password = CommonUtil.string2MD5(sysuser.getPassword());
        List<Map<String,Object>> list = jdbcTemplate.queryForList(sql, sysuser.getUsername(),password);
        return list;
    }

    /**
     * 根据传入的用户信息查询用户
     * @param sysuser
     * @return
     */
    @Override
    public List<Sysuser> getUserInfo1(Sysuser sysuser) {
        String sql = "select * from sysuser where state=1";// and password=?

        if(!CommonUtil.isBlank(sysuser.getIsfront()) && sysuser.getIsfront() == 1){
            sql += " and isfront='1'" ;
        }
        if(!CommonUtil.isBlank(sysuser.getIsconsole()) && sysuser.getIsconsole() == 1){
            sql += " and isconsole='1'" ;
        }
        if(!CommonUtil.isBlank(sysuser.getLoginname())){
            sql += " and loginname='"+ sysuser.getLoginname() + "'" ;
        }
        if(!CommonUtil.isBlank(sysuser.getUsername())){
            sql += " and username='"+ sysuser.getUsername() + "'" ;
        }
        if(!CommonUtil.isBlank(sysuser.getPassword())){
            sql += " and password='"+ sysuser.getPassword() + "'" ;
        }
        if(!CommonUtil.isBlank(sysuser.getPhone())){
            sql += " and phone='"+ sysuser.getPhone() + "'" ;
        }

        //查询
        List<Sysuser> list = jdbcTemplate.query(sql,new BeanPropertyRowMapper<Sysuser>(Sysuser.class));

        return list;
    }

    @Override
    public int modifyPassword(Map<String,Object> params) {
        String userId = params.get("userId") + "";
        String newPassword = CommonUtil.string2MD5(params.get("newPassword") + "");
        String sql = "update sysuser set password=? where id=?";

        int i = jdbcTemplate.update(sql,newPassword,userId);

        return i;
    }

    @Override
    public int modifyPhoto(String userId, String photoPath, String userType) {

        String sql = "update sysuser set userphoto=? where id=?";

        int i = jdbcTemplate.update(sql,photoPath,userId);
        return i;
    }

    @Override
    public Sysuser getUserInfoFromId(String userId) {
        Sysuser tSysuser1 = sysuserDao.get(userId);
        return tSysuser1;
    }

    @Override
    public int setUserConsole(int userId, int state) {

        String sql = "update sysuser set isconsole=? where id=?";

        int i = jdbcTemplate.update(sql,state,userId);

        return i;
    }

    @Override
    public int setUserFront(int userId, int state) {
        String sql = "update sysuser set isfront=? where id=?";

        int i = jdbcTemplate.update(sql,state,userId);

        return i;
    }

}
