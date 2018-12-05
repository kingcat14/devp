package net.aicoder.speedcloud.console.business.speedcloud.app.valid;


import net.aicoder.speedcloud.business.app.dto.CodeRepositoryAddDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class CodeRepositoryValidator implements Validator {

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
	    if(obj instanceof CodeRepositoryAddDto){
            this.validateAddDto((CodeRepositoryAddDto)obj, errors);
        }
	}

	/**
     * 验证新增信息
     * @param codeRepository 代码库
     * @param errors
     */
	public void validateAddDto(CodeRepositoryAddDto codeRepository, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(codeRepository.getName()) > 255){
			errors.rejectValue("name", null, "名称最长255个字符");
		}
		if(StringUtils.length(codeRepository.getType()) > 255){
			errors.rejectValue("type", null, "代码库类型最长255个字符");
		}
		if(StringUtils.length(codeRepository.getUrl()) > 255){
			errors.rejectValue("url", null, "url最长255个字符");
		}
		if(StringUtils.length(codeRepository.getUsername()) > 255){
			errors.rejectValue("username", null, "用户名最长255个字符");
		}
		if(StringUtils.length(codeRepository.getPassword()) > 255){
			errors.rejectValue("password", null, "密码最长255个字符");
		}
		if(StringUtils.length(codeRepository.getApp()) > 255){
			errors.rejectValue("app", null, "应用最长255个字符");
		}
	}
}