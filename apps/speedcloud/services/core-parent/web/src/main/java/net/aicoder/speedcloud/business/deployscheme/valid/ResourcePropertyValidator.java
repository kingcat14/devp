package net.aicoder.speedcloud.business.deployscheme.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.deployscheme.domain.ResourceProperty;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourcePropertyAddDto;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourcePropertyCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class ResourcePropertyValidator implements Validator {

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
	    if(obj instanceof ResourcePropertyAddDto){
            this.validateResourcePropertyAddDto((ResourcePropertyAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<ResourcePropertyCondition> search){
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new ResourcePropertyCondition());
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param resourceProperty 资源属性
     * @param errors
     */
	public void validateResourcePropertyAddDto(ResourcePropertyAddDto resourceProperty, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(resourceProperty.getName()) > 255){
			errors.rejectValue(ResourceProperty.PROPERTY_NAME,null,"属性名称最长255个字符");
		}
		if(StringUtils.length(resourceProperty.getCode()) > 255){
			errors.rejectValue(ResourceProperty.PROPERTY_CODE,null,"属性代码最长255个字符");
		}
		if(StringUtils.length(resourceProperty.getValue()) > 255){
			errors.rejectValue(ResourceProperty.PROPERTY_VALUE,null,"属性值最长255个字符");
		}
	}
}