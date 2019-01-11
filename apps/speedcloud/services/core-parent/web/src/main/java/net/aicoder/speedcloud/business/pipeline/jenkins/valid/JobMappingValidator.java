package net.aicoder.speedcloud.business.pipeline.jenkins.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.pipeline.jenkins.domain.JobMapping;
import net.aicoder.speedcloud.business.pipeline.jenkins.dto.JobMappingAddDto;
import net.aicoder.speedcloud.business.pipeline.jenkins.dto.JobMappingCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class JobMappingValidator implements Validator {

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
	    if(obj instanceof JobMappingAddDto){
            this.validateJobMappingAddDto((JobMappingAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj, errors);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<JobMappingCondition> search, Errors errors) {
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new JobMappingCondition());
        }
        if(search.getSearchCondition().getTid() == null){
        	errors.rejectValue("NOT_TENANT_ID", "未正确设置租户ID");
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param jobMapping 任务映射
     * @param errors
     */
	public void validateJobMappingAddDto(JobMappingAddDto jobMapping, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(jobMapping.getTaskType()) > 255){
			errors.rejectValue(JobMapping.PROPERTY_TASK_TYPE,null,"类型最长255个字符");
		}
		if(StringUtils.length(jobMapping.getJobInPlatform()) > 255){
			errors.rejectValue(JobMapping.PROPERTY_JOB_IN_PLATFORM,null,"任务或流水线最长255个字符");
		}
		if(StringUtils.length(jobMapping.getJobInPlatformName()) > 255){
			errors.rejectValue(JobMapping.PROPERTY_JOB_IN_PLATFORM_NAME,null,"任务或流水线名称最长255个字符");
		}
		if(StringUtils.length(jobMapping.getJobInJenkinsName()) > 255){
			errors.rejectValue(JobMapping.PROPERTY_JOB_IN_JENKINS_NAME,null,"Jenkins中任务名称最长255个字符");
		}
	}
}