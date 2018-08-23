package net.aicoder.maintenance.business.devp.publish.valid;


import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.business.publish.dto.DevpSysOpsPipePlanAddDto;
import net.aicoder.devp.business.publish.dto.DevpSysOpsPipePlanEditDto;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class DevpSysOpsPipePlanValidator implements Validator {

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
	    if(obj instanceof DevpSysOpsPipePlanAddDto){
            this.validateAddDto((DevpSysOpsPipePlanAddDto)obj, errors);
        }
	}

	/**
     * 验证新增信息
     * @param devpSysOpsPipePlan 产品运维流水线执行计划
     * @param errors
     */
	public void validateAddDto(DevpSysOpsPipePlanAddDto devpSysOpsPipePlan, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if (null == devpSysOpsPipePlan.getTid() ) {
			errors.rejectValue("tid", "EMPTY_TID", "租户编号不能为空");
		}
		if(StringUtils.isEmpty(devpSysOpsPipePlan.getEtype())){
			errors.rejectValue("etype", "EMPTY_ETYPE", "元素类型不能为空");
		}
       
		if(StringUtils.isEmpty(devpSysOpsPipePlan.getName())){
			errors.rejectValue("name", "EMPTY_NAME", "系统元素名称不能为空");
		}
       
		if (null == devpSysOpsPipePlan.getPrdRid() ) {
			errors.rejectValue("prdRid", "EMPTY_PRD_RID", "产品编号不能为空");
		}
		if (null == devpSysOpsPipePlan.getSchemeRid() ) {
			errors.rejectValue("schemeRid", "EMPTY_SCHEME_RID", "部署方案编号不能为空");
		}

		//验证长度
		if(StringUtils.length(devpSysOpsPipePlan.getEtype()) > 255){
			errors.rejectValue("etype", null, "元素类型最长255个字符");
		}
		if(StringUtils.length(devpSysOpsPipePlan.getName()) > 255){
			errors.rejectValue("name", null, "系统元素名称最长255个字符");
		}
		if(StringUtils.length(devpSysOpsPipePlan.getCode()) > 255){
			errors.rejectValue("code", null, "系统元素代码最长255个字符");
		}
		if(StringUtils.length(devpSysOpsPipePlan.getAlias()) > 255){
			errors.rejectValue("alias", null, "系统元素别名最长255个字符");
		}
		if(StringUtils.length(devpSysOpsPipePlan.getDescription()) > 255){
			errors.rejectValue("description", null, "系统元素描述最长255个字符");
		}
		if(StringUtils.length(devpSysOpsPipePlan.getType()) > 255){
			errors.rejectValue("type", null, "类型最长255个字符");
		}
		if(StringUtils.length(devpSysOpsPipePlan.getSubType()) > 255){
			errors.rejectValue("subType", null, "子类型最长255个字符");
		}
		if(StringUtils.length(devpSysOpsPipePlan.getSchedule()) > 255){
			errors.rejectValue("schedule", null, "定时执行配置最长255个字符");
		}
		if(StringUtils.length(devpSysOpsPipePlan.getCreateUcode()) > 255){
			errors.rejectValue("createUcode", null, "创建用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysOpsPipePlan.getCreateUname()) > 255){
			errors.rejectValue("createUname", null, "创建用户姓名最长255个字符");
		}
		if(StringUtils.length(devpSysOpsPipePlan.getModifyUcode()) > 255){
			errors.rejectValue("modifyUcode", null, "修改用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysOpsPipePlan.getModifyUname()) > 255){
			errors.rejectValue("modifyUname", null, "修改用户姓名最长255个字符");
		}
	}
}