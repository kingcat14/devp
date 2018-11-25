package net.aicoder.speedcloud.icode.business.domain.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.icode.business.domain.domain.EntityActionParameter;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityActionParameterAddDto;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityActionParameterCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class EntityActionParameterValidator implements Validator {

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
	    if(obj instanceof EntityActionParameterAddDto){
            this.validateEntityActionParameterAddDto((EntityActionParameterAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj, errors);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<EntityActionParameterCondition> search, Errors errors) {
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new EntityActionParameterCondition());
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param entityActionParameter 领域对象行为参数
     * @param errors
     */
	public void validateEntityActionParameterAddDto(EntityActionParameterAddDto entityActionParameter, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(entityActionParameter.getCode()) > 255){
			errors.rejectValue(EntityActionParameter.PROPERTY_CODE,null,"对象代码最长255个字符");
		}
		if(StringUtils.length(entityActionParameter.getName()) > 255){
			errors.rejectValue(EntityActionParameter.PROPERTY_NAME,null,"对象名称最长255个字符");
		}
		if(StringUtils.length(entityActionParameter.getMemo()) > 255){
			errors.rejectValue(EntityActionParameter.PROPERTY_MEMO,null,"备注最长255个字符");
		}
		if(StringUtils.length(entityActionParameter.getDescription()) > 255){
			errors.rejectValue(EntityActionParameter.PROPERTY_DESCRIPTION,null,"描述最长255个字符");
		}
		if(StringUtils.length(entityActionParameter.getEntityAction()) > 255){
			errors.rejectValue(EntityActionParameter.PROPERTY_ENTITY_ACTION,null,"领域对象行为最长255个字符");
		}
	}
}