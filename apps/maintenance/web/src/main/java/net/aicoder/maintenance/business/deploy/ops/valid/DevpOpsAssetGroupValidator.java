package net.aicoder.maintenance.business.deploy.ops.valid;


import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.business.ops.dto.DevpOpsAssetGroupAddDto;
import net.aicoder.devp.business.ops.dto.DevpOpsAssetGroupEditDto;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class DevpOpsAssetGroupValidator implements Validator {

	/**
	 * 判断支持的JavaBean类型
	 * @param aClass
	 * @return
	 */
	@Override
	public boolean supports(Class<?> aClass) {
		if(DevpOpsAssetGroupAddDto.class.equals(aClass))
			return true;
		if(DevpOpsAssetGroupEditDto.class.equals(aClass))
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
	    if(obj instanceof DevpOpsAssetGroupAddDto){
            this.validateAddDto((DevpOpsAssetGroupAddDto)obj, errors);
        }
	}

	/**
     * 验证新增信息
     * @param devpOpsAssetGroup 资产分组
     * @param errors
     */
	public void validateAddDto(DevpOpsAssetGroupAddDto devpOpsAssetGroup, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if (null == devpOpsAssetGroup.getTid() ) {
			errors.rejectValue("tid", "EMPTY_TID", "租户编号不能为空");
		}
		if(StringUtils.isEmpty(devpOpsAssetGroup.getEtype())){
			errors.rejectValue("etype", "EMPTY_ETYPE", "元素类型不能为空");
		}
       
		if(StringUtils.isEmpty(devpOpsAssetGroup.getName())){
			errors.rejectValue("name", "EMPTY_NAME", "名称不能为空");
		}
       

		//验证长度
		if(StringUtils.length(devpOpsAssetGroup.getEtype()) > 255){
			errors.rejectValue("etype", null, "元素类型最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetGroup.getName()) > 255){
			errors.rejectValue("name", null, "名称最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetGroup.getCode()) > 255){
			errors.rejectValue("code", null, "代码最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetGroup.getAlias()) > 255){
			errors.rejectValue("alias", null, "别名最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetGroup.getDescription()) > 255){
			errors.rejectValue("description", null, "描述最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetGroup.getTypeCode()) > 255){
			errors.rejectValue("typeCode", null, "类型代码最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetGroup.getTypeName()) > 255){
			errors.rejectValue("typeName", null, "类型名称最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetGroup.getStereotype()) > 255){
			errors.rejectValue("stereotype", null, "构造型最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetGroup.getScope()) > 255){
			errors.rejectValue("scope", null, "访问控制范围最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetGroup.getVersion()) > 255){
			errors.rejectValue("version", null, "版本最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetGroup.getPhase()) > 255){
			errors.rejectValue("phase", null, "阶段最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetGroup.getStatus()) > 255){
			errors.rejectValue("status", null, "状态最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetGroup.getParasCode()) > 255){
			errors.rejectValue("parasCode", null, "参数定义标识最长255个字符");
		}
	}
}