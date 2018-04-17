package com.base.web.service.fronts;

import com.base.web.domain.User;

import java.util.List;
import java.util.Map;

/**
 * Created by suyx on 2016/12/18.
 */
public interface UserService {

    //创建一个用户
    public void createUser(User user);
    //修改用户信息
    public void saveUser(User user);

    //根据传入的用户信息查询用户主要用户名和密码，返回list<map>
    public List<Map<String,Object>> signIn(User user);

    //根据传入的用户信息查询用户主要用户名和密码，返回list<User>
    public List<User> getUserInfo1(User User);

    //修改密码
    public int modifyPassword(Map<String, Object> params);

    //修改头像
    public int modifyPhoto(String userId, String photoPath, String userType);

    //根据用户ID查询用户
    public User getUserInfoFromId(String userId);

    //设置用户是否可登陆后台
    public int setUserConsole(int userId,int state);

    //设置用户是否可登陆前台
    public int setUserFront(int userId,int state);

}
