package net.aicoder.speedcloud.business.env.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.env.domain.Machine;
import net.aicoder.speedcloud.business.env.dto.MachineAddDto;
import net.aicoder.speedcloud.business.env.dto.MachineCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

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
            this.validateMachineAddDto((MachineAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj, errors);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<MachineCondition> search, Errors errors) {
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new MachineCondition());
        }
        if(search.getSearchCondition().getTid() == null){
        	errors.rejectValue("NOT_TENANT_ID", "未正确设置租户ID");
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param machine 服务器
     * @param errors
     */
	public void validateMachineAddDto(MachineAddDto machine, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(machine.getName()) > 255){
			errors.rejectValue(Machine.PROPERTY_NAME,null,"名称最长255个字符");
		}
		if(StringUtils.length(machine.getIpAddress()) > 255){
			errors.rejectValue(Machine.PROPERTY_IP_ADDRESS,null,"IP地址最长255个字符");
		}
	}
}