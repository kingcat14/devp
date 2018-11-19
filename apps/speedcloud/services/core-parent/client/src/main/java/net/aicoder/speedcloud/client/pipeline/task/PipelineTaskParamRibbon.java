package net.aicoder.speedcloud.client.pipeline.task;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskParamAddDto;
import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskParamCondition;
import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskParamEditDto;
import net.aicoder.speedcloud.client.pipeline.task.result.PipelineTaskParamPageResult;
import net.aicoder.speedcloud.client.pipeline.task.result.PipelineTaskParamResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


/**
 * 任务参数客户端
 * @author icode
 */
@Service
public class PipelineTaskParamRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineTaskParamRibbon.class);

    private String host = "SPEEDCLOUD-SPEEDCLOUD-MICROSERVICE";

	@Autowired(required = false)
    private RestTemplate restTemplate;

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public void setHost(String host) {
        this.host = host;
    }


    /**
     * 新增任务参数
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public PipelineTaskParamResult add(PipelineTaskParamAddDto addDto) {
        String url = "http://"+host+"/speedcloud/pipeline/task/pipelinetaskparam";
        return restTemplate.postForObject(url, addDto, PipelineTaskParamResult.class);
    }
    private PipelineTaskParamResult addFail(PipelineTaskParamAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除任务参数
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public PipelineTaskParamResult delete(Long id) {
        String url = "http://"+host+"/speedcloud/pipeline/task/pipelinetaskparam/"+id;
        ResponseEntity<PipelineTaskParamResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<PipelineTaskParamResult>() {});
        return response.getBody();
    }
    private PipelineTaskParamResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 更新任务参数
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public PipelineTaskParamResult update(Long id, PipelineTaskParamEditDto editDto) {
        String url = "http://"+host+"/speedcloud/pipeline/task/pipelinetaskparam/"+id;
        ResponseEntity<PipelineTaskParamResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<PipelineTaskParamResult>() {});
        return response.getBody();
    }

    public PipelineTaskParamResult updateFail(Long id, PipelineTaskParamEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询任务参数
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public PipelineTaskParamResult get(Long id) {
        String url = "http://"+host+"/speedcloud/pipeline/task/pipelinetaskparam/"+id;
        return restTemplate.getForObject(url, PipelineTaskParamResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private PipelineTaskParamResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询任务参数列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public PipelineTaskParamPageResult list(PageSearchRequest<PipelineTaskParamCondition> pageSearchRequest) {
        String url = "http://"+host+"/speedcloud/pipeline/task/pipelinetaskparam/list";
        return restTemplate.postForObject(url, pageSearchRequest, PipelineTaskParamPageResult.class);
    }
    public PipelineTaskParamPageResult listFail(PageSearchRequest<PipelineTaskParamCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        PipelineTaskParamPageResult result = new PipelineTaskParamPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private PipelineTaskParamResult errorResult(){
        PipelineTaskParamResult result = new PipelineTaskParamResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }


}
