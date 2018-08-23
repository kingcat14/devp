package net.aicoder.devp.business.publish.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskCompileAddDto;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskCompileEditDto;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskCompileCondition;
import net.aicoder.devp.business.publish.domain.DevpSysOpsTaskCompile;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class DevpSysOpsTaskCompileValidator implements Validator {

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
	    if(obj instanceof DevpSysOpsTaskCompileAddDto){
            this.validateDevpSysOpsTaskCompileAddDto((DevpSysOpsTaskCompileAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<DevpSysOpsTaskCompileCondition> search){
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new DevpSysOpsTaskCompileCondition());
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param devpSysOpsTaskCompile 编译设置
     * @param errors
     */
	public void validateDevpSysOpsTaskCompileAddDto(DevpSysOpsTaskCompileAddDto devpSysOpsTaskCompile, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(devpSysOpsTaskCompile.getEtype()) > 255){
			errors.rejectValue(DevpSysOpsTaskCompile.PROPERTY_ETYPE,null,"元素类型最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskCompile.getName()) > 255){
			errors.rejectValue(DevpSysOpsTaskCompile.PROPERTY_NAME,null,"系统元素名称最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskCompile.getCode()) > 255){
			errors.rejectValue(DevpSysOpsTaskCompile.PROPERTY_CODE,null,"系统元素代码最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskCompile.getAlias()) > 255){
			errors.rejectValue(DevpSysOpsTaskCompile.PROPERTY_ALIAS,null,"系统元素别名最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskCompile.getDescription()) > 255){
			errors.rejectValue(DevpSysOpsTaskCompile.PROPERTY_DESCRIPTION,null,"系统元素描述最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskCompile.getType()) > 255){
			errors.rejectValue(DevpSysOpsTaskCompile.PROPERTY_TYPE,null,"类型最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskCompile.getSubType()) > 255){
			errors.rejectValue(DevpSysOpsTaskCompile.PROPERTY_SUB_TYPE,null,"子类型最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskCompile.getCmType()) > 255){
			errors.rejectValue(DevpSysOpsTaskCompile.PROPERTY_CM_TYPE,null,"代码仓库类型最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskCompile.getCmRepository()) > 255){
			errors.rejectValue(DevpSysOpsTaskCompile.PROPERTY_CM_REPOSITORY,null,"仓库地址最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskCompile.getCmTag()) > 255){
			errors.rejectValue(DevpSysOpsTaskCompile.PROPERTY_CM_TAG,null,"分支标识最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskCompile.getCmUser()) > 255){
			errors.rejectValue(DevpSysOpsTaskCompile.PROPERTY_CM_USER,null,"用户名最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskCompile.getCmPassword()) > 255){
			errors.rejectValue(DevpSysOpsTaskCompile.PROPERTY_CM_PASSWORD,null,"密码最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskCompile.getSubDir()) > 255){
			errors.rejectValue(DevpSysOpsTaskCompile.PROPERTY_SUB_DIR,null,"子目录最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskCompile.getBaseline()) > 255){
			errors.rejectValue(DevpSysOpsTaskCompile.PROPERTY_BASELINE,null,"基线标识最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskCompile.getSvnCodeUrl()) > 255){
			errors.rejectValue(DevpSysOpsTaskCompile.PROPERTY_SVN_CODE_URL,null,"代码路径最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskCompile.getBuildTools()) > 255){
			errors.rejectValue(DevpSysOpsTaskCompile.PROPERTY_BUILD_TOOLS,null,"构建工具最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskCompile.getEnvVersion()) > 255){
			errors.rejectValue(DevpSysOpsTaskCompile.PROPERTY_ENV_VERSION,null,"执行环境版本最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskCompile.getToolVersion()) > 255){
			errors.rejectValue(DevpSysOpsTaskCompile.PROPERTY_TOOL_VERSION,null,"工具版本最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskCompile.getPreAction()) > 255){
			errors.rejectValue(DevpSysOpsTaskCompile.PROPERTY_PRE_ACTION,null,"构建前操作最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskCompile.getBuildAction()) > 255){
			errors.rejectValue(DevpSysOpsTaskCompile.PROPERTY_BUILD_ACTION,null,"构建操作最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskCompile.getPostAction()) > 255){
			errors.rejectValue(DevpSysOpsTaskCompile.PROPERTY_POST_ACTION,null,"构建后操作最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskCompile.getStatus()) > 255){
			errors.rejectValue(DevpSysOpsTaskCompile.PROPERTY_STATUS,null,"状态最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskCompile.getNotes()) > 255){
			errors.rejectValue(DevpSysOpsTaskCompile.PROPERTY_NOTES,null,"备注最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskCompile.getCreateUcode()) > 255){
			errors.rejectValue(DevpSysOpsTaskCompile.PROPERTY_CREATE_UCODE,null,"创建用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskCompile.getCreateUname()) > 255){
			errors.rejectValue(DevpSysOpsTaskCompile.PROPERTY_CREATE_UNAME,null,"创建用户姓名最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskCompile.getModifyUcode()) > 255){
			errors.rejectValue(DevpSysOpsTaskCompile.PROPERTY_MODIFY_UCODE,null,"修改用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskCompile.getModifyUname()) > 255){
			errors.rejectValue(DevpSysOpsTaskCompile.PROPERTY_MODIFY_UNAME,null,"修改用户姓名最长255个字符");
		}
	}
}