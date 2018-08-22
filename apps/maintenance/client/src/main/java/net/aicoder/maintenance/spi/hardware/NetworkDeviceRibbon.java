package net.aicoder.maintenance.spi.hardware;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;

import net.aicoder.maintenance.business.hardware.dto.NetworkDeviceAddDto;
import net.aicoder.maintenance.business.hardware.dto.NetworkDeviceCondition;
import net.aicoder.maintenance.business.hardware.dto.NetworkDeviceEditDto;
import net.aicoder.maintenance.spi.hardware.result.NetworkDevicePageResult;
import net.aicoder.maintenance.spi.hardware.result.NetworkDeviceResult;

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
 * 网络设备客户端
 * @author icode
 */
@Service
public class NetworkDeviceRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(NetworkDeviceRibbon.class);

    private String host = "DEVP";
    private static final String E_TYPE = "ASSET_NETWORK";

	@Autowired(required = false)
    private RestTemplate restTemplate;

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public void setHost(String host) {
        this.host = host;
    }


    /**
     * 新增网络设备
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public NetworkDeviceResult add(NetworkDeviceAddDto addDto) {
        addDto.setEtype(E_TYPE);
        String url = "http://"+host+"/ops/devpOpsAssetCmdb";
        return restTemplate.postForObject(url, addDto, NetworkDeviceResult.class);
    }
    private NetworkDeviceResult addFail(NetworkDeviceAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        NetworkDeviceResult result = new NetworkDeviceResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 删除网络设备
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public NetworkDeviceResult delete(Long id) {
        String url = "http://"+host+"/ops/devpOpsAssetCmdb/"+id;
        ResponseEntity<NetworkDeviceResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<NetworkDeviceResult>() {});
        return response.getBody();
    }
    private NetworkDeviceResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        NetworkDeviceResult result = new NetworkDeviceResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 更新网络设备
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public NetworkDeviceResult update(Long id, NetworkDeviceEditDto editDto) {

        editDto.setEtype(E_TYPE);
        String url = "http://"+host+"/ops/devpOpsAssetCmdb/"+id;
        ResponseEntity<NetworkDeviceResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<NetworkDeviceResult>() {});
        return response.getBody();
    }

    public NetworkDeviceResult updateFail(Long id, NetworkDeviceEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        NetworkDeviceResult result = new NetworkDeviceResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }



    /**
	 * 根据ID查询网络设备
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public NetworkDeviceResult get(Long id) {
        String url = "http://"+host+"/ops/devpOpsAssetCmdb/"+id;
        return restTemplate.getForObject(url, NetworkDeviceResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private NetworkDeviceResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        NetworkDeviceResult result = new NetworkDeviceResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 查询网络设备列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public NetworkDevicePageResult list(PageSearchRequest<NetworkDeviceCondition> pageSearchRequest) {

        if(pageSearchRequest.getSearchCondition()==null){
            pageSearchRequest.setSearchCondition(new NetworkDeviceCondition());
        }
        pageSearchRequest.getSearchCondition().setEtype(E_TYPE);

	    String url = "http://"+host+"/ops/devpOpsAssetCmdb/list";
        return restTemplate.postForObject(url, pageSearchRequest, NetworkDevicePageResult.class);
    }
    public NetworkDevicePageResult listFail(PageSearchRequest<NetworkDeviceCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        NetworkDevicePageResult result = new NetworkDevicePageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }



}
