package net.aicoder.speedcloud.business.pipeline.exec.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecNodeAddDto;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecNodeEditDto;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecNodeCondition;
import net.aicoder.speedcloud.business.pipeline.exec.domain.PipelineExecNode;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class PipelineExecNodeValidator implements Validator {

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
	    if(obj instanceof PipelineExecNodeAddDto){
            this.validatePipelineExecNodeAddDto((PipelineExecNodeAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<PipelineExecNodeCondition> search){
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new PipelineExecNodeCondition());
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param pipelineExecNode 运行实例节点
     * @param errors
     */
	public void validatePipelineExecNodeAddDto(PipelineExecNodeAddDto pipelineExecNode, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(pipelineExecNode.getCode()) > 255){
			errors.rejectValue(PipelineExecNode.PROPERTY_CODE,null,"编号最长255个字符");
		}
		if(StringUtils.length(pipelineExecNode.getName()) > 255){
			errors.rejectValue(PipelineExecNode.PROPERTY_NAME,null,"节点名称最长255个字符");
		}
		if(StringUtils.length(pipelineExecNode.getNodeType()) > 255){
			errors.rejectValue(PipelineExecNode.PROPERTY_NODE_TYPE,null,"节点类型最长255个字符");
		}
		if(StringUtils.length(pipelineExecNode.getExecMode()) > 255){
			errors.rejectValue(PipelineExecNode.PROPERTY_EXEC_MODE,null,"执行方式最长255个字符");
		}
		if(StringUtils.length(pipelineExecNode.getStatus()) > 255){
			errors.rejectValue(PipelineExecNode.PROPERTY_STATUS,null,"运行状态最长255个字符");
		}
		if(StringUtils.length(pipelineExecNode.getResult()) > 255){
			errors.rejectValue(PipelineExecNode.PROPERTY_RESULT,null,"运行结果最长255个字符");
		}
		if(StringUtils.length(pipelineExecNode.getResultMessage()) > 255){
			errors.rejectValue(PipelineExecNode.PROPERTY_RESULT_MESSAGE,null,"结果消息最长255个字符");
		}
		if(StringUtils.length(pipelineExecNode.getParentId()) > 255){
			errors.rejectValue(PipelineExecNode.PROPERTY_PARENT_ID,null,"上级节点最长255个字符");
		}
	}
}