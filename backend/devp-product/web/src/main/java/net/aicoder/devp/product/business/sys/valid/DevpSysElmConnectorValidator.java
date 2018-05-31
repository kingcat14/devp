package net.aicoder.devp.product.business.sys.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.product.business.sys.dto.DevpSysElmConnectorAddDto;
import net.aicoder.devp.product.business.sys.dto.DevpSysElmConnectorEditDto;
import net.aicoder.devp.product.business.sys.domain.DevpSysElmConnector;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class DevpSysElmConnectorValidator implements Validator {

	/**
	 * 判断支持的JavaBean类型
	 * @param aClass
	 * @return
	 */
	@Override
	public boolean supports(Class<?> aClass) {
		if(DevpSysElmConnectorAddDto.class.equals(aClass))
			return true;
		if(DevpSysElmConnectorEditDto.class.equals(aClass))
			return true;
		if(PageSearchRequest.class.equals(aClass))
			return true;
		return DevpSysElmConnector.class.equals(aClass);
	}

	/**
	 * 实现Validator中的validate接口
	 * @param obj
	 * @param errors
	 */
	@Override
	public void validate(Object obj, Errors errors) {
	    if(obj instanceof DevpSysElmConnector){
            this.validateDevpSysElmConnector((DevpSysElmConnector)obj, errors);
        }
	}

	/**
     * 实现Validator中的validate接口
     * @param devpSysElmConnector 系统元素间关系
     * @param errors
     */
	public void validateDevpSysElmConnector(DevpSysElmConnector devpSysElmConnector, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if (null == devpSysElmConnector.getTid() ) {
			errors.rejectValue(DevpSysElmConnector.PROPERTY_TID, "EMPTY_"+DevpSysElmConnector.PROPERTY_TID, "租户编号不能为空");
		}
		if (null == devpSysElmConnector.getSprdRid() ) {
			errors.rejectValue(DevpSysElmConnector.PROPERTY_SPRD_RID, "EMPTY_"+DevpSysElmConnector.PROPERTY_SPRD_RID, "来源产品编号不能为空");
		}
		if (null == devpSysElmConnector.getSelmRid() ) {
			errors.rejectValue(DevpSysElmConnector.PROPERTY_SELM_RID, "EMPTY_"+DevpSysElmConnector.PROPERTY_SELM_RID, "来源系统元素编号不能为空");
		}
		if (null == devpSysElmConnector.getDprdRid() ) {
			errors.rejectValue(DevpSysElmConnector.PROPERTY_DPRD_RID, "EMPTY_"+DevpSysElmConnector.PROPERTY_DPRD_RID, "目标产品编号不能为空");
		}
		if (null == devpSysElmConnector.getDelmRid() ) {
			errors.rejectValue(DevpSysElmConnector.PROPERTY_DELM_RID, "EMPTY_"+DevpSysElmConnector.PROPERTY_DELM_RID, "目标系统元素编号不能为空");
		}
		if (null == devpSysElmConnector.getSinstRid() ) {
			errors.rejectValue(DevpSysElmConnector.PROPERTY_SINST_RID, "EMPTY_"+DevpSysElmConnector.PROPERTY_SINST_RID, "来源系统元素实例编号不能为空");
		}
		if (null == devpSysElmConnector.getDinstRid() ) {
			errors.rejectValue(DevpSysElmConnector.PROPERTY_DINST_RID, "EMPTY_"+DevpSysElmConnector.PROPERTY_DINST_RID, "目标系统元素实例编号不能为空");
		}

		//验证长度
		if(StringUtils.length(devpSysElmConnector.getCode()) > 255){
			errors.rejectValue(DevpSysElmConnector.PROPERTY_CODE,null,"对应关系代码最长255个字符");
		}
		if(StringUtils.length(devpSysElmConnector.getName()) > 255){
			errors.rejectValue(DevpSysElmConnector.PROPERTY_NAME,null,"对应关系名称最长255个字符");
		}
		if(StringUtils.length(devpSysElmConnector.getAlias()) > 255){
			errors.rejectValue(DevpSysElmConnector.PROPERTY_ALIAS,null,"对应关系别名最长255个字符");
		}
		if(StringUtils.length(devpSysElmConnector.getDescription()) > 255){
			errors.rejectValue(DevpSysElmConnector.PROPERTY_DESCRIPTION,null,"对应关系描述最长255个字符");
		}
		if(StringUtils.length(devpSysElmConnector.getType()) > 255){
			errors.rejectValue(DevpSysElmConnector.PROPERTY_TYPE,null,"对应关系类型最长255个字符");
		}
		if(StringUtils.length(devpSysElmConnector.getSubType()) > 255){
			errors.rejectValue(DevpSysElmConnector.PROPERTY_SUB_TYPE,null,"对应关系子类型最长255个字符");
		}
		if(StringUtils.length(devpSysElmConnector.getStereotype()) > 255){
			errors.rejectValue(DevpSysElmConnector.PROPERTY_STEREOTYPE,null,"构造型最长255个字符");
		}
		if(StringUtils.length(devpSysElmConnector.getScope()) > 255){
			errors.rejectValue(DevpSysElmConnector.PROPERTY_SCOPE,null,"范围最长255个字符");
		}
		if(StringUtils.length(devpSysElmConnector.getDirection()) > 255){
			errors.rejectValue(DevpSysElmConnector.PROPERTY_DIRECTION,null,"方向最长255个字符");
		}
		if(StringUtils.length(devpSysElmConnector.getSrcMulti()) > 255){
			errors.rejectValue(DevpSysElmConnector.PROPERTY_SRC_MULTI,null,"来源对应数量最长255个字符");
		}
		if(StringUtils.length(devpSysElmConnector.getSrcRole()) > 255){
			errors.rejectValue(DevpSysElmConnector.PROPERTY_SRC_ROLE,null,"来源角色最长255个字符");
		}
		if(StringUtils.length(devpSysElmConnector.getSrcRoleType()) > 255){
			errors.rejectValue(DevpSysElmConnector.PROPERTY_SRC_ROLE_TYPE,null,"来源角色类型最长255个字符");
		}
		if(StringUtils.length(devpSysElmConnector.getDestMulti()) > 255){
			errors.rejectValue(DevpSysElmConnector.PROPERTY_DEST_MULTI,null,"目标对应数量最长255个字符");
		}
		if(StringUtils.length(devpSysElmConnector.getDestRole()) > 255){
			errors.rejectValue(DevpSysElmConnector.PROPERTY_DEST_ROLE,null,"目标角色最长255个字符");
		}
		if(StringUtils.length(devpSysElmConnector.getDestRoleType()) > 255){
			errors.rejectValue(DevpSysElmConnector.PROPERTY_DEST_ROLE_TYPE,null,"目标角色类型最长255个字符");
		}
		if(StringUtils.length(devpSysElmConnector.getAttrRelation()) > 255){
			errors.rejectValue(DevpSysElmConnector.PROPERTY_ATTR_RELATION,null,"属性对应关系最长255个字符");
		}
		if(StringUtils.length(devpSysElmConnector.getCreateUcode()) > 255){
			errors.rejectValue(DevpSysElmConnector.PROPERTY_CREATE_UCODE,null,"创建用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysElmConnector.getModifyUcode()) > 255){
			errors.rejectValue(DevpSysElmConnector.PROPERTY_MODIFY_UCODE,null,"修改用户代码最长255个字符");
		}
	}
}