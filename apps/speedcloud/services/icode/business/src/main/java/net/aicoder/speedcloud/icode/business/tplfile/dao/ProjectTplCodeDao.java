package net.aicoder.speedcloud.icode.business.tplfile.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.speedcloud.icode.business.tplfile.domain.ProjectTplCode;
import org.springframework.stereotype.Repository;


/**
 * 组件代码模板的数据库操作
 * @author icode
 */
@Repository("projectTplCodeDao")
public interface ProjectTplCodeDao extends BaseDao<ProjectTplCode, String>{


}
