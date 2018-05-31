package net.aicoder.devp.product.client.sys;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.product.client.sys.result.DevpSysElmInstInfoPageResult;
import net.aicoder.devp.product.client.sys.result.DevpSysElmInstInfoResult;
import net.aicoder.devp.product.business.sys.dto.DevpSysElmInstInfoAddDto;
import net.aicoder.devp.product.business.sys.dto.DevpSysElmInstInfoCondition;
import net.aicoder.devp.product.business.sys.dto.DevpSysElmInstInfoEditDto;
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
 * 系统元素实例客户端
 * @author icode
 */
@Service
public class DevpSysElmInstInfoRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysElmInstInfoRibbon.class);

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
     * 新增系统元素实例
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public DevpSysElmInstInfoResult add(DevpSysElmInstInfoAddDto addDto) {
        String url = "http://"+host+"/sys/devpSysElmInstInfo";
        return restTemplate.postForObject(url, addDto, DevpSysElmInstInfoResult.class);
    }
    private DevpSysElmInstInfoResult addFail(DevpSysElmInstInfoAddDto addDto) {
        DevpSysElmInstInfoResult result = new DevpSysElmInstInfoResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 删除系统元素实例
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public DevpSysElmInstInfoResult delete(Long id) {
        String url = "http://"+host+"/sys/devpSysElmInstInfo/"+id;
        ResponseEntity<DevpSysElmInstInfoResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<DevpSysElmInstInfoResult>() {});
        return response.getBody();
    }
    private DevpSysElmInstInfoResult deleteFail(Long id) {
        DevpSysElmInstInfoResult result = new DevpSysElmInstInfoResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 更新系统元素实例
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public DevpSysElmInstInfoResult update(Long id, DevpSysElmInstInfoEditDto editDto) {
        String url = "http://"+host+"/sys/devpSysElmInstInfo/"+id;
        ResponseEntity<DevpSysElmInstInfoResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<DevpSysElmInstInfoResult>() {});
        return response.getBody();
    }

    public DevpSysElmInstInfoResult updateFail(Long id, DevpSysElmInstInfoEditDto updateRequest) {
        DevpSysElmInstInfoResult result = new DevpSysElmInstInfoResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }



    /**
	 * 根据ID查询系统元素实例
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public DevpSysElmInstInfoResult get(Long id) {
        String url = "http://"+host+"/sys/devpSysElmInstInfo/"+id;
        return restTemplate.getForObject(url, DevpSysElmInstInfoResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private DevpSysElmInstInfoResult getFail(Long id) {
        DevpSysElmInstInfoResult result = new DevpSysElmInstInfoResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 查询系统元素实例列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public DevpSysElmInstInfoPageResult list(PageSearchRequest<DevpSysElmInstInfoCondition> pageSearchRequest) {
        String url = "http://"+host+"/sys/devpSysElmInstInfo/list";
        return restTemplate.postForObject(url, pageSearchRequest, DevpSysElmInstInfoPageResult.class);
    }
    public DevpSysElmInstInfoPageResult listFail(PageSearchRequest<DevpSysElmInstInfoCondition> pageSearchRequest) {
        DevpSysElmInstInfoPageResult result = new DevpSysElmInstInfoPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }



}
