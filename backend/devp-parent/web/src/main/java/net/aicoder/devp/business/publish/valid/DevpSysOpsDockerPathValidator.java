package net.aicoder.devp.business.publish.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.business.publish.dto.DevpSysOpsDockerPathAddDto;
import net.aicoder.devp.business.publish.dto.DevpSysOpsDockerPathEditDto;
import net.aicoder.devp.business.publish.dto.DevpSysOpsDockerPathCondition;
import net.aicoder.devp.business.publish.domain.DevpSysOpsDockerPath;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class DevpSysOpsDockerPathValidator implements Validator {

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
	    if(obj instanceof DevpSysOpsDockerPathAddDto){
            this.validateDevpSysOpsDockerPathAddDto((DevpSysOpsDockerPathAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<DevpSysOpsDockerPathCondition> search){
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new DevpSysOpsDockerPathCondition());
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param devpSysOpsDockerPath 存储路径定义
     * @param errors
     */
	public void validateDevpSysOpsDockerPathAddDto(DevpSysOpsDockerPathAddDto devpSysOpsDockerPath, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(devpSysOpsDockerPath.getEtype()) > 255){
			errors.rejectValue(DevpSysOpsDockerPath.PROPERTY_ETYPE,null,"元素类型最长255个字符");
		}
		if(StringUtils.length(devpSysOpsDockerPath.getName()) > 255){
			errors.rejectValue(DevpSysOpsDockerPath.PROPERTY_NAME,null,"系统元素名称最长255个字符");
		}
		if(StringUtils.length(devpSysOpsDockerPath.getCode()) > 255){
			errors.rejectValue(DevpSysOpsDockerPath.PROPERTY_CODE,null,"系统元素代码最长255个字符");
		}
		if(StringUtils.length(devpSysOpsDockerPath.getAlias()) > 255){
			errors.rejectValue(DevpSysOpsDockerPath.PROPERTY_ALIAS,null,"系统元素别名最长255个字符");
		}
		if(StringUtils.length(devpSysOpsDockerPath.getDescription()) > 255){
			errors.rejectValue(DevpSysOpsDockerPath.PROPERTY_DESCRIPTION,null,"系统元素描述最长255个字符");
		}
		if(StringUtils.length(devpSysOpsDockerPath.getType()) > 255){
			errors.rejectValue(DevpSysOpsDockerPath.PROPERTY_TYPE,null,"类型最长255个字符");
		}
		if(StringUtils.length(devpSysOpsDockerPath.getSubType()) > 255){
			errors.rejectValue(DevpSysOpsDockerPath.PROPERTY_SUB_TYPE,null,"子类型最长255个字符");
		}
		if(StringUtils.length(devpSysOpsDockerPath.getDockerPath()) > 255){
			errors.rejectValue(DevpSysOpsDockerPath.PROPERTY_DOCKER_PATH,null,"容器挂载路径最长255个字符");
		}
		if(StringUtils.length(devpSysOpsDockerPath.getHostPath()) > 255){
			errors.rejectValue(DevpSysOpsDockerPath.PROPERTY_HOST_PATH,null,"属主机路径最长255个字符");
		}
		if(StringUtils.length(devpSysOpsDockerPath.getNotes()) > 255){
			errors.rejectValue(DevpSysOpsDockerPath.PROPERTY_NOTES,null,"备注最长255个字符");
		}
		if(StringUtils.length(devpSysOpsDockerPath.getCreateUcode()) > 255){
			errors.rejectValue(DevpSysOpsDockerPath.PROPERTY_CREATE_UCODE,null,"创建用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysOpsDockerPath.getCreateUname()) > 255){
			errors.rejectValue(DevpSysOpsDockerPath.PROPERTY_CREATE_UNAME,null,"创建用户姓名最长255个字符");
		}
		if(StringUtils.length(devpSysOpsDockerPath.getModifyUcode()) > 255){
			errors.rejectValue(DevpSysOpsDockerPath.PROPERTY_MODIFY_UCODE,null,"修改用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysOpsDockerPath.getModifyUname()) > 255){
			errors.rejectValue(DevpSysOpsDockerPath.PROPERTY_MODIFY_UNAME,null,"修改用户姓名最长255个字符");
		}
	}
}