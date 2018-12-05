package net.aicoder.speedcloud.business.app.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.speedcloud.business.app.domain.CodeBaseInfo;
import org.springframework.stereotype.Repository;


/**
 * 代码基本信息的数据库操作
 * @author icode
 */
@Repository("codeBaseInfoDao")
public interface CodeBaseInfoDao extends BaseDao<CodeBaseInfo, String>{


}
