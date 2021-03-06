package com.stuloan.web.service.consoles.impl;

import com.stuloan.web.dao.SysuserDao;
import com.stuloan.web.dao.ResourceDao;
import com.stuloan.web.dao.UserresourceDao;
import com.stuloan.web.domain.Sysuser;
import com.stuloan.web.domain.Resource;
import com.stuloan.web.domain.Userresource;
import com.stuloan.web.service.consoles.ConsoleService;
import com.stuloan.web.util.CommonUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by suyx on 2017/1/11.
 */
@Service("consoleService")
public class ConsoleServiceImpl implements ConsoleService {

    @javax.annotation.Resource
    private SysuserDao sysuserDao;

    public void setSysuserDao(SysuserDao sysuserDao) {
        this.sysuserDao = sysuserDao;
    }

    @javax.annotation.Resource
    private ResourceDao resourceDao;

    public void setResourceDao(ResourceDao resourceDao) {
        this.resourceDao = resourceDao;
    }

    @javax.annotation.Resource
    private UserresourceDao userresourceDao;

    public void setUserresourceDao(UserresourceDao userresourceDao) {
        this.userresourceDao = userresourceDao;
    }

    @javax.annotation.Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Sysuser> getUserList(Map<String, Object> params) {

        String sql = "select * from sysuser where state<>9";

        if(!CommonUtil.isBlank(params.get("id"))){
            sql += " and id='" + params.get("id") + "'";
        }
        if(!CommonUtil.isBlank(params.get("userfrom"))){
            sql += " and userfrom='" + params.get("userfrom") + "'";
        }
        if(!CommonUtil.isBlank(params.get("username"))){
            sql += " and username like '%" + params.get("username") + "%'";
        }
        if(!CommonUtil.isBlank(params.get("loginname"))){
            sql += " and loginname like '%" + params.get("loginname") + "%'";
        }
        if(!CommonUtil.isBlank(params.get("province"))){
            sql += " and province='" + params.get("province") + "'";
        }
        if(!CommonUtil.isBlank(params.get("city"))){
            sql += " and city='" + params.get("city") + "'";
        }
        if(!CommonUtil.isBlank(params.get("district"))){
            sql += " and district='" + params.get("district") + "'";
        }
        if(!CommonUtil.isBlank(params.get("usertype"))){
            if(CommonUtil.parseInt(params.get("usertype")) == 1){
                sql += " and isfront=1";
            }
            if(CommonUtil.parseInt(params.get("usertype")) == 2){
                sql += " and isconsole=1";
            }
        }
        if(!CommonUtil.isBlank(params.get("orderby"))){
            sql += " order by " + params.get("orderby") + "";
        }
        List<Sysuser> list = jdbcTemplate.query(sql,new BeanPropertyRowMapper<Sysuser>(Sysuser.class));

        return list;
    }

    @Override
    public int gettotalusercount(Map<String, Object> params) {
        String sql = "select count(*) as count from sysuser where state<>9";

        if(!CommonUtil.isBlank(params.get("id"))){
            sql += " and id='" + params.get("id") + "'";
        }
        if(!CommonUtil.isBlank(params.get("userfrom"))){
            sql += " and userfrom='" + params.get("userfrom") + "'";
        }
        if(!CommonUtil.isBlank(params.get("username"))){
            sql += " and username like '%" + params.get("username") + "%'";
        }
        if(!CommonUtil.isBlank(params.get("loginname"))){
            sql += " and loginname like '%" + params.get("loginname") + "%'";
        }
        if(!CommonUtil.isBlank(params.get("province"))){
            sql += " and province='" + params.get("province") + "'";
        }
        if(!CommonUtil.isBlank(params.get("city"))){
            sql += " and city='" + params.get("city") + "'";
        }
        if(!CommonUtil.isBlank(params.get("district"))){
            sql += " and district='" + params.get("district") + "'";
        }
        List<Map<String,Object>> list = jdbcTemplate.queryForList(sql);
        if(list != null && list.size() >0){
            return list.size();
        }
        return 0;
    }

    @Override
    public void saveUser(Sysuser sysuser) {
        int i = 0;
        sysuserDao.save(sysuser);
    }

    @Override
    public int modifyPassword(Map<String, Object> params) {
        String newPassword = CommonUtil.string2MD5(params.get("newPassword") + "");
        String sql = "update sysuser set password=? where id=?";

        int i = jdbcTemplate.update(sql,newPassword,params.get("userId"));

        return i;
    }

    @Override
    public int deleteUser(Map<String, Object> params) {

        String ids = params.get("ids") + "";
        String[] id = ids.split(",");

        String sql = "update sysuser set state=9 where id=?";
        int ii = 0;
        for(int i=0;i<id.length;i++){
            ii += jdbcTemplate.update(sql,id[i]);
        }

        return ii;
    }

    @Override
    public void saveResource(Resource resource) {
        int i = 0;
        //如果resource的ID大于0，则为修改
        resourceDao.save(resource);
    }

    @Override
    public int deleteResource(Map<String, Object> params) {
        String ids = params.get("ids") + "";
        String[] id = ids.split(",");

        String sql = "update resource set state=9 where id=?";
        int ii = 0;
        for(int i=0;i<id.length;i++){
            ii += jdbcTemplate.update(sql,id[i]);
        }
        return ii;
    }

    @Override
    public List<Resource> getResourceList(Map<String, Object> params) {
        String sql = "select * from resource where state<>9";

        if(!CommonUtil.isBlank(params)){
            if(!CommonUtil.isBlank(params.get("id"))){
                sql += " and id='" + params.get("id") + "'";
            }
            if(!CommonUtil.isBlank(params.get("sourcename"))){
                sql += " and sourcename='" + params.get("sourcename") + "'";
            }
        }

        List<Resource> list = jdbcTemplate.query(sql,new BeanPropertyRowMapper<Resource>(Resource.class));
//        List<TRole> list = tRoleDao.find(params);
        return list;
    }

    @Override
    public List<Userresource> getUserResource(Map<String,Object> params){

        String hql = " from Userresource where userid=?";
        List<Userresource> list = userresourceDao.find(hql,params.get("userid"));

        return list;
    }

    @Override
    public int saveAuth(Map<String,Object> params){
        int ii = 0;

        String userId = params.get("userid") + "";
        String sourceIds = params.get("sourceIds") + "";
        String[] sourceIdArr = sourceIds.split(",");

        //先删除当前用户的权限
        String del = "delete from userresource where userid=?";

        jdbcTemplate.update(del,userId);

        for(int i=0;i<sourceIdArr.length;i++){
            Userresource userresource = new Userresource(CommonUtil.uuid());
            userresource.setUserid(userId);
            userresource.setResourceid(sourceIdArr[i]);
            userresourceDao.save(userresource);
            ii ++;
        }

        return ii;
    }

    @Override
    public List<Map<String,Object>> getUserMenu(Map<String,Object> params){

        String sql = "select t1.*,t2.userid from resource t1,userresource t2";
        sql += " where t1.id=t2.resourceid and t1.state=1 and t2.userid='" + params.get("userid") + "'";
        List<Map<String,Object>> list = jdbcTemplate.queryForList(sql);
        return list;
    }

    @Override
    public List<Map<String, Object>> getUserMenu4admin(Map<String, Object> params) {
        String sql = "select *from resource";
        sql += " where state=1";
        List<Map<String,Object>> list = jdbcTemplate.queryForList(sql);
        return list;
    }

}

