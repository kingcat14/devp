package net.aicoder.devp.business.sys.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.business.sys.dto.DevpSysAttachmentAddDto;
import net.aicoder.devp.business.sys.dto.DevpSysAttachmentEditDto;
import net.aicoder.devp.business.sys.dto.DevpSysAttachmentCondition;
import net.aicoder.devp.business.sys.domain.DevpSysAttachment;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class DevpSysAttachmentValidator implements Validator {

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
	    if(obj instanceof DevpSysAttachmentAddDto){
            this.validateDevpSysAttachmentAddDto((DevpSysAttachmentAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<DevpSysAttachmentCondition> search){
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new DevpSysAttachmentCondition());
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param devpSysAttachment 附件
     * @param errors
     */
	public void validateDevpSysAttachmentAddDto(DevpSysAttachmentAddDto devpSysAttachment, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(devpSysAttachment.getEtype()) > 255){
			errors.rejectValue(DevpSysAttachment.PROPERTY_ETYPE,null,"元素类型最长255个字符");
		}
		if(StringUtils.length(devpSysAttachment.getName()) > 255){
			errors.rejectValue(DevpSysAttachment.PROPERTY_NAME,null,"附件名称最长255个字符");
		}
		if(StringUtils.length(devpSysAttachment.getCode()) > 255){
			errors.rejectValue(DevpSysAttachment.PROPERTY_CODE,null,"附件代码最长255个字符");
		}
		if(StringUtils.length(devpSysAttachment.getAlias()) > 255){
			errors.rejectValue(DevpSysAttachment.PROPERTY_ALIAS,null,"附件别名最长255个字符");
		}
		if(StringUtils.length(devpSysAttachment.getDescription()) > 255){
			errors.rejectValue(DevpSysAttachment.PROPERTY_DESCRIPTION,null,"附件描述最长255个字符");
		}
		if(StringUtils.length(devpSysAttachment.getType()) > 255){
			errors.rejectValue(DevpSysAttachment.PROPERTY_TYPE,null,"类型最长255个字符");
		}
		if(StringUtils.length(devpSysAttachment.getSubType()) > 255){
			errors.rejectValue(DevpSysAttachment.PROPERTY_SUB_TYPE,null,"子类型最长255个字符");
		}
		if(StringUtils.length(devpSysAttachment.getFileType()) > 255){
			errors.rejectValue(DevpSysAttachment.PROPERTY_FILE_TYPE,null,"文件类型最长255个字符");
		}
		if(StringUtils.length(devpSysAttachment.getAccessType()) > 255){
			errors.rejectValue(DevpSysAttachment.PROPERTY_ACCESS_TYPE,null,"访问方式最长255个字符");
		}
		if(StringUtils.length(devpSysAttachment.getWorkspace()) > 255){
			errors.rejectValue(DevpSysAttachment.PROPERTY_WORKSPACE,null,"工作空间最长255个字符");
		}
		if(StringUtils.length(devpSysAttachment.getPath()) > 255){
			errors.rejectValue(DevpSysAttachment.PROPERTY_PATH,null,"访问路径最长255个字符");
		}
		if(StringUtils.length(devpSysAttachment.getFileName()) > 255){
			errors.rejectValue(DevpSysAttachment.PROPERTY_FILE_NAME,null,"访问地址最长255个字符");
		}
		if(StringUtils.length(devpSysAttachment.getFileVersion()) > 255){
			errors.rejectValue(DevpSysAttachment.PROPERTY_FILE_VERSION,null,"附件版本最长255个字符");
		}
		if(StringUtils.length(devpSysAttachment.getFileCreater()) > 255){
			errors.rejectValue(DevpSysAttachment.PROPERTY_FILE_CREATER,null,"文件创建器最长255个字符");
		}
		if(StringUtils.length(devpSysAttachment.getFileEditor()) > 255){
			errors.rejectValue(DevpSysAttachment.PROPERTY_FILE_EDITOR,null,"文件编辑器最长255个字符");
		}
		if(StringUtils.length(devpSysAttachment.getCreateUcode()) > 255){
			errors.rejectValue(DevpSysAttachment.PROPERTY_CREATE_UCODE,null,"创建用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysAttachment.getCreateUname()) > 255){
			errors.rejectValue(DevpSysAttachment.PROPERTY_CREATE_UNAME,null,"创建用户姓名最长255个字符");
		}
		if(StringUtils.length(devpSysAttachment.getCmodifyUcode()) > 255){
			errors.rejectValue(DevpSysAttachment.PROPERTY_CMODIFY_UCODE,null,"修改用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysAttachment.getModifyUname()) > 255){
			errors.rejectValue(DevpSysAttachment.PROPERTY_MODIFY_UNAME,null,"修改用户姓名最长255个字符");
		}
	}
}