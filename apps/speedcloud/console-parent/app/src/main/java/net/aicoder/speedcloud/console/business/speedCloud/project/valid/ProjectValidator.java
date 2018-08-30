package net.aicoder.speedcloud.console.business.speedCloud.project.valid;


import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.project.dto.ProjectAddDto;
import net.aicoder.speedcloud.business.project.dto.ProjectEditDto;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class ProjectValidator implements Validator {

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
	    if(obj instanceof ProjectAddDto){
            this.validateAddDto((ProjectAddDto)obj, errors);
        }
	}

	/**
     * 验证新增信息
     * @param project 项目
     * @param errors
     */
	public void validateAddDto(ProjectAddDto project, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if (null == project.getTid() ) {
			errors.rejectValue("tid", "EMPTY_TID", "租户id不能为空");
		}

		//验证长度
		if(StringUtils.length(project.getName()) > 255){
			errors.rejectValue("name", null, "名称最长255个字符");
		}
		if(StringUtils.length(project.getType()) > 255){
			errors.rejectValue("type", null, "类型最长255个字符");
		}
		if(StringUtils.length(project.getScope()) > 255){
			errors.rejectValue("scope", null, "公开性最长255个字符");
		}
		if(StringUtils.length(project.getParent()) > 255){
			errors.rejectValue("parent", null, "上级项目最长255个字符");
		}
	}
}