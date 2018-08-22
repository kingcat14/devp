package net.aicoder.maintenance.business.deploy.deploy.valid;


import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.business.deploy.dto.DevpSysDpyResInstAddDto;
import net.aicoder.devp.business.deploy.dto.DevpSysDpyResInstEditDto;
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
            this.validateAddDto((DevpSysDpyResInstAddDto)obj, errors);
        }
	}

	/**
     * 验证新增信息
     * @param devpSysDpyResInst 部署关联资源实例
     * @param errors
     */
	public void validateAddDto(DevpSysDpyResInstAddDto devpSysDpyResInst, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if (null == devpSysDpyResInst.getTid() ) {
			errors.rejectValue("tid", "EMPTY_TID", "租户编号不能为空");
		}
		if(StringUtils.isEmpty(devpSysDpyResInst.getEtype())){
			errors.rejectValue("etype", "EMPTY_ETYPE", "元素类型不能为空");
		}
       
		if (null == devpSysDpyResInst.getPrdRid() ) {
			errors.rejectValue("prdRid", "EMPTY_PRD_RID", "产品编号不能为空");
		}
		if (null == devpSysDpyResInst.getSchemeRid() ) {
			errors.rejectValue("schemeRid", "EMPTY_SCHEME_RID", "部署方案编号不能为空");
		}
		if (null == devpSysDpyResInst.getResRid() ) {
			errors.rejectValue("resRid", "EMPTY_RES_RID", "关联资源编号不能为空");
		}

		//验证长度
		if(StringUtils.length(devpSysDpyResInst.getEtype()) > 255){
			errors.rejectValue("etype", null, "元素类型最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResInst.getName()) > 255){
			errors.rejectValue("name", null, "系统元素名称最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResInst.getCode()) > 255){
			errors.rejectValue("code", null, "系统元素代码最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResInst.getAlias()) > 255){
			errors.rejectValue("alias", null, "系统元素别名最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResInst.getDescription()) > 255){
			errors.rejectValue("description", null, "系统元素描述最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResInst.getFlag()) > 255){
			errors.rejectValue("flag", null, "资源实例标识最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResInst.getType()) > 255){
			errors.rejectValue("type", null, "类型最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResInst.getSubType()) > 255){
			errors.rejectValue("subType", null, "子类型最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResInst.getDpyModel()) > 255){
			errors.rejectValue("dpyModel", null, "部署模式最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResInst.getDpyDescription()) > 255){
			errors.rejectValue("dpyDescription", null, "部署说明最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResInst.getAccessAddr()) > 255){
			errors.rejectValue("accessAddr", null, "访问地址最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResInst.getStatus()) > 255){
			errors.rejectValue("status", null, "状态最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResInst.getNotes()) > 255){
			errors.rejectValue("notes", null, "备注最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResInst.getAssetEtype()) > 255){
			errors.rejectValue("assetEtype", null, "关联IT资产元素类型最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResInst.getAssetTypeCode()) > 255){
			errors.rejectValue("assetTypeCode", null, "关联IT资产类型代码最长255个字符");
		}
	}
}