package net.aicoder.devp.business.ops.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.business.ops.dto.DevpOpsElementInfoAddDto;
import net.aicoder.devp.business.ops.dto.DevpOpsElementInfoEditDto;
import net.aicoder.devp.business.ops.dto.DevpOpsElementInfoCondition;
import net.aicoder.devp.business.ops.domain.DevpOpsElementInfo;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class DevpOpsElementInfoValidator implements Validator {

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
	    if(obj instanceof DevpOpsElementInfoAddDto){
            this.validateDevpOpsElementInfoAddDto((DevpOpsElementInfoAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<DevpOpsElementInfoCondition> search){
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new DevpOpsElementInfoCondition());
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param devpOpsElementInfo 运维元素扩充信息
     * @param errors
     */
	public void validateDevpOpsElementInfoAddDto(DevpOpsElementInfoAddDto devpOpsElementInfo, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(devpOpsElementInfo.getEtype()) > 255){
			errors.rejectValue(DevpOpsElementInfo.PROPERTY_ETYPE,null,"元素类型最长255个字符");
		}
		if(StringUtils.length(devpOpsElementInfo.getCode()) > 255){
			errors.rejectValue(DevpOpsElementInfo.PROPERTY_CODE,null,"扩展信息代码最长255个字符");
		}
		if(StringUtils.length(devpOpsElementInfo.getName()) > 255){
			errors.rejectValue(DevpOpsElementInfo.PROPERTY_NAME,null,"扩展信息名称最长255个字符");
		}
		if(StringUtils.length(devpOpsElementInfo.getAlias()) > 255){
			errors.rejectValue(DevpOpsElementInfo.PROPERTY_ALIAS,null,"扩展信息别名最长255个字符");
		}
		if(StringUtils.length(devpOpsElementInfo.getDescription()) > 255){
			errors.rejectValue(DevpOpsElementInfo.PROPERTY_DESCRIPTION,null,"扩展信息描述最长255个字符");
		}
		if(StringUtils.length(devpOpsElementInfo.getInfoCode1()) > 255){
			errors.rejectValue(DevpOpsElementInfo.PROPERTY_INFO_CODE1,null,"扩展信息代码1最长255个字符");
		}
		if(StringUtils.length(devpOpsElementInfo.getInfoValue1()) > 255){
			errors.rejectValue(DevpOpsElementInfo.PROPERTY_INFO_VALUE1,null,"扩展信息值1最长255个字符");
		}
		if(StringUtils.length(devpOpsElementInfo.getInfoCode2()) > 255){
			errors.rejectValue(DevpOpsElementInfo.PROPERTY_INFO_CODE2,null,"扩展信息代码2最长255个字符");
		}
		if(StringUtils.length(devpOpsElementInfo.getInfoValue2()) > 255){
			errors.rejectValue(DevpOpsElementInfo.PROPERTY_INFO_VALUE2,null,"扩展信息值2最长255个字符");
		}
		if(StringUtils.length(devpOpsElementInfo.getInfoCode3()) > 255){
			errors.rejectValue(DevpOpsElementInfo.PROPERTY_INFO_CODE3,null,"扩展信息代码3最长255个字符");
		}
		if(StringUtils.length(devpOpsElementInfo.getInfoValue3()) > 255){
			errors.rejectValue(DevpOpsElementInfo.PROPERTY_INFO_VALUE3,null,"扩展信息值3最长255个字符");
		}
		if(StringUtils.length(devpOpsElementInfo.getInfoCode4()) > 255){
			errors.rejectValue(DevpOpsElementInfo.PROPERTY_INFO_CODE4,null,"扩展信息代码4最长255个字符");
		}
		if(StringUtils.length(devpOpsElementInfo.getInfoValue4()) > 255){
			errors.rejectValue(DevpOpsElementInfo.PROPERTY_INFO_VALUE4,null,"扩展信息值4最长255个字符");
		}
		if(StringUtils.length(devpOpsElementInfo.getInfoCode5()) > 255){
			errors.rejectValue(DevpOpsElementInfo.PROPERTY_INFO_CODE5,null,"扩展信息代码5最长255个字符");
		}
		if(StringUtils.length(devpOpsElementInfo.getInfoValue5()) > 255){
			errors.rejectValue(DevpOpsElementInfo.PROPERTY_INFO_VALUE5,null,"扩展信息值5最长255个字符");
		}
		if(StringUtils.length(devpOpsElementInfo.getNotes()) > 255){
			errors.rejectValue(DevpOpsElementInfo.PROPERTY_NOTES,null,"备注最长255个字符");
		}
		if(StringUtils.length(devpOpsElementInfo.getParasCode()) > 255){
			errors.rejectValue(DevpOpsElementInfo.PROPERTY_PARAS_CODE,null,"参数定义标识最长255个字符");
		}
		if(StringUtils.length(devpOpsElementInfo.getCreateUcode()) > 255){
			errors.rejectValue(DevpOpsElementInfo.PROPERTY_CREATE_UCODE,null,"创建用户代码最长255个字符");
		}
		if(StringUtils.length(devpOpsElementInfo.getCreateUname()) > 255){
			errors.rejectValue(DevpOpsElementInfo.PROPERTY_CREATE_UNAME,null,"创建用户姓名最长255个字符");
		}
		if(StringUtils.length(devpOpsElementInfo.getModifyUcode()) > 255){
			errors.rejectValue(DevpOpsElementInfo.PROPERTY_MODIFY_UCODE,null,"修改用户代码最长255个字符");
		}
		if(StringUtils.length(devpOpsElementInfo.getModifyUname()) > 255){
			errors.rejectValue(DevpOpsElementInfo.PROPERTY_MODIFY_UNAME,null,"修改用户姓名最长255个字符");
		}
	}
}