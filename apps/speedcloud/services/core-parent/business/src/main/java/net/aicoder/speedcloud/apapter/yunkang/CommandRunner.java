package net.aicoder.speedcloud.apapter.yunkang;

import com.devin.ciserver.model.job.PipelineJob;
import com.devin.ciserver.model.job.PipelineStage;
import com.devin.ciserver.model.job.StringParm;
import com.yunkang.saas.common.framework.exception.BusinessException;
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
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Scheduled(cron = "*/20 * * * * *")
    @Transactional
    public void schedual(){

        List<PipelineJobCommand> commandList = pipelineJobCommandService.findWaitJob(1L);

        for(PipelineJobCommand command : commandList){

            if(CollectionUtils.isEmpty(commandList)){
                return;
            }
            try {
                run(command);
            }catch (Exception e){
                command.setStatus("FINISH");
                command.setResult(StringUtils.isEmpty(e.getMessage())?e.getClass().getName():e.getMessage());
                LOGGER.error("ERROR IN RUN COMMAND:{}", command);
                LOGGER.error("ERROR IN RUN COMMAND", e);
            }
            pipelineJobCommandService.merge(command);
        }

    }

    public void run(PipelineJobCommand pipelineJobCommand){

        if("CREATE".equals(pipelineJobCommand.getType())){
//            createJob(pipelineJobCommand);
            createPipeline(pipelineJobCommand);
        }
        if("UPDATE".equals(pipelineJobCommand.getType())){
//            updateJob(pipelineJobCommand);
            updatePipeline(pipelineJobCommand);
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

        Result result = yunkangClient.update(createJobAction);
        if(StringUtils.equals("FAILURE",result.getFlag())){
            result = yunkangClient.create(createJobAction);
        }
        command.setResult(result.getFlag()+":"+result.getErrorMsg());
        command.setStatus("FINISH");

    }

    public void deleteJob(PipelineJobCommand command){

        Result result = yunkangClient.delete(command.getTask()+"");

        command.setResult(result.getFlag()+":"+result.getErrorMsg());
        command.setStatus("FINISH");

    }

    /**
     * 在云康的发布平台创建一个JOB
     * @param command
     */
    public void createPipeline(PipelineJobCommand command){

        PipelineJob createJobAction = pipelineJob(command.getTask());

        Result result = yunkangClient.create(createJobAction);
//
        command.setResult(result.getFlag()+":"+result.getErrorMsg());
        command.setStatus("FINISH");

    }

    public void updatePipeline(PipelineJobCommand command){

        PipelineJob createJobAction = pipelineJob(command.getTask());

        Result result = yunkangClient.update(createJobAction);
        if(StringUtils.equals("FAILURE",result.getFlag())){
            result = yunkangClient.create(createJobAction);
        }
        command.setResult(result.getFlag()+":"+result.getErrorMsg());
        command.setStatus("FINISH");
    }

    private CreateJobAction buildCreateJobAction(Long taskId){

        PipelineTask task = pipelineTaskService.find(taskId);

        if(task==null){
            throw new BusinessException("JOB COMMAND RUNNER", "CREATE_JOB_ACTION", "NO TASK", "找不到task:"+taskId);
        }

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

    public PipelineJob pipelineJob(Long taskId){

        PipelineTask task = pipelineTaskService.find(taskId);

        if(task==null){
            throw new BusinessException("JOB COMMAND RUNNER", "CREATE_JOB_ACTION", "NO TASK", "找不到task:"+taskId);
        }

        List<PipelineTaskParam> pipelineTaskParamList = pipelineTaskParamService.findByTask(task.getId());
        List<PipelineTaskAction> pipelineTaskActionList = pipelineTaskActionService.findByTask(task.getId());



        List<PipelineStage> stageList = new ArrayList<>();

        List<String> shellCmdList;
        PipelineStage pipelineStage;

        for(PipelineTaskAction action : pipelineTaskActionList){

            pipelineStage = new PipelineStage();
            pipelineStage.setStageName(action.getName());
            shellCmdList = new ArrayList<>();
            shellCmdList.add(action.getContent());
            pipelineStage.setShellCmd(shellCmdList);

            stageList.add(pipelineStage);
        }

        List<StringParm> stringParmaList = new ArrayList<>();


        for(PipelineTaskParam param : pipelineTaskParamList){
            StringParm stringParm = new StringParm();
            stringParm.setName(param.getName());
            stringParm.setDefaultValue(param.getDefaultValue());
            stringParm.setDescription(param.getDescription());
            stringParmaList.add(stringParm);
        }


        PipelineJob pipelineJob = new PipelineJob();

        pipelineJob.setDesc(task.getDescription());
        pipelineJob.setJobName(task.getId()+"");
        pipelineJob.setPipelineStages(stageList);
        pipelineJob.setStringParms(stringParmaList);
        return pipelineJob;
    }

}
