package net.aicoder.devp.product.business.sys.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.product.business.sys.dto.DevpSysElementInfoAddDto;
import net.aicoder.devp.product.business.sys.dto.DevpSysElementInfoEditDto;
import net.aicoder.devp.product.business.sys.domain.DevpSysElementInfo;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class DevpSysElementInfoValidator implements Validator {

	/**
	 * 判断支持的JavaBean类型
	 * @param aClass
	 * @return
	 */
	@Override
	public boolean supports(Class<?> aClass) {
		if(DevpSysElementInfoAddDto.class.equals(aClass))
			return true;
		if(DevpSysElementInfoEditDto.class.equals(aClass))
			return true;
		if(PageSearchRequest.class.equals(aClass))
			return true;
		return DevpSysElementInfo.class.equals(aClass);
	}

	/**
	 * 实现Validator中的validate接口
	 * @param obj
	 * @param errors
	 */
	@Override
	public void validate(Object obj, Errors errors) {
	    if(obj instanceof DevpSysElementInfo){
            this.validateDevpSysElementInfo((DevpSysElementInfo)obj, errors);
        }
	}

	/**
     * 实现Validator中的validate接口
     * @param devpSysElementInfo 系统元素扩充信息
     * @param errors
     */
	public void validateDevpSysElementInfo(DevpSysElementInfo devpSysElementInfo, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if (null == devpSysElementInfo.getTid() ) {
			errors.rejectValue(DevpSysElementInfo.PROPERTY_TID, "EMPTY_"+DevpSysElementInfo.PROPERTY_TID, "租户编号不能为空");
		}
		if(StringUtils.isEmpty(devpSysElementInfo.getCode())){
			errors.rejectValue(DevpSysElementInfo.PROPERTY_CODE, "EMPTY_"+DevpSysElementInfo.PROPERTY_CODE, "扩展信息代码不能为空");
		}
		if (null == devpSysElementInfo.getPrdRid() ) {
			errors.rejectValue(DevpSysElementInfo.PROPERTY_PRD_RID, "EMPTY_"+DevpSysElementInfo.PROPERTY_PRD_RID, "产品编号不能为空");
		}
		if (null == devpSysElementInfo.getElmRid() ) {
			errors.rejectValue(DevpSysElementInfo.PROPERTY_ELM_RID, "EMPTY_"+DevpSysElementInfo.PROPERTY_ELM_RID, "系统元素编号不能为空");
		}
		if (null == devpSysElementInfo.getInstRid() ) {
			errors.rejectValue(DevpSysElementInfo.PROPERTY_INST_RID, "EMPTY_"+DevpSysElementInfo.PROPERTY_INST_RID, "系统元素实例编号不能为空");
		}

		//验证长度
		if(StringUtils.length(devpSysElementInfo.getCode()) > 255){
			errors.rejectValue(DevpSysElementInfo.PROPERTY_CODE,null,"扩展信息代码最长255个字符");
		}
		if(StringUtils.length(devpSysElementInfo.getName()) > 255){
			errors.rejectValue(DevpSysElementInfo.PROPERTY_NAME,null,"扩展信息名称最长255个字符");
		}
		if(StringUtils.length(devpSysElementInfo.getAlias()) > 255){
			errors.rejectValue(DevpSysElementInfo.PROPERTY_ALIAS,null,"扩展信息别名最长255个字符");
		}
		if(StringUtils.length(devpSysElementInfo.getDescription()) > 255){
			errors.rejectValue(DevpSysElementInfo.PROPERTY_DESCRIPTION,null,"扩展信息描述最长255个字符");
		}
		if(StringUtils.length(devpSysElementInfo.getInfoValue1()) > 255){
			errors.rejectValue(DevpSysElementInfo.PROPERTY_INFO_VALUE1,null,"信息值1最长255个字符");
		}
		if(StringUtils.length(devpSysElementInfo.getInfoValue2()) > 255){
			errors.rejectValue(DevpSysElementInfo.PROPERTY_INFO_VALUE2,null,"信息值2最长255个字符");
		}
		if(StringUtils.length(devpSysElementInfo.getInfoValue3()) > 255){
			errors.rejectValue(DevpSysElementInfo.PROPERTY_INFO_VALUE3,null,"信息值3最长255个字符");
		}
		if(StringUtils.length(devpSysElementInfo.getInfoValue4()) > 255){
			errors.rejectValue(DevpSysElementInfo.PROPERTY_INFO_VALUE4,null,"信息值4最长255个字符");
		}
		if(StringUtils.length(devpSysElementInfo.getInfoValue5()) > 255){
			errors.rejectValue(DevpSysElementInfo.PROPERTY_INFO_VALUE5,null,"信息值5最长255个字符");
		}
		if(StringUtils.length(devpSysElementInfo.getNotes()) > 255){
			errors.rejectValue(DevpSysElementInfo.PROPERTY_NOTES,null,"备注最长255个字符");
		}
		if(StringUtils.length(devpSysElementInfo.getCreateUcode()) > 255){
			errors.rejectValue(DevpSysElementInfo.PROPERTY_CREATE_UCODE,null,"创建用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysElementInfo.getModifyUcode()) > 255){
			errors.rejectValue(DevpSysElementInfo.PROPERTY_MODIFY_UCODE,null,"修改用户代码最长255个字符");
		}
	}
}