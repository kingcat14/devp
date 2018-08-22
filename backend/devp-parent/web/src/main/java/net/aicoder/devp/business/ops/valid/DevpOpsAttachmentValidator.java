package net.aicoder.devp.business.ops.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.business.ops.dto.DevpOpsAttachmentAddDto;
import net.aicoder.devp.business.ops.dto.DevpOpsAttachmentEditDto;
import net.aicoder.devp.business.ops.domain.DevpOpsAttachment;
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
		return true;
	}

	/**
	 * 实现Validator中的validate接口
	 * @param obj
	 * @param errors
	 */
	@Override
	public void validate(Object obj, Errors errors) {
	    if(obj instanceof DevpOpsAttachmentAddDto){
            this.validateDevpOpsAttachmentAddDto((DevpOpsAttachmentAddDto)obj, errors);
        }
	}

	/**
     * 实现Validator中的validate接口
     * @param devpOpsAttachment 附件
     * @param errors
     */
	public void validateDevpOpsAttachmentAddDto(DevpOpsAttachmentAddDto devpOpsAttachment, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(devpOpsAttachment.getEtype()) > 255){
			errors.rejectValue(DevpOpsAttachment.PROPERTY_ETYPE,null,"元素类型最长255个字符");
		}
		if(StringUtils.length(devpOpsAttachment.getCode()) > 255){
			errors.rejectValue(DevpOpsAttachment.PROPERTY_CODE,null,"附件代码最长255个字符");
		}
		if(StringUtils.length(devpOpsAttachment.getName()) > 255){
			errors.rejectValue(DevpOpsAttachment.PROPERTY_NAME,null,"附件名称最长255个字符");
		}
		if(StringUtils.length(devpOpsAttachment.getAlias()) > 255){
			errors.rejectValue(DevpOpsAttachment.PROPERTY_ALIAS,null,"附件别名最长255个字符");
		}
		if(StringUtils.length(devpOpsAttachment.getDescription()) > 255){
			errors.rejectValue(DevpOpsAttachment.PROPERTY_DESCRIPTION,null,"附件描述最长255个字符");
		}
		if(StringUtils.length(devpOpsAttachment.getTypeCode()) > 255){
			errors.rejectValue(DevpOpsAttachment.PROPERTY_TYPE_CODE,null,"类型代码最长255个字符");
		}
		if(StringUtils.length(devpOpsAttachment.getTypeName()) > 255){
			errors.rejectValue(DevpOpsAttachment.PROPERTY_TYPE_NAME,null,"类型名称最长255个字符");
		}
		if(StringUtils.length(devpOpsAttachment.getFileType()) > 255){
			errors.rejectValue(DevpOpsAttachment.PROPERTY_FILE_TYPE,null,"文件类型最长255个字符");
		}
		if(StringUtils.length(devpOpsAttachment.getAccessType()) > 255){
			errors.rejectValue(DevpOpsAttachment.PROPERTY_ACCESS_TYPE,null,"访问方式最长255个字符");
		}
		if(StringUtils.length(devpOpsAttachment.getDomain()) > 255){
			errors.rejectValue(DevpOpsAttachment.PROPERTY_DOMAIN,null,"访问域最长255个字符");
		}
		if(StringUtils.length(devpOpsAttachment.getAddress()) > 255){
			errors.rejectValue(DevpOpsAttachment.PROPERTY_ADDRESS,null,"访问地址最长255个字符");
		}
		if(StringUtils.length(devpOpsAttachment.getFileVersion()) > 255){
			errors.rejectValue(DevpOpsAttachment.PROPERTY_FILE_VERSION,null,"附件版本最长255个字符");
		}
		if(StringUtils.length(devpOpsAttachment.getNexusType()) > 255){
			errors.rejectValue(DevpOpsAttachment.PROPERTY_NEXUS_TYPE,null,"关联记录类型最长255个字符");
		}
		if(StringUtils.length(devpOpsAttachment.getCreateUcode()) > 255){
			errors.rejectValue(DevpOpsAttachment.PROPERTY_CREATE_UCODE,null,"创建用户代码最长255个字符");
		}
		if(StringUtils.length(devpOpsAttachment.getCreateUname()) > 255){
			errors.rejectValue(DevpOpsAttachment.PROPERTY_CREATE_UNAME,null,"创建用户姓名最长255个字符");
		}
		if(StringUtils.length(devpOpsAttachment.getModifyUcode()) > 255){
			errors.rejectValue(DevpOpsAttachment.PROPERTY_MODIFY_UCODE,null,"修改用户代码最长255个字符");
		}
		if(StringUtils.length(devpOpsAttachment.getModifyUname()) > 255){
			errors.rejectValue(DevpOpsAttachment.PROPERTY_MODIFY_UNAME,null,"修改用户姓名最长255个字符");
		}
		if(StringUtils.length(devpOpsAttachment.getCmodifyUcode()) > 255){
			errors.rejectValue(DevpOpsAttachment.PROPERTY_CMODIFY_UCODE,null,"cmodify_ucode最长255个字符");
		}
	}
}