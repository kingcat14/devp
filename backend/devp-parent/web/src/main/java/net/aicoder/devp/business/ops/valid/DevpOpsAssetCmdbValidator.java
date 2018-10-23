package net.aicoder.devp.business.ops.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.business.ops.dto.DevpOpsAssetCmdbAddDto;
import net.aicoder.devp.business.ops.dto.DevpOpsAssetCmdbEditDto;
import net.aicoder.devp.business.ops.dto.DevpOpsAssetCmdbCondition;
import net.aicoder.devp.business.ops.domain.DevpOpsAssetCmdb;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class DevpOpsAssetCmdbValidator implements Validator {

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
	    if(obj instanceof DevpOpsAssetCmdbAddDto){
            this.validateDevpOpsAssetCmdbAddDto((DevpOpsAssetCmdbAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj, errors);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<DevpOpsAssetCmdbCondition> search, Errors errors){
        if(search.getSearchCondition() == null || search.getSearchCondition().getTid() == null){
			errors.rejectValue("NOT TENANT ID", "未正确设置租户ID");
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param devpOpsAssetCmdb IT资产配置数据库
     * @param errors
     */
	public void validateDevpOpsAssetCmdbAddDto(DevpOpsAssetCmdbAddDto devpOpsAssetCmdb, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(devpOpsAssetCmdb.getEtype()) > 255){
			errors.rejectValue(DevpOpsAssetCmdb.PROPERTY_ETYPE,null,"etype最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getName()) > 255){
			errors.rejectValue(DevpOpsAssetCmdb.PROPERTY_NAME,null,"name最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getCode()) > 255){
			errors.rejectValue(DevpOpsAssetCmdb.PROPERTY_CODE,null,"code最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getAlias()) > 255){
			errors.rejectValue(DevpOpsAssetCmdb.PROPERTY_ALIAS,null,"alias最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getDescription()) > 255){
			errors.rejectValue(DevpOpsAssetCmdb.PROPERTY_DESCRIPTION,null,"description最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getTypeCode()) > 255){
			errors.rejectValue(DevpOpsAssetCmdb.PROPERTY_TYPE_CODE,null,"type_code最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getTypeName()) > 255){
			errors.rejectValue(DevpOpsAssetCmdb.PROPERTY_TYPE_NAME,null,"type_name最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getStereotype()) > 255){
			errors.rejectValue(DevpOpsAssetCmdb.PROPERTY_STEREOTYPE,null,"stereotype最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getScope()) > 255){
			errors.rejectValue(DevpOpsAssetCmdb.PROPERTY_SCOPE,null,"scope最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getHardwareModel()) > 255){
			errors.rejectValue(DevpOpsAssetCmdb.PROPERTY_HARDWARE_MODEL,null,"hardware_model最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getSoftwareModel()) > 255){
			errors.rejectValue(DevpOpsAssetCmdb.PROPERTY_SOFTWARE_MODEL,null,"software_model最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getVersion()) > 255){
			errors.rejectValue(DevpOpsAssetCmdb.PROPERTY_VERSION,null,"version最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getStatus()) > 255){
			errors.rejectValue(DevpOpsAssetCmdb.PROPERTY_STATUS,null,"status最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getAssetProject()) > 255){
			errors.rejectValue(DevpOpsAssetCmdb.PROPERTY_ASSET_PROJECT,null,"asset_project最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getAssetArea()) > 255){
			errors.rejectValue(DevpOpsAssetCmdb.PROPERTY_ASSET_AREA,null,"asset_area最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getAssetLocation()) > 255){
			errors.rejectValue(DevpOpsAssetCmdb.PROPERTY_ASSET_LOCATION,null,"asset_location最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getIntAccessAddr()) > 255){
			errors.rejectValue(DevpOpsAssetCmdb.PROPERTY_INT_ACCESS_ADDR,null,"int_access_addr最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getExtAccessAddr()) > 255){
			errors.rejectValue(DevpOpsAssetCmdb.PROPERTY_EXT_ACCESS_ADDR,null,"ext_access_addr最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getAcquisitionMode()) > 255){
			errors.rejectValue(DevpOpsAssetCmdb.PROPERTY_ACQUISITION_MODE,null,"acquisition_mode最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getAcquisitionDesc()) > 255){
			errors.rejectValue(DevpOpsAssetCmdb.PROPERTY_ACQUISITION_DESC,null,"acquisition_desc最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getAssetDept()) > 255){
			errors.rejectValue(DevpOpsAssetCmdb.PROPERTY_ASSET_DEPT,null,"asset_dept最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getAssetManager()) > 255){
			errors.rejectValue(DevpOpsAssetCmdb.PROPERTY_ASSET_MANAGER,null,"asset_manager最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getUseDept()) > 255){
			errors.rejectValue(DevpOpsAssetCmdb.PROPERTY_USE_DEPT,null,"use_dept最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getUseManager()) > 255){
			errors.rejectValue(DevpOpsAssetCmdb.PROPERTY_USE_MANAGER,null,"use_manager最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getOpsDept()) > 255){
			errors.rejectValue(DevpOpsAssetCmdb.PROPERTY_OPS_DEPT,null,"ops_dept最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getOpsManager()) > 255){
			errors.rejectValue(DevpOpsAssetCmdb.PROPERTY_OPS_MANAGER,null,"ops_manager最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getBizLine()) > 255){
			errors.rejectValue(DevpOpsAssetCmdb.PROPERTY_BIZ_LINE,null,"biz_line最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getBizManager()) > 255){
			errors.rejectValue(DevpOpsAssetCmdb.PROPERTY_BIZ_MANAGER,null,"biz_manager最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getMajorCust()) > 255){
			errors.rejectValue(DevpOpsAssetCmdb.PROPERTY_MAJOR_CUST,null,"major_cust最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getCustManager()) > 255){
			errors.rejectValue(DevpOpsAssetCmdb.PROPERTY_CUST_MANAGER,null,"cust_manager最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getCustUsage()) > 255){
			errors.rejectValue(DevpOpsAssetCmdb.PROPERTY_CUST_USAGE,null,"cust_usage最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getNotes()) > 255){
			errors.rejectValue(DevpOpsAssetCmdb.PROPERTY_NOTES,null,"notes最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getParasCode()) > 255){
			errors.rejectValue(DevpOpsAssetCmdb.PROPERTY_PARAS_CODE,null,"paras_code最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getCreateUcode()) > 255){
			errors.rejectValue(DevpOpsAssetCmdb.PROPERTY_CREATE_UCODE,null,"create_ucode最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getCreateUname()) > 255){
			errors.rejectValue(DevpOpsAssetCmdb.PROPERTY_CREATE_UNAME,null,"create_uname最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getModifyUcode()) > 255){
			errors.rejectValue(DevpOpsAssetCmdb.PROPERTY_MODIFY_UCODE,null,"modify_ucode最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getModifyUname()) > 255){
			errors.rejectValue(DevpOpsAssetCmdb.PROPERTY_MODIFY_UNAME,null,"modify_uname最长255个字符");
		}
		if(StringUtils.length(devpOpsAssetCmdb.getAcquisitionProvider()) > 255){
			errors.rejectValue(DevpOpsAssetCmdb.PROPERTY_ACQUISITION_PROVIDER,null,"acquisition_provider最长255个字符");
		}
	}
}