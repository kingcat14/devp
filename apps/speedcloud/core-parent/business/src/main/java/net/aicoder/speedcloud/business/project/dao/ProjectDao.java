package net.aicoder.speedcloud.business.project.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.project.domain.Project;
import org.springframework.stereotype.Repository;


/**
 * 项目的数据库操作
 * @author icode
 */
@Repository("projectDao")
public interface ProjectDao extends BaseDao<Project, Long>{


}
