package net.aicoder.speedcloud.business.pipeline.task.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.pipeline.task.domain.PipelineTaskActionTypeProperty;
import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskActionTypePropertyAddDto;
import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskActionTypePropertyCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class PipelineTaskActionTypePropertyValidator implements Validator {

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
	    if(obj instanceof PipelineTaskActionTypePropertyAddDto){
            this.validatePipelineTaskActionTypePropertyAddDto((PipelineTaskActionTypePropertyAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<PipelineTaskActionTypePropertyCondition> search){
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new PipelineTaskActionTypePropertyCondition());
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param pipelineTaskActionTypeProperty 操作类型属性定义
     * @param errors
     */
	public void validatePipelineTaskActionTypePropertyAddDto(PipelineTaskActionTypePropertyAddDto pipelineTaskActionTypeProperty, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if (null == pipelineTaskActionTypeProperty.getViewOrder() ) {
			errors.rejectValue(PipelineTaskActionTypeProperty.PROPERTY_VIEW_ORDER, "EMPTY_"+PipelineTaskActionTypeProperty.PROPERTY_VIEW_ORDER, "展现顺序不能为空");
		}

		//验证长度
		if(StringUtils.length(pipelineTaskActionTypeProperty.getName()) > 255){
			errors.rejectValue(PipelineTaskActionTypeProperty.PROPERTY_NAME,null,"属性名称最长255个字符");
		}
		if(StringUtils.length(pipelineTaskActionTypeProperty.getCode()) > 255){
			errors.rejectValue(PipelineTaskActionTypeProperty.PROPERTY_CODE,null,"属性代码最长255个字符");
		}
		if(StringUtils.length(pipelineTaskActionTypeProperty.getOptionValue()) > 255){
			errors.rejectValue(PipelineTaskActionTypeProperty.PROPERTY_OPTION_VALUE,null,"可选值最长255个字符");
		}
	}
}