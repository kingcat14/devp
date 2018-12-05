package net.aicoder.speedcloud.icode.business.domain.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.speedcloud.icode.business.domain.domain.EntityActionParameterProperty;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 领域对象行为参数属性的数据库操作
 * @author icode
 */
@Repository("entityActionParameterPropertyDao")
public interface EntityActionParameterPropertyDao extends BaseDao<EntityActionParameterProperty, String>{

    List<EntityActionParameterProperty> findByActionParameter(String parameterId);

    int deleteByActionParameter(String paramId);
}
