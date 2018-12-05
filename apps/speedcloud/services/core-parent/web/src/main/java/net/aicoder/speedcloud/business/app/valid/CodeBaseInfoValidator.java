package net.aicoder.speedcloud.business.app.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.app.domain.CodeBaseInfo;
import net.aicoder.speedcloud.business.app.dto.CodeBaseInfoAddDto;
import net.aicoder.speedcloud.business.app.dto.CodeBaseInfoCondition;
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
            this.validateCodeBaseInfoAddDto((CodeBaseInfoAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj, errors);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<CodeBaseInfoCondition> search, Errors errors) {
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new CodeBaseInfoCondition());
        }
        if(search.getSearchCondition().getTid() == null){
        	errors.rejectValue("NOT_TENANT_ID", "未正确设置租户ID");
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param codeBaseInfo 代码基本信息
     * @param errors
     */
	public void validateCodeBaseInfoAddDto(CodeBaseInfoAddDto codeBaseInfo, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(codeBaseInfo.getCodeRepository()) > 255){
			errors.rejectValue(CodeBaseInfo.PROPERTY_CODE_REPOSITORY,null,"代码库最长255个字符");
		}
		if(StringUtils.length(codeBaseInfo.getLanguage()) > 255){
			errors.rejectValue(CodeBaseInfo.PROPERTY_LANGUAGE,null,"开发语言最长255个字符");
		}
		if(StringUtils.length(codeBaseInfo.getLanguageLevel()) > 255){
			errors.rejectValue(CodeBaseInfo.PROPERTY_LANGUAGE_LEVEL,null,"语言级别最长255个字符");
		}
	}
}