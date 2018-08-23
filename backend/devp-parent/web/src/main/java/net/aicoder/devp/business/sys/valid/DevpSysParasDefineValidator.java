package net.aicoder.devp.business.sys.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.business.sys.dto.DevpSysParasDefineAddDto;
import net.aicoder.devp.business.sys.dto.DevpSysParasDefineEditDto;
import net.aicoder.devp.business.sys.dto.DevpSysParasDefineCondition;
import net.aicoder.devp.business.sys.domain.DevpSysParasDefine;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class DevpSysParasDefineValidator implements Validator {

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
	    if(obj instanceof DevpSysParasDefineAddDto){
            this.validateDevpSysParasDefineAddDto((DevpSysParasDefineAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<DevpSysParasDefineCondition> search){
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new DevpSysParasDefineCondition());
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param devpSysParasDefine 系统元素扩充信息
     * @param errors
     */
	public void validateDevpSysParasDefineAddDto(DevpSysParasDefineAddDto devpSysParasDefine, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(devpSysParasDefine.getEtype()) > 255){
			errors.rejectValue(DevpSysParasDefine.PROPERTY_ETYPE,null,"元素类型最长255个字符");
		}
		if(StringUtils.length(devpSysParasDefine.getCode()) > 255){
			errors.rejectValue(DevpSysParasDefine.PROPERTY_CODE,null,"扩展信息代码最长255个字符");
		}
		if(StringUtils.length(devpSysParasDefine.getName()) > 255){
			errors.rejectValue(DevpSysParasDefine.PROPERTY_NAME,null,"扩展信息名称最长255个字符");
		}
		if(StringUtils.length(devpSysParasDefine.getAlias()) > 255){
			errors.rejectValue(DevpSysParasDefine.PROPERTY_ALIAS,null,"扩展信息别名最长255个字符");
		}
		if(StringUtils.length(devpSysParasDefine.getDescription()) > 255){
			errors.rejectValue(DevpSysParasDefine.PROPERTY_DESCRIPTION,null,"扩展信息描述最长255个字符");
		}
		if(StringUtils.length(devpSysParasDefine.getNotes()) > 255){
			errors.rejectValue(DevpSysParasDefine.PROPERTY_NOTES,null,"备注最长255个字符");
		}
		if(StringUtils.length(devpSysParasDefine.getCreateUcode()) > 255){
			errors.rejectValue(DevpSysParasDefine.PROPERTY_CREATE_UCODE,null,"创建用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysParasDefine.getCreateUname()) > 255){
			errors.rejectValue(DevpSysParasDefine.PROPERTY_CREATE_UNAME,null,"创建用户姓名最长255个字符");
		}
		if(StringUtils.length(devpSysParasDefine.getModifyUcode()) > 255){
			errors.rejectValue(DevpSysParasDefine.PROPERTY_MODIFY_UCODE,null,"修改用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysParasDefine.getModifyUname()) > 255){
			errors.rejectValue(DevpSysParasDefine.PROPERTY_MODIFY_UNAME,null,"修改用户姓名最长255个字符");
		}
	}
}