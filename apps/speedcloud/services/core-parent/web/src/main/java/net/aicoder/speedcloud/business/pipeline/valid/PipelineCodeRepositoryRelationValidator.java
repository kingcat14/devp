package net.aicoder.speedcloud.business.pipeline.valid;

import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineCodeRepositoryRelationAddDto;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineCodeRepositoryRelationCondition;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class PipelineCodeRepositoryRelationValidator implements Validator {

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
	    if(obj instanceof PipelineCodeRepositoryRelationAddDto){
            this.validatePipelineCodeRepositoryRelationAddDto((PipelineCodeRepositoryRelationAddDto)obj, errors);
        }
        if(obj instanceof PageSearchRequest){
            this.validateSearchDto((PageSearchRequest)obj);
        }
	}
	
    public void validateSearchDto(PageSearchRequest<PipelineCodeRepositoryRelationCondition> search){
        if(search.getSearchCondition() == null){
            search.setSearchCondition(new PipelineCodeRepositoryRelationCondition());
        }
    }

	/**
     * 实现Validator中的validate接口
     * @param pipelineCodeRepositoryRelation 流水线代码库关联
     * @param errors
     */
	public void validatePipelineCodeRepositoryRelationAddDto(PipelineCodeRepositoryRelationAddDto pipelineCodeRepositoryRelation, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
	}
}