package net.aicoder.speedcloud.console.business.speedCloud.pipeline.exec.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.client.pipeline.exec.ExecRibbon;
import net.aicoder.speedcloud.client.pipeline.exec.result.ExecPageResult;
import net.aicoder.speedcloud.client.pipeline.exec.result.ExecResult;
import net.aicoder.speedcloud.business.pipeline.exec.dto.ExecCondition;
import net.aicoder.speedcloud.business.pipeline.exec.dto.ExecAddDto;
import net.aicoder.speedcloud.business.pipeline.exec.dto.ExecEditDto;
import net.aicoder.speedcloud.business.pipeline.exec.vo.ExecVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("execRibbonService")
public class ExecRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExecRibbonService.class);


	@Autowired
	private ExecRibbon execRibbon;


	public ExecVO add(ExecAddDto addDto){
		ExecResult result = execRibbon.add(addDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE.EXEC", result.getCode()+"", result.getMessage());
		}
		return result.getData();
	
	}

	public void delete(Long id){
		if(null == id){
			LOGGER.warn("try delete T by empty id. Code need check");
			return ;
		}
		LOGGER.debug("delete t:{}", id);
		ExecResult result = execRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE.EXEC", result.getCode()+"", result.getMessage());
		}
	}
	public ExecVO merge(Long id, ExecEditDto editDto){
		ExecResult result = execRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE.EXEC", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public ExecVO find(Long id){
		ExecResult result = execRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE.EXEC", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<ExecVO> list(PageSearchRequest<ExecCondition> pageSearchRequest) {
		ExecPageResult result = execRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE.EXEC", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
