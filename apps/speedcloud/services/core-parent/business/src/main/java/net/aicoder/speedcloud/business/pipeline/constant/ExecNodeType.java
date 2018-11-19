package net.aicoder.speedcloud.business.pipeline.constant;

import org.apache.commons.lang3.StringUtils;

public class ExecNodeType {

    public static final String PIPELINE = "PIPELINE";
    public static final String STAGE = "STAGE";
    public static final String TASK = "TASK";
    public static final String TASK_COMPILE = "COMPILE";
    public static final String TASK_DEPLOY = "DEPLOY";


    public static String getType(String type){

        if(StringUtils.equalsIgnoreCase(type, PIPELINE)){
            return PIPELINE;
        }

        if(StringUtils.equalsIgnoreCase(type, TASK)){
            return TASK;
        }

        if(StringUtils.equalsIgnoreCase(type, TASK_COMPILE)){
            return TASK_COMPILE;
        }

        if(StringUtils.equalsIgnoreCase(type, TASK_DEPLOY)){
            return TASK_DEPLOY;
        }

        return null;
    }

}
