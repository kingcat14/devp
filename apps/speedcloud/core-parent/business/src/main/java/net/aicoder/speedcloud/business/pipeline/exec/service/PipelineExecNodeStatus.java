package net.aicoder.speedcloud.business.pipeline.exec.service;

/**
 * 节点的状态
 */
public class PipelineExecNodeStatus {

    /**等待调度*/
    public static final String WAIT = "WAIT";
    /**已准备好运行*/
    public static final String PREPARED = "PREPARED";
    /**运行中*/
    public static final String RUNNING = "RUNNING";
    /**自行结束*/
    public static final String FINISH = "FINISH";
}
