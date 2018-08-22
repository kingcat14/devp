package net.aicoder.devp.business.product.dao;

import com.yunkang.saas.common.jpa.BaseDao;

import net.aicoder.devp.business.product.domain.DevpPrdPrdline;

import org.springframework.stereotype.Repository;


/**
 * 产品线定义的数据库操作
 * @author icode
 */
@Repository("devpPrdPrdlineDao")
public interface DevpPrdPrdlineDao extends BaseDao<DevpPrdPrdline, Long>{


}
