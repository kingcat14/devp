package net.aicoder.devp.maintenance.business.deploy.ops.valid;


import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.deploy.business.ops.dto.DevpOpsParasDefineAddDto;
import net.aicoder.devp.deploy.business.ops.dto.DevpOpsParasDefineEditDto;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class DevpOpsParasDefineValidator implements Validator {

	/**
	 * 判断支持的JavaBean类型
	 * @param aClass
	 * @return
	 */
	@Override
	public boolean supports(Class<?> aClass) {
		if(DevpOpsParasDefineAddDto.class.equals(aClass))
			return true;
		if(DevpOpsParasDefineEditDto.class.equals(aClass))
			return true;
		if(PageSearchRequest.class.equals(aClass))
			return true;
		return false;
	}

	/**
	 * 实现Validator中的validate接口
	 * @param obj
	 * @param errors
	 */
	@Override
	public void validate(Object obj, Errors errors) {
	    if(obj instanceof DevpOpsParasDefineAddDto){
            this.validateAddDto((DevpOpsParasDefineAddDto)obj, errors);
        }
	}

	/**
     * 验证新增信息
     * @param devpOpsParasDefine 运维元素扩充信息
     * @param errors
     */
	public void validateAddDto(DevpOpsParasDefineAddDto devpOpsParasDefine, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if (null == devpOpsParasDefine.getTid() ) {
			errors.rejectValue("tid", "EMPTY_TID", "租户编号不能为空");
		}
		if(StringUtils.isEmpty(devpOpsParasDefine.getEtype())){
			errors.rejectValue("etype", "EMPTY_ETYPE", "元素类型不能为空");
		}
       
		if(StringUtils.isEmpty(devpOpsParasDefine.getCode())){
			errors.rejectValue("code", "EMPTY_CODE", "扩展信息代码不能为空");
		}
       

		//验证长度
		if(StringUtils.length(devpOpsParasDefine.getEtype()) > 255){
			errors.rejectValue("etype", null, "元素类型最长255个字符");
		}
		if(StringUtils.length(devpOpsParasDefine.getCode()) > 255){
			errors.rejectValue("code", null, "扩展信息代码最长255个字符");
		}
		if(StringUtils.length(devpOpsParasDefine.getName()) > 255){
			errors.rejectValue("name", null, "扩展信息名称最长255个字符");
		}
		if(StringUtils.length(devpOpsParasDefine.getAlias()) > 255){
			errors.rejectValue("alias", null, "扩展信息别名最长255个字符");
		}
		if(StringUtils.length(devpOpsParasDefine.getDescription()) > 255){
			errors.rejectValue("description", null, "扩展信息描述最长255个字符");
		}
		if(StringUtils.length(devpOpsParasDefine.getContent()) > 255){
			errors.rejectValue("content", null, "内容最长255个字符");
		}
		if(StringUtils.length(devpOpsParasDefine.getNotes()) > 255){
			errors.rejectValue("notes", null, "备注最长255个字符");
		}
	}
}