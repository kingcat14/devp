package net.aicoder.speedcloud.console.business.speedcloud.pipeline.task.valid;


import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskAddDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class PipelineTaskValidator implements Validator {

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
	    if(obj instanceof PipelineTaskAddDto){
            this.validateAddDto((PipelineTaskAddDto)obj, errors);
        }
	}

	/**
     * 验证新增信息
     * @param pipelineTask 任务
     * @param errors
     */
		public void validateAddDto(PipelineTaskAddDto pipelineTask, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if(StringUtils.isEmpty(pipelineTask.getName())){
			errors.rejectValue("name", "EMPTY_NAME", "任务名称不能为空");
		}
		if(StringUtils.isEmpty(pipelineTask.getExecType())){
			errors.rejectValue("execType", "EMPTY_EXEC_TYPE", "任务执行方式不能为空");
		}

		//验证长度
		if(StringUtils.length(pipelineTask.getName()) > 255){
			errors.rejectValue("name", null, "任务名称最长255个字符");
		}
		if(StringUtils.length(pipelineTask.getTaskStartTime()) > 255){
			errors.rejectValue("taskStartTime", null, "执行开始时间最长255个字符");
		}
		if(StringUtils.length(pipelineTask.getTaskDayOfWeeks()) > 255){
			errors.rejectValue("taskDayOfWeeks", null, "执行日最长255个字符");
		}
	}
}