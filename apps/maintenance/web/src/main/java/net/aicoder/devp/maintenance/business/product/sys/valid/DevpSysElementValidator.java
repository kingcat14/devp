package net.aicoder.devp.maintenance.business.product.sys.valid;


import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.product.business.sys.dto.DevpSysElementAddDto;
import net.aicoder.devp.product.business.sys.dto.DevpSysElementEditDto;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class DevpSysElementValidator implements Validator {

	/**
	 * 判断支持的JavaBean类型
	 * @param aClass
	 * @return
	 */
	@Override
	public boolean supports(Class<?> aClass) {
		if(DevpSysElementAddDto.class.equals(aClass))
			return true;
		if(DevpSysElementEditDto.class.equals(aClass))
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
	    if(obj instanceof DevpSysElementAddDto){
            this.validateAddDto((DevpSysElementAddDto)obj, errors);
        }
	}

	/**
     * 验证新增信息
     * @param devpSysElement 系统元素
     * @param errors
     */
	public void validateAddDto(DevpSysElementAddDto devpSysElement, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if (null == devpSysElement.getTid() ) {
			errors.rejectValue("tid", "EMPTY_TID", "租户编号不能为空");
		}
		if(StringUtils.isEmpty(devpSysElement.getName())){
			errors.rejectValue("name", "EMPTY_NAME", "系统元素名称不能为空");
		}
       
		if(StringUtils.isEmpty(devpSysElement.getElmFlag())){
			errors.rejectValue("elmFlag", "EMPTY_ELM_FLAG", "系统元素所属类型标识不能为空");
		}
       
		if (null == devpSysElement.getPrdRid() ) {
			errors.rejectValue("prdRid", "EMPTY_PRD_RID", "产品编号不能为空");
		}

		//验证长度
		if(StringUtils.length(devpSysElement.getName()) > 255){
			errors.rejectValue("name", null, "系统元素名称最长255个字符");
		}
		if(StringUtils.length(devpSysElement.getCode()) > 255){
			errors.rejectValue("code", null, "系统元素代码最长255个字符");
		}
		if(StringUtils.length(devpSysElement.getAlias()) > 255){
			errors.rejectValue("alias", null, "系统元素别名最长255个字符");
		}
		if(StringUtils.length(devpSysElement.getDescription()) > 255){
			errors.rejectValue("description", null, "系统元素描述最长255个字符");
		}
		if(StringUtils.length(devpSysElement.getElmFlag()) > 255){
			errors.rejectValue("elmFlag", null, "系统元素所属类型标识最长255个字符");
		}
		if(StringUtils.length(devpSysElement.getType()) > 255){
			errors.rejectValue("type", null, "类型最长255个字符");
		}
		if(StringUtils.length(devpSysElement.getSubType()) > 255){
			errors.rejectValue("subType", null, "子类型最长255个字符");
		}
		if(StringUtils.length(devpSysElement.getStereotype()) > 255){
			errors.rejectValue("stereotype", null, "构造型最长255个字符");
		}
		if(StringUtils.length(devpSysElement.getScope()) > 255){
			errors.rejectValue("scope", null, "范围最长255个字符");
		}
		if(StringUtils.length(devpSysElement.getVersion()) > 255){
			errors.rejectValue("version", null, "版本最长255个字符");
		}
		if(StringUtils.length(devpSysElement.getPhase()) > 255){
			errors.rejectValue("phase", null, "阶段最长255个字符");
		}
		if(StringUtils.length(devpSysElement.getStatus()) > 255){
			errors.rejectValue("status", null, "状态最长255个字符");
		}
		if(StringUtils.length(devpSysElement.getNotes()) > 255){
			errors.rejectValue("notes", null, "备注最长255个字符");
		}
		if(StringUtils.length(devpSysElement.getCreateUcode()) > 255){
			errors.rejectValue("createUcode", null, "创建用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysElement.getModifyUcode()) > 255){
			errors.rejectValue("modifyUcode", null, "修改用户代码最长255个字符");
		}
	}
}