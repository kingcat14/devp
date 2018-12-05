package net.aicoder.speedcloud.console.business.icode.codegen.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
* 代码生成任务的值对象
*/
@Getter @Setter @ToString
public class CodeGenTaskVO {

    private String id;

    /**模型所在的组件ID*/
    private String modelComponentId;

    /**模型ID*/
    private String modelId;


    /**模板ID*/
    private String tplCodeId;


    /**文件路径*/
    private String destPath;


    /**目标组件, 为该组件生成代码*/
    private String targetComponentId;

    /**执行结果*/
    private String status;


}