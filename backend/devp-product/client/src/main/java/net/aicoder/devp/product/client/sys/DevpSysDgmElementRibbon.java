package net.aicoder.devp.product.client.sys;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.product.client.sys.result.DevpSysDgmElementPageResult;
import net.aicoder.devp.product.client.sys.result.DevpSysDgmElementResult;
import net.aicoder.devp.product.business.sys.dto.DevpSysDgmElementAddDto;
import net.aicoder.devp.product.business.sys.dto.DevpSysDgmElementCondition;
import net.aicoder.devp.product.business.sys.dto.DevpSysDgmElementEditDto;
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
 * UML图包含元素客户端
 * @author icode
 */
@Service
public class DevpSysDgmElementRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysDgmElementRibbon.class);

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
     * 新增UML图包含元素
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public DevpSysDgmElementResult add(DevpSysDgmElementAddDto addDto) {
        String url = "http://"+host+"/sys/devpSysDgmElement";
        return restTemplate.postForObject(url, addDto, DevpSysDgmElementResult.class);
    }
    private DevpSysDgmElementResult addFail(DevpSysDgmElementAddDto addDto) {
        DevpSysDgmElementResult result = new DevpSysDgmElementResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 删除UML图包含元素
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public DevpSysDgmElementResult delete(Long id) {
        String url = "http://"+host+"/sys/devpSysDgmElement/"+id;
        ResponseEntity<DevpSysDgmElementResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<DevpSysDgmElementResult>() {});
        return response.getBody();
    }
    private DevpSysDgmElementResult deleteFail(Long id) {
        DevpSysDgmElementResult result = new DevpSysDgmElementResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 更新UML图包含元素
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public DevpSysDgmElementResult update(Long id, DevpSysDgmElementEditDto editDto) {
        String url = "http://"+host+"/sys/devpSysDgmElement/"+id;
        ResponseEntity<DevpSysDgmElementResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<DevpSysDgmElementResult>() {});
        return response.getBody();
    }

    public DevpSysDgmElementResult updateFail(Long id, DevpSysDgmElementEditDto updateRequest) {
        DevpSysDgmElementResult result = new DevpSysDgmElementResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }



    /**
	 * 根据ID查询UML图包含元素
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public DevpSysDgmElementResult get(Long id) {
        String url = "http://"+host+"/sys/devpSysDgmElement/"+id;
        return restTemplate.getForObject(url, DevpSysDgmElementResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private DevpSysDgmElementResult getFail(Long id) {
        DevpSysDgmElementResult result = new DevpSysDgmElementResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 查询UML图包含元素列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public DevpSysDgmElementPageResult list(PageSearchRequest<DevpSysDgmElementCondition> pageSearchRequest) {
        String url = "http://"+host+"/sys/devpSysDgmElement/list";
        return restTemplate.postForObject(url, pageSearchRequest, DevpSysDgmElementPageResult.class);
    }
    public DevpSysDgmElementPageResult listFail(PageSearchRequest<DevpSysDgmElementCondition> pageSearchRequest) {
        DevpSysDgmElementPageResult result = new DevpSysDgmElementPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }



}
