package net.aicoder.speedcloud.client.pipeline;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.client.pipeline.result.PipelineTaskTypeParamsPageResult;
import net.aicoder.speedcloud.client.pipeline.result.PipelineTaskTypeParamsResult;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineTaskTypeParamsAddDto;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineTaskTypeParamsCondition;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineTaskTypeParamsEditDto;
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
 * 任务类型参数定义客户端
 * @author icode
 */
@Service
public class PipelineTaskTypeParamsRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineTaskTypeParamsRibbon.class);

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
     * 新增任务类型参数定义
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public PipelineTaskTypeParamsResult add(PipelineTaskTypeParamsAddDto addDto) {
        String url = "http://"+host+"/speedcloud/pipeline/pipelinetasktypeparams";
        return restTemplate.postForObject(url, addDto, PipelineTaskTypeParamsResult.class);
    }
    private PipelineTaskTypeParamsResult addFail(PipelineTaskTypeParamsAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除任务类型参数定义
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public PipelineTaskTypeParamsResult delete(Long id) {
        String url = "http://"+host+"/speedcloud/pipeline/pipelinetasktypeparams/"+id;
        ResponseEntity<PipelineTaskTypeParamsResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<PipelineTaskTypeParamsResult>() {});
        return response.getBody();
    }
    private PipelineTaskTypeParamsResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 更新任务类型参数定义
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public PipelineTaskTypeParamsResult update(Long id, PipelineTaskTypeParamsEditDto editDto) {
        String url = "http://"+host+"/speedcloud/pipeline/pipelinetasktypeparams/"+id;
        ResponseEntity<PipelineTaskTypeParamsResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<PipelineTaskTypeParamsResult>() {});
        return response.getBody();
    }

    public PipelineTaskTypeParamsResult updateFail(Long id, PipelineTaskTypeParamsEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询任务类型参数定义
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public PipelineTaskTypeParamsResult get(Long id) {
        String url = "http://"+host+"/speedcloud/pipeline/pipelinetasktypeparams/"+id;
        return restTemplate.getForObject(url, PipelineTaskTypeParamsResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private PipelineTaskTypeParamsResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询任务类型参数定义列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public PipelineTaskTypeParamsPageResult list(PageSearchRequest<PipelineTaskTypeParamsCondition> pageSearchRequest) {
        String url = "http://"+host+"/speedcloud/pipeline/pipelinetasktypeparams/list";
        return restTemplate.postForObject(url, pageSearchRequest, PipelineTaskTypeParamsPageResult.class);
    }
    public PipelineTaskTypeParamsPageResult listFail(PageSearchRequest<PipelineTaskTypeParamsCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        PipelineTaskTypeParamsPageResult result = new PipelineTaskTypeParamsPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private PipelineTaskTypeParamsResult errorResult(){
        PipelineTaskTypeParamsResult result = new PipelineTaskTypeParamsResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }


}
