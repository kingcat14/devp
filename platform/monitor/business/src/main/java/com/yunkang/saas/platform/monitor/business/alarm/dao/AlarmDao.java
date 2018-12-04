package com.yunkang.saas.platform.monitor.business.alarm.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import com.yunkang.saas.platform.monitor.business.alarm.domain.Alarm;
import org.springframework.stereotype.Repository;


/**
 * 告警的数据库操作
 * @author icode
 */
@Repository("alarmDao")
public interface AlarmDao extends BaseDao<Alarm, String>{


}
