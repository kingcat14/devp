package net.aicoder.devp.deploy.business.ops.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.deploy.business.ops.dto.DevpOpsRequirementAddDto;
import net.aicoder.devp.deploy.business.ops.dto.DevpOpsRequirementEditDto;
import net.aicoder.devp.deploy.business.ops.domain.DevpOpsRequirement;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class DevpOpsRequirementValidator implements Validator {

	/**
	 * 判断支持的JavaBean类型
	 * @param aClass
	 * @return
	 */
	@Override
	public boolean supports(Class<?> aClass) {
		if(DevpOpsRequirementAddDto.class.equals(aClass))
			return true;
		if(DevpOpsRequirementEditDto.class.equals(aClass))
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
	    if(obj instanceof DevpOpsRequirementAddDto){
            this.validateDevpOpsRequirementAddDto((DevpOpsRequirementAddDto)obj, errors);
        }
	}

	/**
     * 实现Validator中的validate接口
     * @param devpOpsRequirement 需求定义
     * @param errors
     */
	public void validateDevpOpsRequirementAddDto(DevpOpsRequirementAddDto devpOpsRequirement, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if (null == devpOpsRequirement.getTid() ) {
			errors.rejectValue(DevpOpsRequirement.PROPERTY_TID, "EMPTY_"+DevpOpsRequirement.PROPERTY_TID, "租户编号不能为空");
		}
		if(StringUtils.isEmpty(devpOpsRequirement.getEtype())){
			errors.rejectValue(DevpOpsRequirement.PROPERTY_ETYPE, "EMPTY_"+DevpOpsRequirement.PROPERTY_ETYPE, "元素类型不能为空");
		}
		if(StringUtils.isEmpty(devpOpsRequirement.getNexusType())){
			errors.rejectValue(DevpOpsRequirement.PROPERTY_NEXUS_TYPE, "EMPTY_"+DevpOpsRequirement.PROPERTY_NEXUS_TYPE, "关联记录类型不能为空");
		}
		if (null == devpOpsRequirement.getNexusRid() ) {
			errors.rejectValue(DevpOpsRequirement.PROPERTY_NEXUS_RID, "EMPTY_"+DevpOpsRequirement.PROPERTY_NEXUS_RID, "关联记录编号不能为空");
		}

		//验证长度
		if(StringUtils.length(devpOpsRequirement.getEtype()) > 255){
			errors.rejectValue(DevpOpsRequirement.PROPERTY_ETYPE,null,"元素类型最长255个字符");
		}
		if(StringUtils.length(devpOpsRequirement.getCode()) > 255){
			errors.rejectValue(DevpOpsRequirement.PROPERTY_CODE,null,"需求代码最长255个字符");
		}
		if(StringUtils.length(devpOpsRequirement.getName()) > 255){
			errors.rejectValue(DevpOpsRequirement.PROPERTY_NAME,null,"需求名称最长255个字符");
		}
		if(StringUtils.length(devpOpsRequirement.getAlias()) > 255){
			errors.rejectValue(DevpOpsRequirement.PROPERTY_ALIAS,null,"需求别名最长255个字符");
		}
		if(StringUtils.length(devpOpsRequirement.getDescription()) > 255){
			errors.rejectValue(DevpOpsRequirement.PROPERTY_DESCRIPTION,null,"需求描述最长255个字符");
		}
		if(StringUtils.length(devpOpsRequirement.getNexusType()) > 255){
			errors.rejectValue(DevpOpsRequirement.PROPERTY_NEXUS_TYPE,null,"关联记录类型最长255个字符");
		}
		if(StringUtils.length(devpOpsRequirement.getTypeCode()) > 255){
			errors.rejectValue(DevpOpsRequirement.PROPERTY_TYPE_CODE,null,"类型代码最长255个字符");
		}
		if(StringUtils.length(devpOpsRequirement.getTypeName()) > 255){
			errors.rejectValue(DevpOpsRequirement.PROPERTY_TYPE_NAME,null,"类型名称最长255个字符");
		}
		if(StringUtils.length(devpOpsRequirement.getContent()) > 255){
			errors.rejectValue(DevpOpsRequirement.PROPERTY_CONTENT,null,"内容最长255个字符");
		}
		if(StringUtils.length(devpOpsRequirement.getStereotype()) > 255){
			errors.rejectValue(DevpOpsRequirement.PROPERTY_STEREOTYPE,null,"构造型最长255个字符");
		}
		if(StringUtils.length(devpOpsRequirement.getScope()) > 255){
			errors.rejectValue(DevpOpsRequirement.PROPERTY_SCOPE,null,"访问控制范围最长255个字符");
		}
		if(StringUtils.length(devpOpsRequirement.getVersion()) > 255){
			errors.rejectValue(DevpOpsRequirement.PROPERTY_VERSION,null,"版本最长255个字符");
		}
		if(StringUtils.length(devpOpsRequirement.getPhase()) > 255){
			errors.rejectValue(DevpOpsRequirement.PROPERTY_PHASE,null,"阶段最长255个字符");
		}
		if(StringUtils.length(devpOpsRequirement.getStatus()) > 255){
			errors.rejectValue(DevpOpsRequirement.PROPERTY_STATUS,null,"状态最长255个字符");
		}
	}
}