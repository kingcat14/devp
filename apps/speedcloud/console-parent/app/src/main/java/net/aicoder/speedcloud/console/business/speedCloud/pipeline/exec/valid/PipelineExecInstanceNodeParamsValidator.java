package net.aicoder.speedcloud.console.business.speedCloud.pipeline.exec.valid;


import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecInstanceNodeParamsAddDto;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecInstanceNodeParamsEditDto;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class PipelineExecInstanceNodeParamsValidator implements Validator {

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
	    if(obj instanceof PipelineExecInstanceNodeParamsAddDto){
            this.validateAddDto((PipelineExecInstanceNodeParamsAddDto)obj, errors);
        }
	}

	/**
     * 验证新增信息
     * @param pipelineExecInstanceNodeParams 运行实例节点参数
     * @param errors
     */
		public void validateAddDto(PipelineExecInstanceNodeParamsAddDto pipelineExecInstanceNodeParams, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(pipelineExecInstanceNodeParams.getName()) > 255){
			errors.rejectValue("name", null, "属性名称最长255个字符");
		}
		if(StringUtils.length(pipelineExecInstanceNodeParams.getCode()) > 255){
			errors.rejectValue("code", null, "属性代码最长255个字符");
		}
		if(StringUtils.length(pipelineExecInstanceNodeParams.getType()) > 255){
			errors.rejectValue("type", null, "类型最长255个字符");
		}
	}
}