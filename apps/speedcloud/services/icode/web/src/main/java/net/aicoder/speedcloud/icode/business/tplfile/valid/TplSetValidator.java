package net.aicoder.speedcloud.icode.business.tplfile.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.icode.business.tplfile.domain.TplSet;
import net.aicoder.speedcloud.icode.business.tplfile.dto.TplSetAddDto;
import net.aicoder.speedcloud.icode.business.tplfile.dto.TplSetCondition;
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
            this.validateTplSetAddDto((TplSetAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj, errors);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<TplSetCondition> search, Errors errors) {
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new TplSetCondition());
        }
        if(search.getSearchCondition().getTid() == null){
        	errors.rejectValue("NOT_TENANT_ID", "未正确设置租户ID");
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param tplSet 公共代码模板集合
     * @param errors
     */
	public void validateTplSetAddDto(TplSetAddDto tplSet, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(tplSet.getCode()) > 255){
			errors.rejectValue(TplSet.PROPERTY_CODE,null,"集合代码最长255个字符");
		}
		if(StringUtils.length(tplSet.getName()) > 255){
			errors.rejectValue(TplSet.PROPERTY_NAME,null,"集合名称最长255个字符");
		}
		if(StringUtils.length(tplSet.getParentId()) > 255){
			errors.rejectValue(TplSet.PROPERTY_PARENT_ID,null,"parent_id最长255个字符");
		}
		if(StringUtils.length(tplSet.getType()) > 255){
			errors.rejectValue(TplSet.PROPERTY_TYPE,null,"集合类型最长255个字符");
		}
		if(StringUtils.length(tplSet.getDescription()) > 255){
			errors.rejectValue(TplSet.PROPERTY_DESCRIPTION,null,"集合描述最长255个字符");
		}
	}
}