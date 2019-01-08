package net.aicoder.speedcloud.business.pipeline.constant;

import org.apache.commons.lang3.StringUtils;

/**
 * 节点的状态
 */
public class PipelineExecNodeStatus {

    /**等待调度(比如还没排上队的stage node)*/
    public static final String WAIT = "WAIT";
    /**已准备好运行*/
    public static final String PREPARED = "PREPARED";
    /**运行中*/
    public static final String RUNNING = "RUNNING";
    /**自行结束*/
    public static final String FINISH = "FINISH";
    /**取消*/
    public static final String CANCEL = "CANCEL";

    /**
     * 是否已开始运行了（运行中或运行结束）
     * @param status
     * @return
     */
    public static boolean isStart(String status){
        return !(StringUtils.equals(WAIT, status) || StringUtils.equals(PREPARED, status) || StringUtils.isEmpty(status));
    }

    /**
     * 是否已结束 FINISH或者CANCEL
     * @param status
     * @return
     */
    public static boolean isEnd(String status){
        return StringUtils.equals(FINISH, status) || StringUtils.equals(FINISH, CANCEL);
    }
}
