package net.aicoder.speedcloud.client.env;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.controller.RestResponse;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.env.dto.AppEnvConfigAddDto;
import net.aicoder.speedcloud.business.env.dto.AppEnvConfigCondition;
import net.aicoder.speedcloud.business.env.dto.AppEnvConfigEditDto;
import net.aicoder.speedcloud.business.env.vo.AppEnvConfigVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;


/**
 * 产品环境客户端
 * @author icode
 */
@FeignClient(name = "SPEEDCLOUD-SPEEDCLOUD-MICROSERVICE", path = "/speedcloud/env/appenvconfig", decode404 = true)
//@FeignClient(name = "SPEEDCLOUD-SPEEDCLOUD-MICROSERVICE", path = "/speedcloud/env/appenvconfig", decode404 = true, configuration = {OAuth2FeignAutoConfiguration.class})
public interface AppEnvConfigClient {


    /**
     * 新增产品环境
     * @param addDto
     * @return
     */
    @PostMapping
	RestResponse<AppEnvConfigVO> add(@RequestBody AppEnvConfigAddDto addDto);


    /**
    * 删除产品环境
    * @param id
    */
    @DeleteMapping("/{id}")
    RestResponse<AppEnvConfigVO> delete(@PathVariable("id") String id);

    /**
    * 更新产品环境
    * @param id
    * @param editDto
    * @return
    */
    @PutMapping("/{id}")
    RestResponse<AppEnvConfigVO> update(@PathVariable("id") String id, @RequestBody AppEnvConfigEditDto editDto);

    /**
    * 根据ID查询产品环境
    * @param id
    * @return
    */
    @GetMapping("/{id}")
    RestResponse<AppEnvConfigVO> get(@PathVariable("id") String id);

    /**
    * 查询产品环境列表
    * @param pageSearchRequest
    * @return
    */
    @PostMapping("/list")
    RestResponse<PageContent<AppEnvConfigVO>> list(@RequestBody PageSearchRequest<AppEnvConfigCondition> pageSearchRequest);

}
