package net.aicoder.speedcloud.console.business.speedcloud.pipeline.template.valid;


import net.aicoder.speedcloud.business.pipeline.template.dto.PipelineTemplateTaskAddDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class PipelineTemplateTaskValidator implements Validator {

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
	    if(obj instanceof PipelineTemplateTaskAddDto){
            this.validateAddDto((PipelineTemplateTaskAddDto)obj, errors);
        }
	}

	/**
     * 验证新增信息
     * @param pipelineTemplateTask 任务模板
     * @param errors
     */
	public void validateAddDto(PipelineTemplateTaskAddDto pipelineTemplateTask, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(pipelineTemplateTask.getName()) > 255){
			errors.rejectValue("name", null, "任务名称最长255个字符");
		}
		if(StringUtils.length(pipelineTemplateTask.getTaskStartTime()) > 255){
			errors.rejectValue("taskStartTime", null, "执行开始时间最长255个字符");
		}
		if(StringUtils.length(pipelineTemplateTask.getTaskDayOfWeeks()) > 255){
			errors.rejectValue("taskDayOfWeeks", null, "执行日最长255个字符");
		}
	}
}