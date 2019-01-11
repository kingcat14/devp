package com.yunkang.saas.workstation.business.application.application.service;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.application.business.application.client.AppRibbon;
import com.yunkang.saas.application.business.application.client.result.AppPageResult;
import com.yunkang.saas.application.business.application.client.result.AppResult;
import com.yunkang.saas.application.business.application.dto.AppCondition;
import com.yunkang.saas.application.business.application.dto.AppAddDto;
import com.yunkang.saas.application.business.application.dto.AppEditDto;
import com.yunkang.saas.application.business.application.vo.AppVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("appRibbonService")
public class AppRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(AppRibbonService.class);


	@Autowired
	private AppRibbon appRibbon;


	public AppVO add(AppAddDto addDto){
		AppResult result = appRibbon.add(addDto);
		return result.getData();
	}

	public void delete(Long id){
		if(null == id){
			LOGGER.warn("try delete T by empty id. Code need check");
			return ;
		}
		LOGGER.debug("delete t:{}", id);
		AppResult result = appRibbon.delete(id);
	}
	public AppVO merge(Long id, AppEditDto editDto){
		AppResult result = appRibbon.update(id, editDto);
		return result.getData();
	}
	public AppVO find(Long id){
		AppResult result = appRibbon.get(id);
		return result.getData();
	}

	public PageContent<AppVO> list(PageSearchRequest<AppCondition> pageSearchRequest) {
		AppPageResult result = appRibbon.list(pageSearchRequest);
		return result.getData();
	}
}
