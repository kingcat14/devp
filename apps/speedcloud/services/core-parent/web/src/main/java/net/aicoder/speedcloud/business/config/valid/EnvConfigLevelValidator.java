package net.aicoder.speedcloud.business.config.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.config.domain.EnvConfigLevel;
import net.aicoder.speedcloud.business.config.dto.EnvConfigLevelAddDto;
import net.aicoder.speedcloud.business.config.dto.EnvConfigLevelCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class EnvConfigLevelValidator implements Validator {

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
	    if(obj instanceof EnvConfigLevelAddDto){
            this.validateEnvConfigLevelAddDto((EnvConfigLevelAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<EnvConfigLevelCondition> search){
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new EnvConfigLevelCondition());
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param envConfigLevel 环境级别
     * @param errors
     */
	public void validateEnvConfigLevelAddDto(EnvConfigLevelAddDto envConfigLevel, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(envConfigLevel.getName()) > 255){
			errors.rejectValue(EnvConfigLevel.PROPERTY_NAME,null,"名称最长255个字符");
		}
		if(StringUtils.length(envConfigLevel.getCode()) > 255){
			errors.rejectValue(EnvConfigLevel.PROPERTY_CODE,null,"代码最长255个字符");
		}
		if(StringUtils.length(envConfigLevel.getType()) > 255){
			errors.rejectValue(EnvConfigLevel.PROPERTY_TYPE,null,"类型最长255个字符");
		}
	}
}