package net.aicoder.devp.product.business.sys.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.product.business.sys.dto.DevpSysElmInstanceAddDto;
import net.aicoder.devp.product.business.sys.dto.DevpSysElmInstanceEditDto;
import net.aicoder.devp.product.business.sys.domain.DevpSysElmInstance;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class DevpSysElmInstanceValidator implements Validator {

	/**
	 * 判断支持的JavaBean类型
	 * @param aClass
	 * @return
	 */
	@Override
	public boolean supports(Class<?> aClass) {
		if(DevpSysElmInstanceAddDto.class.equals(aClass))
			return true;
		if(DevpSysElmInstanceEditDto.class.equals(aClass))
			return true;
		if(PageSearchRequest.class.equals(aClass))
			return true;
		return DevpSysElmInstance.class.equals(aClass);
	}

	/**
	 * 实现Validator中的validate接口
	 * @param obj
	 * @param errors
	 */
	@Override
	public void validate(Object obj, Errors errors) {
	    if(obj instanceof DevpSysElmInstance){
            this.validateDevpSysElmInstance((DevpSysElmInstance)obj, errors);
        }
	}

	/**
     * 实现Validator中的validate接口
     * @param devpSysElmInstance 系统元素实例
     * @param errors
     */
	public void validateDevpSysElmInstance(DevpSysElmInstance devpSysElmInstance, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if (null == devpSysElmInstance.getTid() ) {
			errors.rejectValue(DevpSysElmInstance.PROPERTY_TID, "EMPTY_"+DevpSysElmInstance.PROPERTY_TID, "租户编号不能为空");
		}
		if(StringUtils.isEmpty(devpSysElmInstance.getName())){
			errors.rejectValue(DevpSysElmInstance.PROPERTY_NAME, "EMPTY_"+DevpSysElmInstance.PROPERTY_NAME, "系统元素名称不能为空");
		}
		if(StringUtils.isEmpty(devpSysElmInstance.getElmFlag())){
			errors.rejectValue(DevpSysElmInstance.PROPERTY_ELM_FLAG, "EMPTY_"+DevpSysElmInstance.PROPERTY_ELM_FLAG, "系统元素所属类型标识不能为空");
		}
		if (null == devpSysElmInstance.getPrdRid() ) {
			errors.rejectValue(DevpSysElmInstance.PROPERTY_PRD_RID, "EMPTY_"+DevpSysElmInstance.PROPERTY_PRD_RID, "产品编号不能为空");
		}
		if (null == devpSysElmInstance.getElmRid() ) {
			errors.rejectValue(DevpSysElmInstance.PROPERTY_ELM_RID, "EMPTY_"+DevpSysElmInstance.PROPERTY_ELM_RID, "系统元素编号不能为空");
		}

		//验证长度
		if(StringUtils.length(devpSysElmInstance.getName()) > 255){
			errors.rejectValue(DevpSysElmInstance.PROPERTY_NAME,null,"系统元素名称最长255个字符");
		}
		if(StringUtils.length(devpSysElmInstance.getCode()) > 255){
			errors.rejectValue(DevpSysElmInstance.PROPERTY_CODE,null,"系统元素代码最长255个字符");
		}
		if(StringUtils.length(devpSysElmInstance.getAlias()) > 255){
			errors.rejectValue(DevpSysElmInstance.PROPERTY_ALIAS,null,"系统元素别名最长255个字符");
		}
		if(StringUtils.length(devpSysElmInstance.getDescription()) > 255){
			errors.rejectValue(DevpSysElmInstance.PROPERTY_DESCRIPTION,null,"系统元素描述最长255个字符");
		}
		if(StringUtils.length(devpSysElmInstance.getElmFlag()) > 255){
			errors.rejectValue(DevpSysElmInstance.PROPERTY_ELM_FLAG,null,"系统元素所属类型标识最长255个字符");
		}
		if(StringUtils.length(devpSysElmInstance.getType()) > 255){
			errors.rejectValue(DevpSysElmInstance.PROPERTY_TYPE,null,"类型最长255个字符");
		}
		if(StringUtils.length(devpSysElmInstance.getSubType()) > 255){
			errors.rejectValue(DevpSysElmInstance.PROPERTY_SUB_TYPE,null,"子类型最长255个字符");
		}
		if(StringUtils.length(devpSysElmInstance.getStereotype()) > 255){
			errors.rejectValue(DevpSysElmInstance.PROPERTY_STEREOTYPE,null,"构造型最长255个字符");
		}
		if(StringUtils.length(devpSysElmInstance.getCreateUcode()) > 255){
			errors.rejectValue(DevpSysElmInstance.PROPERTY_CREATE_UCODE,null,"创建用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysElmInstance.getModifyUcode()) > 255){
			errors.rejectValue(DevpSysElmInstance.PROPERTY_MODIFY_UCODE,null,"修改用户代码最长255个字符");
		}
	}
}