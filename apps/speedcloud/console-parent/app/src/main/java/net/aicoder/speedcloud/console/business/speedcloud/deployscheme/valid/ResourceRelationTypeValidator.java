package net.aicoder.speedcloud.console.business.speedcloud.deployscheme.valid;


import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceRelationTypeAddDto;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceRelationTypeEditDto;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class ResourceRelationTypeValidator implements Validator {

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
	    if(obj instanceof ResourceRelationTypeAddDto){
            this.validateAddDto((ResourceRelationTypeAddDto)obj, errors);
        }
	}

	/**
     * 验证新增信息
     * @param resourceRelationType 资源关系类型
     * @param errors
     */
		public void validateAddDto(ResourceRelationTypeAddDto resourceRelationType, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(resourceRelationType.getName()) > 255){
			errors.rejectValue("name", null, "名称最长255个字符");
		}
		if(StringUtils.length(resourceRelationType.getCode()) > 255){
			errors.rejectValue("code", null, "代码最长255个字符");
		}
		if(StringUtils.length(resourceRelationType.getIcon()) > 255){
			errors.rejectValue("icon", null, "图标最长255个字符");
		}
	}
}