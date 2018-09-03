package net.aicoder.speedcloud.console.business.speedCloud.pipeline.exec.valid;


import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecInstanceAddDto;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecInstanceEditDto;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class PipelineExecInstanceValidator implements Validator {

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
	    if(obj instanceof PipelineExecInstanceAddDto){
            this.validateAddDto((PipelineExecInstanceAddDto)obj, errors);
        }
	}

	/**
     * 验证新增信息
     * @param pipelineExecInstance 运行计划
     * @param errors
     */
		public void validateAddDto(PipelineExecInstanceAddDto pipelineExecInstance, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(pipelineExecInstance.getCode()) > 255){
			errors.rejectValue("code", null, "编号最长255个字符");
		}
		if(StringUtils.length(pipelineExecInstance.getExecuteTargetType()) > 255){
			errors.rejectValue("executeTargetType", null, "运行类型最长255个字符");
		}
		if(StringUtils.length(pipelineExecInstance.getStatus()) > 255){
			errors.rejectValue("status", null, "运行状态最长255个字符");
		}
		if(StringUtils.length(pipelineExecInstance.getResult()) > 255){
			errors.rejectValue("result", null, "运行结果最长255个字符");
		}
	}
}