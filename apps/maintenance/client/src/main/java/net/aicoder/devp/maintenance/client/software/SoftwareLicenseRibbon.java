package net.aicoder.devp.maintenance.client.software;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.maintenance.business.software.dto.SoftwareLicenseAddDto;
import net.aicoder.devp.maintenance.business.software.dto.SoftwareLicenseCondition;
import net.aicoder.devp.maintenance.business.software.dto.SoftwareLicenseEditDto;
import net.aicoder.devp.maintenance.client.software.result.SoftwareLicensePageResult;
import net.aicoder.devp.maintenance.client.software.result.SoftwareLicenseResult;
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
 * 服务许可客户端
 * @author icode
 */
@Service
public class SoftwareLicenseRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(SoftwareLicenseRibbon.class);

    private String host = "DEPLOY";
    private static final String E_TYPE = "ASSET_SVC_LIC";

	@Autowired(required = false)
    private RestTemplate restTemplate;

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public void setHost(String host) {
        this.host = host;
    }


    /**
     * 新增服务许可
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public SoftwareLicenseResult add(SoftwareLicenseAddDto addDto) {
        addDto.setEtype(E_TYPE);
        String url = "http://"+host+"//ops/devpOpsAssetCmdb";
        return restTemplate.postForObject(url, addDto, SoftwareLicenseResult.class);
    }
    private SoftwareLicenseResult addFail(SoftwareLicenseAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        SoftwareLicenseResult result = new SoftwareLicenseResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 删除服务许可
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public SoftwareLicenseResult delete(Long id) {
        String url = "http://"+host+"//ops/devpOpsAssetCmdb/"+id;
        ResponseEntity<SoftwareLicenseResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<SoftwareLicenseResult>() {});
        return response.getBody();
    }
    private SoftwareLicenseResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        SoftwareLicenseResult result = new SoftwareLicenseResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 更新服务许可
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public SoftwareLicenseResult update(Long id, SoftwareLicenseEditDto editDto) {
        editDto.setEtype(E_TYPE);
        String url = "http://"+host+"//ops/devpOpsAssetCmdb/"+id;
        ResponseEntity<SoftwareLicenseResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<SoftwareLicenseResult>() {});
        return response.getBody();
    }

    public SoftwareLicenseResult updateFail(Long id, SoftwareLicenseEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        SoftwareLicenseResult result = new SoftwareLicenseResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }



    /**
	 * 根据ID查询服务许可
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public SoftwareLicenseResult get(Long id) {
        String url = "http://"+host+"//ops/devpOpsAssetCmdb/"+id;
        return restTemplate.getForObject(url, SoftwareLicenseResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private SoftwareLicenseResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        SoftwareLicenseResult result = new SoftwareLicenseResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 查询服务许可列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public SoftwareLicensePageResult list(PageSearchRequest<SoftwareLicenseCondition> pageSearchRequest) {

        if(pageSearchRequest.getSearchCondition()==null){
            pageSearchRequest.setSearchCondition(new SoftwareLicenseCondition());
        }
        pageSearchRequest.getSearchCondition().setEtype(E_TYPE);

        String url = "http://"+host+"//ops/devpOpsAssetCmdb/list";
        return restTemplate.postForObject(url, pageSearchRequest, SoftwareLicensePageResult.class);
    }
    public SoftwareLicensePageResult listFail(PageSearchRequest<SoftwareLicenseCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        SoftwareLicensePageResult result = new SoftwareLicensePageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }



}
