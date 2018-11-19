package net.aicoder.speedcloud.asset.business.asset.info.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.asset.business.asset.info.domain.AssetCmdb;
import net.aicoder.speedcloud.asset.business.asset.info.dto.AssetCmdbAddDto;
import net.aicoder.speedcloud.asset.business.asset.info.dto.AssetCmdbCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class AssetCmdbValidator implements Validator {

	/**
	 * 判断支持的JavaBean类型
	 * @param aClass
	 * @return
	 */
	@Override
	public boolean supports(Class<?> aClass) {
		return true;
	}

	/**
	 * 实现Validator中的validate接口
	 * @param obj
	 * @param errors
	 */
	@Override
	public void validate(Object obj, Errors errors) {
	    if(obj instanceof AssetCmdbAddDto){
            this.validateAssetCmdbAddDto((AssetCmdbAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<AssetCmdbCondition> search){
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new AssetCmdbCondition());
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param assetCmdb IT资产
     * @param errors
     */
	public void validateAssetCmdbAddDto(AssetCmdbAddDto assetCmdb, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(assetCmdb.getName()) > 255){
			errors.rejectValue(AssetCmdb.PROPERTY_NAME,null,"资产名称最长255个字符");
		}
		if(StringUtils.length(assetCmdb.getCode()) > 255){
			errors.rejectValue(AssetCmdb.PROPERTY_CODE,null,"资产代码最长255个字符");
		}
		if(StringUtils.length(assetCmdb.getAlias()) > 255){
			errors.rejectValue(AssetCmdb.PROPERTY_ALIAS,null,"资产别名最长255个字符");
		}
		if(StringUtils.length(assetCmdb.getDescription()) > 255){
			errors.rejectValue(AssetCmdb.PROPERTY_DESCRIPTION,null,"描述最长255个字符");
		}
		if(StringUtils.length(assetCmdb.getStatus()) > 255){
			errors.rejectValue(AssetCmdb.PROPERTY_STATUS,null,"状态最长255个字符");
		}
		if(StringUtils.length(assetCmdb.getAssetArea()) > 255){
			errors.rejectValue(AssetCmdb.PROPERTY_ASSET_AREA,null,"所在区域最长255个字符");
		}
		if(StringUtils.length(assetCmdb.getAssetLocation()) > 255){
			errors.rejectValue(AssetCmdb.PROPERTY_ASSET_LOCATION,null,"所在地址最长255个字符");
		}
		if(StringUtils.length(assetCmdb.getAcquisitionMode()) > 255){
			errors.rejectValue(AssetCmdb.PROPERTY_ACQUISITION_MODE,null,"获取模式最长255个字符");
		}
		if(StringUtils.length(assetCmdb.getAcquisitionDesc()) > 255){
			errors.rejectValue(AssetCmdb.PROPERTY_ACQUISITION_DESC,null,"获取描述最长255个字符");
		}
		if(StringUtils.length(assetCmdb.getNotes()) > 255){
			errors.rejectValue(AssetCmdb.PROPERTY_NOTES,null,"notes最长255个字符");
		}
	}
}