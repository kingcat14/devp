package net.aicoder.devp.business.sys.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.business.sys.dto.DevpSysCmpResAddDto;
import net.aicoder.devp.business.sys.dto.DevpSysCmpResEditDto;
import net.aicoder.devp.business.sys.dto.DevpSysCmpResCondition;
import net.aicoder.devp.business.sys.domain.DevpSysCmpRes;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class DevpSysCmpResValidator implements Validator {

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
	    if(obj instanceof DevpSysCmpResAddDto){
            this.validateDevpSysCmpResAddDto((DevpSysCmpResAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<DevpSysCmpResCondition> search){
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new DevpSysCmpResCondition());
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param devpSysCmpRes 组件关联资源
     * @param errors
     */
	public void validateDevpSysCmpResAddDto(DevpSysCmpResAddDto devpSysCmpRes, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(devpSysCmpRes.getEtype()) > 255){
			errors.rejectValue(DevpSysCmpRes.PROPERTY_ETYPE,null,"元素类型最长255个字符");
		}
		if(StringUtils.length(devpSysCmpRes.getCode()) > 255){
			errors.rejectValue(DevpSysCmpRes.PROPERTY_CODE,null,"对应关系代码最长255个字符");
		}
		if(StringUtils.length(devpSysCmpRes.getName()) > 255){
			errors.rejectValue(DevpSysCmpRes.PROPERTY_NAME,null,"对应关系名称最长255个字符");
		}
		if(StringUtils.length(devpSysCmpRes.getAlias()) > 255){
			errors.rejectValue(DevpSysCmpRes.PROPERTY_ALIAS,null,"对应关系别名最长255个字符");
		}
		if(StringUtils.length(devpSysCmpRes.getDescription()) > 255){
			errors.rejectValue(DevpSysCmpRes.PROPERTY_DESCRIPTION,null,"对应关系描述最长255个字符");
		}
		if(StringUtils.length(devpSysCmpRes.getCreateUcode()) > 255){
			errors.rejectValue(DevpSysCmpRes.PROPERTY_CREATE_UCODE,null,"创建用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysCmpRes.getCreateUname()) > 255){
			errors.rejectValue(DevpSysCmpRes.PROPERTY_CREATE_UNAME,null,"创建用户姓名最长255个字符");
		}
		if(StringUtils.length(devpSysCmpRes.getModifyUcode()) > 255){
			errors.rejectValue(DevpSysCmpRes.PROPERTY_MODIFY_UCODE,null,"修改用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysCmpRes.getModifyUname()) > 255){
			errors.rejectValue(DevpSysCmpRes.PROPERTY_MODIFY_UNAME,null,"修改用户姓名最长255个字符");
		}
	}
}