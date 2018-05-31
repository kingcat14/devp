package net.aicoder.devp.maintenance.business.product.product.valid;


import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.product.business.product.dto.DevpSysExtcmpAddDto;
import net.aicoder.devp.product.business.product.dto.DevpSysExtcmpEditDto;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class DevpSysExtcmpValidator implements Validator {

	/**
	 * 判断支持的JavaBean类型
	 * @param aClass
	 * @return
	 */
	@Override
	public boolean supports(Class<?> aClass) {
		if(DevpSysExtcmpAddDto.class.equals(aClass))
			return true;
		if(DevpSysExtcmpEditDto.class.equals(aClass))
			return true;
		if(PageSearchRequest.class.equals(aClass))
			return true;
		return false;
	}

	/**
	 * 实现Validator中的validate接口
	 * @param obj
	 * @param errors
	 */
	@Override
	public void validate(Object obj, Errors errors) {
	    if(obj instanceof DevpSysExtcmpAddDto){
            this.validateAddDto((DevpSysExtcmpAddDto)obj, errors);
        }
	}

	/**
     * 验证新增信息
     * @param devpSysExtcmp 产品包含的外部组件
     * @param errors
     */
	public void validateAddDto(DevpSysExtcmpAddDto devpSysExtcmp, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if (null == devpSysExtcmp.getTid() ) {
			errors.rejectValue("tid", "EMPTY_TID", "租户编号不能为空");
		}
		if (null == devpSysExtcmp.getPrdRid() ) {
			errors.rejectValue("prdRid", "EMPTY_PRD_RID", "产品编号不能为空");
		}
		if (null == devpSysExtcmp.getExtPrdRid() ) {
			errors.rejectValue("extPrdRid", "EMPTY_EXT_PRD_RID", "外部产品编号不能为空");
		}
		if (null == devpSysExtcmp.getExtElmRid() ) {
			errors.rejectValue("extElmRid", "EMPTY_EXT_ELM_RID", "外部系统元素编号不能为空");
		}

		//验证长度
		if(StringUtils.length(devpSysExtcmp.getCode()) > 255){
			errors.rejectValue("code", null, "代码最长255个字符");
		}
		if(StringUtils.length(devpSysExtcmp.getName()) > 255){
			errors.rejectValue("name", null, "名称最长255个字符");
		}
		if(StringUtils.length(devpSysExtcmp.getAlias()) > 255){
			errors.rejectValue("alias", null, "别名最长255个字符");
		}
		if(StringUtils.length(devpSysExtcmp.getDescription()) > 255){
			errors.rejectValue("description", null, "描述最长255个字符");
		}
		if(StringUtils.length(devpSysExtcmp.getType()) > 255){
			errors.rejectValue("type", null, "类型最长255个字符");
		}
		if(StringUtils.length(devpSysExtcmp.getStereotype()) > 255){
			errors.rejectValue("stereotype", null, "构造型最长255个字符");
		}
		if(StringUtils.length(devpSysExtcmp.getCreateUcode()) > 255){
			errors.rejectValue("createUcode", null, "创建用户代码最长255个字符");
		}
		if(StringUtils.length(devpSysExtcmp.getModifyUcode()) > 255){
			errors.rejectValue("modifyUcode", null, "修改用户代码最长255个字符");
		}
	}
}