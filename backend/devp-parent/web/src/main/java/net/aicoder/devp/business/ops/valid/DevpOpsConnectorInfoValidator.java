package net.aicoder.devp.business.ops.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.business.ops.dto.DevpOpsConnectorInfoAddDto;
import net.aicoder.devp.business.ops.dto.DevpOpsConnectorInfoEditDto;
import net.aicoder.devp.business.ops.dto.DevpOpsConnectorInfoCondition;
import net.aicoder.devp.business.ops.domain.DevpOpsConnectorInfo;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class DevpOpsConnectorInfoValidator implements Validator {

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
	    if(obj instanceof DevpOpsConnectorInfoAddDto){
            this.validateDevpOpsConnectorInfoAddDto((DevpOpsConnectorInfoAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<DevpOpsConnectorInfoCondition> search){
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new DevpOpsConnectorInfoCondition());
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param devpOpsConnectorInfo 运维元素连接扩充信息
     * @param errors
     */
	public void validateDevpOpsConnectorInfoAddDto(DevpOpsConnectorInfoAddDto devpOpsConnectorInfo, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(devpOpsConnectorInfo.getEtype()) > 255){
			errors.rejectValue(DevpOpsConnectorInfo.PROPERTY_ETYPE,null,"元素类型最长255个字符");
		}
		if(StringUtils.length(devpOpsConnectorInfo.getCode()) > 255){
			errors.rejectValue(DevpOpsConnectorInfo.PROPERTY_CODE,null,"扩展信息代码最长255个字符");
		}
		if(StringUtils.length(devpOpsConnectorInfo.getName()) > 255){
			errors.rejectValue(DevpOpsConnectorInfo.PROPERTY_NAME,null,"扩展信息名称最长255个字符");
		}
		if(StringUtils.length(devpOpsConnectorInfo.getAlias()) > 255){
			errors.rejectValue(DevpOpsConnectorInfo.PROPERTY_ALIAS,null,"扩展信息别名最长255个字符");
		}
		if(StringUtils.length(devpOpsConnectorInfo.getDescription()) > 255){
			errors.rejectValue(DevpOpsConnectorInfo.PROPERTY_DESCRIPTION,null,"扩展信息描述最长255个字符");
		}
		if(StringUtils.length(devpOpsConnectorInfo.getTypeCode()) > 255){
			errors.rejectValue(DevpOpsConnectorInfo.PROPERTY_TYPE_CODE,null,"设值方式代码最长255个字符");
		}
		if(StringUtils.length(devpOpsConnectorInfo.getTypeName()) > 255){
			errors.rejectValue(DevpOpsConnectorInfo.PROPERTY_TYPE_NAME,null,"设值方式名称最长255个字符");
		}
		if(StringUtils.length(devpOpsConnectorInfo.getInfoCode1()) > 255){
			errors.rejectValue(DevpOpsConnectorInfo.PROPERTY_INFO_CODE1,null,"扩展信息代码1最长255个字符");
		}
		if(StringUtils.length(devpOpsConnectorInfo.getInfoValue1()) > 255){
			errors.rejectValue(DevpOpsConnectorInfo.PROPERTY_INFO_VALUE1,null,"扩展信息值1最长255个字符");
		}
		if(StringUtils.length(devpOpsConnectorInfo.getInfoCode2()) > 255){
			errors.rejectValue(DevpOpsConnectorInfo.PROPERTY_INFO_CODE2,null,"扩展信息代码2最长255个字符");
		}
		if(StringUtils.length(devpOpsConnectorInfo.getInfoValue2()) > 255){
			errors.rejectValue(DevpOpsConnectorInfo.PROPERTY_INFO_VALUE2,null,"扩展信息值2最长255个字符");
		}
		if(StringUtils.length(devpOpsConnectorInfo.getInfoCode3()) > 255){
			errors.rejectValue(DevpOpsConnectorInfo.PROPERTY_INFO_CODE3,null,"扩展信息代码3最长255个字符");
		}
		if(StringUtils.length(devpOpsConnectorInfo.getInfoValue3()) > 255){
			errors.rejectValue(DevpOpsConnectorInfo.PROPERTY_INFO_VALUE3,null,"扩展信息值3最长255个字符");
		}
		if(StringUtils.length(devpOpsConnectorInfo.getInfoCode4()) > 255){
			errors.rejectValue(DevpOpsConnectorInfo.PROPERTY_INFO_CODE4,null,"扩展信息代码4最长255个字符");
		}
		if(StringUtils.length(devpOpsConnectorInfo.getInfoValue4()) > 255){
			errors.rejectValue(DevpOpsConnectorInfo.PROPERTY_INFO_VALUE4,null,"扩展信息值4最长255个字符");
		}
		if(StringUtils.length(devpOpsConnectorInfo.getInfoCode5()) > 255){
			errors.rejectValue(DevpOpsConnectorInfo.PROPERTY_INFO_CODE5,null,"扩展信息代码5最长255个字符");
		}
		if(StringUtils.length(devpOpsConnectorInfo.getInfoValue5()) > 255){
			errors.rejectValue(DevpOpsConnectorInfo.PROPERTY_INFO_VALUE5,null,"扩展信息值5最长255个字符");
		}
		if(StringUtils.length(devpOpsConnectorInfo.getNotes()) > 255){
			errors.rejectValue(DevpOpsConnectorInfo.PROPERTY_NOTES,null,"备注最长255个字符");
		}
		if(StringUtils.length(devpOpsConnectorInfo.getParasCode()) > 255){
			errors.rejectValue(DevpOpsConnectorInfo.PROPERTY_PARAS_CODE,null,"参数定义标识最长255个字符");
		}
		if(StringUtils.length(devpOpsConnectorInfo.getCreateUcode()) > 255){
			errors.rejectValue(DevpOpsConnectorInfo.PROPERTY_CREATE_UCODE,null,"创建用户代码最长255个字符");
		}
		if(StringUtils.length(devpOpsConnectorInfo.getCreateUname()) > 255){
			errors.rejectValue(DevpOpsConnectorInfo.PROPERTY_CREATE_UNAME,null,"创建用户姓名最长255个字符");
		}
		if(StringUtils.length(devpOpsConnectorInfo.getModifyUcode()) > 255){
			errors.rejectValue(DevpOpsConnectorInfo.PROPERTY_MODIFY_UCODE,null,"修改用户代码最长255个字符");
		}
		if(StringUtils.length(devpOpsConnectorInfo.getModifyUname()) > 255){
			errors.rejectValue(DevpOpsConnectorInfo.PROPERTY_MODIFY_UNAME,null,"修改用户姓名最长255个字符");
		}
	}
}