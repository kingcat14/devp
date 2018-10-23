package net.aicoder.speedcloud.console.business.speedcloud.pipeline.valid;


import net.aicoder.speedcloud.business.pipeline.dto.PipelineCodeRepositoryRelationAddDto;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.stereotype.Service;

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
            this.validateAddDto((PipelineCodeRepositoryRelationAddDto)obj, errors);
        }
	}

	/**
     * 验证新增信息
     * @param pipelineCodeRepositoryRelation 流水线代码库关联
     * @param errors
     */
	public void validateAddDto(PipelineCodeRepositoryRelationAddDto pipelineCodeRepositoryRelation, Errors errors) {


		//把校验信息注册到Error的实现类里
		//验证必填

		//验证长度
	}
}