package net.aicoder.speedcloud.client.pipeline;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.client.pipeline.result.PipelinePageResult;
import net.aicoder.speedcloud.client.pipeline.result.PipelineResult;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineAddDto;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineCondition;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineEditDto;
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
 * 流水线客户端
 * @author icode
 */
@Service
public class PipelineRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineRibbon.class);

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
     * 新增流水线
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public PipelineResult add(PipelineAddDto addDto) {
        String url = "http://"+host+"/speedcloud/pipeline/pipeline";
        return restTemplate.postForObject(url, addDto, PipelineResult.class);
    }
    private PipelineResult addFail(PipelineAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除流水线
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public PipelineResult delete(Long id) {
        String url = "http://"+host+"/speedcloud/pipeline/pipeline/"+id;
        ResponseEntity<PipelineResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<PipelineResult>() {});
        return response.getBody();
    }
    private PipelineResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 更新流水线
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public PipelineResult update(Long id, PipelineEditDto editDto) {
        String url = "http://"+host+"/speedcloud/pipeline/pipeline/"+id;
        ResponseEntity<PipelineResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<PipelineResult>() {});
        return response.getBody();
    }

    public PipelineResult updateFail(Long id, PipelineEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询流水线
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public PipelineResult get(Long id) {
        String url = "http://"+host+"/speedcloud/pipeline/pipeline/"+id;
        return restTemplate.getForObject(url, PipelineResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private PipelineResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询流水线列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public PipelinePageResult list(PageSearchRequest<PipelineCondition> pageSearchRequest) {
        String url = "http://"+host+"/speedcloud/pipeline/pipeline/list";
        return restTemplate.postForObject(url, pageSearchRequest, PipelinePageResult.class);
    }
    public PipelinePageResult listFail(PageSearchRequest<PipelineCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        PipelinePageResult result = new PipelinePageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private PipelineResult errorResult(){
        PipelineResult result = new PipelineResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }


}
