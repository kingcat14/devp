package net.aicoder.devp.maintenance.business.product.sys.valid;


import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.product.business.sys.dto.DevpSysCmpModuleAddDto;
import net.aicoder.devp.product.business.sys.dto.DevpSysCmpModuleEditDto;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class DevpSysCmpModuleValidator implements Validator {

	/**
	 * 判断支持的JavaBean类型
	 * @param aClass
	 * @return
	 */
	@Override
	public boolean supports(Class<?> aClass) {
		if(DevpSysCmpModuleAddDto.class.equals(aClass))
			return true;
		if(DevpSysCmpModuleEditDto.class.equals(aClass))
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
	    if(obj instanceof DevpSysCmpModuleAddDto){
            this.validateAddDto((DevpSysCmpModuleAddDto)obj, errors);
        }
	}

	/**
     * 验证新增信息
     * @param devpSysCmpModule 组件对应模块
     * @param errors
     */
	public void validateAddDto(DevpSysCmpModuleAddDto devpSysCmpModule, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if (null == devpSysCmpModule.getTid() ) {
			errors.rejectValue("tid", "EMPTY_TID", "租户编号不能为空");
		}
		if(StringUtils.isEmpty(devpSysCmpModule.getEtype())){
			errors.rejectValue("etype", "EMPTY_ETYPE", "元素类型不能为空");
		}
       
		if (null == devpSysCmpModule.getPrdRid() ) {
			errors.rejectValue("prdRid", "EMPTY_PRD_RID", "产品编号不能为空");
		}
		if (null == devpSysCmpModule.getCmpRid() ) {
			errors.rejectValue("cmpRid", "EMPTY_CMP_RID", "组件编号不能为空");
		}
		if (null == devpSysCmpModule.getMduRid() ) {
			errors.rejectValue("mduRid", "EMPTY_MDU_RID", "模块编号不能为空");
		}

		//验证长度
		if(StringUtils.length(devpSysCmpModule.getEtype()) > 255){
			errors.rejectValue("etype", null, "元素类型最长255个字符");
		}
		if(StringUtils.length(devpSysCmpModule.getName()) > 255){
			errors.rejectValue("name", null, "对应关系名称最长255个字符");
		}
		if(StringUtils.length(devpSysCmpModule.getCode()) > 255){
			errors.rejectValue("code", null, "对应关系代码最长255个字符");
		}
		if(StringUtils.length(devpSysCmpModule.getAlias()) > 255){
			errors.rejectValue("alias", null, "对应关系别名最长255个字符");
		}
		if(StringUtils.length(devpSysCmpModule.getDescription()) > 255){
			errors.rejectValue("description", null, "对应关系描述最长255个字符");
		}
		if(StringUtils.length(devpSysCmpModule.getType()) > 255){
			errors.rejectValue("type", null, "类型最长255个字符");
		}
		if(StringUtils.length(devpSysCmpModule.getSubType()) > 255){
			errors.rejectValue("subType", null, "子类型最长255个字符");
		}
	}
}