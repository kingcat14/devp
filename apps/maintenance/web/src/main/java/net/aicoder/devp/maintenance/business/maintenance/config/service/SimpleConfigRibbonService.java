package net.aicoder.devp.maintenance.business.maintenance.config.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.maintenance.client.config.SimpleConfigRibbon;
import net.aicoder.devp.maintenance.client.config.result.SimpleConfigPageResult;
import net.aicoder.devp.maintenance.client.config.result.SimpleConfigResult;
import net.aicoder.devp.maintenance.business.config.dto.SimpleConfigCondition;
import net.aicoder.devp.maintenance.business.config.dto.SimpleConfigAddDto;
import net.aicoder.devp.maintenance.business.config.dto.SimpleConfigEditDto;
import net.aicoder.devp.maintenance.business.config.vo.SimpleConfigVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("simpleConfigRibbonService")
public class SimpleConfigRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(SimpleConfigRibbonService.class);


	@Autowired
	private SimpleConfigRibbon simpleConfigRibbon;


	public SimpleConfigVO add(SimpleConfigAddDto addDto){
		SimpleConfigResult result = simpleConfigRibbon.add(addDto);

		if(!result.isSuccess()){
			throw new BusinessException("MAINTENANCE", "CONFIG", result.getCode()+"", result.getMessage());
		}
		return result.getData();
	
	}

	public void delete(Long id){
		if(null == id){
			LOGGER.warn("try delete T by empty id. Code need check");
			return ;
		}
		LOGGER.debug("delete t:{}", id);
		SimpleConfigResult result = simpleConfigRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("MAINTENANCE", "CONFIG", result.getCode()+"", result.getMessage());
		}
	}
	public SimpleConfigVO merge(Long id, SimpleConfigEditDto editDto){
		SimpleConfigResult result = simpleConfigRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("MAINTENANCE", "CONFIG", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public SimpleConfigVO find(Long id){
		SimpleConfigResult result = simpleConfigRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("MAINTENANCE", "CONFIG", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<SimpleConfigVO> list(PageSearchRequest<SimpleConfigCondition> pageSearchRequest) {
		SimpleConfigPageResult result = simpleConfigRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("MAINTENANCE", "CONFIG", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
