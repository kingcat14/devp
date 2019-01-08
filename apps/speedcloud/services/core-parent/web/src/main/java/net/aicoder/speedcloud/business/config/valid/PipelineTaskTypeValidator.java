package net.aicoder.speedcloud.business.config.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.config.domain.PipelineTaskType;
import net.aicoder.speedcloud.business.config.dto.PipelineTaskTypeAddDto;
import net.aicoder.speedcloud.business.config.dto.PipelineTaskTypeCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class PipelineTaskTypeValidator implements Validator {

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
	    if(obj instanceof PipelineTaskTypeAddDto){
            this.validatePipelineTaskTypeAddDto((PipelineTaskTypeAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj, errors);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<PipelineTaskTypeCondition> search, Errors errors) {
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new PipelineTaskTypeCondition());
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param pipelineTaskType 任务类型
     * @param errors
     */
	public void validatePipelineTaskTypeAddDto(PipelineTaskTypeAddDto pipelineTaskType, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(pipelineTaskType.getName()) > 255){
			errors.rejectValue(PipelineTaskType.PROPERTY_NAME,null,"名称最长255个字符");
		}
		if(StringUtils.length(pipelineTaskType.getCode()) > 255){
			errors.rejectValue(PipelineTaskType.PROPERTY_CODE,null,"代码最长255个字符");
		}
	}
}