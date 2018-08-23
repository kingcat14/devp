package net.aicoder.maintenance.business.devp.publish.valid;


import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskPublishAddDto;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskPublishEditDto;
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
            this.validateAddDto((DevpSysOpsTaskPublishAddDto)obj, errors);
        }
	}

	/**
     * 验证新增信息
     * @param devpSysOpsTaskPublish 发布设置
     * @param errors
     */
	public void validateAddDto(DevpSysOpsTaskPublishAddDto devpSysOpsTaskPublish, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if (null == devpSysOpsTaskPublish.getTid() ) {
			errors.rejectValue("tid", "EMPTY_TID", "租户编号不能为空");
		}
		if(StringUtils.isEmpty(devpSysOpsTaskPublish.getEtype())){
			errors.rejectValue("etype", "EMPTY_ETYPE", "元素类型不能为空");
		}
       
		if(StringUtils.isEmpty(devpSysOpsTaskPublish.getName())){
			errors.rejectValue("name", "EMPTY_NAME", "系统元素名称不能为空");
		}
       
		if (null == devpSysOpsTaskPublish.getPrdRid() ) {
			errors.rejectValue("prdRid", "EMPTY_PRD_RID", "产品编号不能为空");
		}
		if (null == devpSysOpsTaskPublish.getSchemeRid() ) {
			errors.rejectValue("schemeRid", "EMPTY_SCHEME_RID", "部署方案编号不能为空");
		}
		if (null == devpSysOpsTaskPublish.getCmpRid() ) {
			errors.rejectValue("cmpRid", "EMPTY_CMP_RID", "组件编号不能为空");
		}
		if (null == devpSysOpsTaskPublish.getTaskRid() ) {
			errors.rejectValue("taskRid", "EMPTY_TASK_RID", "任务编号不能为空");
		}

		//验证长度
		if(StringUtils.length(devpSysOpsTaskPublish.getEtype()) > 255){
			errors.rejectValue("etype", null, "元素类型最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskPublish.getName()) > 255){
			errors.rejectValue("name", null, "系统元素名称最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskPublish.getCode()) > 255){
			errors.rejectValue("code", null, "系统元素代码最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskPublish.getAlias()) > 255){
			errors.rejectValue("alias", null, "系统元素别名最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskPublish.getDescription()) > 255){
			errors.rejectValue("description", null, "系统元素描述最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskPublish.getType()) > 255){
			errors.rejectValue("type", null, "类型最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskPublish.getSubType()) > 255){
			errors.rejectValue("subType", null, "子类型最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskPublish.getTemplate()) > 255){
			errors.rejectValue("template", null, "参考模板最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskPublish.getGroup()) > 255){
			errors.rejectValue("group", null, "发布物分组最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskPublish.getArtifact()) > 255){
			errors.rejectValue("artifact", null, "发布物最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskPublish.getVersion()) > 255){
			errors.rejectValue("version", null, "版本标识最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskPublish.getPublishFile()) > 255){
			errors.rejectValue("publishFile", null, "发布文件名最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskPublish.getStatus()) > 255){
			errors.rejectValue("status", null, "状态最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskPublish.getNotes()) > 255){
			errors.rejectValue("notes", null, "备注最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskPublish.getCreateUcode()) > 255){
			errors.rejectValue("createUcode", null, "创建用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskPublish.getCreateUname()) > 255){
			errors.rejectValue("createUname", null, "创建用户姓名最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskPublish.getModifyUcode()) > 255){
			errors.rejectValue("modifyUcode", null, "修改用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskPublish.getModifyUname()) > 255){
			errors.rejectValue("modifyUname", null, "修改用户姓名最长255个字符");
		}
	}
}