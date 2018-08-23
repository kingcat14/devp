package net.aicoder.devp.business.sys.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.business.sys.dto.DevpSysOpsCmpTaskAddDto;
import net.aicoder.devp.business.sys.dto.DevpSysOpsCmpTaskEditDto;
import net.aicoder.devp.business.sys.dto.DevpSysOpsCmpTaskCondition;
import net.aicoder.devp.business.sys.domain.DevpSysOpsCmpTask;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class DevpSysOpsCmpTaskValidator implements Validator {

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
	    if(obj instanceof DevpSysOpsCmpTaskAddDto){
            this.validateDevpSysOpsCmpTaskAddDto((DevpSysOpsCmpTaskAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<DevpSysOpsCmpTaskCondition> search){
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new DevpSysOpsCmpTaskCondition());
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param devpSysOpsCmpTask 组件任务
     * @param errors
     */
	public void validateDevpSysOpsCmpTaskAddDto(DevpSysOpsCmpTaskAddDto devpSysOpsCmpTask, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(devpSysOpsCmpTask.getEtype()) > 255){
			errors.rejectValue(DevpSysOpsCmpTask.PROPERTY_ETYPE,null,"元素类型最长255个字符");
		}
		if(StringUtils.length(devpSysOpsCmpTask.getName()) > 255){
			errors.rejectValue(DevpSysOpsCmpTask.PROPERTY_NAME,null,"系统元素名称最长255个字符");
		}
		if(StringUtils.length(devpSysOpsCmpTask.getCode()) > 255){
			errors.rejectValue(DevpSysOpsCmpTask.PROPERTY_CODE,null,"系统元素代码最长255个字符");
		}
		if(StringUtils.length(devpSysOpsCmpTask.getAlias()) > 255){
			errors.rejectValue(DevpSysOpsCmpTask.PROPERTY_ALIAS,null,"系统元素别名最长255个字符");
		}
		if(StringUtils.length(devpSysOpsCmpTask.getDescription()) > 255){
			errors.rejectValue(DevpSysOpsCmpTask.PROPERTY_DESCRIPTION,null,"系统元素描述最长255个字符");
		}
		if(StringUtils.length(devpSysOpsCmpTask.getType()) > 255){
			errors.rejectValue(DevpSysOpsCmpTask.PROPERTY_TYPE,null,"类型最长255个字符");
		}
		if(StringUtils.length(devpSysOpsCmpTask.getSubType()) > 255){
			errors.rejectValue(DevpSysOpsCmpTask.PROPERTY_SUB_TYPE,null,"子类型最长255个字符");
		}
		if(StringUtils.length(devpSysOpsCmpTask.getNotes()) > 255){
			errors.rejectValue(DevpSysOpsCmpTask.PROPERTY_NOTES,null,"备注最长255个字符");
		}
		if(StringUtils.length(devpSysOpsCmpTask.getCreateUcode()) > 255){
			errors.rejectValue(DevpSysOpsCmpTask.PROPERTY_CREATE_UCODE,null,"创建用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysOpsCmpTask.getCreateUname()) > 255){
			errors.rejectValue(DevpSysOpsCmpTask.PROPERTY_CREATE_UNAME,null,"创建用户姓名最长255个字符");
		}
		if(StringUtils.length(devpSysOpsCmpTask.getModifyUcode()) > 255){
			errors.rejectValue(DevpSysOpsCmpTask.PROPERTY_MODIFY_UCODE,null,"修改用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysOpsCmpTask.getModifyUname()) > 255){
			errors.rejectValue(DevpSysOpsCmpTask.PROPERTY_MODIFY_UNAME,null,"修改用户姓名最长255个字符");
		}
	}
}