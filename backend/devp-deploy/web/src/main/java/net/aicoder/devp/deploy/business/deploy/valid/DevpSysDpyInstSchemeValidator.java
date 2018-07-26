package net.aicoder.devp.deploy.business.deploy.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.deploy.business.deploy.dto.DevpSysDpyInstSchemeAddDto;
import net.aicoder.devp.deploy.business.deploy.dto.DevpSysDpyInstSchemeEditDto;
import net.aicoder.devp.deploy.business.deploy.domain.DevpSysDpyInstScheme;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class DevpSysDpyInstSchemeValidator implements Validator {

	/**
	 * 判断支持的JavaBean类型
	 * @param aClass
	 * @return
	 */
	@Override
	public boolean supports(Class<?> aClass) {
		if(DevpSysDpyInstSchemeAddDto.class.equals(aClass))
			return true;
		if(DevpSysDpyInstSchemeEditDto.class.equals(aClass))
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
	    if(obj instanceof DevpSysDpyInstSchemeAddDto){
            this.validateDevpSysDpyInstSchemeAddDto((DevpSysDpyInstSchemeAddDto)obj, errors);
        }
	}

	/**
     * 实现Validator中的validate接口
     * @param devpSysDpyInstScheme 资源实例所属的部署方案
     * @param errors
     */
	public void validateDevpSysDpyInstSchemeAddDto(DevpSysDpyInstSchemeAddDto devpSysDpyInstScheme, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if (null == devpSysDpyInstScheme.getTid() ) {
			errors.rejectValue(DevpSysDpyInstScheme.PROPERTY_TID, "EMPTY_"+DevpSysDpyInstScheme.PROPERTY_TID, "租户编号不能为空");
		}
		if(StringUtils.isEmpty(devpSysDpyInstScheme.getEtype())){
			errors.rejectValue(DevpSysDpyInstScheme.PROPERTY_ETYPE, "EMPTY_"+DevpSysDpyInstScheme.PROPERTY_ETYPE, "元素类型不能为空");
		}
		if(StringUtils.isEmpty(devpSysDpyInstScheme.getName())){
			errors.rejectValue(DevpSysDpyInstScheme.PROPERTY_NAME, "EMPTY_"+DevpSysDpyInstScheme.PROPERTY_NAME, "系统元素名称不能为空");
		}
		if (null == devpSysDpyInstScheme.getPrdRid() ) {
			errors.rejectValue(DevpSysDpyInstScheme.PROPERTY_PRD_RID, "EMPTY_"+DevpSysDpyInstScheme.PROPERTY_PRD_RID, "产品编号不能为空");
		}
		if (null == devpSysDpyInstScheme.getResRid() ) {
			errors.rejectValue(DevpSysDpyInstScheme.PROPERTY_RES_RID, "EMPTY_"+DevpSysDpyInstScheme.PROPERTY_RES_RID, "关联资源编号不能为空");
		}
		if (null == devpSysDpyInstScheme.getInstRid() ) {
			errors.rejectValue(DevpSysDpyInstScheme.PROPERTY_INST_RID, "EMPTY_"+DevpSysDpyInstScheme.PROPERTY_INST_RID, "关联资源实例编号不能为空");
		}
		if (null == devpSysDpyInstScheme.getSchemeRid() ) {
			errors.rejectValue(DevpSysDpyInstScheme.PROPERTY_SCHEME_RID, "EMPTY_"+DevpSysDpyInstScheme.PROPERTY_SCHEME_RID, "部署方案编号不能为空");
		}

		//验证长度
		if(StringUtils.length(devpSysDpyInstScheme.getEtype()) > 255){
			errors.rejectValue(DevpSysDpyInstScheme.PROPERTY_ETYPE,null,"元素类型最长255个字符");
		}
		if(StringUtils.length(devpSysDpyInstScheme.getName()) > 255){
			errors.rejectValue(DevpSysDpyInstScheme.PROPERTY_NAME,null,"系统元素名称最长255个字符");
		}
		if(StringUtils.length(devpSysDpyInstScheme.getCode()) > 255){
			errors.rejectValue(DevpSysDpyInstScheme.PROPERTY_CODE,null,"系统元素代码最长255个字符");
		}
		if(StringUtils.length(devpSysDpyInstScheme.getAlias()) > 255){
			errors.rejectValue(DevpSysDpyInstScheme.PROPERTY_ALIAS,null,"系统元素别名最长255个字符");
		}
		if(StringUtils.length(devpSysDpyInstScheme.getDescription()) > 255){
			errors.rejectValue(DevpSysDpyInstScheme.PROPERTY_DESCRIPTION,null,"系统元素描述最长255个字符");
		}
		if(StringUtils.length(devpSysDpyInstScheme.getType()) > 255){
			errors.rejectValue(DevpSysDpyInstScheme.PROPERTY_TYPE,null,"类型最长255个字符");
		}
		if(StringUtils.length(devpSysDpyInstScheme.getSubType()) > 255){
			errors.rejectValue(DevpSysDpyInstScheme.PROPERTY_SUB_TYPE,null,"子类型最长255个字符");
		}
		if(StringUtils.length(devpSysDpyInstScheme.getStereotype()) > 255){
			errors.rejectValue(DevpSysDpyInstScheme.PROPERTY_STEREOTYPE,null,"构造型最长255个字符");
		}
	}
}