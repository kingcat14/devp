package net.aicoder.devp.maintenance.client.hardware;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunkang.saas.common.framework.web.controller.RestStatus;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.maintenance.business.hardware.dto.MachineAddDto;
import net.aicoder.devp.maintenance.business.hardware.dto.MachineCondition;
import net.aicoder.devp.maintenance.business.hardware.dto.MachineEditDto;
import net.aicoder.devp.maintenance.client.hardware.result.MachinePageResult;
import net.aicoder.devp.maintenance.client.hardware.result.MachineResult;
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
 * 服务器客户端
 * @author icode
 */
@Service
public class MachineRibbon {

	private static final Logger LOGGER = LoggerFactory.getLogger(MachineRibbon.class);

    private String host = "DEPLOY";

	@Autowired(required = false)
    private RestTemplate restTemplate;

	private static final String E_TYPE = "ASSET_HOST";

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public void setHost(String host) {
        this.host = host;
    }


    /**
     * 新增服务器
     * @param addDto
     * @return
     */
    @HystrixCommand(fallbackMethod = "addFail")
    public MachineResult add(MachineAddDto addDto) {
        addDto.setEtype(E_TYPE);
        String url = "http://"+host+"/ops/devpOpsAssetCmdb";
        return restTemplate.postForObject(url, addDto, MachineResult.class);
    }
    private MachineResult addFail(MachineAddDto addDto, Throwable throwable) {

        LOGGER.error("", throwable);

        MachineResult result = new MachineResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 删除服务器
	 * @param id
	 */
    @HystrixCommand(fallbackMethod = "deleteFail")
    public MachineResult delete(Long id) {
        String url = "http://"+host+"/ops/devpOpsAssetCmdb/"+id;
        ResponseEntity<MachineResult> response =
                restTemplate.exchange(url, HttpMethod.DELETE, null, new ParameterizedTypeReference<MachineResult>() {});
        return response.getBody();
    }
    private MachineResult deleteFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        MachineResult result = new MachineResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 更新服务器
	 * @param id
	 * @param editDto
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "updateFail")
    public MachineResult update(Long id, MachineEditDto editDto) {
        editDto.setEtype(E_TYPE);
        String url = "http://"+host+"/ops/devpOpsAssetCmdb/"+id;
        ResponseEntity<MachineResult> response =
                restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(editDto), new ParameterizedTypeReference<MachineResult>() {});
        return response.getBody();
    }

    public MachineResult updateFail(Long id, MachineEditDto updateRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        MachineResult result = new MachineResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }



    /**
	 * 根据ID查询服务器
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "getFail")
    public MachineResult get(Long id) {
        String url = "http://"+host+"/ops/devpOpsAssetCmdb/"+id;
        return restTemplate.getForObject(url, MachineResult.class);
    }
    /**
     * 失败处理
     * @param id
     * @return
     */
    private MachineResult getFail(Long id, Throwable throwable) {

        LOGGER.error("", throwable);

        MachineResult result = new MachineResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }

	/**
	 * 查询服务器列表
	 * @param pageSearchRequest
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "listFail")
    public MachinePageResult list(PageSearchRequest<MachineCondition> pageSearchRequest) {
	    if(pageSearchRequest.getSearchCondition()==null){
            pageSearchRequest.setSearchCondition(new MachineCondition());
        }
	    pageSearchRequest.getSearchCondition().setEtype(E_TYPE);
        String url = "http://"+host+"/ops/devpOpsAssetCmdb/list";
        return restTemplate.postForObject(url, pageSearchRequest, MachinePageResult.class);
    }
    public MachinePageResult listFail(PageSearchRequest<MachineCondition> pageSearchRequest, Throwable throwable) {

        LOGGER.error("", throwable);

        MachinePageResult result = new MachinePageResult();
        result.setCode(RestStatus.SERVER_ERROR.ordinal());
        result.setMessage("系统繁忙，请稍后再试");
        return result;
    }



}
