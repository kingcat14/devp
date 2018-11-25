package net.aicoder.speedcloud.icode.business.domain.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.icode.business.domain.domain.EntityActionParameterProperty;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityActionParameterPropertyAddDto;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityActionParameterPropertyCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class EntityActionParameterPropertyValidator implements Validator {

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
	    if(obj instanceof EntityActionParameterPropertyAddDto){
            this.validateEntityActionParameterPropertyAddDto((EntityActionParameterPropertyAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj, errors);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<EntityActionParameterPropertyCondition> search, Errors errors) {
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new EntityActionParameterPropertyCondition());
        }
        if(search.getSearchCondition().getTid() == null){
        	errors.rejectValue("NOT_TENANT_ID", "未正确设置租户ID");
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param entityActionParameterProperty 领域对象行为参数属性
     * @param errors
     */
	public void validateEntityActionParameterPropertyAddDto(EntityActionParameterPropertyAddDto entityActionParameterProperty, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(entityActionParameterProperty.getActionParameter()) > 255){
			errors.rejectValue(EntityActionParameterProperty.PROPERTY_ACTION_PARAMETER,null,"所属行为参数最长255个字符");
		}
		if(StringUtils.length(entityActionParameterProperty.getCode()) > 255){
			errors.rejectValue(EntityActionParameterProperty.PROPERTY_CODE,null,"属性代码最长255个字符");
		}
		if(StringUtils.length(entityActionParameterProperty.getName()) > 255){
			errors.rejectValue(EntityActionParameterProperty.PROPERTY_NAME,null,"属性名称最长255个字符");
		}
		if(StringUtils.length(entityActionParameterProperty.getType()) > 255){
			errors.rejectValue(EntityActionParameterProperty.PROPERTY_TYPE,null,"类型最长255个字符");
		}
		if(StringUtils.length(entityActionParameterProperty.getWrapperType()) > 255){
			errors.rejectValue(EntityActionParameterProperty.PROPERTY_WRAPPER_TYPE,null,"外覆类型最长255个字符");
		}
		if(StringUtils.length(entityActionParameterProperty.getMemo()) > 255){
			errors.rejectValue(EntityActionParameterProperty.PROPERTY_MEMO,null,"备注最长255个字符");
		}
	}
}