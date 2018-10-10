package net.aicoder.speedcloud.business.deploy.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.deploy.dto.DevpSysDpyResourceRelationTypeAddDto;
import net.aicoder.speedcloud.business.deploy.dto.DevpSysDpyResourceRelationTypeEditDto;
import net.aicoder.speedcloud.business.deploy.dto.DevpSysDpyResourceRelationTypeCondition;
import net.aicoder.speedcloud.business.deploy.domain.DevpSysDpyResourceRelationType;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class DevpSysDpyResourceRelationTypeValidator implements Validator {

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
	    if(obj instanceof DevpSysDpyResourceRelationTypeAddDto){
            this.validateDevpSysDpyResourceRelationTypeAddDto((DevpSysDpyResourceRelationTypeAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<DevpSysDpyResourceRelationTypeCondition> search){
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new DevpSysDpyResourceRelationTypeCondition());
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param devpSysDpyResourceRelationType 资源关系类型
     * @param errors
     */
	public void validateDevpSysDpyResourceRelationTypeAddDto(DevpSysDpyResourceRelationTypeAddDto devpSysDpyResourceRelationType, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(devpSysDpyResourceRelationType.getName()) > 255){
			errors.rejectValue(DevpSysDpyResourceRelationType.PROPERTY_NAME,null,"名称最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResourceRelationType.getCode()) > 255){
			errors.rejectValue(DevpSysDpyResourceRelationType.PROPERTY_CODE,null,"代码最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResourceRelationType.getIcon()) > 255){
			errors.rejectValue(DevpSysDpyResourceRelationType.PROPERTY_ICON,null,"图标最长255个字符");
		}
	}
}