package net.aicoder.speedcloud.business.pipeline.template.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.pipeline.template.domain.PipelineTemplateTaskAction;
import net.aicoder.speedcloud.business.pipeline.template.dto.PipelineTemplateTaskActionAddDto;
import net.aicoder.speedcloud.business.pipeline.template.dto.PipelineTemplateTaskActionCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class PipelineTemplateTaskActionValidator implements Validator {

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
	    if(obj instanceof PipelineTemplateTaskActionAddDto){
            this.validatePipelineTemplateTaskActionAddDto((PipelineTemplateTaskActionAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj, errors);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<PipelineTemplateTaskActionCondition> search, Errors errors) {
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new PipelineTemplateTaskActionCondition());
        }
        if(search.getSearchCondition().getTid() == null){
        	errors.rejectValue("NOT_TENANT_ID", "未正确设置租户ID");
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param pipelineTemplateTaskAction 操作模板
     * @param errors
     */
	public void validatePipelineTemplateTaskActionAddDto(PipelineTemplateTaskActionAddDto pipelineTemplateTaskAction, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(pipelineTemplateTaskAction.getTask()) > 255){
			errors.rejectValue(PipelineTemplateTaskAction.PROPERTY_TASK,null,"所属任务最长255个字符");
		}
		if(StringUtils.length(pipelineTemplateTaskAction.getMemo()) > 255){
			errors.rejectValue(PipelineTemplateTaskAction.PROPERTY_MEMO,null,"操作说明最长255个字符");
		}
		if(StringUtils.length(pipelineTemplateTaskAction.getName()) > 255){
			errors.rejectValue(PipelineTemplateTaskAction.PROPERTY_NAME,null,"操作名称最长255个字符");
		}
	}
}