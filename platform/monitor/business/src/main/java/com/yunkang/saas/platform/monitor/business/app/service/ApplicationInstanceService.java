package com.yunkang.saas.platform.monitor.business.app.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import com.yunkang.saas.platform.monitor.business.app.dao.ApplicationInstanceDao;
import com.yunkang.saas.platform.monitor.business.app.dao.ApplicationInstanceSpecification;
import com.yunkang.saas.platform.monitor.business.app.domain.ApplicationInstance;
import com.yunkang.saas.platform.monitor.business.app.dto.ApplicationInstanceCondition;
import de.codecentric.boot.admin.model.Application;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.List;


@Service("applicationInstanceService")
@Slf4j
public class ApplicationInstanceService  extends GenericCrudService<ApplicationInstance, String, ApplicationInstanceCondition, ApplicationInstanceDao> {

	public String getInstanceId(Application application){

		String result = null;

		URL url = null;
		try {
			url = new URL(application.getServiceUrl());
		} catch (MalformedURLException e) {
			log.error(e.getMessage(), e);
		}

		ApplicationInstance instanceInfo = find(application.getName(), url.getHost(), url.getPort());
		if(instanceInfo != null){
			result = instanceInfo.getId();
		}else {
			result = url.getHost() + ":" + application.getName() + ":" + url.getPort();
		}

		return result;
	}

	public ApplicationInstance find(String appCode, String ip, Integer port){
		return dao.findByAppAndHostAndPort(appCode, ip, port);
	}


	public List<ApplicationInstance> find(String appCode, Boolean alive){
		return dao.findByAppAndAlive(appCode, alive);
	}

	public void markAliveInstanceDead(String appCode){
		List<ApplicationInstance> list = dao.findByAppAndAlive(appCode, true);
		list.stream().forEach((instance) ->{
			instance.setAlive(false);
			instance.setDetectionTime(new Date());
			dao.saveAndFlush(instance);
		});
	}

	@Override
	public Specification<ApplicationInstance> getSpecification(ApplicationInstanceCondition condition) {
		return new ApplicationInstanceSpecification(condition);
	}

	public Sort getDefaultSort(){
		Sort sort = new Sort(Sort.Direction.DESC, ApplicationInstance.PROPERTY_APP, ApplicationInstance.PROPERTY_DETECTION_TIME);
		return sort;
	}
}