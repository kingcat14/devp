package net.aicoder.devp.business.sys.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.business.sys.dto.DevpSysCmpModuleAddDto;
import net.aicoder.devp.business.sys.dto.DevpSysCmpModuleEditDto;
import net.aicoder.devp.business.sys.dto.DevpSysCmpModuleCondition;
import net.aicoder.devp.business.sys.domain.DevpSysCmpModule;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class DevpSysCmpModuleValidator implements Validator {

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
	    if(obj instanceof DevpSysCmpModuleAddDto){
            this.validateDevpSysCmpModuleAddDto((DevpSysCmpModuleAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<DevpSysCmpModuleCondition> search){
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new DevpSysCmpModuleCondition());
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param devpSysCmpModule 组件对应模块
     * @param errors
     */
	public void validateDevpSysCmpModuleAddDto(DevpSysCmpModuleAddDto devpSysCmpModule, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(devpSysCmpModule.getEtype()) > 255){
			errors.rejectValue(DevpSysCmpModule.PROPERTY_ETYPE,null,"元素类型最长255个字符");
		}
		if(StringUtils.length(devpSysCmpModule.getName()) > 255){
			errors.rejectValue(DevpSysCmpModule.PROPERTY_NAME,null,"对应关系名称最长255个字符");
		}
		if(StringUtils.length(devpSysCmpModule.getCode()) > 255){
			errors.rejectValue(DevpSysCmpModule.PROPERTY_CODE,null,"对应关系代码最长255个字符");
		}
		if(StringUtils.length(devpSysCmpModule.getAlias()) > 255){
			errors.rejectValue(DevpSysCmpModule.PROPERTY_ALIAS,null,"对应关系别名最长255个字符");
		}
		if(StringUtils.length(devpSysCmpModule.getDescription()) > 255){
			errors.rejectValue(DevpSysCmpModule.PROPERTY_DESCRIPTION,null,"对应关系描述最长255个字符");
		}
		if(StringUtils.length(devpSysCmpModule.getType()) > 255){
			errors.rejectValue(DevpSysCmpModule.PROPERTY_TYPE,null,"类型最长255个字符");
		}
		if(StringUtils.length(devpSysCmpModule.getSubType()) > 255){
			errors.rejectValue(DevpSysCmpModule.PROPERTY_SUB_TYPE,null,"子类型最长255个字符");
		}
		if(StringUtils.length(devpSysCmpModule.getCreateUcode()) > 255){
			errors.rejectValue(DevpSysCmpModule.PROPERTY_CREATE_UCODE,null,"创建用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysCmpModule.getCreateUname()) > 255){
			errors.rejectValue(DevpSysCmpModule.PROPERTY_CREATE_UNAME,null,"创建用户姓名最长255个字符");
		}
		if(StringUtils.length(devpSysCmpModule.getModifyUcode()) > 255){
			errors.rejectValue(DevpSysCmpModule.PROPERTY_MODIFY_UCODE,null,"修改用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysCmpModule.getModifyUname()) > 255){
			errors.rejectValue(DevpSysCmpModule.PROPERTY_MODIFY_UNAME,null,"修改用户姓名最长255个字符");
		}
	}
}