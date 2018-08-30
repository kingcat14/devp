package net.aicoder.speedcloud.console.business.speedCloud.env.valid;


import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.env.dto.MachineAddDto;
import net.aicoder.speedcloud.business.env.dto.MachineEditDto;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class MachineValidator implements Validator {

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
	    if(obj instanceof MachineAddDto){
            this.validateAddDto((MachineAddDto)obj, errors);
        }
	}

	/**
     * 验证新增信息
     * @param machine 服务器
     * @param errors
     */
	public void validateAddDto(MachineAddDto machine, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if (null == machine.getTid() ) {
			errors.rejectValue("tid", "EMPTY_TID", "租户id不能为空");
		}

		//验证长度
		if(StringUtils.length(machine.getName()) > 255){
			errors.rejectValue("name", null, "名称最长255个字符");
		}
		if(StringUtils.length(machine.getIpAddress()) > 255){
			errors.rejectValue("ipAddress", null, "IP地址最长255个字符");
		}
	}
}