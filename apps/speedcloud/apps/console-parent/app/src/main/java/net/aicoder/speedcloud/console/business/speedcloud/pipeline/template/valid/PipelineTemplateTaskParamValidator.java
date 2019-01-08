package net.aicoder.speedcloud.console.business.speedcloud.pipeline.template.valid;


import net.aicoder.speedcloud.business.pipeline.template.dto.PipelineTemplateTaskParamAddDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class PipelineTemplateTaskParamValidator implements Validator {

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
	    if(obj instanceof PipelineTemplateTaskParamAddDto){
            this.validateAddDto((PipelineTemplateTaskParamAddDto)obj, errors);
        }
	}

	/**
     * 验证新增信息
     * @param pipelineTemplateTaskParam 任务模板参数
     * @param errors
     */
	public void validateAddDto(PipelineTemplateTaskParamAddDto pipelineTemplateTaskParam, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(pipelineTemplateTaskParam.getTask()) > 255){
			errors.rejectValue("task", null, "所属任务最长255个字符");
		}
		if(StringUtils.length(pipelineTemplateTaskParam.getName()) > 255){
			errors.rejectValue("name", null, "参数名称最长255个字符");
		}
		if(StringUtils.length(pipelineTemplateTaskParam.getType()) > 255){
			errors.rejectValue("type", null, "参数类型最长255个字符");
		}
		if(StringUtils.length(pipelineTemplateTaskParam.getDefaultValue()) > 255){
			errors.rejectValue("defaultValue", null, "默认值最长255个字符");
		}
		if(StringUtils.length(pipelineTemplateTaskParam.getDescription()) > 255){
			errors.rejectValue("description", null, "参数描述最长255个字符");
		}
		if(StringUtils.length(pipelineTemplateTaskParam.getEnumValue()) > 255){
			errors.rejectValue("enumValue", null, "可选值最长255个字符");
		}
		if(StringUtils.length(pipelineTemplateTaskParam.getValue()) > 255){
			errors.rejectValue("value", null, "参数值最长255个字符");
		}
	}
}