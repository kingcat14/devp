package net.aicoder.speedcloud.business.app.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.app.dto.AppBaseInfoAddDto;
import net.aicoder.speedcloud.business.app.dto.AppBaseInfoEditDto;
import net.aicoder.speedcloud.business.app.dto.AppBaseInfoCondition;
import net.aicoder.speedcloud.business.app.domain.AppBaseInfo;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class AppBaseInfoValidator implements Validator {

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
	    if(obj instanceof AppBaseInfoAddDto){
            this.validateAppBaseInfoAddDto((AppBaseInfoAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<AppBaseInfoCondition> search){
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new AppBaseInfoCondition());
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param appBaseInfo 应用
     * @param errors
     */
	public void validateAppBaseInfoAddDto(AppBaseInfoAddDto appBaseInfo, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(appBaseInfo.getName()) > 255){
			errors.rejectValue(AppBaseInfo.PROPERTY_NAME,null,"名称最长255个字符");
		}
		if(StringUtils.length(appBaseInfo.getType()) > 255){
			errors.rejectValue(AppBaseInfo.PROPERTY_TYPE,null,"应用类型最长255个字符");
		}
		if(StringUtils.length(appBaseInfo.getStatus()) > 255){
			errors.rejectValue(AppBaseInfo.PROPERTY_STATUS,null,"状态最长255个字符");
		}
		if(StringUtils.length(appBaseInfo.getRegistTime()) > 255){
			errors.rejectValue(AppBaseInfo.PROPERTY_REGIST_TIME,null,"注册时间最长255个字符");
		}
	}
}