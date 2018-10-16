package net.aicoder.speedcloud.client.pipeline;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.client.pipeline.result.PipelineCodeRepositoryRelationPageResult;
import net.aicoder.speedcloud.client.pipeline.result.PipelineCodeRepositoryRelationResult;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineCodeRepositoryRelationAddDto;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineCodeRepositoryRelationCondition;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineCodeRepositoryRelationEditDto;
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
 * 流水线代码库关联客户端
 * @author icode
 */
@Service
public class PipelineCodeRepositoryRelationRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineCodeRepositoryRelationRibbon.class);

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
     * 新增流水线代码库关联
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public PipelineCodeRepositoryRelationResult add(PipelineCodeRepositoryRelationAddDto addDto) {
        String url = "http://"+host+"/speedcloud/pipeline/pipelinecoderepositoryrelation";
        return restTemplate.postForObject(url, addDto, PipelineCodeRepositoryRelationResult.class);
    }
    private PipelineCodeRepositoryRelationResult addFail(PipelineCodeRepositoryRelationAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除流水线代码库关联
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public PipelineCodeRepositoryRelationResult delete(Long id) {
        String url = "http://"+host+"/speedcloud/pipeline/pipelinecoderepositoryrelation/"+id;
        ResponseEntity<PipelineCodeRepositoryRelationResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<PipelineCodeRepositoryRelationResult>() {});
        return response.getBody();
    }
    private PipelineCodeRepositoryRelationResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 更新流水线代码库关联
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public PipelineCodeRepositoryRelationResult update(Long id, PipelineCodeRepositoryRelationEditDto editDto) {
        String url = "http://"+host+"/speedcloud/pipeline/pipelinecoderepositoryrelation/"+id;
        ResponseEntity<PipelineCodeRepositoryRelationResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<PipelineCodeRepositoryRelationResult>() {});
        return response.getBody();
    }

    public PipelineCodeRepositoryRelationResult updateFail(Long id, PipelineCodeRepositoryRelationEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询流水线代码库关联
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public PipelineCodeRepositoryRelationResult get(Long id) {
        String url = "http://"+host+"/speedcloud/pipeline/pipelinecoderepositoryrelation/"+id;
        return restTemplate.getForObject(url, PipelineCodeRepositoryRelationResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private PipelineCodeRepositoryRelationResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询流水线代码库关联列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public PipelineCodeRepositoryRelationPageResult list(PageSearchRequest<PipelineCodeRepositoryRelationCondition> pageSearchRequest) {
        String url = "http://"+host+"/speedcloud/pipeline/pipelinecoderepositoryrelation/list";
        return restTemplate.postForObject(url, pageSearchRequest, PipelineCodeRepositoryRelationPageResult.class);
    }
    public PipelineCodeRepositoryRelationPageResult listFail(PageSearchRequest<PipelineCodeRepositoryRelationCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        PipelineCodeRepositoryRelationPageResult result = new PipelineCodeRepositoryRelationPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private PipelineCodeRepositoryRelationResult errorResult(){
        PipelineCodeRepositoryRelationResult result = new PipelineCodeRepositoryRelationResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }


}
