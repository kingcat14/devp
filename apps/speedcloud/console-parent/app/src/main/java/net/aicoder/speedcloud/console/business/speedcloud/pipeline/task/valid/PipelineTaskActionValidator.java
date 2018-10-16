package net.aicoder.speedcloud.console.business.speedcloud.pipeline.task.valid;


import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskActionAddDto;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class PipelineTaskActionValidator implements Validator {

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
	    if(obj instanceof PipelineTaskActionAddDto){
            this.validateAddDto((PipelineTaskActionAddDto)obj, errors);
        }
	}

	/**
     * 验证新增信息
     * @param pipelineTaskAction 操作
     * @param errors
     */
		public void validateAddDto(PipelineTaskActionAddDto pipelineTaskAction, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if (null == pipelineTaskAction.getTask() ) {
			errors.rejectValue("task", "EMPTY_TASK", "所属任务不能为空");
		}

		//验证长度
		if(StringUtils.length(pipelineTaskAction.getName()) > 255){
			errors.rejectValue("name", null, "操作名称最长255个字符");
		}
		if(StringUtils.length(pipelineTaskAction.getMemo()) > 255){
			errors.rejectValue("memo", null, "操作说明最长255个字符");
		}
	}
}