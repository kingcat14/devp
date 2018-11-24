package net.aicoder.speedcloud.icode.business.tplfile.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.speedcloud.icode.business.tplfile.domain.TplSet;
import org.springframework.stereotype.Repository;


/**
 * 公共代码模板集合的数据库操作
 * @author icode
 */
@Repository("tplSetDao")
public interface TplSetDao extends BaseDao<TplSet, String>{


}
