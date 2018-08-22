package net.aicoder.devp.business.sys.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.business.sys.dto.DevpDevFunaModuleAddDto;
import net.aicoder.devp.business.sys.dto.DevpDevFunaModuleEditDto;
import net.aicoder.devp.business.sys.domain.DevpDevFunaModule;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class DevpDevFunaModuleValidator implements Validator {

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
	    if(obj instanceof DevpDevFunaModuleAddDto){
            this.validateDevpDevFunaModuleAddDto((DevpDevFunaModuleAddDto)obj, errors);
        }
	}

	/**
     * 实现Validator中的validate接口
     * @param devpDevFunaModule 功能模块
     * @param errors
     */
	public void validateDevpDevFunaModuleAddDto(DevpDevFunaModuleAddDto devpDevFunaModule, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(devpDevFunaModule.getName()) > 255){
			errors.rejectValue(DevpDevFunaModule.PROPERTY_NAME,null,"模块名称最长255个字符");
		}
		if(StringUtils.length(devpDevFunaModule.getCode()) > 255){
			errors.rejectValue(DevpDevFunaModule.PROPERTY_CODE,null,"模块代码最长255个字符");
		}
		if(StringUtils.length(devpDevFunaModule.getAlias()) > 255){
			errors.rejectValue(DevpDevFunaModule.PROPERTY_ALIAS,null,"模块别名最长255个字符");
		}
		if(StringUtils.length(devpDevFunaModule.getDescription()) > 255){
			errors.rejectValue(DevpDevFunaModule.PROPERTY_DESCRIPTION,null,"模块描述最长255个字符");
		}
		if(StringUtils.length(devpDevFunaModule.getType()) > 255){
			errors.rejectValue(DevpDevFunaModule.PROPERTY_TYPE,null,"类型最长255个字符");
		}
		if(StringUtils.length(devpDevFunaModule.getStereotype()) > 255){
			errors.rejectValue(DevpDevFunaModule.PROPERTY_STEREOTYPE,null,"构造型最长255个字符");
		}
		if(StringUtils.length(devpDevFunaModule.getScope()) > 255){
			errors.rejectValue(DevpDevFunaModule.PROPERTY_SCOPE,null,"访问控制范围最长255个字符");
		}
		if(StringUtils.length(devpDevFunaModule.getActor()) > 255){
			errors.rejectValue(DevpDevFunaModule.PROPERTY_ACTOR,null,"使用者最长255个字符");
		}
		if(StringUtils.length(devpDevFunaModule.getPriority()) > 255){
			errors.rejectValue(DevpDevFunaModule.PROPERTY_PRIORITY,null,"优先级最长255个字符");
		}
		if(StringUtils.length(devpDevFunaModule.getVersion()) > 255){
			errors.rejectValue(DevpDevFunaModule.PROPERTY_VERSION,null,"版本最长255个字符");
		}
		if(StringUtils.length(devpDevFunaModule.getPhase()) > 255){
			errors.rejectValue(DevpDevFunaModule.PROPERTY_PHASE,null,"阶段最长255个字符");
		}
		if(StringUtils.length(devpDevFunaModule.getStatus()) > 255){
			errors.rejectValue(DevpDevFunaModule.PROPERTY_STATUS,null,"状态最长255个字符");
		}
		if(StringUtils.length(devpDevFunaModule.getNotes()) > 255){
			errors.rejectValue(DevpDevFunaModule.PROPERTY_NOTES,null,"备注最长255个字符");
		}
		if(StringUtils.length(devpDevFunaModule.getCreateUcode()) > 255){
			errors.rejectValue(DevpDevFunaModule.PROPERTY_CREATE_UCODE,null,"创建用户代码最长255个字符");
		}
		if(StringUtils.length(devpDevFunaModule.getCreateUname()) > 255){
			errors.rejectValue(DevpDevFunaModule.PROPERTY_CREATE_UNAME,null,"创建用户姓名最长255个字符");
		}
		if(StringUtils.length(devpDevFunaModule.getModifyUcode()) > 255){
			errors.rejectValue(DevpDevFunaModule.PROPERTY_MODIFY_UCODE,null,"修改用户代码最长255个字符");
		}
		if(StringUtils.length(devpDevFunaModule.getModifyUname()) > 255){
			errors.rejectValue(DevpDevFunaModule.PROPERTY_MODIFY_UNAME,null,"修改用户姓名最长255个字符");
		}
	}
}