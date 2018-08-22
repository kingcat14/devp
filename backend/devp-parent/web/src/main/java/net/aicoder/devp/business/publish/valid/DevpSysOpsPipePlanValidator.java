package net.aicoder.devp.business.publish.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.business.publish.dto.DevpSysOpsPipePlanAddDto;
import net.aicoder.devp.business.publish.dto.DevpSysOpsPipePlanEditDto;
import net.aicoder.devp.business.publish.domain.DevpSysOpsPipePlan;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class DevpSysOpsPipePlanValidator implements Validator {

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
	    if(obj instanceof DevpSysOpsPipePlanAddDto){
            this.validateDevpSysOpsPipePlanAddDto((DevpSysOpsPipePlanAddDto)obj, errors);
        }
	}

	/**
     * 实现Validator中的validate接口
     * @param devpSysOpsPipePlan 产品运维流水线执行计划
     * @param errors
     */
	public void validateDevpSysOpsPipePlanAddDto(DevpSysOpsPipePlanAddDto devpSysOpsPipePlan, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(devpSysOpsPipePlan.getEtype()) > 255){
			errors.rejectValue(DevpSysOpsPipePlan.PROPERTY_ETYPE,null,"元素类型最长255个字符");
		}
		if(StringUtils.length(devpSysOpsPipePlan.getName()) > 255){
			errors.rejectValue(DevpSysOpsPipePlan.PROPERTY_NAME,null,"系统元素名称最长255个字符");
		}
		if(StringUtils.length(devpSysOpsPipePlan.getCode()) > 255){
			errors.rejectValue(DevpSysOpsPipePlan.PROPERTY_CODE,null,"系统元素代码最长255个字符");
		}
		if(StringUtils.length(devpSysOpsPipePlan.getAlias()) > 255){
			errors.rejectValue(DevpSysOpsPipePlan.PROPERTY_ALIAS,null,"系统元素别名最长255个字符");
		}
		if(StringUtils.length(devpSysOpsPipePlan.getDescription()) > 255){
			errors.rejectValue(DevpSysOpsPipePlan.PROPERTY_DESCRIPTION,null,"系统元素描述最长255个字符");
		}
		if(StringUtils.length(devpSysOpsPipePlan.getType()) > 255){
			errors.rejectValue(DevpSysOpsPipePlan.PROPERTY_TYPE,null,"类型最长255个字符");
		}
		if(StringUtils.length(devpSysOpsPipePlan.getSubType()) > 255){
			errors.rejectValue(DevpSysOpsPipePlan.PROPERTY_SUB_TYPE,null,"子类型最长255个字符");
		}
		if(StringUtils.length(devpSysOpsPipePlan.getSchedule()) > 255){
			errors.rejectValue(DevpSysOpsPipePlan.PROPERTY_SCHEDULE,null,"定时执行配置最长255个字符");
		}
		if(StringUtils.length(devpSysOpsPipePlan.getCreateUcode()) > 255){
			errors.rejectValue(DevpSysOpsPipePlan.PROPERTY_CREATE_UCODE,null,"创建用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysOpsPipePlan.getCreateUname()) > 255){
			errors.rejectValue(DevpSysOpsPipePlan.PROPERTY_CREATE_UNAME,null,"创建用户姓名最长255个字符");
		}
		if(StringUtils.length(devpSysOpsPipePlan.getModifyUcode()) > 255){
			errors.rejectValue(DevpSysOpsPipePlan.PROPERTY_MODIFY_UCODE,null,"修改用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysOpsPipePlan.getModifyUname()) > 255){
			errors.rejectValue(DevpSysOpsPipePlan.PROPERTY_MODIFY_UNAME,null,"修改用户姓名最长255个字符");
		}
	}
}