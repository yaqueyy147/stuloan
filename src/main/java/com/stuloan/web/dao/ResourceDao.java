package com.stuloan.web.dao;

import com.stuloan.web.domain.Resource;
import com.stuloan.web.hibernate.BaseHibernateDao;
import org.springframework.stereotype.Repository;

/**
 * Created by suyx on 2016/12/21 0021.
 */
@Repository("resourceDao")
public class ResourceDao extends BaseHibernateDao<Resource> {
}
