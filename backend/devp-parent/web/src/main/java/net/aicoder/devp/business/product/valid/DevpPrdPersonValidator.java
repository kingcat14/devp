package net.aicoder.devp.business.product.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.business.product.dto.DevpPrdPersonAddDto;
import net.aicoder.devp.business.product.dto.DevpPrdPersonEditDto;
import net.aicoder.devp.business.product.dto.DevpPrdPersonCondition;
import net.aicoder.devp.business.product.domain.DevpPrdPerson;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class DevpPrdPersonValidator implements Validator {

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
	    if(obj instanceof DevpPrdPersonAddDto){
            this.validateDevpPrdPersonAddDto((DevpPrdPersonAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<DevpPrdPersonCondition> search){
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new DevpPrdPersonCondition());
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param devpPrdPerson 产品干系人
     * @param errors
     */
	public void validateDevpPrdPersonAddDto(DevpPrdPersonAddDto devpPrdPerson, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(devpPrdPerson.getEtype()) > 255){
			errors.rejectValue(DevpPrdPerson.PROPERTY_ETYPE,null,"etype最长255个字符");
		}
		if(StringUtils.length(devpPrdPerson.getCode()) > 255){
			errors.rejectValue(DevpPrdPerson.PROPERTY_CODE,null,"用户代码最长255个字符");
		}
		if(StringUtils.length(devpPrdPerson.getName()) > 255){
			errors.rejectValue(DevpPrdPerson.PROPERTY_NAME,null,"用户名称最长255个字符");
		}
		if(StringUtils.length(devpPrdPerson.getAlias()) > 255){
			errors.rejectValue(DevpPrdPerson.PROPERTY_ALIAS,null,"用户别名最长255个字符");
		}
		if(StringUtils.length(devpPrdPerson.getDescription()) > 255){
			errors.rejectValue(DevpPrdPerson.PROPERTY_DESCRIPTION,null,"用户描述最长255个字符");
		}
		if(StringUtils.length(devpPrdPerson.getNexusType()) > 255){
			errors.rejectValue(DevpPrdPerson.PROPERTY_NEXUS_TYPE,null,"关联元素类型最长255个字符");
		}
		if(StringUtils.length(devpPrdPerson.getType()) > 255){
			errors.rejectValue(DevpPrdPerson.PROPERTY_TYPE,null,"用户类型最长255个字符");
		}
		if(StringUtils.length(devpPrdPerson.getRole()) > 255){
			errors.rejectValue(DevpPrdPerson.PROPERTY_ROLE,null,"用户类型最长255个字符");
		}
		if(StringUtils.length(devpPrdPerson.getStatus()) > 255){
			errors.rejectValue(DevpPrdPerson.PROPERTY_STATUS,null,"状态最长255个字符");
		}
		if(StringUtils.length(devpPrdPerson.getOrgName()) > 255){
			errors.rejectValue(DevpPrdPerson.PROPERTY_ORG_NAME,null,"组织名称最长255个字符");
		}
		if(StringUtils.length(devpPrdPerson.getCreateUcode()) > 255){
			errors.rejectValue(DevpPrdPerson.PROPERTY_CREATE_UCODE,null,"创建用户代码最长255个字符");
		}
		if(StringUtils.length(devpPrdPerson.getCreateUname()) > 255){
			errors.rejectValue(DevpPrdPerson.PROPERTY_CREATE_UNAME,null,"创建用户姓名最长255个字符");
		}
		if(StringUtils.length(devpPrdPerson.getModifyUcode()) > 255){
			errors.rejectValue(DevpPrdPerson.PROPERTY_MODIFY_UCODE,null,"修改用户代码最长255个字符");
		}
		if(StringUtils.length(devpPrdPerson.getModifyUname()) > 255){
			errors.rejectValue(DevpPrdPerson.PROPERTY_MODIFY_UNAME,null,"修改用户姓名最长255个字符");
		}
	}
}