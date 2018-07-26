package net.aicoder.devp.maintenance.client.software;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.maintenance.business.software.dto.BusinessSoftwareAddDto;
import net.aicoder.devp.maintenance.business.software.dto.BusinessSoftwareCondition;
import net.aicoder.devp.maintenance.business.software.dto.BusinessSoftwareEditDto;
import net.aicoder.devp.maintenance.client.software.result.BusinessSoftwarePageResult;
import net.aicoder.devp.maintenance.client.software.result.BusinessSoftwareResult;
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
 * 应用软件客户端
 * @author icode
 */
@Service
public class BusinessSoftwareRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(BusinessSoftwareRibbon.class);

    private String host = "DEPLOY";
    private static final String E_TYPE = "ASSET_BIZ_SW";

	@Autowired(required = false)
    private RestTemplate restTemplate;

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public void setHost(String host) {
        this.host = host;
    }


    /**
     * 新增应用软件
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public BusinessSoftwareResult add(BusinessSoftwareAddDto addDto) {
        addDto.setEtype(E_TYPE);
        String url = "http://"+host+"//ops/devpOpsAssetCmdb";
        return restTemplate.postForObject(url, addDto, BusinessSoftwareResult.class);
    }
    private BusinessSoftwareResult addFail(BusinessSoftwareAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        BusinessSoftwareResult result = new BusinessSoftwareResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 删除应用软件
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public BusinessSoftwareResult delete(Long id) {
        String url = "http://"+host+"//ops/devpOpsAssetCmdb/"+id;
        ResponseEntity<BusinessSoftwareResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<BusinessSoftwareResult>() {});
        return response.getBody();
    }
    private BusinessSoftwareResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        BusinessSoftwareResult result = new BusinessSoftwareResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 更新应用软件
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public BusinessSoftwareResult update(Long id, BusinessSoftwareEditDto editDto) {
        editDto.setEtype(E_TYPE);
        String url = "http://"+host+"//ops/devpOpsAssetCmdb/"+id;
        ResponseEntity<BusinessSoftwareResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<BusinessSoftwareResult>() {});
        return response.getBody();
    }

    public BusinessSoftwareResult updateFail(Long id, BusinessSoftwareEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        BusinessSoftwareResult result = new BusinessSoftwareResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }



    /**
	 * 根据ID查询应用软件
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public BusinessSoftwareResult get(Long id) {
        String url = "http://"+host+"//ops/devpOpsAssetCmdb/"+id;
        return restTemplate.getForObject(url, BusinessSoftwareResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private BusinessSoftwareResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        BusinessSoftwareResult result = new BusinessSoftwareResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 查询应用软件列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public BusinessSoftwarePageResult list(PageSearchRequest<BusinessSoftwareCondition> pageSearchRequest) {

        if(pageSearchRequest.getSearchCondition()==null){
            pageSearchRequest.setSearchCondition(new BusinessSoftwareCondition());
        }
        pageSearchRequest.getSearchCondition().setEtype(E_TYPE);
	    
	    String url = "http://"+host+"//ops/devpOpsAssetCmdb/list";
        return restTemplate.postForObject(url, pageSearchRequest, BusinessSoftwarePageResult.class);
    }
    public BusinessSoftwarePageResult listFail(PageSearchRequest<BusinessSoftwareCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        BusinessSoftwarePageResult result = new BusinessSoftwarePageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }



}
