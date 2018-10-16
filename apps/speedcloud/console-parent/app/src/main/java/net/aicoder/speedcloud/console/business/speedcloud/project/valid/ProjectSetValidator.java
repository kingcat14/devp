package net.aicoder.speedcloud.console.business.speedcloud.project.valid;


import net.aicoder.speedcloud.business.project.dto.ProjectSetAddDto;
import org.springframework.validation.Errors;
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
            this.validateAddDto((ProjectSetAddDto)obj, errors);
        }
	}

	/**
     * 验证新增信息
     * @param projectSet 项目集
     * @param errors
     */
	public void validateAddDto(ProjectSetAddDto projectSet, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if (null == projectSet.getTid() ) {
			errors.rejectValue("tid", "EMPTY_TID", "租户id不能为空");
		}

		//验证长度
		if(StringUtils.length(projectSet.getName()) > 255){
			errors.rejectValue("name", null, "名称最长255个字符");
		}
	}
}