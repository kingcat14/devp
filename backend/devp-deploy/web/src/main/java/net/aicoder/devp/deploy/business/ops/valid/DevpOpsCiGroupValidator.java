package net.aicoder.devp.deploy.business.ops.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.deploy.business.ops.dto.DevpOpsCiGroupAddDto;
import net.aicoder.devp.deploy.business.ops.dto.DevpOpsCiGroupEditDto;
import net.aicoder.devp.deploy.business.ops.domain.DevpOpsCiGroup;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class DevpOpsCiGroupValidator implements Validator {

	/**
	 * 判断支持的JavaBean类型
	 * @param aClass
	 * @return
	 */
	@Override
	public boolean supports(Class<?> aClass) {
		if(DevpOpsCiGroupAddDto.class.equals(aClass))
			return true;
		if(DevpOpsCiGroupEditDto.class.equals(aClass))
			return true;
		if(PageSearchRequest.class.equals(aClass))
			return true;
		return false;
	}

	/**
	 * 实现Validator中的validate接口
	 * @param obj
	 * @param errors
	 */
	@Override
	public void validate(Object obj, Errors errors) {
	    if(obj instanceof DevpOpsCiGroupAddDto){
            this.validateDevpOpsCiGroupAddDto((DevpOpsCiGroupAddDto)obj, errors);
        }
	}

	/**
     * 实现Validator中的validate接口
     * @param devpOpsCiGroup 资产项目分组映射
     * @param errors
     */
	public void validateDevpOpsCiGroupAddDto(DevpOpsCiGroupAddDto devpOpsCiGroup, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if (null == devpOpsCiGroup.getTid() ) {
			errors.rejectValue(DevpOpsCiGroup.PROPERTY_TID, "EMPTY_"+DevpOpsCiGroup.PROPERTY_TID, "租户编号不能为空");
		}
		if(StringUtils.isEmpty(devpOpsCiGroup.getEtype())){
			errors.rejectValue(DevpOpsCiGroup.PROPERTY_ETYPE, "EMPTY_"+DevpOpsCiGroup.PROPERTY_ETYPE, "元素类型不能为空");
		}
		if (null == devpOpsCiGroup.getGroupRid() ) {
			errors.rejectValue(DevpOpsCiGroup.PROPERTY_GROUP_RID, "EMPTY_"+DevpOpsCiGroup.PROPERTY_GROUP_RID, "分组记录编号不能为空");
		}
		if (null == devpOpsCiGroup.getCiRid() ) {
			errors.rejectValue(DevpOpsCiGroup.PROPERTY_CI_RID, "EMPTY_"+DevpOpsCiGroup.PROPERTY_CI_RID, "资产记录编号不能为空");
		}

		//验证长度
		if(StringUtils.length(devpOpsCiGroup.getEtype()) > 255){
			errors.rejectValue(DevpOpsCiGroup.PROPERTY_ETYPE,null,"元素类型最长255个字符");
		}
		if(StringUtils.length(devpOpsCiGroup.getName()) > 255){
			errors.rejectValue(DevpOpsCiGroup.PROPERTY_NAME,null,"名称最长255个字符");
		}
		if(StringUtils.length(devpOpsCiGroup.getCode()) > 255){
			errors.rejectValue(DevpOpsCiGroup.PROPERTY_CODE,null,"代码最长255个字符");
		}
		if(StringUtils.length(devpOpsCiGroup.getAlias()) > 255){
			errors.rejectValue(DevpOpsCiGroup.PROPERTY_ALIAS,null,"别名最长255个字符");
		}
		if(StringUtils.length(devpOpsCiGroup.getDescription()) > 255){
			errors.rejectValue(DevpOpsCiGroup.PROPERTY_DESCRIPTION,null,"描述最长255个字符");
		}
		if(StringUtils.length(devpOpsCiGroup.getTypeCode()) > 255){
			errors.rejectValue(DevpOpsCiGroup.PROPERTY_TYPE_CODE,null,"类型代码最长255个字符");
		}
		if(StringUtils.length(devpOpsCiGroup.getTypeName()) > 255){
			errors.rejectValue(DevpOpsCiGroup.PROPERTY_TYPE_NAME,null,"类型名称最长255个字符");
		}
		if(StringUtils.length(devpOpsCiGroup.getParasCode()) > 255){
			errors.rejectValue(DevpOpsCiGroup.PROPERTY_PARAS_CODE,null,"参数定义标识最长255个字符");
		}
		if(StringUtils.length(devpOpsCiGroup.getCmodifyUcode()) > 255){
			errors.rejectValue(DevpOpsCiGroup.PROPERTY_CMODIFY_UCODE,null,"修改用户代码最长255个字符");
		}
	}
}