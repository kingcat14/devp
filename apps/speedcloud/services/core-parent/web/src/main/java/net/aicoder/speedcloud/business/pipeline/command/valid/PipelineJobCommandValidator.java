package net.aicoder.speedcloud.business.pipeline.command.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.pipeline.command.domain.PipelineJobCommand;
import net.aicoder.speedcloud.business.pipeline.command.dto.PipelineJobCommandAddDto;
import net.aicoder.speedcloud.business.pipeline.command.dto.PipelineJobCommandCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class PipelineJobCommandValidator implements Validator {

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
	    if(obj instanceof PipelineJobCommandAddDto){
            this.validatePipelineJobCommandAddDto((PipelineJobCommandAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<PipelineJobCommandCondition> search){
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new PipelineJobCommandCondition());
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param pipelineJobCommand 创建Job指令
     * @param errors
     */
	public void validatePipelineJobCommandAddDto(PipelineJobCommandAddDto pipelineJobCommand, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(pipelineJobCommand.getStatus()) > 255){
			errors.rejectValue(PipelineJobCommand.PROPERTY_STATUS,null,"状态最长255个字符");
		}
		if(StringUtils.length(pipelineJobCommand.getType()) > 255){
			errors.rejectValue(PipelineJobCommand.PROPERTY_TYPE,null,"之类类型最长255个字符");
		}
		if(StringUtils.length(pipelineJobCommand.getResult()) > 255){
			errors.rejectValue(PipelineJobCommand.PROPERTY_RESULT,null,"执行结果最长255个字符");
		}
	}
}