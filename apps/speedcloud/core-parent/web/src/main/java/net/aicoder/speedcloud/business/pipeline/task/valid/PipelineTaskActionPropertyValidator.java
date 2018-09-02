package net.aicoder.speedcloud.business.pipeline.task.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskActionPropertyAddDto;
import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskActionPropertyEditDto;
import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskActionPropertyCondition;
import net.aicoder.speedcloud.business.pipeline.task.domain.PipelineTaskActionProperty;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class PipelineTaskActionPropertyValidator implements Validator {

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
	    if(obj instanceof PipelineTaskActionPropertyAddDto){
            this.validatePipelineTaskActionPropertyAddDto((PipelineTaskActionPropertyAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<PipelineTaskActionPropertyCondition> search){
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new PipelineTaskActionPropertyCondition());
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param pipelineTaskActionProperty 操作属性
     * @param errors
     */
	public void validatePipelineTaskActionPropertyAddDto(PipelineTaskActionPropertyAddDto pipelineTaskActionProperty, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(pipelineTaskActionProperty.getName()) > 255){
			errors.rejectValue(PipelineTaskActionProperty.PROPERTY_NAME,null,"属性名称最长255个字符");
		}
		if(StringUtils.length(pipelineTaskActionProperty.getCode()) > 255){
			errors.rejectValue(PipelineTaskActionProperty.PROPERTY_CODE,null,"属性代码最长255个字符");
		}
		if(StringUtils.length(pipelineTaskActionProperty.getType()) > 255){
			errors.rejectValue(PipelineTaskActionProperty.PROPERTY_TYPE,null,"类型最长255个字符");
		}
	}
}