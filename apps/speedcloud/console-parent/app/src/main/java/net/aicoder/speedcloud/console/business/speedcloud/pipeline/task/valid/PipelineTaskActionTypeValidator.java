package net.aicoder.speedcloud.console.business.speedcloud.pipeline.task.valid;


import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskActionTypeAddDto;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class PipelineTaskActionTypeValidator implements Validator {

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
	    if(obj instanceof PipelineTaskActionTypeAddDto){
            this.validateAddDto((PipelineTaskActionTypeAddDto)obj, errors);
        }
	}

	/**
     * 验证新增信息
     * @param pipelineTaskActionType 操作类型
     * @param errors
     */
		public void validateAddDto(PipelineTaskActionTypeAddDto pipelineTaskActionType, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(pipelineTaskActionType.getCode()) > 255){
			errors.rejectValue("code", null, "类型代码最长255个字符");
		}
		if(StringUtils.length(pipelineTaskActionType.getName()) > 255){
			errors.rejectValue("name", null, "类型名称最长255个字符");
		}
		if(StringUtils.length(pipelineTaskActionType.getViewOrder()) > 255){
			errors.rejectValue("viewOrder", null, "展现顺序最长255个字符");
		}
		if(StringUtils.length(pipelineTaskActionType.getMemo()) > 255){
			errors.rejectValue("memo", null, "说明最长255个字符");
		}
	}
}