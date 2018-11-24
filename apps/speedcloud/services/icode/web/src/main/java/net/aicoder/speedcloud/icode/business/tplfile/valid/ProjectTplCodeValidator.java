package net.aicoder.speedcloud.icode.business.tplfile.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.icode.business.tplfile.domain.ProjectTplCode;
import net.aicoder.speedcloud.icode.business.tplfile.dto.ProjectTplCodeAddDto;
import net.aicoder.speedcloud.icode.business.tplfile.dto.ProjectTplCodeCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class ProjectTplCodeValidator implements Validator {

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
	    if(obj instanceof ProjectTplCodeAddDto){
            this.validateProjectTplCodeAddDto((ProjectTplCodeAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj, errors);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<ProjectTplCodeCondition> search, Errors errors) {
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new ProjectTplCodeCondition());
        }
        if(search.getSearchCondition().getTid() == null){
        	errors.rejectValue("NOT_TENANT_ID", "未正确设置租户ID");
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param projectTplCode 组件代码模板
     * @param errors
     */
	public void validateProjectTplCodeAddDto(ProjectTplCodeAddDto projectTplCode, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(projectTplCode.getCode()) > 255){
			errors.rejectValue(ProjectTplCode.PROPERTY_CODE,null,"模板代码最长255个字符");
		}
		if(StringUtils.length(projectTplCode.getName()) > 255){
			errors.rejectValue(ProjectTplCode.PROPERTY_NAME,null,"模板名称最长255个字符");
		}
		if(StringUtils.length(projectTplCode.getFileName()) > 255){
			errors.rejectValue(ProjectTplCode.PROPERTY_FILE_NAME,null,"生成文件名最长255个字符");
		}
		if(StringUtils.length(projectTplCode.getFilePath()) > 255){
			errors.rejectValue(ProjectTplCode.PROPERTY_FILE_PATH,null,"生成文件路径最长255个字符");
		}
		if(StringUtils.length(projectTplCode.getContent()) > 255){
			errors.rejectValue(ProjectTplCode.PROPERTY_CONTENT,null,"模板内容最长255个字符");
		}
		if(StringUtils.length(projectTplCode.getType()) > 255){
			errors.rejectValue(ProjectTplCode.PROPERTY_TYPE,null,"模板类型最长255个字符");
		}
		if(StringUtils.length(projectTplCode.getAcceptModelType()) > 255){
			errors.rejectValue(ProjectTplCode.PROPERTY_ACCEPT_MODEL_TYPE,null,"接受的模型类型最长255个字符");
		}
		if(StringUtils.length(projectTplCode.getComponent()) > 255){
			errors.rejectValue(ProjectTplCode.PROPERTY_COMPONENT,null,"所属系统组件最长255个字符");
		}
		if(StringUtils.length(projectTplCode.getTplCode()) > 255){
			errors.rejectValue(ProjectTplCode.PROPERTY_TPL_CODE,null,"关联的代码模板最长255个字符");
		}
	}
}