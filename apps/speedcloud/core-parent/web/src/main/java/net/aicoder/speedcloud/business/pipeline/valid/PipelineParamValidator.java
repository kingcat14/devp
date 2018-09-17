package net.aicoder.speedcloud.business.pipeline.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineParamAddDto;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineParamEditDto;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineParamCondition;
import net.aicoder.speedcloud.business.pipeline.domain.PipelineParam;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class PipelineParamValidator implements Validator {

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
	    if(obj instanceof PipelineParamAddDto){
            this.validatePipelineParamAddDto((PipelineParamAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<PipelineParamCondition> search){
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new PipelineParamCondition());
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param pipelineParam 流水线参数
     * @param errors
     */
	public void validatePipelineParamAddDto(PipelineParamAddDto pipelineParam, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(pipelineParam.getName()) > 255){
			errors.rejectValue(PipelineParam.PROPERTY_NAME,null,"参数名称最长255个字符");
		}
		if(StringUtils.length(pipelineParam.getType()) > 255){
			errors.rejectValue(PipelineParam.PROPERTY_TYPE,null,"参数类型最长255个字符");
		}
		if(StringUtils.length(pipelineParam.getDefaultValue()) > 255){
			errors.rejectValue(PipelineParam.PROPERTY_DEFAULT_VALUE,null,"默认值最长255个字符");
		}
		if(StringUtils.length(pipelineParam.getDescription()) > 255){
			errors.rejectValue(PipelineParam.PROPERTY_DESCRIPTION,null,"参数描述最长255个字符");
		}
		if(StringUtils.length(pipelineParam.getEnumValue()) > 255){
			errors.rejectValue(PipelineParam.PROPERTY_ENUM_VALUE,null,"可选值最长255个字符");
		}
		if(StringUtils.length(pipelineParam.getValue()) > 255){
			errors.rejectValue(PipelineParam.PROPERTY_VALUE,null,"参数值最长255个字符");
		}
	}
}