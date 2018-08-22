package net.aicoder.devp.business.product.dao;

import com.yunkang.saas.common.jpa.BaseDao;

import net.aicoder.devp.business.product.domain.DevpPrdPerson;

import org.springframework.stereotype.Repository;


/**
 * 产品干系人的数据库操作
 * @author icode
 */
@Repository("devpPrdPersonDao")
public interface DevpPrdPersonDao extends BaseDao<DevpPrdPerson, Long>{


}
