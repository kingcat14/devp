package net.aicoder.speedcloud.console.business.speedcloud.pipeline.exec.service;

import com.yunkang.saas.common.framework.exception.BusinessException;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.pipeline.exec.dto.*;
import net.aicoder.speedcloud.business.pipeline.exec.vo.PipelineExecInstanceVO;
import net.aicoder.speedcloud.business.pipeline.exec.vo.PipelineExecNodeVO;
import net.aicoder.speedcloud.client.pipeline.exec.PipelineExecInstanceRibbon;
import net.aicoder.speedcloud.client.pipeline.exec.PipelineExecNodeRibbon;
import net.aicoder.speedcloud.client.pipeline.exec.result.PipelineExecInstancePageResult;
import net.aicoder.speedcloud.client.pipeline.exec.result.PipelineExecInstanceResult;
import net.aicoder.speedcloud.client.pipeline.exec.result.PipelineExecNodePageResult;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("pipelineExecInstanceRibbonService")
public class PipelineExecInstanceRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineExecInstanceRibbonService.class);

	@Autowired
	private PipelineExecInstanceRibbon pipelineExecInstanceRibbon;

	@Autowired
	private PipelineExecNodeRibbon pipelineExecNodeRibbon;

	/**
	 * 定制执行实例
	 * @param addDto
	 * @return
	 */
	public PipelineExecInstanceVO custom(PipelineExecNodeCustomAddDto addDto){
		PipelineExecInstanceResult result = pipelineExecInstanceRibbon.custom(addDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEED_CLOUD", "PIPELINE.EXEC", result.getCode()+"", result.getMessage());
		}
		return result.getData();

	}

	/**
	 * 执行任务
	 * @param addDto
	 * @return
	 */
	public PipelineExecInstanceVO task(PipelineExecNodeCustomAddDto addDto){
		PipelineExecInstanceResult result = pipelineExecInstanceRibbon.task(addDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEED_CLOUD", "PIPELINE.EXEC", result.getCode()+"", result.getMessage());
		}
		return result.getData();

	}

	/**
	 * 流水线
	 * @param addDto
	 * @return
	 */
	public PipelineExecInstanceVO pipeline(PipelineExecNodeCustomAddDto addDto){
		PipelineExecInstanceResult result = pipelineExecInstanceRibbon.pipeline(addDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEED_CLOUD", "PIPELINE.EXEC", result.getCode()+"", result.getMessage());
		}
		return result.getData();

	}


	public PipelineExecInstanceVO add(PipelineExecInstanceAddDto addDto){
		PipelineExecInstanceResult result = pipelineExecInstanceRibbon.add(addDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEED_CLOUD", "PIPELINE.EXEC", result.getCode()+"", result.getMessage());
		}
		return result.getData();
	
	}

	public void delete(Long id){
		if(null == id){
			LOGGER.warn("try delete T by empty id. Code need check");
			return ;
		}
		LOGGER.debug("delete t:{}", id);
		PipelineExecInstanceResult result = pipelineExecInstanceRibbon.delete(id);
		if(!result.isSuccess()){
			throw new BusinessException("SPEED_CLOUD", "PIPELINE.EXEC", result.getCode()+"", result.getMessage());
		}
	}
	public PipelineExecInstanceVO merge(Long id, PipelineExecInstanceEditDto editDto){
		PipelineExecInstanceResult result = pipelineExecInstanceRibbon.update(id, editDto);

		if(!result.isSuccess()){
			throw new BusinessException("SPEED_CLOUD", "PIPELINE.EXEC", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}
	public PipelineExecInstanceVO find(Long id){

		//得到instance
		PipelineExecInstanceResult result = pipelineExecInstanceRibbon.get(id);

		if(!result.isSuccess()){
			throw new BusinessException("SPEED_CLOUD", "PIPELINE.EXEC", result.getCode()+"", result.getMessage());
		}

		PipelineExecInstanceVO instanceVO = result.getData();

		//得到instance包含的node
		PipelineExecNodeCondition condition = new PipelineExecNodeCondition();
		condition.setExec(id);
		PageSearchRequest<PipelineExecNodeCondition> pageSearchRequest = new PageSearchRequest<>();
		pageSearchRequest.setPage(0);
		pageSearchRequest.setLimit(Integer.MAX_VALUE);
		pageSearchRequest.setSearchCondition(condition);


		PipelineExecNodePageResult nodePageResult = pipelineExecNodeRibbon.list(pageSearchRequest);
		List<PipelineExecNodeVO> nodeList = nodePageResult.getData().getContent();

		instanceVO.setNodeList(nodeList);

		return instanceVO;
	}

	public PageContent<PipelineExecInstanceVO> list(PageSearchRequest<PipelineExecInstanceCondition> pageSearchRequest) {
		PipelineExecInstancePageResult result = pipelineExecInstanceRibbon.list(pageSearchRequest);

		if(!result.isSuccess()){
			throw new BusinessException("SPEEDCLOUD", "PIPELINE.EXEC", result.getCode()+"", result.getMessage());
		}

		return result.getData();
	}


	/**
	 * 得到最近一次执行情况
	 * @param taskId
	 * @return
	 */
	public PipelineExecInstanceVO getLastExec(Long taskId){

		PageSearchRequest<PipelineExecInstanceCondition> pageSearchRequest = new PageSearchRequest<>();
		PipelineExecInstanceCondition condition = new PipelineExecInstanceCondition();
		condition.setExecuteTargetId(taskId);
		condition.setExecuteTargetType("TASK");
		pageSearchRequest.setSearchCondition(condition);
		pageSearchRequest.setLimit(1);
		PipelineExecInstancePageResult execResult = pipelineExecInstanceRibbon.list(pageSearchRequest);
		List<PipelineExecInstanceVO> voList = execResult.getData().getContent();

		PipelineExecInstanceVO result = null;
		if(CollectionUtils.size(voList) > 0){
			result = voList.get(0);
		}
		return result;
	}
}
