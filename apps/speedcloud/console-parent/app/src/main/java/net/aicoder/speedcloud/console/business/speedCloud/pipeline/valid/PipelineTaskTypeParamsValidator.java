package net.aicoder.speedcloud.console.business.speedCloud.pipeline.valid;


import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineTaskTypeParamsAddDto;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineTaskTypeParamsEditDto;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class PipelineTaskTypeParamsValidator implements Validator {

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
	    if(obj instanceof PipelineTaskTypeParamsAddDto){
            this.validateAddDto((PipelineTaskTypeParamsAddDto)obj, errors);
        }
	}

	/**
     * 验证新增信息
     * @param pipelineTaskTypeParams 任务类型参数定义
     * @param errors
     */
		public void validateAddDto(PipelineTaskTypeParamsAddDto pipelineTaskTypeParams, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if (null == pipelineTaskTypeParams.getViewOrder() ) {
			errors.rejectValue("viewOrder", "EMPTY_VIEW_ORDER", "展现顺序不能为空");
		}

		//验证长度
		if(StringUtils.length(pipelineTaskTypeParams.getName()) > 255){
			errors.rejectValue("name", null, "参数名称最长255个字符");
		}
		if(StringUtils.length(pipelineTaskTypeParams.getCode()) > 255){
			errors.rejectValue("code", null, "参数代码最长255个字符");
		}
	}
}