package net.aicoder.devp.business.publish.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.business.publish.dto.DevpSysOpsDockerParamAddDto;
import net.aicoder.devp.business.publish.dto.DevpSysOpsDockerParamEditDto;
import net.aicoder.devp.business.publish.dto.DevpSysOpsDockerParamCondition;
import net.aicoder.devp.business.publish.domain.DevpSysOpsDockerParam;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class DevpSysOpsDockerParamValidator implements Validator {

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
	    if(obj instanceof DevpSysOpsDockerParamAddDto){
            this.validateDevpSysOpsDockerParamAddDto((DevpSysOpsDockerParamAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<DevpSysOpsDockerParamCondition> search){
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new DevpSysOpsDockerParamCondition());
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param devpSysOpsDockerParam 部署容器参数定义
     * @param errors
     */
	public void validateDevpSysOpsDockerParamAddDto(DevpSysOpsDockerParamAddDto devpSysOpsDockerParam, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(devpSysOpsDockerParam.getEtype()) > 255){
			errors.rejectValue(DevpSysOpsDockerParam.PROPERTY_ETYPE,null,"元素类型最长255个字符");
		}
		if(StringUtils.length(devpSysOpsDockerParam.getName()) > 255){
			errors.rejectValue(DevpSysOpsDockerParam.PROPERTY_NAME,null,"系统元素名称最长255个字符");
		}
		if(StringUtils.length(devpSysOpsDockerParam.getCode()) > 255){
			errors.rejectValue(DevpSysOpsDockerParam.PROPERTY_CODE,null,"参数代码最长255个字符");
		}
		if(StringUtils.length(devpSysOpsDockerParam.getAlias()) > 255){
			errors.rejectValue(DevpSysOpsDockerParam.PROPERTY_ALIAS,null,"系统元素别名最长255个字符");
		}
		if(StringUtils.length(devpSysOpsDockerParam.getDescription()) > 255){
			errors.rejectValue(DevpSysOpsDockerParam.PROPERTY_DESCRIPTION,null,"系统元素描述最长255个字符");
		}
		if(StringUtils.length(devpSysOpsDockerParam.getType()) > 255){
			errors.rejectValue(DevpSysOpsDockerParam.PROPERTY_TYPE,null,"类型最长255个字符");
		}
		if(StringUtils.length(devpSysOpsDockerParam.getSubType()) > 255){
			errors.rejectValue(DevpSysOpsDockerParam.PROPERTY_SUB_TYPE,null,"子类型最长255个字符");
		}
		if(StringUtils.length(devpSysOpsDockerParam.getValue()) > 255){
			errors.rejectValue(DevpSysOpsDockerParam.PROPERTY_VALUE,null,"参数值最长255个字符");
		}
		if(StringUtils.length(devpSysOpsDockerParam.getNotes()) > 255){
			errors.rejectValue(DevpSysOpsDockerParam.PROPERTY_NOTES,null,"备注最长255个字符");
		}
		if(StringUtils.length(devpSysOpsDockerParam.getCreateUcode()) > 255){
			errors.rejectValue(DevpSysOpsDockerParam.PROPERTY_CREATE_UCODE,null,"创建用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysOpsDockerParam.getCreateUname()) > 255){
			errors.rejectValue(DevpSysOpsDockerParam.PROPERTY_CREATE_UNAME,null,"创建用户姓名最长255个字符");
		}
		if(StringUtils.length(devpSysOpsDockerParam.getModifyUcode()) > 255){
			errors.rejectValue(DevpSysOpsDockerParam.PROPERTY_MODIFY_UCODE,null,"修改用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysOpsDockerParam.getModifyUname()) > 255){
			errors.rejectValue(DevpSysOpsDockerParam.PROPERTY_MODIFY_UNAME,null,"修改用户姓名最长255个字符");
		}
	}
}