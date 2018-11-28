package net.aicoder.speedcloud.icode.business.project.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.speedcloud.icode.business.project.domain.Component;
import org.springframework.stereotype.Repository;


/**
 * 组件的数据库操作
 * @author icode
 */
@Repository("componentDao")
public interface ComponentDao extends BaseDao<Component, String>{


}
