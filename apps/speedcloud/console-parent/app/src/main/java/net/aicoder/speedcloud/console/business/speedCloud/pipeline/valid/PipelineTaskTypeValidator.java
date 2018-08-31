package net.aicoder.speedcloud.console.business.speedCloud.pipeline.valid;


import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineTaskTypeAddDto;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineTaskTypeEditDto;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class PipelineTaskTypeValidator implements Validator {

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
	    if(obj instanceof PipelineTaskTypeAddDto){
            this.validateAddDto((PipelineTaskTypeAddDto)obj, errors);
        }
	}

	/**
     * 验证新增信息
     * @param pipelineTaskType 任务类型
     * @param errors
     */
		public void validateAddDto(PipelineTaskTypeAddDto pipelineTaskType, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(pipelineTaskType.getCode()) > 255){
			errors.rejectValue("code", null, "类型代码最长255个字符");
		}
		if(StringUtils.length(pipelineTaskType.getName()) > 255){
			errors.rejectValue("name", null, "类型名称最长255个字符");
		}
	}
}