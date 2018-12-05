package net.aicoder.speedcloud.client.deployscheme;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.controller.RestResponse;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceCategoryAddDto;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceCategoryCondition;
import net.aicoder.speedcloud.business.deployscheme.dto.ResourceCategoryEditDto;
import net.aicoder.speedcloud.business.deployscheme.vo.ResourceCategoryVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;


/**
 * 部署资源类别客户端
 * @author icode
 */
@FeignClient(name = "SPEEDCLOUD-SPEEDCLOUD-MICROSERVICE", path = "/speedcloud/deployscheme/resourcecategory", decode404 = true)
//@FeignClient(name = "SPEEDCLOUD-SPEEDCLOUD-MICROSERVICE", path = "/speedcloud/deployscheme/resourcecategory", decode404 = true, configuration = {OAuth2FeignAutoConfiguration.class})
public interface ResourceCategoryClient {


    /**
     * 新增部署资源类别
     * @param addDto
     * @return
     */
    @PostMapping
	RestResponse<ResourceCategoryVO> add(@RequestBody ResourceCategoryAddDto addDto);


    /**
    * 删除部署资源类别
    * @param id
    */
    @DeleteMapping("/{id}")
    RestResponse<ResourceCategoryVO> delete(@PathVariable("id") String id);

    /**
    * 更新部署资源类别
    * @param id
    * @param editDto
    * @return
    */
    @PutMapping("/{id}")
    RestResponse<ResourceCategoryVO> update(@PathVariable("id") String id, @RequestBody ResourceCategoryEditDto editDto);

    /**
    * 根据ID查询部署资源类别
    * @param id
    * @return
    */
    @GetMapping("/{id}")
    RestResponse<ResourceCategoryVO> get(@PathVariable("id") String id);

    /**
    * 查询部署资源类别列表
    * @param pageSearchRequest
    * @return
    */
    @PostMapping("/list")
    RestResponse<PageContent<ResourceCategoryVO>> list(@RequestBody PageSearchRequest<ResourceCategoryCondition> pageSearchRequest);

}
