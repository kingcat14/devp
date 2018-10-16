package net.aicoder.speedcloud.console.business.speedcloud.deployscheme.valid;


import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.deployscheme.dto.SchemeAddDto;
import net.aicoder.speedcloud.business.deployscheme.dto.SchemeEditDto;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class SchemeValidator implements Validator {

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
	    if(obj instanceof SchemeAddDto){
            this.validateAddDto((SchemeAddDto)obj, errors);
        }
	}

	/**
     * 验证新增信息
     * @param scheme 部署方案
     * @param errors
     */
		public void validateAddDto(SchemeAddDto scheme, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(scheme.getName()) > 255){
			errors.rejectValue("name", null, "方案名称最长255个字符");
		}
		if(StringUtils.length(scheme.getCode()) > 255){
			errors.rejectValue("code", null, "方案代码最长255个字符");
		}
		if(StringUtils.length(scheme.getAlias()) > 255){
			errors.rejectValue("alias", null, "方案别名最长255个字符");
		}
		if(StringUtils.length(scheme.getType()) > 255){
			errors.rejectValue("type", null, "方案类型最长255个字符");
		}
		if(StringUtils.length(scheme.getVersion()) > 255){
			errors.rejectValue("version", null, "版本最长255个字符");
		}
		if(StringUtils.length(scheme.getVerPostfix()) > 255){
			errors.rejectValue("verPostfix", null, "版本标识后缀最长255个字符");
		}
		if(StringUtils.length(scheme.getNotes()) > 255){
			errors.rejectValue("notes", null, "备注最长255个字符");
		}
	}
}