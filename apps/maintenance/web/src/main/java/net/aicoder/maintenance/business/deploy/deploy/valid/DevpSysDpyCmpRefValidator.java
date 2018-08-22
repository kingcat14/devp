package net.aicoder.maintenance.business.deploy.deploy.valid;


import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.business.deploy.dto.DevpSysDpyCmpRefAddDto;
import net.aicoder.devp.business.deploy.dto.DevpSysDpyCmpRefEditDto;
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
            this.validateAddDto((DevpSysDpyCmpRefAddDto)obj, errors);
        }
	}

	/**
     * 验证新增信息
     * @param devpSysDpyCmpRef 系统元素关系定义
     * @param errors
     */
	public void validateAddDto(DevpSysDpyCmpRefAddDto devpSysDpyCmpRef, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if (null == devpSysDpyCmpRef.getTid() ) {
			errors.rejectValue("tid", "EMPTY_TID", "租户编号不能为空");
		}
		if(StringUtils.isEmpty(devpSysDpyCmpRef.getEtype())){
			errors.rejectValue("etype", "EMPTY_ETYPE", "元素类型不能为空");
		}
       
		if (null == devpSysDpyCmpRef.getPrdRid() ) {
			errors.rejectValue("prdRid", "EMPTY_PRD_RID", "产品编号不能为空");
		}
		if (null == devpSysDpyCmpRef.getSchemeRid() ) {
			errors.rejectValue("schemeRid", "EMPTY_SCHEME_RID", "部署方案编号不能为空");
		}
		if (null == devpSysDpyCmpRef.getCmpRid() ) {
			errors.rejectValue("cmpRid", "EMPTY_CMP_RID", "组件编号不能为空");
		}
		if (null == devpSysDpyCmpRef.getRefEtype() ) {
			errors.rejectValue("refEtype", "EMPTY_REF_ETYPE", "关联元素类型不能为空");
		}
		if (null == devpSysDpyCmpRef.getRefPrdRid() ) {
			errors.rejectValue("refPrdRid", "EMPTY_REF_PRD_RID", "关联产品编号不能为空");
		}
		if (null == devpSysDpyCmpRef.getRefElmRid() ) {
			errors.rejectValue("refElmRid", "EMPTY_REF_ELM_RID", "关联元素编号不能为空");
		}
		if (null == devpSysDpyCmpRef.getRefInstRid() ) {
			errors.rejectValue("refInstRid", "EMPTY_REF_INST_RID", "关联元素实例编号不能为空");
		}

		//验证长度
		if(StringUtils.length(devpSysDpyCmpRef.getEtype()) > 255){
			errors.rejectValue("etype", null, "元素类型最长255个字符");
		}
		if(StringUtils.length(devpSysDpyCmpRef.getCode()) > 255){
			errors.rejectValue("code", null, "对应关系代码最长255个字符");
		}
		if(StringUtils.length(devpSysDpyCmpRef.getName()) > 255){
			errors.rejectValue("name", null, "对应关系名称最长255个字符");
		}
		if(StringUtils.length(devpSysDpyCmpRef.getAlias()) > 255){
			errors.rejectValue("alias", null, "对应关系别名最长255个字符");
		}
		if(StringUtils.length(devpSysDpyCmpRef.getDescription()) > 255){
			errors.rejectValue("description", null, "对应关系描述最长255个字符");
		}
		if(StringUtils.length(devpSysDpyCmpRef.getType()) > 255){
			errors.rejectValue("type", null, "类型最长255个字符");
		}
		if(StringUtils.length(devpSysDpyCmpRef.getSubType()) > 255){
			errors.rejectValue("subType", null, "子类型最长255个字符");
		}
		if(StringUtils.length(devpSysDpyCmpRef.getStereotype()) > 255){
			errors.rejectValue("stereotype", null, "构造型最长255个字符");
		}
		if(StringUtils.length(devpSysDpyCmpRef.getScope()) > 255){
			errors.rejectValue("scope", null, "范围最长255个字符");
		}
		if(StringUtils.length(devpSysDpyCmpRef.getDirection()) > 255){
			errors.rejectValue("direction", null, "方向最长255个字符");
		}
		if(StringUtils.length(devpSysDpyCmpRef.getSrcMulti()) > 255){
			errors.rejectValue("srcMulti", null, "来源对应数量最长255个字符");
		}
		if(StringUtils.length(devpSysDpyCmpRef.getSrcRole()) > 255){
			errors.rejectValue("srcRole", null, "来源角色最长255个字符");
		}
		if(StringUtils.length(devpSysDpyCmpRef.getSrcRoleType()) > 255){
			errors.rejectValue("srcRoleType", null, "来源角色类型最长255个字符");
		}
		if(StringUtils.length(devpSysDpyCmpRef.getDestMulti()) > 255){
			errors.rejectValue("destMulti", null, "目标对应数量最长255个字符");
		}
		if(StringUtils.length(devpSysDpyCmpRef.getDestRole()) > 255){
			errors.rejectValue("destRole", null, "目标角色最长255个字符");
		}
		if(StringUtils.length(devpSysDpyCmpRef.getDestRoleType()) > 255){
			errors.rejectValue("destRoleType", null, "目标角色类型最长255个字符");
		}
		if(StringUtils.length(devpSysDpyCmpRef.getAttrRelation()) > 255){
			errors.rejectValue("attrRelation", null, "属性对应关系最长255个字符");
		}
	}
}