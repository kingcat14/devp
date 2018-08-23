package net.aicoder.devp.business.deploy.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.business.deploy.dto.DevpSysDpyResourcesAddDto;
import net.aicoder.devp.business.deploy.dto.DevpSysDpyResourcesEditDto;
import net.aicoder.devp.business.deploy.dto.DevpSysDpyResourcesCondition;
import net.aicoder.devp.business.deploy.domain.DevpSysDpyResources;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class DevpSysDpyResourcesValidator implements Validator {

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
	    if(obj instanceof DevpSysDpyResourcesAddDto){
            this.validateDevpSysDpyResourcesAddDto((DevpSysDpyResourcesAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<DevpSysDpyResourcesCondition> search){
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new DevpSysDpyResourcesCondition());
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param devpSysDpyResources 部署关联资源
     * @param errors
     */
	public void validateDevpSysDpyResourcesAddDto(DevpSysDpyResourcesAddDto devpSysDpyResources, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(devpSysDpyResources.getEtype()) > 255){
			errors.rejectValue(DevpSysDpyResources.PROPERTY_ETYPE,null,"元素类型最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResources.getName()) > 255){
			errors.rejectValue(DevpSysDpyResources.PROPERTY_NAME,null,"系统元素名称最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResources.getCode()) > 255){
			errors.rejectValue(DevpSysDpyResources.PROPERTY_CODE,null,"系统元素代码最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResources.getAlias()) > 255){
			errors.rejectValue(DevpSysDpyResources.PROPERTY_ALIAS,null,"系统元素别名最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResources.getDescription()) > 255){
			errors.rejectValue(DevpSysDpyResources.PROPERTY_DESCRIPTION,null,"系统元素描述最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResources.getType()) > 255){
			errors.rejectValue(DevpSysDpyResources.PROPERTY_TYPE,null,"类型最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResources.getSubType()) > 255){
			errors.rejectValue(DevpSysDpyResources.PROPERTY_SUB_TYPE,null,"子类型最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResources.getStereotype()) > 255){
			errors.rejectValue(DevpSysDpyResources.PROPERTY_STEREOTYPE,null,"构造型最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResources.getScope()) > 255){
			errors.rejectValue(DevpSysDpyResources.PROPERTY_SCOPE,null,"范围最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResources.getVersion()) > 255){
			errors.rejectValue(DevpSysDpyResources.PROPERTY_VERSION,null,"版本最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResources.getPhase()) > 255){
			errors.rejectValue(DevpSysDpyResources.PROPERTY_PHASE,null,"阶段最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResources.getStatus()) > 255){
			errors.rejectValue(DevpSysDpyResources.PROPERTY_STATUS,null,"状态最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResources.getNotes()) > 255){
			errors.rejectValue(DevpSysDpyResources.PROPERTY_NOTES,null,"备注最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResources.getCreateUcode()) > 255){
			errors.rejectValue(DevpSysDpyResources.PROPERTY_CREATE_UCODE,null,"创建用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResources.getCreateUname()) > 255){
			errors.rejectValue(DevpSysDpyResources.PROPERTY_CREATE_UNAME,null,"创建用户姓名最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResources.getModifyUcode()) > 255){
			errors.rejectValue(DevpSysDpyResources.PROPERTY_MODIFY_UCODE,null,"修改用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResources.getModifyUname()) > 255){
			errors.rejectValue(DevpSysDpyResources.PROPERTY_MODIFY_UNAME,null,"修改用户姓名最长255个字符");
		}
	}
}