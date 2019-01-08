package net.aicoder.speedcloud.business.pipeline.template.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.pipeline.template.domain.PipelineTemplateTask;
import net.aicoder.speedcloud.business.pipeline.template.dto.PipelineTemplateTaskAddDto;
import net.aicoder.speedcloud.business.pipeline.template.dto.PipelineTemplateTaskCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class PipelineTemplateTaskValidator implements Validator {

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
	    if(obj instanceof PipelineTemplateTaskAddDto){
            this.validatePipelineTemplateTaskAddDto((PipelineTemplateTaskAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj, errors);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<PipelineTemplateTaskCondition> search, Errors errors) {
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new PipelineTemplateTaskCondition());
        }
        if(search.getSearchCondition().getTid() == null){
        	errors.rejectValue("NOT_TENANT_ID", "未正确设置租户ID");
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param pipelineTemplateTask 任务模板
     * @param errors
     */
	public void validatePipelineTemplateTaskAddDto(PipelineTemplateTaskAddDto pipelineTemplateTask, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(pipelineTemplateTask.getName()) > 255){
			errors.rejectValue(PipelineTemplateTask.PROPERTY_NAME,null,"任务名称最长255个字符");
		}
		if(StringUtils.length(pipelineTemplateTask.getTaskStartTime()) > 255){
			errors.rejectValue(PipelineTemplateTask.PROPERTY_TASK_START_TIME,null,"执行开始时间最长255个字符");
		}
		if(StringUtils.length(pipelineTemplateTask.getTaskDayOfWeeks()) > 255){
			errors.rejectValue(PipelineTemplateTask.PROPERTY_TASK_DAY_OF_WEEKS,null,"执行日最长255个字符");
		}
	}
}