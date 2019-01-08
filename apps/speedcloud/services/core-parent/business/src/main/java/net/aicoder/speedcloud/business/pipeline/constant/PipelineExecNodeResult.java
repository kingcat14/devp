package net.aicoder.speedcloud.business.pipeline.constant;

import org.apache.commons.lang3.StringUtils;

/**
 * 节点的运行结果
 */
public class PipelineExecNodeResult {

    /**成功*/
    public static final String SUCCESS = "SUCCESS";
    /**失败*/
    public static final String FAILURE = "FAILURE";

    public static boolean success(String result){
        return StringUtils.equals(SUCCESS, result);
    }
}
