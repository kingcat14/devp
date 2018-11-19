package net.aicoder.speedcloud.console.business.asset.asset.config.valid;


import net.aicoder.speedcloud.asset.business.asset.config.dto.AssetTypePropertyAddDto;
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
            this.validateAddDto((AssetTypePropertyAddDto)obj, errors);
        }
	}

	/**
     * 验证新增信息
     * @param assetTypeProperty 资产分类属性
     * @param errors
     */
	public void validateAddDto(AssetTypePropertyAddDto assetTypeProperty, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if (null == assetTypeProperty.getAssetType() ) {
			errors.rejectValue("assetType", "EMPTY_ASSET_TYPE", "资产分类不能为空");
		}
		if(StringUtils.isEmpty(assetTypeProperty.getCode())){
			errors.rejectValue("code", "EMPTY_CODE", "属性代码不能为空");
		}
		

		//验证长度
		if(StringUtils.length(assetTypeProperty.getName()) > 255){
			errors.rejectValue("name", null, "属性名称最长255个字符");
		}
		if(StringUtils.length(assetTypeProperty.getType()) > 255){
			errors.rejectValue("type", null, "属性类型最长255个字符");
		}
		if(StringUtils.length(assetTypeProperty.getCode()) > 255){
			errors.rejectValue("code", null, "属性代码最长255个字符");
		}
		if(StringUtils.length(assetTypeProperty.getOptionValues()) > 255){
			errors.rejectValue("optionValues", null, "备选值最长255个字符");
		}
	}
}