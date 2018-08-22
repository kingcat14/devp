package net.aicoder.devp.business.sys.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.business.sys.dto.DevpSysElmInfoDefineAddDto;
import net.aicoder.devp.business.sys.dto.DevpSysElmInfoDefineEditDto;
import net.aicoder.devp.business.sys.domain.DevpSysElmInfoDefine;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class DevpSysElmInfoDefineValidator implements Validator {

	/**
	 * 判断支持的JavaBean类型
	 * @param aClass
	 * @return
	 */
	@Override
	public boolean supports(Class<?> aClass) {
		return true;
	}

	/**
	 * 实现Validator中的validate接口
	 * @param obj
	 * @param errors
	 */
	@Override
	public void validate(Object obj, Errors errors) {
	    if(obj instanceof DevpSysElmInfoDefineAddDto){
            this.validateDevpSysElmInfoDefineAddDto((DevpSysElmInfoDefineAddDto)obj, errors);
        }
	}

	/**
     * 实现Validator中的validate接口
     * @param devpSysElmInfoDefine 系统元素扩充信息
     * @param errors
     */
	public void validateDevpSysElmInfoDefineAddDto(DevpSysElmInfoDefineAddDto devpSysElmInfoDefine, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(devpSysElmInfoDefine.getEtype()) > 255){
			errors.rejectValue(DevpSysElmInfoDefine.PROPERTY_ETYPE,null,"元素类型最长255个字符");
		}
		if(StringUtils.length(devpSysElmInfoDefine.getCode()) > 255){
			errors.rejectValue(DevpSysElmInfoDefine.PROPERTY_CODE,null,"扩展信息代码最长255个字符");
		}
		if(StringUtils.length(devpSysElmInfoDefine.getName()) > 255){
			errors.rejectValue(DevpSysElmInfoDefine.PROPERTY_NAME,null,"扩展信息名称最长255个字符");
		}
		if(StringUtils.length(devpSysElmInfoDefine.getAlias()) > 255){
			errors.rejectValue(DevpSysElmInfoDefine.PROPERTY_ALIAS,null,"扩展信息别名最长255个字符");
		}
		if(StringUtils.length(devpSysElmInfoDefine.getDescription()) > 255){
			errors.rejectValue(DevpSysElmInfoDefine.PROPERTY_DESCRIPTION,null,"扩展信息描述最长255个字符");
		}
		if(StringUtils.length(devpSysElmInfoDefine.getDataType()) > 255){
			errors.rejectValue(DevpSysElmInfoDefine.PROPERTY_DATA_TYPE,null,"数据类型最长255个字符");
		}
		if(StringUtils.length(devpSysElmInfoDefine.getInfoValue()) > 255){
			errors.rejectValue(DevpSysElmInfoDefine.PROPERTY_INFO_VALUE,null,"扩展信息值最长255个字符");
		}
		if(StringUtils.length(devpSysElmInfoDefine.getNotes()) > 255){
			errors.rejectValue(DevpSysElmInfoDefine.PROPERTY_NOTES,null,"备注最长255个字符");
		}
		if(StringUtils.length(devpSysElmInfoDefine.getCreateUcode()) > 255){
			errors.rejectValue(DevpSysElmInfoDefine.PROPERTY_CREATE_UCODE,null,"创建用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysElmInfoDefine.getCreateUname()) > 255){
			errors.rejectValue(DevpSysElmInfoDefine.PROPERTY_CREATE_UNAME,null,"创建用户姓名最长255个字符");
		}
		if(StringUtils.length(devpSysElmInfoDefine.getModifyUcode()) > 255){
			errors.rejectValue(DevpSysElmInfoDefine.PROPERTY_MODIFY_UCODE,null,"修改用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysElmInfoDefine.getModifyUname()) > 255){
			errors.rejectValue(DevpSysElmInfoDefine.PROPERTY_MODIFY_UNAME,null,"修改用户姓名最长255个字符");
		}
	}
}