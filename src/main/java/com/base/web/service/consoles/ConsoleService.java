package com.base.web.service.consoles;

import com.base.web.domain.User;
import com.base.web.domain.Resource;
import com.base.web.domain.Userresource;

import java.util.List;
import java.util.Map;

/**
 * Created by suyx on 2017/1/11.
 */
public interface ConsoleService {


    public List<User> getUserList(Map<String,Object> params);

    public void saveUser(User user);

    public int modifyPassword(Map<String,Object> params);

    public int deleteUser(Map<String,Object> params);

    public void saveResource(Resource consoleresourceesource);

    public int deleteResource(Map<String,Object> params);

    public List<Resource> getResourceList(Map<String,Object> params);

    public List<Userresource> getUserResource(Map<String,Object> params);

    public int saveAuth(Map<String,Object> params);

    public List<Map<String,Object>> getUserMenu(Map<String,Object> params);

    public List<Map<String,Object>> getUserMenu4admin(Map<String,Object> params);
}
