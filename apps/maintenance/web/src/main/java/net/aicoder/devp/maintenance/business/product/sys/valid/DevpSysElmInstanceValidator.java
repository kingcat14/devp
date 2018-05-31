package net.aicoder.devp.maintenance.business.product.sys.valid;


import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.product.business.sys.dto.DevpSysElmInstanceAddDto;
import net.aicoder.devp.product.business.sys.dto.DevpSysElmInstanceEditDto;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class DevpSysElmInstanceValidator implements Validator {

	/**
	 * 判断支持的JavaBean类型
	 * @param aClass
	 * @return
	 */
	@Override
	public boolean supports(Class<?> aClass) {
		if(DevpSysElmInstanceAddDto.class.equals(aClass))
			return true;
		if(DevpSysElmInstanceEditDto.class.equals(aClass))
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
	    if(obj instanceof DevpSysElmInstanceAddDto){
            this.validateAddDto((DevpSysElmInstanceAddDto)obj, errors);
        }
	}

	/**
     * 验证新增信息
     * @param devpSysElmInstance 系统元素实例
     * @param errors
     */
	public void validateAddDto(DevpSysElmInstanceAddDto devpSysElmInstance, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if (null == devpSysElmInstance.getTid() ) {
			errors.rejectValue("tid", "EMPTY_TID", "租户编号不能为空");
		}
		if(StringUtils.isEmpty(devpSysElmInstance.getName())){
			errors.rejectValue("name", "EMPTY_NAME", "系统元素名称不能为空");
		}
       
		if(StringUtils.isEmpty(devpSysElmInstance.getElmFlag())){
			errors.rejectValue("elmFlag", "EMPTY_ELM_FLAG", "系统元素所属类型标识不能为空");
		}
       
		if (null == devpSysElmInstance.getPrdRid() ) {
			errors.rejectValue("prdRid", "EMPTY_PRD_RID", "产品编号不能为空");
		}
		if (null == devpSysElmInstance.getElmRid() ) {
			errors.rejectValue("elmRid", "EMPTY_ELM_RID", "系统元素编号不能为空");
		}

		//验证长度
		if(StringUtils.length(devpSysElmInstance.getName()) > 255){
			errors.rejectValue("name", null, "系统元素名称最长255个字符");
		}
		if(StringUtils.length(devpSysElmInstance.getCode()) > 255){
			errors.rejectValue("code", null, "系统元素代码最长255个字符");
		}
		if(StringUtils.length(devpSysElmInstance.getAlias()) > 255){
			errors.rejectValue("alias", null, "系统元素别名最长255个字符");
		}
		if(StringUtils.length(devpSysElmInstance.getDescription()) > 255){
			errors.rejectValue("description", null, "系统元素描述最长255个字符");
		}
		if(StringUtils.length(devpSysElmInstance.getElmFlag()) > 255){
			errors.rejectValue("elmFlag", null, "系统元素所属类型标识最长255个字符");
		}
		if(StringUtils.length(devpSysElmInstance.getType()) > 255){
			errors.rejectValue("type", null, "类型最长255个字符");
		}
		if(StringUtils.length(devpSysElmInstance.getSubType()) > 255){
			errors.rejectValue("subType", null, "子类型最长255个字符");
		}
		if(StringUtils.length(devpSysElmInstance.getStereotype()) > 255){
			errors.rejectValue("stereotype", null, "构造型最长255个字符");
		}
		if(StringUtils.length(devpSysElmInstance.getCreateUcode()) > 255){
			errors.rejectValue("createUcode", null, "创建用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysElmInstance.getModifyUcode()) > 255){
			errors.rejectValue("modifyUcode", null, "修改用户代码最长255个字符");
		}
	}
}