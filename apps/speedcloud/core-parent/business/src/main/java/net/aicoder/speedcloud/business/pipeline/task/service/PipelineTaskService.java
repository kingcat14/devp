package net.aicoder.speedcloud.business.pipeline.task.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.pipeline.command.domain.PipelineJobCommand;
import net.aicoder.speedcloud.business.pipeline.command.service.PipelineJobCommandService;
import net.aicoder.speedcloud.business.pipeline.task.dao.PipelineTaskDao;
import net.aicoder.speedcloud.business.pipeline.task.dao.PipelineTaskSpecification;
import net.aicoder.speedcloud.business.pipeline.task.domain.PipelineTask;
import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


@Service("pipelineTaskService")
public class PipelineTaskService  extends GenericCrudService<PipelineTask, Long, PipelineTaskCondition, PipelineTaskDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineTaskService.class);

	@Autowired
	private PipelineJobCommandService pipelineJobCommandService;

	@Transactional
	public void add(PipelineTask t){
		super.add(t);

		PipelineJobCommand pipelineJobCommand = new PipelineJobCommand();
		pipelineJobCommand.setStatus("WAIT");
		pipelineJobCommand.setCreateTime(new Date());
		pipelineJobCommand.setTask(t.getId());
		pipelineJobCommand.setType("CREATE");
		pipelineJobCommand.setTid(t.getTid());

		//创建一个指令
		pipelineJobCommandService.add(pipelineJobCommand);


	}

	@Override
	public Specification<PipelineTask> getSpecification(PipelineTaskCondition condition) {
		return new PipelineTaskSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, PipelineTask.PROPERTY_NAME);
		return sort;
	}
}