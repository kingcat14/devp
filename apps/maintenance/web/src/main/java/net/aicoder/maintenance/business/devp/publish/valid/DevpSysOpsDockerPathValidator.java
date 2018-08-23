package net.aicoder.maintenance.business.devp.publish.valid;


import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.business.publish.dto.DevpSysOpsDockerPathAddDto;
import net.aicoder.devp.business.publish.dto.DevpSysOpsDockerPathEditDto;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class DevpSysOpsDockerPathValidator implements Validator {

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
	    if(obj instanceof DevpSysOpsDockerPathAddDto){
            this.validateAddDto((DevpSysOpsDockerPathAddDto)obj, errors);
        }
	}

	/**
     * 验证新增信息
     * @param devpSysOpsDockerPath 存储路径定义
     * @param errors
     */
	public void validateAddDto(DevpSysOpsDockerPathAddDto devpSysOpsDockerPath, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if (null == devpSysOpsDockerPath.getTid() ) {
			errors.rejectValue("tid", "EMPTY_TID", "租户编号不能为空");
		}
		if(StringUtils.isEmpty(devpSysOpsDockerPath.getEtype())){
			errors.rejectValue("etype", "EMPTY_ETYPE", "元素类型不能为空");
		}
       
		if(StringUtils.isEmpty(devpSysOpsDockerPath.getDockerPath())){
			errors.rejectValue("dockerPath", "EMPTY_DOCKER_PATH", "容器挂载路径不能为空");
		}
       
		if(StringUtils.isEmpty(devpSysOpsDockerPath.getHostPath())){
			errors.rejectValue("hostPath", "EMPTY_HOST_PATH", "属主机路径不能为空");
		}
       
		if (null == devpSysOpsDockerPath.getPrdRid() ) {
			errors.rejectValue("prdRid", "EMPTY_PRD_RID", "产品编号不能为空");
		}
		if (null == devpSysOpsDockerPath.getSchemeRid() ) {
			errors.rejectValue("schemeRid", "EMPTY_SCHEME_RID", "部署方案编号不能为空");
		}
		if (null == devpSysOpsDockerPath.getCmpRid() ) {
			errors.rejectValue("cmpRid", "EMPTY_CMP_RID", "组件编号不能为空");
		}
		if (null == devpSysOpsDockerPath.getTaskRid() ) {
			errors.rejectValue("taskRid", "EMPTY_TASK_RID", "任务编号不能为空");
		}
		if (null == devpSysOpsDockerPath.getDockerRid() ) {
			errors.rejectValue("dockerRid", "EMPTY_DOCKER_RID", "容器编号不能为空");
		}

		//验证长度
		if(StringUtils.length(devpSysOpsDockerPath.getEtype()) > 255){
			errors.rejectValue("etype", null, "元素类型最长255个字符");
		}
		if(StringUtils.length(devpSysOpsDockerPath.getName()) > 255){
			errors.rejectValue("name", null, "系统元素名称最长255个字符");
		}
		if(StringUtils.length(devpSysOpsDockerPath.getCode()) > 255){
			errors.rejectValue("code", null, "系统元素代码最长255个字符");
		}
		if(StringUtils.length(devpSysOpsDockerPath.getAlias()) > 255){
			errors.rejectValue("alias", null, "系统元素别名最长255个字符");
		}
		if(StringUtils.length(devpSysOpsDockerPath.getDescription()) > 255){
			errors.rejectValue("description", null, "系统元素描述最长255个字符");
		}
		if(StringUtils.length(devpSysOpsDockerPath.getType()) > 255){
			errors.rejectValue("type", null, "类型最长255个字符");
		}
		if(StringUtils.length(devpSysOpsDockerPath.getSubType()) > 255){
			errors.rejectValue("subType", null, "子类型最长255个字符");
		}
		if(StringUtils.length(devpSysOpsDockerPath.getDockerPath()) > 255){
			errors.rejectValue("dockerPath", null, "容器挂载路径最长255个字符");
		}
		if(StringUtils.length(devpSysOpsDockerPath.getHostPath()) > 255){
			errors.rejectValue("hostPath", null, "属主机路径最长255个字符");
		}
		if(StringUtils.length(devpSysOpsDockerPath.getNotes()) > 255){
			errors.rejectValue("notes", null, "备注最长255个字符");
		}
		if(StringUtils.length(devpSysOpsDockerPath.getCreateUcode()) > 255){
			errors.rejectValue("createUcode", null, "创建用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysOpsDockerPath.getCreateUname()) > 255){
			errors.rejectValue("createUname", null, "创建用户姓名最长255个字符");
		}
		if(StringUtils.length(devpSysOpsDockerPath.getModifyUcode()) > 255){
			errors.rejectValue("modifyUcode", null, "修改用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysOpsDockerPath.getModifyUname()) > 255){
			errors.rejectValue("modifyUname", null, "修改用户姓名最长255个字符");
		}
	}
}