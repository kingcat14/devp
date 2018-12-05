package net.aicoder.speedcloud.client.app;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.controller.RestResponse;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.speedcloud.business.app.dto.CodeRepositoryAddDto;
import net.aicoder.speedcloud.business.app.dto.CodeRepositoryCondition;
import net.aicoder.speedcloud.business.app.dto.CodeRepositoryEditDto;
import net.aicoder.speedcloud.business.app.vo.CodeRepositoryVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;


/**
 * 代码库客户端
 * @author icode
 */
@FeignClient(name = "SPEEDCLOUD-SPEEDCLOUD-MICROSERVICE", path = "/speedcloud/app/coderepository", decode404 = true)
//@FeignClient(name = "SPEEDCLOUD-SPEEDCLOUD-MICROSERVICE", path = "/speedcloud/app/coderepository", decode404 = true, configuration = {OAuth2FeignAutoConfiguration.class})
public interface CodeRepositoryClient {


    /**
     * 新增代码库
     * @param addDto
     * @return
     */
    @PostMapping
	RestResponse<CodeRepositoryVO> add(@RequestBody CodeRepositoryAddDto addDto);


    /**
    * 删除代码库
    * @param id
    */
    @DeleteMapping("/{id}")
    RestResponse<CodeRepositoryVO> delete(@PathVariable("id") String id);

    /**
    * 更新代码库
    * @param id
    * @param editDto
    * @return
    */
    @PutMapping("/{id}")
    RestResponse<CodeRepositoryVO> update(@PathVariable("id") String id, @RequestBody CodeRepositoryEditDto editDto);

    /**
    * 根据ID查询代码库
    * @param id
    * @return
    */
    @GetMapping("/{id}")
    RestResponse<CodeRepositoryVO> get(@PathVariable("id") String id);

    /**
    * 查询代码库列表
    * @param pageSearchRequest
    * @return
    */
    @PostMapping("/list")
    RestResponse<PageContent<CodeRepositoryVO>> list(@RequestBody PageSearchRequest<CodeRepositoryCondition> pageSearchRequest);

}
