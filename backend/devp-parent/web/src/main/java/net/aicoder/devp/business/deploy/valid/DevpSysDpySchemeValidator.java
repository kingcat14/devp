package net.aicoder.devp.business.deploy.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.business.deploy.dto.DevpSysDpySchemeAddDto;
import net.aicoder.devp.business.deploy.dto.DevpSysDpySchemeEditDto;
import net.aicoder.devp.business.deploy.dto.DevpSysDpySchemeCondition;
import net.aicoder.devp.business.deploy.domain.DevpSysDpyScheme;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class DevpSysDpySchemeValidator implements Validator {

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
	    if(obj instanceof DevpSysDpySchemeAddDto){
            this.validateDevpSysDpySchemeAddDto((DevpSysDpySchemeAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<DevpSysDpySchemeCondition> search){
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new DevpSysDpySchemeCondition());
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param devpSysDpyScheme 产品部署方案
     * @param errors
     */
	public void validateDevpSysDpySchemeAddDto(DevpSysDpySchemeAddDto devpSysDpyScheme, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(devpSysDpyScheme.getEtype()) > 255){
			errors.rejectValue(DevpSysDpyScheme.PROPERTY_ETYPE,null,"元素类型最长255个字符");
		}
		if(StringUtils.length(devpSysDpyScheme.getName()) > 255){
			errors.rejectValue(DevpSysDpyScheme.PROPERTY_NAME,null,"系统元素名称最长255个字符");
		}
		if(StringUtils.length(devpSysDpyScheme.getCode()) > 255){
			errors.rejectValue(DevpSysDpyScheme.PROPERTY_CODE,null,"系统元素代码最长255个字符");
		}
		if(StringUtils.length(devpSysDpyScheme.getAlias()) > 255){
			errors.rejectValue(DevpSysDpyScheme.PROPERTY_ALIAS,null,"系统元素别名最长255个字符");
		}
		if(StringUtils.length(devpSysDpyScheme.getDescription()) > 255){
			errors.rejectValue(DevpSysDpyScheme.PROPERTY_DESCRIPTION,null,"系统元素描述最长255个字符");
		}
		if(StringUtils.length(devpSysDpyScheme.getType()) > 255){
			errors.rejectValue(DevpSysDpyScheme.PROPERTY_TYPE,null,"类型最长255个字符");
		}
		if(StringUtils.length(devpSysDpyScheme.getSubType()) > 255){
			errors.rejectValue(DevpSysDpyScheme.PROPERTY_SUB_TYPE,null,"子类型最长255个字符");
		}
		if(StringUtils.length(devpSysDpyScheme.getStereotype()) > 255){
			errors.rejectValue(DevpSysDpyScheme.PROPERTY_STEREOTYPE,null,"构造型最长255个字符");
		}
		if(StringUtils.length(devpSysDpyScheme.getScope()) > 255){
			errors.rejectValue(DevpSysDpyScheme.PROPERTY_SCOPE,null,"范围最长255个字符");
		}
		if(StringUtils.length(devpSysDpyScheme.getVersion()) > 255){
			errors.rejectValue(DevpSysDpyScheme.PROPERTY_VERSION,null,"版本最长255个字符");
		}
		if(StringUtils.length(devpSysDpyScheme.getVerPostfix()) > 255){
			errors.rejectValue(DevpSysDpyScheme.PROPERTY_VER_POSTFIX,null,"版本标识后缀最长255个字符");
		}
		if(StringUtils.length(devpSysDpyScheme.getPhase()) > 255){
			errors.rejectValue(DevpSysDpyScheme.PROPERTY_PHASE,null,"阶段最长255个字符");
		}
		if(StringUtils.length(devpSysDpyScheme.getStatus()) > 255){
			errors.rejectValue(DevpSysDpyScheme.PROPERTY_STATUS,null,"状态最长255个字符");
		}
		if(StringUtils.length(devpSysDpyScheme.getNotes()) > 255){
			errors.rejectValue(DevpSysDpyScheme.PROPERTY_NOTES,null,"备注最长255个字符");
		}
		if(StringUtils.length(devpSysDpyScheme.getCreateUcode()) > 255){
			errors.rejectValue(DevpSysDpyScheme.PROPERTY_CREATE_UCODE,null,"创建用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysDpyScheme.getCreateUname()) > 255){
			errors.rejectValue(DevpSysDpyScheme.PROPERTY_CREATE_UNAME,null,"创建用户姓名最长255个字符");
		}
		if(StringUtils.length(devpSysDpyScheme.getModifyUcode()) > 255){
			errors.rejectValue(DevpSysDpyScheme.PROPERTY_MODIFY_UCODE,null,"修改用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysDpyScheme.getModifyUname()) > 255){
			errors.rejectValue(DevpSysDpyScheme.PROPERTY_MODIFY_UNAME,null,"修改用户姓名最长255个字符");
		}
	}
}