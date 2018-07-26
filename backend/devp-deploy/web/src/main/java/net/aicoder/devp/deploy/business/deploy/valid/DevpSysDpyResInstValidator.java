package net.aicoder.devp.deploy.business.deploy.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.deploy.business.deploy.dto.DevpSysDpyResInstAddDto;
import net.aicoder.devp.deploy.business.deploy.dto.DevpSysDpyResInstEditDto;
import net.aicoder.devp.deploy.business.deploy.domain.DevpSysDpyResInst;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class DevpSysDpyResInstValidator implements Validator {

	/**
	 * 判断支持的JavaBean类型
	 * @param aClass
	 * @return
	 */
	@Override
	public boolean supports(Class<?> aClass) {
		if(DevpSysDpyResInstAddDto.class.equals(aClass))
			return true;
		if(DevpSysDpyResInstEditDto.class.equals(aClass))
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
	    if(obj instanceof DevpSysDpyResInstAddDto){
            this.validateDevpSysDpyResInstAddDto((DevpSysDpyResInstAddDto)obj, errors);
        }
	}

	/**
     * 实现Validator中的validate接口
     * @param devpSysDpyResInst 部署关联资源实例定义
     * @param errors
     */
	public void validateDevpSysDpyResInstAddDto(DevpSysDpyResInstAddDto devpSysDpyResInst, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if (null == devpSysDpyResInst.getTid() ) {
			errors.rejectValue(DevpSysDpyResInst.PROPERTY_TID, "EMPTY_"+DevpSysDpyResInst.PROPERTY_TID, "租户编号不能为空");
		}
		if(StringUtils.isEmpty(devpSysDpyResInst.getEtype())){
			errors.rejectValue(DevpSysDpyResInst.PROPERTY_ETYPE, "EMPTY_"+DevpSysDpyResInst.PROPERTY_ETYPE, "元素类型不能为空");
		}
		if(StringUtils.isEmpty(devpSysDpyResInst.getName())){
			errors.rejectValue(DevpSysDpyResInst.PROPERTY_NAME, "EMPTY_"+DevpSysDpyResInst.PROPERTY_NAME, "系统元素名称不能为空");
		}
		if (null == devpSysDpyResInst.getPrdRid() ) {
			errors.rejectValue(DevpSysDpyResInst.PROPERTY_PRD_RID, "EMPTY_"+DevpSysDpyResInst.PROPERTY_PRD_RID, "产品编号不能为空");
		}
		if (null == devpSysDpyResInst.getResRid() ) {
			errors.rejectValue(DevpSysDpyResInst.PROPERTY_RES_RID, "EMPTY_"+DevpSysDpyResInst.PROPERTY_RES_RID, "关联资源编号不能为空");
		}

		//验证长度
		if(StringUtils.length(devpSysDpyResInst.getEtype()) > 255){
			errors.rejectValue(DevpSysDpyResInst.PROPERTY_ETYPE,null,"元素类型最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResInst.getName()) > 255){
			errors.rejectValue(DevpSysDpyResInst.PROPERTY_NAME,null,"系统元素名称最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResInst.getCode()) > 255){
			errors.rejectValue(DevpSysDpyResInst.PROPERTY_CODE,null,"系统元素代码最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResInst.getAlias()) > 255){
			errors.rejectValue(DevpSysDpyResInst.PROPERTY_ALIAS,null,"系统元素别名最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResInst.getDescription()) > 255){
			errors.rejectValue(DevpSysDpyResInst.PROPERTY_DESCRIPTION,null,"系统元素描述最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResInst.getType()) > 255){
			errors.rejectValue(DevpSysDpyResInst.PROPERTY_TYPE,null,"类型最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResInst.getSubType()) > 255){
			errors.rejectValue(DevpSysDpyResInst.PROPERTY_SUB_TYPE,null,"子类型最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResInst.getStereotype()) > 255){
			errors.rejectValue(DevpSysDpyResInst.PROPERTY_STEREOTYPE,null,"构造型最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResInst.getScope()) > 255){
			errors.rejectValue(DevpSysDpyResInst.PROPERTY_SCOPE,null,"范围最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResInst.getVersion()) > 255){
			errors.rejectValue(DevpSysDpyResInst.PROPERTY_VERSION,null,"版本最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResInst.getPhase()) > 255){
			errors.rejectValue(DevpSysDpyResInst.PROPERTY_PHASE,null,"阶段最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResInst.getStatus()) > 255){
			errors.rejectValue(DevpSysDpyResInst.PROPERTY_STATUS,null,"状态最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResInst.getNotes()) > 255){
			errors.rejectValue(DevpSysDpyResInst.PROPERTY_NOTES,null,"备注最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResInst.getAssetEtype()) > 255){
			errors.rejectValue(DevpSysDpyResInst.PROPERTY_ASSET_ETYPE,null,"关联IT资产元素类型最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResInst.getAssetTypeCode()) > 255){
			errors.rejectValue(DevpSysDpyResInst.PROPERTY_ASSET_TYPE_CODE,null,"关联IT资产类型代码最长255个字符");
		}
	}
}