package net.aicoder.speedcloud.icode.business.project.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.icode.business.project.domain.Component;
import net.aicoder.speedcloud.icode.business.project.dto.ComponentAddDto;
import net.aicoder.speedcloud.icode.business.project.dto.ComponentCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class ComponentValidator implements Validator {

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
	    if(obj instanceof ComponentAddDto){
            this.validateComponentAddDto((ComponentAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj, errors);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<ComponentCondition> search, Errors errors) {
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new ComponentCondition());
        }
        if(search.getSearchCondition().getTid() == null){
        	errors.rejectValue("NOT_TENANT_ID", "未正确设置租户ID");
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param component 系统组件
     * @param errors
     */
	public void validateComponentAddDto(ComponentAddDto component, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(component.getTid()) > 255){
			errors.rejectValue(Component.PROPERTY_TID,null,"租户id最长255个字符");
		}
		if(StringUtils.length(component.getName()) > 255){
			errors.rejectValue(Component.PROPERTY_NAME,null,"组件名称最长255个字符");
		}
		if(StringUtils.length(component.getCode()) > 255){
			errors.rejectValue(Component.PROPERTY_CODE,null,"组件代码最长255个字符");
		}
		if(StringUtils.length(component.getBasePackage()) > 255){
			errors.rejectValue(Component.PROPERTY_BASE_PACKAGE,null,"基础包最长255个字符");
		}
		if(StringUtils.length(component.getDescription()) > 255){
			errors.rejectValue(Component.PROPERTY_DESCRIPTION,null,"描述最长255个字符");
		}
		if(StringUtils.length(component.getTplSet()) > 255){
			errors.rejectValue(Component.PROPERTY_TPL_SET,null,"代码模板最长255个字符");
		}
		if(StringUtils.length(component.getGroupCode()) > 255){
			errors.rejectValue(Component.PROPERTY_GROUP_CODE,null,"分组代码最长255个字符");
		}
		if(StringUtils.length(component.getProduct()) > 255){
			errors.rejectValue(Component.PROPERTY_PRODUCT,null,"所属产品最长255个字符");
		}
	}
}