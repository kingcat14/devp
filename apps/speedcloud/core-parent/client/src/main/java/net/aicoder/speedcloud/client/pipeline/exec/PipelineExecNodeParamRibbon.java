package net.aicoder.speedcloud.client.pipeline.exec;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.client.pipeline.exec.result.PipelineExecNodeParamPageResult;
import net.aicoder.speedcloud.client.pipeline.exec.result.PipelineExecNodeParamResult;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecNodeParamAddDto;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecNodeParamCondition;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecNodeParamEditDto;
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
 * 运行实例节点参数客户端
 * @author icode
 */
@Service
public class PipelineExecNodeParamRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineExecNodeParamRibbon.class);

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
     * 新增运行实例节点参数
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public PipelineExecNodeParamResult add(PipelineExecNodeParamAddDto addDto) {
        String url = "http://"+host+"/speedcloud/pipeline/exec/pipelineexecnodeparam";
        return restTemplate.postForObject(url, addDto, PipelineExecNodeParamResult.class);
    }
    private PipelineExecNodeParamResult addFail(PipelineExecNodeParamAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除运行实例节点参数
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public PipelineExecNodeParamResult delete(Long id) {
        String url = "http://"+host+"/speedcloud/pipeline/exec/pipelineexecnodeparam/"+id;
        ResponseEntity<PipelineExecNodeParamResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<PipelineExecNodeParamResult>() {});
        return response.getBody();
    }
    private PipelineExecNodeParamResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 更新运行实例节点参数
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public PipelineExecNodeParamResult update(Long id, PipelineExecNodeParamEditDto editDto) {
        String url = "http://"+host+"/speedcloud/pipeline/exec/pipelineexecnodeparam/"+id;
        ResponseEntity<PipelineExecNodeParamResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<PipelineExecNodeParamResult>() {});
        return response.getBody();
    }

    public PipelineExecNodeParamResult updateFail(Long id, PipelineExecNodeParamEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询运行实例节点参数
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public PipelineExecNodeParamResult get(Long id) {
        String url = "http://"+host+"/speedcloud/pipeline/exec/pipelineexecnodeparam/"+id;
        return restTemplate.getForObject(url, PipelineExecNodeParamResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private PipelineExecNodeParamResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询运行实例节点参数列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public PipelineExecNodeParamPageResult list(PageSearchRequest<PipelineExecNodeParamCondition> pageSearchRequest) {
        String url = "http://"+host+"/speedcloud/pipeline/exec/pipelineexecnodeparam/list";
        return restTemplate.postForObject(url, pageSearchRequest, PipelineExecNodeParamPageResult.class);
    }
    public PipelineExecNodeParamPageResult listFail(PageSearchRequest<PipelineExecNodeParamCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        PipelineExecNodeParamPageResult result = new PipelineExecNodeParamPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private PipelineExecNodeParamResult errorResult(){
        PipelineExecNodeParamResult result = new PipelineExecNodeParamResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }


}
