package net.aicoder.speedcloud.console.business.icode.domain.valid;


import net.aicoder.speedcloud.icode.business.domain.dto.DomainAddDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class DomainValidator implements Validator {

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
	    if(obj instanceof DomainAddDto){
            this.validateAddDto((DomainAddDto)obj, errors);
        }
	}

	/**
     * 验证新增信息
     * @param domain 领域
     * @param errors
     */
	public void validateAddDto(DomainAddDto domain, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(domain.getName()) > 255){
			errors.rejectValue("name", null, "领域名称最长255个字符");
		}
		if(StringUtils.length(domain.getCode()) > 255){
			errors.rejectValue("code", null, "领域代码最长255个字符");
		}
		if(StringUtils.length(domain.getParent()) > 255){
			errors.rejectValue("parent", null, "父领域最长255个字符");
		}
		if(StringUtils.length(domain.getPrefix()) > 255){
			errors.rejectValue("prefix", null, "领域代码前缀最长255个字符");
		}
	}
}