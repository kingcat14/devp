package net.aicoder.speedcloud.icode.business.domain.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.speedcloud.icode.business.domain.domain.EntityViewProperty;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 领域对象展现属性的数据库操作
 * @author icode
 */
@Repository("entityViewPropertyDao")
public interface EntityViewPropertyDao extends BaseDao<EntityViewProperty, String>{

    List<EntityViewProperty> findByEntity(String entity);

    @Modifying
    int deleteByEntity(String entity);
}
