package net.aicoder.speedcloud.business.deployscheme.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.deployscheme.domain.ResourceCategory;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceCategoryAddDto;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceCategoryCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class ResourceCategoryValidator implements Validator {

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
	    if(obj instanceof ResourceCategoryAddDto){
            this.validateResourceCategoryAddDto((ResourceCategoryAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj, errors);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<ResourceCategoryCondition> search, Errors errors) {
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new ResourceCategoryCondition());
        }
        if(search.getSearchCondition().getTid() == null){
        	errors.rejectValue("NOT_TENANT_ID", "未正确设置租户ID");
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param resourceCategory 部署资源类别
     * @param errors
     */
	public void validateResourceCategoryAddDto(ResourceCategoryAddDto resourceCategory, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(resourceCategory.getName()) > 255){
			errors.rejectValue(ResourceCategory.PROPERTY_NAME,null,"名称最长255个字符");
		}
		if(StringUtils.length(resourceCategory.getCode()) > 255){
			errors.rejectValue(ResourceCategory.PROPERTY_CODE,null,"代码最长255个字符");
		}
		if(StringUtils.length(resourceCategory.getIcon()) > 255){
			errors.rejectValue(ResourceCategory.PROPERTY_ICON,null,"图标最长255个字符");
		}
	}
}