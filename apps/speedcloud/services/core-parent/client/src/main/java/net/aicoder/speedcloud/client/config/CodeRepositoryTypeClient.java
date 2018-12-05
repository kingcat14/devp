package net.aicoder.speedcloud.client.config;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.controller.RestResponse;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.config.dto.CodeRepositoryTypeAddDto;
import net.aicoder.speedcloud.business.config.dto.CodeRepositoryTypeCondition;
import net.aicoder.speedcloud.business.config.dto.CodeRepositoryTypeEditDto;
import net.aicoder.speedcloud.business.config.vo.CodeRepositoryTypeVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;


/**
 * 代码库类型客户端
 * @author icode
 */
@FeignClient(name = "SPEEDCLOUD-SPEEDCLOUD-MICROSERVICE", path = "/speedcloud/config/coderepositorytype", decode404 = true)
//@FeignClient(name = "SPEEDCLOUD-SPEEDCLOUD-MICROSERVICE", path = "/speedcloud/config/coderepositorytype", decode404 = true, configuration = {OAuth2FeignAutoConfiguration.class})
public interface CodeRepositoryTypeClient {


    /**
     * 新增代码库类型
     * @param addDto
     * @return
     */
    @PostMapping
	RestResponse<CodeRepositoryTypeVO> add(@RequestBody CodeRepositoryTypeAddDto addDto);


    /**
    * 删除代码库类型
    * @param id
    */
    @DeleteMapping("/{id}")
    RestResponse<CodeRepositoryTypeVO> delete(@PathVariable("id") String id);

    /**
    * 更新代码库类型
    * @param id
    * @param editDto
    * @return
    */
    @PutMapping("/{id}")
    RestResponse<CodeRepositoryTypeVO> update(@PathVariable("id") String id, @RequestBody CodeRepositoryTypeEditDto editDto);

    /**
    * 根据ID查询代码库类型
    * @param id
    * @return
    */
    @GetMapping("/{id}")
    RestResponse<CodeRepositoryTypeVO> get(@PathVariable("id") String id);

    /**
    * 查询代码库类型列表
    * @param pageSearchRequest
    * @return
    */
    @PostMapping("/list")
    RestResponse<PageContent<CodeRepositoryTypeVO>> list(@RequestBody PageSearchRequest<CodeRepositoryTypeCondition> pageSearchRequest);

}
