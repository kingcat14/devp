package net.aicoder.devp.business.sys.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.business.sys.dto.DevpSysIdeOutputAddDto;
import net.aicoder.devp.business.sys.dto.DevpSysIdeOutputEditDto;
import net.aicoder.devp.business.sys.dto.DevpSysIdeOutputCondition;
import net.aicoder.devp.business.sys.domain.DevpSysIdeOutput;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class DevpSysIdeOutputValidator implements Validator {

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
	    if(obj instanceof DevpSysIdeOutputAddDto){
            this.validateDevpSysIdeOutputAddDto((DevpSysIdeOutputAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<DevpSysIdeOutputCondition> search){
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new DevpSysIdeOutputCondition());
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param devpSysIdeOutput 开发工程产出组件
     * @param errors
     */
	public void validateDevpSysIdeOutputAddDto(DevpSysIdeOutputAddDto devpSysIdeOutput, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(devpSysIdeOutput.getEtype()) > 255){
			errors.rejectValue(DevpSysIdeOutput.PROPERTY_ETYPE,null,"元素类型最长255个字符");
		}
		if(StringUtils.length(devpSysIdeOutput.getName()) > 255){
			errors.rejectValue(DevpSysIdeOutput.PROPERTY_NAME,null,"系统元素名称最长255个字符");
		}
		if(StringUtils.length(devpSysIdeOutput.getCode()) > 255){
			errors.rejectValue(DevpSysIdeOutput.PROPERTY_CODE,null,"系统元素代码最长255个字符");
		}
		if(StringUtils.length(devpSysIdeOutput.getAlias()) > 255){
			errors.rejectValue(DevpSysIdeOutput.PROPERTY_ALIAS,null,"系统元素别名最长255个字符");
		}
		if(StringUtils.length(devpSysIdeOutput.getDescription()) > 255){
			errors.rejectValue(DevpSysIdeOutput.PROPERTY_DESCRIPTION,null,"系统元素描述最长255个字符");
		}
		if(StringUtils.length(devpSysIdeOutput.getType()) > 255){
			errors.rejectValue(DevpSysIdeOutput.PROPERTY_TYPE,null,"类型最长255个字符");
		}
		if(StringUtils.length(devpSysIdeOutput.getSubType()) > 255){
			errors.rejectValue(DevpSysIdeOutput.PROPERTY_SUB_TYPE,null,"子类型最长255个字符");
		}
		if(StringUtils.length(devpSysIdeOutput.getCreateUcode()) > 255){
			errors.rejectValue(DevpSysIdeOutput.PROPERTY_CREATE_UCODE,null,"创建用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysIdeOutput.getCreateUname()) > 255){
			errors.rejectValue(DevpSysIdeOutput.PROPERTY_CREATE_UNAME,null,"创建用户姓名最长255个字符");
		}
		if(StringUtils.length(devpSysIdeOutput.getModifyUcode()) > 255){
			errors.rejectValue(DevpSysIdeOutput.PROPERTY_MODIFY_UCODE,null,"修改用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysIdeOutput.getModifyUname()) > 255){
			errors.rejectValue(DevpSysIdeOutput.PROPERTY_MODIFY_UNAME,null,"修改用户姓名最长255个字符");
		}
	}
}