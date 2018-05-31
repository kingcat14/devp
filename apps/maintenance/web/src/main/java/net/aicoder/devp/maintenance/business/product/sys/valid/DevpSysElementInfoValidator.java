package net.aicoder.devp.maintenance.business.product.sys.valid;


import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.product.business.sys.dto.DevpSysElementInfoAddDto;
import net.aicoder.devp.product.business.sys.dto.DevpSysElementInfoEditDto;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class DevpSysElementInfoValidator implements Validator {

	/**
	 * 判断支持的JavaBean类型
	 * @param aClass
	 * @return
	 */
	@Override
	public boolean supports(Class<?> aClass) {
		if(DevpSysElementInfoAddDto.class.equals(aClass))
			return true;
		if(DevpSysElementInfoEditDto.class.equals(aClass))
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
	    if(obj instanceof DevpSysElementInfoAddDto){
            this.validateAddDto((DevpSysElementInfoAddDto)obj, errors);
        }
	}

	/**
     * 验证新增信息
     * @param devpSysElementInfo 系统元素扩充信息
     * @param errors
     */
	public void validateAddDto(DevpSysElementInfoAddDto devpSysElementInfo, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if (null == devpSysElementInfo.getTid() ) {
			errors.rejectValue("tid", "EMPTY_TID", "租户编号不能为空");
		}
		if(StringUtils.isEmpty(devpSysElementInfo.getCode())){
			errors.rejectValue("code", "EMPTY_CODE", "扩展信息代码不能为空");
		}
       
		if (null == devpSysElementInfo.getPrdRid() ) {
			errors.rejectValue("prdRid", "EMPTY_PRD_RID", "产品编号不能为空");
		}
		if (null == devpSysElementInfo.getElmRid() ) {
			errors.rejectValue("elmRid", "EMPTY_ELM_RID", "系统元素编号不能为空");
		}
		if (null == devpSysElementInfo.getInstRid() ) {
			errors.rejectValue("instRid", "EMPTY_INST_RID", "系统元素实例编号不能为空");
		}

		//验证长度
		if(StringUtils.length(devpSysElementInfo.getCode()) > 255){
			errors.rejectValue("code", null, "扩展信息代码最长255个字符");
		}
		if(StringUtils.length(devpSysElementInfo.getName()) > 255){
			errors.rejectValue("name", null, "扩展信息名称最长255个字符");
		}
		if(StringUtils.length(devpSysElementInfo.getAlias()) > 255){
			errors.rejectValue("alias", null, "扩展信息别名最长255个字符");
		}
		if(StringUtils.length(devpSysElementInfo.getDescription()) > 255){
			errors.rejectValue("description", null, "扩展信息描述最长255个字符");
		}
		if(StringUtils.length(devpSysElementInfo.getInfoValue1()) > 255){
			errors.rejectValue("infoValue1", null, "信息值1最长255个字符");
		}
		if(StringUtils.length(devpSysElementInfo.getInfoValue2()) > 255){
			errors.rejectValue("infoValue2", null, "信息值2最长255个字符");
		}
		if(StringUtils.length(devpSysElementInfo.getInfoValue3()) > 255){
			errors.rejectValue("infoValue3", null, "信息值3最长255个字符");
		}
		if(StringUtils.length(devpSysElementInfo.getInfoValue4()) > 255){
			errors.rejectValue("infoValue4", null, "信息值4最长255个字符");
		}
		if(StringUtils.length(devpSysElementInfo.getInfoValue5()) > 255){
			errors.rejectValue("infoValue5", null, "信息值5最长255个字符");
		}
		if(StringUtils.length(devpSysElementInfo.getNotes()) > 255){
			errors.rejectValue("notes", null, "备注最长255个字符");
		}
		if(StringUtils.length(devpSysElementInfo.getCreateUcode()) > 255){
			errors.rejectValue("createUcode", null, "创建用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysElementInfo.getModifyUcode()) > 255){
			errors.rejectValue("modifyUcode", null, "修改用户代码最长255个字符");
		}
	}
}