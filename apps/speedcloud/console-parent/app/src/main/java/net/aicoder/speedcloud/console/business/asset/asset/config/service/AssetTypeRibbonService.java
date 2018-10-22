package net.aicoder.speedcloud.console.business.asset.asset.config.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.asset.client.asset.config.AssetTypeRibbon;
import net.aicoder.speedcloud.asset.client.asset.config.result.AssetTypePageResult;
import net.aicoder.speedcloud.asset.client.asset.config.result.AssetTypeResult;
import net.aicoder.speedcloud.asset.business.asset.config.dto.AssetTypeCondition;
import net.aicoder.speedcloud.asset.business.asset.config.dto.AssetTypeAddDto;
import net.aicoder.speedcloud.asset.business.asset.config.dto.AssetTypeEditDto;
import net.aicoder.speedcloud.asset.business.asset.config.vo.AssetTypeVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("assetTypeRibbonService")
public class AssetTypeRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(AssetTypeRibbonService.class);


	@Autowired
	private AssetTypeRibbon assetTypeRibbon;


	public AssetTypeVO add(AssetTypeAddDto addDto){
		AssetTypeResult result = assetTypeRibbon.add(addDto);

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
		AssetTypeResult result = assetTypeRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("ASSET", "ASSET.CONFIG", result.getCode()+"", result.getMessage());
		}
	}
	public AssetTypeVO merge(Long id, AssetTypeEditDto editDto){
		AssetTypeResult result = assetTypeRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("ASSET", "ASSET.CONFIG", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public AssetTypeVO find(Long id){
		AssetTypeResult result = assetTypeRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("ASSET", "ASSET.CONFIG", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<AssetTypeVO> list(PageSearchRequest<AssetTypeCondition> pageSearchRequest) {
		AssetTypePageResult result = assetTypeRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("ASSET", "ASSET.CONFIG", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
