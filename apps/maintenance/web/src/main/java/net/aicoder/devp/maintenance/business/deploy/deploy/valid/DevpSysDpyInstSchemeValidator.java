package net.aicoder.devp.maintenance.business.deploy.deploy.valid;


import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.deploy.business.deploy.dto.DevpSysDpyInstSchemeAddDto;
import net.aicoder.devp.deploy.business.deploy.dto.DevpSysDpyInstSchemeEditDto;
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
            this.validateAddDto((DevpSysDpyInstSchemeAddDto)obj, errors);
        }
	}

	/**
     * 验证新增信息
     * @param devpSysDpyInstScheme 资源实例所属的部署方案
     * @param errors
     */
	public void validateAddDto(DevpSysDpyInstSchemeAddDto devpSysDpyInstScheme, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if (null == devpSysDpyInstScheme.getTid() ) {
			errors.rejectValue("tid", "EMPTY_TID", "租户编号不能为空");
		}
		if(StringUtils.isEmpty(devpSysDpyInstScheme.getEtype())){
			errors.rejectValue("etype", "EMPTY_ETYPE", "元素类型不能为空");
		}
       
		if(StringUtils.isEmpty(devpSysDpyInstScheme.getName())){
			errors.rejectValue("name", "EMPTY_NAME", "系统元素名称不能为空");
		}
       
		if (null == devpSysDpyInstScheme.getPrdRid() ) {
			errors.rejectValue("prdRid", "EMPTY_PRD_RID", "产品编号不能为空");
		}
		if (null == devpSysDpyInstScheme.getResRid() ) {
			errors.rejectValue("resRid", "EMPTY_RES_RID", "关联资源编号不能为空");
		}
		if (null == devpSysDpyInstScheme.getInstRid() ) {
			errors.rejectValue("instRid", "EMPTY_INST_RID", "关联资源实例编号不能为空");
		}
		if (null == devpSysDpyInstScheme.getSchemeRid() ) {
			errors.rejectValue("schemeRid", "EMPTY_SCHEME_RID", "部署方案编号不能为空");
		}

		//验证长度
		if(StringUtils.length(devpSysDpyInstScheme.getEtype()) > 255){
			errors.rejectValue("etype", null, "元素类型最长255个字符");
		}
		if(StringUtils.length(devpSysDpyInstScheme.getName()) > 255){
			errors.rejectValue("name", null, "系统元素名称最长255个字符");
		}
		if(StringUtils.length(devpSysDpyInstScheme.getCode()) > 255){
			errors.rejectValue("code", null, "系统元素代码最长255个字符");
		}
		if(StringUtils.length(devpSysDpyInstScheme.getAlias()) > 255){
			errors.rejectValue("alias", null, "系统元素别名最长255个字符");
		}
		if(StringUtils.length(devpSysDpyInstScheme.getDescription()) > 255){
			errors.rejectValue("description", null, "系统元素描述最长255个字符");
		}
		if(StringUtils.length(devpSysDpyInstScheme.getType()) > 255){
			errors.rejectValue("type", null, "类型最长255个字符");
		}
		if(StringUtils.length(devpSysDpyInstScheme.getSubType()) > 255){
			errors.rejectValue("subType", null, "子类型最长255个字符");
		}
		if(StringUtils.length(devpSysDpyInstScheme.getStereotype()) > 255){
			errors.rejectValue("stereotype", null, "构造型最长255个字符");
		}
	}
}