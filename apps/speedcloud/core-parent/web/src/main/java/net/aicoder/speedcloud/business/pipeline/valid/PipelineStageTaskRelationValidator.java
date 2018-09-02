package net.aicoder.speedcloud.business.pipeline.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineStageTaskRelationAddDto;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineStageTaskRelationEditDto;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineStageTaskRelationCondition;
import net.aicoder.speedcloud.business.pipeline.domain.PipelineStageTaskRelation;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class PipelineStageTaskRelationValidator implements Validator {

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
	    if(obj instanceof PipelineStageTaskRelationAddDto){
            this.validatePipelineStageTaskRelationAddDto((PipelineStageTaskRelationAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<PipelineStageTaskRelationCondition> search){
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new PipelineStageTaskRelationCondition());
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param pipelineStageTaskRelation 阶段任务关联
     * @param errors
     */
	public void validatePipelineStageTaskRelationAddDto(PipelineStageTaskRelationAddDto pipelineStageTaskRelation, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
	}
}