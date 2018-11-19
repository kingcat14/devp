package net.aicoder.speedcloud.business.pipeline.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.pipeline.domain.PipelineStageNodeParam;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineStageNodeParamAddDto;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineStageNodeParamCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class PipelineStageNodeParamValidator implements Validator {

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
	    if(obj instanceof PipelineStageNodeParamAddDto){
            this.validatePipelineStageNodeParamAddDto((PipelineStageNodeParamAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<PipelineStageNodeParamCondition> search){
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new PipelineStageNodeParamCondition());
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param pipelineStageNodeParam 阶段执行节点参数
     * @param errors
     */
	public void validatePipelineStageNodeParamAddDto(PipelineStageNodeParamAddDto pipelineStageNodeParam, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(pipelineStageNodeParam.getName()) > 255){
			errors.rejectValue(PipelineStageNodeParam.PROPERTY_NAME,null,"参数名称最长255个字符");
		}
		if(StringUtils.length(pipelineStageNodeParam.getValue()) > 255){
			errors.rejectValue(PipelineStageNodeParam.PROPERTY_VALUE,null,"参数值最长255个字符");
		}
	}
}