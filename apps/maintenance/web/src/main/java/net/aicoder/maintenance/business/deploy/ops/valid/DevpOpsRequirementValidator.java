package net.aicoder.maintenance.business.deploy.ops.valid;


import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.business.ops.dto.DevpOpsRequirementAddDto;
import net.aicoder.devp.business.ops.dto.DevpOpsRequirementEditDto;
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
            this.validateAddDto((DevpOpsRequirementAddDto)obj, errors);
        }
	}

	/**
     * 验证新增信息
     * @param devpOpsRequirement 需求定义
     * @param errors
     */
	public void validateAddDto(DevpOpsRequirementAddDto devpOpsRequirement, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if (null == devpOpsRequirement.getTid() ) {
			errors.rejectValue("tid", "EMPTY_TID", "租户编号不能为空");
		}
		if(StringUtils.isEmpty(devpOpsRequirement.getEtype())){
			errors.rejectValue("etype", "EMPTY_ETYPE", "元素类型不能为空");
		}
       
		if(StringUtils.isEmpty(devpOpsRequirement.getNexusType())){
			errors.rejectValue("nexusType", "EMPTY_NEXUS_TYPE", "关联记录类型不能为空");
		}
       
		if (null == devpOpsRequirement.getNexusRid() ) {
			errors.rejectValue("nexusRid", "EMPTY_NEXUS_RID", "关联记录编号不能为空");
		}

		//验证长度
		if(StringUtils.length(devpOpsRequirement.getEtype()) > 255){
			errors.rejectValue("etype", null, "元素类型最长255个字符");
		}
		if(StringUtils.length(devpOpsRequirement.getCode()) > 255){
			errors.rejectValue("code", null, "需求代码最长255个字符");
		}
		if(StringUtils.length(devpOpsRequirement.getName()) > 255){
			errors.rejectValue("name", null, "需求名称最长255个字符");
		}
		if(StringUtils.length(devpOpsRequirement.getAlias()) > 255){
			errors.rejectValue("alias", null, "需求别名最长255个字符");
		}
		if(StringUtils.length(devpOpsRequirement.getDescription()) > 255){
			errors.rejectValue("description", null, "需求描述最长255个字符");
		}
		if(StringUtils.length(devpOpsRequirement.getNexusType()) > 255){
			errors.rejectValue("nexusType", null, "关联记录类型最长255个字符");
		}
		if(StringUtils.length(devpOpsRequirement.getTypeCode()) > 255){
			errors.rejectValue("typeCode", null, "类型代码最长255个字符");
		}
		if(StringUtils.length(devpOpsRequirement.getTypeName()) > 255){
			errors.rejectValue("typeName", null, "类型名称最长255个字符");
		}
		if(StringUtils.length(devpOpsRequirement.getContent()) > 255){
			errors.rejectValue("content", null, "内容最长255个字符");
		}
		if(StringUtils.length(devpOpsRequirement.getStereotype()) > 255){
			errors.rejectValue("stereotype", null, "构造型最长255个字符");
		}
		if(StringUtils.length(devpOpsRequirement.getScope()) > 255){
			errors.rejectValue("scope", null, "访问控制范围最长255个字符");
		}
		if(StringUtils.length(devpOpsRequirement.getVersion()) > 255){
			errors.rejectValue("version", null, "版本最长255个字符");
		}
		if(StringUtils.length(devpOpsRequirement.getPhase()) > 255){
			errors.rejectValue("phase", null, "阶段最长255个字符");
		}
		if(StringUtils.length(devpOpsRequirement.getStatus()) > 255){
			errors.rejectValue("status", null, "状态最长255个字符");
		}
	}
}