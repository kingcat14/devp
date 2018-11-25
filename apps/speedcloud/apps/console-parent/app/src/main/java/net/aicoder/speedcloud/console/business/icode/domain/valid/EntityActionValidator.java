package net.aicoder.speedcloud.console.business.icode.domain.valid;


import net.aicoder.speedcloud.icode.business.domain.dto.EntityActionAddDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class EntityActionValidator implements Validator {

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
	    if(obj instanceof EntityActionAddDto){
            this.validateAddDto((EntityActionAddDto)obj, errors);
        }
	}

	/**
     * 验证新增信息
     * @param entityAction 领域对象行为
     * @param errors
     */
	public void validateAddDto(EntityActionAddDto entityAction, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if(StringUtils.isEmpty(entityAction.getCode())){
			errors.rejectValue("code", "EMPTY_CODE", "代码不能为空");
		}
		
		if(StringUtils.isEmpty(entityAction.getName())){
			errors.rejectValue("name", "EMPTY_NAME", "名称不能为空");
		}
		

		//验证长度
		if(StringUtils.length(entityAction.getCode()) > 255){
			errors.rejectValue("code", null, "代码最长255个字符");
		}
		if(StringUtils.length(entityAction.getName()) > 255){
			errors.rejectValue("name", null, "名称最长255个字符");
		}
		if(StringUtils.length(entityAction.getMemo()) > 255){
			errors.rejectValue("memo", null, "备注最长255个字符");
		}
		if(StringUtils.length(entityAction.getRequest()) > 255){
			errors.rejectValue("request", null, "行为输入对象最长255个字符");
		}
		if(StringUtils.length(entityAction.getResponse()) > 255){
			errors.rejectValue("response", null, "行为响应对象最长255个字符");
		}
		if(StringUtils.length(entityAction.getEntity()) > 255){
			errors.rejectValue("entity", null, "所属领域对象最长255个字符");
		}
		if(StringUtils.length(entityAction.getType()) > 255){
			errors.rejectValue("type", null, "行为类型最长255个字符");
		}
	}
}