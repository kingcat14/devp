package net.aicoder.speedcloud.console.business.icode.tplfile.valid;


import net.aicoder.speedcloud.icode.business.tplfile.dto.TplCodeAddDto;
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
            this.validateAddDto((TplCodeAddDto)obj, errors);
        }
	}

	/**
     * 验证新增信息
     * @param tplCode 公共代码模板
     * @param errors
     */
	public void validateAddDto(TplCodeAddDto tplCode, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(tplCode.getCode()) > 255){
			errors.rejectValue("code", null, "模板代码最长255个字符");
		}
		if(StringUtils.length(tplCode.getName()) > 255){
			errors.rejectValue("name", null, "模板名称最长255个字符");
		}
		if(StringUtils.length(tplCode.getType()) > 255){
			errors.rejectValue("type", null, "模板类型最长255个字符");
		}
		if(StringUtils.length(tplCode.getContent()) > 255){
			errors.rejectValue("content", null, "模板内容最长255个字符");
		}
		if(StringUtils.length(tplCode.getFileName()) > 255){
			errors.rejectValue("fileName", null, "文件名称最长255个字符");
		}
		if(StringUtils.length(tplCode.getFilePath()) > 255){
			errors.rejectValue("filePath", null, "文件路径最长255个字符");
		}
		if(StringUtils.length(tplCode.getTplSet()) > 255){
			errors.rejectValue("tplSet", null, "模板集合最长255个字符");
		}
		if(StringUtils.length(tplCode.getAcceptModelType()) > 255){
			errors.rejectValue("acceptModelType", null, "接受的模型类型最长255个字符");
		}
	}
}