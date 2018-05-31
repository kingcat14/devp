package net.aicoder.devp.product.business.sys.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.product.business.sys.dto.DevpSysElmInstInfoAddDto;
import net.aicoder.devp.product.business.sys.dto.DevpSysElmInstInfoEditDto;
import net.aicoder.devp.product.business.sys.domain.DevpSysElmInstInfo;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class DevpSysElmInstInfoValidator implements Validator {

	/**
	 * 判断支持的JavaBean类型
	 * @param aClass
	 * @return
	 */
	@Override
	public boolean supports(Class<?> aClass) {
		if(DevpSysElmInstInfoAddDto.class.equals(aClass))
			return true;
		if(DevpSysElmInstInfoEditDto.class.equals(aClass))
			return true;
		if(PageSearchRequest.class.equals(aClass))
			return true;
		return DevpSysElmInstInfo.class.equals(aClass);
	}

	/**
	 * 实现Validator中的validate接口
	 * @param obj
	 * @param errors
	 */
	@Override
	public void validate(Object obj, Errors errors) {
	    if(obj instanceof DevpSysElmInstInfo){
            this.validateDevpSysElmInstInfo((DevpSysElmInstInfo)obj, errors);
        }
	}

	/**
     * 实现Validator中的validate接口
     * @param devpSysElmInstInfo 系统元素实例
     * @param errors
     */
	public void validateDevpSysElmInstInfo(DevpSysElmInstInfo devpSysElmInstInfo, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if (null == devpSysElmInstInfo.getTid() ) {
			errors.rejectValue(DevpSysElmInstInfo.PROPERTY_TID, "EMPTY_"+DevpSysElmInstInfo.PROPERTY_TID, "租户编号不能为空");
		}
		if(StringUtils.isEmpty(devpSysElmInstInfo.getCode())){
			errors.rejectValue(DevpSysElmInstInfo.PROPERTY_CODE, "EMPTY_"+DevpSysElmInstInfo.PROPERTY_CODE, "扩展信息代码不能为空");
		}
		if (null == devpSysElmInstInfo.getPrdRid() ) {
			errors.rejectValue(DevpSysElmInstInfo.PROPERTY_PRD_RID, "EMPTY_"+DevpSysElmInstInfo.PROPERTY_PRD_RID, "产品编号不能为空");
		}
		if (null == devpSysElmInstInfo.getContRid() ) {
			errors.rejectValue(DevpSysElmInstInfo.PROPERTY_CONT_RID, "EMPTY_"+DevpSysElmInstInfo.PROPERTY_CONT_RID, "关联连接编号不能为空");
		}
		if (null == devpSysElmInstInfo.getSprdRid() ) {
			errors.rejectValue(DevpSysElmInstInfo.PROPERTY_SPRD_RID, "EMPTY_"+DevpSysElmInstInfo.PROPERTY_SPRD_RID, "来源产品编号不能为空");
		}
		if (null == devpSysElmInstInfo.getSelmRid() ) {
			errors.rejectValue(DevpSysElmInstInfo.PROPERTY_SELM_RID, "EMPTY_"+DevpSysElmInstInfo.PROPERTY_SELM_RID, "来源系统元素编号不能为空");
		}
		if (null == devpSysElmInstInfo.getDprdRid() ) {
			errors.rejectValue(DevpSysElmInstInfo.PROPERTY_DPRD_RID, "EMPTY_"+DevpSysElmInstInfo.PROPERTY_DPRD_RID, "目标产品编号不能为空");
		}
		if (null == devpSysElmInstInfo.getDelmRid() ) {
			errors.rejectValue(DevpSysElmInstInfo.PROPERTY_DELM_RID, "EMPTY_"+DevpSysElmInstInfo.PROPERTY_DELM_RID, "目标系统元素编号不能为空");
		}
		if (null == devpSysElmInstInfo.getSinstRid() ) {
			errors.rejectValue(DevpSysElmInstInfo.PROPERTY_SINST_RID, "EMPTY_"+DevpSysElmInstInfo.PROPERTY_SINST_RID, "来源系统元素实例编号不能为空");
		}
		if (null == devpSysElmInstInfo.getDinstRid() ) {
			errors.rejectValue(DevpSysElmInstInfo.PROPERTY_DINST_RID, "EMPTY_"+DevpSysElmInstInfo.PROPERTY_DINST_RID, "目标系统元素实例编号不能为空");
		}

		//验证长度
		if(StringUtils.length(devpSysElmInstInfo.getCode()) > 255){
			errors.rejectValue(DevpSysElmInstInfo.PROPERTY_CODE,null,"扩展信息代码最长255个字符");
		}
		if(StringUtils.length(devpSysElmInstInfo.getName()) > 255){
			errors.rejectValue(DevpSysElmInstInfo.PROPERTY_NAME,null,"扩展信息名称最长255个字符");
		}
		if(StringUtils.length(devpSysElmInstInfo.getAlias()) > 255){
			errors.rejectValue(DevpSysElmInstInfo.PROPERTY_ALIAS,null,"扩展信息别名最长255个字符");
		}
		if(StringUtils.length(devpSysElmInstInfo.getDescription()) > 255){
			errors.rejectValue(DevpSysElmInstInfo.PROPERTY_DESCRIPTION,null,"扩展信息描述最长255个字符");
		}
		if(StringUtils.length(devpSysElmInstInfo.getType()) > 255){
			errors.rejectValue(DevpSysElmInstInfo.PROPERTY_TYPE,null,"设值方式最长255个字符");
		}
		if(StringUtils.length(devpSysElmInstInfo.getInfoValue1()) > 255){
			errors.rejectValue(DevpSysElmInstInfo.PROPERTY_INFO_VALUE1,null,"信息值1最长255个字符");
		}
		if(StringUtils.length(devpSysElmInstInfo.getInfoValue2()) > 255){
			errors.rejectValue(DevpSysElmInstInfo.PROPERTY_INFO_VALUE2,null,"信息值2最长255个字符");
		}
		if(StringUtils.length(devpSysElmInstInfo.getInfoValue3()) > 255){
			errors.rejectValue(DevpSysElmInstInfo.PROPERTY_INFO_VALUE3,null,"信息值3最长255个字符");
		}
		if(StringUtils.length(devpSysElmInstInfo.getInfoValue4()) > 255){
			errors.rejectValue(DevpSysElmInstInfo.PROPERTY_INFO_VALUE4,null,"信息值4最长255个字符");
		}
		if(StringUtils.length(devpSysElmInstInfo.getInfoValue5()) > 255){
			errors.rejectValue(DevpSysElmInstInfo.PROPERTY_INFO_VALUE5,null,"信息值5最长255个字符");
		}
		if(StringUtils.length(devpSysElmInstInfo.getNotes()) > 255){
			errors.rejectValue(DevpSysElmInstInfo.PROPERTY_NOTES,null,"备注最长255个字符");
		}
		if(StringUtils.length(devpSysElmInstInfo.getCreateUcode()) > 255){
			errors.rejectValue(DevpSysElmInstInfo.PROPERTY_CREATE_UCODE,null,"创建用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysElmInstInfo.getModifyUcode()) > 255){
			errors.rejectValue(DevpSysElmInstInfo.PROPERTY_MODIFY_UCODE,null,"修改用户代码最长255个字符");
		}
	}
}