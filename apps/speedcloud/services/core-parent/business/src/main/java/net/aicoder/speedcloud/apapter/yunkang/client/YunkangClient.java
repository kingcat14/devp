package net.aicoder.speedcloud.apapter.yunkang.client;

import com.devin.ciserver.model.job.PipelineJob;
import com.devin.ciserver.model.job.response.ResultBuildInfo;
import lombok.extern.slf4j.Slf4j;
import net.aicoder.speedcloud.apapter.yunkang.client.dto.CreateJobAction;
import net.aicoder.speedcloud.apapter.yunkang.client.dto.ExecParam;
import net.aicoder.speedcloud.business.pipeline.exec.vo.PipelineBuildLogVO;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
public class YunkangClient {

    RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());

//    String yunkangHost = "10.10.40.234:8181";

    String yunkangHost = "127.0.0.1:6767";
    public Result create(CreateJobAction createJobAction){
        String url = "http://"+yunkangHost+"/api/job-admin/v1/job";
        return restTemplate.postForObject(url, createJobAction, Result.class);
    }

    public Result update(CreateJobAction createJobAction){
        String url = "http://"+yunkangHost+"/api/job-admin/v1/job";
        return restTemplate.patchForObject(url, createJobAction, Result.class);
    }

    public Result create(PipelineJob createJobAction){
        String url = "http://"+yunkangHost+"/api/job-admin/v1/job/pipeline";
        return restTemplate.postForObject(url, createJobAction, Result.class);
    }
    public Result update(PipelineJob createJobAction){
        String url = "http://"+yunkangHost+"/api/job-admin/v1/job/pipeline";
        return restTemplate.patchForObject(url, createJobAction, Result.class);
    }

    public Result delete(String jobName){
        String url = "http://"+yunkangHost+"/api/job-admin/v1/job?jobName="+jobName;
        ResponseEntity<Result> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<Result>() {});
        return response.getBody();
    }

    /**
     * 获取job第number次执行的build明细信息
     * @param jobId
     * @param number
     * @return
     */
    public ResultBuildInfo buildInfo(String jobId, String number){

        String url = "http://"+yunkangHost+"/api/job-admin/v1/job/build/"+jobId+"/detail/"+number;
        return restTemplate.getForObject(url, ResultBuildInfo.class);
    }

    /**
     * 获取job第number次执行的build明细信息
     * @param jobId
     * @param number
     * @return
     */
    public PipelineBuildLogVO pipelineBuildInfo(String jobId, String number){

        String url = "http://"+yunkangHost+"/api/job-admin/v1/job/build/"+jobId+"/detail/"+number;

        ResultBuildInfo buildInfo = restTemplate.getForObject(url, ResultBuildInfo.class);

        if(buildInfo.getData() != null) {

            log.info("buildInfo result:{}", buildInfo.getData().getResult());

            return ConvertUtil.buildWithDetailToBuildDetail(buildInfo.getData(), jobId);
        }
        return null;
    }

    /**
     * 执行job(带参数)
     * @param jobId
     * @param execParamList
     * @return
     */
    public ExecResult exec(String jobId, List<ExecParam> execParamList){


        String url = "http://"+yunkangHost+"/api/job-admin/v1/job/build/"+jobId;
        //
        if(CollectionUtils.isNotEmpty(execParamList)){
            url = "http://"+yunkangHost+"/api/job-admin/v1/job/build/"+jobId+"/param";
        }
        return restTemplate.postForObject(url, execParamList, ExecResult.class);

    }
}
