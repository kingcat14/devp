package net.aicoder.maintenance.business.product.product.valid;


import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.business.product.dto.DevpPrdPrdlineAddDto;
import net.aicoder.devp.business.product.dto.DevpPrdPrdlineEditDto;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
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
		if(DevpPrdPrdlineEditDto.class.equals(aClass))
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
	    if(obj instanceof DevpPrdPrdlineAddDto){
            this.validateAddDto((DevpPrdPrdlineAddDto)obj, errors);
        }
	}

	/**
     * 验证新增信息
     * @param devpPrdPrdline 产品线定义
     * @param errors
     */
	public void validateAddDto(DevpPrdPrdlineAddDto devpPrdPrdline, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if(StringUtils.isEmpty(devpPrdPrdline.getCode())){
			errors.rejectValue("code", "EMPTY_CODE", "产品线代码不能为空");
		}
       
		if(StringUtils.isEmpty(devpPrdPrdline.getName())){
			errors.rejectValue("name", "EMPTY_NAME", "产品线名称不能为空");
		}
       

		//验证长度
		if(StringUtils.length(devpPrdPrdline.getCode()) > 255){
			errors.rejectValue("code", null, "产品线代码最长255个字符");
		}
		if(StringUtils.length(devpPrdPrdline.getName()) > 255){
			errors.rejectValue("name", null, "产品线名称最长255个字符");
		}
		if(StringUtils.length(devpPrdPrdline.getAlias()) > 255){
			errors.rejectValue("alias", null, "产品线别名最长255个字符");
		}
		if(StringUtils.length(devpPrdPrdline.getDescription()) > 255){
			errors.rejectValue("description", null, "产品线描述最长255个字符");
		}
		if(StringUtils.length(devpPrdPrdline.getType()) > 255){
			errors.rejectValue("type", null, "产品线类型最长255个字符");
		}
		if(StringUtils.length(devpPrdPrdline.getDomain()) > 255){
			errors.rejectValue("domain", null, "领域最长255个字符");
		}
		if(StringUtils.length(devpPrdPrdline.getStereotype()) > 255){
			errors.rejectValue("stereotype", null, "构造型最长255个字符");
		}
		if(StringUtils.length(devpPrdPrdline.getScope()) > 255){
			errors.rejectValue("scope", null, "访问控制范围最长255个字符");
		}
		if(StringUtils.length(devpPrdPrdline.getVersion()) > 255){
			errors.rejectValue("version", null, "版本最长255个字符");
		}
		if(StringUtils.length(devpPrdPrdline.getPhase()) > 255){
			errors.rejectValue("phase", null, "阶段最长255个字符");
		}
		if(StringUtils.length(devpPrdPrdline.getStatus()) > 255){
			errors.rejectValue("status", null, "状态最长255个字符");
		}
		if(StringUtils.length(devpPrdPrdline.getCreateUcode()) > 255){
			errors.rejectValue("createUcode", null, "创建用户代码最长255个字符");
		}
		if(StringUtils.length(devpPrdPrdline.getModifyUcode()) > 255){
			errors.rejectValue("modifyUcode", null, "修改用户代码最长255个字符");
		}
	}
}