package net.aicoder.speedcloud.business.deployscheme.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.deployscheme.domain.ResourceType;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceTypeAddDto;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceTypeCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class ResourceTypeValidator implements Validator {

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
	    if(obj instanceof ResourceTypeAddDto){
            this.validateResourceTypeAddDto((ResourceTypeAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj, errors);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<ResourceTypeCondition> search, Errors errors) {
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new ResourceTypeCondition());
        }
        if(search.getSearchCondition().getTid() == null){
        	errors.rejectValue("NOT_TENANT_ID", "未正确设置租户ID");
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param resourceType 部署资源类型
     * @param errors
     */
	public void validateResourceTypeAddDto(ResourceTypeAddDto resourceType, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(resourceType.getCategory()) > 255){
			errors.rejectValue(ResourceType.PROPERTY_CATEGORY,null,"资源类别最长255个字符");
		}
		if(StringUtils.length(resourceType.getName()) > 255){
			errors.rejectValue(ResourceType.PROPERTY_NAME,null,"名称最长255个字符");
		}
		if(StringUtils.length(resourceType.getCode()) > 255){
			errors.rejectValue(ResourceType.PROPERTY_CODE,null,"代码最长255个字符");
		}
		if(StringUtils.length(resourceType.getIcon()) > 255){
			errors.rejectValue(ResourceType.PROPERTY_ICON,null,"图标最长255个字符");
		}
	}
}