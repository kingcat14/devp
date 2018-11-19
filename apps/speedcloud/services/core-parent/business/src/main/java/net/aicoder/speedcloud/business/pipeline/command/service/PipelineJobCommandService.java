package net.aicoder.speedcloud.business.pipeline.command.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.pipeline.command.dao.PipelineJobCommandDao;
import net.aicoder.speedcloud.business.pipeline.command.dao.PipelineJobCommandSpecification;
import net.aicoder.speedcloud.business.pipeline.command.domain.PipelineJobCommand;
import net.aicoder.speedcloud.business.pipeline.command.dto.PipelineJobCommandCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("pipelineJobCommandService")
public class PipelineJobCommandService  extends GenericCrudService<PipelineJobCommand, Long, PipelineJobCommandCondition, PipelineJobCommandDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineJobCommandService.class);

	/**
	 * 查询某租户没处理的任务
	 * @param tid
	 * @return
	 */
	public List<PipelineJobCommand> findWaitJob(Long tid){
		return dao.findByTidAndStatus(tid, "WAIT");
	}


	@Override
	public Specification<PipelineJobCommand> getSpecification(PipelineJobCommandCondition condition) {
		return new PipelineJobCommandSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, PipelineJobCommand.PROPERTY_TASK);
		return sort;
	}
}