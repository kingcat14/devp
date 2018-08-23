package net.aicoder.maintenance.business.devp.publish.valid;


import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.business.publish.dto.DevpSysOpsDockerParamAddDto;
import net.aicoder.devp.business.publish.dto.DevpSysOpsDockerParamEditDto;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class DevpSysOpsDockerParamValidator implements Validator {

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
	    if(obj instanceof DevpSysOpsDockerParamAddDto){
            this.validateAddDto((DevpSysOpsDockerParamAddDto)obj, errors);
        }
	}

	/**
     * 验证新增信息
     * @param devpSysOpsDockerParam 部署容器参数定义
     * @param errors
     */
	public void validateAddDto(DevpSysOpsDockerParamAddDto devpSysOpsDockerParam, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if (null == devpSysOpsDockerParam.getTid() ) {
			errors.rejectValue("tid", "EMPTY_TID", "租户编号不能为空");
		}
		if(StringUtils.isEmpty(devpSysOpsDockerParam.getEtype())){
			errors.rejectValue("etype", "EMPTY_ETYPE", "元素类型不能为空");
		}
       
		if(StringUtils.isEmpty(devpSysOpsDockerParam.getCode())){
			errors.rejectValue("code", "EMPTY_CODE", "参数代码不能为空");
		}
       
		if (null == devpSysOpsDockerParam.getPrdRid() ) {
			errors.rejectValue("prdRid", "EMPTY_PRD_RID", "产品编号不能为空");
		}
		if (null == devpSysOpsDockerParam.getSchemeRid() ) {
			errors.rejectValue("schemeRid", "EMPTY_SCHEME_RID", "部署方案编号不能为空");
		}
		if (null == devpSysOpsDockerParam.getCmpRid() ) {
			errors.rejectValue("cmpRid", "EMPTY_CMP_RID", "组件编号不能为空");
		}
		if (null == devpSysOpsDockerParam.getTaskRid() ) {
			errors.rejectValue("taskRid", "EMPTY_TASK_RID", "任务编号不能为空");
		}
		if (null == devpSysOpsDockerParam.getDockerRid() ) {
			errors.rejectValue("dockerRid", "EMPTY_DOCKER_RID", "容器编号不能为空");
		}

		//验证长度
		if(StringUtils.length(devpSysOpsDockerParam.getEtype()) > 255){
			errors.rejectValue("etype", null, "元素类型最长255个字符");
		}
		if(StringUtils.length(devpSysOpsDockerParam.getName()) > 255){
			errors.rejectValue("name", null, "系统元素名称最长255个字符");
		}
		if(StringUtils.length(devpSysOpsDockerParam.getCode()) > 255){
			errors.rejectValue("code", null, "参数代码最长255个字符");
		}
		if(StringUtils.length(devpSysOpsDockerParam.getAlias()) > 255){
			errors.rejectValue("alias", null, "系统元素别名最长255个字符");
		}
		if(StringUtils.length(devpSysOpsDockerParam.getDescription()) > 255){
			errors.rejectValue("description", null, "系统元素描述最长255个字符");
		}
		if(StringUtils.length(devpSysOpsDockerParam.getType()) > 255){
			errors.rejectValue("type", null, "类型最长255个字符");
		}
		if(StringUtils.length(devpSysOpsDockerParam.getSubType()) > 255){
			errors.rejectValue("subType", null, "子类型最长255个字符");
		}
		if(StringUtils.length(devpSysOpsDockerParam.getValue()) > 255){
			errors.rejectValue("value", null, "参数值最长255个字符");
		}
		if(StringUtils.length(devpSysOpsDockerParam.getNotes()) > 255){
			errors.rejectValue("notes", null, "备注最长255个字符");
		}
		if(StringUtils.length(devpSysOpsDockerParam.getCreateUcode()) > 255){
			errors.rejectValue("createUcode", null, "创建用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysOpsDockerParam.getCreateUname()) > 255){
			errors.rejectValue("createUname", null, "创建用户姓名最长255个字符");
		}
		if(StringUtils.length(devpSysOpsDockerParam.getModifyUcode()) > 255){
			errors.rejectValue("modifyUcode", null, "修改用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysOpsDockerParam.getModifyUname()) > 255){
			errors.rejectValue("modifyUname", null, "修改用户姓名最长255个字符");
		}
	}
}