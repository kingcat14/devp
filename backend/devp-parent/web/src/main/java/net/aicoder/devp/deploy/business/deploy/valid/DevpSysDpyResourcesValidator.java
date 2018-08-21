package net.aicoder.devp.deploy.business.deploy.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.deploy.business.deploy.dto.DevpSysDpyResourcesAddDto;
import net.aicoder.devp.deploy.business.deploy.dto.DevpSysDpyResourcesEditDto;
import net.aicoder.devp.deploy.business.deploy.domain.DevpSysDpyResources;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class DevpSysDpyResourcesValidator implements Validator {

	/**
	 * 判断支持的JavaBean类型
	 * @param aClass
	 * @return
	 */
	@Override
	public boolean supports(Class<?> aClass) {
		if(DevpSysDpyResourcesAddDto.class.equals(aClass))
			return true;
		if(DevpSysDpyResourcesEditDto.class.equals(aClass))
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
	    if(obj instanceof DevpSysDpyResourcesAddDto){
            this.validateDevpSysDpyResourcesAddDto((DevpSysDpyResourcesAddDto)obj, errors);
        }
	}

	/**
     * 实现Validator中的validate接口
     * @param devpSysDpyResources 部署关联资源
     * @param errors
     */
	public void validateDevpSysDpyResourcesAddDto(DevpSysDpyResourcesAddDto devpSysDpyResources, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if (null == devpSysDpyResources.getTid() ) {
			errors.rejectValue(DevpSysDpyResources.PROPERTY_TID, "EMPTY_"+DevpSysDpyResources.PROPERTY_TID, "租户编号不能为空");
		}
		if(StringUtils.isEmpty(devpSysDpyResources.getEtype())){
			errors.rejectValue(DevpSysDpyResources.PROPERTY_ETYPE, "EMPTY_"+DevpSysDpyResources.PROPERTY_ETYPE, "元素类型不能为空");
		}
		if(StringUtils.isEmpty(devpSysDpyResources.getName())){
			errors.rejectValue(DevpSysDpyResources.PROPERTY_NAME, "EMPTY_"+DevpSysDpyResources.PROPERTY_NAME, "系统元素名称不能为空");
		}
		if(StringUtils.isEmpty(devpSysDpyResources.getCode())){
			errors.rejectValue(DevpSysDpyResources.PROPERTY_CODE, "EMPTY_"+DevpSysDpyResources.PROPERTY_CODE, "系统元素代码不能为空");
		}
		if (null == devpSysDpyResources.getPrdRid() ) {
			errors.rejectValue(DevpSysDpyResources.PROPERTY_PRD_RID, "EMPTY_"+DevpSysDpyResources.PROPERTY_PRD_RID, "产品编号不能为空");
		}

		//验证长度
		if(StringUtils.length(devpSysDpyResources.getEtype()) > 255){
			errors.rejectValue(DevpSysDpyResources.PROPERTY_ETYPE,null,"元素类型最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResources.getName()) > 255){
			errors.rejectValue(DevpSysDpyResources.PROPERTY_NAME,null,"系统元素名称最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResources.getCode()) > 255){
			errors.rejectValue(DevpSysDpyResources.PROPERTY_CODE,null,"系统元素代码最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResources.getAlias()) > 255){
			errors.rejectValue(DevpSysDpyResources.PROPERTY_ALIAS,null,"系统元素别名最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResources.getDescription()) > 255){
			errors.rejectValue(DevpSysDpyResources.PROPERTY_DESCRIPTION,null,"系统元素描述最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResources.getType()) > 255){
			errors.rejectValue(DevpSysDpyResources.PROPERTY_TYPE,null,"类型最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResources.getSubType()) > 255){
			errors.rejectValue(DevpSysDpyResources.PROPERTY_SUB_TYPE,null,"子类型最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResources.getStereotype()) > 255){
			errors.rejectValue(DevpSysDpyResources.PROPERTY_STEREOTYPE,null,"构造型最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResources.getScope()) > 255){
			errors.rejectValue(DevpSysDpyResources.PROPERTY_SCOPE,null,"范围最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResources.getVersion()) > 255){
			errors.rejectValue(DevpSysDpyResources.PROPERTY_VERSION,null,"版本最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResources.getPhase()) > 255){
			errors.rejectValue(DevpSysDpyResources.PROPERTY_PHASE,null,"阶段最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResources.getStatus()) > 255){
			errors.rejectValue(DevpSysDpyResources.PROPERTY_STATUS,null,"状态最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResources.getNotes()) > 255){
			errors.rejectValue(DevpSysDpyResources.PROPERTY_NOTES,null,"备注最长255个字符");
		}
	}
}