package net.aicoder.devp.maintenance.business.common.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.maintenance.business.common.dto.SimpleConfigAddDto;
import net.aicoder.devp.maintenance.business.common.dto.SimpleConfigEditDto;
import net.aicoder.devp.maintenance.business.common.domain.SimpleConfig;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class SimpleConfigValidator implements Validator {

	/**
	 * 判断支持的JavaBean类型
	 * @param aClass
	 * @return
	 */
	@Override
	public boolean supports(Class<?> aClass) {
		if(SimpleConfigAddDto.class.equals(aClass))
			return true;
		if(SimpleConfigEditDto.class.equals(aClass))
			return true;
		if(PageSearchRequest.class.equals(aClass))
			return true;
		return false;
	}

	/**
	 * 实现Validator中的validate接口
	 * @param obj
	 * @param errors
	 */
	@Override
	public void validate(Object obj, Errors errors) {
	    if(obj instanceof SimpleConfigAddDto){
            this.validateSimpleConfigAddDto((SimpleConfigAddDto)obj, errors);
        }
	}

	/**
     * 实现Validator中的validate接口
     * @param simpleConfig 通用配置
     * @param errors
     */
	public void validateSimpleConfigAddDto(SimpleConfigAddDto simpleConfig, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if(StringUtils.isEmpty(simpleConfig.getConfigType())){
			errors.rejectValue(SimpleConfig.PROPERTY_CONFIG_TYPE, "EMPTY_"+SimpleConfig.PROPERTY_CONFIG_TYPE, "配置类型不能为空");
		}
		if(StringUtils.isEmpty(simpleConfig.getDisplayName())){
			errors.rejectValue(SimpleConfig.PROPERTY_DISPLAY_NAME, "EMPTY_"+SimpleConfig.PROPERTY_DISPLAY_NAME, "展现名称不能为空");
		}
		if(StringUtils.isEmpty(simpleConfig.getCode())){
			errors.rejectValue(SimpleConfig.PROPERTY_CODE, "EMPTY_"+SimpleConfig.PROPERTY_CODE, "参数代码不能为空");
		}
		if(StringUtils.isEmpty(simpleConfig.getValue())){
			errors.rejectValue(SimpleConfig.PROPERTY_VALUE, "EMPTY_"+SimpleConfig.PROPERTY_VALUE, "参数值不能为空");
		}

		//验证长度
		if(StringUtils.length(simpleConfig.getConfigType()) > 255){
			errors.rejectValue(SimpleConfig.PROPERTY_CONFIG_TYPE,null,"配置类型最长255个字符");
		}
		if(StringUtils.length(simpleConfig.getDisplayName()) > 255){
			errors.rejectValue(SimpleConfig.PROPERTY_DISPLAY_NAME,null,"展现名称最长255个字符");
		}
		if(StringUtils.length(simpleConfig.getCode()) > 255){
			errors.rejectValue(SimpleConfig.PROPERTY_CODE,null,"参数代码最长255个字符");
		}
		if(StringUtils.length(simpleConfig.getValue()) > 255){
			errors.rejectValue(SimpleConfig.PROPERTY_VALUE,null,"参数值最长255个字符");
		}
	}
}