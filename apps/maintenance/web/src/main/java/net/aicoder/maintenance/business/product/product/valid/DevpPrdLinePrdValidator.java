package net.aicoder.maintenance.business.product.product.valid;


import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.business.product.dto.DevpPrdLinePrdAddDto;
import net.aicoder.devp.business.product.dto.DevpPrdLinePrdEditDto;
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
		if(DevpPrdLinePrdAddDto.class.equals(aClass))
			return true;
		if(DevpPrdLinePrdEditDto.class.equals(aClass))
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
	    if(obj instanceof DevpPrdLinePrdAddDto){
            this.validateAddDto((DevpPrdLinePrdAddDto)obj, errors);
        }
	}

	/**
     * 验证新增信息
     * @param devpPrdLinePrd 产品所属产品线定义
     * @param errors
     */
	public void validateAddDto(DevpPrdLinePrdAddDto devpPrdLinePrd, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if (null == devpPrdLinePrd.getTid() ) {
			errors.rejectValue("tid", "EMPTY_TID", "租户编号不能为空");
		}

		//验证长度
		if(StringUtils.length(devpPrdLinePrd.getCode()) > 255){
			errors.rejectValue("code", null, "代码最长255个字符");
		}
		if(StringUtils.length(devpPrdLinePrd.getName()) > 255){
			errors.rejectValue("name", null, "名称最长255个字符");
		}
		if(StringUtils.length(devpPrdLinePrd.getAlias()) > 255){
			errors.rejectValue("alias", null, "别名最长255个字符");
		}
		if(StringUtils.length(devpPrdLinePrd.getDescription()) > 255){
			errors.rejectValue("description", null, "描述最长255个字符");
		}
		if(StringUtils.length(devpPrdLinePrd.getType()) > 255){
			errors.rejectValue("type", null, "类型最长255个字符");
		}
		if(StringUtils.length(devpPrdLinePrd.getStereotype()) > 255){
			errors.rejectValue("stereotype", null, "构造型最长255个字符");
		}
		if(StringUtils.length(devpPrdLinePrd.getScope()) > 255){
			errors.rejectValue("scope", null, "范围最长255个字符");
		}
		if(StringUtils.length(devpPrdLinePrd.getCreateUcode()) > 255){
			errors.rejectValue("createUcode", null, "创建用户代码最长255个字符");
		}
		if(StringUtils.length(devpPrdLinePrd.getCmodifyUcode()) > 255){
			errors.rejectValue("cmodifyUcode", null, "修改用户代码最长255个字符");
		}
	}
}