package net.aicoder.speedcloud.console.business.speedcloud.deploy.valid;


import net.aicoder.speedcloud.business.deploy.dto.DevpSysDpyResourceRefAddDto;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class DevpSysDpyResourceRefValidator implements Validator {

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
	    if(obj instanceof DevpSysDpyResourceRefAddDto){
            this.validateAddDto((DevpSysDpyResourceRefAddDto)obj, errors);
        }
	}

	/**
     * 验证新增信息
     * @param devpSysDpyResourceRef 方案资源间关系
     * @param errors
     */
		public void validateAddDto(DevpSysDpyResourceRefAddDto devpSysDpyResourceRef, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(devpSysDpyResourceRef.getCode()) > 255){
			errors.rejectValue("code", null, "对应关系代码最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResourceRef.getName()) > 255){
			errors.rejectValue("name", null, "对应关系名称最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResourceRef.getAlias()) > 255){
			errors.rejectValue("alias", null, "对应关系别名最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResourceRef.getDescription()) > 255){
			errors.rejectValue("description", null, "对应关系描述最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResourceRef.getDirection()) > 255){
			errors.rejectValue("direction", null, "对应关系方向最长255个字符");
		}
	}
}