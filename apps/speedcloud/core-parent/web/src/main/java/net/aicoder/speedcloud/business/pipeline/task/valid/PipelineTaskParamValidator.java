package net.aicoder.speedcloud.business.pipeline.task.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskParamAddDto;
import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskParamEditDto;
import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskParamCondition;
import net.aicoder.speedcloud.business.pipeline.task.domain.PipelineTaskParam;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class PipelineTaskParamValidator implements Validator {

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
	    if(obj instanceof PipelineTaskParamAddDto){
            this.validatePipelineTaskParamAddDto((PipelineTaskParamAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<PipelineTaskParamCondition> search){
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new PipelineTaskParamCondition());
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param pipelineTaskParam 任务参数
     * @param errors
     */
	public void validatePipelineTaskParamAddDto(PipelineTaskParamAddDto pipelineTaskParam, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(pipelineTaskParam.getName()) > 255){
			errors.rejectValue(PipelineTaskParam.PROPERTY_NAME,null,"参数名称最长255个字符");
		}
		if(StringUtils.length(pipelineTaskParam.getType()) > 255){
			errors.rejectValue(PipelineTaskParam.PROPERTY_TYPE,null,"参数类型最长255个字符");
		}
		if(StringUtils.length(pipelineTaskParam.getDefaultValue()) > 255){
			errors.rejectValue(PipelineTaskParam.PROPERTY_DEFAULT_VALUE,null,"默认值最长255个字符");
		}
		if(StringUtils.length(pipelineTaskParam.getDescription()) > 255){
			errors.rejectValue(PipelineTaskParam.PROPERTY_DESCRIPTION,null,"参数描述最长255个字符");
		}
		if(StringUtils.length(pipelineTaskParam.getEnumValue()) > 255){
			errors.rejectValue(PipelineTaskParam.PROPERTY_ENUM_VALUE,null,"可选值最长255个字符");
		}
		if(StringUtils.length(pipelineTaskParam.getValue()) > 255){
			errors.rejectValue(PipelineTaskParam.PROPERTY_VALUE,null,"参数值最长255个字符");
		}
	}
}