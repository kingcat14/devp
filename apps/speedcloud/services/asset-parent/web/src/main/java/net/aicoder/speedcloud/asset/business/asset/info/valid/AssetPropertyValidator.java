package net.aicoder.speedcloud.asset.business.asset.info.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.asset.business.asset.info.domain.AssetProperty;
import net.aicoder.speedcloud.asset.business.asset.info.dto.AssetPropertyAddDto;
import net.aicoder.speedcloud.asset.business.asset.info.dto.AssetPropertyCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class AssetPropertyValidator implements Validator {

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
	    if(obj instanceof AssetPropertyAddDto){
            this.validateAssetPropertyAddDto((AssetPropertyAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<AssetPropertyCondition> search){
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new AssetPropertyCondition());
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param assetProperty 资产属性
     * @param errors
     */
	public void validateAssetPropertyAddDto(AssetPropertyAddDto assetProperty, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(assetProperty.getName()) > 255){
			errors.rejectValue(AssetProperty.PROPERTY_NAME,null,"属性名称最长255个字符");
		}
		if(StringUtils.length(assetProperty.getValue()) > 255){
			errors.rejectValue(AssetProperty.PROPERTY_VALUE,null,"属性值最长255个字符");
		}
	}
}