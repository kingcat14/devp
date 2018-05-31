package net.aicoder.devp.product.client.sys;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.product.client.sys.result.DevpSysElementPageResult;
import net.aicoder.devp.product.client.sys.result.DevpSysElementResult;
import net.aicoder.devp.product.business.sys.dto.DevpSysElementAddDto;
import net.aicoder.devp.product.business.sys.dto.DevpSysElementCondition;
import net.aicoder.devp.product.business.sys.dto.DevpSysElementEditDto;
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
 * 系统元素客户端
 * @author icode
 */
@Service
public class DevpSysElementRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysElementRibbon.class);

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
     * 新增系统元素
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public DevpSysElementResult add(DevpSysElementAddDto addDto) {
        String url = "http://"+host+"/sys/devpSysElement";
        return restTemplate.postForObject(url, addDto, DevpSysElementResult.class);
    }
    private DevpSysElementResult addFail(DevpSysElementAddDto addDto) {
        DevpSysElementResult result = new DevpSysElementResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 删除系统元素
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public DevpSysElementResult delete(Long id) {
        String url = "http://"+host+"/sys/devpSysElement/"+id;
        ResponseEntity<DevpSysElementResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<DevpSysElementResult>() {});
        return response.getBody();
    }
    private DevpSysElementResult deleteFail(Long id) {
        DevpSysElementResult result = new DevpSysElementResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 更新系统元素
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public DevpSysElementResult update(Long id, DevpSysElementEditDto editDto) {
        String url = "http://"+host+"/sys/devpSysElement/"+id;
        ResponseEntity<DevpSysElementResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<DevpSysElementResult>() {});
        return response.getBody();
    }

    public DevpSysElementResult updateFail(Long id, DevpSysElementEditDto updateRequest) {
        DevpSysElementResult result = new DevpSysElementResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }



    /**
	 * 根据ID查询系统元素
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public DevpSysElementResult get(Long id) {
        String url = "http://"+host+"/sys/devpSysElement/"+id;
        return restTemplate.getForObject(url, DevpSysElementResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private DevpSysElementResult getFail(Long id) {
        DevpSysElementResult result = new DevpSysElementResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 查询系统元素列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public DevpSysElementPageResult list(PageSearchRequest<DevpSysElementCondition> pageSearchRequest) {
        String url = "http://"+host+"/sys/devpSysElement/list";
        return restTemplate.postForObject(url, pageSearchRequest, DevpSysElementPageResult.class);
    }
    public DevpSysElementPageResult listFail(PageSearchRequest<DevpSysElementCondition> pageSearchRequest) {
        DevpSysElementPageResult result = new DevpSysElementPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }



}
