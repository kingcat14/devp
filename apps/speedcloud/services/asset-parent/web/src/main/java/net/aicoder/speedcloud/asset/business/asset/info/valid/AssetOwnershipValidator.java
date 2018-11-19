package net.aicoder.speedcloud.asset.business.asset.info.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.asset.business.asset.info.domain.AssetOwnership;
import net.aicoder.speedcloud.asset.business.asset.info.dto.AssetOwnershipAddDto;
import net.aicoder.speedcloud.asset.business.asset.info.dto.AssetOwnershipCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class AssetOwnershipValidator implements Validator {

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
	    if(obj instanceof AssetOwnershipAddDto){
            this.validateAssetOwnershipAddDto((AssetOwnershipAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<AssetOwnershipCondition> search){
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new AssetOwnershipCondition());
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param assetOwnership IT资产归属
     * @param errors
     */
	public void validateAssetOwnershipAddDto(AssetOwnershipAddDto assetOwnership, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(assetOwnership.getName()) > 255){
			errors.rejectValue(AssetOwnership.PROPERTY_NAME,null,"资产名称最长255个字符");
		}
		if(StringUtils.length(assetOwnership.getCode()) > 255){
			errors.rejectValue(AssetOwnership.PROPERTY_CODE,null,"资产代码最长255个字符");
		}
		if(StringUtils.length(assetOwnership.getTypeCode()) > 255){
			errors.rejectValue(AssetOwnership.PROPERTY_TYPE_CODE,null,"资产分类代码最长255个字符");
		}
		if(StringUtils.length(assetOwnership.getTypeName()) > 255){
			errors.rejectValue(AssetOwnership.PROPERTY_TYPE_NAME,null,"资产分类名称最长255个字符");
		}
		if(StringUtils.length(assetOwnership.getAssetDept()) > 255){
			errors.rejectValue(AssetOwnership.PROPERTY_ASSET_DEPT,null,"资产部门最长255个字符");
		}
		if(StringUtils.length(assetOwnership.getAssetManager()) > 255){
			errors.rejectValue(AssetOwnership.PROPERTY_ASSET_MANAGER,null,"资产负责人最长255个字符");
		}
		if(StringUtils.length(assetOwnership.getUseDept()) > 255){
			errors.rejectValue(AssetOwnership.PROPERTY_USE_DEPT,null,"使用部门最长255个字符");
		}
		if(StringUtils.length(assetOwnership.getUseManager()) > 255){
			errors.rejectValue(AssetOwnership.PROPERTY_USE_MANAGER,null,"使用负责人最长255个字符");
		}
		if(StringUtils.length(assetOwnership.getOpsDept()) > 255){
			errors.rejectValue(AssetOwnership.PROPERTY_OPS_DEPT,null,"操作部门最长255个字符");
		}
		if(StringUtils.length(assetOwnership.getOpsManager()) > 255){
			errors.rejectValue(AssetOwnership.PROPERTY_OPS_MANAGER,null,"操作负责人最长255个字符");
		}
		if(StringUtils.length(assetOwnership.getBizLine()) > 255){
			errors.rejectValue(AssetOwnership.PROPERTY_BIZ_LINE,null,"业务线最长255个字符");
		}
		if(StringUtils.length(assetOwnership.getBizManager()) > 255){
			errors.rejectValue(AssetOwnership.PROPERTY_BIZ_MANAGER,null,"业务负责人最长255个字符");
		}
		if(StringUtils.length(assetOwnership.getMajorCust()) > 255){
			errors.rejectValue(AssetOwnership.PROPERTY_MAJOR_CUST,null,"主要客户最长255个字符");
		}
		if(StringUtils.length(assetOwnership.getCustManager()) > 255){
			errors.rejectValue(AssetOwnership.PROPERTY_CUST_MANAGER,null,"客户负责人最长255个字符");
		}
		if(StringUtils.length(assetOwnership.getCustUsage()) > 255){
			errors.rejectValue(AssetOwnership.PROPERTY_CUST_USAGE,null,"客户使用情况最长255个字符");
		}
		if(StringUtils.length(assetOwnership.getAcquisitionProvider()) > 255){
			errors.rejectValue(AssetOwnership.PROPERTY_ACQUISITION_PROVIDER,null,"供应商最长255个字符");
		}
	}
}