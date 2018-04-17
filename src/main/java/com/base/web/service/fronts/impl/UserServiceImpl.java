package com.base.web.service.fronts.impl;

import com.base.web.dao.UserDao;
import com.base.web.domain.User;
import com.base.web.service.fronts.UserService;
import com.base.web.util.CommonUtil;
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
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Resource
    private JdbcTemplate jdbcTemplate;

    /**
     * 用户注册
     * @param user
     * @return
     */
    @Override
    public void createUser(User user) {
        user.setPassword(CommonUtil.string2MD5(user.getPassword()));
        int id = 0;
        try {
            userDao.save(user);
        }catch (Exception da){
            da.printStackTrace();
        }
    }

    @Override
    public void saveUser(User user) {
        int i = 0;
        try {
            userDao.save(user);
            i ++;
        }catch (Exception e){

        }
    }

    /**
     * 用户登录
     * @param user
     * @return
     */
    @Override
    public List<Map<String, Object>> signIn(User user) {
        String sql = "select * from cartoonuser where loginname=? and password=?";
        //将密码加密
        String password = CommonUtil.string2MD5(user.getPassword());
        List<Map<String,Object>> list = jdbcTemplate.queryForList(sql, user.getUsername(),password);
        return list;
    }

    /**
     * 根据传入的用户信息查询用户
     * @param user
     * @return
     */
    @Override
    public List<User> getUserInfo1(User user) {
        String sql = "select * from cartoonuser where state=1";// and password=?

        if(!CommonUtil.isBlank(user.getIsfront()) && user.getIsfront() == 1){
            sql += " and isfront='1'" ;
        }
        if(!CommonUtil.isBlank(user.getIsconsole()) && user.getIsconsole() == 1){
            sql += " and isconsole='1'" ;
        }
        if(!CommonUtil.isBlank(user.getLoginname())){
            sql += " and loginname='"+ user.getLoginname() + "'" ;
        }
        if(!CommonUtil.isBlank(user.getUsername())){
            sql += " and username='"+ user.getUsername() + "'" ;
        }
        if(!CommonUtil.isBlank(user.getPassword())){
            sql += " and password='"+ user.getPassword() + "'" ;
        }

        //查询
        List<User> list = jdbcTemplate.query(sql,new BeanPropertyRowMapper<User>(User.class));

        return list;
    }

    @Override
    public int modifyPassword(Map<String,Object> params) {
        String userId = params.get("userId") + "";
        String newPassword = CommonUtil.string2MD5(params.get("newPassword") + "");
        String sql = "update cartoonuser set password=? where id=?";

        int i = jdbcTemplate.update(sql,newPassword,userId);

        return i;
    }

    @Override
    public int modifyPhoto(String userId, String photoPath, String userType) {

        String sql = "update cartoonuser set userphoto=? where id=?";

        int i = jdbcTemplate.update(sql,photoPath,userId);
        return i;
    }

    @Override
    public User getUserInfoFromId(String userId) {
        User tUser1 = userDao.get(userId);
        return tUser1;
    }

    @Override
    public int setUserConsole(int userId, int state) {

        String sql = "update cartoonuser set isconsole=? where id=?";

        int i = jdbcTemplate.update(sql,state,userId);

        return i;
    }

    @Override
    public int setUserFront(int userId, int state) {
        String sql = "update cartoonuser set isfront=? where id=?";

        int i = jdbcTemplate.update(sql,state,userId);

        return i;
    }

}
