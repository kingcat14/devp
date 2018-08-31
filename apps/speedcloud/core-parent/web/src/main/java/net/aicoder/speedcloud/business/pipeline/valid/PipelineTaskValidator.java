package net.aicoder.speedcloud.business.pipeline.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineTaskAddDto;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineTaskEditDto;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineTaskCondition;
import net.aicoder.speedcloud.business.pipeline.domain.PipelineTask;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class PipelineTaskValidator implements Validator {

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
	    if(obj instanceof PipelineTaskAddDto){
            this.validatePipelineTaskAddDto((PipelineTaskAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<PipelineTaskCondition> search){
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new PipelineTaskCondition());
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param pipelineTask 任务
     * @param errors
     */
	public void validatePipelineTaskAddDto(PipelineTaskAddDto pipelineTask, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if (null == pipelineTask.getStage() ) {
			errors.rejectValue(PipelineTask.PROPERTY_STAGE, "EMPTY_"+PipelineTask.PROPERTY_STAGE, "所属阶段不能为空");
		}
		if (null == pipelineTask.getTaskType() ) {
			errors.rejectValue(PipelineTask.PROPERTY_TASK_TYPE, "EMPTY_"+PipelineTask.PROPERTY_TASK_TYPE, "任务类型不能为空");
		}

		//验证长度
	}
}