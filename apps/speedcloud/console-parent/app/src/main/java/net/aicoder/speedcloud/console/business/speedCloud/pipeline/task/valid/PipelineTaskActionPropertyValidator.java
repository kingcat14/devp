package net.aicoder.speedcloud.console.business.speedCloud.pipeline.task.valid;


import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskActionPropertyAddDto;
import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskActionPropertyEditDto;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class PipelineTaskActionPropertyValidator implements Validator {

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
	    if(obj instanceof PipelineTaskActionPropertyAddDto){
            this.validateAddDto((PipelineTaskActionPropertyAddDto)obj, errors);
        }
	}

	/**
     * 验证新增信息
     * @param pipelineTaskActionProperty 操作属性
     * @param errors
     */
		public void validateAddDto(PipelineTaskActionPropertyAddDto pipelineTaskActionProperty, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(pipelineTaskActionProperty.getName()) > 255){
			errors.rejectValue("name", null, "属性名称最长255个字符");
		}
		if(StringUtils.length(pipelineTaskActionProperty.getCode()) > 255){
			errors.rejectValue("code", null, "属性代码最长255个字符");
		}
		if(StringUtils.length(pipelineTaskActionProperty.getType()) > 255){
			errors.rejectValue("type", null, "类型最长255个字符");
		}
	}
}