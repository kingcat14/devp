package net.aicoder.devp.product.business.sys.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.product.business.sys.domain.DevpSysCmpModule;
import net.aicoder.devp.product.business.sys.dto.DevpSysCmpModuleAddDto;
import net.aicoder.devp.product.business.sys.dto.DevpSysCmpModuleEditDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class DevpSysCmpModuleValidator implements Validator {

	/**
	 * 判断支持的JavaBean类型
	 * @param aClass
	 * @return
	 */
	@Override
	public boolean supports(Class<?> aClass) {
		if(DevpSysCmpModuleAddDto.class.equals(aClass))
			return true;
		if(DevpSysCmpModuleEditDto.class.equals(aClass))
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
	    if(obj instanceof DevpSysCmpModuleAddDto){
            this.validateDevpSysCmpModuleAddDto((DevpSysCmpModuleAddDto)obj, errors);
        }
	}

	/**
     * 实现Validator中的validate接口
     * @param devpSysCmpModule 组件对应模块
     * @param errors
     */
	public void validateDevpSysCmpModuleAddDto(DevpSysCmpModuleAddDto devpSysCmpModule, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if (null == devpSysCmpModule.getTid() ) {
			errors.rejectValue(DevpSysCmpModule.PROPERTY_TID, "EMPTY_"+DevpSysCmpModule.PROPERTY_TID, "租户编号不能为空");
		}
		if(StringUtils.isEmpty(devpSysCmpModule.getEtype())){
			errors.rejectValue(DevpSysCmpModule.PROPERTY_ETYPE, "EMPTY_"+DevpSysCmpModule.PROPERTY_ETYPE, "元素类型不能为空");
		}
		if (null == devpSysCmpModule.getPrdRid() ) {
			errors.rejectValue(DevpSysCmpModule.PROPERTY_PRD_RID, "EMPTY_"+DevpSysCmpModule.PROPERTY_PRD_RID, "产品编号不能为空");
		}
		if (null == devpSysCmpModule.getCmpRid() ) {
			errors.rejectValue(DevpSysCmpModule.PROPERTY_CMP_RID, "EMPTY_"+DevpSysCmpModule.PROPERTY_CMP_RID, "组件编号不能为空");
		}
		if (null == devpSysCmpModule.getMduRid() ) {
			errors.rejectValue(DevpSysCmpModule.PROPERTY_MDU_RID, "EMPTY_"+DevpSysCmpModule.PROPERTY_MDU_RID, "模块编号不能为空");
		}

		//验证长度
		if(StringUtils.length(devpSysCmpModule.getEtype()) > 255){
			errors.rejectValue(DevpSysCmpModule.PROPERTY_ETYPE,null,"元素类型最长255个字符");
		}
		if(StringUtils.length(devpSysCmpModule.getName()) > 255){
			errors.rejectValue(DevpSysCmpModule.PROPERTY_NAME,null,"对应关系名称最长255个字符");
		}
		if(StringUtils.length(devpSysCmpModule.getCode()) > 255){
			errors.rejectValue(DevpSysCmpModule.PROPERTY_CODE,null,"对应关系代码最长255个字符");
		}
		if(StringUtils.length(devpSysCmpModule.getAlias()) > 255){
			errors.rejectValue(DevpSysCmpModule.PROPERTY_ALIAS,null,"对应关系别名最长255个字符");
		}
		if(StringUtils.length(devpSysCmpModule.getDescription()) > 255){
			errors.rejectValue(DevpSysCmpModule.PROPERTY_DESCRIPTION,null,"对应关系描述最长255个字符");
		}
		if(StringUtils.length(devpSysCmpModule.getType()) > 255){
			errors.rejectValue(DevpSysCmpModule.PROPERTY_TYPE,null,"类型最长255个字符");
		}
		if(StringUtils.length(devpSysCmpModule.getSubType()) > 255){
			errors.rejectValue(DevpSysCmpModule.PROPERTY_SUB_TYPE,null,"子类型最长255个字符");
		}
	}
}