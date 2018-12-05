package net.aicoder.speedcloud.business.app.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.app.domain.ApplicationType;
import net.aicoder.speedcloud.business.app.dto.ApplicationTypeAddDto;
import net.aicoder.speedcloud.business.app.dto.ApplicationTypeCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class ApplicationTypeValidator implements Validator {

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
	    if(obj instanceof ApplicationTypeAddDto){
            this.validateApplicationTypeAddDto((ApplicationTypeAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj, errors);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<ApplicationTypeCondition> search, Errors errors) {
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new ApplicationTypeCondition());
        }
        if(search.getSearchCondition().getTid() == null){
        	errors.rejectValue("NOT_TENANT_ID", "未正确设置租户ID");
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param applicationType 应用类型
     * @param errors
     */
	public void validateApplicationTypeAddDto(ApplicationTypeAddDto applicationType, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(applicationType.getCode()) > 255){
			errors.rejectValue(ApplicationType.PROPERTY_CODE,null,"代码最长255个字符");
		}
		if(StringUtils.length(applicationType.getName()) > 255){
			errors.rejectValue(ApplicationType.PROPERTY_NAME,null,"名称最长255个字符");
		}
		if(StringUtils.length(applicationType.getCategory()) > 255){
			errors.rejectValue(ApplicationType.PROPERTY_CATEGORY,null,"种类最长255个字符");
		}
		if(StringUtils.length(applicationType.getIcon()) > 255){
			errors.rejectValue(ApplicationType.PROPERTY_ICON,null,"默认图标最长255个字符");
		}
	}
}