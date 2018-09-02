package net.aicoder.speedcloud.client.pipeline.task;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.client.pipeline.task.result.PipelineTaskActionPageResult;
import net.aicoder.speedcloud.client.pipeline.task.result.PipelineTaskActionResult;
import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskActionAddDto;
import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskActionCondition;
import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskActionEditDto;
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
 * 操作客户端
 * @author icode
 */
@Service
public class PipelineTaskActionRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineTaskActionRibbon.class);

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
     * 新增操作
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public PipelineTaskActionResult add(PipelineTaskActionAddDto addDto) {
        String url = "http://"+host+"/speedcloud/pipeline/task/pipelinetaskaction";
        return restTemplate.postForObject(url, addDto, PipelineTaskActionResult.class);
    }
    private PipelineTaskActionResult addFail(PipelineTaskActionAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除操作
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public PipelineTaskActionResult delete(Long id) {
        String url = "http://"+host+"/speedcloud/pipeline/task/pipelinetaskaction/"+id;
        ResponseEntity<PipelineTaskActionResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<PipelineTaskActionResult>() {});
        return response.getBody();
    }
    private PipelineTaskActionResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 更新操作
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public PipelineTaskActionResult update(Long id, PipelineTaskActionEditDto editDto) {
        String url = "http://"+host+"/speedcloud/pipeline/task/pipelinetaskaction/"+id;
        ResponseEntity<PipelineTaskActionResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<PipelineTaskActionResult>() {});
        return response.getBody();
    }

    public PipelineTaskActionResult updateFail(Long id, PipelineTaskActionEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询操作
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public PipelineTaskActionResult get(Long id) {
        String url = "http://"+host+"/speedcloud/pipeline/task/pipelinetaskaction/"+id;
        return restTemplate.getForObject(url, PipelineTaskActionResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private PipelineTaskActionResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询操作列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public PipelineTaskActionPageResult list(PageSearchRequest<PipelineTaskActionCondition> pageSearchRequest) {
        String url = "http://"+host+"/speedcloud/pipeline/task/pipelinetaskaction/list";
        return restTemplate.postForObject(url, pageSearchRequest, PipelineTaskActionPageResult.class);
    }
    public PipelineTaskActionPageResult listFail(PageSearchRequest<PipelineTaskActionCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        PipelineTaskActionPageResult result = new PipelineTaskActionPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private PipelineTaskActionResult errorResult(){
        PipelineTaskActionResult result = new PipelineTaskActionResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }


}
