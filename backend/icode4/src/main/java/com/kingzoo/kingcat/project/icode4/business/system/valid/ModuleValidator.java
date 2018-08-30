package com.kingzoo.kingcat.project.icode4.business.system.valid;

import com.kingzoo.kingcat.commons.framework.spring.data.PageSearchRequest;
import com.kingzoo.kingcat.project.icode4.business.system.controller.vo.ModuleAddRequest;
import com.kingzoo.kingcat.project.icode4.business.system.domain.Module;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ModuleValidator implements Validator {

	/**
	 * 判断支持的JavaBean类型
	 * @param aClass
	 * @return
	 */
	@Override
	public boolean supports(Class<?> aClass) {
		if(PageSearchRequest.class.equals(aClass))
			return true;
		if(ModuleAddRequest.class.equals(aClass))
            return true;
		return Module.class.equals(aClass);
	}

	/**
	 * 实现Validator中的validate接口
	 * @param obj
	 * @param errors
	 */
	@Override
	public void validate(Object obj, Errors errors) {
	    if(obj instanceof Module){
            this.validateModule((Module)obj, errors);
        }
	}

	/**
     * 实现Validator中的validate接口
     * @param module 模块
     * @param errors
     */
	public void validateModule(Module module, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if(StringUtils.isEmpty(module.getName())){
			errors.rejectValue(Module.PROPERTY_NAME, "EMPTY_"+Module.PROPERTY_NAME, "模块名称不能为空");
		}
		if(StringUtils.isEmpty(module.getCode())){
			errors.rejectValue(Module.PROPERTY_CODE, "EMPTY_"+Module.PROPERTY_CODE, "模块代码不能为空");
		}
		if(StringUtils.isEmpty(module.getSystemId()) && StringUtils.isEmpty(module.getParentModuleId())){
			errors.rejectValue(Module.PROPERTY_SYSTEM_ID, "EMPTY_"+Module.PROPERTY_SYSTEM_ID, "所属系统不能为空和父模块不能同时为空");
		}

		//验证长度
		if(StringUtils.length(module.getName()) > 255){
			errors.rejectValue(Module.PROPERTY_NAME,null,"模块名称最长255个字符");
		}
		if(StringUtils.length(module.getCode()) > 255){
			errors.rejectValue(Module.PROPERTY_CODE,null,"模块代码最长255个字符");
		}
	}
}