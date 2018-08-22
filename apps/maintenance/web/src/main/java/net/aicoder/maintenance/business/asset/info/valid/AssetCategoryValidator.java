package net.aicoder.maintenance.business.asset.info.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;

import net.aicoder.maintenance.business.asset.info.domain.AssetCategory;
import net.aicoder.maintenance.business.asset.info.dto.AssetCategoryAddDto;
import net.aicoder.maintenance.business.asset.info.dto.AssetCategoryEditDto;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class AssetCategoryValidator implements Validator {

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
	    if(obj instanceof AssetCategoryAddDto){
            this.validateAssetCategoryAddDto((AssetCategoryAddDto)obj, errors);
        }
	}

	/**
     * 实现Validator中的validate接口
     * @param assetCategory 资产大类
     * @param errors
     */
	public void validateAssetCategoryAddDto(AssetCategoryAddDto assetCategory, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if(StringUtils.isEmpty(assetCategory.getName())){
			errors.rejectValue(AssetCategory.PROPERTY_NAME, "EMPTY_"+AssetCategory.PROPERTY_NAME, "名称不能为空");
		}
		if(StringUtils.isEmpty(assetCategory.getCode())){
			errors.rejectValue(AssetCategory.PROPERTY_CODE, "EMPTY_"+AssetCategory.PROPERTY_CODE, "代码不能为空");
		}

		//验证长度
		if(StringUtils.length(assetCategory.getNum()) > 255){
			errors.rejectValue(AssetCategory.PROPERTY_NUM,null,"编号最长255个字符");
		}
		if(StringUtils.length(assetCategory.getName()) > 255){
			errors.rejectValue(AssetCategory.PROPERTY_NAME,null,"名称最长255个字符");
		}
		if(StringUtils.length(assetCategory.getCode()) > 255){
			errors.rejectValue(AssetCategory.PROPERTY_CODE,null,"代码最长255个字符");
		}
		if(StringUtils.length(assetCategory.getViewIndex()) > 255){
			errors.rejectValue(AssetCategory.PROPERTY_VIEW_INDEX,null,"展现顺序最长255个字符");
		}
	}
}