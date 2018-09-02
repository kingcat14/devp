package net.aicoder.speedcloud.console.business.speedCloud.pipeline.exec.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.client.pipeline.exec.ExecNodeRibbon;
import net.aicoder.speedcloud.client.pipeline.exec.result.ExecNodePageResult;
import net.aicoder.speedcloud.client.pipeline.exec.result.ExecNodeResult;
import net.aicoder.speedcloud.business.pipeline.exec.dto.ExecNodeCondition;
import net.aicoder.speedcloud.business.pipeline.exec.dto.ExecNodeAddDto;
import net.aicoder.speedcloud.business.pipeline.exec.dto.ExecNodeEditDto;
import net.aicoder.speedcloud.business.pipeline.exec.vo.ExecNodeVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("execNodeRibbonService")
public class ExecNodeRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExecNodeRibbonService.class);


	@Autowired
	private ExecNodeRibbon execNodeRibbon;


	public ExecNodeVO add(ExecNodeAddDto addDto){
		ExecNodeResult result = execNodeRibbon.add(addDto);

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
		ExecNodeResult result = execNodeRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE.EXEC", result.getCode()+"", result.getMessage());
		}
	}
	public ExecNodeVO merge(Long id, ExecNodeEditDto editDto){
		ExecNodeResult result = execNodeRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE.EXEC", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public ExecNodeVO find(Long id){
		ExecNodeResult result = execNodeRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE.EXEC", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}

	public PageContent<ExecNodeVO> list(PageSearchRequest<ExecNodeCondition> pageSearchRequest) {
		ExecNodePageResult result = execNodeRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE.EXEC", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
}
