package net.aicoder.speedcloud.console.business.speedcloud.deploy.valid;


import net.aicoder.speedcloud.business.deploy.dto.DevpSysDpyResourcesAddDto;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class DevpSysDpyResourcesValidator implements Validator {

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
	    if(obj instanceof DevpSysDpyResourcesAddDto){
            this.validateAddDto((DevpSysDpyResourcesAddDto)obj, errors);
        }
	}

	/**
     * 验证新增信息
     * @param devpSysDpyResources 方案资源
     * @param errors
     */
		public void validateAddDto(DevpSysDpyResourcesAddDto devpSysDpyResources, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if (null == devpSysDpyResources.getScheme() ) {
			errors.rejectValue("scheme", "EMPTY_SCHEME", "所属方案不能为空");
		}

		//验证长度
		if(StringUtils.length(devpSysDpyResources.getName()) > 255){
			errors.rejectValue("name", null, "资源名称最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResources.getCode()) > 255){
			errors.rejectValue("code", null, "资源代码最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResources.getAlias()) > 255){
			errors.rejectValue("alias", null, "资源别名最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResources.getNotes()) > 255){
			errors.rejectValue("notes", null, "备注最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResources.getVersion()) > 255){
			errors.rejectValue("version", null, "版本最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResources.getStatus()) > 255){
			errors.rejectValue("status", null, "状态最长255个字符");
		}
	}
}