package net.aicoder.speedcloud.console.business.speedCloud.pipeline.exec.valid;


import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecNodeParamAddDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class PipelineExecNodeParamValidator implements Validator {

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
	    if(obj instanceof PipelineExecNodeParamAddDto){
            this.validateAddDto((PipelineExecNodeParamAddDto)obj, errors);
        }
	}

	/**
     * 验证新增信息
     * @param pipelineExecNodeParam 运行实例节点参数
     * @param errors
     */
		public void validateAddDto(PipelineExecNodeParamAddDto pipelineExecNodeParam, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(pipelineExecNodeParam.getName()) > 255){
			errors.rejectValue("name", null, "属性名称最长255个字符");
		}
		if(StringUtils.length(pipelineExecNodeParam.getCode()) > 255){
			errors.rejectValue("code", null, "属性代码最长255个字符");
		}
		if(StringUtils.length(pipelineExecNodeParam.getType()) > 255){
			errors.rejectValue("type", null, "类型最长255个字符");
		}
	}
}