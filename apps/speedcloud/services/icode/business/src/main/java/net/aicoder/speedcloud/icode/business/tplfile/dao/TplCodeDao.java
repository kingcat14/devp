package net.aicoder.speedcloud.icode.business.tplfile.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.speedcloud.icode.business.tplfile.domain.TplCode;
import org.springframework.stereotype.Repository;


/**
 * 公共代码模板的数据库操作
 * @author icode
 */
@Repository("tplCodeDao")
public interface TplCodeDao extends BaseDao<TplCode, String>{


}
