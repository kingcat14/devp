package net.aicoder.speedcloud.business.pipeline.exec.executor;

import net.aicoder.speedcloud.business.pipeline.exec.domain.PipelineExecNode;
import net.aicoder.speedcloud.business.pipeline.exec.service.ExecNodeAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;

@Component
public class NodeExecutorCenter {

    private static final Logger LOGGER = LoggerFactory.getLogger(NodeExecutorCenter.class);

    private HashMap<String, NodeExecutor> executorMap = new HashMap<>();

    @Autowired
    private ExecNodeAction execNodeAction;

    public void execute(PipelineExecNode node){

        NodeExecutor nodeBuilder = executorMap.get(node.getNodeType());

        if(nodeBuilder != null){
            node.setScheduleTime(new Date());
            nodeBuilder.execute(node);
        }else{
            LOGGER.error("try start unknown node type: {}", node.getNodeType());
            LOGGER.error(node.toString());
        }
    }

    public void finish(PipelineExecNode node){


        execNodeAction.finishNode(node);

//        NodeExecutor nodeBuilder = executorMap.get(node.getNodeType());
//
//        if(nodeBuilder != null){
//            nodeBuilder.execute(node);
//        }else{
//            LOGGER.error("try start unknown node type: {}", node.getNodeType());
//            LOGGER.error(node.toString());
//        }
    }

    public void register(String type, NodeExecutor nodeExecutor){
        this.executorMap.put(type, nodeExecutor);
    }

}
