package com.yunkang.saas.platform.monitor.business.supporter.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.platform.monitor.business.supporter.domain.SupporterAppRelation;
import com.yunkang.saas.platform.monitor.business.supporter.dto.SupporterAppRelationAddDto;
import com.yunkang.saas.platform.monitor.business.supporter.dto.SupporterAppRelationCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class SupporterAppRelationValidator implements Validator {

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
	    if(obj instanceof SupporterAppRelationAddDto){
            this.validateSupporterAppRelationAddDto((SupporterAppRelationAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj, errors);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<SupporterAppRelationCondition> search, Errors errors) {
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new SupporterAppRelationCondition());
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param supporterAppRelation 支持应用
     * @param errors
     */
	public void validateSupporterAppRelationAddDto(SupporterAppRelationAddDto supporterAppRelation, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(supporterAppRelation.getSupporter()) > 255){
			errors.rejectValue(SupporterAppRelation.PROPERTY_SUPPORTER,null,"运维人员最长255个字符");
		}
		if(StringUtils.length(supporterAppRelation.getApplication()) > 255){
			errors.rejectValue(SupporterAppRelation.PROPERTY_APPLICATION,null,"支持程序最长255个字符");
		}
		if(StringUtils.length(supporterAppRelation.getNotificationType()) > 255){
			errors.rejectValue(SupporterAppRelation.PROPERTY_NOTIFICATION_TYPE,null,"接收通知方式最长255个字符");
		}
	}
}