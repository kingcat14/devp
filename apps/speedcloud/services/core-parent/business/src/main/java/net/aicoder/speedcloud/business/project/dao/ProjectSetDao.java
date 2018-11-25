package net.aicoder.speedcloud.business.project.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.speedcloud.business.project.domain.ProjectSet;
import org.springframework.stereotype.Repository;


/**
 * 项目集的数据库操作
 * @author icode
 */
@Repository("projectSetDao")
public interface ProjectSetDao extends BaseDao<ProjectSet, String>{


}
