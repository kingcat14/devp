package net.aicoder.devp.maintenance.business.deploy.ops.valid;


import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.deploy.business.ops.dto.DevpOpsAttachmentAddDto;
import net.aicoder.devp.deploy.business.ops.dto.DevpOpsAttachmentEditDto;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class DevpOpsAttachmentValidator implements Validator {

	/**
	 * 判断支持的JavaBean类型
	 * @param aClass
	 * @return
	 */
	@Override
	public boolean supports(Class<?> aClass) {
		if(DevpOpsAttachmentAddDto.class.equals(aClass))
			return true;
		if(DevpOpsAttachmentEditDto.class.equals(aClass))
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
	    if(obj instanceof DevpOpsAttachmentAddDto){
            this.validateAddDto((DevpOpsAttachmentAddDto)obj, errors);
        }
	}

	/**
     * 验证新增信息
     * @param devpOpsAttachment 附件定义
     * @param errors
     */
	public void validateAddDto(DevpOpsAttachmentAddDto devpOpsAttachment, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if (null == devpOpsAttachment.getTid() ) {
			errors.rejectValue("tid", "EMPTY_TID", "租户编号不能为空");
		}
		if(StringUtils.isEmpty(devpOpsAttachment.getNexusType())){
			errors.rejectValue("nexusType", "EMPTY_NEXUS_TYPE", "关联记录类型不能为空");
		}
       
		if (null == devpOpsAttachment.getNexusRid() ) {
			errors.rejectValue("nexusRid", "EMPTY_NEXUS_RID", "关联记录编号不能为空");
		}

		//验证长度
		if(StringUtils.length(devpOpsAttachment.getEtype()) > 255){
			errors.rejectValue("etype", null, "元素类型最长255个字符");
		}
		if(StringUtils.length(devpOpsAttachment.getCode()) > 255){
			errors.rejectValue("code", null, "附件代码最长255个字符");
		}
		if(StringUtils.length(devpOpsAttachment.getName()) > 255){
			errors.rejectValue("name", null, "附件名称最长255个字符");
		}
		if(StringUtils.length(devpOpsAttachment.getAlias()) > 255){
			errors.rejectValue("alias", null, "附件别名最长255个字符");
		}
		if(StringUtils.length(devpOpsAttachment.getDescription()) > 255){
			errors.rejectValue("description", null, "附件描述最长255个字符");
		}
		if(StringUtils.length(devpOpsAttachment.getTypeCode()) > 255){
			errors.rejectValue("typeCode", null, "类型代码最长255个字符");
		}
		if(StringUtils.length(devpOpsAttachment.getTypeName()) > 255){
			errors.rejectValue("typeName", null, "类型名称最长255个字符");
		}
		if(StringUtils.length(devpOpsAttachment.getFileType()) > 255){
			errors.rejectValue("fileType", null, "文件类型最长255个字符");
		}
		if(StringUtils.length(devpOpsAttachment.getAccessType()) > 255){
			errors.rejectValue("accessType", null, "访问方式最长255个字符");
		}
		if(StringUtils.length(devpOpsAttachment.getDomain()) > 255){
			errors.rejectValue("domain", null, "访问域最长255个字符");
		}
		if(StringUtils.length(devpOpsAttachment.getAddress()) > 255){
			errors.rejectValue("address", null, "访问地址最长255个字符");
		}
		if(StringUtils.length(devpOpsAttachment.getFileVersion()) > 255){
			errors.rejectValue("fileVersion", null, "附件版本最长255个字符");
		}
		if(StringUtils.length(devpOpsAttachment.getNexusType()) > 255){
			errors.rejectValue("nexusType", null, "关联记录类型最长255个字符");
		}
		if(StringUtils.length(devpOpsAttachment.getCmodifyUcode()) > 255){
			errors.rejectValue("cmodifyUcode", null, "修改用户代码最长255个字符");
		}
	}
}