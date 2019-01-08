package net.aicoder.speedcloud.business.pipeline.template.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.pipeline.template.domain.PipelineTemplateTaskParam;
import net.aicoder.speedcloud.business.pipeline.template.dto.PipelineTemplateTaskParamAddDto;
import net.aicoder.speedcloud.business.pipeline.template.dto.PipelineTemplateTaskParamCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class PipelineTemplateTaskParamValidator implements Validator {

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
	    if(obj instanceof PipelineTemplateTaskParamAddDto){
            this.validatePipelineTemplateTaskParamAddDto((PipelineTemplateTaskParamAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj, errors);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<PipelineTemplateTaskParamCondition> search, Errors errors) {
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new PipelineTemplateTaskParamCondition());
        }
        if(search.getSearchCondition().getTid() == null){
        	errors.rejectValue("NOT_TENANT_ID", "未正确设置租户ID");
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param pipelineTemplateTaskParam 任务模板参数
     * @param errors
     */
	public void validatePipelineTemplateTaskParamAddDto(PipelineTemplateTaskParamAddDto pipelineTemplateTaskParam, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(pipelineTemplateTaskParam.getTask()) > 255){
			errors.rejectValue(PipelineTemplateTaskParam.PROPERTY_TASK,null,"所属任务最长255个字符");
		}
		if(StringUtils.length(pipelineTemplateTaskParam.getName()) > 255){
			errors.rejectValue(PipelineTemplateTaskParam.PROPERTY_NAME,null,"参数名称最长255个字符");
		}
		if(StringUtils.length(pipelineTemplateTaskParam.getType()) > 255){
			errors.rejectValue(PipelineTemplateTaskParam.PROPERTY_TYPE,null,"参数类型最长255个字符");
		}
		if(StringUtils.length(pipelineTemplateTaskParam.getDefaultValue()) > 255){
			errors.rejectValue(PipelineTemplateTaskParam.PROPERTY_DEFAULT_VALUE,null,"默认值最长255个字符");
		}
		if(StringUtils.length(pipelineTemplateTaskParam.getDescription()) > 255){
			errors.rejectValue(PipelineTemplateTaskParam.PROPERTY_DESCRIPTION,null,"参数描述最长255个字符");
		}
		if(StringUtils.length(pipelineTemplateTaskParam.getEnumValue()) > 255){
			errors.rejectValue(PipelineTemplateTaskParam.PROPERTY_ENUM_VALUE,null,"可选值最长255个字符");
		}
		if(StringUtils.length(pipelineTemplateTaskParam.getValue()) > 255){
			errors.rejectValue(PipelineTemplateTaskParam.PROPERTY_VALUE,null,"参数值最长255个字符");
		}
	}
}