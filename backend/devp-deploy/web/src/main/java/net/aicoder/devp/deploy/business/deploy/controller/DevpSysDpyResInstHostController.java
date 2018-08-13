package net.aicoder.devp.deploy.business.deploy.controller;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.common.framework.web.data.SortCondition;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.devp.deploy.business.deploy.domain.DevpSysDpyResInstHost;
import net.aicoder.devp.deploy.business.deploy.dto.DevpSysDpyResInstHostCondition;
import net.aicoder.devp.deploy.business.deploy.dto.DevpSysDpyResInstHostAddDto;
import net.aicoder.devp.deploy.business.deploy.dto.DevpSysDpyResInstHostEditDto;
import net.aicoder.devp.deploy.business.deploy.service.DevpSysDpyResInstHostService;
import net.aicoder.devp.deploy.business.deploy.valid.DevpSysDpyResInstHostValidator;
import net.aicoder.devp.deploy.business.deploy.vo.DevpSysDpyResInstHostVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.WebDataBinder;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * 管理资源实例部署主机节点
 * @author icode
 */
@Api(description = "资源实例部署主机节点", tags = "DevpSysDpyResInstHost")
@RestController
@RequestMapping(value = "/deploy/devpSysDpyResInstHost")
public class DevpSysDpyResInstHostController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysDpyResInstHostController.class);


	@Autowired
	private DevpSysDpyResInstHostService devpSysDpyResInstHostService;


	@Autowired
	private DevpSysDpyResInstHostValidator devpSysDpyResInstHostValidator;

    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpSysDpyResInstHostValidator);
	}

	/**
	 * 新增资源实例部署主机节点
	 * @param devpSysDpyResInstHostAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增资源实例部署主机节点", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpSysDpyResInstHostVO add(@RequestBody @Valid DevpSysDpyResInstHostAddDto devpSysDpyResInstHostAddDto){
		DevpSysDpyResInstHost devpSysDpyResInstHost = new DevpSysDpyResInstHost();
		BeanUtils.copyProperties(devpSysDpyResInstHostAddDto, devpSysDpyResInstHost);

		devpSysDpyResInstHostService.add(devpSysDpyResInstHost);

		return  initViewProperty(devpSysDpyResInstHost);
	}

	/**
	 * 删除资源实例部署主机节点,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除资源实例部署主机节点", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpSysDpyResInstHost :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpSysDpyResInstHostService.delete(Long.valueOf(id));
		}

	}

	/**
	 * 更新资源实例部署主机节点
	 * @param devpSysDpyResInstHostEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产资源实例部署主机节点(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	DevpSysDpyResInstHostVO update(@RequestBody @Valid DevpSysDpyResInstHostEditDto devpSysDpyResInstHostEditDto, @PathVariable Long id){
		DevpSysDpyResInstHost devpSysDpyResInstHost = new DevpSysDpyResInstHost();
		BeanUtils.copyProperties(devpSysDpyResInstHostEditDto, devpSysDpyResInstHost);
		devpSysDpyResInstHost.setId(id);
		devpSysDpyResInstHostService.merge(devpSysDpyResInstHost);

		DevpSysDpyResInstHostVO vo = initViewProperty(devpSysDpyResInstHost);
		return  vo;
	}

	/**
	 * 根据ID查询资源实例部署主机节点
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询资源实例部署主机节点", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  DevpSysDpyResInstHostVO get(@PathVariable Long id) {

		DevpSysDpyResInstHost devpSysDpyResInstHost = devpSysDpyResInstHostService.find(id);

		DevpSysDpyResInstHostVO vo = initViewProperty(devpSysDpyResInstHost);
		return vo;
	}

	/**
	 * 查询资源实例部署主机节点列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询资源实例部署主机节点列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpSysDpyResInstHostVO> list(@RequestBody PageSearchRequest<DevpSysDpyResInstHostCondition> pageSearchRequest){

		SortCondition sortCondition = pageSearchRequest.getSortCondition();
		Sort sort   = null;
		if(sortCondition!=null){
			sort = new Sort(Sort.Direction.fromStringOrNull(sortCondition.getDirection().toString()), sortCondition.getProperty());
		}
		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		pageRequest.setSort(sort);
		Page<DevpSysDpyResInstHost> page = devpSysDpyResInstHostService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<DevpSysDpyResInstHostVO> voList = new ArrayList<>();
		for(DevpSysDpyResInstHost devpSysDpyResInstHost : page.getContent()){
			voList.add(initViewProperty(devpSysDpyResInstHost));
		}

		PageContent<DevpSysDpyResInstHostVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	private DevpSysDpyResInstHostVO initViewProperty(DevpSysDpyResInstHost devpSysDpyResInstHost){
	    DevpSysDpyResInstHostVO vo = new DevpSysDpyResInstHostVO();

        BeanUtils.copyProperties(devpSysDpyResInstHost, vo);

	    //初始化其他对象
        return vo;
	}


}
