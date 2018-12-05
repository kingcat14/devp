package net.aicoder.speedcloud.client.app;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.controller.RestResponse;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.app.dto.SecurityConfigAddDto;
import net.aicoder.speedcloud.business.app.dto.SecurityConfigCondition;
import net.aicoder.speedcloud.business.app.dto.SecurityConfigEditDto;
import net.aicoder.speedcloud.business.app.vo.SecurityConfigVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;


/**
 * 应用私密配置客户端
 * @author icode
 */
@FeignClient(name = "SPEEDCLOUD-SPEEDCLOUD-MICROSERVICE", path = "/speedcloud/app/securityconfig", decode404 = true)
//@FeignClient(name = "SPEEDCLOUD-SPEEDCLOUD-MICROSERVICE", path = "/speedcloud/app/securityconfig", decode404 = true, configuration = {OAuth2FeignAutoConfiguration.class})
public interface SecurityConfigClient {


    /**
     * 新增应用私密配置
     * @param addDto
     * @return
     */
    @PostMapping
	RestResponse<SecurityConfigVO> add(@RequestBody SecurityConfigAddDto addDto);


    /**
    * 删除应用私密配置
    * @param id
    */
    @DeleteMapping("/{id}")
    RestResponse<SecurityConfigVO> delete(@PathVariable("id") String id);

    /**
    * 更新应用私密配置
    * @param id
    * @param editDto
    * @return
    */
    @PutMapping("/{id}")
    RestResponse<SecurityConfigVO> update(@PathVariable("id") String id, @RequestBody SecurityConfigEditDto editDto);

    /**
    * 根据ID查询应用私密配置
    * @param id
    * @return
    */
    @GetMapping("/{id}")
    RestResponse<SecurityConfigVO> get(@PathVariable("id") String id);

    /**
    * 查询应用私密配置列表
    * @param pageSearchRequest
    * @return
    */
    @PostMapping("/list")
    RestResponse<PageContent<SecurityConfigVO>> list(@RequestBody PageSearchRequest<SecurityConfigCondition> pageSearchRequest);

}
