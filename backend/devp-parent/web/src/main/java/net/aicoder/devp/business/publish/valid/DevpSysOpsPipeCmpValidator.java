package net.aicoder.devp.business.publish.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.business.publish.dto.DevpSysOpsPipeCmpAddDto;
import net.aicoder.devp.business.publish.dto.DevpSysOpsPipeCmpEditDto;
import net.aicoder.devp.business.publish.dto.DevpSysOpsPipeCmpCondition;
import net.aicoder.devp.business.publish.domain.DevpSysOpsPipeCmp;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class DevpSysOpsPipeCmpValidator implements Validator {

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
	    if(obj instanceof DevpSysOpsPipeCmpAddDto){
            this.validateDevpSysOpsPipeCmpAddDto((DevpSysOpsPipeCmpAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<DevpSysOpsPipeCmpCondition> search){
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new DevpSysOpsPipeCmpCondition());
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param devpSysOpsPipeCmp 产品运维流水线对应的组件
     * @param errors
     */
	public void validateDevpSysOpsPipeCmpAddDto(DevpSysOpsPipeCmpAddDto devpSysOpsPipeCmp, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(devpSysOpsPipeCmp.getEtype()) > 255){
			errors.rejectValue(DevpSysOpsPipeCmp.PROPERTY_ETYPE,null,"元素类型最长255个字符");
		}
		if(StringUtils.length(devpSysOpsPipeCmp.getName()) > 255){
			errors.rejectValue(DevpSysOpsPipeCmp.PROPERTY_NAME,null,"系统元素名称最长255个字符");
		}
		if(StringUtils.length(devpSysOpsPipeCmp.getCode()) > 255){
			errors.rejectValue(DevpSysOpsPipeCmp.PROPERTY_CODE,null,"系统元素代码最长255个字符");
		}
		if(StringUtils.length(devpSysOpsPipeCmp.getAlias()) > 255){
			errors.rejectValue(DevpSysOpsPipeCmp.PROPERTY_ALIAS,null,"系统元素别名最长255个字符");
		}
		if(StringUtils.length(devpSysOpsPipeCmp.getDescription()) > 255){
			errors.rejectValue(DevpSysOpsPipeCmp.PROPERTY_DESCRIPTION,null,"系统元素描述最长255个字符");
		}
		if(StringUtils.length(devpSysOpsPipeCmp.getType()) > 255){
			errors.rejectValue(DevpSysOpsPipeCmp.PROPERTY_TYPE,null,"类型最长255个字符");
		}
		if(StringUtils.length(devpSysOpsPipeCmp.getSubType()) > 255){
			errors.rejectValue(DevpSysOpsPipeCmp.PROPERTY_SUB_TYPE,null,"子类型最长255个字符");
		}
		if(StringUtils.length(devpSysOpsPipeCmp.getCreateUcode()) > 255){
			errors.rejectValue(DevpSysOpsPipeCmp.PROPERTY_CREATE_UCODE,null,"创建用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysOpsPipeCmp.getCreateUname()) > 255){
			errors.rejectValue(DevpSysOpsPipeCmp.PROPERTY_CREATE_UNAME,null,"创建用户姓名最长255个字符");
		}
		if(StringUtils.length(devpSysOpsPipeCmp.getModifyUcode()) > 255){
			errors.rejectValue(DevpSysOpsPipeCmp.PROPERTY_MODIFY_UCODE,null,"修改用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysOpsPipeCmp.getModifyUname()) > 255){
			errors.rejectValue(DevpSysOpsPipeCmp.PROPERTY_MODIFY_UNAME,null,"修改用户姓名最长255个字符");
		}
	}
}