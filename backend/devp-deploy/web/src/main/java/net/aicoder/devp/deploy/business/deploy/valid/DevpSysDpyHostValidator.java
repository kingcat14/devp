package net.aicoder.devp.deploy.business.deploy.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.deploy.business.deploy.dto.DevpSysDpyHostAddDto;
import net.aicoder.devp.deploy.business.deploy.dto.DevpSysDpyHostEditDto;
import net.aicoder.devp.deploy.business.deploy.domain.DevpSysDpyHost;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class DevpSysDpyHostValidator implements Validator {

	/**
	 * 判断支持的JavaBean类型
	 * @param aClass
	 * @return
	 */
	@Override
	public boolean supports(Class<?> aClass) {
		if(DevpSysDpyHostAddDto.class.equals(aClass))
			return true;
		if(DevpSysDpyHostEditDto.class.equals(aClass))
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
	    if(obj instanceof DevpSysDpyHostAddDto){
            this.validateDevpSysDpyHostAddDto((DevpSysDpyHostAddDto)obj, errors);
        }
	}

	/**
     * 实现Validator中的validate接口
     * @param devpSysDpyHost 部署主机节点
     * @param errors
     */
	public void validateDevpSysDpyHostAddDto(DevpSysDpyHostAddDto devpSysDpyHost, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if (null == devpSysDpyHost.getTid() ) {
			errors.rejectValue(DevpSysDpyHost.PROPERTY_TID, "EMPTY_"+DevpSysDpyHost.PROPERTY_TID, "租户编号不能为空");
		}
		if(StringUtils.isEmpty(devpSysDpyHost.getEtype())){
			errors.rejectValue(DevpSysDpyHost.PROPERTY_ETYPE, "EMPTY_"+DevpSysDpyHost.PROPERTY_ETYPE, "元素类型不能为空");
		}
		if(StringUtils.isEmpty(devpSysDpyHost.getFlag())){
			errors.rejectValue(DevpSysDpyHost.PROPERTY_FLAG, "EMPTY_"+DevpSysDpyHost.PROPERTY_FLAG, "主机标识不能为空");
		}
		if (null == devpSysDpyHost.getPrdRid() ) {
			errors.rejectValue(DevpSysDpyHost.PROPERTY_PRD_RID, "EMPTY_"+DevpSysDpyHost.PROPERTY_PRD_RID, "产品编号不能为空");
		}
		if (null == devpSysDpyHost.getSchemeRid() ) {
			errors.rejectValue(DevpSysDpyHost.PROPERTY_SCHEME_RID, "EMPTY_"+DevpSysDpyHost.PROPERTY_SCHEME_RID, "部署方案编号不能为空");
		}
		if (null == devpSysDpyHost.getAssetRid() ) {
			errors.rejectValue(DevpSysDpyHost.PROPERTY_ASSET_RID, "EMPTY_"+DevpSysDpyHost.PROPERTY_ASSET_RID, "关联IT资产编号不能为空");
		}
		if(StringUtils.isEmpty(devpSysDpyHost.getAssetEtype())){
			errors.rejectValue(DevpSysDpyHost.PROPERTY_ASSET_ETYPE, "EMPTY_"+DevpSysDpyHost.PROPERTY_ASSET_ETYPE, "关联IT资产元素类型不能为空");
		}
		if(StringUtils.isEmpty(devpSysDpyHost.getAssetTypeCode())){
			errors.rejectValue(DevpSysDpyHost.PROPERTY_ASSET_TYPE_CODE, "EMPTY_"+DevpSysDpyHost.PROPERTY_ASSET_TYPE_CODE, "关联IT资产类型代码不能为空");
		}

		//验证长度
		if(StringUtils.length(devpSysDpyHost.getEtype()) > 255){
			errors.rejectValue(DevpSysDpyHost.PROPERTY_ETYPE,null,"元素类型最长255个字符");
		}
		if(StringUtils.length(devpSysDpyHost.getName()) > 255){
			errors.rejectValue(DevpSysDpyHost.PROPERTY_NAME,null,"系统元素名称最长255个字符");
		}
		if(StringUtils.length(devpSysDpyHost.getCode()) > 255){
			errors.rejectValue(DevpSysDpyHost.PROPERTY_CODE,null,"系统元素代码最长255个字符");
		}
		if(StringUtils.length(devpSysDpyHost.getAlias()) > 255){
			errors.rejectValue(DevpSysDpyHost.PROPERTY_ALIAS,null,"系统元素别名最长255个字符");
		}
		if(StringUtils.length(devpSysDpyHost.getDescription()) > 255){
			errors.rejectValue(DevpSysDpyHost.PROPERTY_DESCRIPTION,null,"系统元素描述最长255个字符");
		}
		if(StringUtils.length(devpSysDpyHost.getFlag()) > 255){
			errors.rejectValue(DevpSysDpyHost.PROPERTY_FLAG,null,"主机标识最长255个字符");
		}
		if(StringUtils.length(devpSysDpyHost.getType()) > 255){
			errors.rejectValue(DevpSysDpyHost.PROPERTY_TYPE,null,"类型最长255个字符");
		}
		if(StringUtils.length(devpSysDpyHost.getSubType()) > 255){
			errors.rejectValue(DevpSysDpyHost.PROPERTY_SUB_TYPE,null,"子类型最长255个字符");
		}
		if(StringUtils.length(devpSysDpyHost.getStatus()) > 255){
			errors.rejectValue(DevpSysDpyHost.PROPERTY_STATUS,null,"状态最长255个字符");
		}
		if(StringUtils.length(devpSysDpyHost.getNotes()) > 255){
			errors.rejectValue(DevpSysDpyHost.PROPERTY_NOTES,null,"备注最长255个字符");
		}
		if(StringUtils.length(devpSysDpyHost.getAssetEtype()) > 255){
			errors.rejectValue(DevpSysDpyHost.PROPERTY_ASSET_ETYPE,null,"关联IT资产元素类型最长255个字符");
		}
		if(StringUtils.length(devpSysDpyHost.getAssetTypeCode()) > 255){
			errors.rejectValue(DevpSysDpyHost.PROPERTY_ASSET_TYPE_CODE,null,"关联IT资产类型代码最长255个字符");
		}
	}
}