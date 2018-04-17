package com.base.web.dao;

import com.base.web.domain.User;
import com.base.web.hibernate.BaseHibernateDao;
import org.springframework.stereotype.Repository;

/**
 * Created by suyx on 2016/12/21 0021.
 */
@Repository("cartoonuserDao")
public class UserDao extends BaseHibernateDao<User> {
}
