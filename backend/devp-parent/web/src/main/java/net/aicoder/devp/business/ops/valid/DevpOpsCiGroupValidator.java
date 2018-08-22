package net.aicoder.devp.business.ops.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.business.ops.dto.DevpOpsCiGroupAddDto;
import net.aicoder.devp.business.ops.dto.DevpOpsCiGroupEditDto;
import net.aicoder.devp.business.ops.domain.DevpOpsCiGroup;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class DevpOpsCiGroupValidator implements Validator {

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
	    if(obj instanceof DevpOpsCiGroupAddDto){
            this.validateDevpOpsCiGroupAddDto((DevpOpsCiGroupAddDto)obj, errors);
        }
	}

	/**
     * 实现Validator中的validate接口
     * @param devpOpsCiGroup 资产项目分组
     * @param errors
     */
	public void validateDevpOpsCiGroupAddDto(DevpOpsCiGroupAddDto devpOpsCiGroup, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(devpOpsCiGroup.getEtype()) > 255){
			errors.rejectValue(DevpOpsCiGroup.PROPERTY_ETYPE,null,"元素类型最长255个字符");
		}
		if(StringUtils.length(devpOpsCiGroup.getName()) > 255){
			errors.rejectValue(DevpOpsCiGroup.PROPERTY_NAME,null,"名称最长255个字符");
		}
		if(StringUtils.length(devpOpsCiGroup.getCode()) > 255){
			errors.rejectValue(DevpOpsCiGroup.PROPERTY_CODE,null,"代码最长255个字符");
		}
		if(StringUtils.length(devpOpsCiGroup.getAlias()) > 255){
			errors.rejectValue(DevpOpsCiGroup.PROPERTY_ALIAS,null,"别名最长255个字符");
		}
		if(StringUtils.length(devpOpsCiGroup.getDescription()) > 255){
			errors.rejectValue(DevpOpsCiGroup.PROPERTY_DESCRIPTION,null,"描述最长255个字符");
		}
		if(StringUtils.length(devpOpsCiGroup.getTypeCode()) > 255){
			errors.rejectValue(DevpOpsCiGroup.PROPERTY_TYPE_CODE,null,"类型代码最长255个字符");
		}
		if(StringUtils.length(devpOpsCiGroup.getTypeName()) > 255){
			errors.rejectValue(DevpOpsCiGroup.PROPERTY_TYPE_NAME,null,"类型名称最长255个字符");
		}
		if(StringUtils.length(devpOpsCiGroup.getParasCode()) > 255){
			errors.rejectValue(DevpOpsCiGroup.PROPERTY_PARAS_CODE,null,"参数定义标识最长255个字符");
		}
		if(StringUtils.length(devpOpsCiGroup.getCreateUcode()) > 255){
			errors.rejectValue(DevpOpsCiGroup.PROPERTY_CREATE_UCODE,null,"创建用户代码最长255个字符");
		}
		if(StringUtils.length(devpOpsCiGroup.getCreateUname()) > 255){
			errors.rejectValue(DevpOpsCiGroup.PROPERTY_CREATE_UNAME,null,"创建用户姓名最长255个字符");
		}
		if(StringUtils.length(devpOpsCiGroup.getCmodifyUcode()) > 255){
			errors.rejectValue(DevpOpsCiGroup.PROPERTY_CMODIFY_UCODE,null,"修改用户代码最长255个字符");
		}
		if(StringUtils.length(devpOpsCiGroup.getModifyUname()) > 255){
			errors.rejectValue(DevpOpsCiGroup.PROPERTY_MODIFY_UNAME,null,"修改用户姓名最长255个字符");
		}
	}
}