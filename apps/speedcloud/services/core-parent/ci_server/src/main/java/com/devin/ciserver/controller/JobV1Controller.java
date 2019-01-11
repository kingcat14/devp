package com.devin.ciserver.controller;

import com.devin.ciserver.Inter.JobService;
import com.devin.ciserver.model.CiResponse;
import com.devin.ciserver.model.Constants;
import com.devin.ciserver.model.job.*;
import com.devin.ciserver.service.NoJobException;
import com.devin.ciserver.util.ConvertUtil;
import com.devin.ciserver.util.XmlUtil;
import com.offbytwo.jenkins.model.BuildWithDetails;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by devin on 2018/8/8.
 */
@Api(value = "集成服务-任务管理接口")
@RequestMapping("/api/job-admin/v1")
@RestController
public class JobV1Controller extends BaseController{
    @Autowired
    private JobService jobService;
    @ApiOperation(value = "获取系统job信息")
    @RequestMapping(value = "/job", method = RequestMethod.GET)
    public CiResponse<List<JobDetail>> getJobs(@RequestParam(value = "queryKey", required = false) String queryKey){
        try {
            List<JobDetail> jobDetails=jobService.getJobs(queryKey);
            return new CiResponse<List<JobDetail>>(Constants.RESPONSE_FLAG_SUCCESS,jobDetails);
        } catch (Exception e) {
            e.printStackTrace();
            return new CiResponse<List<JobDetail>>(Constants.RESPONSE_FLAG_FAILURE,e.getMessage());
        }
    }

    @ApiOperation(value = "获取单个job明细")
    @RequestMapping(value = "/job/{jobName}/detail", method = RequestMethod.GET)
    public CiResponse<JobDetail> getJobDetail(@PathVariable(value = "jobName") String jobName){
        try {
            if(jobName==null||jobName.equals("")){
                return new CiResponse<JobDetail>(Constants.RESPONSE_FLAG_FAILURE,"job名称不能为空!");
            }
            JobDetail jobDetail=jobService.getJobDetail(jobName);
            return new CiResponse<JobDetail>(Constants.RESPONSE_FLAG_SUCCESS,jobDetail);
        } catch (Exception e) {
            e.printStackTrace();
            return new CiResponse<JobDetail>(Constants.RESPONSE_FLAG_FAILURE,e.getMessage());
        }
    }

    @ApiOperation("创建普通job")
     @RequestMapping(value = "/job", method = RequestMethod.POST)
     public CiResponse<Object> jobAdd(@RequestBody BaseJob baseJob) {
        try {
            if(baseJob.getJobName()==null||baseJob.getJobName().equals("")){
                return new CiResponse<Object>(Constants.RESPONSE_FLAG_FAILURE,"job名称不能为空!");
            }
            if(baseJob.getKeepBuildDay()<=0){
                return new CiResponse<Object>(Constants.RESPONSE_FLAG_FAILURE,"job build保持天数至少为1!");
            }
            if(baseJob.getKeepBuildNumber()<=0){
                return new CiResponse<Object>(Constants.RESPONSE_FLAG_FAILURE,"job build记录数数至少为1!");
            }
            jobService.createJob(baseJob.getJobName(), XmlUtil.baseJobToXml(baseJob));
            return new CiResponse<Object>(Constants.RESPONSE_FLAG_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new CiResponse<Object>(Constants.RESPONSE_FLAG_FAILURE,e.getMessage());
        }
    }



    @ApiOperation("修改job")
    @RequestMapping(value = "/job", method = RequestMethod.PATCH)
    public CiResponse<Object> jobUpdate(@RequestBody BaseJob baseJob) {
        try {
            if(baseJob.getJobName()==null||baseJob.getJobName().equals("")){
                return new CiResponse<Object>(Constants.RESPONSE_FLAG_FAILURE,"job名称不能为空!");
            }
            if(baseJob.getKeepBuildDay()<=0){
                return new CiResponse<Object>(Constants.RESPONSE_FLAG_FAILURE,"job build保持天数至少为1!");
            }
            if(baseJob.getKeepBuildNumber()<=0){
                return new CiResponse<Object>(Constants.RESPONSE_FLAG_FAILURE,"job build记录数数至少为1!");
            }
            jobService.updateJob(baseJob.getJobName(),XmlUtil.baseJobToXml(baseJob));
            return new CiResponse<Object>(Constants.RESPONSE_FLAG_SUCCESS);
        } catch (NoJobException noJobException){
            noJobException.printStackTrace();
            return new CiResponse<Object>(Constants.RESPONSE_FLAG_FAILURE, noJobException.getMessage());
        }catch (Exception e) {
            e.printStackTrace();
            return new CiResponse<Object>(Constants.RESPONSE_FLAG_FAILURE, e.getMessage());
        }
    }

    @ApiOperation("创建流水线类型的job")
    @RequestMapping(value = "/job/pipeline", method = RequestMethod.POST)
    public CiResponse<Object> pipelineAdd(@RequestBody PipelineJob pipelineJob) {
        try {
            if(pipelineJob.getJobName()==null||pipelineJob.getJobName().equals("")){
                return new CiResponse<Object>(Constants.RESPONSE_FLAG_FAILURE,"job名称不能为空!");
            }
            if(pipelineJob.getKeepBuildDay()<=0){
                return new CiResponse<Object>(Constants.RESPONSE_FLAG_FAILURE,"job build保持天数至少为1!");
            }
            if(pipelineJob.getKeepBuildNumber()<=0){
                return new CiResponse<Object>(Constants.RESPONSE_FLAG_FAILURE,"job build记录数数至少为1!");
            }
            jobService.createJob(pipelineJob.getJobName(), XmlUtil.pipelineJobToXml(pipelineJob));
            return new CiResponse<Object>(Constants.RESPONSE_FLAG_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new CiResponse<Object>(Constants.RESPONSE_FLAG_FAILURE,e.getMessage());
        }
    }

    @ApiOperation("修改流水线类型的job")
    @RequestMapping(value = "/job/pipeline", method = RequestMethod.PATCH)
    public CiResponse<Object> pipelineUpdate(@RequestBody PipelineJob pipelineJob) {
        try {
            if(pipelineJob.getJobName()==null||pipelineJob.getJobName().equals("")){
                return new CiResponse<Object>(Constants.RESPONSE_FLAG_FAILURE,"job名称不能为空!");
            }
            if(pipelineJob.getKeepBuildDay()<=0){
                return new CiResponse<Object>(Constants.RESPONSE_FLAG_FAILURE,"job build保持天数至少为1!");
            }
            if(pipelineJob.getKeepBuildNumber()<=0){
                return new CiResponse<Object>(Constants.RESPONSE_FLAG_FAILURE,"job build记录数数至少为1!");
            }
            jobService.updateJob(pipelineJob.getJobName(),XmlUtil.pipelineJobToXml(pipelineJob));
            return new CiResponse<Object>(Constants.RESPONSE_FLAG_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new CiResponse<Object>(Constants.RESPONSE_FLAG_FAILURE,e.getMessage());
        }
    }

    @ApiOperation("删除job")
    @RequestMapping(value = "/job", method = RequestMethod.DELETE)
    public CiResponse<Object> jobDelete(@RequestParam(value = "jobName", required = true) String jobName) {
        try {
            if(jobName==null||jobName.equals("")){
                return new CiResponse<Object>(Constants.RESPONSE_FLAG_FAILURE,"job名称不能为空!");
            }
            jobService.deleteJob(jobName);
            return new CiResponse<Object>(Constants.RESPONSE_FLAG_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new CiResponse<Object>(Constants.RESPONSE_FLAG_FAILURE,e.getMessage());
        }
    }

    @ApiOperation("执行job")
    @RequestMapping(value = "/job/build/{jobName}", method = RequestMethod.POST)
    public CiResponse<Object> jobBuild(@PathVariable(value = "jobName") String jobName) {
        try {
            if(jobName==null||jobName.equals("")){
                return new CiResponse<Object>(Constants.RESPONSE_FLAG_FAILURE,"job名称不能为空!");
            }
            int currentBuildNumber=jobService.buildJob(jobName);
            Map<String,String> mResult=new HashMap<String, String>();
            mResult.put("currentBuildNumber",Integer.toString(currentBuildNumber));
            return new CiResponse<Object>(Constants.RESPONSE_FLAG_SUCCESS,mResult);
        } catch (Exception e) {
            e.printStackTrace();
            return new CiResponse<Object>(Constants.RESPONSE_FLAG_FAILURE,e.getMessage());
        }
    }

    @ApiOperation("执行job(带参数)")
    @RequestMapping(value = "/job/build/{jobName}/param", method = RequestMethod.POST)
    public CiResponse<Object> jobBuild1(@PathVariable(value = "jobName") String jobName,@RequestBody List<StringPair> params) {
        try {
            if(jobName==null||jobName.equals("")){
                return new CiResponse<Object>(Constants.RESPONSE_FLAG_FAILURE,"job名称不能为空!");
            }
            if(params==null||params.size()==0){
                return new CiResponse<Object>(Constants.RESPONSE_FLAG_FAILURE,"参数不能为空!");
            }
            Map<String,String> paramMap=new HashMap<String, String>();
            for(StringPair sp:params){
                paramMap.put(sp.getParamName(),sp.getParamValue());
            }
            int currentBuildNumber=jobService.buildJob(jobName,paramMap);
            Map<String,String> mResult=new HashMap<String, String>();
            mResult.put("currentBuildNumber",Integer.toString(currentBuildNumber));
            return new CiResponse<Object>(Constants.RESPONSE_FLAG_SUCCESS,mResult);
        } catch (Exception e) {
            e.printStackTrace();
            return new CiResponse<Object>(Constants.RESPONSE_FLAG_FAILURE,e.getMessage());
        }
    }

    @ApiOperation(value = "获取单个job的所有build信息")
    @RequestMapping(value = "/job/build/{jobName}", method = RequestMethod.GET)
    public CiResponse<List<BuildDetail>> getJobBuilds(@PathVariable(value = "jobName") String jobName){
        try {
            if(jobName==null||jobName.equals("")){
                return new CiResponse<List<BuildDetail>>(Constants.RESPONSE_FLAG_FAILURE,"job名称不能为空!");
            }
            List<BuildDetail> buildDetails=jobService.getJobBuilds(jobName);
            return new CiResponse<List<BuildDetail>>(Constants.RESPONSE_FLAG_SUCCESS,buildDetails);
        } catch (Exception e) {
            e.printStackTrace();
            return new CiResponse<List<BuildDetail>>(Constants.RESPONSE_FLAG_FAILURE,e.getMessage());
        }
    }

    @ApiOperation(value = "根据范围获取单个job的build信息")
    @RequestMapping(value = "/job/build/{jobName}/range/{from}/{to}", method = RequestMethod.GET)
    public CiResponse<List<BuildDetail>> getJobBuildsByRange(@PathVariable(value = "jobName") String jobName,
                                                             @PathVariable(value = "from") Integer from,
                                                             @PathVariable(value = "to") Integer to){
        try {
            if(jobName==null||jobName.equals("")){
                return new CiResponse<List<BuildDetail>>(Constants.RESPONSE_FLAG_FAILURE,"job名称不能为空!");
            }
            List<BuildDetail> buildDetails=jobService.getJobBuildsByRange(jobName,from,to);
            return new CiResponse<List<BuildDetail>>(Constants.RESPONSE_FLAG_SUCCESS,buildDetails);
        } catch (Exception e) {
            e.printStackTrace();
            return new CiResponse<List<BuildDetail>>(Constants.RESPONSE_FLAG_FAILURE,e.getMessage());
        }
    }

    @ApiOperation(value = "获取job最后一次执行的build明细信息")
    @RequestMapping(value = "/job/build/{jobName}/detail/last", method = RequestMethod.GET)
    public CiResponse<BuildDetail> jobBuildDetail(@PathVariable(value = "jobName") String jobName){
        try {
            if(jobName==null||jobName.equals("")){
                return new CiResponse<>(Constants.RESPONSE_FLAG_FAILURE,"job名称不能为空!");
            }
            BuildWithDetails bwd=jobService.getBuildJobDetailByLastTime(jobName);
            if(bwd.getId()==null){
                return new CiResponse<>(Constants.RESPONSE_FLAG_FAILURE,"job["+jobName+"]没有build记录!");
            }
            BuildDetail bd = ConvertUtil.buildWithDetailToBuildDetail(bwd);
            return new CiResponse<>(Constants.RESPONSE_FLAG_SUCCESS,bd);
        } catch (Exception e) {
            e.printStackTrace();
            return new CiResponse<>(Constants.RESPONSE_FLAG_FAILURE,e.getMessage());
        }
    }

    @ApiOperation(value = "获取job第number次执行的build明细信息")
    @RequestMapping(value = "/job/build/{jobName}/detail/{number}", method = RequestMethod.GET)
    public CiResponse<BuildDetail> jobBuildDetailByNumber(@PathVariable(value = "jobName") String jobName,@PathVariable(value = "number") int number){
        try {
            if(jobName==null||jobName.equals("")){
                return new CiResponse<>(Constants.RESPONSE_FLAG_FAILURE,"job名称不能为空!");
            }
            BuildWithDetails bwd = jobService.getBuildJobDetailByNumber(jobName,number);
            if(bwd.getId()==null){
                return new CiResponse<>(Constants.RESPONSE_FLAG_FAILURE,"job["+jobName+"]没有第["+number+"]的build记录!");
            }
            BuildDetail bd=ConvertUtil.buildWithDetailToBuildDetail(bwd);
            return new CiResponse<>(Constants.RESPONSE_FLAG_SUCCESS,bd);
        } catch (Exception e) {
            e.printStackTrace();
            return new CiResponse<>(Constants.RESPONSE_FLAG_FAILURE,e.getMessage());
        }
    }
}
