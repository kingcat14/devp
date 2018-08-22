package net.aicoder.devp.business.product.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;

import net.aicoder.devp.business.product.domain.DevpPrdPerson;
import net.aicoder.devp.business.product.dto.DevpPrdPersonAddDto;
import net.aicoder.devp.business.product.dto.DevpPrdPersonEditDto;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class DevpPrdPersonValidator implements Validator {

	/**
	 * 判断支持的JavaBean类型
	 * @param aClass
	 * @return
	 */
	@Override
	public boolean supports(Class<?> aClass) {
		if(DevpPrdPersonAddDto.class.equals(aClass))
			return true;
		if(DevpPrdPersonEditDto.class.equals(aClass))
			return true;
		if(PageSearchRequest.class.equals(aClass))
			return true;
		return DevpPrdPerson.class.equals(aClass);
	}

	/**
	 * 实现Validator中的validate接口
	 * @param obj
	 * @param errors
	 */
	@Override
	public void validate(Object obj, Errors errors) {
	    if(obj instanceof DevpPrdPerson){
            this.validateDevpPrdPerson((DevpPrdPerson)obj, errors);
        }
	}

	/**
     * 实现Validator中的validate接口
     * @param devpPrdPerson 产品干系人
     * @param errors
     */
	public void validateDevpPrdPerson(DevpPrdPerson devpPrdPerson, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if (null == devpPrdPerson.getTid() ) {
			errors.rejectValue(DevpPrdPerson.PROPERTY_TID, "EMPTY_"+DevpPrdPerson.PROPERTY_TID, "租户编号不能为空");
		}
		if(StringUtils.isEmpty(devpPrdPerson.getCode())){
			errors.rejectValue(DevpPrdPerson.PROPERTY_CODE, "EMPTY_"+DevpPrdPerson.PROPERTY_CODE, "用户代码不能为空");
		}
		if(StringUtils.isEmpty(devpPrdPerson.getName())){
			errors.rejectValue(DevpPrdPerson.PROPERTY_NAME, "EMPTY_"+DevpPrdPerson.PROPERTY_NAME, "用户名称不能为空");
		}
		if(StringUtils.isEmpty(devpPrdPerson.getNexusType())){
			errors.rejectValue(DevpPrdPerson.PROPERTY_NEXUS_TYPE, "EMPTY_"+DevpPrdPerson.PROPERTY_NEXUS_TYPE, "关联元素类型不能为空");
		}
		if (null == devpPrdPerson.getNexusRid() ) {
			errors.rejectValue(DevpPrdPerson.PROPERTY_NEXUS_RID, "EMPTY_"+DevpPrdPerson.PROPERTY_NEXUS_RID, "关联元素编号不能为空");
		}
		if (null == devpPrdPerson.getUid() ) {
			errors.rejectValue(DevpPrdPerson.PROPERTY_UID, "EMPTY_"+DevpPrdPerson.PROPERTY_UID, "用户编号不能为空");
		}

		//验证长度
		if(StringUtils.length(devpPrdPerson.getCode()) > 255){
			errors.rejectValue(DevpPrdPerson.PROPERTY_CODE,null,"用户代码最长255个字符");
		}
		if(StringUtils.length(devpPrdPerson.getName()) > 255){
			errors.rejectValue(DevpPrdPerson.PROPERTY_NAME,null,"用户名称最长255个字符");
		}
		if(StringUtils.length(devpPrdPerson.getAlias()) > 255){
			errors.rejectValue(DevpPrdPerson.PROPERTY_ALIAS,null,"用户别名最长255个字符");
		}
		if(StringUtils.length(devpPrdPerson.getDescription()) > 255){
			errors.rejectValue(DevpPrdPerson.PROPERTY_DESCRIPTION,null,"用户描述最长255个字符");
		}
		if(StringUtils.length(devpPrdPerson.getNexusType()) > 255){
			errors.rejectValue(DevpPrdPerson.PROPERTY_NEXUS_TYPE,null,"关联元素类型最长255个字符");
		}
		if(StringUtils.length(devpPrdPerson.getType()) > 255){
			errors.rejectValue(DevpPrdPerson.PROPERTY_TYPE,null,"用户类型最长255个字符");
		}
		if(StringUtils.length(devpPrdPerson.getRole()) > 255){
			errors.rejectValue(DevpPrdPerson.PROPERTY_ROLE,null,"用户角色最长255个字符");
		}
		if(StringUtils.length(devpPrdPerson.getStatus()) > 255){
			errors.rejectValue(DevpPrdPerson.PROPERTY_STATUS,null,"状态最长255个字符");
		}
		if(StringUtils.length(devpPrdPerson.getOrgName()) > 255){
			errors.rejectValue(DevpPrdPerson.PROPERTY_ORG_NAME,null,"组织名称最长255个字符");
		}
		if(StringUtils.length(devpPrdPerson.getCreateUcode()) > 255){
			errors.rejectValue(DevpPrdPerson.PROPERTY_CREATE_UCODE,null,"创建用户代码最长255个字符");
		}
		if(StringUtils.length(devpPrdPerson.getModifyUcode()) > 255){
			errors.rejectValue(DevpPrdPerson.PROPERTY_MODIFY_UCODE,null,"修改用户代码最长255个字符");
		}
	}
}