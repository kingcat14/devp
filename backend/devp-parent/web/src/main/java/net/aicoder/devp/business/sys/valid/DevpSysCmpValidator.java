package net.aicoder.devp.business.sys.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.business.sys.dto.DevpSysCmpAddDto;
import net.aicoder.devp.business.sys.dto.DevpSysCmpEditDto;
import net.aicoder.devp.business.sys.dto.DevpSysCmpCondition;
import net.aicoder.devp.business.sys.domain.DevpSysCmp;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class DevpSysCmpValidator implements Validator {

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
	    if(obj instanceof DevpSysCmpAddDto){
            this.validateDevpSysCmpAddDto((DevpSysCmpAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<DevpSysCmpCondition> search){
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new DevpSysCmpCondition());
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param devpSysCmp 系统组件
     * @param errors
     */
	public void validateDevpSysCmpAddDto(DevpSysCmpAddDto devpSysCmp, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(devpSysCmp.getEtype()) > 255){
			errors.rejectValue(DevpSysCmp.PROPERTY_ETYPE,null,"元素类型最长255个字符");
		}
		if(StringUtils.length(devpSysCmp.getName()) > 255){
			errors.rejectValue(DevpSysCmp.PROPERTY_NAME,null,"系统元素名称最长255个字符");
		}
		if(StringUtils.length(devpSysCmp.getCode()) > 255){
			errors.rejectValue(DevpSysCmp.PROPERTY_CODE,null,"系统元素代码最长255个字符");
		}
		if(StringUtils.length(devpSysCmp.getAlias()) > 255){
			errors.rejectValue(DevpSysCmp.PROPERTY_ALIAS,null,"系统元素别名最长255个字符");
		}
		if(StringUtils.length(devpSysCmp.getDescription()) > 255){
			errors.rejectValue(DevpSysCmp.PROPERTY_DESCRIPTION,null,"系统元素描述最长255个字符");
		}
		if(StringUtils.length(devpSysCmp.getType()) > 255){
			errors.rejectValue(DevpSysCmp.PROPERTY_TYPE,null,"类型最长255个字符");
		}
		if(StringUtils.length(devpSysCmp.getSubType()) > 255){
			errors.rejectValue(DevpSysCmp.PROPERTY_SUB_TYPE,null,"子类型最长255个字符");
		}
		if(StringUtils.length(devpSysCmp.getStereotype()) > 255){
			errors.rejectValue(DevpSysCmp.PROPERTY_STEREOTYPE,null,"构造型最长255个字符");
		}
		if(StringUtils.length(devpSysCmp.getScope()) > 255){
			errors.rejectValue(DevpSysCmp.PROPERTY_SCOPE,null,"范围最长255个字符");
		}
		if(StringUtils.length(devpSysCmp.getVersion()) > 255){
			errors.rejectValue(DevpSysCmp.PROPERTY_VERSION,null,"版本最长255个字符");
		}
		if(StringUtils.length(devpSysCmp.getPhase()) > 255){
			errors.rejectValue(DevpSysCmp.PROPERTY_PHASE,null,"阶段最长255个字符");
		}
		if(StringUtils.length(devpSysCmp.getStatus()) > 255){
			errors.rejectValue(DevpSysCmp.PROPERTY_STATUS,null,"状态最长255个字符");
		}
		if(StringUtils.length(devpSysCmp.getNotes()) > 255){
			errors.rejectValue(DevpSysCmp.PROPERTY_NOTES,null,"备注最长255个字符");
		}
		if(StringUtils.length(devpSysCmp.getCreateUcode()) > 255){
			errors.rejectValue(DevpSysCmp.PROPERTY_CREATE_UCODE,null,"创建用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysCmp.getCreateUname()) > 255){
			errors.rejectValue(DevpSysCmp.PROPERTY_CREATE_UNAME,null,"创建用户姓名最长255个字符");
		}
		if(StringUtils.length(devpSysCmp.getModifyUcode()) > 255){
			errors.rejectValue(DevpSysCmp.PROPERTY_MODIFY_UCODE,null,"修改用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysCmp.getModifyUname()) > 255){
			errors.rejectValue(DevpSysCmp.PROPERTY_MODIFY_UNAME,null,"修改用户姓名最长255个字符");
		}
	}
}