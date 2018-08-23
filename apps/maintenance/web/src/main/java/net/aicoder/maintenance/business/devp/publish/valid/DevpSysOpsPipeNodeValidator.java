package net.aicoder.maintenance.business.devp.publish.valid;


import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.business.publish.dto.DevpSysOpsPipeNodeAddDto;
import net.aicoder.devp.business.publish.dto.DevpSysOpsPipeNodeEditDto;
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
            this.validateAddDto((DevpSysOpsPipeNodeAddDto)obj, errors);
        }
	}

	/**
     * 验证新增信息
     * @param devpSysOpsPipeNode 流水线执行节点
     * @param errors
     */
	public void validateAddDto(DevpSysOpsPipeNodeAddDto devpSysOpsPipeNode, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if (null == devpSysOpsPipeNode.getTid() ) {
			errors.rejectValue("tid", "EMPTY_TID", "租户编号不能为空");
		}
		if(StringUtils.isEmpty(devpSysOpsPipeNode.getEtype())){
			errors.rejectValue("etype", "EMPTY_ETYPE", "元素类型不能为空");
		}
       
		if(StringUtils.isEmpty(devpSysOpsPipeNode.getName())){
			errors.rejectValue("name", "EMPTY_NAME", "系统元素名称不能为空");
		}
       
		if (null == devpSysOpsPipeNode.getPrdRid() ) {
			errors.rejectValue("prdRid", "EMPTY_PRD_RID", "产品编号不能为空");
		}
		if (null == devpSysOpsPipeNode.getSchemeRid() ) {
			errors.rejectValue("schemeRid", "EMPTY_SCHEME_RID", "部署方案编号不能为空");
		}
		if (null == devpSysOpsPipeNode.getPipelineRid() ) {
			errors.rejectValue("pipelineRid", "EMPTY_PIPELINE_RID", "流水线编号不能为空");
		}

		//验证长度
		if(StringUtils.length(devpSysOpsPipeNode.getEtype()) > 255){
			errors.rejectValue("etype", null, "元素类型最长255个字符");
		}
		if(StringUtils.length(devpSysOpsPipeNode.getName()) > 255){
			errors.rejectValue("name", null, "系统元素名称最长255个字符");
		}
		if(StringUtils.length(devpSysOpsPipeNode.getCode()) > 255){
			errors.rejectValue("code", null, "系统元素代码最长255个字符");
		}
		if(StringUtils.length(devpSysOpsPipeNode.getAlias()) > 255){
			errors.rejectValue("alias", null, "系统元素别名最长255个字符");
		}
		if(StringUtils.length(devpSysOpsPipeNode.getDescription()) > 255){
			errors.rejectValue("description", null, "系统元素描述最长255个字符");
		}
		if(StringUtils.length(devpSysOpsPipeNode.getType()) > 255){
			errors.rejectValue("type", null, "类型最长255个字符");
		}
		if(StringUtils.length(devpSysOpsPipeNode.getSubType()) > 255){
			errors.rejectValue("subType", null, "子类型最长255个字符");
		}
		if(StringUtils.length(devpSysOpsPipeNode.getExecModel()) > 255){
			errors.rejectValue("execModel", null, "执行方式最长255个字符");
		}
		if(StringUtils.length(devpSysOpsPipeNode.getExecSeq()) > 255){
			errors.rejectValue("execSeq", null, "执行顺序最长255个字符");
		}
		if(StringUtils.length(devpSysOpsPipeNode.getReturnCode()) > 255){
			errors.rejectValue("returnCode", null, "返回码最长255个字符");
		}
		if(StringUtils.length(devpSysOpsPipeNode.getExecStatus()) > 255){
			errors.rejectValue("execStatus", null, "执行状态最长255个字符");
		}
		if(StringUtils.length(devpSysOpsPipeNode.getNotes()) > 255){
			errors.rejectValue("notes", null, "备注最长255个字符");
		}
		if(StringUtils.length(devpSysOpsPipeNode.getCreateUcode()) > 255){
			errors.rejectValue("createUcode", null, "创建用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysOpsPipeNode.getCreateUname()) > 255){
			errors.rejectValue("createUname", null, "创建用户姓名最长255个字符");
		}
		if(StringUtils.length(devpSysOpsPipeNode.getModifyUcode()) > 255){
			errors.rejectValue("modifyUcode", null, "修改用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysOpsPipeNode.getModifyUname()) > 255){
			errors.rejectValue("modifyUname", null, "修改用户姓名最长255个字符");
		}
	}
}