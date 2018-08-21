package net.aicoder.devp.maintenance.business.rdc.config.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.devp.maintenance.business.rdc.config.domain.DevelopType;
import org.springframework.stereotype.Repository;


/**
 * 开发模式的数据库操作
 * @author icode
 */
@Repository("developTypeDao")
public interface DevelopTypeDao extends BaseDao<DevelopType, String>{


}
