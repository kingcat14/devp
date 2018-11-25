package net.aicoder.speedcloud.icode.business.domain.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.icode.business.domain.domain.Domain;
import net.aicoder.speedcloud.icode.business.domain.dto.DomainAddDto;
import net.aicoder.speedcloud.icode.business.domain.dto.DomainCondition;
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
            this.validateDomainAddDto((DomainAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj, errors);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<DomainCondition> search, Errors errors) {
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new DomainCondition());
        }
        if(search.getSearchCondition().getTid() == null){
        	errors.rejectValue("NOT_TENANT_ID", "未正确设置租户ID");
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param domain 领域
     * @param errors
     */
	public void validateDomainAddDto(DomainAddDto domain, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(domain.getName()) > 255){
			errors.rejectValue(Domain.PROPERTY_NAME,null,"领域名称最长255个字符");
		}
		if(StringUtils.length(domain.getCode()) > 255){
			errors.rejectValue(Domain.PROPERTY_CODE,null,"领域代码最长255个字符");
		}
		if(StringUtils.length(domain.getParent()) > 255){
			errors.rejectValue(Domain.PROPERTY_PARENT,null,"父领域最长255个字符");
		}
		if(StringUtils.length(domain.getPrefix()) > 255){
			errors.rejectValue(Domain.PROPERTY_PREFIX,null,"领域代码前缀最长255个字符");
		}
	}
}