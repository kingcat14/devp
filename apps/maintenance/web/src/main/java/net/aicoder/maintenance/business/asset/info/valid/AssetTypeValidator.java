package net.aicoder.maintenance.business.asset.info.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;

import net.aicoder.maintenance.business.asset.info.domain.AssetType;
import net.aicoder.maintenance.business.asset.info.dto.AssetTypeAddDto;
import net.aicoder.maintenance.business.asset.info.dto.AssetTypeEditDto;

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
	}

	/**
     * 实现Validator中的validate接口
     * @param assetType 资产分类
     * @param errors
     */
	public void validateAssetTypeAddDto(AssetTypeAddDto assetType, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(assetType.getNum()) > 255){
			errors.rejectValue(AssetType.PROPERTY_NUM,null,"编号最长255个字符");
		}
		if(StringUtils.length(assetType.getName()) > 255){
			errors.rejectValue(AssetType.PROPERTY_NAME,null,"名称最长255个字符");
		}
		if(StringUtils.length(assetType.getCode()) > 255){
			errors.rejectValue(AssetType.PROPERTY_CODE,null,"代码最长255个字符");
		}
		if(StringUtils.length(assetType.getViewIndex()) > 255){
			errors.rejectValue(AssetType.PROPERTY_VIEW_INDEX,null,"展现顺序最长255个字符");
		}
		if(StringUtils.length(assetType.getParentCode()) > 255){
			errors.rejectValue(AssetType.PROPERTY_PARENT_CODE,null,"上级代码最长255个字符");
		}
	}
}