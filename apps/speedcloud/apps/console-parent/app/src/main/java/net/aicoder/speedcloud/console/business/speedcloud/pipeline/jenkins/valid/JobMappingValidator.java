package net.aicoder.speedcloud.console.business.speedcloud.pipeline.jenkins.valid;


import net.aicoder.speedcloud.business.pipeline.jenkins.dto.JobMappingAddDto;
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
            this.validateAddDto((JobMappingAddDto)obj, errors);
        }
	}

	/**
     * 验证新增信息
     * @param jobMapping 任务映射
     * @param errors
     */
	public void validateAddDto(JobMappingAddDto jobMapping, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(jobMapping.getTaskType()) > 255){
			errors.rejectValue("taskType", null, "类型最长255个字符");
		}
		if(StringUtils.length(jobMapping.getJobInPlatform()) > 255){
			errors.rejectValue("jobInPlatform", null, "任务或流水线最长255个字符");
		}
		if(StringUtils.length(jobMapping.getJobInPlatformName()) > 255){
			errors.rejectValue("jobInPlatformName", null, "任务或流水线名称最长255个字符");
		}
		if(StringUtils.length(jobMapping.getJobInJenkinsName()) > 255){
			errors.rejectValue("jobInJenkinsName", null, "Jenkins中任务名称最长255个字符");
		}
	}
}