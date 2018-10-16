package net.aicoder.speedcloud.client.pipeline;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.client.pipeline.result.PipelineParamPageResult;
import net.aicoder.speedcloud.client.pipeline.result.PipelineParamResult;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineParamAddDto;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineParamCondition;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineParamEditDto;
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
 * 流水线参数客户端
 * @author icode
 */
@Service
public class PipelineParamRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineParamRibbon.class);

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
     * 新增流水线参数
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public PipelineParamResult add(PipelineParamAddDto addDto) {
        String url = "http://"+host+"/speedcloud/pipeline/pipelineparam";
        return restTemplate.postForObject(url, addDto, PipelineParamResult.class);
    }
    private PipelineParamResult addFail(PipelineParamAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除流水线参数
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public PipelineParamResult delete(Long id) {
        String url = "http://"+host+"/speedcloud/pipeline/pipelineparam/"+id;
        ResponseEntity<PipelineParamResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<PipelineParamResult>() {});
        return response.getBody();
    }
    private PipelineParamResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 更新流水线参数
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public PipelineParamResult update(Long id, PipelineParamEditDto editDto) {
        String url = "http://"+host+"/speedcloud/pipeline/pipelineparam/"+id;
        ResponseEntity<PipelineParamResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<PipelineParamResult>() {});
        return response.getBody();
    }

    public PipelineParamResult updateFail(Long id, PipelineParamEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询流水线参数
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public PipelineParamResult get(Long id) {
        String url = "http://"+host+"/speedcloud/pipeline/pipelineparam/"+id;
        return restTemplate.getForObject(url, PipelineParamResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private PipelineParamResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询流水线参数列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public PipelineParamPageResult list(PageSearchRequest<PipelineParamCondition> pageSearchRequest) {
        String url = "http://"+host+"/speedcloud/pipeline/pipelineparam/list";
        return restTemplate.postForObject(url, pageSearchRequest, PipelineParamPageResult.class);
    }
    public PipelineParamPageResult listFail(PageSearchRequest<PipelineParamCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        PipelineParamPageResult result = new PipelineParamPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private PipelineParamResult errorResult(){
        PipelineParamResult result = new PipelineParamResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }


}
