package net.aicoder.devp.deploy.business.deploy.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.deploy.business.deploy.dto.DevpSysDpyCmpRefAddDto;
import net.aicoder.devp.deploy.business.deploy.dto.DevpSysDpyCmpRefEditDto;
import net.aicoder.devp.deploy.business.deploy.domain.DevpSysDpyCmpRef;
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
		if(DevpSysDpyCmpRefAddDto.class.equals(aClass))
			return true;
		if(DevpSysDpyCmpRefEditDto.class.equals(aClass))
			return true;
		if(PageSearchRequest.class.equals(aClass))
			return true;
		return false;
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
     * @param devpSysDpyCmpRef 系统元素间关系定义
     * @param errors
     */
	public void validateDevpSysDpyCmpRefAddDto(DevpSysDpyCmpRefAddDto devpSysDpyCmpRef, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if (null == devpSysDpyCmpRef.getTid() ) {
			errors.rejectValue(DevpSysDpyCmpRef.PROPERTY_TID, "EMPTY_"+DevpSysDpyCmpRef.PROPERTY_TID, "租户编号不能为空");
		}
		if(StringUtils.isEmpty(devpSysDpyCmpRef.getEtype())){
			errors.rejectValue(DevpSysDpyCmpRef.PROPERTY_ETYPE, "EMPTY_"+DevpSysDpyCmpRef.PROPERTY_ETYPE, "元素类型不能为空");
		}
		if (null == devpSysDpyCmpRef.getPrdRid() ) {
			errors.rejectValue(DevpSysDpyCmpRef.PROPERTY_PRD_RID, "EMPTY_"+DevpSysDpyCmpRef.PROPERTY_PRD_RID, "产品编号不能为空");
		}
		if (null == devpSysDpyCmpRef.getSchemeRid() ) {
			errors.rejectValue(DevpSysDpyCmpRef.PROPERTY_SCHEME_RID, "EMPTY_"+DevpSysDpyCmpRef.PROPERTY_SCHEME_RID, "部署方案编号不能为空");
		}
		if (null == devpSysDpyCmpRef.getCmpRid() ) {
			errors.rejectValue(DevpSysDpyCmpRef.PROPERTY_CMP_RID, "EMPTY_"+DevpSysDpyCmpRef.PROPERTY_CMP_RID, "组件编号不能为空");
		}
		if (null == devpSysDpyCmpRef.getRefEtype() ) {
			errors.rejectValue(DevpSysDpyCmpRef.PROPERTY_REF_ETYPE, "EMPTY_"+DevpSysDpyCmpRef.PROPERTY_REF_ETYPE, "关联元素类型不能为空");
		}
		if (null == devpSysDpyCmpRef.getRefPrdRid() ) {
			errors.rejectValue(DevpSysDpyCmpRef.PROPERTY_REF_PRD_RID, "EMPTY_"+DevpSysDpyCmpRef.PROPERTY_REF_PRD_RID, "关联产品编号不能为空");
		}
		if (null == devpSysDpyCmpRef.getRefElmRid() ) {
			errors.rejectValue(DevpSysDpyCmpRef.PROPERTY_REF_ELM_RID, "EMPTY_"+DevpSysDpyCmpRef.PROPERTY_REF_ELM_RID, "关联元素编号不能为空");
		}
		if (null == devpSysDpyCmpRef.getRefInstRid() ) {
			errors.rejectValue(DevpSysDpyCmpRef.PROPERTY_REF_INST_RID, "EMPTY_"+DevpSysDpyCmpRef.PROPERTY_REF_INST_RID, "关联元素实例编号不能为空");
		}

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
	}
}