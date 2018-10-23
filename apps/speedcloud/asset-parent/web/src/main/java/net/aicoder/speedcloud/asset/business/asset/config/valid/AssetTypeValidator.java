package net.aicoder.speedcloud.asset.business.asset.config.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.asset.business.asset.config.dto.AssetTypeAddDto;
import net.aicoder.speedcloud.asset.business.asset.config.dto.AssetTypeEditDto;
import net.aicoder.speedcloud.asset.business.asset.config.dto.AssetTypeCondition;
import net.aicoder.speedcloud.asset.business.asset.config.domain.AssetType;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class AssetTypeValidator implements Validator {

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
	    if(obj instanceof AssetTypeAddDto){
            this.validateAssetTypeAddDto((AssetTypeAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj, errors);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<AssetTypeCondition> search, Errors errors) {
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new AssetTypeCondition());
        }
        if(search.getSearchCondition().getTid() == null){
        	errors.rejectValue("NOT_TENANT_ID", "未正确设置租户ID");
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param assetType 资产分类
     * @param errors
     */
	public void validateAssetTypeAddDto(AssetTypeAddDto assetType, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if(StringUtils.isEmpty(assetType.getAssetCategoryCode())){
			errors.rejectValue(AssetType.PROPERTY_ASSET_CATEGORY_CODE, "EMPTY_"+AssetType.PROPERTY_ASSET_CATEGORY_CODE, "所属大类不能为空");
		}

		//验证长度
		if(StringUtils.length(assetType.getName()) > 255){
			errors.rejectValue(AssetType.PROPERTY_NAME,null,"名称最长255个字符");
		}
		if(StringUtils.length(assetType.getCode()) > 255){
			errors.rejectValue(AssetType.PROPERTY_CODE,null,"代码最长255个字符");
		}
		if(StringUtils.length(assetType.getParentCode()) > 255){
			errors.rejectValue(AssetType.PROPERTY_PARENT_CODE,null,"上级代码最长255个字符");
		}
		if(StringUtils.length(assetType.getAssetCategoryCode()) > 255){
			errors.rejectValue(AssetType.PROPERTY_ASSET_CATEGORY_CODE,null,"所属大类最长255个字符");
		}
	}
}