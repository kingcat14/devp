package net.aicoder.speedcloud.console.business.speedCloud.app.valid;


import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.app.dto.CodeBaseInfoAddDto;
import net.aicoder.speedcloud.business.app.dto.CodeBaseInfoEditDto;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

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
     * @param codeBaseInfo 代码库详细信息
     * @param errors
     */
	public void validateAddDto(CodeBaseInfoAddDto codeBaseInfo, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if (null == codeBaseInfo.getTid() ) {
			errors.rejectValue("tid", "EMPTY_TID", "租户id不能为空");
		}

		//验证长度
		if(StringUtils.length(codeBaseInfo.getLanguage()) > 255){
			errors.rejectValue("language", null, "开发语言最长255个字符");
		}
		if(StringUtils.length(codeBaseInfo.getLanguageLevel()) > 255){
			errors.rejectValue("languageLevel", null, "语言级别最长255个字符");
		}
	}
}