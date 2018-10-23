package net.aicoder.speedcloud.console.business.asset.asset.config.valid;


import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.asset.business.asset.config.dto.AssetCategoryAddDto;
import net.aicoder.speedcloud.asset.business.asset.config.dto.AssetCategoryEditDto;
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
            this.validateAddDto((AssetCategoryAddDto)obj, errors);
        }
	}

	/**
     * 验证新增信息
     * @param assetCategory 资产大类
     * @param errors
     */
	public void validateAddDto(AssetCategoryAddDto assetCategory, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if(StringUtils.isEmpty(assetCategory.getName())){
			errors.rejectValue("name", "EMPTY_NAME", "名称不能为空");
		}
		
		if(StringUtils.isEmpty(assetCategory.getCode())){
			errors.rejectValue("code", "EMPTY_CODE", "代码不能为空");
		}
		

		//验证长度
		if(StringUtils.length(assetCategory.getNum()) > 255){
			errors.rejectValue("num", null, "编号最长255个字符");
		}
		if(StringUtils.length(assetCategory.getName()) > 255){
			errors.rejectValue("name", null, "名称最长255个字符");
		}
		if(StringUtils.length(assetCategory.getCode()) > 255){
			errors.rejectValue("code", null, "代码最长255个字符");
		}
		if(StringUtils.length(assetCategory.getViewIndex()) > 255){
			errors.rejectValue("viewIndex", null, "展现顺序最长255个字符");
		}
	}
}