package com.yunkang.saas.platform.monitor.business.indicator.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import com.yunkang.saas.common.jpa.GenericCrudService;
import com.yunkang.saas.platform.monitor.business.indicator.domain.Indicator;
import org.springframework.stereotype.Repository;


/**
 * 指标的数据库操作
 * @author icode
 */
@Repository("indicatorDao")
public interface IndicatorDao extends BaseDao<Indicator, String>{


}
