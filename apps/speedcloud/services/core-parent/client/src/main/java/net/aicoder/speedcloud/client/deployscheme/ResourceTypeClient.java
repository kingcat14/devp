package net.aicoder.speedcloud.client.deployscheme;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.controller.RestResponse;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceTypeAddDto;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceTypeCondition;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceTypeEditDto;
import net.aicoder.speedcloud.business.deployscheme.vo.ResourceTypeVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;


/**
 * 部署资源类型客户端
 * @author icode
 */
@FeignClient(name = "SPEEDCLOUD-SPEEDCLOUD-MICROSERVICE", path = "/speedcloud/deployscheme/resourcetype", decode404 = true)
//@FeignClient(name = "SPEEDCLOUD-SPEEDCLOUD-MICROSERVICE", path = "/speedcloud/deployscheme/resourcetype", decode404 = true, configuration = {OAuth2FeignAutoConfiguration.class})
public interface ResourceTypeClient {


    /**
     * 新增部署资源类型
     * @param addDto
     * @return
     */
    @PostMapping
	RestResponse<ResourceTypeVO> add(@RequestBody ResourceTypeAddDto addDto);


    /**
    * 删除部署资源类型
    * @param id
    */
    @DeleteMapping("/{id}")
    RestResponse<ResourceTypeVO> delete(@PathVariable("id") String id);

    /**
    * 更新部署资源类型
    * @param id
    * @param editDto
    * @return
    */
    @PutMapping("/{id}")
    RestResponse<ResourceTypeVO> update(@PathVariable("id") String id, @RequestBody ResourceTypeEditDto editDto);

    /**
    * 根据ID查询部署资源类型
    * @param id
    * @return
    */
    @GetMapping("/{id}")
    RestResponse<ResourceTypeVO> get(@PathVariable("id") String id);

    /**
    * 查询部署资源类型列表
    * @param pageSearchRequest
    * @return
    */
    @PostMapping("/list")
    RestResponse<PageContent<ResourceTypeVO>> list(@RequestBody PageSearchRequest<ResourceTypeCondition> pageSearchRequest);

}
