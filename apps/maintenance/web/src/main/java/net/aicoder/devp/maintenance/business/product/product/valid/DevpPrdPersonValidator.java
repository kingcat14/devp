package net.aicoder.devp.maintenance.business.product.product.valid;


import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.product.business.product.dto.DevpPrdPersonAddDto;
import net.aicoder.devp.product.business.product.dto.DevpPrdPersonEditDto;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

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
		return false;
	}

	/**
	 * 实现Validator中的validate接口
	 * @param obj
	 * @param errors
	 */
	@Override
	public void validate(Object obj, Errors errors) {
	    if(obj instanceof DevpPrdPersonAddDto){
            this.validateAddDto((DevpPrdPersonAddDto)obj, errors);
        }
	}

	/**
     * 验证新增信息
     * @param devpPrdPerson 产品干系人
     * @param errors
     */
	public void validateAddDto(DevpPrdPersonAddDto devpPrdPerson, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if (null == devpPrdPerson.getTid() ) {
			errors.rejectValue("tid", "EMPTY_TID", "租户编号不能为空");
		}
		if(StringUtils.isEmpty(devpPrdPerson.getCode())){
			errors.rejectValue("code", "EMPTY_CODE", "用户代码不能为空");
		}
       
		if(StringUtils.isEmpty(devpPrdPerson.getName())){
			errors.rejectValue("name", "EMPTY_NAME", "用户名称不能为空");
		}
       
		if(StringUtils.isEmpty(devpPrdPerson.getNexusType())){
			errors.rejectValue("nexusType", "EMPTY_NEXUS_TYPE", "关联元素类型不能为空");
		}
       
		if (null == devpPrdPerson.getNexusRid() ) {
			errors.rejectValue("nexusRid", "EMPTY_NEXUS_RID", "关联元素编号不能为空");
		}
		if (null == devpPrdPerson.getUid() ) {
			errors.rejectValue("uid", "EMPTY_UID", "用户编号不能为空");
		}

		//验证长度
		if(StringUtils.length(devpPrdPerson.getCode()) > 255){
			errors.rejectValue("code", null, "用户代码最长255个字符");
		}
		if(StringUtils.length(devpPrdPerson.getName()) > 255){
			errors.rejectValue("name", null, "用户名称最长255个字符");
		}
		if(StringUtils.length(devpPrdPerson.getAlias()) > 255){
			errors.rejectValue("alias", null, "用户别名最长255个字符");
		}
		if(StringUtils.length(devpPrdPerson.getDescription()) > 255){
			errors.rejectValue("description", null, "用户描述最长255个字符");
		}
		if(StringUtils.length(devpPrdPerson.getNexusType()) > 255){
			errors.rejectValue("nexusType", null, "关联元素类型最长255个字符");
		}
		if(StringUtils.length(devpPrdPerson.getType()) > 255){
			errors.rejectValue("type", null, "用户类型最长255个字符");
		}
		if(StringUtils.length(devpPrdPerson.getRole()) > 255){
			errors.rejectValue("role", null, "用户角色最长255个字符");
		}
		if(StringUtils.length(devpPrdPerson.getStatus()) > 255){
			errors.rejectValue("status", null, "状态最长255个字符");
		}
		if(StringUtils.length(devpPrdPerson.getOrgName()) > 255){
			errors.rejectValue("orgName", null, "组织名称最长255个字符");
		}
		if(StringUtils.length(devpPrdPerson.getCreateUcode()) > 255){
			errors.rejectValue("createUcode", null, "创建用户代码最长255个字符");
		}
		if(StringUtils.length(devpPrdPerson.getModifyUcode()) > 255){
			errors.rejectValue("modifyUcode", null, "修改用户代码最长255个字符");
		}
	}
}