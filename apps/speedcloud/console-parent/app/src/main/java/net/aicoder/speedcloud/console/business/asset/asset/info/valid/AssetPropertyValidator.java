package net.aicoder.speedcloud.console.business.asset.asset.info.valid;


import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.asset.business.asset.info.dto.AssetPropertyAddDto;
import net.aicoder.speedcloud.asset.business.asset.info.dto.AssetPropertyEditDto;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class AssetPropertyValidator implements Validator {

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
	    if(obj instanceof AssetPropertyAddDto){
            this.validateAddDto((AssetPropertyAddDto)obj, errors);
        }
	}

	/**
     * 验证新增信息
     * @param assetProperty 资产属性
     * @param errors
     */
		public void validateAddDto(AssetPropertyAddDto assetProperty, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(assetProperty.getName()) > 255){
			errors.rejectValue("name", null, "属性名称最长255个字符");
		}
		if(StringUtils.length(assetProperty.getValue()) > 255){
			errors.rejectValue("value", null, "属性值最长255个字符");
		}
	}
}