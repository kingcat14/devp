package net.aicoder.speedcloud.business.pipeline.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineStageAddDto;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineStageEditDto;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineStageCondition;
import net.aicoder.speedcloud.business.pipeline.domain.PipelineStage;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
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
            this.validatePipelineStageAddDto((PipelineStageAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<PipelineStageCondition> search){
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new PipelineStageCondition());
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param pipelineStage 阶段
     * @param errors
     */
	public void validatePipelineStageAddDto(PipelineStageAddDto pipelineStage, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(pipelineStage.getName()) > 255){
			errors.rejectValue(PipelineStage.PROPERTY_NAME,null,"阶段名称最长255个字符");
		}
	}
}