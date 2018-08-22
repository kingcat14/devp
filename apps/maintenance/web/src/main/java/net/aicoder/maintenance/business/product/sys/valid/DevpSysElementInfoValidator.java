package net.aicoder.maintenance.business.product.sys.valid;


import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.business.sys.dto.DevpSysElementInfoAddDto;
import net.aicoder.devp.business.sys.dto.DevpSysElementInfoEditDto;
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
		if(StringUtils.isEmpty(devpSysElementInfo.getEtype())){
			errors.rejectValue("etype", "EMPTY_ETYPE", "元素类型不能为空");
		}
       
		if(StringUtils.isEmpty(devpSysElementInfo.getCode())){
			errors.rejectValue("code", "EMPTY_CODE", "扩展信息代码不能为空");
		}
       
		if (null == devpSysElementInfo.getObjRid() ) {
			errors.rejectValue("objRid", "EMPTY_OBJ_RID", "元素编号不能为空");
		}

		//验证长度
		if(StringUtils.length(devpSysElementInfo.getEtype()) > 255){
			errors.rejectValue("etype", null, "元素类型最长255个字符");
		}
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
		if(StringUtils.length(devpSysElementInfo.getDataType()) > 255){
			errors.rejectValue("dataType", null, "数据类型最长255个字符");
		}
		if(StringUtils.length(devpSysElementInfo.getInfoValue()) > 255){
			errors.rejectValue("infoValue", null, "扩展信息值最长255个字符");
		}
		if(StringUtils.length(devpSysElementInfo.getNotes()) > 255){
			errors.rejectValue("notes", null, "备注最长255个字符");
		}
	}
}