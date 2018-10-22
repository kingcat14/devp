package net.aicoder.speedcloud.console.business.asset.asset.info.valid;


import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.asset.business.asset.info.dto.AssetCmdbAddDto;
import net.aicoder.speedcloud.asset.business.asset.info.dto.AssetCmdbEditDto;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class AssetCmdbValidator implements Validator {

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
	    if(obj instanceof AssetCmdbAddDto){
            this.validateAddDto((AssetCmdbAddDto)obj, errors);
        }
	}

	/**
     * 验证新增信息
     * @param assetCmdb IT资产
     * @param errors
     */
		public void validateAddDto(AssetCmdbAddDto assetCmdb, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(assetCmdb.getBarcode()) > 255){
			errors.rejectValue("barcode", null, "资产条码最长255个字符");
		}
		if(StringUtils.length(assetCmdb.getName()) > 255){
			errors.rejectValue("name", null, "资产名称最长255个字符");
		}
		if(StringUtils.length(assetCmdb.getCode()) > 255){
			errors.rejectValue("code", null, "资产代码最长255个字符");
		}
		if(StringUtils.length(assetCmdb.getAlias()) > 255){
			errors.rejectValue("alias", null, "资产别名最长255个字符");
		}
		if(StringUtils.length(assetCmdb.getUnit()) > 255){
			errors.rejectValue("unit", null, "计量单位最长255个字符");
		}
		if(StringUtils.length(assetCmdb.getDescription()) > 255){
			errors.rejectValue("description", null, "描述最长255个字符");
		}
		if(StringUtils.length(assetCmdb.getStatus()) > 255){
			errors.rejectValue("status", null, "状态最长255个字符");
		}
		if(StringUtils.length(assetCmdb.getAssetArea()) > 255){
			errors.rejectValue("assetArea", null, "所在区域最长255个字符");
		}
		if(StringUtils.length(assetCmdb.getAssetLocation()) > 255){
			errors.rejectValue("assetLocation", null, "所在地址最长255个字符");
		}
		if(StringUtils.length(assetCmdb.getAcquisitionMode()) > 255){
			errors.rejectValue("acquisitionMode", null, "获取模式最长255个字符");
		}
		if(StringUtils.length(assetCmdb.getAcquisitionDesc()) > 255){
			errors.rejectValue("acquisitionDesc", null, "获取描述最长255个字符");
		}
		if(StringUtils.length(assetCmdb.getNotes()) > 255){
			errors.rejectValue("notes", null, "notes最长255个字符");
		}
	}
}