package net.aicoder.devp.maintenance.business.product.sys.valid;


import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.product.business.sys.dto.DevpSysElmConnectorAddDto;
import net.aicoder.devp.product.business.sys.dto.DevpSysElmConnectorEditDto;
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
		return false;
	}

	/**
	 * 实现Validator中的validate接口
	 * @param obj
	 * @param errors
	 */
	@Override
	public void validate(Object obj, Errors errors) {
	    if(obj instanceof DevpSysElmConnectorAddDto){
            this.validateAddDto((DevpSysElmConnectorAddDto)obj, errors);
        }
	}

	/**
     * 验证新增信息
     * @param devpSysElmConnector 系统元素间关系
     * @param errors
     */
	public void validateAddDto(DevpSysElmConnectorAddDto devpSysElmConnector, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if (null == devpSysElmConnector.getTid() ) {
			errors.rejectValue("tid", "EMPTY_TID", "租户编号不能为空");
		}
		if (null == devpSysElmConnector.getSprdRid() ) {
			errors.rejectValue("sprdRid", "EMPTY_SPRD_RID", "来源产品编号不能为空");
		}
		if (null == devpSysElmConnector.getSelmRid() ) {
			errors.rejectValue("selmRid", "EMPTY_SELM_RID", "来源系统元素编号不能为空");
		}
		if (null == devpSysElmConnector.getDprdRid() ) {
			errors.rejectValue("dprdRid", "EMPTY_DPRD_RID", "目标产品编号不能为空");
		}
		if (null == devpSysElmConnector.getDelmRid() ) {
			errors.rejectValue("delmRid", "EMPTY_DELM_RID", "目标系统元素编号不能为空");
		}
		if (null == devpSysElmConnector.getSinstRid() ) {
			errors.rejectValue("sinstRid", "EMPTY_SINST_RID", "来源系统元素实例编号不能为空");
		}
		if (null == devpSysElmConnector.getDinstRid() ) {
			errors.rejectValue("dinstRid", "EMPTY_DINST_RID", "目标系统元素实例编号不能为空");
		}

		//验证长度
		if(StringUtils.length(devpSysElmConnector.getCode()) > 255){
			errors.rejectValue("code", null, "对应关系代码最长255个字符");
		}
		if(StringUtils.length(devpSysElmConnector.getName()) > 255){
			errors.rejectValue("name", null, "对应关系名称最长255个字符");
		}
		if(StringUtils.length(devpSysElmConnector.getAlias()) > 255){
			errors.rejectValue("alias", null, "对应关系别名最长255个字符");
		}
		if(StringUtils.length(devpSysElmConnector.getDescription()) > 255){
			errors.rejectValue("description", null, "对应关系描述最长255个字符");
		}
		if(StringUtils.length(devpSysElmConnector.getType()) > 255){
			errors.rejectValue("type", null, "对应关系类型最长255个字符");
		}
		if(StringUtils.length(devpSysElmConnector.getSubType()) > 255){
			errors.rejectValue("subType", null, "对应关系子类型最长255个字符");
		}
		if(StringUtils.length(devpSysElmConnector.getStereotype()) > 255){
			errors.rejectValue("stereotype", null, "构造型最长255个字符");
		}
		if(StringUtils.length(devpSysElmConnector.getScope()) > 255){
			errors.rejectValue("scope", null, "范围最长255个字符");
		}
		if(StringUtils.length(devpSysElmConnector.getDirection()) > 255){
			errors.rejectValue("direction", null, "方向最长255个字符");
		}
		if(StringUtils.length(devpSysElmConnector.getSrcMulti()) > 255){
			errors.rejectValue("srcMulti", null, "来源对应数量最长255个字符");
		}
		if(StringUtils.length(devpSysElmConnector.getSrcRole()) > 255){
			errors.rejectValue("srcRole", null, "来源角色最长255个字符");
		}
		if(StringUtils.length(devpSysElmConnector.getSrcRoleType()) > 255){
			errors.rejectValue("srcRoleType", null, "来源角色类型最长255个字符");
		}
		if(StringUtils.length(devpSysElmConnector.getDestMulti()) > 255){
			errors.rejectValue("destMulti", null, "目标对应数量最长255个字符");
		}
		if(StringUtils.length(devpSysElmConnector.getDestRole()) > 255){
			errors.rejectValue("destRole", null, "目标角色最长255个字符");
		}
		if(StringUtils.length(devpSysElmConnector.getDestRoleType()) > 255){
			errors.rejectValue("destRoleType", null, "目标角色类型最长255个字符");
		}
		if(StringUtils.length(devpSysElmConnector.getAttrRelation()) > 255){
			errors.rejectValue("attrRelation", null, "属性对应关系最长255个字符");
		}
		if(StringUtils.length(devpSysElmConnector.getCreateUcode()) > 255){
			errors.rejectValue("createUcode", null, "创建用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysElmConnector.getModifyUcode()) > 255){
			errors.rejectValue("modifyUcode", null, "修改用户代码最长255个字符");
		}
	}
}