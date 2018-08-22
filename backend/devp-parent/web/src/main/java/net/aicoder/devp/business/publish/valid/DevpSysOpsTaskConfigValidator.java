package net.aicoder.devp.business.publish.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskConfigAddDto;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskConfigEditDto;
import net.aicoder.devp.business.publish.domain.DevpSysOpsTaskConfig;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class DevpSysOpsTaskConfigValidator implements Validator {

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
	    if(obj instanceof DevpSysOpsTaskConfigAddDto){
            this.validateDevpSysOpsTaskConfigAddDto((DevpSysOpsTaskConfigAddDto)obj, errors);
        }
	}

	/**
     * 实现Validator中的validate接口
     * @param devpSysOpsTaskConfig 配置文件设置
     * @param errors
     */
	public void validateDevpSysOpsTaskConfigAddDto(DevpSysOpsTaskConfigAddDto devpSysOpsTaskConfig, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(devpSysOpsTaskConfig.getEtype()) > 255){
			errors.rejectValue(DevpSysOpsTaskConfig.PROPERTY_ETYPE,null,"元素类型最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskConfig.getName()) > 255){
			errors.rejectValue(DevpSysOpsTaskConfig.PROPERTY_NAME,null,"系统元素名称最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskConfig.getCode()) > 255){
			errors.rejectValue(DevpSysOpsTaskConfig.PROPERTY_CODE,null,"系统元素代码最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskConfig.getAlias()) > 255){
			errors.rejectValue(DevpSysOpsTaskConfig.PROPERTY_ALIAS,null,"系统元素别名最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskConfig.getDescription()) > 255){
			errors.rejectValue(DevpSysOpsTaskConfig.PROPERTY_DESCRIPTION,null,"系统元素描述最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskConfig.getType()) > 255){
			errors.rejectValue(DevpSysOpsTaskConfig.PROPERTY_TYPE,null,"类型最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskConfig.getSubType()) > 255){
			errors.rejectValue(DevpSysOpsTaskConfig.PROPERTY_SUB_TYPE,null,"子类型最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskConfig.getAccessType()) > 255){
			errors.rejectValue(DevpSysOpsTaskConfig.PROPERTY_ACCESS_TYPE,null,"访问方式最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskConfig.getTplPath()) > 255){
			errors.rejectValue(DevpSysOpsTaskConfig.PROPERTY_TPL_PATH,null,"模板路径最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskConfig.getTplFileName()) > 255){
			errors.rejectValue(DevpSysOpsTaskConfig.PROPERTY_TPL_FILE_NAME,null,"模板文件名最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskConfig.getTargetPath()) > 255){
			errors.rejectValue(DevpSysOpsTaskConfig.PROPERTY_TARGET_PATH,null,"模板路径最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskConfig.getTargetFileName()) > 255){
			errors.rejectValue(DevpSysOpsTaskConfig.PROPERTY_TARGET_FILE_NAME,null,"模板文件名最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskConfig.getStatus()) > 255){
			errors.rejectValue(DevpSysOpsTaskConfig.PROPERTY_STATUS,null,"状态最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskConfig.getNotes()) > 255){
			errors.rejectValue(DevpSysOpsTaskConfig.PROPERTY_NOTES,null,"备注最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskConfig.getCreateUcode()) > 255){
			errors.rejectValue(DevpSysOpsTaskConfig.PROPERTY_CREATE_UCODE,null,"创建用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskConfig.getCreateUname()) > 255){
			errors.rejectValue(DevpSysOpsTaskConfig.PROPERTY_CREATE_UNAME,null,"创建用户姓名最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskConfig.getModifyUcode()) > 255){
			errors.rejectValue(DevpSysOpsTaskConfig.PROPERTY_MODIFY_UCODE,null,"修改用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskConfig.getModifyUname()) > 255){
			errors.rejectValue(DevpSysOpsTaskConfig.PROPERTY_MODIFY_UNAME,null,"修改用户姓名最长255个字符");
		}
	}
}