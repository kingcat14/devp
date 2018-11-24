package net.aicoder.speedcloud.icode.business.tplfile.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.icode.business.tplfile.domain.TplCode;
import net.aicoder.speedcloud.icode.business.tplfile.dto.TplCodeAddDto;
import net.aicoder.speedcloud.icode.business.tplfile.dto.TplCodeCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class TplCodeValidator implements Validator {

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
	    if(obj instanceof TplCodeAddDto){
            this.validateTplCodeAddDto((TplCodeAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj, errors);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<TplCodeCondition> search, Errors errors) {
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new TplCodeCondition());
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param tplCode 公共代码模板
     * @param errors
     */
	public void validateTplCodeAddDto(TplCodeAddDto tplCode, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(tplCode.getCode()) > 255){
			errors.rejectValue(TplCode.PROPERTY_CODE,null,"模板代码最长255个字符");
		}
		if(StringUtils.length(tplCode.getName()) > 255){
			errors.rejectValue(TplCode.PROPERTY_NAME,null,"模板名称最长255个字符");
		}
		if(StringUtils.length(tplCode.getType()) > 255){
			errors.rejectValue(TplCode.PROPERTY_TYPE,null,"模板类型最长255个字符");
		}
		if(StringUtils.length(tplCode.getContent()) > 255){
			errors.rejectValue(TplCode.PROPERTY_CONTENT,null,"模板内容最长255个字符");
		}
		if(StringUtils.length(tplCode.getFileName()) > 255){
			errors.rejectValue(TplCode.PROPERTY_FILE_NAME,null,"文件名称最长255个字符");
		}
		if(StringUtils.length(tplCode.getFilePath()) > 255){
			errors.rejectValue(TplCode.PROPERTY_FILE_PATH,null,"文件路径最长255个字符");
		}
		if(StringUtils.length(tplCode.getTplSet()) > 255){
			errors.rejectValue(TplCode.PROPERTY_TPL_SET,null,"模板集合最长255个字符");
		}
		if(StringUtils.length(tplCode.getAcceptModelType()) > 255){
			errors.rejectValue(TplCode.PROPERTY_ACCEPT_MODEL_TYPE,null,"接受的模型类型最长255个字符");
		}
	}
}