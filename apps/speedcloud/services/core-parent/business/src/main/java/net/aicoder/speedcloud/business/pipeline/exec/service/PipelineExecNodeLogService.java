package net.aicoder.speedcloud.business.pipeline.exec.service;


import com.devin.ciserver.model.job.response.ResultBuildInfo;
import lombok.extern.slf4j.Slf4j;
import net.aicoder.speedcloud.apapter.yunkang.client.ConvertUtil;
import net.aicoder.speedcloud.apapter.yunkang.client.YunkangClient;
import net.aicoder.speedcloud.business.pipeline.exec.dao.PipelineExecNodeLogDao;
import net.aicoder.speedcloud.business.pipeline.exec.domain.PipelineExecNode;
import net.aicoder.speedcloud.business.pipeline.exec.domain.PipelineExecNodeLog;
import net.aicoder.speedcloud.business.pipeline.exec.vo.PipelineBuildLogVO;
import net.aicoder.speedcloud.business.pipeline.exec.vo.PipelineExecNodeLogVO;
import net.aicoder.speedcloud.business.pipeline.exec.vo.StageConsoleOutputVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("pipelineExecNodeLogService")
@Slf4j
public class PipelineExecNodeLogService  {

	@Autowired
	private YunkangClient yunkangClient;
	@Autowired
	private PipelineExecNodeService pipelineExecNodeService;

	@Autowired
	private PipelineExecNodeLogDao pipelineExecNodeLogDao;

	public PipelineExecNodeLog find(Long id){

		PipelineExecNodeLog pipelineExecNodeLog = new PipelineExecNodeLog();
		pipelineExecNodeLog.setId(id+"");

		PipelineExecNode node = pipelineExecNodeService.find(id);

		pipelineExecNodeLog.setStatus(node.getStatus());

		if(StringUtils.isNotEmpty(node.getResultRelationObj())){
			ResultBuildInfo buildInfo = yunkangClient.buildInfo(node.getRelationObjId()+"", node.getResultRelationObj());
//			pipelineExecNodeLog.setLog(buildInfo.getData().getConsoleOutputHtml());
            if(buildInfo.getData()!= null) {
                pipelineExecNodeLog.setLog(buildInfo.getData().getConsoleOutputText());
            }
		}else{
			pipelineExecNodeLog.setLog(node.getResultMessage());
		}

		return pipelineExecNodeLog;
	}

	public PipelineExecNodeLogVO findPipeline(Long id){

		PipelineExecNodeLogVO result = new PipelineExecNodeLogVO();

		PipelineExecNode node = pipelineExecNodeService.find(id);


		PipelineExecNodeLog nodeLog = pipelineExecNodeLogDao.findOne(id+"");

		if(nodeLog != null){
			List<StageConsoleOutputVO> list =  ConvertUtil.consoleOutputConvert(nodeLog.getLog(), nodeLog.getResult());

			PipelineBuildLogVO buildInfo = new PipelineBuildLogVO();
			buildInfo.setPipelineConsoleOutputText(list);
			result.setLog(nodeLog.getLog());
		}
		else if(StringUtils.isNotEmpty(node.getResultRelationObj())){
			PipelineBuildLogVO buildInfo = yunkangClient.pipelineBuildInfo(node.getRelationObjId()+"", node.getResultRelationObj());
			if(buildInfo != null) {
				log.info("pipelineBuildInfo result :" + buildInfo.getResult());

				result.setPipelineBuildLog(buildInfo);
				result.setLog(buildInfo.getConsoleOutputText());

				node.setResult(buildInfo.getResult());
			}
			pipelineExecNodeService.merge(node);
		}
		result.setId(id+"");
		result.setStatus(node.getStatus());
		result.setResult(node.getResult());
		return result;
	}

}