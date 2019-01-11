package com.yunkang.saas.platform.monitor.business.alarm.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import com.yunkang.saas.platform.monitor.business.alarm.domain.AlarmType;
import org.springframework.stereotype.Repository;


/**
 * 告警类型的数据库操作
 * @author icode
 */
@Repository("alarmTypeDao")
public interface AlarmTypeDao extends BaseDao<AlarmType, String>{


}
