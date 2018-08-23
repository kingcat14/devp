package net.aicoder.maintenance.business.devp.publish.valid;


import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskDeployAddDto;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskDeployEditDto;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class DevpSysOpsTaskDeployValidator implements Validator {

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
	    if(obj instanceof DevpSysOpsTaskDeployAddDto){
            this.validateAddDto((DevpSysOpsTaskDeployAddDto)obj, errors);
        }
	}

	/**
     * 验证新增信息
     * @param devpSysOpsTaskDeploy 组件部署设置
     * @param errors
     */
	public void validateAddDto(DevpSysOpsTaskDeployAddDto devpSysOpsTaskDeploy, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if (null == devpSysOpsTaskDeploy.getTid() ) {
			errors.rejectValue("tid", "EMPTY_TID", "租户编号不能为空");
		}
		if(StringUtils.isEmpty(devpSysOpsTaskDeploy.getEtype())){
			errors.rejectValue("etype", "EMPTY_ETYPE", "元素类型不能为空");
		}
       
		if(StringUtils.isEmpty(devpSysOpsTaskDeploy.getName())){
			errors.rejectValue("name", "EMPTY_NAME", "系统元素名称不能为空");
		}
       
		if (null == devpSysOpsTaskDeploy.getPrdRid() ) {
			errors.rejectValue("prdRid", "EMPTY_PRD_RID", "产品编号不能为空");
		}
		if (null == devpSysOpsTaskDeploy.getSchemeRid() ) {
			errors.rejectValue("schemeRid", "EMPTY_SCHEME_RID", "部署方案编号不能为空");
		}
		if (null == devpSysOpsTaskDeploy.getCmpRid() ) {
			errors.rejectValue("cmpRid", "EMPTY_CMP_RID", "组件编号不能为空");
		}
		if (null == devpSysOpsTaskDeploy.getTaskRid() ) {
			errors.rejectValue("taskRid", "EMPTY_TASK_RID", "任务编号不能为空");
		}

		//验证长度
		if(StringUtils.length(devpSysOpsTaskDeploy.getEtype()) > 255){
			errors.rejectValue("etype", null, "元素类型最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskDeploy.getName()) > 255){
			errors.rejectValue("name", null, "系统元素名称最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskDeploy.getCode()) > 255){
			errors.rejectValue("code", null, "系统元素代码最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskDeploy.getAlias()) > 255){
			errors.rejectValue("alias", null, "系统元素别名最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskDeploy.getDescription()) > 255){
			errors.rejectValue("description", null, "系统元素描述最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskDeploy.getType()) > 255){
			errors.rejectValue("type", null, "类型最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskDeploy.getSubType()) > 255){
			errors.rejectValue("subType", null, "子类型最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskDeploy.getDeployPath()) > 255){
			errors.rejectValue("deployPath", null, "部署路径最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskDeploy.getBackupPath()) > 255){
			errors.rejectValue("backupPath", null, "备份路径最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskDeploy.getPreAction()) > 255){
			errors.rejectValue("preAction", null, "部署前操作最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskDeploy.getDeployAction()) > 255){
			errors.rejectValue("deployAction", null, "部署操作最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskDeploy.getPostAction()) > 255){
			errors.rejectValue("postAction", null, "部署后操作最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskDeploy.getStatus()) > 255){
			errors.rejectValue("status", null, "状态最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskDeploy.getNotes()) > 255){
			errors.rejectValue("notes", null, "备注最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskDeploy.getCreateUcode()) > 255){
			errors.rejectValue("createUcode", null, "创建用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskDeploy.getCreateUname()) > 255){
			errors.rejectValue("createUname", null, "创建用户姓名最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskDeploy.getModifyUcode()) > 255){
			errors.rejectValue("modifyUcode", null, "修改用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskDeploy.getModifyUname()) > 255){
			errors.rejectValue("modifyUname", null, "修改用户姓名最长255个字符");
		}
	}
}