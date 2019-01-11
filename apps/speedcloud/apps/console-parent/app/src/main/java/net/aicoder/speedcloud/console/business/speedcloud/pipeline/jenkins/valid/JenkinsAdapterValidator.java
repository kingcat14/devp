package net.aicoder.speedcloud.console.business.speedcloud.pipeline.jenkins.valid;


import net.aicoder.speedcloud.business.pipeline.jenkins.dto.JenkinsAdapterAddDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class JenkinsAdapterValidator implements Validator {

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
	    if(obj instanceof JenkinsAdapterAddDto){
            this.validateAddDto((JenkinsAdapterAddDto)obj, errors);
        }
	}

	/**
     * 验证新增信息
     * @param jenkinsAdapter JenkinsAdapter
     * @param errors
     */
	public void validateAddDto(JenkinsAdapterAddDto jenkinsAdapter, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if(StringUtils.isEmpty(jenkinsAdapter.getProject())){
			errors.rejectValue("project", "EMPTY_PROJECT", "所属产品不能为空");
		}
		
		if(StringUtils.isEmpty(jenkinsAdapter.getEnv())){
			errors.rejectValue("env", "EMPTY_ENV", "所属环境不能为空");
		}
		
		if (null == jenkinsAdapter.getPort() ) {
			errors.rejectValue("port", "EMPTY_PORT", "端口不能为空");
		}
		if(StringUtils.isEmpty(jenkinsAdapter.getHost())){
			errors.rejectValue("host", "EMPTY_HOST", "IP不能为空");
		}
		

		//验证长度
		if(StringUtils.length(jenkinsAdapter.getProject()) > 255){
			errors.rejectValue("project", null, "所属产品最长255个字符");
		}
		if(StringUtils.length(jenkinsAdapter.getEnv()) > 255){
			errors.rejectValue("env", null, "所属环境最长255个字符");
		}
		if(StringUtils.length(jenkinsAdapter.getHost()) > 255){
			errors.rejectValue("host", null, "IP最长255个字符");
		}
	}
}