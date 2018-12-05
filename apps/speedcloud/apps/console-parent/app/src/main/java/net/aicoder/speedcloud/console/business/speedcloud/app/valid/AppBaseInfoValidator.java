package net.aicoder.speedcloud.console.business.speedcloud.app.valid;


import net.aicoder.speedcloud.business.app.dto.AppBaseInfoAddDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class AppBaseInfoValidator implements Validator {

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
	    if(obj instanceof AppBaseInfoAddDto){
            this.validateAddDto((AppBaseInfoAddDto)obj, errors);
        }
	}

	/**
     * 验证新增信息
     * @param appBaseInfo 应用（系统）
     * @param errors
     */
	public void validateAddDto(AppBaseInfoAddDto appBaseInfo, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(appBaseInfo.getProject()) > 255){
			errors.rejectValue("project", null, "所属项目最长255个字符");
		}
		if(StringUtils.length(appBaseInfo.getType()) > 255){
			errors.rejectValue("type", null, "应用类型最长255个字符");
		}
		if(StringUtils.length(appBaseInfo.getName()) > 255){
			errors.rejectValue("name", null, "名称最长255个字符");
		}
		if(StringUtils.length(appBaseInfo.getCode()) > 255){
			errors.rejectValue("code", null, "代码最长255个字符");
		}
		if(StringUtils.length(appBaseInfo.getStatus()) > 255){
			errors.rejectValue("status", null, "状态最长255个字符");
		}
		if(StringUtils.length(appBaseInfo.getRegistTime()) > 255){
			errors.rejectValue("registTime", null, "注册时间最长255个字符");
		}
	}
}