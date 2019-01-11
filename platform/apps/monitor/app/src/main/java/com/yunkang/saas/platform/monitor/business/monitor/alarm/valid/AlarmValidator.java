package com.yunkang.saas.platform.monitor.business.monitor.alarm.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.platform.monitor.business.alarm.domain.Alarm;
import com.yunkang.saas.platform.monitor.business.alarm.dto.AlarmAddDto;
import com.yunkang.saas.platform.monitor.business.alarm.dto.AlarmCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class AlarmValidator implements Validator {

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
	    if(obj instanceof AlarmAddDto){
            this.validateAlarmAddDto((AlarmAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj, errors);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<AlarmCondition> search, Errors errors) {
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new AlarmCondition());
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param alarm 告警
     * @param errors
     */
	public void validateAlarmAddDto(AlarmAddDto alarm, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(alarm.getCode()) > 255){
			errors.rejectValue(Alarm.PROPERTY_CODE,null,"代码最长255个字符");
		}
		if(StringUtils.length(alarm.getName()) > 255){
			errors.rejectValue(Alarm.PROPERTY_NAME,null,"名称最长255个字符");
		}
		if(StringUtils.length(alarm.getType()) > 255){
			errors.rejectValue(Alarm.PROPERTY_TYPE,null,"类型最长255个字符");
		}
		if(StringUtils.length(alarm.getApp()) > 255){
			errors.rejectValue(Alarm.PROPERTY_APP,null,"程序最长255个字符");
		}
		if(StringUtils.length(alarm.getCounter()) > 255){
			errors.rejectValue(Alarm.PROPERTY_COUNTER,null,"指标最长255个字符");
		}
		if(StringUtils.length(alarm.getValue()) > 255){
			errors.rejectValue(Alarm.PROPERTY_VALUE,null,"指标值最长255个字符");
		}
		if(StringUtils.length(alarm.getStatus()) > 255){
			errors.rejectValue(Alarm.PROPERTY_STATUS,null,"状态最长255个字符");
		}
		if(StringUtils.length(alarm.getContent()) > 255){
			errors.rejectValue(Alarm.PROPERTY_CONTENT,null,"内容最长255个字符");
		}
	}
}