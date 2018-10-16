package net.aicoder.speedcloud.business.deployscheme.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceInstanceRelationAddDto;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceInstanceRelationEditDto;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceInstanceRelationCondition;
import net.aicoder.speedcloud.business.deployscheme.domain.ResourceInstanceRelation;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class ResourceInstanceRelationValidator implements Validator {

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
	    if(obj instanceof ResourceInstanceRelationAddDto){
            this.validateResourceInstanceRelationAddDto((ResourceInstanceRelationAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<ResourceInstanceRelationCondition> search){
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new ResourceInstanceRelationCondition());
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param resourceInstanceRelation 方案资源关联实例
     * @param errors
     */
	public void validateResourceInstanceRelationAddDto(ResourceInstanceRelationAddDto resourceInstanceRelation, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(resourceInstanceRelation.getType()) > 255){
			errors.rejectValue(ResourceInstanceRelation.PROPERTY_TYPE,null,"类型最长255个字符");
		}
		if(StringUtils.length(resourceInstanceRelation.getNotes()) > 255){
			errors.rejectValue(ResourceInstanceRelation.PROPERTY_NOTES,null,"备注最长255个字符");
		}
		if(StringUtils.length(resourceInstanceRelation.getAssetCategoryCode()) > 255){
			errors.rejectValue(ResourceInstanceRelation.PROPERTY_ASSET_CATEGORY_CODE,null,"关联实例类别代码最长255个字符");
		}
		if(StringUtils.length(resourceInstanceRelation.getAssetTypeCode()) > 255){
			errors.rejectValue(ResourceInstanceRelation.PROPERTY_ASSET_TYPE_CODE,null,"关联实例类型代码最长255个字符");
		}
	}
}