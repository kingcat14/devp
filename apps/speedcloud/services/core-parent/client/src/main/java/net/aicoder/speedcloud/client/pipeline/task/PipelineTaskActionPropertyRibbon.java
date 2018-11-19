package net.aicoder.speedcloud.client.pipeline.task;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskActionPropertyAddDto;
import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskActionPropertyCondition;
import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskActionPropertyEditDto;
import net.aicoder.speedcloud.client.pipeline.task.result.PipelineTaskActionPropertyPageResult;
import net.aicoder.speedcloud.client.pipeline.task.result.PipelineTaskActionPropertyResult;
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
 * 操作属性客户端
 * @author icode
 */
@Service
public class PipelineTaskActionPropertyRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineTaskActionPropertyRibbon.class);

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
     * 新增操作属性
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public PipelineTaskActionPropertyResult add(PipelineTaskActionPropertyAddDto addDto) {
        String url = "http://"+host+"/speedcloud/pipeline/task/pipelinetaskactionproperty";
        return restTemplate.postForObject(url, addDto, PipelineTaskActionPropertyResult.class);
    }
    private PipelineTaskActionPropertyResult addFail(PipelineTaskActionPropertyAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除操作属性
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public PipelineTaskActionPropertyResult delete(Long id) {
        String url = "http://"+host+"/speedcloud/pipeline/task/pipelinetaskactionproperty/"+id;
        ResponseEntity<PipelineTaskActionPropertyResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<PipelineTaskActionPropertyResult>() {});
        return response.getBody();
    }
    private PipelineTaskActionPropertyResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 更新操作属性
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public PipelineTaskActionPropertyResult update(Long id, PipelineTaskActionPropertyEditDto editDto) {
        String url = "http://"+host+"/speedcloud/pipeline/task/pipelinetaskactionproperty/"+id;
        ResponseEntity<PipelineTaskActionPropertyResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<PipelineTaskActionPropertyResult>() {});
        return response.getBody();
    }

    public PipelineTaskActionPropertyResult updateFail(Long id, PipelineTaskActionPropertyEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询操作属性
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public PipelineTaskActionPropertyResult get(Long id) {
        String url = "http://"+host+"/speedcloud/pipeline/task/pipelinetaskactionproperty/"+id;
        return restTemplate.getForObject(url, PipelineTaskActionPropertyResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private PipelineTaskActionPropertyResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询操作属性列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public PipelineTaskActionPropertyPageResult list(PageSearchRequest<PipelineTaskActionPropertyCondition> pageSearchRequest) {
        String url = "http://"+host+"/speedcloud/pipeline/task/pipelinetaskactionproperty/list";
        return restTemplate.postForObject(url, pageSearchRequest, PipelineTaskActionPropertyPageResult.class);
    }
    public PipelineTaskActionPropertyPageResult listFail(PageSearchRequest<PipelineTaskActionPropertyCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        PipelineTaskActionPropertyPageResult result = new PipelineTaskActionPropertyPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private PipelineTaskActionPropertyResult errorResult(){
        PipelineTaskActionPropertyResult result = new PipelineTaskActionPropertyResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }


}
