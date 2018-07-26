package net.aicoder.devp.maintenance.business.deploy.deploy.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.deploy.client.deploy.DevpSysDpyInstSchemeRibbon;
import net.aicoder.devp.deploy.client.deploy.result.DevpSysDpyInstSchemePageResult;
import net.aicoder.devp.deploy.client.deploy.result.DevpSysDpyInstSchemeResult;
import net.aicoder.devp.deploy.business.deploy.dto.DevpSysDpyInstSchemeCondition;
import net.aicoder.devp.deploy.business.deploy.dto.DevpSysDpyInstSchemeAddDto;
import net.aicoder.devp.deploy.business.deploy.dto.DevpSysDpyInstSchemeEditDto;
import net.aicoder.devp.deploy.business.deploy.vo.DevpSysDpyInstSchemeVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("devpSysDpyInstSchemeRibbonService")
public class DevpSysDpyInstSchemeRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysDpyInstSchemeRibbonService.class);


	@Autowired
	private DevpSysDpyInstSchemeRibbon devpSysDpyInstSchemeRibbon;


	public DevpSysDpyInstSchemeVO add(DevpSysDpyInstSchemeAddDto addDto){
		DevpSysDpyInstSchemeResult result = devpSysDpyInstSchemeRibbon.add(addDto);

		if(!result.isSuccess()){
			throw new BusinessException("DEPLOY", "DEPLOY", result.getCode()+"", result.getMessage());
		}
		return result.getData();
	
	}

	public void delete(Long id){
		if(null == id){
			LOGGER.warn("try delete T by empty id. Code need check");
			return ;
		}
		LOGGER.debug("delete t:{}", id);
		DevpSysDpyInstSchemeResult result = devpSysDpyInstSchemeRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("DEPLOY", "DEPLOY", result.getCode()+"", result.getMessage());
		}
	}
	public DevpSysDpyInstSchemeVO merge(Long id, DevpSysDpyInstSchemeEditDto editDto){
		DevpSysDpyInstSchemeResult result = devpSysDpyInstSchemeRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("DEPLOY", "DEPLOY", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public DevpSysDpyInstSchemeVO find(Long id){
		DevpSysDpyInstSchemeResult result = devpSysDpyInstSchemeRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("DEPLOY", "DEPLOY", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<DevpSysDpyInstSchemeVO> list(PageSearchRequest<DevpSysDpyInstSchemeCondition> pageSearchRequest) {
		DevpSysDpyInstSchemePageResult result = devpSysDpyInstSchemeRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("DEPLOY", "DEPLOY", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
