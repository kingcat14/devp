package net.aicoder.devp.product.business.sys.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.product.business.sys.dto.DevpSysDgmElementAddDto;
import net.aicoder.devp.product.business.sys.dto.DevpSysDgmElementEditDto;
import net.aicoder.devp.product.business.sys.domain.DevpSysDgmElement;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class DevpSysDgmElementValidator implements Validator {

	/**
	 * 判断支持的JavaBean类型
	 * @param aClass
	 * @return
	 */
	@Override
	public boolean supports(Class<?> aClass) {
		if(DevpSysDgmElementAddDto.class.equals(aClass))
			return true;
		if(DevpSysDgmElementEditDto.class.equals(aClass))
			return true;
		if(PageSearchRequest.class.equals(aClass))
			return true;
		return DevpSysDgmElement.class.equals(aClass);
	}

	/**
	 * 实现Validator中的validate接口
	 * @param obj
	 * @param errors
	 */
	@Override
	public void validate(Object obj, Errors errors) {
	    if(obj instanceof DevpSysDgmElement){
            this.validateDevpSysDgmElement((DevpSysDgmElement)obj, errors);
        }
	}

	/**
     * 实现Validator中的validate接口
     * @param devpSysDgmElement UML图包含元素
     * @param errors
     */
	public void validateDevpSysDgmElement(DevpSysDgmElement devpSysDgmElement, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if (null == devpSysDgmElement.getTid() ) {
			errors.rejectValue(DevpSysDgmElement.PROPERTY_TID, "EMPTY_"+DevpSysDgmElement.PROPERTY_TID, "租户编号不能为空");
		}
		if(StringUtils.isEmpty(devpSysDgmElement.getName())){
			errors.rejectValue(DevpSysDgmElement.PROPERTY_NAME, "EMPTY_"+DevpSysDgmElement.PROPERTY_NAME, "系统元素名称不能为空");
		}
		if (null == devpSysDgmElement.getPrdRid() ) {
			errors.rejectValue(DevpSysDgmElement.PROPERTY_PRD_RID, "EMPTY_"+DevpSysDgmElement.PROPERTY_PRD_RID, "产品编号不能为空");
		}
		if (null == devpSysDgmElement.getDgmRid() ) {
			errors.rejectValue(DevpSysDgmElement.PROPERTY_DGM_RID, "EMPTY_"+DevpSysDgmElement.PROPERTY_DGM_RID, "UML图编号不能为空");
		}
		if (null == devpSysDgmElement.getElmRid() ) {
			errors.rejectValue(DevpSysDgmElement.PROPERTY_ELM_RID, "EMPTY_"+DevpSysDgmElement.PROPERTY_ELM_RID, "系统元素编号不能为空");
		}

		//验证长度
		if(StringUtils.length(devpSysDgmElement.getName()) > 255){
			errors.rejectValue(DevpSysDgmElement.PROPERTY_NAME,null,"系统元素名称最长255个字符");
		}
		if(StringUtils.length(devpSysDgmElement.getCode()) > 255){
			errors.rejectValue(DevpSysDgmElement.PROPERTY_CODE,null,"系统元素代码最长255个字符");
		}
		if(StringUtils.length(devpSysDgmElement.getAlias()) > 255){
			errors.rejectValue(DevpSysDgmElement.PROPERTY_ALIAS,null,"系统元素别名最长255个字符");
		}
		if(StringUtils.length(devpSysDgmElement.getDescription()) > 255){
			errors.rejectValue(DevpSysDgmElement.PROPERTY_DESCRIPTION,null,"系统元素描述最长255个字符");
		}
		if(StringUtils.length(devpSysDgmElement.getType()) > 255){
			errors.rejectValue(DevpSysDgmElement.PROPERTY_TYPE,null,"类型最长255个字符");
		}
		if(StringUtils.length(devpSysDgmElement.getSubType()) > 255){
			errors.rejectValue(DevpSysDgmElement.PROPERTY_SUB_TYPE,null,"子类型最长255个字符");
		}
		if(StringUtils.length(devpSysDgmElement.getStereotype()) > 255){
			errors.rejectValue(DevpSysDgmElement.PROPERTY_STEREOTYPE,null,"构造型最长255个字符");
		}
		if(StringUtils.length(devpSysDgmElement.getScope()) > 255){
			errors.rejectValue(DevpSysDgmElement.PROPERTY_SCOPE,null,"范围最长255个字符");
		}
		if(StringUtils.length(devpSysDgmElement.getCreateUcode()) > 255){
			errors.rejectValue(DevpSysDgmElement.PROPERTY_CREATE_UCODE,null,"创建用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysDgmElement.getModifyUcode()) > 255){
			errors.rejectValue(DevpSysDgmElement.PROPERTY_MODIFY_UCODE,null,"修改用户代码最长255个字符");
		}
	}
}