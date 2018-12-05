package net.aicoder.speedcloud.business.config.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.speedcloud.business.config.domain.CodeRepositoryType;
import org.springframework.stereotype.Repository;


/**
 * 代码库类型的数据库操作
 * @author icode
 */
@Repository("codeRepositoryTypeDao")
public interface CodeRepositoryTypeDao extends BaseDao<CodeRepositoryType, String>{


}
