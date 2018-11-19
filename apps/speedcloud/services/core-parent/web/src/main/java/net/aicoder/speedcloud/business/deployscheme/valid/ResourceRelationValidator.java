package net.aicoder.speedcloud.business.deployscheme.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.deployscheme.domain.ResourceRelation;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceRelationAddDto;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceRelationCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class ResourceRelationValidator implements Validator {

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
	    if(obj instanceof ResourceRelationAddDto){
            this.validateResourceRelationAddDto((ResourceRelationAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<ResourceRelationCondition> search){
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new ResourceRelationCondition());
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param resourceRelation 方案资源间关系
     * @param errors
     */
	public void validateResourceRelationAddDto(ResourceRelationAddDto resourceRelation, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(resourceRelation.getCode()) > 255){
			errors.rejectValue(ResourceRelation.PROPERTY_CODE,null,"对应关系代码最长255个字符");
		}
		if(StringUtils.length(resourceRelation.getName()) > 255){
			errors.rejectValue(ResourceRelation.PROPERTY_NAME,null,"对应关系名称最长255个字符");
		}
		if(StringUtils.length(resourceRelation.getAlias()) > 255){
			errors.rejectValue(ResourceRelation.PROPERTY_ALIAS,null,"对应关系别名最长255个字符");
		}
		if(StringUtils.length(resourceRelation.getDescription()) > 255){
			errors.rejectValue(ResourceRelation.PROPERTY_DESCRIPTION,null,"对应关系描述最长255个字符");
		}
		if(StringUtils.length(resourceRelation.getDirection()) > 255){
			errors.rejectValue(ResourceRelation.PROPERTY_DIRECTION,null,"对应关系方向最长255个字符");
		}
	}
}