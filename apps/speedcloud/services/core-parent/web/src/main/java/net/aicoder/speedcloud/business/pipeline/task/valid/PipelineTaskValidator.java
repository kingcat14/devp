package net.aicoder.speedcloud.business.pipeline.task.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.pipeline.task.domain.PipelineTask;
import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskAddDto;
import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

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
		if(StringUtils.isEmpty(pipelineTask.getName())){
			errors.rejectValue(PipelineTask.PROPERTY_NAME, "EMPTY_"+PipelineTask.PROPERTY_NAME, "任务名称不能为空");
		}

		//验证长度
		if(StringUtils.length(pipelineTask.getName()) > 255){
			errors.rejectValue(PipelineTask.PROPERTY_NAME,null,"任务名称最长255个字符");
		}
		if(StringUtils.length(pipelineTask.getTaskStartTime()) > 255){
			errors.rejectValue(PipelineTask.PROPERTY_TASK_START_TIME,null,"执行开始时间最长255个字符");
		}
		if(StringUtils.length(pipelineTask.getTaskDayOfWeeks()) > 255){
			errors.rejectValue(PipelineTask.PROPERTY_TASK_DAY_OF_WEEKS,null,"执行日最长255个字符");
		}
	}
}