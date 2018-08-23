package net.aicoder.devp.business.sys.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.business.sys.dto.DevpSysIdeRefAddDto;
import net.aicoder.devp.business.sys.dto.DevpSysIdeRefEditDto;
import net.aicoder.devp.business.sys.dto.DevpSysIdeRefCondition;
import net.aicoder.devp.business.sys.domain.DevpSysIdeRef;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class DevpSysIdeRefValidator implements Validator {

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
	    if(obj instanceof DevpSysIdeRefAddDto){
            this.validateDevpSysIdeRefAddDto((DevpSysIdeRefAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<DevpSysIdeRefCondition> search){
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new DevpSysIdeRefCondition());
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param devpSysIdeRef 开发工程引用组件
     * @param errors
     */
	public void validateDevpSysIdeRefAddDto(DevpSysIdeRefAddDto devpSysIdeRef, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(devpSysIdeRef.getEtype()) > 255){
			errors.rejectValue(DevpSysIdeRef.PROPERTY_ETYPE,null,"元素类型最长255个字符");
		}
		if(StringUtils.length(devpSysIdeRef.getName()) > 255){
			errors.rejectValue(DevpSysIdeRef.PROPERTY_NAME,null,"系统元素名称最长255个字符");
		}
		if(StringUtils.length(devpSysIdeRef.getCode()) > 255){
			errors.rejectValue(DevpSysIdeRef.PROPERTY_CODE,null,"系统元素代码最长255个字符");
		}
		if(StringUtils.length(devpSysIdeRef.getAlias()) > 255){
			errors.rejectValue(DevpSysIdeRef.PROPERTY_ALIAS,null,"系统元素别名最长255个字符");
		}
		if(StringUtils.length(devpSysIdeRef.getDescription()) > 255){
			errors.rejectValue(DevpSysIdeRef.PROPERTY_DESCRIPTION,null,"系统元素描述最长255个字符");
		}
		if(StringUtils.length(devpSysIdeRef.getType()) > 255){
			errors.rejectValue(DevpSysIdeRef.PROPERTY_TYPE,null,"类型最长255个字符");
		}
		if(StringUtils.length(devpSysIdeRef.getSubType()) > 255){
			errors.rejectValue(DevpSysIdeRef.PROPERTY_SUB_TYPE,null,"子类型最长255个字符");
		}
		if(StringUtils.length(devpSysIdeRef.getVersion()) > 255){
			errors.rejectValue(DevpSysIdeRef.PROPERTY_VERSION,null,"版本最长255个字符");
		}
		if(StringUtils.length(devpSysIdeRef.getNexusEtype()) > 255){
			errors.rejectValue(DevpSysIdeRef.PROPERTY_NEXUS_ETYPE,null,"关联记录类型最长255个字符");
		}
		if(StringUtils.length(devpSysIdeRef.getCreateUcode()) > 255){
			errors.rejectValue(DevpSysIdeRef.PROPERTY_CREATE_UCODE,null,"创建用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysIdeRef.getCreateUname()) > 255){
			errors.rejectValue(DevpSysIdeRef.PROPERTY_CREATE_UNAME,null,"创建用户姓名最长255个字符");
		}
		if(StringUtils.length(devpSysIdeRef.getModifyUcode()) > 255){
			errors.rejectValue(DevpSysIdeRef.PROPERTY_MODIFY_UCODE,null,"修改用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysIdeRef.getModifyUname()) > 255){
			errors.rejectValue(DevpSysIdeRef.PROPERTY_MODIFY_UNAME,null,"修改用户姓名最长255个字符");
		}
	}
}