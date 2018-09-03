package net.aicoder.speedcloud.business.pipeline.exec.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecInstanceNodeParamAddDto;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecInstanceNodeParamEditDto;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecInstanceNodeParamCondition;
import net.aicoder.speedcloud.business.pipeline.exec.domain.PipelineExecInstanceNodeParam;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class PipelineExecInstanceNodeParamValidator implements Validator {

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
	    if(obj instanceof PipelineExecInstanceNodeParamAddDto){
            this.validatePipelineExecInstanceNodeParamAddDto((PipelineExecInstanceNodeParamAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<PipelineExecInstanceNodeParamCondition> search){
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new PipelineExecInstanceNodeParamCondition());
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param pipelineExecInstanceNodeParam 运行实例节点参数
     * @param errors
     */
	public void validatePipelineExecInstanceNodeParamAddDto(PipelineExecInstanceNodeParamAddDto pipelineExecInstanceNodeParam, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(pipelineExecInstanceNodeParam.getName()) > 255){
			errors.rejectValue(PipelineExecInstanceNodeParam.PROPERTY_NAME,null,"属性名称最长255个字符");
		}
		if(StringUtils.length(pipelineExecInstanceNodeParam.getCode()) > 255){
			errors.rejectValue(PipelineExecInstanceNodeParam.PROPERTY_CODE,null,"属性代码最长255个字符");
		}
		if(StringUtils.length(pipelineExecInstanceNodeParam.getType()) > 255){
			errors.rejectValue(PipelineExecInstanceNodeParam.PROPERTY_TYPE,null,"类型最长255个字符");
		}
	}
}