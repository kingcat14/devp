package net.aicoder.devp.business.deploy.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.business.deploy.dto.DevpSysDpyHostAddDto;
import net.aicoder.devp.business.deploy.dto.DevpSysDpyHostEditDto;
import net.aicoder.devp.business.deploy.domain.DevpSysDpyHost;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class DevpSysDpyHostValidator implements Validator {

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
	    if(obj instanceof DevpSysDpyHostAddDto){
            this.validateDevpSysDpyHostAddDto((DevpSysDpyHostAddDto)obj, errors);
        }
	}

	/**
     * 实现Validator中的validate接口
     * @param devpSysDpyHost 部署主机节点
     * @param errors
     */
	public void validateDevpSysDpyHostAddDto(DevpSysDpyHostAddDto devpSysDpyHost, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(devpSysDpyHost.getEtype()) > 255){
			errors.rejectValue(DevpSysDpyHost.PROPERTY_ETYPE,null,"元素类型最长255个字符");
		}
		if(StringUtils.length(devpSysDpyHost.getName()) > 255){
			errors.rejectValue(DevpSysDpyHost.PROPERTY_NAME,null,"系统元素名称最长255个字符");
		}
		if(StringUtils.length(devpSysDpyHost.getCode()) > 255){
			errors.rejectValue(DevpSysDpyHost.PROPERTY_CODE,null,"系统元素代码最长255个字符");
		}
		if(StringUtils.length(devpSysDpyHost.getAlias()) > 255){
			errors.rejectValue(DevpSysDpyHost.PROPERTY_ALIAS,null,"系统元素别名最长255个字符");
		}
		if(StringUtils.length(devpSysDpyHost.getDescription()) > 255){
			errors.rejectValue(DevpSysDpyHost.PROPERTY_DESCRIPTION,null,"系统元素描述最长255个字符");
		}
		if(StringUtils.length(devpSysDpyHost.getFlag()) > 255){
			errors.rejectValue(DevpSysDpyHost.PROPERTY_FLAG,null,"主机标识最长255个字符");
		}
		if(StringUtils.length(devpSysDpyHost.getType()) > 255){
			errors.rejectValue(DevpSysDpyHost.PROPERTY_TYPE,null,"类型最长255个字符");
		}
		if(StringUtils.length(devpSysDpyHost.getSubType()) > 255){
			errors.rejectValue(DevpSysDpyHost.PROPERTY_SUB_TYPE,null,"子类型最长255个字符");
		}
		if(StringUtils.length(devpSysDpyHost.getStatus()) > 255){
			errors.rejectValue(DevpSysDpyHost.PROPERTY_STATUS,null,"状态最长255个字符");
		}
		if(StringUtils.length(devpSysDpyHost.getIntAccessAddr()) > 255){
			errors.rejectValue(DevpSysDpyHost.PROPERTY_INT_ACCESS_ADDR,null,"内部访问地址最长255个字符");
		}
		if(StringUtils.length(devpSysDpyHost.getExtAccessAddr()) > 255){
			errors.rejectValue(DevpSysDpyHost.PROPERTY_EXT_ACCESS_ADDR,null,"外部访问地址最长255个字符");
		}
		if(StringUtils.length(devpSysDpyHost.getNotes()) > 255){
			errors.rejectValue(DevpSysDpyHost.PROPERTY_NOTES,null,"备注最长255个字符");
		}
		if(StringUtils.length(devpSysDpyHost.getAssetEtype()) > 255){
			errors.rejectValue(DevpSysDpyHost.PROPERTY_ASSET_ETYPE,null,"关联IT资产元素类型最长255个字符");
		}
		if(StringUtils.length(devpSysDpyHost.getAssetTypeCode()) > 255){
			errors.rejectValue(DevpSysDpyHost.PROPERTY_ASSET_TYPE_CODE,null,"关联IT资产类型代码最长255个字符");
		}
		if(StringUtils.length(devpSysDpyHost.getCreateUcode()) > 255){
			errors.rejectValue(DevpSysDpyHost.PROPERTY_CREATE_UCODE,null,"创建用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysDpyHost.getCreateUname()) > 255){
			errors.rejectValue(DevpSysDpyHost.PROPERTY_CREATE_UNAME,null,"创建用户姓名最长255个字符");
		}
		if(StringUtils.length(devpSysDpyHost.getModifyUcode()) > 255){
			errors.rejectValue(DevpSysDpyHost.PROPERTY_MODIFY_UCODE,null,"修改用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysDpyHost.getModifyUname()) > 255){
			errors.rejectValue(DevpSysDpyHost.PROPERTY_MODIFY_UNAME,null,"修改用户姓名最长255个字符");
		}
	}
}