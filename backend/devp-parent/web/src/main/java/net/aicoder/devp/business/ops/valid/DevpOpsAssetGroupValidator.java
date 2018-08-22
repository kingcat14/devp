package net.aicoder.devp.business.ops.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.business.ops.dto.DevpOpsAssetGroupAddDto;
import net.aicoder.devp.business.ops.dto.DevpOpsAssetGroupEditDto;
import net.aicoder.devp.business.ops.domain.DevpOpsAssetGroup;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class DevpOpsAssetGroupValidator implements Validator {

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
	    if(obj instanceof DevpOpsAssetGroupAddDto){
            this.validateDevpOpsAssetGroupAddDto((DevpOpsAssetGroupAddDto)obj, errors);
        }
	}

	/**
     * 实现Validator中的validate接口
     * @param devpOpsAssetGroup 资产分组
     * @param errors
     */
	public void validateDevpOpsAssetGroupAddDto(DevpOpsAssetGroupAddDto devpOpsAssetGroup, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(devpOpsAssetGroup.getEtype()) > 255){
			errors.rejectValue(DevpOpsAssetGroup.PROPERTY_ETYPE,null,"元素类型最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetGroup.getName()) > 255){
			errors.rejectValue(DevpOpsAssetGroup.PROPERTY_NAME,null,"名称最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetGroup.getCode()) > 255){
			errors.rejectValue(DevpOpsAssetGroup.PROPERTY_CODE,null,"代码最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetGroup.getAlias()) > 255){
			errors.rejectValue(DevpOpsAssetGroup.PROPERTY_ALIAS,null,"别名最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetGroup.getDescription()) > 255){
			errors.rejectValue(DevpOpsAssetGroup.PROPERTY_DESCRIPTION,null,"描述最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetGroup.getTypeCode()) > 255){
			errors.rejectValue(DevpOpsAssetGroup.PROPERTY_TYPE_CODE,null,"类型代码最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetGroup.getTypeName()) > 255){
			errors.rejectValue(DevpOpsAssetGroup.PROPERTY_TYPE_NAME,null,"类型名称最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetGroup.getStereotype()) > 255){
			errors.rejectValue(DevpOpsAssetGroup.PROPERTY_STEREOTYPE,null,"构造型最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetGroup.getScope()) > 255){
			errors.rejectValue(DevpOpsAssetGroup.PROPERTY_SCOPE,null,"访问控制范围最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetGroup.getVersion()) > 255){
			errors.rejectValue(DevpOpsAssetGroup.PROPERTY_VERSION,null,"版本最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetGroup.getPhase()) > 255){
			errors.rejectValue(DevpOpsAssetGroup.PROPERTY_PHASE,null,"阶段最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetGroup.getStatus()) > 255){
			errors.rejectValue(DevpOpsAssetGroup.PROPERTY_STATUS,null,"状态最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetGroup.getParasCode()) > 255){
			errors.rejectValue(DevpOpsAssetGroup.PROPERTY_PARAS_CODE,null,"参数定义标识最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetGroup.getCreateUcode()) > 255){
			errors.rejectValue(DevpOpsAssetGroup.PROPERTY_CREATE_UCODE,null,"创建用户代码最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetGroup.getCreateUname()) > 255){
			errors.rejectValue(DevpOpsAssetGroup.PROPERTY_CREATE_UNAME,null,"创建用户姓名最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetGroup.getModifyUcode()) > 255){
			errors.rejectValue(DevpOpsAssetGroup.PROPERTY_MODIFY_UCODE,null,"修改用户代码最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetGroup.getModifyUname()) > 255){
			errors.rejectValue(DevpOpsAssetGroup.PROPERTY_MODIFY_UNAME,null,"修改用户姓名最长255个字符");
		}
	}
}