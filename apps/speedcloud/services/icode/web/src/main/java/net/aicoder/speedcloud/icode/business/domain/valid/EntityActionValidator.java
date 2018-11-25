package net.aicoder.speedcloud.icode.business.domain.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.icode.business.domain.domain.EntityAction;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityActionAddDto;
import net.aicoder.speedcloud.icode.business.domain.dto.EntityActionCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class EntityActionValidator implements Validator {

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
	    if(obj instanceof EntityActionAddDto){
            this.validateEntityActionAddDto((EntityActionAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj, errors);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<EntityActionCondition> search, Errors errors) {
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new EntityActionCondition());
        }
        if(search.getSearchCondition().getTid() == null){
        	errors.rejectValue("NOT_TENANT_ID", "未正确设置租户ID");
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param entityAction 领域对象行为
     * @param errors
     */
	public void validateEntityActionAddDto(EntityActionAddDto entityAction, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(entityAction.getCode()) > 255){
			errors.rejectValue(EntityAction.PROPERTY_CODE,null,"代码最长255个字符");
		}
		if(StringUtils.length(entityAction.getName()) > 255){
			errors.rejectValue(EntityAction.PROPERTY_NAME,null,"行为名称最长255个字符");
		}
		if(StringUtils.length(entityAction.getMemo()) > 255){
			errors.rejectValue(EntityAction.PROPERTY_MEMO,null,"备注最长255个字符");
		}
		if(StringUtils.length(entityAction.getRequest()) > 255){
			errors.rejectValue(EntityAction.PROPERTY_REQUEST,null,"行为输入对象最长255个字符");
		}
		if(StringUtils.length(entityAction.getResponse()) > 255){
			errors.rejectValue(EntityAction.PROPERTY_RESPONSE,null,"行为响应对象最长255个字符");
		}
		if(StringUtils.length(entityAction.getEntity()) > 255){
			errors.rejectValue(EntityAction.PROPERTY_ENTITY,null,"所属领域对象最长255个字符");
		}
		if(StringUtils.length(entityAction.getType()) > 255){
			errors.rejectValue(EntityAction.PROPERTY_TYPE,null,"行为类型最长255个字符");
		}
	}
}