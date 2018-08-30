package net.aicoder.speedcloud.business.app.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.app.domain.CodeBaseInfo;
import org.springframework.stereotype.Repository;


/**
 * 代码库详细信息的数据库操作
 * @author icode
 */
@Repository("codeBaseInfoDao")
public interface CodeBaseInfoDao extends BaseDao<CodeBaseInfo, Long>{


}
