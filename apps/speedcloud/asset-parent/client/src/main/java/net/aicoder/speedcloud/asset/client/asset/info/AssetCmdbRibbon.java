package net.aicoder.speedcloud.asset.client.asset.info;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.asset.client.asset.info.result.AssetCmdbPageResult;
import net.aicoder.speedcloud.asset.client.asset.info.result.AssetCmdbResult;
import net.aicoder.speedcloud.asset.business.asset.info.dto.AssetCmdbAddDto;
import net.aicoder.speedcloud.asset.business.asset.info.dto.AssetCmdbCondition;
import net.aicoder.speedcloud.asset.business.asset.info.dto.AssetCmdbEditDto;
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
 * IT资产客户端
 * @author icode
 */
@Service
public class AssetCmdbRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(AssetCmdbRibbon.class);

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
     * 新增IT资产
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public AssetCmdbResult add(AssetCmdbAddDto addDto) {
        String url = "http://"+host+"/asset/asset/info/assetcmdb";
        return restTemplate.postForObject(url, addDto, AssetCmdbResult.class);
    }
    private AssetCmdbResult addFail(AssetCmdbAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 删除IT资产
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public AssetCmdbResult delete(Long id) {
        String url = "http://"+host+"/asset/asset/info/assetcmdb/"+id;
        ResponseEntity<AssetCmdbResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<AssetCmdbResult>() {});
        return response.getBody();
    }
    private AssetCmdbResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 更新IT资产
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public AssetCmdbResult update(Long id, AssetCmdbEditDto editDto) {
        String url = "http://"+host+"/asset/asset/info/assetcmdb/"+id;
        ResponseEntity<AssetCmdbResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<AssetCmdbResult>() {});
        return response.getBody();
    }

    public AssetCmdbResult updateFail(Long id, AssetCmdbEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }



    /**
	 * 根据ID查询IT资产
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public AssetCmdbResult get(Long id) {
        String url = "http://"+host+"/asset/asset/info/assetcmdb/"+id;
        return restTemplate.getForObject(url, AssetCmdbResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private AssetCmdbResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        return errorResult();
    }

	/**
	 * 查询IT资产列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public AssetCmdbPageResult list(PageSearchRequest<AssetCmdbCondition> pageSearchRequest) {
        String url = "http://"+host+"/asset/asset/info/assetcmdb/list";
        return restTemplate.postForObject(url, pageSearchRequest, AssetCmdbPageResult.class);
    }
    public AssetCmdbPageResult listFail(PageSearchRequest<AssetCmdbCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        AssetCmdbPageResult result = new AssetCmdbPageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

    private AssetCmdbResult errorResult(){
        AssetCmdbResult result = new AssetCmdbResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }


}
