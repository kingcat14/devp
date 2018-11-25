package net.aicoder.speedcloud.icode.business.project.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.icode.business.project.domain.ComponentDomainRelation;
import net.aicoder.speedcloud.icode.business.project.dto.ComponentDomainRelationAddDto;
import net.aicoder.speedcloud.icode.business.project.dto.ComponentDomainRelationCondition;
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
            this.validateComponentDomainRelationAddDto((ComponentDomainRelationAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj, errors);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<ComponentDomainRelationCondition> search, Errors errors) {
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new ComponentDomainRelationCondition());
        }
        if(search.getSearchCondition().getTid() == null){
        	errors.rejectValue("NOT_TENANT_ID", "未正确设置租户ID");
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param componentDomainRelation 组件-领域-关系
     * @param errors
     */
	public void validateComponentDomainRelationAddDto(ComponentDomainRelationAddDto componentDomainRelation, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(componentDomainRelation.getComponent()) > 255){
			errors.rejectValue(ComponentDomainRelation.PROPERTY_COMPONENT,null,"系统组件最长255个字符");
		}
		if(StringUtils.length(componentDomainRelation.getDomain()) > 255){
			errors.rejectValue(ComponentDomainRelation.PROPERTY_DOMAIN,null,"领域最长255个字符");
		}
		if(StringUtils.length(componentDomainRelation.getRelationType()) > 255){
			errors.rejectValue(ComponentDomainRelation.PROPERTY_RELATION_TYPE,null,"关系类型最长255个字符");
		}
	}
}