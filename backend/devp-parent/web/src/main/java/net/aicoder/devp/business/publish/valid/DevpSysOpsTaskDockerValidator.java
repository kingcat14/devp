package net.aicoder.devp.business.publish.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskDockerAddDto;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskDockerEditDto;
import net.aicoder.devp.business.publish.domain.DevpSysOpsTaskDocker;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class DevpSysOpsTaskDockerValidator implements Validator {

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
	    if(obj instanceof DevpSysOpsTaskDockerAddDto){
            this.validateDevpSysOpsTaskDockerAddDto((DevpSysOpsTaskDockerAddDto)obj, errors);
        }
	}

	/**
     * 实现Validator中的validate接口
     * @param devpSysOpsTaskDocker 部署容器
     * @param errors
     */
	public void validateDevpSysOpsTaskDockerAddDto(DevpSysOpsTaskDockerAddDto devpSysOpsTaskDocker, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(devpSysOpsTaskDocker.getEtype()) > 255){
			errors.rejectValue(DevpSysOpsTaskDocker.PROPERTY_ETYPE,null,"元素类型最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskDocker.getName()) > 255){
			errors.rejectValue(DevpSysOpsTaskDocker.PROPERTY_NAME,null,"系统元素名称最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskDocker.getCode()) > 255){
			errors.rejectValue(DevpSysOpsTaskDocker.PROPERTY_CODE,null,"系统元素代码最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskDocker.getAlias()) > 255){
			errors.rejectValue(DevpSysOpsTaskDocker.PROPERTY_ALIAS,null,"系统元素别名最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskDocker.getDescription()) > 255){
			errors.rejectValue(DevpSysOpsTaskDocker.PROPERTY_DESCRIPTION,null,"系统元素描述最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskDocker.getType()) > 255){
			errors.rejectValue(DevpSysOpsTaskDocker.PROPERTY_TYPE,null,"类型最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskDocker.getSubType()) > 255){
			errors.rejectValue(DevpSysOpsTaskDocker.PROPERTY_SUB_TYPE,null,"子类型最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskDocker.getGroup()) > 255){
			errors.rejectValue(DevpSysOpsTaskDocker.PROPERTY_GROUP,null,"所在集群最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskDocker.getNamespace()) > 255){
			errors.rejectValue(DevpSysOpsTaskDocker.PROPERTY_NAMESPACE,null,"命名空间最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskDocker.getPublishGroup()) > 255){
			errors.rejectValue(DevpSysOpsTaskDocker.PROPERTY_PUBLISH_GROUP,null,"发布物分组最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskDocker.getPublishArtifact()) > 255){
			errors.rejectValue(DevpSysOpsTaskDocker.PROPERTY_PUBLISH_ARTIFACT,null,"发布物最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskDocker.getPublishVersion()) > 255){
			errors.rejectValue(DevpSysOpsTaskDocker.PROPERTY_PUBLISH_VERSION,null,"版本标识最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskDocker.getPublishFile()) > 255){
			errors.rejectValue(DevpSysOpsTaskDocker.PROPERTY_PUBLISH_FILE,null,"发布文件名最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskDocker.getLbStrategy()) > 255){
			errors.rejectValue(DevpSysOpsTaskDocker.PROPERTY_LB_STRATEGY,null,"负载策略最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskDocker.getAccessType()) > 255){
			errors.rejectValue(DevpSysOpsTaskDocker.PROPERTY_ACCESS_TYPE,null,"访问类型最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskDocker.getStatus()) > 255){
			errors.rejectValue(DevpSysOpsTaskDocker.PROPERTY_STATUS,null,"状态最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskDocker.getNotes()) > 255){
			errors.rejectValue(DevpSysOpsTaskDocker.PROPERTY_NOTES,null,"备注最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskDocker.getCreateUcode()) > 255){
			errors.rejectValue(DevpSysOpsTaskDocker.PROPERTY_CREATE_UCODE,null,"创建用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskDocker.getCreateUname()) > 255){
			errors.rejectValue(DevpSysOpsTaskDocker.PROPERTY_CREATE_UNAME,null,"创建用户姓名最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskDocker.getModifyUcode()) > 255){
			errors.rejectValue(DevpSysOpsTaskDocker.PROPERTY_MODIFY_UCODE,null,"修改用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskDocker.getModifyUname()) > 255){
			errors.rejectValue(DevpSysOpsTaskDocker.PROPERTY_MODIFY_UNAME,null,"修改用户姓名最长255个字符");
		}
	}
}