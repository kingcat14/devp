package net.aicoder.maintenance.business.devp.publish.valid;


import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.business.publish.dto.DevpSysOpsDockerAccessAddDto;
import net.aicoder.devp.business.publish.dto.DevpSysOpsDockerAccessEditDto;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class DevpSysOpsDockerAccessValidator implements Validator {

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
	    if(obj instanceof DevpSysOpsDockerAccessAddDto){
            this.validateAddDto((DevpSysOpsDockerAccessAddDto)obj, errors);
        }
	}

	/**
     * 验证新增信息
     * @param devpSysOpsDockerAccess 部署容器访问参数
     * @param errors
     */
	public void validateAddDto(DevpSysOpsDockerAccessAddDto devpSysOpsDockerAccess, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if (null == devpSysOpsDockerAccess.getTid() ) {
			errors.rejectValue("tid", "EMPTY_TID", "租户编号不能为空");
		}
		if(StringUtils.isEmpty(devpSysOpsDockerAccess.getEtype())){
			errors.rejectValue("etype", "EMPTY_ETYPE", "元素类型不能为空");
		}
       
		if (null == devpSysOpsDockerAccess.getServicesPort() ) {
			errors.rejectValue("servicesPort", "EMPTY_SERVICES_PORT", "服务端口不能为空");
		}
		if (null == devpSysOpsDockerAccess.getDockerPort() ) {
			errors.rejectValue("dockerPort", "EMPTY_DOCKER_PORT", "服务端口不能为空");
		}
		if (null == devpSysOpsDockerAccess.getPrdRid() ) {
			errors.rejectValue("prdRid", "EMPTY_PRD_RID", "产品编号不能为空");
		}
		if (null == devpSysOpsDockerAccess.getSchemeRid() ) {
			errors.rejectValue("schemeRid", "EMPTY_SCHEME_RID", "部署方案编号不能为空");
		}
		if (null == devpSysOpsDockerAccess.getCmpRid() ) {
			errors.rejectValue("cmpRid", "EMPTY_CMP_RID", "组件编号不能为空");
		}
		if (null == devpSysOpsDockerAccess.getTaskRid() ) {
			errors.rejectValue("taskRid", "EMPTY_TASK_RID", "任务编号不能为空");
		}
		if (null == devpSysOpsDockerAccess.getDockerRid() ) {
			errors.rejectValue("dockerRid", "EMPTY_DOCKER_RID", "容器编号不能为空");
		}

		//验证长度
		if(StringUtils.length(devpSysOpsDockerAccess.getEtype()) > 255){
			errors.rejectValue("etype", null, "元素类型最长255个字符");
		}
		if(StringUtils.length(devpSysOpsDockerAccess.getName()) > 255){
			errors.rejectValue("name", null, "系统元素名称最长255个字符");
		}
		if(StringUtils.length(devpSysOpsDockerAccess.getCode()) > 255){
			errors.rejectValue("code", null, "系统元素代码最长255个字符");
		}
		if(StringUtils.length(devpSysOpsDockerAccess.getAlias()) > 255){
			errors.rejectValue("alias", null, "系统元素别名最长255个字符");
		}
		if(StringUtils.length(devpSysOpsDockerAccess.getDescription()) > 255){
			errors.rejectValue("description", null, "系统元素描述最长255个字符");
		}
		if(StringUtils.length(devpSysOpsDockerAccess.getType()) > 255){
			errors.rejectValue("type", null, "类型最长255个字符");
		}
		if(StringUtils.length(devpSysOpsDockerAccess.getNotes()) > 255){
			errors.rejectValue("notes", null, "备注最长255个字符");
		}
		if(StringUtils.length(devpSysOpsDockerAccess.getProtocol()) > 255){
			errors.rejectValue("protocol", null, "协议最长255个字符");
		}
		if(StringUtils.length(devpSysOpsDockerAccess.getCreateUcode()) > 255){
			errors.rejectValue("createUcode", null, "创建用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysOpsDockerAccess.getCreateUname()) > 255){
			errors.rejectValue("createUname", null, "创建用户姓名最长255个字符");
		}
		if(StringUtils.length(devpSysOpsDockerAccess.getModifyUcode()) > 255){
			errors.rejectValue("modifyUcode", null, "修改用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysOpsDockerAccess.getModifyUname()) > 255){
			errors.rejectValue("modifyUname", null, "修改用户姓名最长255个字符");
		}
	}
}