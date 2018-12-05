package net.aicoder.speedcloud.console.business.speedcloud.app.valid;


import net.aicoder.speedcloud.business.app.dto.CodeBaseInfoAddDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class CodeBaseInfoValidator implements Validator {

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
	    if(obj instanceof CodeBaseInfoAddDto){
            this.validateAddDto((CodeBaseInfoAddDto)obj, errors);
        }
	}

	/**
     * 验证新增信息
     * @param codeBaseInfo 代码基本信息
     * @param errors
     */
	public void validateAddDto(CodeBaseInfoAddDto codeBaseInfo, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(codeBaseInfo.getCodeRepository()) > 255){
			errors.rejectValue("codeRepository", null, "代码库最长255个字符");
		}
		if(StringUtils.length(codeBaseInfo.getLanguage()) > 255){
			errors.rejectValue("language", null, "开发语言最长255个字符");
		}
		if(StringUtils.length(codeBaseInfo.getLanguageLevel()) > 255){
			errors.rejectValue("languageLevel", null, "语言级别最长255个字符");
		}
	}
}