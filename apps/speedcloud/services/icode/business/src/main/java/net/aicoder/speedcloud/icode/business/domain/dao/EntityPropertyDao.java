package net.aicoder.speedcloud.icode.business.domain.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.speedcloud.icode.business.domain.domain.EntityProperty;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 领域对象属性的数据库操作
 * @author icode
 */
@Repository("entityPropertyDao")
public interface EntityPropertyDao extends BaseDao<EntityProperty, String>{

    List<EntityProperty> findByEntity(String entity);

    @Modifying
    int deleteByEntity(String entity);

}
