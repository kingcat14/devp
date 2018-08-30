package com.kingzoo.kingcat.project.icode4.business.database.valid;

import com.kingzoo.kingcat.commons.framework.spring.data.PageSearchRequest;
import com.kingzoo.kingcat.project.icode4.business.database.controller.vo.DatabaseTableInfoAddRequest;
import com.kingzoo.kingcat.project.icode4.business.database.domain.DatabaseTableInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class DatabaseTableInfoValidator implements Validator {

	/**
	 * 判断支持的JavaBean类型
	 * @param aClass
	 * @return
	 */
	@Override
	public boolean supports(Class<?> aClass) {
		if(PageSearchRequest.class.equals(aClass))
			return true;
		if(DatabaseTableInfoAddRequest.class.equals(aClass))
            return true;
		return DatabaseTableInfo.class.equals(aClass);
	}

	/**
	 * 实现Validator中的validate接口
	 * @param obj
	 * @param errors
	 */
	@Override
	public void validate(Object obj, Errors errors) {
	    if(obj instanceof DatabaseTableInfo){
            this.validateDatabaseTableInfo((DatabaseTableInfo)obj, errors);
        }
	}

	/**
     * 实现Validator中的validate接口
     * @param databaseTableInfo 数据库表信息
     * @param errors
     */
	public void validateDatabaseTableInfo(DatabaseTableInfo databaseTableInfo, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
		if(StringUtils.length(databaseTableInfo.getConnectionId()) > 255){
			errors.rejectValue(DatabaseTableInfo.PROPERTY_CONNECTION_ID,null,"链接ID最长255个字符");
		}
		if(StringUtils.length(databaseTableInfo.getConnectionUrl()) > 255){
			errors.rejectValue(DatabaseTableInfo.PROPERTY_CONNECTION_URL,null,"链接url最长255个字符");
		}
		if(StringUtils.length(databaseTableInfo.getModuleName()) > 255){
			errors.rejectValue(DatabaseTableInfo.PROPERTY_MODULE_NAME,null,"模块名称最长255个字符");
		}
		if(StringUtils.length(databaseTableInfo.getTableName()) > 255){
			errors.rejectValue(DatabaseTableInfo.PROPERTY_TABLE_NAME,null,"表名称最长255个字符");
		}
		if(StringUtils.length(databaseTableInfo.getTableDisplayName()) > 255){
			errors.rejectValue(DatabaseTableInfo.PROPERTY_TABLE_DISPLAY_NAME,null,"表展现名称最长255个字符");
		}
		if(StringUtils.length(databaseTableInfo.getEntityName()) > 255){
			errors.rejectValue(DatabaseTableInfo.PROPERTY_ENTITY_NAME,null,"实体名称最长255个字符");
		}
		if(StringUtils.length(databaseTableInfo.getTableDesc()) > 255){
			errors.rejectValue(DatabaseTableInfo.PROPERTY_TABLE_DESC,null,"表描述最长255个字符");
		}
	}
}