package net.aicoder.devp.product.business.sys.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.product.business.sys.domain.DevpSysCmp;
import net.aicoder.devp.product.business.sys.dto.DevpSysCmpAddDto;
import net.aicoder.devp.product.business.sys.dto.DevpSysCmpEditDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class DevpSysCmpValidator implements Validator {

	/**
	 * 判断支持的JavaBean类型
	 * @param aClass
	 * @return
	 */
	@Override
	public boolean supports(Class<?> aClass) {
		if(DevpSysCmpAddDto.class.equals(aClass))
			return true;
		if(DevpSysCmpEditDto.class.equals(aClass))
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
	    if(obj instanceof DevpSysCmpAddDto){
            this.validateDevpSysCmpAddDto((DevpSysCmpAddDto)obj, errors);
        }
	}

	/**
     * 实现Validator中的validate接口
     * @param devpSysCmp 系统组件
     * @param errors
     */
	public void validateDevpSysCmpAddDto(DevpSysCmpAddDto devpSysCmp, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if (null == devpSysCmp.getTid() ) {
			errors.rejectValue(DevpSysCmp.PROPERTY_TID, "EMPTY_"+DevpSysCmp.PROPERTY_TID, "租户编号不能为空");
		}
		if(StringUtils.isEmpty(devpSysCmp.getEtype())){
			errors.rejectValue(DevpSysCmp.PROPERTY_ETYPE, "EMPTY_"+DevpSysCmp.PROPERTY_ETYPE, "元素类型不能为空");
		}
		if(StringUtils.isEmpty(devpSysCmp.getName())){
			errors.rejectValue(DevpSysCmp.PROPERTY_NAME, "EMPTY_"+DevpSysCmp.PROPERTY_NAME, "系统元素名称不能为空");
		}
		if (null == devpSysCmp.getPrdRid() ) {
			errors.rejectValue(DevpSysCmp.PROPERTY_PRD_RID, "EMPTY_"+DevpSysCmp.PROPERTY_PRD_RID, "产品编号不能为空");
		}

		//验证长度
		if(StringUtils.length(devpSysCmp.getEtype()) > 255){
			errors.rejectValue(DevpSysCmp.PROPERTY_ETYPE,null,"元素类型最长255个字符");
		}
		if(StringUtils.length(devpSysCmp.getName()) > 255){
			errors.rejectValue(DevpSysCmp.PROPERTY_NAME,null,"系统元素名称最长255个字符");
		}
		if(StringUtils.length(devpSysCmp.getCode()) > 255){
			errors.rejectValue(DevpSysCmp.PROPERTY_CODE,null,"系统元素代码最长255个字符");
		}
		if(StringUtils.length(devpSysCmp.getAlias()) > 255){
			errors.rejectValue(DevpSysCmp.PROPERTY_ALIAS,null,"系统元素别名最长255个字符");
		}
		if(StringUtils.length(devpSysCmp.getDescription()) > 255){
			errors.rejectValue(DevpSysCmp.PROPERTY_DESCRIPTION,null,"系统元素描述最长255个字符");
		}
		if(StringUtils.length(devpSysCmp.getType()) > 255){
			errors.rejectValue(DevpSysCmp.PROPERTY_TYPE,null,"类型最长255个字符");
		}
		if(StringUtils.length(devpSysCmp.getSubType()) > 255){
			errors.rejectValue(DevpSysCmp.PROPERTY_SUB_TYPE,null,"子类型最长255个字符");
		}
		if(StringUtils.length(devpSysCmp.getStereotype()) > 255){
			errors.rejectValue(DevpSysCmp.PROPERTY_STEREOTYPE,null,"构造型最长255个字符");
		}
		if(StringUtils.length(devpSysCmp.getScope()) > 255){
			errors.rejectValue(DevpSysCmp.PROPERTY_SCOPE,null,"范围最长255个字符");
		}
		if(StringUtils.length(devpSysCmp.getVersion()) > 255){
			errors.rejectValue(DevpSysCmp.PROPERTY_VERSION,null,"版本最长255个字符");
		}
		if(StringUtils.length(devpSysCmp.getPhase()) > 255){
			errors.rejectValue(DevpSysCmp.PROPERTY_PHASE,null,"阶段最长255个字符");
		}
		if(StringUtils.length(devpSysCmp.getStatus()) > 255){
			errors.rejectValue(DevpSysCmp.PROPERTY_STATUS,null,"状态最长255个字符");
		}
		if(StringUtils.length(devpSysCmp.getNotes()) > 255){
			errors.rejectValue(DevpSysCmp.PROPERTY_NOTES,null,"备注最长255个字符");
		}
	}
}