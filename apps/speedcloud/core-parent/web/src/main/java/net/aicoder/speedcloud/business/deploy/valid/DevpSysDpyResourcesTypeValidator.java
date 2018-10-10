package net.aicoder.speedcloud.business.deploy.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.deploy.dto.DevpSysDpyResourcesTypeAddDto;
import net.aicoder.speedcloud.business.deploy.dto.DevpSysDpyResourcesTypeEditDto;
import net.aicoder.speedcloud.business.deploy.dto.DevpSysDpyResourcesTypeCondition;
import net.aicoder.speedcloud.business.deploy.domain.DevpSysDpyResourcesType;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class DevpSysDpyResourcesTypeValidator implements Validator {

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
	    if(obj instanceof DevpSysDpyResourcesTypeAddDto){
            this.validateDevpSysDpyResourcesTypeAddDto((DevpSysDpyResourcesTypeAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<DevpSysDpyResourcesTypeCondition> search){
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new DevpSysDpyResourcesTypeCondition());
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param devpSysDpyResourcesType 部署资源类型
     * @param errors
     */
	public void validateDevpSysDpyResourcesTypeAddDto(DevpSysDpyResourcesTypeAddDto devpSysDpyResourcesType, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(devpSysDpyResourcesType.getName()) > 255){
			errors.rejectValue(DevpSysDpyResourcesType.PROPERTY_NAME,null,"名称最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResourcesType.getCode()) > 255){
			errors.rejectValue(DevpSysDpyResourcesType.PROPERTY_CODE,null,"代码最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResourcesType.getIcon()) > 255){
			errors.rejectValue(DevpSysDpyResourcesType.PROPERTY_ICON,null,"图标最长255个字符");
		}
	}
}