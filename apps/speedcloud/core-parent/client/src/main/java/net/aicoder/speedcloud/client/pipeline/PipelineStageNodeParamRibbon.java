package net.aicoder.speedcloud.client.pipeline;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.client.pipeline.result.PipelineStageNodeParamPageResult;
import net.aicoder.speedcloud.client.pipeline.result.PipelineStageNodeParamResult;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineStageNodeParamAddDto;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineStageNodeParamCondition;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineStageNodeParamEditDto;
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
 * 阶段执行节点参数客户端
 * @author icode
 */
@Service
public class PipelineStageNodeParamRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineStageNodeParamRibbon.class);

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
     * 新增阶段执行节点参数
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public PipelineStageNodeParamResult add(PipelineStageNodeParamAddDto addDto) {
        String url = "http://"+host+"/speedcloud/pipeline/pipelinestagenodeparam";
        return restTemplate.postForObject(url, addDto, PipelineStageNodeParamResult.class);
    }
    private PipelineStageNodeParamResult addFail(PipelineStageNodeParamAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除阶段执行节点参数
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public PipelineStageNodeParamResult delete(Long id) {
        String url = "http://"+host+"/speedcloud/pipeline/pipelinestagenodeparam/"+id;
        ResponseEntity<PipelineStageNodeParamResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<PipelineStageNodeParamResult>() {});
        return response.getBody();
    }
    private PipelineStageNodeParamResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 更新阶段执行节点参数
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public PipelineStageNodeParamResult update(Long id, PipelineStageNodeParamEditDto editDto) {
        String url = "http://"+host+"/speedcloud/pipeline/pipelinestagenodeparam/"+id;
        ResponseEntity<PipelineStageNodeParamResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<PipelineStageNodeParamResult>() {});
        return response.getBody();
    }

    public PipelineStageNodeParamResult updateFail(Long id, PipelineStageNodeParamEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询阶段执行节点参数
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public PipelineStageNodeParamResult get(Long id) {
        String url = "http://"+host+"/speedcloud/pipeline/pipelinestagenodeparam/"+id;
        return restTemplate.getForObject(url, PipelineStageNodeParamResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private PipelineStageNodeParamResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询阶段执行节点参数列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public PipelineStageNodeParamPageResult list(PageSearchRequest<PipelineStageNodeParamCondition> pageSearchRequest) {
        String url = "http://"+host+"/speedcloud/pipeline/pipelinestagenodeparam/list";
        return restTemplate.postForObject(url, pageSearchRequest, PipelineStageNodeParamPageResult.class);
    }
    public PipelineStageNodeParamPageResult listFail(PageSearchRequest<PipelineStageNodeParamCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        PipelineStageNodeParamPageResult result = new PipelineStageNodeParamPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private PipelineStageNodeParamResult errorResult(){
        PipelineStageNodeParamResult result = new PipelineStageNodeParamResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }


}
