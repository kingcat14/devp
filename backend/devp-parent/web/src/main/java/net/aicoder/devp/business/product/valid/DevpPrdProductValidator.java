package net.aicoder.devp.business.product.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;

import net.aicoder.devp.business.product.domain.DevpPrdProduct;
import net.aicoder.devp.business.product.dto.DevpPrdProductAddDto;
import net.aicoder.devp.business.product.dto.DevpPrdProductEditDto;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class DevpPrdProductValidator implements Validator {

	/**
	 * 判断支持的JavaBean类型
	 * @param aClass
	 * @return
	 */
	@Override
	public boolean supports(Class<?> aClass) {
		if(DevpPrdProductAddDto.class.equals(aClass))
			return true;
		if(DevpPrdProductEditDto.class.equals(aClass))
			return true;
		if(PageSearchRequest.class.equals(aClass))
			return true;
		return DevpPrdProduct.class.equals(aClass);
	}

	/**
	 * 实现Validator中的validate接口
	 * @param obj
	 * @param errors
	 */
	@Override
	public void validate(Object obj, Errors errors) {
	    if(obj instanceof DevpPrdProduct){
            this.validateDevpPrdProduct((DevpPrdProduct)obj, errors);
        }
	}

	/**
     * 实现Validator中的validate接口
     * @param devpPrdProduct 产品定义
     * @param errors
     */
	public void validateDevpPrdProduct(DevpPrdProduct devpPrdProduct, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填
		if (null == devpPrdProduct.getTid() ) {
			errors.rejectValue(DevpPrdProduct.PROPERTY_TID, "EMPTY_"+DevpPrdProduct.PROPERTY_TID, "租户编号不能为空");
		}
		if(StringUtils.isEmpty(devpPrdProduct.getCode())){
			errors.rejectValue(DevpPrdProduct.PROPERTY_CODE, "EMPTY_"+DevpPrdProduct.PROPERTY_CODE, "产品代码不能为空");
		}
		if(StringUtils.isEmpty(devpPrdProduct.getName())){
			errors.rejectValue(DevpPrdProduct.PROPERTY_NAME, "EMPTY_"+DevpPrdProduct.PROPERTY_NAME, "产品名称不能为空");
		}

		//验证长度
		if(StringUtils.length(devpPrdProduct.getCode()) > 255){
			errors.rejectValue(DevpPrdProduct.PROPERTY_CODE,null,"产品代码最长255个字符");
		}
		if(StringUtils.length(devpPrdProduct.getName()) > 255){
			errors.rejectValue(DevpPrdProduct.PROPERTY_NAME,null,"产品名称最长255个字符");
		}
		if(StringUtils.length(devpPrdProduct.getAlias()) > 255){
			errors.rejectValue(DevpPrdProduct.PROPERTY_ALIAS,null,"产品别名最长255个字符");
		}
		if(StringUtils.length(devpPrdProduct.getDescription()) > 255){
			errors.rejectValue(DevpPrdProduct.PROPERTY_DESCRIPTION,null,"产品描述最长255个字符");
		}
		if(StringUtils.length(devpPrdProduct.getType()) > 255){
			errors.rejectValue(DevpPrdProduct.PROPERTY_TYPE,null,"产品类型最长255个字符");
		}
		if(StringUtils.length(devpPrdProduct.getStereotype()) > 255){
			errors.rejectValue(DevpPrdProduct.PROPERTY_STEREOTYPE,null,"构造型最长255个字符");
		}
		if(StringUtils.length(devpPrdProduct.getScope()) > 255){
			errors.rejectValue(DevpPrdProduct.PROPERTY_SCOPE,null,"范围最长255个字符");
		}
		if(StringUtils.length(devpPrdProduct.getPrdDept()) > 255){
			errors.rejectValue(DevpPrdProduct.PROPERTY_PRD_DEPT,null,"所属部门最长255个字符");
		}
		if(StringUtils.length(devpPrdProduct.getPrdOwner()) > 255){
			errors.rejectValue(DevpPrdProduct.PROPERTY_PRD_OWNER,null,"产品负责人最长255个字符");
		}
		if(StringUtils.length(devpPrdProduct.getDevManager()) > 255){
			errors.rejectValue(DevpPrdProduct.PROPERTY_DEV_MANAGER,null,"开发负责人最长255个字符");
		}
		if(StringUtils.length(devpPrdProduct.getOpsManager()) > 255){
			errors.rejectValue(DevpPrdProduct.PROPERTY_OPS_MANAGER,null,"维护负责人最长255个字符");
		}
		if(StringUtils.length(devpPrdProduct.getBizLine()) > 255){
			errors.rejectValue(DevpPrdProduct.PROPERTY_BIZ_LINE,null,"业务线最长255个字符");
		}
		if(StringUtils.length(devpPrdProduct.getBizManager()) > 255){
			errors.rejectValue(DevpPrdProduct.PROPERTY_BIZ_MANAGER,null,"业务代表最长255个字符");
		}
		if(StringUtils.length(devpPrdProduct.getMajorCust()) > 255){
			errors.rejectValue(DevpPrdProduct.PROPERTY_MAJOR_CUST,null,"主要客户最长255个字符");
		}
		if(StringUtils.length(devpPrdProduct.getCustManager()) > 255){
			errors.rejectValue(DevpPrdProduct.PROPERTY_CUST_MANAGER,null,"客户代表最长255个字符");
		}
		if(StringUtils.length(devpPrdProduct.getCustUsage()) > 255){
			errors.rejectValue(DevpPrdProduct.PROPERTY_CUST_USAGE,null,"客户使用情况最长255个字符");
		}
		if(StringUtils.length(devpPrdProduct.getAcquisitionMode()) > 255){
			errors.rejectValue(DevpPrdProduct.PROPERTY_ACQUISITION_MODE,null,"获取方式最长255个字符");
		}
		if(StringUtils.length(devpPrdProduct.getAcquisitionDesc()) > 255){
			errors.rejectValue(DevpPrdProduct.PROPERTY_ACQUISITION_DESC,null,"获取方式说明最长255个字符");
		}
		if(StringUtils.length(devpPrdProduct.getVersion()) > 255){
			errors.rejectValue(DevpPrdProduct.PROPERTY_VERSION,null,"版本最长255个字符");
		}
		if(StringUtils.length(devpPrdProduct.getPhase()) > 255){
			errors.rejectValue(DevpPrdProduct.PROPERTY_PHASE,null,"阶段最长255个字符");
		}
		if(StringUtils.length(devpPrdProduct.getStatus()) > 255){
			errors.rejectValue(DevpPrdProduct.PROPERTY_STATUS,null,"状态最长255个字符");
		}
		if(StringUtils.length(devpPrdProduct.getNotes()) > 255){
			errors.rejectValue(DevpPrdProduct.PROPERTY_NOTES,null,"备注最长255个字符");
		}
		if(StringUtils.length(devpPrdProduct.getCreateUcode()) > 255){
			errors.rejectValue(DevpPrdProduct.PROPERTY_CREATE_UCODE,null,"创建用户代码最长255个字符");
		}
		if(StringUtils.length(devpPrdProduct.getCmodifyUcode()) > 255){
			errors.rejectValue(DevpPrdProduct.PROPERTY_CMODIFY_UCODE,null,"修改用户代码最长255个字符");
		}
	}
}