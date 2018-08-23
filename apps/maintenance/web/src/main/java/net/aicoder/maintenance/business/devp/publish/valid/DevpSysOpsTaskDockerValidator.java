package net.aicoder.maintenance.business.devp.publish.valid;


import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskDockerAddDto;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskDockerEditDto;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class DevpSysOpsTaskDockerValidator implements Validator {

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
	    if(obj instanceof DevpSysOpsTaskDockerAddDto){
            this.validateAddDto((DevpSysOpsTaskDockerAddDto)obj, errors);
        }
	}

	/**
     * 验证新增信息
     * @param devpSysOpsTaskDocker 部署容器
     * @param errors
     */
	public void validateAddDto(DevpSysOpsTaskDockerAddDto devpSysOpsTaskDocker, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if (null == devpSysOpsTaskDocker.getTid() ) {
			errors.rejectValue("tid", "EMPTY_TID", "租户编号不能为空");
		}
		if(StringUtils.isEmpty(devpSysOpsTaskDocker.getEtype())){
			errors.rejectValue("etype", "EMPTY_ETYPE", "元素类型不能为空");
		}
       
		if(StringUtils.isEmpty(devpSysOpsTaskDocker.getName())){
			errors.rejectValue("name", "EMPTY_NAME", "系统元素名称不能为空");
		}
       
		if(StringUtils.isEmpty(devpSysOpsTaskDocker.getGroup())){
			errors.rejectValue("group", "EMPTY_GROUP", "所在集群不能为空");
		}
       
		if(StringUtils.isEmpty(devpSysOpsTaskDocker.getNamespace())){
			errors.rejectValue("namespace", "EMPTY_NAMESPACE", "命名空间不能为空");
		}
       
		if(StringUtils.isEmpty(devpSysOpsTaskDocker.getPublishArtifact())){
			errors.rejectValue("publishArtifact", "EMPTY_PUBLISH_ARTIFACT", "发布物不能为空");
		}
       
		if (null == devpSysOpsTaskDocker.getPrdRid() ) {
			errors.rejectValue("prdRid", "EMPTY_PRD_RID", "产品编号不能为空");
		}
		if (null == devpSysOpsTaskDocker.getSchemeRid() ) {
			errors.rejectValue("schemeRid", "EMPTY_SCHEME_RID", "部署方案编号不能为空");
		}
		if (null == devpSysOpsTaskDocker.getCmpRid() ) {
			errors.rejectValue("cmpRid", "EMPTY_CMP_RID", "组件编号不能为空");
		}
		if (null == devpSysOpsTaskDocker.getTaskRid() ) {
			errors.rejectValue("taskRid", "EMPTY_TASK_RID", "任务编号不能为空");
		}

		//验证长度
		if(StringUtils.length(devpSysOpsTaskDocker.getEtype()) > 255){
			errors.rejectValue("etype", null, "元素类型最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskDocker.getName()) > 255){
			errors.rejectValue("name", null, "系统元素名称最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskDocker.getCode()) > 255){
			errors.rejectValue("code", null, "系统元素代码最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskDocker.getAlias()) > 255){
			errors.rejectValue("alias", null, "系统元素别名最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskDocker.getDescription()) > 255){
			errors.rejectValue("description", null, "系统元素描述最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskDocker.getType()) > 255){
			errors.rejectValue("type", null, "类型最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskDocker.getSubType()) > 255){
			errors.rejectValue("subType", null, "子类型最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskDocker.getGroup()) > 255){
			errors.rejectValue("group", null, "所在集群最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskDocker.getNamespace()) > 255){
			errors.rejectValue("namespace", null, "命名空间最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskDocker.getPublishGroup()) > 255){
			errors.rejectValue("publishGroup", null, "发布物分组最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskDocker.getPublishArtifact()) > 255){
			errors.rejectValue("publishArtifact", null, "发布物最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskDocker.getPublishVersion()) > 255){
			errors.rejectValue("publishVersion", null, "版本标识最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskDocker.getPublishFile()) > 255){
			errors.rejectValue("publishFile", null, "发布文件名最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskDocker.getLbStrategy()) > 255){
			errors.rejectValue("lbStrategy", null, "负载策略最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskDocker.getAccessType()) > 255){
			errors.rejectValue("accessType", null, "访问类型最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskDocker.getStatus()) > 255){
			errors.rejectValue("status", null, "状态最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskDocker.getNotes()) > 255){
			errors.rejectValue("notes", null, "备注最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskDocker.getCreateUcode()) > 255){
			errors.rejectValue("createUcode", null, "创建用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskDocker.getCreateUname()) > 255){
			errors.rejectValue("createUname", null, "创建用户姓名最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskDocker.getModifyUcode()) > 255){
			errors.rejectValue("modifyUcode", null, "修改用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskDocker.getModifyUname()) > 255){
			errors.rejectValue("modifyUname", null, "修改用户姓名最长255个字符");
		}
	}
}