package com.yunkang.saas.application.business.application.dao;

import com.yunkang.saas.application.business.application.domain.AppCategory;
import com.yunkang.saas.common.jpa.BaseDao;
import org.springframework.stereotype.Repository;



/**
 * 应用类别的数据库操作
 * @author icode
 */
@Repository("appCategoryDao")
public interface AppCategoryDao extends BaseDao<AppCategory, Long>{


}
