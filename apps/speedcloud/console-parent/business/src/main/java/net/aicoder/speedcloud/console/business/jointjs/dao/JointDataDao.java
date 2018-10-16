package net.aicoder.speedcloud.console.business.jointjs.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.console.business.jointjs.domain.JointData;
import org.springframework.stereotype.Repository;


/**
 * 图形数据的数据库操作
 * @author icode
 */
@Repository("jointDataDao")
public interface JointDataDao extends BaseDao<JointData, String>{


}
