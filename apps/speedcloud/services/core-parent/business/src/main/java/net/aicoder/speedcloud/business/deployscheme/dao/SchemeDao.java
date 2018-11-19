package net.aicoder.speedcloud.business.deployscheme.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.speedcloud.business.deployscheme.domain.Scheme;
import org.springframework.stereotype.Repository;


/**
 * 部署方案的数据库操作
 * @author icode
 */
@Repository("schemeDao")
public interface SchemeDao extends BaseDao<Scheme, Long>{


}
