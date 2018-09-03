package net.aicoder.speedcloud.console.business.speedCloud.pipeline.exec.valid;


import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecInstanceNodeAddDto;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecInstanceNodeEditDto;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class PipelineExecInstanceNodeValidator implements Validator {

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
	    if(obj instanceof PipelineExecInstanceNodeAddDto){
            this.validateAddDto((PipelineExecInstanceNodeAddDto)obj, errors);
        }
	}

	/**
     * 验证新增信息
     * @param pipelineExecInstanceNode 运行实例节点
     * @param errors
     */
		public void validateAddDto(PipelineExecInstanceNodeAddDto pipelineExecInstanceNode, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(pipelineExecInstanceNode.getCode()) > 255){
			errors.rejectValue("code", null, "编号最长255个字符");
		}
		if(StringUtils.length(pipelineExecInstanceNode.getName()) > 255){
			errors.rejectValue("name", null, "节点名称最长255个字符");
		}
		if(StringUtils.length(pipelineExecInstanceNode.getNodeType()) > 255){
			errors.rejectValue("nodeType", null, "节点类型最长255个字符");
		}
		if(StringUtils.length(pipelineExecInstanceNode.getExecMode()) > 255){
			errors.rejectValue("execMode", null, "执行方式最长255个字符");
		}
		if(StringUtils.length(pipelineExecInstanceNode.getStatus()) > 255){
			errors.rejectValue("status", null, "运行状态最长255个字符");
		}
		if(StringUtils.length(pipelineExecInstanceNode.getResult()) > 255){
			errors.rejectValue("result", null, "运行结果最长255个字符");
		}
		if(StringUtils.length(pipelineExecInstanceNode.getResultMessage()) > 255){
			errors.rejectValue("resultMessage", null, "结果消息最长255个字符");
		}
		if(StringUtils.length(pipelineExecInstanceNode.getParentId()) > 255){
			errors.rejectValue("parentId", null, "上级节点最长255个字符");
		}
	}
}