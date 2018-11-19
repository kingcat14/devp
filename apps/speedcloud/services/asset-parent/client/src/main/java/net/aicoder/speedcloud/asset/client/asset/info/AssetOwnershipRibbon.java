package net.aicoder.speedcloud.asset.client.asset.info;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.asset.business.asset.info.dto.AssetOwnershipAddDto;
import net.aicoder.speedcloud.asset.business.asset.info.dto.AssetOwnershipCondition;
import net.aicoder.speedcloud.asset.business.asset.info.dto.AssetOwnershipEditDto;
import net.aicoder.speedcloud.asset.client.asset.info.result.AssetOwnershipPageResult;
import net.aicoder.speedcloud.asset.client.asset.info.result.AssetOwnershipResult;
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
 * IT资产归属客户端
 * @author icode
 */
@Service
public class AssetOwnershipRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(AssetOwnershipRibbon.class);

    private String host = "SPEEDCLOUD-ASSET-MICROSERVICE";

	@Autowired(required = false)
    private RestTemplate restTemplate;

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public void setHost(String host) {
        this.host = host;
    }


    /**
     * 新增IT资产归属
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public AssetOwnershipResult add(AssetOwnershipAddDto addDto) {
        String url = "http://"+host+"/asset/asset/info/assetownership";
        return restTemplate.postForObject(url, addDto, AssetOwnershipResult.class);
    }
    private AssetOwnershipResult addFail(AssetOwnershipAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除IT资产归属
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public AssetOwnershipResult delete(Long id) {
        String url = "http://"+host+"/asset/asset/info/assetownership/"+id;
        ResponseEntity<AssetOwnershipResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<AssetOwnershipResult>() {});
        return response.getBody();
    }
    private AssetOwnershipResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 更新IT资产归属
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public AssetOwnershipResult update(Long id, AssetOwnershipEditDto editDto) {
        String url = "http://"+host+"/asset/asset/info/assetownership/"+id;
        ResponseEntity<AssetOwnershipResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<AssetOwnershipResult>() {});
        return response.getBody();
    }

    public AssetOwnershipResult updateFail(Long id, AssetOwnershipEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询IT资产归属
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public AssetOwnershipResult get(Long id) {
        String url = "http://"+host+"/asset/asset/info/assetownership/"+id;
        return restTemplate.getForObject(url, AssetOwnershipResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private AssetOwnershipResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询IT资产归属列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public AssetOwnershipPageResult list(PageSearchRequest<AssetOwnershipCondition> pageSearchRequest) {
        String url = "http://"+host+"/asset/asset/info/assetownership/list";
        return restTemplate.postForObject(url, pageSearchRequest, AssetOwnershipPageResult.class);
    }
    public AssetOwnershipPageResult listFail(PageSearchRequest<AssetOwnershipCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        AssetOwnershipPageResult result = new AssetOwnershipPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private AssetOwnershipResult errorResult(){
        AssetOwnershipResult result = new AssetOwnershipResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }


}
