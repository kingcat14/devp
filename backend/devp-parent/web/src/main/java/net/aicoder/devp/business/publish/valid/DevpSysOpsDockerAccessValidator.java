package net.aicoder.devp.business.publish.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.business.publish.dto.DevpSysOpsDockerAccessAddDto;
import net.aicoder.devp.business.publish.dto.DevpSysOpsDockerAccessEditDto;
import net.aicoder.devp.business.publish.dto.DevpSysOpsDockerAccessCondition;
import net.aicoder.devp.business.publish.domain.DevpSysOpsDockerAccess;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class DevpSysOpsDockerAccessValidator implements Validator {

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
	    if(obj instanceof DevpSysOpsDockerAccessAddDto){
            this.validateDevpSysOpsDockerAccessAddDto((DevpSysOpsDockerAccessAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<DevpSysOpsDockerAccessCondition> search){
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new DevpSysOpsDockerAccessCondition());
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param devpSysOpsDockerAccess 部署容器访问参数
     * @param errors
     */
	public void validateDevpSysOpsDockerAccessAddDto(DevpSysOpsDockerAccessAddDto devpSysOpsDockerAccess, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(devpSysOpsDockerAccess.getEtype()) > 255){
			errors.rejectValue(DevpSysOpsDockerAccess.PROPERTY_ETYPE,null,"元素类型最长255个字符");
		}
		if(StringUtils.length(devpSysOpsDockerAccess.getName()) > 255){
			errors.rejectValue(DevpSysOpsDockerAccess.PROPERTY_NAME,null,"系统元素名称最长255个字符");
		}
		if(StringUtils.length(devpSysOpsDockerAccess.getCode()) > 255){
			errors.rejectValue(DevpSysOpsDockerAccess.PROPERTY_CODE,null,"系统元素代码最长255个字符");
		}
		if(StringUtils.length(devpSysOpsDockerAccess.getAlias()) > 255){
			errors.rejectValue(DevpSysOpsDockerAccess.PROPERTY_ALIAS,null,"系统元素别名最长255个字符");
		}
		if(StringUtils.length(devpSysOpsDockerAccess.getDescription()) > 255){
			errors.rejectValue(DevpSysOpsDockerAccess.PROPERTY_DESCRIPTION,null,"系统元素描述最长255个字符");
		}
		if(StringUtils.length(devpSysOpsDockerAccess.getType()) > 255){
			errors.rejectValue(DevpSysOpsDockerAccess.PROPERTY_TYPE,null,"类型最长255个字符");
		}
		if(StringUtils.length(devpSysOpsDockerAccess.getNotes()) > 255){
			errors.rejectValue(DevpSysOpsDockerAccess.PROPERTY_NOTES,null,"备注最长255个字符");
		}
		if(StringUtils.length(devpSysOpsDockerAccess.getProtocol()) > 255){
			errors.rejectValue(DevpSysOpsDockerAccess.PROPERTY_PROTOCOL,null,"协议最长255个字符");
		}
		if(StringUtils.length(devpSysOpsDockerAccess.getCreateUcode()) > 255){
			errors.rejectValue(DevpSysOpsDockerAccess.PROPERTY_CREATE_UCODE,null,"创建用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysOpsDockerAccess.getCreateUname()) > 255){
			errors.rejectValue(DevpSysOpsDockerAccess.PROPERTY_CREATE_UNAME,null,"创建用户姓名最长255个字符");
		}
		if(StringUtils.length(devpSysOpsDockerAccess.getModifyUcode()) > 255){
			errors.rejectValue(DevpSysOpsDockerAccess.PROPERTY_MODIFY_UCODE,null,"修改用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysOpsDockerAccess.getModifyUname()) > 255){
			errors.rejectValue(DevpSysOpsDockerAccess.PROPERTY_MODIFY_UNAME,null,"修改用户姓名最长255个字符");
		}
	}
}