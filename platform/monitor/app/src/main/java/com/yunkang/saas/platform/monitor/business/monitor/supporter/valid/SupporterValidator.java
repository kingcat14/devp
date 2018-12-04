package com.yunkang.saas.platform.monitor.business.supporter.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.platform.monitor.business.supporter.domain.Supporter;
import com.yunkang.saas.platform.monitor.business.supporter.dto.SupporterAddDto;
import com.yunkang.saas.platform.monitor.business.supporter.dto.SupporterCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class SupporterValidator implements Validator {

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
	    if(obj instanceof SupporterAddDto){
            this.validateSupporterAddDto((SupporterAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj, errors);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<SupporterCondition> search, Errors errors) {
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new SupporterCondition());
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param supporter 运维人员
     * @param errors
     */
	public void validateSupporterAddDto(SupporterAddDto supporter, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(supporter.getName()) > 255){
			errors.rejectValue(Supporter.PROPERTY_NAME,null,"姓名最长255个字符");
		}
		if(StringUtils.length(supporter.getMobile()) > 255){
			errors.rejectValue(Supporter.PROPERTY_MOBILE,null,"手机号码最长255个字符");
		}
		if(StringUtils.length(supporter.getEmail()) > 255){
			errors.rejectValue(Supporter.PROPERTY_EMAIL,null,"邮箱最长255个字符");
		}
	}
}