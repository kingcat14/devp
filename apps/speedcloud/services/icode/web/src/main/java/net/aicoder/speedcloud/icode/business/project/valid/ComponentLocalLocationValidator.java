package net.aicoder.speedcloud.icode.business.project.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.icode.business.project.domain.ComponentLocalLocation;
import net.aicoder.speedcloud.icode.business.project.dto.ComponentLocalLocationAddDto;
import net.aicoder.speedcloud.icode.business.project.dto.ComponentLocalLocationCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class ComponentLocalLocationValidator implements Validator {

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
	    if(obj instanceof ComponentLocalLocationAddDto){
            this.validateComponentLocalLocationAddDto((ComponentLocalLocationAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj, errors);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<ComponentLocalLocationCondition> search, Errors errors) {
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new ComponentLocalLocationCondition());
        }
        if(search.getSearchCondition().getTid() == null){
        	errors.rejectValue("NOT_TENANT_ID", "未正确设置租户ID");
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param componentLocalLocation 组件本地路径
     * @param errors
     */
	public void validateComponentLocalLocationAddDto(ComponentLocalLocationAddDto componentLocalLocation, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(componentLocalLocation.getComponent()) > 255){
			errors.rejectValue(ComponentLocalLocation.PROPERTY_COMPONENT,null,"组件最长255个字符");
		}
		if(StringUtils.length(componentLocalLocation.getLocation()) > 255){
			errors.rejectValue(ComponentLocalLocation.PROPERTY_LOCATION,null,"本地路径最长255个字符");
		}
	}
}