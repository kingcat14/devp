package net.aicoder.speedcloud.business.pipeline.exec.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.pipeline.exec.domain.PipelineExecInstance;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecInstanceAddDto;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecInstanceCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

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
            this.validatePipelineExecInstanceAddDto((PipelineExecInstanceAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<PipelineExecInstanceCondition> search){
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new PipelineExecInstanceCondition());
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param pipelineExecInstance 运行计划
     * @param errors
     */
	public void validatePipelineExecInstanceAddDto(PipelineExecInstanceAddDto pipelineExecInstance, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(pipelineExecInstance.getCode()) > 255){
			errors.rejectValue(PipelineExecInstance.PROPERTY_CODE,null,"编号最长255个字符");
		}
		if(StringUtils.length(pipelineExecInstance.getExecuteTargetType()) > 255){
			errors.rejectValue(PipelineExecInstance.PROPERTY_EXECUTE_TARGET_TYPE,null,"运行类型最长255个字符");
		}
		if(StringUtils.length(pipelineExecInstance.getStatus()) > 255){
			errors.rejectValue(PipelineExecInstance.PROPERTY_STATUS,null,"运行状态最长255个字符");
		}
		if(StringUtils.length(pipelineExecInstance.getResult()) > 255){
			errors.rejectValue(PipelineExecInstance.PROPERTY_RESULT,null,"运行结果最长255个字符");
		}
	}
}