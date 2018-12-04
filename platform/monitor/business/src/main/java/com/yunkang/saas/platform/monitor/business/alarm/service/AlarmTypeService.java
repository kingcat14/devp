package com.yunkang.saas.platform.monitor.business.alarm.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import com.yunkang.saas.platform.monitor.business.alarm.dao.AlarmTypeDao;
import com.yunkang.saas.platform.monitor.business.alarm.dao.AlarmTypeSpecification;
import com.yunkang.saas.platform.monitor.business.alarm.domain.AlarmType;
import com.yunkang.saas.platform.monitor.business.alarm.dto.AlarmTypeCondition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service("alarmTypeService")
@Slf4j
public class AlarmTypeService  extends GenericCrudService<AlarmType, String, AlarmTypeCondition, AlarmTypeDao> {

	@Override
	public Specification<AlarmType> getSpecification(AlarmTypeCondition condition) {
		return new AlarmTypeSpecification(condition);
	}

	public Sort getDefaultSort(){
		Sort sort = new Sort(Sort.Direction.DESC);

		return sort;
	}
}