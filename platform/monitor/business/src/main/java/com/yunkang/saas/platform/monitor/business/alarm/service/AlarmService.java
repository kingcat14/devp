package com.yunkang.saas.platform.monitor.business.alarm.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import com.yunkang.saas.platform.monitor.business.alarm.dao.AlarmDao;
import com.yunkang.saas.platform.monitor.business.alarm.dao.AlarmSpecification;
import com.yunkang.saas.platform.monitor.business.alarm.domain.Alarm;
import com.yunkang.saas.platform.monitor.business.alarm.dto.AlarmCondition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("alarmService")
@Slf4j
public class AlarmService  extends GenericCrudService<Alarm, String, AlarmCondition, AlarmDao> {

	@Override
	public Specification<Alarm> getSpecification(AlarmCondition condition) {
		return new AlarmSpecification(condition);
	}



	public Sort getDefaultSort(){
		Sort sort = new Sort(Sort.Direction.DESC, Alarm.PROPERTY_MODIFY_AT);

		return sort;
	}
}