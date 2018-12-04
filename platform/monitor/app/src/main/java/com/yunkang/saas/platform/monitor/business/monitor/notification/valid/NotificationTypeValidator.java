package com.yunkang.saas.platform.monitor.business.notification.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.platform.monitor.business.notification.domain.NotificationType;
import com.yunkang.saas.platform.monitor.business.notification.dto.NotificationTypeAddDto;
import com.yunkang.saas.platform.monitor.business.notification.dto.NotificationTypeCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class NotificationTypeValidator implements Validator {

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
	    if(obj instanceof NotificationTypeAddDto){
            this.validateNotificationTypeAddDto((NotificationTypeAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj, errors);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<NotificationTypeCondition> search, Errors errors) {
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new NotificationTypeCondition());
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param notificationType 通知方式
     * @param errors
     */
	public void validateNotificationTypeAddDto(NotificationTypeAddDto notificationType, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(notificationType.getCode()) > 255){
			errors.rejectValue(NotificationType.PROPERTY_CODE,null,"通知方式最长255个字符");
		}
	}
}