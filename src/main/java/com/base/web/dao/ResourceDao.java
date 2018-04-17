package com.base.web.dao;

import com.base.web.domain.Resource;
import com.base.web.hibernate.BaseHibernateDao;
import org.springframework.stereotype.Repository;

/**
 * Created by suyx on 2016/12/21 0021.
 */
@Repository("consoleresourceDao")
public class ResourceDao extends BaseHibernateDao<Resource> {
}
