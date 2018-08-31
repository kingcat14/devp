package net.aicoder.speedcloud.client.pipeline;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.client.pipeline.result.PipelineTaskTypePageResult;
import net.aicoder.speedcloud.client.pipeline.result.PipelineTaskTypeResult;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineTaskTypeAddDto;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineTaskTypeCondition;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineTaskTypeEditDto;
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
 * 任务类型客户端
 * @author icode
 */
@Service
public class PipelineTaskTypeRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineTaskTypeRibbon.class);

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
     * 新增任务类型
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public PipelineTaskTypeResult add(PipelineTaskTypeAddDto addDto) {
        String url = "http://"+host+"/speedcloud/pipeline/pipelinetasktype";
        return restTemplate.postForObject(url, addDto, PipelineTaskTypeResult.class);
    }
    private PipelineTaskTypeResult addFail(PipelineTaskTypeAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除任务类型
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public PipelineTaskTypeResult delete(Long id) {
        String url = "http://"+host+"/speedcloud/pipeline/pipelinetasktype/"+id;
        ResponseEntity<PipelineTaskTypeResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<PipelineTaskTypeResult>() {});
        return response.getBody();
    }
    private PipelineTaskTypeResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 更新任务类型
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public PipelineTaskTypeResult update(Long id, PipelineTaskTypeEditDto editDto) {
        String url = "http://"+host+"/speedcloud/pipeline/pipelinetasktype/"+id;
        ResponseEntity<PipelineTaskTypeResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<PipelineTaskTypeResult>() {});
        return response.getBody();
    }

    public PipelineTaskTypeResult updateFail(Long id, PipelineTaskTypeEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询任务类型
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public PipelineTaskTypeResult get(Long id) {
        String url = "http://"+host+"/speedcloud/pipeline/pipelinetasktype/"+id;
        return restTemplate.getForObject(url, PipelineTaskTypeResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private PipelineTaskTypeResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询任务类型列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public PipelineTaskTypePageResult list(PageSearchRequest<PipelineTaskTypeCondition> pageSearchRequest) {
        String url = "http://"+host+"/speedcloud/pipeline/pipelinetasktype/list";
        return restTemplate.postForObject(url, pageSearchRequest, PipelineTaskTypePageResult.class);
    }
    public PipelineTaskTypePageResult listFail(PageSearchRequest<PipelineTaskTypeCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        PipelineTaskTypePageResult result = new PipelineTaskTypePageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private PipelineTaskTypeResult errorResult(){
        PipelineTaskTypeResult result = new PipelineTaskTypeResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }


}
