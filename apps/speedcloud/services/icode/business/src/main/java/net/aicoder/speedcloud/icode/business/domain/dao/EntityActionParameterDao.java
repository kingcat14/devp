package net.aicoder.speedcloud.icode.business.domain.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.speedcloud.icode.business.domain.domain.EntityActionParameter;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 领域对象行为参数的数据库操作
 * @author icode
 */
@Repository("entityActionParameterDao")
public interface EntityActionParameterDao extends BaseDao<EntityActionParameter, String>{

    List<EntityActionParameter> findByEntityAction(String actionId);

    @Modifying
    int deleteByEntityAction(String actionId);

}
