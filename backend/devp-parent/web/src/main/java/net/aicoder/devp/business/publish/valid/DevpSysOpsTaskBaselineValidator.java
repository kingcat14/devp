package net.aicoder.devp.business.publish.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskBaselineAddDto;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskBaselineEditDto;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskBaselineCondition;
import net.aicoder.devp.business.publish.domain.DevpSysOpsTaskBaseline;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class DevpSysOpsTaskBaselineValidator implements Validator {

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
	    if(obj instanceof DevpSysOpsTaskBaselineAddDto){
            this.validateDevpSysOpsTaskBaselineAddDto((DevpSysOpsTaskBaselineAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<DevpSysOpsTaskBaselineCondition> search){
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new DevpSysOpsTaskBaselineCondition());
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param devpSysOpsTaskBaseline 基线设置
     * @param errors
     */
	public void validateDevpSysOpsTaskBaselineAddDto(DevpSysOpsTaskBaselineAddDto devpSysOpsTaskBaseline, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(devpSysOpsTaskBaseline.getEtype()) > 255){
			errors.rejectValue(DevpSysOpsTaskBaseline.PROPERTY_ETYPE,null,"元素类型最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskBaseline.getName()) > 255){
			errors.rejectValue(DevpSysOpsTaskBaseline.PROPERTY_NAME,null,"系统元素名称最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskBaseline.getCode()) > 255){
			errors.rejectValue(DevpSysOpsTaskBaseline.PROPERTY_CODE,null,"系统元素代码最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskBaseline.getAlias()) > 255){
			errors.rejectValue(DevpSysOpsTaskBaseline.PROPERTY_ALIAS,null,"系统元素别名最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskBaseline.getDescription()) > 255){
			errors.rejectValue(DevpSysOpsTaskBaseline.PROPERTY_DESCRIPTION,null,"系统元素描述最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskBaseline.getType()) > 255){
			errors.rejectValue(DevpSysOpsTaskBaseline.PROPERTY_TYPE,null,"类型最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskBaseline.getSubType()) > 255){
			errors.rejectValue(DevpSysOpsTaskBaseline.PROPERTY_SUB_TYPE,null,"子类型最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskBaseline.getCmType()) > 255){
			errors.rejectValue(DevpSysOpsTaskBaseline.PROPERTY_CM_TYPE,null,"代码仓库类型最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskBaseline.getCmRepository()) > 255){
			errors.rejectValue(DevpSysOpsTaskBaseline.PROPERTY_CM_REPOSITORY,null,"仓库地址最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskBaseline.getCmTag()) > 255){
			errors.rejectValue(DevpSysOpsTaskBaseline.PROPERTY_CM_TAG,null,"分支标识最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskBaseline.getCmUser()) > 255){
			errors.rejectValue(DevpSysOpsTaskBaseline.PROPERTY_CM_USER,null,"用户名最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskBaseline.getCmPassword()) > 255){
			errors.rejectValue(DevpSysOpsTaskBaseline.PROPERTY_CM_PASSWORD,null,"密码最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskBaseline.getSubDir()) > 255){
			errors.rejectValue(DevpSysOpsTaskBaseline.PROPERTY_SUB_DIR,null,"子目录最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskBaseline.getBaseline()) > 255){
			errors.rejectValue(DevpSysOpsTaskBaseline.PROPERTY_BASELINE,null,"基线标识最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskBaseline.getSvnFromUrl()) > 255){
			errors.rejectValue(DevpSysOpsTaskBaseline.PROPERTY_SVN_FROM_URL,null,"来源代码路径最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskBaseline.getSvnToUrl()) > 255){
			errors.rejectValue(DevpSysOpsTaskBaseline.PROPERTY_SVN_TO_URL,null,"目标基线路径最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskBaseline.getOperType()) > 255){
			errors.rejectValue(DevpSysOpsTaskBaseline.PROPERTY_OPER_TYPE,null,"操作方式最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskBaseline.getStatus()) > 255){
			errors.rejectValue(DevpSysOpsTaskBaseline.PROPERTY_STATUS,null,"状态最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskBaseline.getNotes()) > 255){
			errors.rejectValue(DevpSysOpsTaskBaseline.PROPERTY_NOTES,null,"备注最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskBaseline.getCreateUcode()) > 255){
			errors.rejectValue(DevpSysOpsTaskBaseline.PROPERTY_CREATE_UCODE,null,"创建用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskBaseline.getCreateUname()) > 255){
			errors.rejectValue(DevpSysOpsTaskBaseline.PROPERTY_CREATE_UNAME,null,"创建用户姓名最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskBaseline.getModifyUcode()) > 255){
			errors.rejectValue(DevpSysOpsTaskBaseline.PROPERTY_MODIFY_UCODE,null,"修改用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskBaseline.getModifyUname()) > 255){
			errors.rejectValue(DevpSysOpsTaskBaseline.PROPERTY_MODIFY_UNAME,null,"修改用户姓名最长255个字符");
		}
	}
}