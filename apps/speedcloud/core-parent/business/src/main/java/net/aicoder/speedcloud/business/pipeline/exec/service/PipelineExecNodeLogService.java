package net.aicoder.speedcloud.business.pipeline.exec.service;


import com.devin.ciserver.model.job.response.ResultBuildInfo;
import net.aicoder.speedcloud.apapter.yunkang.client.YunkangClient;
import net.aicoder.speedcloud.business.pipeline.exec.domain.PipelineExecNode;
import net.aicoder.speedcloud.business.pipeline.exec.domain.PipelineExecNodeLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("pipelineExecNodeLogService")
public class PipelineExecNodeLogService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineExecNodeLogService.class);

	@Autowired
	private YunkangClient yunkangClient;
	@Autowired
	private PipelineExecNodeService pipelineExecNodeService;

	public PipelineExecNodeLog find(Long id){

		PipelineExecNode node = pipelineExecNodeService.find(id);

		ResultBuildInfo buildInfo = yunkangClient.buildInfo(node.getRelationObjId()+"", node.getResultRelationObj());

		PipelineExecNodeLog pipelineExecNodeLog = new PipelineExecNodeLog();
		pipelineExecNodeLog.setId(id+"");


		if("SUCCESS".equals(buildInfo.getFlag())) {
			pipelineExecNodeLog.setLog(buildInfo.getData().getConsoleOutputText());
		}

		return pipelineExecNodeLog;
	}

}