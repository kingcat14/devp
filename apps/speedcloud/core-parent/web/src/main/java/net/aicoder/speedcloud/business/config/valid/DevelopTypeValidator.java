package net.aicoder.speedcloud.business.config.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.config.dto.DevelopTypeAddDto;
import net.aicoder.speedcloud.business.config.dto.DevelopTypeEditDto;
import net.aicoder.speedcloud.business.config.dto.DevelopTypeCondition;
import net.aicoder.speedcloud.business.config.domain.DevelopType;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class DevelopTypeValidator implements Validator {

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
	    if(obj instanceof DevelopTypeAddDto){
            this.validateDevelopTypeAddDto((DevelopTypeAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<DevelopTypeCondition> search){
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new DevelopTypeCondition());
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param developType 开发模式
     * @param errors
     */
	public void validateDevelopTypeAddDto(DevelopTypeAddDto developType, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(developType.getName()) > 255){
			errors.rejectValue(DevelopType.PROPERTY_NAME,null,"名称最长255个字符");
		}
	}
}