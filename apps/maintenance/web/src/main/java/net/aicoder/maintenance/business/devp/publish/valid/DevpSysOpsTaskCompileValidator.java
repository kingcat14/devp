package net.aicoder.maintenance.business.devp.publish.valid;


import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskCompileAddDto;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskCompileEditDto;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class DevpSysOpsTaskCompileValidator implements Validator {

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
	    if(obj instanceof DevpSysOpsTaskCompileAddDto){
            this.validateAddDto((DevpSysOpsTaskCompileAddDto)obj, errors);
        }
	}

	/**
     * 验证新增信息
     * @param devpSysOpsTaskCompile 编译设置
     * @param errors
     */
	public void validateAddDto(DevpSysOpsTaskCompileAddDto devpSysOpsTaskCompile, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if (null == devpSysOpsTaskCompile.getTid() ) {
			errors.rejectValue("tid", "EMPTY_TID", "租户编号不能为空");
		}
		if(StringUtils.isEmpty(devpSysOpsTaskCompile.getEtype())){
			errors.rejectValue("etype", "EMPTY_ETYPE", "元素类型不能为空");
		}
       
		if(StringUtils.isEmpty(devpSysOpsTaskCompile.getName())){
			errors.rejectValue("name", "EMPTY_NAME", "系统元素名称不能为空");
		}
       
		if (null == devpSysOpsTaskCompile.getPrdRid() ) {
			errors.rejectValue("prdRid", "EMPTY_PRD_RID", "产品编号不能为空");
		}
		if (null == devpSysOpsTaskCompile.getSchemeRid() ) {
			errors.rejectValue("schemeRid", "EMPTY_SCHEME_RID", "部署方案编号不能为空");
		}
		if (null == devpSysOpsTaskCompile.getCmpRid() ) {
			errors.rejectValue("cmpRid", "EMPTY_CMP_RID", "组件编号不能为空");
		}
		if (null == devpSysOpsTaskCompile.getTaskRid() ) {
			errors.rejectValue("taskRid", "EMPTY_TASK_RID", "任务编号不能为空");
		}

		//验证长度
		if(StringUtils.length(devpSysOpsTaskCompile.getEtype()) > 255){
			errors.rejectValue("etype", null, "元素类型最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskCompile.getName()) > 255){
			errors.rejectValue("name", null, "系统元素名称最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskCompile.getCode()) > 255){
			errors.rejectValue("code", null, "系统元素代码最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskCompile.getAlias()) > 255){
			errors.rejectValue("alias", null, "系统元素别名最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskCompile.getDescription()) > 255){
			errors.rejectValue("description", null, "系统元素描述最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskCompile.getType()) > 255){
			errors.rejectValue("type", null, "类型最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskCompile.getSubType()) > 255){
			errors.rejectValue("subType", null, "子类型最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskCompile.getCmType()) > 255){
			errors.rejectValue("cmType", null, "代码仓库类型最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskCompile.getCmRepository()) > 255){
			errors.rejectValue("cmRepository", null, "仓库地址最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskCompile.getCmTag()) > 255){
			errors.rejectValue("cmTag", null, "分支标识最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskCompile.getCmUser()) > 255){
			errors.rejectValue("cmUser", null, "用户名最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskCompile.getCmPassword()) > 255){
			errors.rejectValue("cmPassword", null, "密码最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskCompile.getSubDir()) > 255){
			errors.rejectValue("subDir", null, "子目录最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskCompile.getBaseline()) > 255){
			errors.rejectValue("baseline", null, "基线标识最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskCompile.getSvnCodeUrl()) > 255){
			errors.rejectValue("svnCodeUrl", null, "代码路径最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskCompile.getBuildTools()) > 255){
			errors.rejectValue("buildTools", null, "构建工具最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskCompile.getEnvVersion()) > 255){
			errors.rejectValue("envVersion", null, "执行环境版本最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskCompile.getToolVersion()) > 255){
			errors.rejectValue("toolVersion", null, "工具版本最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskCompile.getPreAction()) > 255){
			errors.rejectValue("preAction", null, "构建前操作最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskCompile.getBuildAction()) > 255){
			errors.rejectValue("buildAction", null, "构建操作最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskCompile.getPostAction()) > 255){
			errors.rejectValue("postAction", null, "构建后操作最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskCompile.getStatus()) > 255){
			errors.rejectValue("status", null, "状态最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskCompile.getNotes()) > 255){
			errors.rejectValue("notes", null, "备注最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskCompile.getCreateUcode()) > 255){
			errors.rejectValue("createUcode", null, "创建用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskCompile.getCreateUname()) > 255){
			errors.rejectValue("createUname", null, "创建用户姓名最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskCompile.getModifyUcode()) > 255){
			errors.rejectValue("modifyUcode", null, "修改用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskCompile.getModifyUname()) > 255){
			errors.rejectValue("modifyUname", null, "修改用户姓名最长255个字符");
		}
	}
}