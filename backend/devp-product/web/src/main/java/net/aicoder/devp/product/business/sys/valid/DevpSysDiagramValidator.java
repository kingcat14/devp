package net.aicoder.devp.product.business.sys.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.product.business.sys.dto.DevpSysDiagramAddDto;
import net.aicoder.devp.product.business.sys.dto.DevpSysDiagramEditDto;
import net.aicoder.devp.product.business.sys.domain.DevpSysDiagram;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class DevpSysDiagramValidator implements Validator {

	/**
	 * 判断支持的JavaBean类型
	 * @param aClass
	 * @return
	 */
	@Override
	public boolean supports(Class<?> aClass) {
		if(DevpSysDiagramAddDto.class.equals(aClass))
			return true;
		if(DevpSysDiagramEditDto.class.equals(aClass))
			return true;
		if(PageSearchRequest.class.equals(aClass))
			return true;
		return DevpSysDiagram.class.equals(aClass);
	}

	/**
	 * 实现Validator中的validate接口
	 * @param obj
	 * @param errors
	 */
	@Override
	public void validate(Object obj, Errors errors) {
	    if(obj instanceof DevpSysDiagram){
            this.validateDevpSysDiagram((DevpSysDiagram)obj, errors);
        }
	}

	/**
     * 实现Validator中的validate接口
     * @param devpSysDiagram UML图
     * @param errors
     */
	public void validateDevpSysDiagram(DevpSysDiagram devpSysDiagram, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if (null == devpSysDiagram.getTid() ) {
			errors.rejectValue(DevpSysDiagram.PROPERTY_TID, "EMPTY_"+DevpSysDiagram.PROPERTY_TID, "租户编号不能为空");
		}
		if(StringUtils.isEmpty(devpSysDiagram.getName())){
			errors.rejectValue(DevpSysDiagram.PROPERTY_NAME, "EMPTY_"+DevpSysDiagram.PROPERTY_NAME, "系统元素名称不能为空");
		}
		if(StringUtils.isEmpty(devpSysDiagram.getDgmFlag())){
			errors.rejectValue(DevpSysDiagram.PROPERTY_DGM_FLAG, "EMPTY_"+DevpSysDiagram.PROPERTY_DGM_FLAG, "系统元素所属类型标识不能为空");
		}
		if (null == devpSysDiagram.getPrdRid() ) {
			errors.rejectValue(DevpSysDiagram.PROPERTY_PRD_RID, "EMPTY_"+DevpSysDiagram.PROPERTY_PRD_RID, "产品编号不能为空");
		}
		if (null == devpSysDiagram.getElmRid() ) {
			errors.rejectValue(DevpSysDiagram.PROPERTY_ELM_RID, "EMPTY_"+DevpSysDiagram.PROPERTY_ELM_RID, "系统元素编号不能为空");
		}

		//验证长度
		if(StringUtils.length(devpSysDiagram.getName()) > 255){
			errors.rejectValue(DevpSysDiagram.PROPERTY_NAME,null,"系统元素名称最长255个字符");
		}
		if(StringUtils.length(devpSysDiagram.getCode()) > 255){
			errors.rejectValue(DevpSysDiagram.PROPERTY_CODE,null,"系统元素代码最长255个字符");
		}
		if(StringUtils.length(devpSysDiagram.getAlias()) > 255){
			errors.rejectValue(DevpSysDiagram.PROPERTY_ALIAS,null,"系统元素别名最长255个字符");
		}
		if(StringUtils.length(devpSysDiagram.getDescription()) > 255){
			errors.rejectValue(DevpSysDiagram.PROPERTY_DESCRIPTION,null,"系统元素描述最长255个字符");
		}
		if(StringUtils.length(devpSysDiagram.getDgmFlag()) > 255){
			errors.rejectValue(DevpSysDiagram.PROPERTY_DGM_FLAG,null,"系统元素所属类型标识最长255个字符");
		}
		if(StringUtils.length(devpSysDiagram.getType()) > 255){
			errors.rejectValue(DevpSysDiagram.PROPERTY_TYPE,null,"类型最长255个字符");
		}
		if(StringUtils.length(devpSysDiagram.getSubType()) > 255){
			errors.rejectValue(DevpSysDiagram.PROPERTY_SUB_TYPE,null,"子类型最长255个字符");
		}
		if(StringUtils.length(devpSysDiagram.getStereotype()) > 255){
			errors.rejectValue(DevpSysDiagram.PROPERTY_STEREOTYPE,null,"构造型最长255个字符");
		}
		if(StringUtils.length(devpSysDiagram.getScope()) > 255){
			errors.rejectValue(DevpSysDiagram.PROPERTY_SCOPE,null,"范围最长255个字符");
		}
		if(StringUtils.length(devpSysDiagram.getVersion()) > 255){
			errors.rejectValue(DevpSysDiagram.PROPERTY_VERSION,null,"版本最长255个字符");
		}
		if(StringUtils.length(devpSysDiagram.getPhase()) > 255){
			errors.rejectValue(DevpSysDiagram.PROPERTY_PHASE,null,"阶段最长255个字符");
		}
		if(StringUtils.length(devpSysDiagram.getStatus()) > 255){
			errors.rejectValue(DevpSysDiagram.PROPERTY_STATUS,null,"状态最长255个字符");
		}
		if(StringUtils.length(devpSysDiagram.getCreateUcode()) > 255){
			errors.rejectValue(DevpSysDiagram.PROPERTY_CREATE_UCODE,null,"创建用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysDiagram.getModifyUcode()) > 255){
			errors.rejectValue(DevpSysDiagram.PROPERTY_MODIFY_UCODE,null,"修改用户代码最长255个字符");
		}
	}
}