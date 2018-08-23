package net.aicoder.devp.business.publish.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskPublishAddDto;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskPublishEditDto;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskPublishCondition;
import net.aicoder.devp.business.publish.domain.DevpSysOpsTaskPublish;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class DevpSysOpsTaskPublishValidator implements Validator {

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
	    if(obj instanceof DevpSysOpsTaskPublishAddDto){
            this.validateDevpSysOpsTaskPublishAddDto((DevpSysOpsTaskPublishAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<DevpSysOpsTaskPublishCondition> search){
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new DevpSysOpsTaskPublishCondition());
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param devpSysOpsTaskPublish 发布设置
     * @param errors
     */
	public void validateDevpSysOpsTaskPublishAddDto(DevpSysOpsTaskPublishAddDto devpSysOpsTaskPublish, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(devpSysOpsTaskPublish.getEtype()) > 255){
			errors.rejectValue(DevpSysOpsTaskPublish.PROPERTY_ETYPE,null,"元素类型最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskPublish.getName()) > 255){
			errors.rejectValue(DevpSysOpsTaskPublish.PROPERTY_NAME,null,"系统元素名称最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskPublish.getCode()) > 255){
			errors.rejectValue(DevpSysOpsTaskPublish.PROPERTY_CODE,null,"系统元素代码最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskPublish.getAlias()) > 255){
			errors.rejectValue(DevpSysOpsTaskPublish.PROPERTY_ALIAS,null,"系统元素别名最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskPublish.getDescription()) > 255){
			errors.rejectValue(DevpSysOpsTaskPublish.PROPERTY_DESCRIPTION,null,"系统元素描述最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskPublish.getType()) > 255){
			errors.rejectValue(DevpSysOpsTaskPublish.PROPERTY_TYPE,null,"类型最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskPublish.getSubType()) > 255){
			errors.rejectValue(DevpSysOpsTaskPublish.PROPERTY_SUB_TYPE,null,"子类型最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskPublish.getTemplate()) > 255){
			errors.rejectValue(DevpSysOpsTaskPublish.PROPERTY_TEMPLATE,null,"参考模板最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskPublish.getGroup()) > 255){
			errors.rejectValue(DevpSysOpsTaskPublish.PROPERTY_GROUP,null,"发布物分组最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskPublish.getArtifact()) > 255){
			errors.rejectValue(DevpSysOpsTaskPublish.PROPERTY_ARTIFACT,null,"发布物最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskPublish.getVersion()) > 255){
			errors.rejectValue(DevpSysOpsTaskPublish.PROPERTY_VERSION,null,"版本标识最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskPublish.getPublishFile()) > 255){
			errors.rejectValue(DevpSysOpsTaskPublish.PROPERTY_PUBLISH_FILE,null,"发布文件名最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskPublish.getStatus()) > 255){
			errors.rejectValue(DevpSysOpsTaskPublish.PROPERTY_STATUS,null,"状态最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskPublish.getNotes()) > 255){
			errors.rejectValue(DevpSysOpsTaskPublish.PROPERTY_NOTES,null,"备注最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskPublish.getCreateUcode()) > 255){
			errors.rejectValue(DevpSysOpsTaskPublish.PROPERTY_CREATE_UCODE,null,"创建用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskPublish.getCreateUname()) > 255){
			errors.rejectValue(DevpSysOpsTaskPublish.PROPERTY_CREATE_UNAME,null,"创建用户姓名最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskPublish.getModifyUcode()) > 255){
			errors.rejectValue(DevpSysOpsTaskPublish.PROPERTY_MODIFY_UCODE,null,"修改用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskPublish.getModifyUname()) > 255){
			errors.rejectValue(DevpSysOpsTaskPublish.PROPERTY_MODIFY_UNAME,null,"修改用户姓名最长255个字符");
		}
	}
}