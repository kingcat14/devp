package net.aicoder.speedcloud.icode.business.project.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.icode.business.project.domain.ComponentType;
import net.aicoder.speedcloud.icode.business.project.dto.ComponentTypeAddDto;
import net.aicoder.speedcloud.icode.business.project.dto.ComponentTypeCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class ComponentTypeValidator implements Validator {

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
	    if(obj instanceof ComponentTypeAddDto){
            this.validateComponentTypeAddDto((ComponentTypeAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj, errors);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<ComponentTypeCondition> search, Errors errors) {
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new ComponentTypeCondition());
        }
        if(search.getSearchCondition().getTid() == null){
        	errors.rejectValue("NOT_TENANT_ID", "未正确设置租户ID");
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param componentType 组件类型
     * @param errors
     */
	public void validateComponentTypeAddDto(ComponentTypeAddDto componentType, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(componentType.getCode()) > 255){
			errors.rejectValue(ComponentType.PROPERTY_CODE,null,"代码最长255个字符");
		}
		if(StringUtils.length(componentType.getName()) > 255){
			errors.rejectValue(ComponentType.PROPERTY_NAME,null,"名称最长255个字符");
		}
		if(StringUtils.length(componentType.getCategory()) > 255){
			errors.rejectValue(ComponentType.PROPERTY_CATEGORY,null,"种类最长255个字符");
		}
		if(StringUtils.length(componentType.getIcon()) > 255){
			errors.rejectValue(ComponentType.PROPERTY_ICON,null,"默认图标最长255个字符");
		}
	}
}