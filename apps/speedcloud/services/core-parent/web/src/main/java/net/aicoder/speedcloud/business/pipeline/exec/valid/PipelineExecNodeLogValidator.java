package net.aicoder.speedcloud.business.pipeline.exec.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.pipeline.exec.domain.PipelineExecNodeLog;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecNodeLogAddDto;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecNodeLogCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class PipelineExecNodeLogValidator implements Validator {

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
	    if(obj instanceof PipelineExecNodeLogAddDto){
            this.validatePipelineExecNodeLogAddDto((PipelineExecNodeLogAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<PipelineExecNodeLogCondition> search){
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new PipelineExecNodeLogCondition());
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param pipelineExecNodeLog 运行节点日志
     * @param errors
     */
	public void validatePipelineExecNodeLogAddDto(PipelineExecNodeLogAddDto pipelineExecNodeLog, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(pipelineExecNodeLog.getLog()) > 255){
			errors.rejectValue(PipelineExecNodeLog.PROPERTY_LOG,null,"log最长255个字符");
		}
	}
}