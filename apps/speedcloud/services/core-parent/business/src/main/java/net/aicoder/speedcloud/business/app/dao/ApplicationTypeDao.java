package net.aicoder.speedcloud.business.app.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.speedcloud.business.app.domain.ApplicationType;
import org.springframework.stereotype.Repository;


/**
 * 应用类型的数据库操作
 * @author icode
 */
@Repository("applicationTypeDao")
public interface ApplicationTypeDao extends BaseDao<ApplicationType, String>{


}
