package net.aicoder.speedcloud.console.business.jointjs.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.console.business.jointjs.domain.JointData;
import net.aicoder.speedcloud.console.business.jointjs.dto.JointDataAddDto;
import net.aicoder.speedcloud.console.business.jointjs.dto.JointDataCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class JointDataValidator implements Validator {

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
	    if(obj instanceof JointDataAddDto){
            this.validateJointDataAddDto((JointDataAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<JointDataCondition> search){
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new JointDataCondition());
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param jointData 图形数据
     * @param errors
     */
	public void validateJointDataAddDto(JointDataAddDto jointData, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(jointData.getType()) > 255){
			errors.rejectValue(JointData.PROPERTY_TYPE,null,"类型最长255个字符");
		}
	}
}