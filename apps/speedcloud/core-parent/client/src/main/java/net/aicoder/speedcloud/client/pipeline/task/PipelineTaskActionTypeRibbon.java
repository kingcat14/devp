package net.aicoder.speedcloud.client.pipeline.task;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.client.pipeline.task.result.PipelineTaskActionTypePageResult;
import net.aicoder.speedcloud.client.pipeline.task.result.PipelineTaskActionTypeResult;
import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskActionTypeAddDto;
import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskActionTypeCondition;
import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskActionTypeEditDto;
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
 * 操作类型客户端
 * @author icode
 */
@Service
public class PipelineTaskActionTypeRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineTaskActionTypeRibbon.class);

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
     * 新增操作类型
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public PipelineTaskActionTypeResult add(PipelineTaskActionTypeAddDto addDto) {
        String url = "http://"+host+"/speedcloud/pipeline/task/pipelinetaskactiontype";
        return restTemplate.postForObject(url, addDto, PipelineTaskActionTypeResult.class);
    }
    private PipelineTaskActionTypeResult addFail(PipelineTaskActionTypeAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除操作类型
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public PipelineTaskActionTypeResult delete(Long id) {
        String url = "http://"+host+"/speedcloud/pipeline/task/pipelinetaskactiontype/"+id;
        ResponseEntity<PipelineTaskActionTypeResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<PipelineTaskActionTypeResult>() {});
        return response.getBody();
    }
    private PipelineTaskActionTypeResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 更新操作类型
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public PipelineTaskActionTypeResult update(Long id, PipelineTaskActionTypeEditDto editDto) {
        String url = "http://"+host+"/speedcloud/pipeline/task/pipelinetaskactiontype/"+id;
        ResponseEntity<PipelineTaskActionTypeResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<PipelineTaskActionTypeResult>() {});
        return response.getBody();
    }

    public PipelineTaskActionTypeResult updateFail(Long id, PipelineTaskActionTypeEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询操作类型
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public PipelineTaskActionTypeResult get(Long id) {
        String url = "http://"+host+"/speedcloud/pipeline/task/pipelinetaskactiontype/"+id;
        return restTemplate.getForObject(url, PipelineTaskActionTypeResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private PipelineTaskActionTypeResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询操作类型列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public PipelineTaskActionTypePageResult list(PageSearchRequest<PipelineTaskActionTypeCondition> pageSearchRequest) {
        String url = "http://"+host+"/speedcloud/pipeline/task/pipelinetaskactiontype/list";
        return restTemplate.postForObject(url, pageSearchRequest, PipelineTaskActionTypePageResult.class);
    }
    public PipelineTaskActionTypePageResult listFail(PageSearchRequest<PipelineTaskActionTypeCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        PipelineTaskActionTypePageResult result = new PipelineTaskActionTypePageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private PipelineTaskActionTypeResult errorResult(){
        PipelineTaskActionTypeResult result = new PipelineTaskActionTypeResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }


}
