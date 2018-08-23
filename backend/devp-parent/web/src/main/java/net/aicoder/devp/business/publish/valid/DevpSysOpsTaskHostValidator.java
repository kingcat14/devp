package net.aicoder.devp.business.publish.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskHostAddDto;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskHostEditDto;
import net.aicoder.devp.business.publish.dto.DevpSysOpsTaskHostCondition;
import net.aicoder.devp.business.publish.domain.DevpSysOpsTaskHost;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class DevpSysOpsTaskHostValidator implements Validator {

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
	    if(obj instanceof DevpSysOpsTaskHostAddDto){
            this.validateDevpSysOpsTaskHostAddDto((DevpSysOpsTaskHostAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<DevpSysOpsTaskHostCondition> search){
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new DevpSysOpsTaskHostCondition());
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param devpSysOpsTaskHost 部署主机节点
     * @param errors
     */
	public void validateDevpSysOpsTaskHostAddDto(DevpSysOpsTaskHostAddDto devpSysOpsTaskHost, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(devpSysOpsTaskHost.getEtype()) > 255){
			errors.rejectValue(DevpSysOpsTaskHost.PROPERTY_ETYPE,null,"元素类型最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskHost.getName()) > 255){
			errors.rejectValue(DevpSysOpsTaskHost.PROPERTY_NAME,null,"系统元素名称最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskHost.getCode()) > 255){
			errors.rejectValue(DevpSysOpsTaskHost.PROPERTY_CODE,null,"系统元素代码最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskHost.getAlias()) > 255){
			errors.rejectValue(DevpSysOpsTaskHost.PROPERTY_ALIAS,null,"系统元素别名最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskHost.getDescription()) > 255){
			errors.rejectValue(DevpSysOpsTaskHost.PROPERTY_DESCRIPTION,null,"系统元素描述最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskHost.getStatus()) > 255){
			errors.rejectValue(DevpSysOpsTaskHost.PROPERTY_STATUS,null,"状态最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskHost.getNotes()) > 255){
			errors.rejectValue(DevpSysOpsTaskHost.PROPERTY_NOTES,null,"备注最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskHost.getCreateUcode()) > 255){
			errors.rejectValue(DevpSysOpsTaskHost.PROPERTY_CREATE_UCODE,null,"创建用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskHost.getCreateUname()) > 255){
			errors.rejectValue(DevpSysOpsTaskHost.PROPERTY_CREATE_UNAME,null,"创建用户姓名最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskHost.getModifyUcode()) > 255){
			errors.rejectValue(DevpSysOpsTaskHost.PROPERTY_MODIFY_UCODE,null,"修改用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysOpsTaskHost.getModifyUname()) > 255){
			errors.rejectValue(DevpSysOpsTaskHost.PROPERTY_MODIFY_UNAME,null,"修改用户姓名最长255个字符");
		}
	}
}