package net.aicoder.devp.business.deploy.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.business.deploy.dto.DevpSysDpyCmpRefAddDto;
import net.aicoder.devp.business.deploy.dto.DevpSysDpyCmpRefEditDto;
import net.aicoder.devp.business.deploy.domain.DevpSysDpyCmpRef;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class DevpSysDpyCmpRefValidator implements Validator {

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
	    if(obj instanceof DevpSysDpyCmpRefAddDto){
            this.validateDevpSysDpyCmpRefAddDto((DevpSysDpyCmpRefAddDto)obj, errors);
        }
	}

	/**
     * 实现Validator中的validate接口
     * @param devpSysDpyCmpRef 系统元素间关系
     * @param errors
     */
	public void validateDevpSysDpyCmpRefAddDto(DevpSysDpyCmpRefAddDto devpSysDpyCmpRef, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(devpSysDpyCmpRef.getEtype()) > 255){
			errors.rejectValue(DevpSysDpyCmpRef.PROPERTY_ETYPE,null,"元素类型最长255个字符");
		}
		if(StringUtils.length(devpSysDpyCmpRef.getCode()) > 255){
			errors.rejectValue(DevpSysDpyCmpRef.PROPERTY_CODE,null,"对应关系代码最长255个字符");
		}
		if(StringUtils.length(devpSysDpyCmpRef.getName()) > 255){
			errors.rejectValue(DevpSysDpyCmpRef.PROPERTY_NAME,null,"对应关系名称最长255个字符");
		}
		if(StringUtils.length(devpSysDpyCmpRef.getAlias()) > 255){
			errors.rejectValue(DevpSysDpyCmpRef.PROPERTY_ALIAS,null,"对应关系别名最长255个字符");
		}
		if(StringUtils.length(devpSysDpyCmpRef.getDescription()) > 255){
			errors.rejectValue(DevpSysDpyCmpRef.PROPERTY_DESCRIPTION,null,"对应关系描述最长255个字符");
		}
		if(StringUtils.length(devpSysDpyCmpRef.getType()) > 255){
			errors.rejectValue(DevpSysDpyCmpRef.PROPERTY_TYPE,null,"类型最长255个字符");
		}
		if(StringUtils.length(devpSysDpyCmpRef.getSubType()) > 255){
			errors.rejectValue(DevpSysDpyCmpRef.PROPERTY_SUB_TYPE,null,"子类型最长255个字符");
		}
		if(StringUtils.length(devpSysDpyCmpRef.getStereotype()) > 255){
			errors.rejectValue(DevpSysDpyCmpRef.PROPERTY_STEREOTYPE,null,"构造型最长255个字符");
		}
		if(StringUtils.length(devpSysDpyCmpRef.getScope()) > 255){
			errors.rejectValue(DevpSysDpyCmpRef.PROPERTY_SCOPE,null,"范围最长255个字符");
		}
		if(StringUtils.length(devpSysDpyCmpRef.getDirection()) > 255){
			errors.rejectValue(DevpSysDpyCmpRef.PROPERTY_DIRECTION,null,"方向最长255个字符");
		}
		if(StringUtils.length(devpSysDpyCmpRef.getSrcMulti()) > 255){
			errors.rejectValue(DevpSysDpyCmpRef.PROPERTY_SRC_MULTI,null,"来源对应数量最长255个字符");
		}
		if(StringUtils.length(devpSysDpyCmpRef.getSrcRole()) > 255){
			errors.rejectValue(DevpSysDpyCmpRef.PROPERTY_SRC_ROLE,null,"来源角色最长255个字符");
		}
		if(StringUtils.length(devpSysDpyCmpRef.getSrcRoleType()) > 255){
			errors.rejectValue(DevpSysDpyCmpRef.PROPERTY_SRC_ROLE_TYPE,null,"来源角色类型最长255个字符");
		}
		if(StringUtils.length(devpSysDpyCmpRef.getDestMulti()) > 255){
			errors.rejectValue(DevpSysDpyCmpRef.PROPERTY_DEST_MULTI,null,"目标对应数量最长255个字符");
		}
		if(StringUtils.length(devpSysDpyCmpRef.getDestRole()) > 255){
			errors.rejectValue(DevpSysDpyCmpRef.PROPERTY_DEST_ROLE,null,"目标角色最长255个字符");
		}
		if(StringUtils.length(devpSysDpyCmpRef.getDestRoleType()) > 255){
			errors.rejectValue(DevpSysDpyCmpRef.PROPERTY_DEST_ROLE_TYPE,null,"目标角色类型最长255个字符");
		}
		if(StringUtils.length(devpSysDpyCmpRef.getAttrRelation()) > 255){
			errors.rejectValue(DevpSysDpyCmpRef.PROPERTY_ATTR_RELATION,null,"属性对应关系最长255个字符");
		}
		if(StringUtils.length(devpSysDpyCmpRef.getCreateUcode()) > 255){
			errors.rejectValue(DevpSysDpyCmpRef.PROPERTY_CREATE_UCODE,null,"创建用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysDpyCmpRef.getCreateUname()) > 255){
			errors.rejectValue(DevpSysDpyCmpRef.PROPERTY_CREATE_UNAME,null,"创建用户姓名最长255个字符");
		}
		if(StringUtils.length(devpSysDpyCmpRef.getModifyUcode()) > 255){
			errors.rejectValue(DevpSysDpyCmpRef.PROPERTY_MODIFY_UCODE,null,"修改用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysDpyCmpRef.getModifyUname()) > 255){
			errors.rejectValue(DevpSysDpyCmpRef.PROPERTY_MODIFY_UNAME,null,"修改用户姓名最长255个字符");
		}
	}
}