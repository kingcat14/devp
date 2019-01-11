package net.aicoder.speedcloud.business.pipeline.jenkins.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.pipeline.jenkins.domain.JenkinsAdapter;
import net.aicoder.speedcloud.business.pipeline.jenkins.dto.JenkinsAdapterAddDto;
import net.aicoder.speedcloud.business.pipeline.jenkins.dto.JenkinsAdapterCondition;
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
            this.validateJenkinsAdapterAddDto((JenkinsAdapterAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj, errors);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<JenkinsAdapterCondition> search, Errors errors) {
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new JenkinsAdapterCondition());
        }
        if(search.getSearchCondition().getTid() == null){
        	errors.rejectValue("NOT_TENANT_ID", "未正确设置租户ID");
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param jenkinsAdapter JenkinsAdapter
     * @param errors
     */
	public void validateJenkinsAdapterAddDto(JenkinsAdapterAddDto jenkinsAdapter, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if(StringUtils.isEmpty(jenkinsAdapter.getProject())){
			errors.rejectValue(JenkinsAdapter.PROPERTY_PROJECT, "EMPTY_"+JenkinsAdapter.PROPERTY_PROJECT, "所属产品不能为空");
		}
		if(StringUtils.isEmpty(jenkinsAdapter.getEnv())){
			errors.rejectValue(JenkinsAdapter.PROPERTY_ENV, "EMPTY_"+JenkinsAdapter.PROPERTY_ENV, "所属环境不能为空");
		}
		if (null == jenkinsAdapter.getPort() ) {
			errors.rejectValue(JenkinsAdapter.PROPERTY_PORT, "EMPTY_"+JenkinsAdapter.PROPERTY_PORT, "端口不能为空");
		}
		if(StringUtils.isEmpty(jenkinsAdapter.getHost())){
			errors.rejectValue(JenkinsAdapter.PROPERTY_HOST, "EMPTY_"+JenkinsAdapter.PROPERTY_HOST, "IP不能为空");
		}

		//验证长度
		if(StringUtils.length(jenkinsAdapter.getProject()) > 255){
			errors.rejectValue(JenkinsAdapter.PROPERTY_PROJECT,null,"所属产品最长255个字符");
		}
		if(StringUtils.length(jenkinsAdapter.getEnv()) > 255){
			errors.rejectValue(JenkinsAdapter.PROPERTY_ENV,null,"所属环境最长255个字符");
		}
		if(StringUtils.length(jenkinsAdapter.getHost()) > 255){
			errors.rejectValue(JenkinsAdapter.PROPERTY_HOST,null,"IP最长255个字符");
		}
	}
}