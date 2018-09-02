package net.aicoder.speedcloud.business.pipeline.task.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskActionAddDto;
import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskActionEditDto;
import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskActionCondition;
import net.aicoder.speedcloud.business.pipeline.task.domain.PipelineTaskAction;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class PipelineTaskActionValidator implements Validator {

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
	    if(obj instanceof PipelineTaskActionAddDto){
            this.validatePipelineTaskActionAddDto((PipelineTaskActionAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<PipelineTaskActionCondition> search){
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new PipelineTaskActionCondition());
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param pipelineTaskAction 操作
     * @param errors
     */
	public void validatePipelineTaskActionAddDto(PipelineTaskActionAddDto pipelineTaskAction, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if (null == pipelineTaskAction.getTask() ) {
			errors.rejectValue(PipelineTaskAction.PROPERTY_TASK, "EMPTY_"+PipelineTaskAction.PROPERTY_TASK, "所属任务不能为空");
		}

		//验证长度
		if(StringUtils.length(pipelineTaskAction.getName()) > 255){
			errors.rejectValue(PipelineTaskAction.PROPERTY_NAME,null,"操作名称最长255个字符");
		}
		if(StringUtils.length(pipelineTaskAction.getMemo()) > 255){
			errors.rejectValue(PipelineTaskAction.PROPERTY_MEMO,null,"操作说明最长255个字符");
		}
	}
}