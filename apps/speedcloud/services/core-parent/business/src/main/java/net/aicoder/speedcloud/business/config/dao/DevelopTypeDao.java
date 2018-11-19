package net.aicoder.speedcloud.business.config.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.speedcloud.business.config.domain.DevelopType;
import org.springframework.stereotype.Repository;


/**
 * 开发模式的数据库操作
 * @author icode
 */
@Repository("developTypeDao")
public interface DevelopTypeDao extends BaseDao<DevelopType, Long>{


}
