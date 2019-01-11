package net.aicoder.speedcloud.business.pipeline.jenkins.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.speedcloud.business.pipeline.jenkins.domain.JenkinsAdapter;
import org.springframework.stereotype.Repository;


/**
 * JenkinsAdapter的数据库操作
 * @author icode
 */
@Repository("jenkinsAdapterDao")
public interface JenkinsAdapterDao extends BaseDao<JenkinsAdapter, String>{


}
