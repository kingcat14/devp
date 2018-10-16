package net.aicoder.speedcloud.console.business.speedcloud.deployscheme.valid;


import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceInstanceRelationAddDto;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceInstanceRelationEditDto;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class ResourceInstanceRelationValidator implements Validator {

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
	    if(obj instanceof ResourceInstanceRelationAddDto){
            this.validateAddDto((ResourceInstanceRelationAddDto)obj, errors);
        }
	}

	/**
     * 验证新增信息
     * @param resourceInstanceRelation 方案资源关联实例
     * @param errors
     */
		public void validateAddDto(ResourceInstanceRelationAddDto resourceInstanceRelation, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(resourceInstanceRelation.getType()) > 255){
			errors.rejectValue("type", null, "类型最长255个字符");
		}
		if(StringUtils.length(resourceInstanceRelation.getNotes()) > 255){
			errors.rejectValue("notes", null, "备注最长255个字符");
		}
		if(StringUtils.length(resourceInstanceRelation.getAssetCategoryCode()) > 255){
			errors.rejectValue("assetCategoryCode", null, "关联实例类别代码最长255个字符");
		}
		if(StringUtils.length(resourceInstanceRelation.getAssetTypeCode()) > 255){
			errors.rejectValue("assetTypeCode", null, "关联实例类型代码最长255个字符");
		}
	}
}