package net.aicoder.speedcloud.console.business.icode.domain.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.controller.RestResponse;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityAddDto;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityCondition;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityEditDto;
import net.aicoder.speedcloud.icode.business.domain.vo.EntityVO;
import net.aicoder.speedcloud.icode.client.domain.EntityClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("entityRibbonService")
public class EntityRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(EntityRibbonService.class);


	@Autowired
	private EntityClient entityRibbon;


	public EntityVO add(EntityAddDto addDto){
		RestResponse<EntityVO> result = entityRibbon.add(addDto);

		if(!result.isSuccess()){
			throw new BusinessException("ICODE", "DOMAIN", result.getCode()+"", result.getMessage());
		}
		return result.getData();

	}

	public void delete(String id){
		if(null == id){
			LOGGER.warn("try delete T by empty id. Code need check");
			return ;
		}
		LOGGER.debug("delete t:{}", id);
		RestResponse<EntityVO> result = entityRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("ICODE", "DOMAIN", result.getCode()+"", result.getMessage());
		}
	}
	public EntityVO merge(String id, EntityEditDto editDto){
		RestResponse<EntityVO> result = entityRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("ICODE", "DOMAIN", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public EntityVO copy(String id){
		RestResponse<EntityVO> result = entityRibbon.copy(id);
		if(!result.isSuccess()){
			throw new BusinessException("ICODE", "DOMAIN", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public EntityVO find(String id){
		RestResponse<EntityVO> result = entityRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("ICODE", "DOMAIN", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public EntityVO getDetail(String id){
		RestResponse<EntityVO> result = entityRibbon.getDetail(id);

		if(!result.isSuccess()){
			throw new BusinessException("ICODE", "DOMAIN", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<EntityVO> list(PageSearchRequest<EntityCondition> pageSearchRequest) {
		RestResponse<PageContent<EntityVO>> result = entityRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("ICODE", "DOMAIN", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
