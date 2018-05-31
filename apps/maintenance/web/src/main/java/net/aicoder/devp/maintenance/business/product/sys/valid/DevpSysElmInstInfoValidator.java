package net.aicoder.devp.maintenance.business.product.sys.valid;


import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.product.business.sys.dto.DevpSysElmInstInfoAddDto;
import net.aicoder.devp.product.business.sys.dto.DevpSysElmInstInfoEditDto;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class DevpSysElmInstInfoValidator implements Validator {

	/**
	 * 判断支持的JavaBean类型
	 * @param aClass
	 * @return
	 */
	@Override
	public boolean supports(Class<?> aClass) {
		if(DevpSysElmInstInfoAddDto.class.equals(aClass))
			return true;
		if(DevpSysElmInstInfoEditDto.class.equals(aClass))
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
	    if(obj instanceof DevpSysElmInstInfoAddDto){
            this.validateAddDto((DevpSysElmInstInfoAddDto)obj, errors);
        }
	}

	/**
     * 验证新增信息
     * @param devpSysElmInstInfo 系统元素实例
     * @param errors
     */
	public void validateAddDto(DevpSysElmInstInfoAddDto devpSysElmInstInfo, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if (null == devpSysElmInstInfo.getTid() ) {
			errors.rejectValue("tid", "EMPTY_TID", "租户编号不能为空");
		}
		if(StringUtils.isEmpty(devpSysElmInstInfo.getCode())){
			errors.rejectValue("code", "EMPTY_CODE", "扩展信息代码不能为空");
		}
       
		if (null == devpSysElmInstInfo.getPrdRid() ) {
			errors.rejectValue("prdRid", "EMPTY_PRD_RID", "产品编号不能为空");
		}
		if (null == devpSysElmInstInfo.getContRid() ) {
			errors.rejectValue("contRid", "EMPTY_CONT_RID", "关联连接编号不能为空");
		}
		if (null == devpSysElmInstInfo.getSprdRid() ) {
			errors.rejectValue("sprdRid", "EMPTY_SPRD_RID", "来源产品编号不能为空");
		}
		if (null == devpSysElmInstInfo.getSelmRid() ) {
			errors.rejectValue("selmRid", "EMPTY_SELM_RID", "来源系统元素编号不能为空");
		}
		if (null == devpSysElmInstInfo.getDprdRid() ) {
			errors.rejectValue("dprdRid", "EMPTY_DPRD_RID", "目标产品编号不能为空");
		}
		if (null == devpSysElmInstInfo.getDelmRid() ) {
			errors.rejectValue("delmRid", "EMPTY_DELM_RID", "目标系统元素编号不能为空");
		}
		if (null == devpSysElmInstInfo.getSinstRid() ) {
			errors.rejectValue("sinstRid", "EMPTY_SINST_RID", "来源系统元素实例编号不能为空");
		}
		if (null == devpSysElmInstInfo.getDinstRid() ) {
			errors.rejectValue("dinstRid", "EMPTY_DINST_RID", "目标系统元素实例编号不能为空");
		}

		//验证长度
		if(StringUtils.length(devpSysElmInstInfo.getCode()) > 255){
			errors.rejectValue("code", null, "扩展信息代码最长255个字符");
		}
		if(StringUtils.length(devpSysElmInstInfo.getName()) > 255){
			errors.rejectValue("name", null, "扩展信息名称最长255个字符");
		}
		if(StringUtils.length(devpSysElmInstInfo.getAlias()) > 255){
			errors.rejectValue("alias", null, "扩展信息别名最长255个字符");
		}
		if(StringUtils.length(devpSysElmInstInfo.getDescription()) > 255){
			errors.rejectValue("description", null, "扩展信息描述最长255个字符");
		}
		if(StringUtils.length(devpSysElmInstInfo.getType()) > 255){
			errors.rejectValue("type", null, "设值方式最长255个字符");
		}
		if(StringUtils.length(devpSysElmInstInfo.getInfoValue1()) > 255){
			errors.rejectValue("infoValue1", null, "信息值1最长255个字符");
		}
		if(StringUtils.length(devpSysElmInstInfo.getInfoValue2()) > 255){
			errors.rejectValue("infoValue2", null, "信息值2最长255个字符");
		}
		if(StringUtils.length(devpSysElmInstInfo.getInfoValue3()) > 255){
			errors.rejectValue("infoValue3", null, "信息值3最长255个字符");
		}
		if(StringUtils.length(devpSysElmInstInfo.getInfoValue4()) > 255){
			errors.rejectValue("infoValue4", null, "信息值4最长255个字符");
		}
		if(StringUtils.length(devpSysElmInstInfo.getInfoValue5()) > 255){
			errors.rejectValue("infoValue5", null, "信息值5最长255个字符");
		}
		if(StringUtils.length(devpSysElmInstInfo.getNotes()) > 255){
			errors.rejectValue("notes", null, "备注最长255个字符");
		}
		if(StringUtils.length(devpSysElmInstInfo.getCreateUcode()) > 255){
			errors.rejectValue("createUcode", null, "创建用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysElmInstInfo.getModifyUcode()) > 255){
			errors.rejectValue("modifyUcode", null, "修改用户代码最长255个字符");
		}
	}
}