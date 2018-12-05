package net.aicoder.speedcloud.client.config;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.controller.RestResponse;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.config.dto.EnvLevelAddDto;
import net.aicoder.speedcloud.business.config.dto.EnvLevelCondition;
import net.aicoder.speedcloud.business.config.dto.EnvLevelEditDto;
import net.aicoder.speedcloud.business.config.vo.EnvLevelVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;


/**
 * 环境级别客户端
 * @author icode
 */
@FeignClient(name = "SPEEDCLOUD-SPEEDCLOUD-MICROSERVICE", path = "/speedcloud/config/envlevel", decode404 = true)
//@FeignClient(name = "SPEEDCLOUD-SPEEDCLOUD-MICROSERVICE", path = "/speedcloud/config/envlevel", decode404 = true, configuration = {OAuth2FeignAutoConfiguration.class})
public interface EnvLevelClient {


    /**
     * 新增环境级别
     * @param addDto
     * @return
     */
    @PostMapping
	RestResponse<EnvLevelVO> add(@RequestBody EnvLevelAddDto addDto);


    /**
    * 删除环境级别
    * @param id
    */
    @DeleteMapping("/{id}")
    RestResponse<EnvLevelVO> delete(@PathVariable("id") String id);

    /**
    * 更新环境级别
    * @param id
    * @param editDto
    * @return
    */
    @PutMapping("/{id}")
    RestResponse<EnvLevelVO> update(@PathVariable("id") String id, @RequestBody EnvLevelEditDto editDto);

    /**
    * 根据ID查询环境级别
    * @param id
    * @return
    */
    @GetMapping("/{id}")
    RestResponse<EnvLevelVO> get(@PathVariable("id") String id);

    /**
    * 查询环境级别列表
    * @param pageSearchRequest
    * @return
    */
    @PostMapping("/list")
    RestResponse<PageContent<EnvLevelVO>> list(@RequestBody PageSearchRequest<EnvLevelCondition> pageSearchRequest);

}
