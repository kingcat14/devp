package net.aicoder.devp.business.product.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.business.product.dto.DevpPrdLinePrdAddDto;
import net.aicoder.devp.business.product.dto.DevpPrdLinePrdEditDto;
import net.aicoder.devp.business.product.domain.DevpPrdLinePrd;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class DevpPrdLinePrdValidator implements Validator {

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
	    if(obj instanceof DevpPrdLinePrdAddDto){
            this.validateDevpPrdLinePrdAddDto((DevpPrdLinePrdAddDto)obj, errors);
        }
	}

	/**
     * 实现Validator中的validate接口
     * @param devpPrdLinePrd 产品所属产品线
     * @param errors
     */
	public void validateDevpPrdLinePrdAddDto(DevpPrdLinePrdAddDto devpPrdLinePrd, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(devpPrdLinePrd.getEtype()) > 255){
			errors.rejectValue(DevpPrdLinePrd.PROPERTY_ETYPE,null,"etype最长255个字符");
		}
		if(StringUtils.length(devpPrdLinePrd.getCode()) > 255){
			errors.rejectValue(DevpPrdLinePrd.PROPERTY_CODE,null,"代码最长255个字符");
		}
		if(StringUtils.length(devpPrdLinePrd.getName()) > 255){
			errors.rejectValue(DevpPrdLinePrd.PROPERTY_NAME,null,"名称最长255个字符");
		}
		if(StringUtils.length(devpPrdLinePrd.getAlias()) > 255){
			errors.rejectValue(DevpPrdLinePrd.PROPERTY_ALIAS,null,"别名最长255个字符");
		}
		if(StringUtils.length(devpPrdLinePrd.getDescription()) > 255){
			errors.rejectValue(DevpPrdLinePrd.PROPERTY_DESCRIPTION,null,"描述最长255个字符");
		}
		if(StringUtils.length(devpPrdLinePrd.getType()) > 255){
			errors.rejectValue(DevpPrdLinePrd.PROPERTY_TYPE,null,"类型最长255个字符");
		}
		if(StringUtils.length(devpPrdLinePrd.getStereotype()) > 255){
			errors.rejectValue(DevpPrdLinePrd.PROPERTY_STEREOTYPE,null,"构造型最长255个字符");
		}
		if(StringUtils.length(devpPrdLinePrd.getScope()) > 255){
			errors.rejectValue(DevpPrdLinePrd.PROPERTY_SCOPE,null,"范围最长255个字符");
		}
		if(StringUtils.length(devpPrdLinePrd.getCreateUcode()) > 255){
			errors.rejectValue(DevpPrdLinePrd.PROPERTY_CREATE_UCODE,null,"创建用户代码最长255个字符");
		}
		if(StringUtils.length(devpPrdLinePrd.getCreateUname()) > 255){
			errors.rejectValue(DevpPrdLinePrd.PROPERTY_CREATE_UNAME,null,"创建用户姓名最长255个字符");
		}
		if(StringUtils.length(devpPrdLinePrd.getModifyUcode()) > 255){
			errors.rejectValue(DevpPrdLinePrd.PROPERTY_MODIFY_UCODE,null,"修改用户代码最长255个字符");
		}
		if(StringUtils.length(devpPrdLinePrd.getModifyUname()) > 255){
			errors.rejectValue(DevpPrdLinePrd.PROPERTY_MODIFY_UNAME,null,"修改用户姓名最长255个字符");
		}
	}
}