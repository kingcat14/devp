package net.aicoder.speedcloud.icode.business.domain.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.icode.business.domain.domain.EntityViewProperty;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityViewPropertyAddDto;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityViewPropertyCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class EntityViewPropertyValidator implements Validator {

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
	    if(obj instanceof EntityViewPropertyAddDto){
            this.validateEntityViewPropertyAddDto((EntityViewPropertyAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj, errors);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<EntityViewPropertyCondition> search, Errors errors) {
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new EntityViewPropertyCondition());
        }
        if(search.getSearchCondition().getTid() == null){
        	errors.rejectValue("NOT_TENANT_ID", "未正确设置租户ID");
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param entityViewProperty 领域对象展现属性
     * @param errors
     */
	public void validateEntityViewPropertyAddDto(EntityViewPropertyAddDto entityViewProperty, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(entityViewProperty.getEntity()) > 255){
			errors.rejectValue(EntityViewProperty.PROPERTY_ENTITY,null,"所属领域对象最长255个字符");
		}
		if(StringUtils.length(entityViewProperty.getCode()) > 255){
			errors.rejectValue(EntityViewProperty.PROPERTY_CODE,null,"代码最长255个字符");
		}
		if(StringUtils.length(entityViewProperty.getName()) > 255){
			errors.rejectValue(EntityViewProperty.PROPERTY_NAME,null,"名称最长255个字符");
		}
	}
}