package net.aicoder.maintenance.business.deploy.ops.valid;


import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.business.ops.dto.DevpOpsIssueAddDto;
import net.aicoder.devp.business.ops.dto.DevpOpsIssueEditDto;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class DevpOpsIssueValidator implements Validator {

	/**
	 * 判断支持的JavaBean类型
	 * @param aClass
	 * @return
	 */
	@Override
	public boolean supports(Class<?> aClass) {
		if(DevpOpsIssueAddDto.class.equals(aClass))
			return true;
		if(DevpOpsIssueEditDto.class.equals(aClass))
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
	    if(obj instanceof DevpOpsIssueAddDto){
            this.validateAddDto((DevpOpsIssueAddDto)obj, errors);
        }
	}

	/**
     * 验证新增信息
     * @param devpOpsIssue 问题记录
     * @param errors
     */
	public void validateAddDto(DevpOpsIssueAddDto devpOpsIssue, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if (null == devpOpsIssue.getTid() ) {
			errors.rejectValue("tid", "EMPTY_TID", "租户编号不能为空");
		}
		if(StringUtils.isEmpty(devpOpsIssue.getEtype())){
			errors.rejectValue("etype", "EMPTY_ETYPE", "元素类型不能为空");
		}
       
		if(StringUtils.isEmpty(devpOpsIssue.getNexusType())){
			errors.rejectValue("nexusType", "EMPTY_NEXUS_TYPE", "关联记录类型不能为空");
		}
       
		if (null == devpOpsIssue.getNexusRid() ) {
			errors.rejectValue("nexusRid", "EMPTY_NEXUS_RID", "关联记录编号不能为空");
		}

		//验证长度
		if(StringUtils.length(devpOpsIssue.getEtype()) > 255){
			errors.rejectValue("etype", null, "元素类型最长255个字符");
		}
		if(StringUtils.length(devpOpsIssue.getCode()) > 255){
			errors.rejectValue("code", null, "问题代码最长255个字符");
		}
		if(StringUtils.length(devpOpsIssue.getName()) > 255){
			errors.rejectValue("name", null, "问题名称最长255个字符");
		}
		if(StringUtils.length(devpOpsIssue.getAlias()) > 255){
			errors.rejectValue("alias", null, "问题别名最长255个字符");
		}
		if(StringUtils.length(devpOpsIssue.getDescription()) > 255){
			errors.rejectValue("description", null, "问题描述最长255个字符");
		}
		if(StringUtils.length(devpOpsIssue.getType()) > 255){
			errors.rejectValue("type", null, "问题类型最长255个字符");
		}
		if(StringUtils.length(devpOpsIssue.getIssue()) > 255){
			errors.rejectValue("issue", null, "问题说明最长255个字符");
		}
		if(StringUtils.length(devpOpsIssue.getReply()) > 255){
			errors.rejectValue("reply", null, "问题回复最长255个字符");
		}
		if(StringUtils.length(devpOpsIssue.getStatus()) > 255){
			errors.rejectValue("status", null, "处理状态最长255个字符");
		}
		if(StringUtils.length(devpOpsIssue.getNexusType()) > 255){
			errors.rejectValue("nexusType", null, "关联记录类型最长255个字符");
		}
		if(StringUtils.length(devpOpsIssue.getNexusVersion()) > 255){
			errors.rejectValue("nexusVersion", null, "关联记录版本最长255个字符");
		}
		if(StringUtils.length(devpOpsIssue.getNexusPhase()) > 255){
			errors.rejectValue("nexusPhase", null, "关联记录阶段最长255个字符");
		}
		if(StringUtils.length(devpOpsIssue.getParasCode()) > 255){
			errors.rejectValue("parasCode", null, "参数定义标识最长255个字符");
		}
		if(StringUtils.length(devpOpsIssue.getCmodifyUcode()) > 255){
			errors.rejectValue("cmodifyUcode", null, "修改用户代码最长255个字符");
		}
	}
}