package net.aicoder.speedcloud.business.project.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.project.dto.ProjectAddDto;
import net.aicoder.speedcloud.business.project.dto.ProjectEditDto;
import net.aicoder.speedcloud.business.project.dto.ProjectCondition;
import net.aicoder.speedcloud.business.project.domain.Project;
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
            this.validateProjectAddDto((ProjectAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<ProjectCondition> search){
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new ProjectCondition());
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param project 项目
     * @param errors
     */
	public void validateProjectAddDto(ProjectAddDto project, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(project.getName()) > 255){
			errors.rejectValue(Project.PROPERTY_NAME,null,"名称最长255个字符");
		}
		if(StringUtils.length(project.getType()) > 255){
			errors.rejectValue(Project.PROPERTY_TYPE,null,"类型最长255个字符");
		}
		if(StringUtils.length(project.getScope()) > 255){
			errors.rejectValue(Project.PROPERTY_SCOPE,null,"公开性最长255个字符");
		}
		if(StringUtils.length(project.getParent()) > 255){
			errors.rejectValue(Project.PROPERTY_PARENT,null,"上级项目最长255个字符");
		}
	}
}