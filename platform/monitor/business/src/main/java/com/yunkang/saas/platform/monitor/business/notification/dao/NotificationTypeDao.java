package com.yunkang.saas.platform.monitor.business.notification.dao;

import com.yunkang.saas.common.jpa.BaseDao;
import com.yunkang.saas.platform.monitor.business.notification.domain.NotificationType;
import org.springframework.stereotype.Repository;


/**
 * 通知方式的数据库操作
 * @author icode
 */
@Repository("notificationTypeDao")
public interface NotificationTypeDao extends BaseDao<NotificationType, String>{


}
