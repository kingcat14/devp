package com.yunkang.saas.platform.monitor.business.monitor.alarm.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.platform.monitor.business.alarm.domain.AlarmType;
import com.yunkang.saas.platform.monitor.business.alarm.dto.AlarmTypeAddDto;
import com.yunkang.saas.platform.monitor.business.alarm.dto.AlarmTypeCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class AlarmTypeValidator implements Validator {

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
	    if(obj instanceof AlarmTypeAddDto){
            this.validateAlarmTypeAddDto((AlarmTypeAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj, errors);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<AlarmTypeCondition> search, Errors errors) {
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new AlarmTypeCondition());
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param alarmType 告警类型
     * @param errors
     */
	public void validateAlarmTypeAddDto(AlarmTypeAddDto alarmType, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(alarmType.getCode()) > 255){
			errors.rejectValue(AlarmType.PROPERTY_CODE,null,"代码最长255个字符");
		}
		if(StringUtils.length(alarmType.getName()) > 255){
			errors.rejectValue(AlarmType.PROPERTY_NAME,null,"名称最长255个字符");
		}
	}
}