package net.aicoder.devp.product.client.sys;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.product.client.sys.result.DevpSysDiagramPageResult;
import net.aicoder.devp.product.client.sys.result.DevpSysDiagramResult;
import net.aicoder.devp.product.business.sys.dto.DevpSysDiagramAddDto;
import net.aicoder.devp.product.business.sys.dto.DevpSysDiagramCondition;
import net.aicoder.devp.product.business.sys.dto.DevpSysDiagramEditDto;
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
 * UML图客户端
 * @author icode
 */
@Service
public class DevpSysDiagramRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysDiagramRibbon.class);

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
     * 新增UML图
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public DevpSysDiagramResult add(DevpSysDiagramAddDto addDto) {
        String url = "http://"+host+"/sys/devpSysDiagram";
        return restTemplate.postForObject(url, addDto, DevpSysDiagramResult.class);
    }
    private DevpSysDiagramResult addFail(DevpSysDiagramAddDto addDto) {
        DevpSysDiagramResult result = new DevpSysDiagramResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 删除UML图
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public DevpSysDiagramResult delete(Long id) {
        String url = "http://"+host+"/sys/devpSysDiagram/"+id;
        ResponseEntity<DevpSysDiagramResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<DevpSysDiagramResult>() {});
        return response.getBody();
    }
    private DevpSysDiagramResult deleteFail(Long id) {
        DevpSysDiagramResult result = new DevpSysDiagramResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 更新UML图
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public DevpSysDiagramResult update(Long id, DevpSysDiagramEditDto editDto) {
        String url = "http://"+host+"/sys/devpSysDiagram/"+id;
        ResponseEntity<DevpSysDiagramResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<DevpSysDiagramResult>() {});
        return response.getBody();
    }

    public DevpSysDiagramResult updateFail(Long id, DevpSysDiagramEditDto updateRequest) {
        DevpSysDiagramResult result = new DevpSysDiagramResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }



    /**
	 * 根据ID查询UML图
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public DevpSysDiagramResult get(Long id) {
        String url = "http://"+host+"/sys/devpSysDiagram/"+id;
        return restTemplate.getForObject(url, DevpSysDiagramResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private DevpSysDiagramResult getFail(Long id) {
        DevpSysDiagramResult result = new DevpSysDiagramResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 查询UML图列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public DevpSysDiagramPageResult list(PageSearchRequest<DevpSysDiagramCondition> pageSearchRequest) {
        String url = "http://"+host+"/sys/devpSysDiagram/list";
        return restTemplate.postForObject(url, pageSearchRequest, DevpSysDiagramPageResult.class);
    }
    public DevpSysDiagramPageResult listFail(PageSearchRequest<DevpSysDiagramCondition> pageSearchRequest) {
        DevpSysDiagramPageResult result = new DevpSysDiagramPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }



}
