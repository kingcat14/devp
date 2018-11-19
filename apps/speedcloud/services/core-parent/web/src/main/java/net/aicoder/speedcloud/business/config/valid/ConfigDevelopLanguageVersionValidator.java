package net.aicoder.speedcloud.business.config.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.config.domain.ConfigDevelopLanguageVersion;
import net.aicoder.speedcloud.business.config.dto.ConfigDevelopLanguageVersionAddDto;
import net.aicoder.speedcloud.business.config.dto.ConfigDevelopLanguageVersionCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class ConfigDevelopLanguageVersionValidator implements Validator {

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
	    if(obj instanceof ConfigDevelopLanguageVersionAddDto){
            this.validateConfigDevelopLanguageVersionAddDto((ConfigDevelopLanguageVersionAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<ConfigDevelopLanguageVersionCondition> search){
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new ConfigDevelopLanguageVersionCondition());
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param configDevelopLanguageVersion 开发语言版本
     * @param errors
     */
	public void validateConfigDevelopLanguageVersionAddDto(ConfigDevelopLanguageVersionAddDto configDevelopLanguageVersion, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(configDevelopLanguageVersion.getName()) > 255){
			errors.rejectValue(ConfigDevelopLanguageVersion.PROPERTY_NAME,null,"版本号最长255个字符");
		}
	}
}