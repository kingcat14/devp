package net.aicoder.speedcloud.business.pipeline.exec.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecNodeParamAddDto;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecNodeParamEditDto;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecNodeParamCondition;
import net.aicoder.speedcloud.business.pipeline.exec.domain.PipelineExecNodeParam;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

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
            this.validatePipelineExecNodeParamAddDto((PipelineExecNodeParamAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<PipelineExecNodeParamCondition> search){
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new PipelineExecNodeParamCondition());
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param pipelineExecNodeParam 运行实例节点参数
     * @param errors
     */
	public void validatePipelineExecNodeParamAddDto(PipelineExecNodeParamAddDto pipelineExecNodeParam, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(pipelineExecNodeParam.getName()) > 255){
			errors.rejectValue(PipelineExecNodeParam.PROPERTY_NAME,null,"属性名称最长255个字符");
		}
		if(StringUtils.length(pipelineExecNodeParam.getCode()) > 255){
			errors.rejectValue(PipelineExecNodeParam.PROPERTY_CODE,null,"属性代码最长255个字符");
		}
		if(StringUtils.length(pipelineExecNodeParam.getType()) > 255){
			errors.rejectValue(PipelineExecNodeParam.PROPERTY_TYPE,null,"类型最长255个字符");
		}
	}
}