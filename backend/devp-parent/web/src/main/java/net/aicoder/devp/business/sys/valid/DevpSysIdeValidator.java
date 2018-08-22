package net.aicoder.devp.business.sys.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.business.sys.dto.DevpSysIdeAddDto;
import net.aicoder.devp.business.sys.dto.DevpSysIdeEditDto;
import net.aicoder.devp.business.sys.domain.DevpSysIde;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class DevpSysIdeValidator implements Validator {

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
	    if(obj instanceof DevpSysIdeAddDto){
            this.validateDevpSysIdeAddDto((DevpSysIdeAddDto)obj, errors);
        }
	}

	/**
     * 实现Validator中的validate接口
     * @param devpSysIde 开发工程
     * @param errors
     */
	public void validateDevpSysIdeAddDto(DevpSysIdeAddDto devpSysIde, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(devpSysIde.getEtype()) > 255){
			errors.rejectValue(DevpSysIde.PROPERTY_ETYPE,null,"元素类型最长255个字符");
		}
		if(StringUtils.length(devpSysIde.getName()) > 255){
			errors.rejectValue(DevpSysIde.PROPERTY_NAME,null,"系统元素名称最长255个字符");
		}
		if(StringUtils.length(devpSysIde.getCode()) > 255){
			errors.rejectValue(DevpSysIde.PROPERTY_CODE,null,"系统元素代码最长255个字符");
		}
		if(StringUtils.length(devpSysIde.getAlias()) > 255){
			errors.rejectValue(DevpSysIde.PROPERTY_ALIAS,null,"系统元素别名最长255个字符");
		}
		if(StringUtils.length(devpSysIde.getDescription()) > 255){
			errors.rejectValue(DevpSysIde.PROPERTY_DESCRIPTION,null,"系统元素描述最长255个字符");
		}
		if(StringUtils.length(devpSysIde.getType()) > 255){
			errors.rejectValue(DevpSysIde.PROPERTY_TYPE,null,"类型最长255个字符");
		}
		if(StringUtils.length(devpSysIde.getSubType()) > 255){
			errors.rejectValue(DevpSysIde.PROPERTY_SUB_TYPE,null,"子类型最长255个字符");
		}
		if(StringUtils.length(devpSysIde.getStereotype()) > 255){
			errors.rejectValue(DevpSysIde.PROPERTY_STEREOTYPE,null,"构造型最长255个字符");
		}
		if(StringUtils.length(devpSysIde.getScope()) > 255){
			errors.rejectValue(DevpSysIde.PROPERTY_SCOPE,null,"范围最长255个字符");
		}
		if(StringUtils.length(devpSysIde.getVersion()) > 255){
			errors.rejectValue(DevpSysIde.PROPERTY_VERSION,null,"版本最长255个字符");
		}
		if(StringUtils.length(devpSysIde.getPhase()) > 255){
			errors.rejectValue(DevpSysIde.PROPERTY_PHASE,null,"阶段最长255个字符");
		}
		if(StringUtils.length(devpSysIde.getStatus()) > 255){
			errors.rejectValue(DevpSysIde.PROPERTY_STATUS,null,"状态最长255个字符");
		}
		if(StringUtils.length(devpSysIde.getNotes()) > 255){
			errors.rejectValue(DevpSysIde.PROPERTY_NOTES,null,"备注最长255个字符");
		}
		if(StringUtils.length(devpSysIde.getCreateUcode()) > 255){
			errors.rejectValue(DevpSysIde.PROPERTY_CREATE_UCODE,null,"创建用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysIde.getCreateUname()) > 255){
			errors.rejectValue(DevpSysIde.PROPERTY_CREATE_UNAME,null,"创建用户姓名最长255个字符");
		}
		if(StringUtils.length(devpSysIde.getModifyUcode()) > 255){
			errors.rejectValue(DevpSysIde.PROPERTY_MODIFY_UCODE,null,"修改用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysIde.getModifyUname()) > 255){
			errors.rejectValue(DevpSysIde.PROPERTY_MODIFY_UNAME,null,"修改用户姓名最长255个字符");
		}
	}
}