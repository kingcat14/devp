package net.aicoder.devp.business.deploy.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.business.deploy.dto.DevpSysDpyResInstAddDto;
import net.aicoder.devp.business.deploy.dto.DevpSysDpyResInstEditDto;
import net.aicoder.devp.business.deploy.domain.DevpSysDpyResInst;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class DevpSysDpyResInstValidator implements Validator {

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
	    if(obj instanceof DevpSysDpyResInstAddDto){
            this.validateDevpSysDpyResInstAddDto((DevpSysDpyResInstAddDto)obj, errors);
        }
	}

	/**
     * 实现Validator中的validate接口
     * @param devpSysDpyResInst 部署关联资源实例
     * @param errors
     */
	public void validateDevpSysDpyResInstAddDto(DevpSysDpyResInstAddDto devpSysDpyResInst, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(devpSysDpyResInst.getEtype()) > 255){
			errors.rejectValue(DevpSysDpyResInst.PROPERTY_ETYPE,null,"元素类型最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResInst.getName()) > 255){
			errors.rejectValue(DevpSysDpyResInst.PROPERTY_NAME,null,"系统元素名称最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResInst.getCode()) > 255){
			errors.rejectValue(DevpSysDpyResInst.PROPERTY_CODE,null,"系统元素代码最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResInst.getAlias()) > 255){
			errors.rejectValue(DevpSysDpyResInst.PROPERTY_ALIAS,null,"系统元素别名最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResInst.getDescription()) > 255){
			errors.rejectValue(DevpSysDpyResInst.PROPERTY_DESCRIPTION,null,"系统元素描述最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResInst.getFlag()) > 255){
			errors.rejectValue(DevpSysDpyResInst.PROPERTY_FLAG,null,"资源实例标识最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResInst.getType()) > 255){
			errors.rejectValue(DevpSysDpyResInst.PROPERTY_TYPE,null,"类型最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResInst.getSubType()) > 255){
			errors.rejectValue(DevpSysDpyResInst.PROPERTY_SUB_TYPE,null,"子类型最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResInst.getDpyModel()) > 255){
			errors.rejectValue(DevpSysDpyResInst.PROPERTY_DPY_MODEL,null,"部署模式最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResInst.getDpyDescription()) > 255){
			errors.rejectValue(DevpSysDpyResInst.PROPERTY_DPY_DESCRIPTION,null,"部署说明最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResInst.getIntAccessAddr()) > 255){
			errors.rejectValue(DevpSysDpyResInst.PROPERTY_INT_ACCESS_ADDR,null,"访问地址最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResInst.getExtAccessAddr()) > 255){
			errors.rejectValue(DevpSysDpyResInst.PROPERTY_EXT_ACCESS_ADDR,null,"访问地址最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResInst.getStatus()) > 255){
			errors.rejectValue(DevpSysDpyResInst.PROPERTY_STATUS,null,"状态最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResInst.getInitScript()) > 255){
			errors.rejectValue(DevpSysDpyResInst.PROPERTY_INIT_SCRIPT,null,"初始化脚本最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResInst.getCompileScript()) > 255){
			errors.rejectValue(DevpSysDpyResInst.PROPERTY_COMPILE_SCRIPT,null,"编译期配置文件最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResInst.getDpyScript()) > 255){
			errors.rejectValue(DevpSysDpyResInst.PROPERTY_DPY_SCRIPT,null,"部署期配置文件最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResInst.getNotes()) > 255){
			errors.rejectValue(DevpSysDpyResInst.PROPERTY_NOTES,null,"备注最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResInst.getAssetEtype()) > 255){
			errors.rejectValue(DevpSysDpyResInst.PROPERTY_ASSET_ETYPE,null,"关联IT资产元素类型最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResInst.getAssetTypeCode()) > 255){
			errors.rejectValue(DevpSysDpyResInst.PROPERTY_ASSET_TYPE_CODE,null,"关联IT资产类型代码最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResInst.getCreateUcode()) > 255){
			errors.rejectValue(DevpSysDpyResInst.PROPERTY_CREATE_UCODE,null,"创建用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResInst.getCreateUname()) > 255){
			errors.rejectValue(DevpSysDpyResInst.PROPERTY_CREATE_UNAME,null,"创建用户姓名最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResInst.getModifyUcode()) > 255){
			errors.rejectValue(DevpSysDpyResInst.PROPERTY_MODIFY_UCODE,null,"修改用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResInst.getModifyUname()) > 255){
			errors.rejectValue(DevpSysDpyResInst.PROPERTY_MODIFY_UNAME,null,"修改用户姓名最长255个字符");
		}
	}
}