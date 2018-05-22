package net.aicoder.devp.product.business.product.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.product.business.product.dto.DevpPrdPrdlineAddDto;
import net.aicoder.devp.product.business.product.domain.DevpPrdPrdline;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;

public class DevpPrdPrdlineValidator implements Validator {

	/**
	 * 判断支持的JavaBean类型
	 * @param aClass
	 * @return
	 */
	@Override
	public boolean supports(Class<?> aClass) {
		if(DevpPrdPrdlineAddDto.class.equals(aClass))
			return true;
		if(DevpPrdPrdlineAddDto.class.equals(aClass))
            return true;
		return DevpPrdPrdline.class.equals(aClass);
	}

	/**
	 * 实现Validator中的validate接口
	 * @param obj
	 * @param errors
	 */
	@Override
	public void validate(Object obj, Errors errors) {
	    if(obj instanceof DevpPrdPrdline){
            this.validateDevpPrdPrdline((DevpPrdPrdline)obj, errors);
        }
	}

	/**
     * 实现Validator中的validate接口
     * @param devpPrdPrdline 产品线定义
     * @param errors
     */
	public void validateDevpPrdPrdline(DevpPrdPrdline devpPrdPrdline, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if (null == devpPrdPrdline.getTid() ) {
			errors.rejectValue(DevpPrdPrdline.PROPERTY_TID, "EMPTY_"+DevpPrdPrdline.PROPERTY_TID, "租户编号不能为空");
		}
		if(StringUtils.isEmpty(devpPrdPrdline.getCode())){
			errors.rejectValue(DevpPrdPrdline.PROPERTY_CODE, "EMPTY_"+DevpPrdPrdline.PROPERTY_CODE, "产品线代码不能为空");
		}
		if(StringUtils.isEmpty(devpPrdPrdline.getName())){
			errors.rejectValue(DevpPrdPrdline.PROPERTY_NAME, "EMPTY_"+DevpPrdPrdline.PROPERTY_NAME, "产品线名称不能为空");
		}

		//验证长度
		if(StringUtils.length(devpPrdPrdline.getCode()) > 255){
			errors.rejectValue(DevpPrdPrdline.PROPERTY_CODE,null,"产品线代码最长255个字符");
		}
		if(StringUtils.length(devpPrdPrdline.getName()) > 255){
			errors.rejectValue(DevpPrdPrdline.PROPERTY_NAME,null,"产品线名称最长255个字符");
		}
		if(StringUtils.length(devpPrdPrdline.getAlias()) > 255){
			errors.rejectValue(DevpPrdPrdline.PROPERTY_ALIAS,null,"产品线别名最长255个字符");
		}
		if(StringUtils.length(devpPrdPrdline.getDescription()) > 255){
			errors.rejectValue(DevpPrdPrdline.PROPERTY_DESCRIPTION,null,"产品线描述最长255个字符");
		}
		if(StringUtils.length(devpPrdPrdline.getType()) > 255){
			errors.rejectValue(DevpPrdPrdline.PROPERTY_TYPE,null,"产品线类型最长255个字符");
		}
		if(StringUtils.length(devpPrdPrdline.getDomain()) > 255){
			errors.rejectValue(DevpPrdPrdline.PROPERTY_DOMAIN,null,"领域最长255个字符");
		}
		if(StringUtils.length(devpPrdPrdline.getStereotype()) > 255){
			errors.rejectValue(DevpPrdPrdline.PROPERTY_STEREOTYPE,null,"构造型最长255个字符");
		}
		if(StringUtils.length(devpPrdPrdline.getScope()) > 255){
			errors.rejectValue(DevpPrdPrdline.PROPERTY_SCOPE,null,"访问控制范围最长255个字符");
		}
		if(StringUtils.length(devpPrdPrdline.getVersion()) > 255){
			errors.rejectValue(DevpPrdPrdline.PROPERTY_VERSION,null,"版本最长255个字符");
		}
		if(StringUtils.length(devpPrdPrdline.getPhase()) > 255){
			errors.rejectValue(DevpPrdPrdline.PROPERTY_PHASE,null,"阶段最长255个字符");
		}
		if(StringUtils.length(devpPrdPrdline.getStatus()) > 255){
			errors.rejectValue(DevpPrdPrdline.PROPERTY_STATUS,null,"状态最长255个字符");
		}
		if(StringUtils.length(devpPrdPrdline.getCreateUcode()) > 255){
			errors.rejectValue(DevpPrdPrdline.PROPERTY_CREATE_UCODE,null,"创建用户代码最长255个字符");
		}
		if(StringUtils.length(devpPrdPrdline.getModifyUcode()) > 255){
			errors.rejectValue(DevpPrdPrdline.PROPERTY_MODIFY_UCODE,null,"修改用户代码最长255个字符");
		}
	}
}