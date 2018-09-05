package net.aicoder.speedcloud.apapter.yunkang;

import net.aicoder.speedcloud.apapter.yunkang.client.Result;
import net.aicoder.speedcloud.apapter.yunkang.client.YunkangClient;
import net.aicoder.speedcloud.apapter.yunkang.client.dto.CreateJobAction;
import net.aicoder.speedcloud.business.pipeline.command.domain.PipelineJobCommand;
import net.aicoder.speedcloud.business.pipeline.command.service.PipelineJobCommandService;
import net.aicoder.speedcloud.business.pipeline.task.domain.PipelineTask;
import net.aicoder.speedcloud.business.pipeline.task.domain.PipelineTaskAction;
import net.aicoder.speedcloud.business.pipeline.task.domain.PipelineTaskParam;
import net.aicoder.speedcloud.business.pipeline.task.service.PipelineTaskActionService;
import net.aicoder.speedcloud.business.pipeline.task.service.PipelineTaskParamService;
import net.aicoder.speedcloud.business.pipeline.task.service.PipelineTaskService;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 执行操作(创建Job等等)
 */
@Service
public class CommandRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommandRunner.class);

    @Autowired
    private PipelineTaskService pipelineTaskService;

    @Autowired
    private PipelineTaskParamService pipelineTaskParamService;

    @Autowired
    private PipelineTaskActionService pipelineTaskActionService;

    @Autowired
    private YunkangClient yunkangClient;

    @Autowired
    private PipelineJobCommandService pipelineJobCommandService;

    @Scheduled(cron = "0 * * * * *")
    public void schedual(){

        List<PipelineJobCommand> commandList = pipelineJobCommandService.findWaitJob(1L);

        for(PipelineJobCommand command : commandList){

            if(CollectionUtils.isEmpty(commandList)){
                return;
            }
            try {
                run(command);
            }catch (Exception e){
                LOGGER.error("ERROR IN RUN COMMAND:{}", command);
                LOGGER.error("ERROR IN RUN COMMAND", e);
            }
            pipelineJobCommandService.merge(command);
        }

    }

    public void run(PipelineJobCommand pipelineJobCommand){

        if("CREATE".equals(pipelineJobCommand.getType())){
            createJob(pipelineJobCommand);
        }
        if("UPDATE".equals(pipelineJobCommand.getType())){

        }
        if("DELETE".equals(pipelineJobCommand.getType())){
            deleteJob(pipelineJobCommand);
        }
    }


    /**
     * 在云康的发布平台创建一个JOB
     * @param command
     */
    public void createJob(PipelineJobCommand command){

        CreateJobAction createJobAction = buildCreateJobAction(command.getTask());

        Result result = yunkangClient.create(createJobAction);
//
        command.setResult(result.getFlag()+":"+result.getErrorMsg());
        command.setStatus("FINISH");

    }

    public void updateJob(PipelineJobCommand command){

        CreateJobAction createJobAction = buildCreateJobAction(command.getTask());

        Result result = yunkangClient.create(createJobAction);

        command.setResult(result.getFlag()+":"+result.getErrorMsg());
        command.setStatus("FINISH");

    }

    public void deleteJob(PipelineJobCommand command){

        Result result = yunkangClient.delete(command.getTask()+"");

        command.setResult(result.getFlag()+":"+result.getErrorMsg());
        command.setStatus("FINISH");

    }


    private CreateJobAction buildCreateJobAction(Long taskId){
        PipelineTask task = pipelineTaskService.find(taskId);

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
        createJobAction.setStringParms(stringParmaList);
        return createJobAction;
    }



}
