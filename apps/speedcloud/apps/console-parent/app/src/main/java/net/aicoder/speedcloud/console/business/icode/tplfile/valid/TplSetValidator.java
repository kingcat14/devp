package net.aicoder.speedcloud.console.business.icode.tplfile.valid;


import net.aicoder.speedcloud.icode.business.tplfile.dto.TplSetAddDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class TplSetValidator implements Validator {

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
	    if(obj instanceof TplSetAddDto){
            this.validateAddDto((TplSetAddDto)obj, errors);
        }
	}

	/**
     * 验证新增信息
     * @param tplSet 公共代码模板集合
     * @param errors
     */
	public void validateAddDto(TplSetAddDto tplSet, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(tplSet.getCode()) > 255){
			errors.rejectValue("code", null, "集合代码最长255个字符");
		}
		if(StringUtils.length(tplSet.getName()) > 255){
			errors.rejectValue("name", null, "集合名称最长255个字符");
		}
		if(StringUtils.length(tplSet.getParentId()) > 255){
			errors.rejectValue("parentId", null, "parent_id最长255个字符");
		}
		if(StringUtils.length(tplSet.getType()) > 255){
			errors.rejectValue("type", null, "集合类型最长255个字符");
		}
		if(StringUtils.length(tplSet.getDescription()) > 255){
			errors.rejectValue("description", null, "集合描述最长255个字符");
		}
	}
}