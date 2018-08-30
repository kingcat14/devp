package net.aicoder.speedcloud.business.project.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.project.dto.ProjectSetAddDto;
import net.aicoder.speedcloud.business.project.dto.ProjectSetEditDto;
import net.aicoder.speedcloud.business.project.dto.ProjectSetCondition;
import net.aicoder.speedcloud.business.project.domain.ProjectSet;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class ProjectSetValidator implements Validator {

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
	    if(obj instanceof ProjectSetAddDto){
            this.validateProjectSetAddDto((ProjectSetAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<ProjectSetCondition> search){
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new ProjectSetCondition());
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param projectSet 项目集
     * @param errors
     */
	public void validateProjectSetAddDto(ProjectSetAddDto projectSet, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(projectSet.getName()) > 255){
			errors.rejectValue(ProjectSet.PROPERTY_NAME,null,"名称最长255个字符");
		}
	}
}