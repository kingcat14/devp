package net.aicoder.speedcloud.console.business.speedCloud.app.valid;


import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.app.dto.CodeRepertoryAddDto;
import net.aicoder.speedcloud.business.app.dto.CodeRepertoryEditDto;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class CodeRepertoryValidator implements Validator {

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
	    if(obj instanceof CodeRepertoryAddDto){
            this.validateAddDto((CodeRepertoryAddDto)obj, errors);
        }
	}

	/**
     * 验证新增信息
     * @param codeRepertory 代码库
     * @param errors
     */
	public void validateAddDto(CodeRepertoryAddDto codeRepertory, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if (null == codeRepertory.getTid() ) {
			errors.rejectValue("tid", "EMPTY_TID", "租户id不能为空");
		}

		//验证长度
		if(StringUtils.length(codeRepertory.getUrl()) > 255){
			errors.rejectValue("url", null, "url最长255个字符");
		}
		if(StringUtils.length(codeRepertory.getUsername()) > 255){
			errors.rejectValue("username", null, "用户名最长255个字符");
		}
		if(StringUtils.length(codeRepertory.getPassword()) > 255){
			errors.rejectValue("password", null, "密码最长255个字符");
		}
	}
}