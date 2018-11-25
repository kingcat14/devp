package net.aicoder.speedcloud.console.business.icode.project.valid;


import net.aicoder.speedcloud.icode.business.project.dto.ComponentDomainRelationAddDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class ComponentDomainRelationValidator implements Validator {

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
	    if(obj instanceof ComponentDomainRelationAddDto){
            this.validateAddDto((ComponentDomainRelationAddDto)obj, errors);
        }
	}

	/**
     * 验证新增信息
     * @param componentDomainRelation 组件-领域-关系
     * @param errors
     */
	public void validateAddDto(ComponentDomainRelationAddDto componentDomainRelation, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(componentDomainRelation.getComponent()) > 255){
			errors.rejectValue("component", null, "系统组件最长255个字符");
		}
		if(StringUtils.length(componentDomainRelation.getDomain()) > 255){
			errors.rejectValue("domain", null, "领域最长255个字符");
		}
		if(StringUtils.length(componentDomainRelation.getRelationType()) > 255){
			errors.rejectValue("relationType", null, "关系类型最长255个字符");
		}
	}
}