package net.aicoder.devp.business.publish.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.business.publish.dto.DevpSysOpsPipeNodeAddDto;
import net.aicoder.devp.business.publish.dto.DevpSysOpsPipeNodeEditDto;
import net.aicoder.devp.business.publish.domain.DevpSysOpsPipeNode;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class DevpSysOpsPipeNodeValidator implements Validator {

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
	    if(obj instanceof DevpSysOpsPipeNodeAddDto){
            this.validateDevpSysOpsPipeNodeAddDto((DevpSysOpsPipeNodeAddDto)obj, errors);
        }
	}

	/**
     * 实现Validator中的validate接口
     * @param devpSysOpsPipeNode 流水线执行节点
     * @param errors
     */
	public void validateDevpSysOpsPipeNodeAddDto(DevpSysOpsPipeNodeAddDto devpSysOpsPipeNode, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(devpSysOpsPipeNode.getEtype()) > 255){
			errors.rejectValue(DevpSysOpsPipeNode.PROPERTY_ETYPE,null,"元素类型最长255个字符");
		}
		if(StringUtils.length(devpSysOpsPipeNode.getName()) > 255){
			errors.rejectValue(DevpSysOpsPipeNode.PROPERTY_NAME,null,"系统元素名称最长255个字符");
		}
		if(StringUtils.length(devpSysOpsPipeNode.getCode()) > 255){
			errors.rejectValue(DevpSysOpsPipeNode.PROPERTY_CODE,null,"系统元素代码最长255个字符");
		}
		if(StringUtils.length(devpSysOpsPipeNode.getAlias()) > 255){
			errors.rejectValue(DevpSysOpsPipeNode.PROPERTY_ALIAS,null,"系统元素别名最长255个字符");
		}
		if(StringUtils.length(devpSysOpsPipeNode.getDescription()) > 255){
			errors.rejectValue(DevpSysOpsPipeNode.PROPERTY_DESCRIPTION,null,"系统元素描述最长255个字符");
		}
		if(StringUtils.length(devpSysOpsPipeNode.getType()) > 255){
			errors.rejectValue(DevpSysOpsPipeNode.PROPERTY_TYPE,null,"类型最长255个字符");
		}
		if(StringUtils.length(devpSysOpsPipeNode.getSubType()) > 255){
			errors.rejectValue(DevpSysOpsPipeNode.PROPERTY_SUB_TYPE,null,"子类型最长255个字符");
		}
		if(StringUtils.length(devpSysOpsPipeNode.getExecModel()) > 255){
			errors.rejectValue(DevpSysOpsPipeNode.PROPERTY_EXEC_MODEL,null,"执行方式最长255个字符");
		}
		if(StringUtils.length(devpSysOpsPipeNode.getExecSeq()) > 255){
			errors.rejectValue(DevpSysOpsPipeNode.PROPERTY_EXEC_SEQ,null,"执行顺序最长255个字符");
		}
		if(StringUtils.length(devpSysOpsPipeNode.getReturnCode()) > 255){
			errors.rejectValue(DevpSysOpsPipeNode.PROPERTY_RETURN_CODE,null,"返回码最长255个字符");
		}
		if(StringUtils.length(devpSysOpsPipeNode.getExecStatus()) > 255){
			errors.rejectValue(DevpSysOpsPipeNode.PROPERTY_EXEC_STATUS,null,"执行状态最长255个字符");
		}
		if(StringUtils.length(devpSysOpsPipeNode.getNotes()) > 255){
			errors.rejectValue(DevpSysOpsPipeNode.PROPERTY_NOTES,null,"备注最长255个字符");
		}
		if(StringUtils.length(devpSysOpsPipeNode.getCreateUcode()) > 255){
			errors.rejectValue(DevpSysOpsPipeNode.PROPERTY_CREATE_UCODE,null,"创建用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysOpsPipeNode.getCreateUname()) > 255){
			errors.rejectValue(DevpSysOpsPipeNode.PROPERTY_CREATE_UNAME,null,"创建用户姓名最长255个字符");
		}
		if(StringUtils.length(devpSysOpsPipeNode.getModifyUcode()) > 255){
			errors.rejectValue(DevpSysOpsPipeNode.PROPERTY_MODIFY_UCODE,null,"修改用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysOpsPipeNode.getModifyUname()) > 255){
			errors.rejectValue(DevpSysOpsPipeNode.PROPERTY_MODIFY_UNAME,null,"修改用户姓名最长255个字符");
		}
	}
}