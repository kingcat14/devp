package net.aicoder.speedcloud.icode.business.domain.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.speedcloud.icode.business.domain.domain.Domain;
import org.springframework.stereotype.Repository;


/**
 * 领域的数据库操作
 * @author icode
 */
@Repository("domainDao")
public interface DomainDao extends BaseDao<Domain, String>{


}
