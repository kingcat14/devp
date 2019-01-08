package net.aicoder.speedcloud.console.business.speedcloud.pipeline.template.valid;


import net.aicoder.speedcloud.business.pipeline.template.dto.PipelineTemplateTaskActionAddDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class PipelineTemplateTaskActionValidator implements Validator {

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
	    if(obj instanceof PipelineTemplateTaskActionAddDto){
            this.validateAddDto((PipelineTemplateTaskActionAddDto)obj, errors);
        }
	}

	/**
     * 验证新增信息
     * @param pipelineTemplateTaskAction 操作模板
     * @param errors
     */
	public void validateAddDto(PipelineTemplateTaskActionAddDto pipelineTemplateTaskAction, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(pipelineTemplateTaskAction.getTask()) > 255){
			errors.rejectValue("task", null, "所属任务最长255个字符");
		}
		if(StringUtils.length(pipelineTemplateTaskAction.getMemo()) > 255){
			errors.rejectValue("memo", null, "操作说明最长255个字符");
		}
		if(StringUtils.length(pipelineTemplateTaskAction.getName()) > 255){
			errors.rejectValue("name", null, "操作名称最长255个字符");
		}
	}
}