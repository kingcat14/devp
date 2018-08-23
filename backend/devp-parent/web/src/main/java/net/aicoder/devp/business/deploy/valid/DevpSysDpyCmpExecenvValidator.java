package net.aicoder.devp.business.deploy.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.business.deploy.dto.DevpSysDpyCmpExecenvAddDto;
import net.aicoder.devp.business.deploy.dto.DevpSysDpyCmpExecenvEditDto;
import net.aicoder.devp.business.deploy.dto.DevpSysDpyCmpExecenvCondition;
import net.aicoder.devp.business.deploy.domain.DevpSysDpyCmpExecenv;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class DevpSysDpyCmpExecenvValidator implements Validator {

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
	    if(obj instanceof DevpSysDpyCmpExecenvAddDto){
            this.validateDevpSysDpyCmpExecenvAddDto((DevpSysDpyCmpExecenvAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<DevpSysDpyCmpExecenvCondition> search){
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new DevpSysDpyCmpExecenvCondition());
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param devpSysDpyCmpExecenv 组件部署环境节点
     * @param errors
     */
	public void validateDevpSysDpyCmpExecenvAddDto(DevpSysDpyCmpExecenvAddDto devpSysDpyCmpExecenv, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(devpSysDpyCmpExecenv.getEtype()) > 255){
			errors.rejectValue(DevpSysDpyCmpExecenv.PROPERTY_ETYPE,null,"元素类型最长255个字符");
		}
		if(StringUtils.length(devpSysDpyCmpExecenv.getName()) > 255){
			errors.rejectValue(DevpSysDpyCmpExecenv.PROPERTY_NAME,null,"系统元素名称最长255个字符");
		}
		if(StringUtils.length(devpSysDpyCmpExecenv.getCode()) > 255){
			errors.rejectValue(DevpSysDpyCmpExecenv.PROPERTY_CODE,null,"系统元素代码最长255个字符");
		}
		if(StringUtils.length(devpSysDpyCmpExecenv.getAlias()) > 255){
			errors.rejectValue(DevpSysDpyCmpExecenv.PROPERTY_ALIAS,null,"系统元素别名最长255个字符");
		}
		if(StringUtils.length(devpSysDpyCmpExecenv.getDescription()) > 255){
			errors.rejectValue(DevpSysDpyCmpExecenv.PROPERTY_DESCRIPTION,null,"系统元素描述最长255个字符");
		}
		if(StringUtils.length(devpSysDpyCmpExecenv.getType()) > 255){
			errors.rejectValue(DevpSysDpyCmpExecenv.PROPERTY_TYPE,null,"类型最长255个字符");
		}
		if(StringUtils.length(devpSysDpyCmpExecenv.getSubType()) > 255){
			errors.rejectValue(DevpSysDpyCmpExecenv.PROPERTY_SUB_TYPE,null,"子类型最长255个字符");
		}
		if(StringUtils.length(devpSysDpyCmpExecenv.getStatus()) > 255){
			errors.rejectValue(DevpSysDpyCmpExecenv.PROPERTY_STATUS,null,"状态最长255个字符");
		}
		if(StringUtils.length(devpSysDpyCmpExecenv.getNotes()) > 255){
			errors.rejectValue(DevpSysDpyCmpExecenv.PROPERTY_NOTES,null,"备注最长255个字符");
		}
		if(StringUtils.length(devpSysDpyCmpExecenv.getCreateUcode()) > 255){
			errors.rejectValue(DevpSysDpyCmpExecenv.PROPERTY_CREATE_UCODE,null,"创建用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysDpyCmpExecenv.getCreateUname()) > 255){
			errors.rejectValue(DevpSysDpyCmpExecenv.PROPERTY_CREATE_UNAME,null,"创建用户姓名最长255个字符");
		}
		if(StringUtils.length(devpSysDpyCmpExecenv.getModifyUcode()) > 255){
			errors.rejectValue(DevpSysDpyCmpExecenv.PROPERTY_MODIFY_UCODE,null,"修改用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysDpyCmpExecenv.getModifyUname()) > 255){
			errors.rejectValue(DevpSysDpyCmpExecenv.PROPERTY_MODIFY_UNAME,null,"修改用户姓名最长255个字符");
		}
	}
}