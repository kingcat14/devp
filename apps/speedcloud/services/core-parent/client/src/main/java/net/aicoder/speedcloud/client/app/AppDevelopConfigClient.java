package net.aicoder.speedcloud.client.app;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.controller.RestResponse;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.app.dto.AppDevelopConfigAddDto;
import net.aicoder.speedcloud.business.app.dto.AppDevelopConfigCondition;
import net.aicoder.speedcloud.business.app.dto.AppDevelopConfigEditDto;
import net.aicoder.speedcloud.business.app.vo.AppDevelopConfigVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;


/**
 * 应用开发配置客户端
 * @author icode
 */
@FeignClient(name = "SPEEDCLOUD-SPEEDCLOUD-MICROSERVICE", path = "/speedcloud/app/appdevelopconfig", decode404 = true)
//@FeignClient(name = "SPEEDCLOUD-SPEEDCLOUD-MICROSERVICE", path = "/speedcloud/app/appdevelopconfig", decode404 = true, configuration = {OAuth2FeignAutoConfiguration.class})
public interface AppDevelopConfigClient {


    /**
     * 新增应用开发配置
     * @param addDto
     * @return
     */
    @PostMapping
	RestResponse<AppDevelopConfigVO> add(@RequestBody AppDevelopConfigAddDto addDto);


    /**
    * 删除应用开发配置
    * @param id
    */
    @DeleteMapping("/{id}")
    RestResponse<AppDevelopConfigVO> delete(@PathVariable("id") String id);

    /**
    * 更新应用开发配置
    * @param id
    * @param editDto
    * @return
    */
    @PutMapping("/{id}")
    RestResponse<AppDevelopConfigVO> update(@PathVariable("id") String id, @RequestBody AppDevelopConfigEditDto editDto);

    /**
    * 根据ID查询应用开发配置
    * @param id
    * @return
    */
    @GetMapping("/{id}")
    RestResponse<AppDevelopConfigVO> get(@PathVariable("id") String id);

    /**
    * 查询应用开发配置列表
    * @param pageSearchRequest
    * @return
    */
    @PostMapping("/list")
    RestResponse<PageContent<AppDevelopConfigVO>> list(@RequestBody PageSearchRequest<AppDevelopConfigCondition> pageSearchRequest);

}
