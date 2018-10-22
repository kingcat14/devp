package net.aicoder.speedcloud.console.business.asset.asset.info.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.asset.client.asset.info.AssetPropertyRibbon;
import net.aicoder.speedcloud.asset.client.asset.info.result.AssetPropertyPageResult;
import net.aicoder.speedcloud.asset.client.asset.info.result.AssetPropertyResult;
import net.aicoder.speedcloud.asset.business.asset.info.dto.AssetPropertyCondition;
import net.aicoder.speedcloud.asset.business.asset.info.dto.AssetPropertyAddDto;
import net.aicoder.speedcloud.asset.business.asset.info.dto.AssetPropertyEditDto;
import net.aicoder.speedcloud.asset.business.asset.info.vo.AssetPropertyVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("assetPropertyRibbonService")
public class AssetPropertyRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(AssetPropertyRibbonService.class);


	@Autowired
	private AssetPropertyRibbon assetPropertyRibbon;


	public AssetPropertyVO add(AssetPropertyAddDto addDto){
		AssetPropertyResult result = assetPropertyRibbon.add(addDto);

		if(!result.isSuccess()){
			throw new BusinessException("ASSET", "ASSET.INFO", result.getCode()+"", result.getMessage());
		}
		return result.getData();
	
	}

	public void delete(Long id){
		if(null == id){
			LOGGER.warn("try delete T by empty id. Code need check");
			return ;
		}
		LOGGER.debug("delete t:{}", id);
		AssetPropertyResult result = assetPropertyRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("ASSET", "ASSET.INFO", result.getCode()+"", result.getMessage());
		}
	}
	public AssetPropertyVO merge(Long id, AssetPropertyEditDto editDto){
		AssetPropertyResult result = assetPropertyRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("ASSET", "ASSET.INFO", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public AssetPropertyVO find(Long id){
		AssetPropertyResult result = assetPropertyRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("ASSET", "ASSET.INFO", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<AssetPropertyVO> list(PageSearchRequest<AssetPropertyCondition> pageSearchRequest) {
		AssetPropertyPageResult result = assetPropertyRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("ASSET", "ASSET.INFO", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
