package net.aicoder.speedcloud.business.app.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.speedcloud.business.app.domain.CodeRepository;
import org.springframework.stereotype.Repository;


/**
 * 代码库的数据库操作
 * @author icode
 */
@Repository("codeRepositoryDao")
public interface CodeRepositoryDao extends BaseDao<CodeRepository, Long>{


}
