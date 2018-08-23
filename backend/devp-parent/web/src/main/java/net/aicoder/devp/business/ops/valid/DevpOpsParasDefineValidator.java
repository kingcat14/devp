package net.aicoder.devp.business.ops.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.business.ops.dto.DevpOpsParasDefineAddDto;
import net.aicoder.devp.business.ops.dto.DevpOpsParasDefineEditDto;
import net.aicoder.devp.business.ops.dto.DevpOpsParasDefineCondition;
import net.aicoder.devp.business.ops.domain.DevpOpsParasDefine;
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
		return true;
	}

	/**
	 * 实现Validator中的validate接口
	 * @param obj
	 * @param errors
	 */
	@Override
	public void validate(Object obj, Errors errors) {
	    if(obj instanceof DevpOpsParasDefineAddDto){
            this.validateDevpOpsParasDefineAddDto((DevpOpsParasDefineAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<DevpOpsParasDefineCondition> search){
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new DevpOpsParasDefineCondition());
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param devpOpsParasDefine 运维元素扩充信息
     * @param errors
     */
	public void validateDevpOpsParasDefineAddDto(DevpOpsParasDefineAddDto devpOpsParasDefine, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(devpOpsParasDefine.getEtype()) > 255){
			errors.rejectValue(DevpOpsParasDefine.PROPERTY_ETYPE,null,"元素类型最长255个字符");
		}
		if(StringUtils.length(devpOpsParasDefine.getCode()) > 255){
			errors.rejectValue(DevpOpsParasDefine.PROPERTY_CODE,null,"扩展信息代码最长255个字符");
		}
		if(StringUtils.length(devpOpsParasDefine.getName()) > 255){
			errors.rejectValue(DevpOpsParasDefine.PROPERTY_NAME,null,"扩展信息名称最长255个字符");
		}
		if(StringUtils.length(devpOpsParasDefine.getAlias()) > 255){
			errors.rejectValue(DevpOpsParasDefine.PROPERTY_ALIAS,null,"扩展信息别名最长255个字符");
		}
		if(StringUtils.length(devpOpsParasDefine.getDescription()) > 255){
			errors.rejectValue(DevpOpsParasDefine.PROPERTY_DESCRIPTION,null,"扩展信息描述最长255个字符");
		}
		if(StringUtils.length(devpOpsParasDefine.getNotes()) > 255){
			errors.rejectValue(DevpOpsParasDefine.PROPERTY_NOTES,null,"备注最长255个字符");
		}
		if(StringUtils.length(devpOpsParasDefine.getCreateUcode()) > 255){
			errors.rejectValue(DevpOpsParasDefine.PROPERTY_CREATE_UCODE,null,"创建用户代码最长255个字符");
		}
		if(StringUtils.length(devpOpsParasDefine.getCreateUname()) > 255){
			errors.rejectValue(DevpOpsParasDefine.PROPERTY_CREATE_UNAME,null,"创建用户姓名最长255个字符");
		}
		if(StringUtils.length(devpOpsParasDefine.getModifyUcode()) > 255){
			errors.rejectValue(DevpOpsParasDefine.PROPERTY_MODIFY_UCODE,null,"修改用户代码最长255个字符");
		}
		if(StringUtils.length(devpOpsParasDefine.getModifyUname()) > 255){
			errors.rejectValue(DevpOpsParasDefine.PROPERTY_MODIFY_UNAME,null,"修改用户姓名最长255个字符");
		}
	}
}