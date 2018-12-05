package net.aicoder.speedcloud.console.business.icode.codegen.valid;


import net.aicoder.speedcloud.console.business.icode.codegen.dto.CodeGenTaskAddDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class CodeGenTaskValidator implements Validator {

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
	    if(obj instanceof CodeGenTaskAddDto){
            this.validateAddDto((CodeGenTaskAddDto)obj, errors);
        }
	}

	/**
     * 验证新增信息
     * @param domain 领域
     * @param errors
     */
	public void validateAddDto(CodeGenTaskAddDto domain, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if(StringUtils.isEmpty(domain.getTargetComponentId())){
			errors.rejectValue("targetComponentId", null, "目标工程信息未设置");
		}
		if(StringUtils.isEmpty(domain.getModelComponentId())){
			errors.rejectValue("modelComponentId", null, "模型所在组件未设置");
		}
		if(StringUtils.isEmpty(domain.getModelId())){
			errors.rejectValue("modelId", null, "模型未设置");
		}
		if(StringUtils.isEmpty(domain.getModelType())){
			errors.rejectValue("modelType", null, "模型类型未设置");
		}
		if(StringUtils.isEmpty(domain.getTplCodeId())){
			errors.rejectValue("tplCodeId", null, "模板未设置");
		}

	}
}