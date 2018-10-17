package net.aicoder.speedcloud.business.pipeline.exec.executor;

import net.aicoder.speedcloud.business.pipeline.constant.ExecNodeType;
import net.aicoder.speedcloud.business.pipeline.exec.domain.PipelineExecNode;
import net.aicoder.speedcloud.business.pipeline.exec.service.PipelineExecNodeService;
import net.aicoder.speedcloud.business.pipeline.exec.service.PipelineExecNodeStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 流水线节点的执行逻辑
 */
@Component
public class PipelineNodeExecutor implements NodeExecutor {

    @Autowired()@Qualifier("pipelineExecNodeService")
    private PipelineExecNodeService execNodeService;

    @Autowired
    private NodeExecutorCenter nodeExecutorCenter;

    @Override
    public void execute(PipelineExecNode node){
        node.setStatus(PipelineExecNodeStatus.RUNNING);
        execNodeService.merge(node);

        PipelineExecNode nextWaitingNode = execNodeService.findNextWaitingChildNode(node.getId());
        if(nextWaitingNode != null ) {
            nodeExecutorCenter.execute(nextWaitingNode);
        }else{
            node.setResult("SUCCESS");
            nodeExecutorCenter.finish(node);
        }
    }

    @PostConstruct
    public void register() {
        nodeExecutorCenter.register(ExecNodeType.PIPELINE, this);
    }


}
