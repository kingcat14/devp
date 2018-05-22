package net.aicoder.devp.product.business.product.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.product.business.product.dto.DevpSysExtcmpAddDto;
import net.aicoder.devp.product.business.product.domain.DevpSysExtcmp;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;

public class DevpSysExtcmpValidator implements Validator {

	/**
	 * 判断支持的JavaBean类型
	 * @param aClass
	 * @return
	 */
	@Override
	public boolean supports(Class<?> aClass) {
		if(DevpSysExtcmpAddDto.class.equals(aClass))
			return true;
		if(DevpSysExtcmpAddDto.class.equals(aClass))
            return true;
		return DevpSysExtcmp.class.equals(aClass);
	}

	/**
	 * 实现Validator中的validate接口
	 * @param obj
	 * @param errors
	 */
	@Override
	public void validate(Object obj, Errors errors) {
	    if(obj instanceof DevpSysExtcmp){
            this.validateDevpSysExtcmp((DevpSysExtcmp)obj, errors);
        }
	}

	/**
     * 实现Validator中的validate接口
     * @param devpSysExtcmp 产品包含的外部组件
     * @param errors
     */
	public void validateDevpSysExtcmp(DevpSysExtcmp devpSysExtcmp, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if (null == devpSysExtcmp.getTid() ) {
			errors.rejectValue(DevpSysExtcmp.PROPERTY_TID, "EMPTY_"+DevpSysExtcmp.PROPERTY_TID, "租户编号不能为空");
		}
		if (null == devpSysExtcmp.getPrdRid() ) {
			errors.rejectValue(DevpSysExtcmp.PROPERTY_PRD_RID, "EMPTY_"+DevpSysExtcmp.PROPERTY_PRD_RID, "产品编号不能为空");
		}
		if (null == devpSysExtcmp.getExtPrdRid() ) {
			errors.rejectValue(DevpSysExtcmp.PROPERTY_EXT_PRD_RID, "EMPTY_"+DevpSysExtcmp.PROPERTY_EXT_PRD_RID, "外部产品编号不能为空");
		}
		if (null == devpSysExtcmp.getExtElmRid() ) {
			errors.rejectValue(DevpSysExtcmp.PROPERTY_EXT_ELM_RID, "EMPTY_"+DevpSysExtcmp.PROPERTY_EXT_ELM_RID, "外部系统元素编号不能为空");
		}

		//验证长度
		if(StringUtils.length(devpSysExtcmp.getCode()) > 255){
			errors.rejectValue(DevpSysExtcmp.PROPERTY_CODE,null,"代码最长255个字符");
		}
		if(StringUtils.length(devpSysExtcmp.getName()) > 255){
			errors.rejectValue(DevpSysExtcmp.PROPERTY_NAME,null,"名称最长255个字符");
		}
		if(StringUtils.length(devpSysExtcmp.getAlias()) > 255){
			errors.rejectValue(DevpSysExtcmp.PROPERTY_ALIAS,null,"别名最长255个字符");
		}
		if(StringUtils.length(devpSysExtcmp.getDescription()) > 255){
			errors.rejectValue(DevpSysExtcmp.PROPERTY_DESCRIPTION,null,"描述最长255个字符");
		}
		if(StringUtils.length(devpSysExtcmp.getType()) > 255){
			errors.rejectValue(DevpSysExtcmp.PROPERTY_TYPE,null,"类型最长255个字符");
		}
		if(StringUtils.length(devpSysExtcmp.getStereotype()) > 255){
			errors.rejectValue(DevpSysExtcmp.PROPERTY_STEREOTYPE,null,"构造型最长255个字符");
		}
		if(StringUtils.length(devpSysExtcmp.getCreateUcode()) > 255){
			errors.rejectValue(DevpSysExtcmp.PROPERTY_CREATE_UCODE,null,"创建用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysExtcmp.getModifyUcode()) > 255){
			errors.rejectValue(DevpSysExtcmp.PROPERTY_MODIFY_UCODE,null,"修改用户代码最长255个字符");
		}
	}
}