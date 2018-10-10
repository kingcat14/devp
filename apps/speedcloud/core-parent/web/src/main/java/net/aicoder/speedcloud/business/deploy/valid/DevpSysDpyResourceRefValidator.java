package net.aicoder.speedcloud.business.deploy.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.deploy.dto.DevpSysDpyResourceRefAddDto;
import net.aicoder.speedcloud.business.deploy.dto.DevpSysDpyResourceRefEditDto;
import net.aicoder.speedcloud.business.deploy.dto.DevpSysDpyResourceRefCondition;
import net.aicoder.speedcloud.business.deploy.domain.DevpSysDpyResourceRef;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class DevpSysDpyResourceRefValidator implements Validator {

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
	    if(obj instanceof DevpSysDpyResourceRefAddDto){
            this.validateDevpSysDpyResourceRefAddDto((DevpSysDpyResourceRefAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<DevpSysDpyResourceRefCondition> search){
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new DevpSysDpyResourceRefCondition());
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param devpSysDpyResourceRef 方案资源间关系
     * @param errors
     */
	public void validateDevpSysDpyResourceRefAddDto(DevpSysDpyResourceRefAddDto devpSysDpyResourceRef, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(devpSysDpyResourceRef.getCode()) > 255){
			errors.rejectValue(DevpSysDpyResourceRef.PROPERTY_CODE,null,"对应关系代码最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResourceRef.getName()) > 255){
			errors.rejectValue(DevpSysDpyResourceRef.PROPERTY_NAME,null,"对应关系名称最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResourceRef.getAlias()) > 255){
			errors.rejectValue(DevpSysDpyResourceRef.PROPERTY_ALIAS,null,"对应关系别名最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResourceRef.getDescription()) > 255){
			errors.rejectValue(DevpSysDpyResourceRef.PROPERTY_DESCRIPTION,null,"对应关系描述最长255个字符");
		}
		if(StringUtils.length(devpSysDpyResourceRef.getDirection()) > 255){
			errors.rejectValue(DevpSysDpyResourceRef.PROPERTY_DIRECTION,null,"对应关系方向最长255个字符");
		}
	}
}