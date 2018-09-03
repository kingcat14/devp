package net.aicoder.speedcloud.apapter.yunkang;

import net.aicoder.speedcloud.apapter.yunkang.client.Result;
import net.aicoder.speedcloud.apapter.yunkang.client.YunkangClient;
import net.aicoder.speedcloud.business.pipeline.command.domain.PipelineJobCommand;
import net.aicoder.speedcloud.business.pipeline.task.domain.PipelineTask;
import net.aicoder.speedcloud.business.pipeline.task.domain.PipelineTaskAction;
import net.aicoder.speedcloud.business.pipeline.task.domain.PipelineTaskParam;
import net.aicoder.speedcloud.business.pipeline.task.service.PipelineTaskActionService;
import net.aicoder.speedcloud.business.pipeline.task.service.PipelineTaskParamService;
import net.aicoder.speedcloud.business.pipeline.task.service.PipelineTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 执行操作(创建Job等等)
 */
@Service
public class CommandRunner {

    @Autowired
    private PipelineTaskService pipelineTaskService;

    @Autowired
    private PipelineTaskParamService pipelineTaskParamService;

    @Autowired
    private PipelineTaskActionService pipelineTaskActionService;

    @Autowired
    private YunkangClient yunkangClient;

    public void run(PipelineJobCommand pipelineJobCommand){

        if("CREATE".equals(pipelineJobCommand.getType())){
            createJob(pipelineJobCommand);
        }
        if("UPDATE".equals(pipelineJobCommand.getType())){

        }
        if("DELETE".equals(pipelineJobCommand.getType())){

        }
    }


    /**
     * 在云康的发布平台创建一个JOB
     * @param command
     */
    public void createJob(PipelineJobCommand command){

        PipelineTask task = pipelineTaskService.find(command.getTask());

        List<PipelineTaskParam> pipelineTaskParamList = pipelineTaskParamService.findByTask(task.getId());
        List<PipelineTaskAction> pipelineTaskActionList = pipelineTaskActionService.findByTask(task.getId());


        List<String> shellCmdList = new ArrayList<>();
        for(PipelineTaskAction action : pipelineTaskActionList){
            shellCmdList.add(action.getContent());
        }

        List<StringParma> stringParmaList = new ArrayList<>();
        for(PipelineTaskParam param : pipelineTaskParamList){
            StringParma stringParm = new StringParma();
            stringParm.setName(param.getName());
            stringParm.setDefaultValue(param.getDefaultValue());
            stringParm.setDescription(param.getDescription());
            stringParmaList.add(stringParm);
        }


        CreateJobAction createJobAction = new CreateJobAction();

        createJobAction.setDesc(task.getDescription());
        createJobAction.setJobName(task.getId()+"");
        createJobAction.setShellCmd(shellCmdList);
        createJobAction.setStringParmas(stringParmaList);

        Result result = yunkangClient.create(createJobAction);

        command.setResult(result.getFlag());
        command.setStatus("FINISH");

    }

}
