package net.aicoder.speedcloud.business.deploy.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.deploy.dto.DevpSysDpyResourcesAddDto;
import net.aicoder.speedcloud.business.deploy.dto.DevpSysDpyResourcesEditDto;
import net.aicoder.speedcloud.business.deploy.dto.DevpSysDpyResourcesCondition;
import net.aicoder.speedcloud.business.deploy.domain.DevpSysDpyResources;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class DevpSysDpyResourcesValidator implements Validator {

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
	    if(obj instanceof DevpSysDpyResourcesAddDto){
            this.validateDevpSysDpyResourcesAddDto((DevpSysDpyResourcesAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<DevpSysDpyResourcesCondition> search){
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new DevpSysDpyResourcesCondition());
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param devpSysDpyResources 方案资源
     * @param errors
     */
	public void validateDevpSysDpyResourcesAddDto(DevpSysDpyResourcesAddDto devpSysDpyResources, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if (null == devpSysDpyResources.getScheme() ) {
			errors.rejectValue(DevpSysDpyResources.PROPERTY_SCHEME, "EMPTY_"+DevpSysDpyResources.PROPERTY_SCHEME, "所属方案不能为空");
		}

		//验证长度
		if(StringUtils.length(devpSysDpyResources.getName()) > 255){
			errors.rejectValue(DevpSysDpyResources.PROPERTY_NAME,null,"资源名称最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResources.getCode()) > 255){
			errors.rejectValue(DevpSysDpyResources.PROPERTY_CODE,null,"资源代码最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResources.getAlias()) > 255){
			errors.rejectValue(DevpSysDpyResources.PROPERTY_ALIAS,null,"资源别名最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResources.getNotes()) > 255){
			errors.rejectValue(DevpSysDpyResources.PROPERTY_NOTES,null,"备注最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResources.getVersion()) > 255){
			errors.rejectValue(DevpSysDpyResources.PROPERTY_VERSION,null,"版本最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResources.getStatus()) > 255){
			errors.rejectValue(DevpSysDpyResources.PROPERTY_STATUS,null,"状态最长255个字符");
		}
	}
}