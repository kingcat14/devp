package net.aicoder.speedcloud.client.pipeline.task;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.client.pipeline.task.result.PipelineTaskActionTypePropertyPageResult;
import net.aicoder.speedcloud.client.pipeline.task.result.PipelineTaskActionTypePropertyResult;
import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskActionTypePropertyAddDto;
import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskActionTypePropertyCondition;
import net.aicoder.speedcloud.business.pipeline.task.dto.PipelineTaskActionTypePropertyEditDto;
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
 * 操作类型属性定义客户端
 * @author icode
 */
@Service
public class PipelineTaskActionTypePropertyRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineTaskActionTypePropertyRibbon.class);

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
     * 新增操作类型属性定义
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public PipelineTaskActionTypePropertyResult add(PipelineTaskActionTypePropertyAddDto addDto) {
        String url = "http://"+host+"/speedcloud/pipeline/task/pipelinetaskactiontypeproperty";
        return restTemplate.postForObject(url, addDto, PipelineTaskActionTypePropertyResult.class);
    }
    private PipelineTaskActionTypePropertyResult addFail(PipelineTaskActionTypePropertyAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除操作类型属性定义
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public PipelineTaskActionTypePropertyResult delete(Long id) {
        String url = "http://"+host+"/speedcloud/pipeline/task/pipelinetaskactiontypeproperty/"+id;
        ResponseEntity<PipelineTaskActionTypePropertyResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<PipelineTaskActionTypePropertyResult>() {});
        return response.getBody();
    }
    private PipelineTaskActionTypePropertyResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 更新操作类型属性定义
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public PipelineTaskActionTypePropertyResult update(Long id, PipelineTaskActionTypePropertyEditDto editDto) {
        String url = "http://"+host+"/speedcloud/pipeline/task/pipelinetaskactiontypeproperty/"+id;
        ResponseEntity<PipelineTaskActionTypePropertyResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<PipelineTaskActionTypePropertyResult>() {});
        return response.getBody();
    }

    public PipelineTaskActionTypePropertyResult updateFail(Long id, PipelineTaskActionTypePropertyEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询操作类型属性定义
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public PipelineTaskActionTypePropertyResult get(Long id) {
        String url = "http://"+host+"/speedcloud/pipeline/task/pipelinetaskactiontypeproperty/"+id;
        return restTemplate.getForObject(url, PipelineTaskActionTypePropertyResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private PipelineTaskActionTypePropertyResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询操作类型属性定义列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public PipelineTaskActionTypePropertyPageResult list(PageSearchRequest<PipelineTaskActionTypePropertyCondition> pageSearchRequest) {
        String url = "http://"+host+"/speedcloud/pipeline/task/pipelinetaskactiontypeproperty/list";
        return restTemplate.postForObject(url, pageSearchRequest, PipelineTaskActionTypePropertyPageResult.class);
    }
    public PipelineTaskActionTypePropertyPageResult listFail(PageSearchRequest<PipelineTaskActionTypePropertyCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        PipelineTaskActionTypePropertyPageResult result = new PipelineTaskActionTypePropertyPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private PipelineTaskActionTypePropertyResult errorResult(){
        PipelineTaskActionTypePropertyResult result = new PipelineTaskActionTypePropertyResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }


}
