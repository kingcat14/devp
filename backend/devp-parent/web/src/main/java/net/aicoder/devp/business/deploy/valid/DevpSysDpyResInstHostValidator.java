package net.aicoder.devp.business.deploy.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.business.deploy.dto.DevpSysDpyResInstHostAddDto;
import net.aicoder.devp.business.deploy.dto.DevpSysDpyResInstHostEditDto;
import net.aicoder.devp.business.deploy.domain.DevpSysDpyResInstHost;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class DevpSysDpyResInstHostValidator implements Validator {

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
	    if(obj instanceof DevpSysDpyResInstHostAddDto){
            this.validateDevpSysDpyResInstHostAddDto((DevpSysDpyResInstHostAddDto)obj, errors);
        }
	}

	/**
     * 实现Validator中的validate接口
     * @param devpSysDpyResInstHost 资源实例部署主机节点
     * @param errors
     */
	public void validateDevpSysDpyResInstHostAddDto(DevpSysDpyResInstHostAddDto devpSysDpyResInstHost, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(devpSysDpyResInstHost.getEtype()) > 255){
			errors.rejectValue(DevpSysDpyResInstHost.PROPERTY_ETYPE,null,"元素类型最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResInstHost.getName()) > 255){
			errors.rejectValue(DevpSysDpyResInstHost.PROPERTY_NAME,null,"系统元素名称最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResInstHost.getCode()) > 255){
			errors.rejectValue(DevpSysDpyResInstHost.PROPERTY_CODE,null,"系统元素代码最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResInstHost.getAlias()) > 255){
			errors.rejectValue(DevpSysDpyResInstHost.PROPERTY_ALIAS,null,"系统元素别名最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResInstHost.getDescription()) > 255){
			errors.rejectValue(DevpSysDpyResInstHost.PROPERTY_DESCRIPTION,null,"系统元素描述最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResInstHost.getFlag()) > 255){
			errors.rejectValue(DevpSysDpyResInstHost.PROPERTY_FLAG,null,"主机标识最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResInstHost.getType()) > 255){
			errors.rejectValue(DevpSysDpyResInstHost.PROPERTY_TYPE,null,"类型最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResInstHost.getSubType()) > 255){
			errors.rejectValue(DevpSysDpyResInstHost.PROPERTY_SUB_TYPE,null,"子类型最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResInstHost.getStatus()) > 255){
			errors.rejectValue(DevpSysDpyResInstHost.PROPERTY_STATUS,null,"状态最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResInstHost.getNotes()) > 255){
			errors.rejectValue(DevpSysDpyResInstHost.PROPERTY_NOTES,null,"备注最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResInstHost.getCreateUcode()) > 255){
			errors.rejectValue(DevpSysDpyResInstHost.PROPERTY_CREATE_UCODE,null,"创建用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResInstHost.getCreateUname()) > 255){
			errors.rejectValue(DevpSysDpyResInstHost.PROPERTY_CREATE_UNAME,null,"创建用户姓名最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResInstHost.getModifyUcode()) > 255){
			errors.rejectValue(DevpSysDpyResInstHost.PROPERTY_MODIFY_UCODE,null,"修改用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResInstHost.getModifyUname()) > 255){
			errors.rejectValue(DevpSysDpyResInstHost.PROPERTY_MODIFY_UNAME,null,"修改用户姓名最长255个字符");
		}
	}
}