package net.aicoder.maintenance.business.deploy.deploy.valid;


import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.business.deploy.dto.DevpSysDpyHostAddDto;
import net.aicoder.devp.business.deploy.dto.DevpSysDpyHostCondition;
import net.aicoder.devp.business.deploy.dto.DevpSysDpyHostEditDto;
import org.springframework.data.domain.Sort;
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
            this.validateAddDto((DevpSysDpyHostAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
        	this.validateSearchDto((PageSearchRequest)obj);
		}
	}

	public void validateSearchDto(PageSearchRequest<DevpSysDpyHostCondition> search){
		if(search.getSearchCondition() == null){
			search.setSearchCondition(new DevpSysDpyHostCondition());
		}
	}

	/**
     * 验证新增信息
     * @param devpSysDpyHost 部署主机节点
     * @param errors
     */
	public void validateAddDto(DevpSysDpyHostAddDto devpSysDpyHost, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if (null == devpSysDpyHost.getTid() ) {
			errors.rejectValue("tid", "EMPTY_TID", "租户编号不能为空");
		}
		if(StringUtils.isEmpty(devpSysDpyHost.getEtype())){
			errors.rejectValue("etype", "EMPTY_ETYPE", "元素类型不能为空");
		}
       
		if(StringUtils.isEmpty(devpSysDpyHost.getFlag())){
			errors.rejectValue("flag", "EMPTY_FLAG", "主机标识不能为空");
		}
       
		if (null == devpSysDpyHost.getPrdRid() ) {
			errors.rejectValue("prdRid", "EMPTY_PRD_RID", "产品编号不能为空");
		}
		if (null == devpSysDpyHost.getSchemeRid() ) {
			errors.rejectValue("schemeRid", "EMPTY_SCHEME_RID", "部署方案编号不能为空");
		}
		if (null == devpSysDpyHost.getAssetRid() ) {
			errors.rejectValue("assetRid", "EMPTY_ASSET_RID", "关联IT资产编号不能为空");
		}
		if(StringUtils.isEmpty(devpSysDpyHost.getAssetEtype())){
			errors.rejectValue("assetEtype", "EMPTY_ASSET_ETYPE", "关联IT资产元素类型不能为空");
		}
       
		if(StringUtils.isEmpty(devpSysDpyHost.getAssetTypeCode())){
			errors.rejectValue("assetTypeCode", "EMPTY_ASSET_TYPE_CODE", "关联IT资产类型代码不能为空");
		}
       

		//验证长度
		if(StringUtils.length(devpSysDpyHost.getEtype()) > 255){
			errors.rejectValue("etype", null, "元素类型最长255个字符");
		}
		if(StringUtils.length(devpSysDpyHost.getName()) > 255){
			errors.rejectValue("name", null, "系统元素名称最长255个字符");
		}
		if(StringUtils.length(devpSysDpyHost.getCode()) > 255){
			errors.rejectValue("code", null, "系统元素代码最长255个字符");
		}
		if(StringUtils.length(devpSysDpyHost.getAlias()) > 255){
			errors.rejectValue("alias", null, "系统元素别名最长255个字符");
		}
		if(StringUtils.length(devpSysDpyHost.getDescription()) > 255){
			errors.rejectValue("description", null, "系统元素描述最长255个字符");
		}
		if(StringUtils.length(devpSysDpyHost.getFlag()) > 255){
			errors.rejectValue("flag", null, "主机标识最长255个字符");
		}
		if(StringUtils.length(devpSysDpyHost.getType()) > 255){
			errors.rejectValue("type", null, "类型最长255个字符");
		}
		if(StringUtils.length(devpSysDpyHost.getSubType()) > 255){
			errors.rejectValue("subType", null, "子类型最长255个字符");
		}
		if(StringUtils.length(devpSysDpyHost.getStatus()) > 255){
			errors.rejectValue("status", null, "状态最长255个字符");
		}
		if(StringUtils.length(devpSysDpyHost.getNotes()) > 255){
			errors.rejectValue("notes", null, "备注最长255个字符");
		}
		if(StringUtils.length(devpSysDpyHost.getAssetEtype()) > 255){
			errors.rejectValue("assetEtype", null, "关联IT资产元素类型最长255个字符");
		}
		if(StringUtils.length(devpSysDpyHost.getAssetTypeCode()) > 255){
			errors.rejectValue("assetTypeCode", null, "关联IT资产类型代码最长255个字符");
		}
	}
}