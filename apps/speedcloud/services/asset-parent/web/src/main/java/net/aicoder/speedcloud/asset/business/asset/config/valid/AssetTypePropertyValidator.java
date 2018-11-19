package net.aicoder.speedcloud.asset.business.asset.config.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.asset.business.asset.config.domain.AssetTypeProperty;
import net.aicoder.speedcloud.asset.business.asset.config.dto.AssetTypePropertyAddDto;
import net.aicoder.speedcloud.asset.business.asset.config.dto.AssetTypePropertyCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class AssetTypePropertyValidator implements Validator {

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
	    if(obj instanceof AssetTypePropertyAddDto){
            this.validateAssetTypePropertyAddDto((AssetTypePropertyAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj, errors);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<AssetTypePropertyCondition> search, Errors errors) {
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new AssetTypePropertyCondition());
        }
        if(search.getSearchCondition().getTid() == null){
        	errors.rejectValue("NOT_TENANT_ID", "未正确设置租户ID");
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param assetTypeProperty 资产分类属性
     * @param errors
     */
	public void validateAssetTypePropertyAddDto(AssetTypePropertyAddDto assetTypeProperty, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if (null == assetTypeProperty.getAssetType() ) {
			errors.rejectValue(AssetTypeProperty.PROPERTY_ASSET_TYPE, "EMPTY_"+AssetTypeProperty.PROPERTY_ASSET_TYPE, "资产分类不能为空");
		}
		if(StringUtils.isEmpty(assetTypeProperty.getCode())){
			errors.rejectValue(AssetTypeProperty.PROPERTY_CODE, "EMPTY_"+AssetTypeProperty.PROPERTY_CODE, "属性代码不能为空");
		}

		//验证长度
		if(StringUtils.length(assetTypeProperty.getName()) > 255){
			errors.rejectValue(AssetTypeProperty.PROPERTY_NAME,null,"属性名称最长255个字符");
		}
		if(StringUtils.length(assetTypeProperty.getType()) > 255){
			errors.rejectValue(AssetTypeProperty.PROPERTY_TYPE,null,"属性类型最长255个字符");
		}
		if(StringUtils.length(assetTypeProperty.getCode()) > 255){
			errors.rejectValue(AssetTypeProperty.PROPERTY_CODE,null,"属性代码最长255个字符");
		}
		if(StringUtils.length(assetTypeProperty.getOptionValues()) > 255){
			errors.rejectValue(AssetTypeProperty.PROPERTY_OPTION_VALUES,null,"备选值最长255个字符");
		}
	}
}