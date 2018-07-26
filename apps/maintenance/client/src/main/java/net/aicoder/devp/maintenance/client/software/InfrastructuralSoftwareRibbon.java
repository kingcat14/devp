package net.aicoder.devp.maintenance.client.software;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.maintenance.business.software.dto.InfrastructuralSoftwareAddDto;
import net.aicoder.devp.maintenance.business.software.dto.InfrastructuralSoftwareCondition;
import net.aicoder.devp.maintenance.business.software.dto.InfrastructuralSoftwareEditDto;
import net.aicoder.devp.maintenance.client.software.result.InfrastructuralSoftwarePageResult;
import net.aicoder.devp.maintenance.client.software.result.InfrastructuralSoftwareResult;
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
 * 基础软件客户端
 * @author icode
 */
@Service
public class InfrastructuralSoftwareRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(InfrastructuralSoftwareRibbon.class);

    private String host = "DEPLOY";
    private static final String E_TYPE = "ASSET_INFRA_SW";

	@Autowired(required = false)
    private RestTemplate restTemplate;

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public void setHost(String host) {
        this.host = host;
    }


    /**
     * 新增基础软件
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public InfrastructuralSoftwareResult add(InfrastructuralSoftwareAddDto addDto) {
        addDto.setEtype(E_TYPE);
        String url = "http://"+host+"//ops/devpOpsAssetCmdb";
        return restTemplate.postForObject(url, addDto, InfrastructuralSoftwareResult.class);
    }
    private InfrastructuralSoftwareResult addFail(InfrastructuralSoftwareAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        InfrastructuralSoftwareResult result = new InfrastructuralSoftwareResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 删除基础软件
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public InfrastructuralSoftwareResult delete(Long id) {
        String url = "http://"+host+"//ops/devpOpsAssetCmdb/"+id;
        ResponseEntity<InfrastructuralSoftwareResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<InfrastructuralSoftwareResult>() {});
        return response.getBody();
    }
    private InfrastructuralSoftwareResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        InfrastructuralSoftwareResult result = new InfrastructuralSoftwareResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 更新基础软件
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public InfrastructuralSoftwareResult update(Long id, InfrastructuralSoftwareEditDto editDto) {
        editDto.setEtype(E_TYPE);
        String url = "http://"+host+"//ops/devpOpsAssetCmdb/"+id;
        ResponseEntity<InfrastructuralSoftwareResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<InfrastructuralSoftwareResult>() {});
        return response.getBody();
    }

    public InfrastructuralSoftwareResult updateFail(Long id, InfrastructuralSoftwareEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        InfrastructuralSoftwareResult result = new InfrastructuralSoftwareResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }



    /**
	 * 根据ID查询基础软件
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public InfrastructuralSoftwareResult get(Long id) {
        String url = "http://"+host+"//ops/devpOpsAssetCmdb/"+id;
        return restTemplate.getForObject(url, InfrastructuralSoftwareResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private InfrastructuralSoftwareResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        InfrastructuralSoftwareResult result = new InfrastructuralSoftwareResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 查询基础软件列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public InfrastructuralSoftwarePageResult list(PageSearchRequest<InfrastructuralSoftwareCondition> pageSearchRequest) {

        if(pageSearchRequest.getSearchCondition()==null){
            pageSearchRequest.setSearchCondition(new InfrastructuralSoftwareCondition());
        }
        pageSearchRequest.getSearchCondition().setEtype(E_TYPE);
        
        String url = "http://"+host+"//ops/devpOpsAssetCmdb/list";
        return restTemplate.postForObject(url, pageSearchRequest, InfrastructuralSoftwarePageResult.class);
    }
    public InfrastructuralSoftwarePageResult listFail(PageSearchRequest<InfrastructuralSoftwareCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        InfrastructuralSoftwarePageResult result = new InfrastructuralSoftwarePageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }



}
