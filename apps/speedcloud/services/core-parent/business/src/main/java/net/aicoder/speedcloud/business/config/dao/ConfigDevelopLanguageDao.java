package net.aicoder.speedcloud.business.config.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.speedcloud.business.config.domain.ConfigDevelopLanguage;
import org.springframework.stereotype.Repository;


/**
 * 开发语言的数据库操作
 * @author icode
 */
@Repository("configDevelopLanguageDao")
public interface ConfigDevelopLanguageDao extends BaseDao<ConfigDevelopLanguage, Long>{


}
