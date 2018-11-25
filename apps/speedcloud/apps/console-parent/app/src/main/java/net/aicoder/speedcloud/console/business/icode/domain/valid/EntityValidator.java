package net.aicoder.speedcloud.console.business.icode.domain.valid;


import net.aicoder.speedcloud.icode.business.domain.dto.EntityAddDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class EntityValidator implements Validator {

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
	    if(obj instanceof EntityAddDto){
            this.validateAddDto((EntityAddDto)obj, errors);
        }
	}

	/**
     * 验证新增信息
     * @param entity 领域对象
     * @param errors
     */
	public void validateAddDto(EntityAddDto entity, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(entity.getCode()) > 255){
			errors.rejectValue("code", null, "实体代码最长255个字符");
		}
		if(StringUtils.length(entity.getName()) > 255){
			errors.rejectValue("name", null, "实体名称最长255个字符");
		}
		if(StringUtils.length(entity.getDescription()) > 255){
			errors.rejectValue("description", null, "描述最长255个字符");
		}
		if(StringUtils.length(entity.getParentEntity()) > 255){
			errors.rejectValue("parentEntity", null, "父对象最长255个字符");
		}
		if(StringUtils.length(entity.getAggregate()) > 255){
			errors.rejectValue("aggregate", null, "所属聚合最长255个字符");
		}
		if(StringUtils.length(entity.getDomain()) > 255){
			errors.rejectValue("domain", null, "所属领域最长255个字符");
		}
	}
}