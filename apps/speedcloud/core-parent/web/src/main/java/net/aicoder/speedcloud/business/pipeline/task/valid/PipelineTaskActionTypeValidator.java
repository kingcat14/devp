package net.aicoder.speedcloud.business.pipeline.task.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskActionTypeAddDto;
import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskActionTypeEditDto;
import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskActionTypeCondition;
import net.aicoder.speedcloud.business.pipeline.task.domain.PipelineTaskActionType;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class PipelineTaskActionTypeValidator implements Validator {

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
	    if(obj instanceof PipelineTaskActionTypeAddDto){
            this.validatePipelineTaskActionTypeAddDto((PipelineTaskActionTypeAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<PipelineTaskActionTypeCondition> search){
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new PipelineTaskActionTypeCondition());
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param pipelineTaskActionType 操作类型
     * @param errors
     */
	public void validatePipelineTaskActionTypeAddDto(PipelineTaskActionTypeAddDto pipelineTaskActionType, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(pipelineTaskActionType.getCode()) > 255){
			errors.rejectValue(PipelineTaskActionType.PROPERTY_CODE,null,"类型代码最长255个字符");
		}
		if(StringUtils.length(pipelineTaskActionType.getName()) > 255){
			errors.rejectValue(PipelineTaskActionType.PROPERTY_NAME,null,"类型名称最长255个字符");
		}
		if(StringUtils.length(pipelineTaskActionType.getViewOrder()) > 255){
			errors.rejectValue(PipelineTaskActionType.PROPERTY_VIEW_ORDER,null,"展现顺序最长255个字符");
		}
		if(StringUtils.length(pipelineTaskActionType.getMemo()) > 255){
			errors.rejectValue(PipelineTaskActionType.PROPERTY_MEMO,null,"说明最长255个字符");
		}
	}
}