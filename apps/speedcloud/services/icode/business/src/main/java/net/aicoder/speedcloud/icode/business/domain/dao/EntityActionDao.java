package net.aicoder.speedcloud.icode.business.domain.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.speedcloud.icode.business.domain.domain.EntityAction;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 领域对象行为的数据库操作
 * @author icode
 */
@Repository("entityActionDao")
public interface EntityActionDao extends BaseDao<EntityAction, String>{

    List<EntityAction> findByEntity(String entity);

    @Modifying
    int deleteByEntity(String entity);

}
