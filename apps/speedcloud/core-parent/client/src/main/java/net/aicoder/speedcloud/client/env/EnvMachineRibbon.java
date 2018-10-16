package net.aicoder.speedcloud.client.env;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.client.env.result.EnvMachinePageResult;
import net.aicoder.speedcloud.client.env.result.EnvMachineResult;
import net.aicoder.speedcloud.business.env.dto.EnvMachineAddDto;
import net.aicoder.speedcloud.business.env.dto.EnvMachineCondition;
import net.aicoder.speedcloud.business.env.dto.EnvMachineEditDto;
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
 * 环境设备关联客户端
 * @author icode
 */
@Service
public class EnvMachineRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(EnvMachineRibbon.class);

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
     * 新增环境设备关联
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public EnvMachineResult add(EnvMachineAddDto addDto) {
        String url = "http://"+host+"/speedcloud/env/envmachine";
        return restTemplate.postForObject(url, addDto, EnvMachineResult.class);
    }
    private EnvMachineResult addFail(EnvMachineAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除环境设备关联
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public EnvMachineResult delete(Long id) {
        String url = "http://"+host+"/speedcloud/env/envmachine/"+id;
        ResponseEntity<EnvMachineResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<EnvMachineResult>() {});
        return response.getBody();
    }
    private EnvMachineResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 更新环境设备关联
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public EnvMachineResult update(Long id, EnvMachineEditDto editDto) {
        String url = "http://"+host+"/speedcloud/env/envmachine/"+id;
        ResponseEntity<EnvMachineResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<EnvMachineResult>() {});
        return response.getBody();
    }

    public EnvMachineResult updateFail(Long id, EnvMachineEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询环境设备关联
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public EnvMachineResult get(Long id) {
        String url = "http://"+host+"/speedcloud/env/envmachine/"+id;
        return restTemplate.getForObject(url, EnvMachineResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private EnvMachineResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询环境设备关联列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public EnvMachinePageResult list(PageSearchRequest<EnvMachineCondition> pageSearchRequest) {
        String url = "http://"+host+"/speedcloud/env/envmachine/list";
        return restTemplate.postForObject(url, pageSearchRequest, EnvMachinePageResult.class);
    }
    public EnvMachinePageResult listFail(PageSearchRequest<EnvMachineCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        EnvMachinePageResult result = new EnvMachinePageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private EnvMachineResult errorResult(){
        EnvMachineResult result = new EnvMachineResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }


}
