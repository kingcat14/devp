package net.aicoder.speedcloud.business.app.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import net.aicoder.speedcloud.business.app.domain.AppBaseInfo;
import org.springframework.stereotype.Repository;


/**
 * 应用的数据库操作
 * @author icode
 */
@Repository("appBaseInfoDao")
public interface AppBaseInfoDao extends BaseDao<AppBaseInfo, Long>{


}
