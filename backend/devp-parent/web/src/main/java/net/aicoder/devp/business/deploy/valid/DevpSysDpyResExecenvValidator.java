package net.aicoder.devp.business.deploy.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.business.deploy.dto.DevpSysDpyResExecenvAddDto;
import net.aicoder.devp.business.deploy.dto.DevpSysDpyResExecenvEditDto;
import net.aicoder.devp.business.deploy.domain.DevpSysDpyResExecenv;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class DevpSysDpyResExecenvValidator implements Validator {

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
	    if(obj instanceof DevpSysDpyResExecenvAddDto){
            this.validateDevpSysDpyResExecenvAddDto((DevpSysDpyResExecenvAddDto)obj, errors);
        }
	}

	/**
     * 实现Validator中的validate接口
     * @param devpSysDpyResExecenv 资源部署环境节点
     * @param errors
     */
	public void validateDevpSysDpyResExecenvAddDto(DevpSysDpyResExecenvAddDto devpSysDpyResExecenv, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(devpSysDpyResExecenv.getEtype()) > 255){
			errors.rejectValue(DevpSysDpyResExecenv.PROPERTY_ETYPE,null,"元素类型最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResExecenv.getName()) > 255){
			errors.rejectValue(DevpSysDpyResExecenv.PROPERTY_NAME,null,"系统元素名称最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResExecenv.getCode()) > 255){
			errors.rejectValue(DevpSysDpyResExecenv.PROPERTY_CODE,null,"系统元素代码最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResExecenv.getAlias()) > 255){
			errors.rejectValue(DevpSysDpyResExecenv.PROPERTY_ALIAS,null,"系统元素别名最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResExecenv.getDescription()) > 255){
			errors.rejectValue(DevpSysDpyResExecenv.PROPERTY_DESCRIPTION,null,"系统元素描述最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResExecenv.getType()) > 255){
			errors.rejectValue(DevpSysDpyResExecenv.PROPERTY_TYPE,null,"类型最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResExecenv.getSubType()) > 255){
			errors.rejectValue(DevpSysDpyResExecenv.PROPERTY_SUB_TYPE,null,"子类型最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResExecenv.getStatus()) > 255){
			errors.rejectValue(DevpSysDpyResExecenv.PROPERTY_STATUS,null,"状态最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResExecenv.getNotes()) > 255){
			errors.rejectValue(DevpSysDpyResExecenv.PROPERTY_NOTES,null,"备注最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResExecenv.getCreateUcode()) > 255){
			errors.rejectValue(DevpSysDpyResExecenv.PROPERTY_CREATE_UCODE,null,"创建用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResExecenv.getCreateUname()) > 255){
			errors.rejectValue(DevpSysDpyResExecenv.PROPERTY_CREATE_UNAME,null,"创建用户姓名最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResExecenv.getModifyUcode()) > 255){
			errors.rejectValue(DevpSysDpyResExecenv.PROPERTY_MODIFY_UCODE,null,"修改用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResExecenv.getModifyUname()) > 255){
			errors.rejectValue(DevpSysDpyResExecenv.PROPERTY_MODIFY_UNAME,null,"修改用户姓名最长255个字符");
		}
	}
}