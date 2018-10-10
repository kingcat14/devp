package net.aicoder.speedcloud.business.deploy.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.deploy.dto.DevpSysDpyResourcesCategoryAddDto;
import net.aicoder.speedcloud.business.deploy.dto.DevpSysDpyResourcesCategoryEditDto;
import net.aicoder.speedcloud.business.deploy.dto.DevpSysDpyResourcesCategoryCondition;
import net.aicoder.speedcloud.business.deploy.domain.DevpSysDpyResourcesCategory;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class DevpSysDpyResourcesCategoryValidator implements Validator {

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
	    if(obj instanceof DevpSysDpyResourcesCategoryAddDto){
            this.validateDevpSysDpyResourcesCategoryAddDto((DevpSysDpyResourcesCategoryAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<DevpSysDpyResourcesCategoryCondition> search){
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new DevpSysDpyResourcesCategoryCondition());
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param devpSysDpyResourcesCategory 部署资源类别
     * @param errors
     */
	public void validateDevpSysDpyResourcesCategoryAddDto(DevpSysDpyResourcesCategoryAddDto devpSysDpyResourcesCategory, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(devpSysDpyResourcesCategory.getName()) > 255){
			errors.rejectValue(DevpSysDpyResourcesCategory.PROPERTY_NAME,null,"名称最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResourcesCategory.getCode()) > 255){
			errors.rejectValue(DevpSysDpyResourcesCategory.PROPERTY_CODE,null,"代码最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResourcesCategory.getIcon()) > 255){
			errors.rejectValue(DevpSysDpyResourcesCategory.PROPERTY_ICON,null,"图标最长255个字符");
		}
	}
}