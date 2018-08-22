package net.aicoder.devp.business.publish.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.business.publish.dto.DevpSysOpsPipelineAddDto;
import net.aicoder.devp.business.publish.dto.DevpSysOpsPipelineEditDto;
import net.aicoder.devp.business.publish.domain.DevpSysOpsPipeline;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class DevpSysOpsPipelineValidator implements Validator {

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
	    if(obj instanceof DevpSysOpsPipelineAddDto){
            this.validateDevpSysOpsPipelineAddDto((DevpSysOpsPipelineAddDto)obj, errors);
        }
	}

	/**
     * 实现Validator中的validate接口
     * @param devpSysOpsPipeline 产品运维流水线
     * @param errors
     */
	public void validateDevpSysOpsPipelineAddDto(DevpSysOpsPipelineAddDto devpSysOpsPipeline, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(devpSysOpsPipeline.getEtype()) > 255){
			errors.rejectValue(DevpSysOpsPipeline.PROPERTY_ETYPE,null,"元素类型最长255个字符");
		}
		if(StringUtils.length(devpSysOpsPipeline.getName()) > 255){
			errors.rejectValue(DevpSysOpsPipeline.PROPERTY_NAME,null,"系统元素名称最长255个字符");
		}
		if(StringUtils.length(devpSysOpsPipeline.getCode()) > 255){
			errors.rejectValue(DevpSysOpsPipeline.PROPERTY_CODE,null,"系统元素代码最长255个字符");
		}
		if(StringUtils.length(devpSysOpsPipeline.getAlias()) > 255){
			errors.rejectValue(DevpSysOpsPipeline.PROPERTY_ALIAS,null,"系统元素别名最长255个字符");
		}
		if(StringUtils.length(devpSysOpsPipeline.getDescription()) > 255){
			errors.rejectValue(DevpSysOpsPipeline.PROPERTY_DESCRIPTION,null,"系统元素描述最长255个字符");
		}
		if(StringUtils.length(devpSysOpsPipeline.getType()) > 255){
			errors.rejectValue(DevpSysOpsPipeline.PROPERTY_TYPE,null,"类型最长255个字符");
		}
		if(StringUtils.length(devpSysOpsPipeline.getSubType()) > 255){
			errors.rejectValue(DevpSysOpsPipeline.PROPERTY_SUB_TYPE,null,"子类型最长255个字符");
		}
		if(StringUtils.length(devpSysOpsPipeline.getStereotype()) > 255){
			errors.rejectValue(DevpSysOpsPipeline.PROPERTY_STEREOTYPE,null,"构造型最长255个字符");
		}
		if(StringUtils.length(devpSysOpsPipeline.getScope()) > 255){
			errors.rejectValue(DevpSysOpsPipeline.PROPERTY_SCOPE,null,"范围最长255个字符");
		}
		if(StringUtils.length(devpSysOpsPipeline.getPhase()) > 255){
			errors.rejectValue(DevpSysOpsPipeline.PROPERTY_PHASE,null,"阶段最长255个字符");
		}
		if(StringUtils.length(devpSysOpsPipeline.getStatus()) > 255){
			errors.rejectValue(DevpSysOpsPipeline.PROPERTY_STATUS,null,"状态最长255个字符");
		}
		if(StringUtils.length(devpSysOpsPipeline.getNotes()) > 255){
			errors.rejectValue(DevpSysOpsPipeline.PROPERTY_NOTES,null,"备注最长255个字符");
		}
		if(StringUtils.length(devpSysOpsPipeline.getCreateUcode()) > 255){
			errors.rejectValue(DevpSysOpsPipeline.PROPERTY_CREATE_UCODE,null,"创建用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysOpsPipeline.getCreateUname()) > 255){
			errors.rejectValue(DevpSysOpsPipeline.PROPERTY_CREATE_UNAME,null,"创建用户姓名最长255个字符");
		}
		if(StringUtils.length(devpSysOpsPipeline.getModifyUcode()) > 255){
			errors.rejectValue(DevpSysOpsPipeline.PROPERTY_MODIFY_UCODE,null,"修改用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysOpsPipeline.getModifyUname()) > 255){
			errors.rejectValue(DevpSysOpsPipeline.PROPERTY_MODIFY_UNAME,null,"修改用户姓名最长255个字符");
		}
	}
}