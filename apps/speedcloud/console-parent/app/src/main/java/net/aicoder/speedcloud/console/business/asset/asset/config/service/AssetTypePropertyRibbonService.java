package net.aicoder.speedcloud.console.business.asset.asset.config.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.asset.client.asset.config.AssetTypePropertyRibbon;
import net.aicoder.speedcloud.asset.client.asset.config.result.AssetTypePropertyPageResult;
import net.aicoder.speedcloud.asset.client.asset.config.result.AssetTypePropertyResult;
import net.aicoder.speedcloud.asset.business.asset.config.dto.AssetTypePropertyCondition;
import net.aicoder.speedcloud.asset.business.asset.config.dto.AssetTypePropertyAddDto;
import net.aicoder.speedcloud.asset.business.asset.config.dto.AssetTypePropertyEditDto;
import net.aicoder.speedcloud.asset.business.asset.config.vo.AssetTypePropertyVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("assetTypePropertyRibbonService")
public class AssetTypePropertyRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(AssetTypePropertyRibbonService.class);


	@Autowired
	private AssetTypePropertyRibbon assetTypePropertyRibbon;


	public AssetTypePropertyVO add(AssetTypePropertyAddDto addDto){
		AssetTypePropertyResult result = assetTypePropertyRibbon.add(addDto);

		if(!result.isSuccess()){
			throw new BusinessException("ASSET", "ASSET.CONFIG", result.getCode()+"", result.getMessage());
		}
		return result.getData();
	
	}

	public void delete(Long id){
		if(null == id){
			LOGGER.warn("try delete T by empty id. Code need check");
			return ;
		}
		LOGGER.debug("delete t:{}", id);
		AssetTypePropertyResult result = assetTypePropertyRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("ASSET", "ASSET.CONFIG", result.getCode()+"", result.getMessage());
		}
	}
	public AssetTypePropertyVO merge(Long id, AssetTypePropertyEditDto editDto){
		AssetTypePropertyResult result = assetTypePropertyRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("ASSET", "ASSET.CONFIG", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public AssetTypePropertyVO find(Long id){
		AssetTypePropertyResult result = assetTypePropertyRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("ASSET", "ASSET.CONFIG", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<AssetTypePropertyVO> list(PageSearchRequest<AssetTypePropertyCondition> pageSearchRequest) {
		AssetTypePropertyPageResult result = assetTypePropertyRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("ASSET", "ASSET.CONFIG", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
