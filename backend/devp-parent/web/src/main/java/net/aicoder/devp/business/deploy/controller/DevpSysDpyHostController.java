package net.aicoder.devp.business.deploy.controller;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.common.framework.web.data.SortCondition;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.devp.business.deploy.domain.DevpSysDpyHost;
import net.aicoder.devp.business.deploy.dto.DevpSysDpyHostAddDto;
import net.aicoder.devp.business.deploy.dto.DevpSysDpyHostCondition;
import net.aicoder.devp.business.deploy.dto.DevpSysDpyHostEditDto;
import net.aicoder.devp.business.deploy.service.DevpSysDpyHostService;
import net.aicoder.devp.business.deploy.valid.DevpSysDpyHostValidator;
import net.aicoder.devp.business.deploy.vo.DevpSysDpyHostVO;

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
 * 管理部署主机节点
 * @author icode
 */
@Api(description = "部署主机节点", tags = "DevpSysDpyHost")
@RestController
@RequestMapping(value = "/deploy/devpSysDpyHost")
public class DevpSysDpyHostController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysDpyHostController.class);


	@Autowired
	private DevpSysDpyHostService devpSysDpyHostService;


	@Autowired
	private DevpSysDpyHostValidator devpSysDpyHostValidator;

    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpSysDpyHostValidator);
	}

	/**
	 * 新增部署主机节点
	 * @param devpSysDpyHostAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增部署主机节点", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpSysDpyHostVO add(@RequestBody @Valid DevpSysDpyHostAddDto devpSysDpyHostAddDto){
		DevpSysDpyHost devpSysDpyHost = new DevpSysDpyHost();
		BeanUtils.copyProperties(devpSysDpyHostAddDto, devpSysDpyHost);

		devpSysDpyHostService.add(devpSysDpyHost);

		return  initViewProperty(devpSysDpyHost);
	}

	/**
	 * 删除部署主机节点,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除部署主机节点", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpSysDpyHost :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpSysDpyHostService.delete(Long.valueOf(id));
		}

	}

	/**
	 * 更新部署主机节点
	 * @param devpSysDpyHostEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产部署主机节点(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	DevpSysDpyHostVO update(@RequestBody @Valid DevpSysDpyHostEditDto devpSysDpyHostEditDto, @PathVariable Long id){
		DevpSysDpyHost devpSysDpyHost = new DevpSysDpyHost();
		BeanUtils.copyProperties(devpSysDpyHostEditDto, devpSysDpyHost);
		devpSysDpyHost.setId(id);
		devpSysDpyHostService.merge(devpSysDpyHost);

		DevpSysDpyHostVO vo = initViewProperty(devpSysDpyHost);
		return  vo;
	}

	/**
	 * 根据ID查询部署主机节点
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询部署主机节点", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  DevpSysDpyHostVO get(@PathVariable Long id) {

		DevpSysDpyHost devpSysDpyHost = devpSysDpyHostService.find(id);

		DevpSysDpyHostVO vo = initViewProperty(devpSysDpyHost);
		return vo;
	}

	/**
	 * 查询部署主机节点列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询部署主机节点列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpSysDpyHostVO> list(@RequestBody PageSearchRequest<DevpSysDpyHostCondition> pageSearchRequest){

		SortCondition sortCondition = pageSearchRequest.getSortCondition();
		Sort sort   = null;
		if(sortCondition!=null){
			sort = new Sort(Sort.Direction.fromStringOrNull(sortCondition.getDirection().toString()), sortCondition.getProperty());
		}
		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		pageRequest.setSort(sort);
		Page<DevpSysDpyHost> page = devpSysDpyHostService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<DevpSysDpyHostVO> voList = new ArrayList<>();
		for(DevpSysDpyHost devpSysDpyHost : page.getContent()){
			voList.add(initViewProperty(devpSysDpyHost));
		}

		PageContent<DevpSysDpyHostVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	private DevpSysDpyHostVO initViewProperty(DevpSysDpyHost devpSysDpyHost){
	    DevpSysDpyHostVO vo = new DevpSysDpyHostVO();

        BeanUtils.copyProperties(devpSysDpyHost, vo);

	    //初始化其他对象
        return vo;
	}


}
