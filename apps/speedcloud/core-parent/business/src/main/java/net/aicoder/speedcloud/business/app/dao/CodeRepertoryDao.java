package net.aicoder.speedcloud.business.app.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.app.domain.CodeRepertory;
import org.springframework.stereotype.Repository;


/**
 * 代码库的数据库操作
 * @author icode
 */
@Repository("codeRepertoryDao")
public interface CodeRepertoryDao extends BaseDao<CodeRepertory, Long>{


}
