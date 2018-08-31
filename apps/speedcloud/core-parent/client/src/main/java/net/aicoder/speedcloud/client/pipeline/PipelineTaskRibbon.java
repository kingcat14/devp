package net.aicoder.speedcloud.client.pipeline;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.client.pipeline.result.PipelineTaskPageResult;
import net.aicoder.speedcloud.client.pipeline.result.PipelineTaskResult;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineTaskAddDto;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineTaskCondition;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineTaskEditDto;
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
 * 任务客户端
 * @author icode
 */
@Service
public class PipelineTaskRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineTaskRibbon.class);

    private String host = "SPEEDCLOUD-MICROSERVICE";

	@Autowired(required = false)
    private RestTemplate restTemplate;

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public void setHost(String host) {
        this.host = host;
    }


    /**
     * 新增任务
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public PipelineTaskResult add(PipelineTaskAddDto addDto) {
        String url = "http://"+host+"/speedcloud/pipeline/pipelinetask";
        return restTemplate.postForObject(url, addDto, PipelineTaskResult.class);
    }
    private PipelineTaskResult addFail(PipelineTaskAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除任务
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public PipelineTaskResult delete(Long id) {
        String url = "http://"+host+"/speedcloud/pipeline/pipelinetask/"+id;
        ResponseEntity<PipelineTaskResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<PipelineTaskResult>() {});
        return response.getBody();
    }
    private PipelineTaskResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 更新任务
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public PipelineTaskResult update(Long id, PipelineTaskEditDto editDto) {
        String url = "http://"+host+"/speedcloud/pipeline/pipelinetask/"+id;
        ResponseEntity<PipelineTaskResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<PipelineTaskResult>() {});
        return response.getBody();
    }

    public PipelineTaskResult updateFail(Long id, PipelineTaskEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询任务
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public PipelineTaskResult get(Long id) {
        String url = "http://"+host+"/speedcloud/pipeline/pipelinetask/"+id;
        return restTemplate.getForObject(url, PipelineTaskResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private PipelineTaskResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询任务列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public PipelineTaskPageResult list(PageSearchRequest<PipelineTaskCondition> pageSearchRequest) {
        String url = "http://"+host+"/speedcloud/pipeline/pipelinetask/list";
        return restTemplate.postForObject(url, pageSearchRequest, PipelineTaskPageResult.class);
    }
    public PipelineTaskPageResult listFail(PageSearchRequest<PipelineTaskCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        PipelineTaskPageResult result = new PipelineTaskPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private PipelineTaskResult errorResult(){
        PipelineTaskResult result = new PipelineTaskResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }


}
