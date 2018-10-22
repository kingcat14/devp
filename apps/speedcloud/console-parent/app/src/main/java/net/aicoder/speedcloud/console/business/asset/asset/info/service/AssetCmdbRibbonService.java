package net.aicoder.speedcloud.console.business.asset.asset.info.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.asset.client.asset.info.AssetCmdbRibbon;
import net.aicoder.speedcloud.asset.client.asset.info.result.AssetCmdbPageResult;
import net.aicoder.speedcloud.asset.client.asset.info.result.AssetCmdbResult;
import net.aicoder.speedcloud.asset.business.asset.info.dto.AssetCmdbCondition;
import net.aicoder.speedcloud.asset.business.asset.info.dto.AssetCmdbAddDto;
import net.aicoder.speedcloud.asset.business.asset.info.dto.AssetCmdbEditDto;
import net.aicoder.speedcloud.asset.business.asset.info.vo.AssetCmdbVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("assetCmdbRibbonService")
public class AssetCmdbRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(AssetCmdbRibbonService.class);


	@Autowired
	private AssetCmdbRibbon assetCmdbRibbon;


	public AssetCmdbVO add(AssetCmdbAddDto addDto){
		AssetCmdbResult result = assetCmdbRibbon.add(addDto);

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
		AssetCmdbResult result = assetCmdbRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("ASSET", "ASSET.INFO", result.getCode()+"", result.getMessage());
		}
	}
	public AssetCmdbVO merge(Long id, AssetCmdbEditDto editDto){
		AssetCmdbResult result = assetCmdbRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("ASSET", "ASSET.INFO", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public AssetCmdbVO find(Long id){
		AssetCmdbResult result = assetCmdbRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("ASSET", "ASSET.INFO", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<AssetCmdbVO> list(PageSearchRequest<AssetCmdbCondition> pageSearchRequest) {
		AssetCmdbPageResult result = assetCmdbRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("ASSET", "ASSET.INFO", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
