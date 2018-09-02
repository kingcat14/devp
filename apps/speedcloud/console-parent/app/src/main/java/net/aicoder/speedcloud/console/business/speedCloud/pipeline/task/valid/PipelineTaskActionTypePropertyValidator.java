package net.aicoder.speedcloud.console.business.speedCloud.pipeline.task.valid;


import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskActionTypePropertyAddDto;
import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskActionTypePropertyEditDto;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

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
            this.validateAddDto((PipelineTaskActionTypePropertyAddDto)obj, errors);
        }
	}

	/**
     * 验证新增信息
     * @param pipelineTaskActionTypeProperty 操作类型属性定义
     * @param errors
     */
		public void validateAddDto(PipelineTaskActionTypePropertyAddDto pipelineTaskActionTypeProperty, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if (null == pipelineTaskActionTypeProperty.getViewOrder() ) {
			errors.rejectValue("viewOrder", "EMPTY_VIEW_ORDER", "展现顺序不能为空");
		}

		//验证长度
		if(StringUtils.length(pipelineTaskActionTypeProperty.getName()) > 255){
			errors.rejectValue("name", null, "属性名称最长255个字符");
		}
		if(StringUtils.length(pipelineTaskActionTypeProperty.getCode()) > 255){
			errors.rejectValue("code", null, "属性代码最长255个字符");
		}
		if(StringUtils.length(pipelineTaskActionTypeProperty.getOptionValue()) > 255){
			errors.rejectValue("optionValue", null, "可选值最长255个字符");
		}
	}
}