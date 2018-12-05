package net.aicoder.speedcloud.icode.business.domain.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.icode.business.domain.domain.EntityProperty;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityPropertyAddDto;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityPropertyCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class EntityPropertyValidator implements Validator {

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
	    if(obj instanceof EntityPropertyAddDto){
            this.validateEntityPropertyAddDto((EntityPropertyAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj, errors);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<EntityPropertyCondition> search, Errors errors) {
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new EntityPropertyCondition());
        }
        if(search.getSearchCondition().getTid() == null){
        	errors.rejectValue("NOT_TENANT_ID", "未正确设置租户ID");
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param entityProperty 领域对象属性
     * @param errors
     */
	public void validateEntityPropertyAddDto(EntityPropertyAddDto entityProperty, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(entityProperty.getEntity()) > 255){
			errors.rejectValue(EntityProperty.PROPERTY_ENTITY,null,"所属领域对象最长255个字符");
		}
		if(StringUtils.length(entityProperty.getCode()) > 255){
			errors.rejectValue(EntityProperty.PROPERTY_CODE,null,"属性代码最长255个字符");
		}
		if(StringUtils.length(entityProperty.getName()) > 255){
			errors.rejectValue(EntityProperty.PROPERTY_NAME,null,"名称最长255个字符");
		}
		if(StringUtils.length(entityProperty.getType()) > 255){
			errors.rejectValue(EntityProperty.PROPERTY_TYPE,null,"类型最长255个字符");
		}
		if(StringUtils.length(entityProperty.getRelatedEntityId()) > 255){
			errors.rejectValue(EntityProperty.PROPERTY_RELATED_ENTITY_ID,null,"引用对象最长255个字符");
		}
		if(StringUtils.length(entityProperty.getRelatedEntityPropertyId()) > 255){
			errors.rejectValue(EntityProperty.PROPERTY_RELATED_ENTITY_PROPERTY_CODE,null,"引用对象属性代码最长255个字符");
		}
		if(StringUtils.length(entityProperty.getMemo()) > 255){
			errors.rejectValue(EntityProperty.PROPERTY_MEMO,null,"备注最长255个字符");
		}
	}
}