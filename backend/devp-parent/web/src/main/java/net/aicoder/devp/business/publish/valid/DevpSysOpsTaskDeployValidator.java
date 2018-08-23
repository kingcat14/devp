package net.aicoder.devp.business.publish.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskDeployAddDto;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskDeployEditDto;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskDeployCondition;
import net.aicoder.devp.business.publish.domain.DevpSysOpsTaskDeploy;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class DevpSysOpsTaskDeployValidator implements Validator {

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
	    if(obj instanceof DevpSysOpsTaskDeployAddDto){
            this.validateDevpSysOpsTaskDeployAddDto((DevpSysOpsTaskDeployAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<DevpSysOpsTaskDeployCondition> search){
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new DevpSysOpsTaskDeployCondition());
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param devpSysOpsTaskDeploy 组件部署设置
     * @param errors
     */
	public void validateDevpSysOpsTaskDeployAddDto(DevpSysOpsTaskDeployAddDto devpSysOpsTaskDeploy, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(devpSysOpsTaskDeploy.getEtype()) > 255){
			errors.rejectValue(DevpSysOpsTaskDeploy.PROPERTY_ETYPE,null,"元素类型最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskDeploy.getName()) > 255){
			errors.rejectValue(DevpSysOpsTaskDeploy.PROPERTY_NAME,null,"系统元素名称最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskDeploy.getCode()) > 255){
			errors.rejectValue(DevpSysOpsTaskDeploy.PROPERTY_CODE,null,"系统元素代码最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskDeploy.getAlias()) > 255){
			errors.rejectValue(DevpSysOpsTaskDeploy.PROPERTY_ALIAS,null,"系统元素别名最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskDeploy.getDescription()) > 255){
			errors.rejectValue(DevpSysOpsTaskDeploy.PROPERTY_DESCRIPTION,null,"系统元素描述最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskDeploy.getType()) > 255){
			errors.rejectValue(DevpSysOpsTaskDeploy.PROPERTY_TYPE,null,"类型最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskDeploy.getSubType()) > 255){
			errors.rejectValue(DevpSysOpsTaskDeploy.PROPERTY_SUB_TYPE,null,"子类型最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskDeploy.getDeployPath()) > 255){
			errors.rejectValue(DevpSysOpsTaskDeploy.PROPERTY_DEPLOY_PATH,null,"部署路径最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskDeploy.getBackupPath()) > 255){
			errors.rejectValue(DevpSysOpsTaskDeploy.PROPERTY_BACKUP_PATH,null,"备份路径最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskDeploy.getPreAction()) > 255){
			errors.rejectValue(DevpSysOpsTaskDeploy.PROPERTY_PRE_ACTION,null,"部署前操作最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskDeploy.getDeployAction()) > 255){
			errors.rejectValue(DevpSysOpsTaskDeploy.PROPERTY_DEPLOY_ACTION,null,"部署操作最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskDeploy.getPostAction()) > 255){
			errors.rejectValue(DevpSysOpsTaskDeploy.PROPERTY_POST_ACTION,null,"部署后操作最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskDeploy.getStatus()) > 255){
			errors.rejectValue(DevpSysOpsTaskDeploy.PROPERTY_STATUS,null,"状态最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskDeploy.getNotes()) > 255){
			errors.rejectValue(DevpSysOpsTaskDeploy.PROPERTY_NOTES,null,"备注最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskDeploy.getCreateUcode()) > 255){
			errors.rejectValue(DevpSysOpsTaskDeploy.PROPERTY_CREATE_UCODE,null,"创建用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskDeploy.getCreateUname()) > 255){
			errors.rejectValue(DevpSysOpsTaskDeploy.PROPERTY_CREATE_UNAME,null,"创建用户姓名最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskDeploy.getModifyUcode()) > 255){
			errors.rejectValue(DevpSysOpsTaskDeploy.PROPERTY_MODIFY_UCODE,null,"修改用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskDeploy.getModifyUname()) > 255){
			errors.rejectValue(DevpSysOpsTaskDeploy.PROPERTY_MODIFY_UNAME,null,"修改用户姓名最长255个字符");
		}
	}
}