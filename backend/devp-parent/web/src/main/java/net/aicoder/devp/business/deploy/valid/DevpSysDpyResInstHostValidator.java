package net.aicoder.devp.business.deploy.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;

import net.aicoder.devp.business.deploy.domain.DevpSysDpyResInstHost;
import net.aicoder.devp.business.deploy.dto.DevpSysDpyResInstHostAddDto;
import net.aicoder.devp.business.deploy.dto.DevpSysDpyResInstHostEditDto;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class DevpSysDpyResInstHostValidator implements Validator {

	/**
	 * 判断支持的JavaBean类型
	 * @param aClass
	 * @return
	 */
	@Override
	public boolean supports(Class<?> aClass) {
		if(DevpSysDpyResInstHostAddDto.class.equals(aClass))
			return true;
		if(DevpSysDpyResInstHostEditDto.class.equals(aClass))
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
	    if(obj instanceof DevpSysDpyResInstHostAddDto){
            this.validateDevpSysDpyResInstHostAddDto((DevpSysDpyResInstHostAddDto)obj, errors);
        }
	}

	/**
     * 实现Validator中的validate接口
     * @param devpSysDpyResInstHost 资源实例部署主机节点
     * @param errors
     */
	public void validateDevpSysDpyResInstHostAddDto(DevpSysDpyResInstHostAddDto devpSysDpyResInstHost, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if (null == devpSysDpyResInstHost.getTid() ) {
			errors.rejectValue(DevpSysDpyResInstHost.PROPERTY_TID, "EMPTY_"+DevpSysDpyResInstHost.PROPERTY_TID, "租户编号不能为空");
		}
		if(StringUtils.isEmpty(devpSysDpyResInstHost.getEtype())){
			errors.rejectValue(DevpSysDpyResInstHost.PROPERTY_ETYPE, "EMPTY_"+DevpSysDpyResInstHost.PROPERTY_ETYPE, "元素类型不能为空");
		}
		if(StringUtils.isEmpty(devpSysDpyResInstHost.getName())){
			errors.rejectValue(DevpSysDpyResInstHost.PROPERTY_NAME, "EMPTY_"+DevpSysDpyResInstHost.PROPERTY_NAME, "系统元素名称不能为空");
		}
		if (null == devpSysDpyResInstHost.getPrdRid() ) {
			errors.rejectValue(DevpSysDpyResInstHost.PROPERTY_PRD_RID, "EMPTY_"+DevpSysDpyResInstHost.PROPERTY_PRD_RID, "产品编号不能为空");
		}
		if (null == devpSysDpyResInstHost.getSchemeRid() ) {
			errors.rejectValue(DevpSysDpyResInstHost.PROPERTY_SCHEME_RID, "EMPTY_"+DevpSysDpyResInstHost.PROPERTY_SCHEME_RID, "部署方案编号不能为空");
		}
		if (null == devpSysDpyResInstHost.getInstRid() ) {
			errors.rejectValue(DevpSysDpyResInstHost.PROPERTY_INST_RID, "EMPTY_"+DevpSysDpyResInstHost.PROPERTY_INST_RID, "关联资源实例编号不能为空");
		}
		if (null == devpSysDpyResInstHost.getHostRid() ) {
			errors.rejectValue(DevpSysDpyResInstHost.PROPERTY_HOST_RID, "EMPTY_"+DevpSysDpyResInstHost.PROPERTY_HOST_RID, "关联主机编号不能为空");
		}

		//验证长度
		if(StringUtils.length(devpSysDpyResInstHost.getEtype()) > 255){
			errors.rejectValue(DevpSysDpyResInstHost.PROPERTY_ETYPE,null,"元素类型最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResInstHost.getName()) > 255){
			errors.rejectValue(DevpSysDpyResInstHost.PROPERTY_NAME,null,"系统元素名称最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResInstHost.getCode()) > 255){
			errors.rejectValue(DevpSysDpyResInstHost.PROPERTY_CODE,null,"系统元素代码最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResInstHost.getAlias()) > 255){
			errors.rejectValue(DevpSysDpyResInstHost.PROPERTY_ALIAS,null,"系统元素别名最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResInstHost.getDescription()) > 255){
			errors.rejectValue(DevpSysDpyResInstHost.PROPERTY_DESCRIPTION,null,"系统元素描述最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResInstHost.getFlag()) > 255){
			errors.rejectValue(DevpSysDpyResInstHost.PROPERTY_FLAG,null,"主机标识最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResInstHost.getType()) > 255){
			errors.rejectValue(DevpSysDpyResInstHost.PROPERTY_TYPE,null,"类型最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResInstHost.getSubType()) > 255){
			errors.rejectValue(DevpSysDpyResInstHost.PROPERTY_SUB_TYPE,null,"子类型最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResInstHost.getStatus()) > 255){
			errors.rejectValue(DevpSysDpyResInstHost.PROPERTY_STATUS,null,"状态最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResInstHost.getNotes()) > 255){
			errors.rejectValue(DevpSysDpyResInstHost.PROPERTY_NOTES,null,"备注最长255个字符");
		}
	}
}