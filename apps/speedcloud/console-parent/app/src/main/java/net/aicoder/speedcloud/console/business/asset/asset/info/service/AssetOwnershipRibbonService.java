package net.aicoder.speedcloud.console.business.asset.asset.info.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.asset.client.asset.info.AssetOwnershipRibbon;
import net.aicoder.speedcloud.asset.client.asset.info.result.AssetOwnershipPageResult;
import net.aicoder.speedcloud.asset.client.asset.info.result.AssetOwnershipResult;
import net.aicoder.speedcloud.asset.business.asset.info.dto.AssetOwnershipCondition;
import net.aicoder.speedcloud.asset.business.asset.info.dto.AssetOwnershipAddDto;
import net.aicoder.speedcloud.asset.business.asset.info.dto.AssetOwnershipEditDto;
import net.aicoder.speedcloud.asset.business.asset.info.vo.AssetOwnershipVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("assetOwnershipRibbonService")
public class AssetOwnershipRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(AssetOwnershipRibbonService.class);


	@Autowired
	private AssetOwnershipRibbon assetOwnershipRibbon;


	public AssetOwnershipVO add(AssetOwnershipAddDto addDto){
		AssetOwnershipResult result = assetOwnershipRibbon.add(addDto);

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
		AssetOwnershipResult result = assetOwnershipRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("ASSET", "ASSET.INFO", result.getCode()+"", result.getMessage());
		}
	}
	public AssetOwnershipVO merge(Long id, AssetOwnershipEditDto editDto){
		AssetOwnershipResult result = assetOwnershipRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("ASSET", "ASSET.INFO", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public AssetOwnershipVO find(Long id){
		AssetOwnershipResult result = assetOwnershipRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("ASSET", "ASSET.INFO", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<AssetOwnershipVO> list(PageSearchRequest<AssetOwnershipCondition> pageSearchRequest) {
		AssetOwnershipPageResult result = assetOwnershipRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("ASSET", "ASSET.INFO", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
