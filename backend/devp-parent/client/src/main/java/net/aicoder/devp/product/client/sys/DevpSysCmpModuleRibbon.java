package net.aicoder.devp.product.client.sys;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.product.business.sys.dto.DevpSysCmpModuleAddDto;
import net.aicoder.devp.product.business.sys.dto.DevpSysCmpModuleCondition;
import net.aicoder.devp.product.business.sys.dto.DevpSysCmpModuleEditDto;
import net.aicoder.devp.product.client.sys.result.DevpSysCmpModulePageResult;
import net.aicoder.devp.product.client.sys.result.DevpSysCmpModuleResult;
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
 * 组件对应模块客户端
 * @author icode
 */
@Service
public class DevpSysCmpModuleRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysCmpModuleRibbon.class);

    private String host = "PRODUCT";

	@Autowired(required = false)
    private RestTemplate restTemplate;

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public void setHost(String host) {
        this.host = host;
    }


    /**
     * 新增组件对应模块
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public DevpSysCmpModuleResult add(DevpSysCmpModuleAddDto addDto) {
        String url = "http://"+host+"/sys/devpSysCmpModule";
        return restTemplate.postForObject(url, addDto, DevpSysCmpModuleResult.class);
    }
    private DevpSysCmpModuleResult addFail(DevpSysCmpModuleAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpSysCmpModuleResult result = new DevpSysCmpModuleResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 删除组件对应模块
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public DevpSysCmpModuleResult delete(Long id) {
        String url = "http://"+host+"/sys/devpSysCmpModule/"+id;
        ResponseEntity<DevpSysCmpModuleResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<DevpSysCmpModuleResult>() {});
        return response.getBody();
    }
    private DevpSysCmpModuleResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpSysCmpModuleResult result = new DevpSysCmpModuleResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 更新组件对应模块
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public DevpSysCmpModuleResult update(Long id, DevpSysCmpModuleEditDto editDto) {
        String url = "http://"+host+"/sys/devpSysCmpModule/"+id;
        ResponseEntity<DevpSysCmpModuleResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<DevpSysCmpModuleResult>() {});
        return response.getBody();
    }

    public DevpSysCmpModuleResult updateFail(Long id, DevpSysCmpModuleEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpSysCmpModuleResult result = new DevpSysCmpModuleResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }



    /**
	 * 根据ID查询组件对应模块
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public DevpSysCmpModuleResult get(Long id) {
        String url = "http://"+host+"/sys/devpSysCmpModule/"+id;
        return restTemplate.getForObject(url, DevpSysCmpModuleResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private DevpSysCmpModuleResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpSysCmpModuleResult result = new DevpSysCmpModuleResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 查询组件对应模块列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public DevpSysCmpModulePageResult list(PageSearchRequest<DevpSysCmpModuleCondition> pageSearchRequest) {
        String url = "http://"+host+"/sys/devpSysCmpModule/list";
        return restTemplate.postForObject(url, pageSearchRequest, DevpSysCmpModulePageResult.class);
    }
    public DevpSysCmpModulePageResult listFail(PageSearchRequest<DevpSysCmpModuleCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        DevpSysCmpModulePageResult result = new DevpSysCmpModulePageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }



}
