package com.yunkang.saas.platform.monitor.business.indicator.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import com.yunkang.saas.platform.monitor.business.indicator.domain.IndicatorValue;
import org.springframework.stereotype.Repository;


/**
 * 指标值的数据库操作
 * @author icode
 */
@Repository("indicatorValueDao")
public interface IndicatorValueDao extends BaseDao<IndicatorValue, String>{


}
