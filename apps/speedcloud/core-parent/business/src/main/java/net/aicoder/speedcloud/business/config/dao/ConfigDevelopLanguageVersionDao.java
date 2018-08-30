package net.aicoder.speedcloud.business.config.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.config.domain.ConfigDevelopLanguageVersion;
import org.springframework.stereotype.Repository;


/**
 * 开发语言版本的数据库操作
 * @author icode
 */
@Repository("configDevelopLanguageVersionDao")
public interface ConfigDevelopLanguageVersionDao extends BaseDao<ConfigDevelopLanguageVersion, Long>{


}
