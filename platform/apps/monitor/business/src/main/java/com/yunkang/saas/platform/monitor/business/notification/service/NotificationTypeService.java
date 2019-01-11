package com.yunkang.saas.platform.monitor.business.notification.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import com.yunkang.saas.platform.monitor.business.notification.dao.NotificationTypeDao;
import com.yunkang.saas.platform.monitor.business.notification.dao.NotificationTypeSpecification;
import com.yunkang.saas.platform.monitor.business.notification.domain.NotificationType;
import com.yunkang.saas.platform.monitor.business.notification.dto.NotificationTypeCondition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("notificationTypeService")
@Slf4j
public class NotificationTypeService  extends GenericCrudService<NotificationType, String, NotificationTypeCondition, NotificationTypeDao> {

	@Override
	public Specification<NotificationType> getSpecification(NotificationTypeCondition condition) {
		return new NotificationTypeSpecification(condition);
	}

	public Sort getDefaultSort(){
		Sort sort = new Sort(Sort.Direction.DESC, NotificationType.PROPERTY_CODE, NotificationType.PROPERTY_MODIFY_AT);

		return sort;
	}
}