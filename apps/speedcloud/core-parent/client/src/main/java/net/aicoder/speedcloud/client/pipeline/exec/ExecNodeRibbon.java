package net.aicoder.speedcloud.client.pipeline.exec;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.client.pipeline.exec.result.ExecNodePageResult;
import net.aicoder.speedcloud.client.pipeline.exec.result.ExecNodeResult;
import net.aicoder.speedcloud.business.pipeline.exec.dto.ExecNodeAddDto;
import net.aicoder.speedcloud.business.pipeline.exec.dto.ExecNodeCondition;
import net.aicoder.speedcloud.business.pipeline.exec.dto.ExecNodeEditDto;
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
 * 运行实例节点客户端
 * @author icode
 */
@Service
public class ExecNodeRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExecNodeRibbon.class);

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
     * 新增运行实例节点
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public ExecNodeResult add(ExecNodeAddDto addDto) {
        String url = "http://"+host+"/speedcloud/pipeline/exec/execnode";
        return restTemplate.postForObject(url, addDto, ExecNodeResult.class);
    }
    private ExecNodeResult addFail(ExecNodeAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除运行实例节点
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public ExecNodeResult delete(Long id) {
        String url = "http://"+host+"/speedcloud/pipeline/exec/execnode/"+id;
        ResponseEntity<ExecNodeResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<ExecNodeResult>() {});
        return response.getBody();
    }
    private ExecNodeResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 更新运行实例节点
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public ExecNodeResult update(Long id, ExecNodeEditDto editDto) {
        String url = "http://"+host+"/speedcloud/pipeline/exec/execnode/"+id;
        ResponseEntity<ExecNodeResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<ExecNodeResult>() {});
        return response.getBody();
    }

    public ExecNodeResult updateFail(Long id, ExecNodeEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询运行实例节点
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public ExecNodeResult get(Long id) {
        String url = "http://"+host+"/speedcloud/pipeline/exec/execnode/"+id;
        return restTemplate.getForObject(url, ExecNodeResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private ExecNodeResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询运行实例节点列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public ExecNodePageResult list(PageSearchRequest<ExecNodeCondition> pageSearchRequest) {
        String url = "http://"+host+"/speedcloud/pipeline/exec/execnode/list";
        return restTemplate.postForObject(url, pageSearchRequest, ExecNodePageResult.class);
    }
    public ExecNodePageResult listFail(PageSearchRequest<ExecNodeCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        ExecNodePageResult result = new ExecNodePageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private ExecNodeResult errorResult(){
        ExecNodeResult result = new ExecNodeResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }


}
