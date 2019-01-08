package net.aicoder.speedcloud.business.pipeline.exec.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.pipeline.constant.ExecNodeType;
import net.aicoder.speedcloud.business.pipeline.constant.PipelineExecNodeStatus;
import net.aicoder.speedcloud.business.pipeline.exec.dao.PipelineExecNodeDao;
import net.aicoder.speedcloud.business.pipeline.exec.dao.PipelineExecNodeSpecification;
import net.aicoder.speedcloud.business.pipeline.exec.domain.PipelineExecNode;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecNodeCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("pipelineExecNodeService")
public class PipelineExecNodeService  extends GenericCrudService<PipelineExecNode, Long, PipelineExecNodeCondition, PipelineExecNodeDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineExecNodeService.class);


	public List<PipelineExecNode> findChildNode(Long parentNodeId){
		return dao.findByParentIdOrderByExecIndex(parentNodeId);
	}

	/**
	 * 查找下一个可执行的子节点
	 * @param parentNodeId
	 * @return
	 */
	public PipelineExecNode findNextWaitingChildNode(Long parentNodeId){

		Pageable pageable = new PageRequest(0 ,1);

		List<PipelineExecNode> nodeList = dao.findByParentIdAndStatusOrderByExecIndex(parentNodeId, PipelineExecNodeStatus.WAIT, pageable);

		PipelineExecNode result = null;

		if(CollectionUtils.isNotEmpty(nodeList)){
			result = nodeList.get(0);
		}

		return result;
	}

	/**
	 * 查询还没开始执行的实例
	 * @param tid
	 * @return
	 */
	public List<PipelineExecNode> findPreparedJob(Long tid){
		return dao.findByTidAndStatusAndNodeType(tid, PipelineExecNodeStatus.PREPARED, ExecNodeType.TASK);
	}

	/**
	 * 查询处于等待中的实例
	 * @param tid
	 * @return
	 */
	public List<PipelineExecNode> findWaitingJob(Long tid){
		return dao.findByTidAndStatus(tid, PipelineExecNodeStatus.WAIT);
	}

	@Override
	public Specification<PipelineExecNode> getSpecification(PipelineExecNodeCondition condition) {
		return new PipelineExecNodeSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, PipelineExecNode.PROPERTY_START_TIME);
		return sort;
	}
}