package net.aicoder.speedcloud.console.business.icode.domain.valid;


import net.aicoder.speedcloud.icode.business.domain.dto.ModelTypeAddDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class ModelTypeValidator implements Validator {

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
	    if(obj instanceof ModelTypeAddDto){
            this.validateAddDto((ModelTypeAddDto)obj, errors);
        }
	}

	/**
     * 验证新增信息
     * @param modelType 模型类型
     * @param errors
     */
	public void validateAddDto(ModelTypeAddDto modelType, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(modelType.getCode()) > 255){
			errors.rejectValue("code", null, "code最长255个字符");
		}
		if(StringUtils.length(modelType.getName()) > 255){
			errors.rejectValue("name", null, "name最长255个字符");
		}
	}
}