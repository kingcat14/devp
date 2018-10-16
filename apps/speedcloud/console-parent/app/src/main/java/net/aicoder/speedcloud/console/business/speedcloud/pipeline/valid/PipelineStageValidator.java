package net.aicoder.speedcloud.console.business.speedcloud.pipeline.valid;


import net.aicoder.speedcloud.business.pipeline.dto.PipelineStageAddDto;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class PipelineStageValidator implements Validator {

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
	    if(obj instanceof PipelineStageAddDto){
            this.validateAddDto((PipelineStageAddDto)obj, errors);
        }
	}

	/**
     * 验证新增信息
     * @param pipelineStage 阶段
     * @param errors
     */
		public void validateAddDto(PipelineStageAddDto pipelineStage, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(pipelineStage.getName()) > 255){
			errors.rejectValue("name", null, "阶段名称最长255个字符");
		}
	}
}