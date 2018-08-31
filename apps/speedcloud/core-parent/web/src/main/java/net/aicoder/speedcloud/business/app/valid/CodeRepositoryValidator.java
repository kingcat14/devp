package net.aicoder.speedcloud.business.app.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.app.dto.CodeRepositoryAddDto;
import net.aicoder.speedcloud.business.app.dto.CodeRepositoryEditDto;
import net.aicoder.speedcloud.business.app.dto.CodeRepositoryCondition;
import net.aicoder.speedcloud.business.app.domain.CodeRepository;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

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
            this.validateCodeRepositoryAddDto((CodeRepositoryAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<CodeRepositoryCondition> search){
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new CodeRepositoryCondition());
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param codeRepository 代码库
     * @param errors
     */
	public void validateCodeRepositoryAddDto(CodeRepositoryAddDto codeRepository, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(codeRepository.getUrl()) > 255){
			errors.rejectValue(CodeRepository.PROPERTY_URL,null,"url最长255个字符");
		}
		if(StringUtils.length(codeRepository.getUsername()) > 255){
			errors.rejectValue(CodeRepository.PROPERTY_USERNAME,null,"用户名最长255个字符");
		}
		if(StringUtils.length(codeRepository.getPassword()) > 255){
			errors.rejectValue(CodeRepository.PROPERTY_PASSWORD,null,"密码最长255个字符");
		}
	}
}