package com.yunkang.saas.platform.monitor.business.app.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import com.yunkang.saas.platform.monitor.business.app.dao.ApplicationDao;
import com.yunkang.saas.platform.monitor.business.app.dao.ApplicationSpecification;
import com.yunkang.saas.platform.monitor.business.app.domain.Application;
import com.yunkang.saas.platform.monitor.business.app.dto.ApplicationCondition;
import com.yunkang.saas.platform.monitor.business.notification.mail.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


@Service("applicationService")
@Slf4j
public class ApplicationService  extends GenericCrudService<Application, String, ApplicationCondition, ApplicationDao> {

	@Autowired
	private UnknownAppService unknownAppService;

	@Autowired
	private MailService mailService;

	@Autowired
	private ApplicationInstanceService applicationInstanceService;

	public void add(Application application){

		/*
		 * 1.添加
		 * 2.从未知应用中移除
		 */
		application.setId(application.getCode());
		dao.save(application);

		unknownAppService.delete(application.getCode());
	}

	public void markAlive(String code, int aliveCount){

		Application application = dao.findOne(code);

		Integer oldAlive = application.getAliveCount()==null?0:application.getAliveCount();

		application.setAliveCount(aliveCount);

		application.setStatus(isHealth(application)?"WELL":"UNUSUAL");

		this.merge(application);

		if(oldAlive > application.getThresholdValue() && aliveCount <= application.getThresholdValue()){

			String messageTpl = "应用%s的实例从%s变为%s,配置量为:%s";
			String message = String.format(messageTpl, code, oldAlive, aliveCount, application.getTotalCount());
			log.warn(message);
			SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
			simpleMailMessage.setText(message);
			simpleMailMessage.setSubject("应用("+application.getName()+"异常!");
			mailService.send(simpleMailMessage);
		}

	}

	public Boolean isHealth(Application application){
		/*
		 * 1.活的实例数量 大于等于 阈值, 则说明是健康的
		 */
		return application.getAliveCount() != null && (application.getAliveCount() >= application.getThresholdValue());

	}

	/**
	 * 把不包含在这个名单里的应用标识为已经挂了
	 * @param nameSet 注册中心包含的应用名
	 */
	public void markOtherAppDead(Set<String> nameSet){

		//找到活着的app的code
		List<String> codeList = dao.findAliveCode();

		for(String appCode : codeList){

			if(nameSet.contains(appCode)){
				continue;
			}
			//如果活着的应用名单里没有当前应用，则当前应用标记为已近挂了
			markDead(appCode);
		}
	}

	private void markDead(String code){
		Application application = dao.findOne(code);
		this.markAlive(code, 0);
		dao.save(application);

		applicationInstanceService.markAliveInstanceDead(code);
	}

	public boolean contain(String code){
		return dao.existsByCode(code);
	}

	public List<String> findAliveCode(){
		return dao.findAliveCode();
	}

	@Override
	public Specification<Application> getSpecification(ApplicationCondition condition) {
		return new ApplicationSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, Application.PROPERTY_NAME);
		return sort;
	}
}