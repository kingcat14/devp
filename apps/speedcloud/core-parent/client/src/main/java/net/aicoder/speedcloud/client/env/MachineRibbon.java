package net.aicoder.speedcloud.client.env;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.client.env.result.MachinePageResult;
import net.aicoder.speedcloud.client.env.result.MachineResult;
import net.aicoder.speedcloud.business.env.dto.MachineAddDto;
import net.aicoder.speedcloud.business.env.dto.MachineCondition;
import net.aicoder.speedcloud.business.env.dto.MachineEditDto;
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
 * 服务器客户端
 * @author icode
 */
@Service
public class MachineRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(MachineRibbon.class);

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
     * 新增服务器
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public MachineResult add(MachineAddDto addDto) {
        String url = "http://"+host+"/speedcloud/env/machine";
        return restTemplate.postForObject(url, addDto, MachineResult.class);
    }
    private MachineResult addFail(MachineAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除服务器
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public MachineResult delete(Long id) {
        String url = "http://"+host+"/speedcloud/env/machine/"+id;
        ResponseEntity<MachineResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<MachineResult>() {});
        return response.getBody();
    }
    private MachineResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 更新服务器
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public MachineResult update(Long id, MachineEditDto editDto) {
        String url = "http://"+host+"/speedcloud/env/machine/"+id;
        ResponseEntity<MachineResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<MachineResult>() {});
        return response.getBody();
    }

    public MachineResult updateFail(Long id, MachineEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询服务器
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public MachineResult get(Long id) {
        String url = "http://"+host+"/speedcloud/env/machine/"+id;
        return restTemplate.getForObject(url, MachineResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private MachineResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询服务器列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public MachinePageResult list(PageSearchRequest<MachineCondition> pageSearchRequest) {
        String url = "http://"+host+"/speedcloud/env/machine/list";
        return restTemplate.postForObject(url, pageSearchRequest, MachinePageResult.class);
    }
    public MachinePageResult listFail(PageSearchRequest<MachineCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        MachinePageResult result = new MachinePageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private MachineResult errorResult(){
        MachineResult result = new MachineResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }


}
