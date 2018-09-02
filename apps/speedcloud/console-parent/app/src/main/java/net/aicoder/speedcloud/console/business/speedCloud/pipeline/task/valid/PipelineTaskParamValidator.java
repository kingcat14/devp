package net.aicoder.speedcloud.console.business.speedCloud.pipeline.task.valid;


import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskParamAddDto;
import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskParamEditDto;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class PipelineTaskParamValidator implements Validator {

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
	    if(obj instanceof PipelineTaskParamAddDto){
            this.validateAddDto((PipelineTaskParamAddDto)obj, errors);
        }
	}

	/**
     * 验证新增信息
     * @param pipelineTaskParam 任务参数
     * @param errors
     */
		public void validateAddDto(PipelineTaskParamAddDto pipelineTaskParam, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(pipelineTaskParam.getName()) > 255){
			errors.rejectValue("name", null, "参数名称最长255个字符");
		}
		if(StringUtils.length(pipelineTaskParam.getType()) > 255){
			errors.rejectValue("type", null, "参数类型最长255个字符");
		}
		if(StringUtils.length(pipelineTaskParam.getDefaultValue()) > 255){
			errors.rejectValue("defaultValue", null, "默认值最长255个字符");
		}
		if(StringUtils.length(pipelineTaskParam.getDescription()) > 255){
			errors.rejectValue("description", null, "参数描述最长255个字符");
		}
		if(StringUtils.length(pipelineTaskParam.getEnumValue()) > 255){
			errors.rejectValue("enumValue", null, "可选值最长255个字符");
		}
		if(StringUtils.length(pipelineTaskParam.getValue()) > 255){
			errors.rejectValue("value", null, "参数值最长255个字符");
		}
	}
}