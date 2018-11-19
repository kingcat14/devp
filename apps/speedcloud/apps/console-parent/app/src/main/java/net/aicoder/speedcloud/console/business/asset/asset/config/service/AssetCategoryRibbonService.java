package net.aicoder.speedcloud.console.business.asset.asset.config.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.asset.business.asset.config.dto.AssetCategoryAddDto;
import net.aicoder.speedcloud.asset.business.asset.config.dto.AssetCategoryCondition;
import net.aicoder.speedcloud.asset.business.asset.config.dto.AssetCategoryEditDto;
import net.aicoder.speedcloud.asset.business.asset.config.vo.AssetCategoryVO;
import net.aicoder.speedcloud.asset.client.asset.config.AssetCategoryRibbon;
import net.aicoder.speedcloud.asset.client.asset.config.result.AssetCategoryPageResult;
import net.aicoder.speedcloud.asset.client.asset.config.result.AssetCategoryResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("assetCategoryRibbonService")
public class AssetCategoryRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(AssetCategoryRibbonService.class);


	@Autowired
	private AssetCategoryRibbon assetCategoryRibbon;


	public AssetCategoryVO add(AssetCategoryAddDto addDto){
		AssetCategoryResult result = assetCategoryRibbon.add(addDto);

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
		AssetCategoryResult result = assetCategoryRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("ASSET", "ASSET.CONFIG", result.getCode()+"", result.getMessage());
		}
	}
	public AssetCategoryVO merge(Long id, AssetCategoryEditDto editDto){
		AssetCategoryResult result = assetCategoryRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("ASSET", "ASSET.CONFIG", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public AssetCategoryVO find(Long id){
		AssetCategoryResult result = assetCategoryRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("ASSET", "ASSET.CONFIG", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<AssetCategoryVO> list(PageSearchRequest<AssetCategoryCondition> pageSearchRequest) {
		AssetCategoryPageResult result = assetCategoryRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("ASSET", "ASSET.CONFIG", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
