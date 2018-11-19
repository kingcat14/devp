package net.aicoder.speedcloud.client.pipeline;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineStageAddDto;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineStageCondition;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineStageEditDto;
import net.aicoder.speedcloud.client.pipeline.result.PipelineStagePageResult;
import net.aicoder.speedcloud.client.pipeline.result.PipelineStageResult;
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
 * 阶段客户端
 * @author icode
 */
@Service
public class PipelineStageRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineStageRibbon.class);

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
     * 新增阶段
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public PipelineStageResult add(PipelineStageAddDto addDto) {
        String url = "http://"+host+"/speedcloud/pipeline/pipelinestage";
        return restTemplate.postForObject(url, addDto, PipelineStageResult.class);
    }
    private PipelineStageResult addFail(PipelineStageAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除阶段
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public PipelineStageResult delete(Long id) {
        String url = "http://"+host+"/speedcloud/pipeline/pipelinestage/"+id;
        ResponseEntity<PipelineStageResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<PipelineStageResult>() {});
        return response.getBody();
    }
    private PipelineStageResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 更新阶段
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public PipelineStageResult update(Long id, PipelineStageEditDto editDto) {
        String url = "http://"+host+"/speedcloud/pipeline/pipelinestage/"+id;
        ResponseEntity<PipelineStageResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<PipelineStageResult>() {});
        return response.getBody();
    }

    public PipelineStageResult updateFail(Long id, PipelineStageEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询阶段
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public PipelineStageResult get(Long id) {
        String url = "http://"+host+"/speedcloud/pipeline/pipelinestage/"+id;
        return restTemplate.getForObject(url, PipelineStageResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private PipelineStageResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询阶段列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public PipelineStagePageResult list(PageSearchRequest<PipelineStageCondition> pageSearchRequest) {
        String url = "http://"+host+"/speedcloud/pipeline/pipelinestage/list";
        return restTemplate.postForObject(url, pageSearchRequest, PipelineStagePageResult.class);
    }
    public PipelineStagePageResult listFail(PageSearchRequest<PipelineStageCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        PipelineStagePageResult result = new PipelineStagePageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private PipelineStageResult errorResult(){
        PipelineStageResult result = new PipelineStageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }


}
