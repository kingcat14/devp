package net.aicoder.speedcloud.asset.business.asset.config.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.asset.business.asset.config.dto.AssetTypePropertyAddDto;
import net.aicoder.speedcloud.asset.business.asset.config.dto.AssetTypePropertyEditDto;
import net.aicoder.speedcloud.asset.business.asset.config.dto.AssetTypePropertyCondition;
import net.aicoder.speedcloud.asset.business.asset.config.domain.AssetTypeProperty;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

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
            this.validateSearchDto((PageSearchRequest)obj);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<AssetTypePropertyCondition> search){
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new AssetTypePropertyCondition());
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

		//验证长度
		if(StringUtils.length(assetTypeProperty.getName()) > 255){
			errors.rejectValue(AssetTypeProperty.PROPERTY_NAME,null,"属性名称最长255个字符");
		}
		if(StringUtils.length(assetTypeProperty.getType()) > 255){
			errors.rejectValue(AssetTypeProperty.PROPERTY_TYPE,null,"属性类型最长255个字符");
		}
		if(StringUtils.length(assetTypeProperty.getOptionValues()) > 255){
			errors.rejectValue(AssetTypeProperty.PROPERTY_OPTION_VALUES,null,"备选值最长255个字符");
		}
	}
}