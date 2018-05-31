package net.aicoder.devp.product.business.sys.controller;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.common.framework.web.data.SortCondition;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.devp.product.business.sys.domain.DevpSysElmConnector;
import net.aicoder.devp.product.business.sys.dto.DevpSysElmConnectorCondition;
import net.aicoder.devp.product.business.sys.dto.DevpSysElmConnectorAddDto;
import net.aicoder.devp.product.business.sys.dto.DevpSysElmConnectorEditDto;
import net.aicoder.devp.product.business.sys.service.DevpSysElmConnectorService;
import net.aicoder.devp.product.business.sys.valid.DevpSysElmConnectorValidator;
import net.aicoder.devp.product.business.sys.vo.DevpSysElmConnectorVO;

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
 * 管理系统元素间关系
 * @author icode
 */
@Api(description = "系统元素间关系", tags = "DevpSysElmConnector")
@RestController
@RequestMapping(value = "/sys/devpSysElmConnector")
public class DevpSysElmConnectorController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysElmConnectorController.class);


	@Autowired
	private DevpSysElmConnectorService devpSysElmConnectorService;


	@Autowired
	private DevpSysElmConnectorValidator devpSysElmConnectorValidator;

    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpSysElmConnectorValidator);
	}

	/**
	 * 新增系统元素间关系
	 * @param devpSysElmConnectorAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增系统元素间关系", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpSysElmConnectorVO add(@RequestBody @Valid DevpSysElmConnectorAddDto devpSysElmConnectorAddDto){
		DevpSysElmConnector devpSysElmConnector = new DevpSysElmConnector();
		BeanUtils.copyProperties(devpSysElmConnectorAddDto, devpSysElmConnector);

		devpSysElmConnectorService.add(devpSysElmConnector);

		return  initViewProperty(devpSysElmConnector);
	}

	/**
	 * 删除系统元素间关系,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除系统元素间关系", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpSysElmConnector :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpSysElmConnectorService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新系统元素间关系
	 * @param devpSysElmConnectorEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产系统元素间关系(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	DevpSysElmConnectorVO update(@RequestBody @Valid DevpSysElmConnectorEditDto devpSysElmConnectorEditDto, @PathVariable Long id){
		DevpSysElmConnector devpSysElmConnector = new DevpSysElmConnector();
		BeanUtils.copyProperties(devpSysElmConnectorEditDto, devpSysElmConnector);
		devpSysElmConnector.setId(id);
		devpSysElmConnectorService.merge(devpSysElmConnector);

		DevpSysElmConnectorVO vo = initViewProperty(devpSysElmConnector);
		return  vo;
	}

	/**
	 * 根据ID查询系统元素间关系
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询系统元素间关系", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  DevpSysElmConnectorVO get(@PathVariable Long id) {

		DevpSysElmConnector devpSysElmConnector = devpSysElmConnectorService.find(id);

		DevpSysElmConnectorVO vo = initViewProperty(devpSysElmConnector);
		return vo;
	}

	/**
	 * 查询系统元素间关系列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询系统元素间关系列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpSysElmConnectorVO> list(@RequestBody PageSearchRequest<DevpSysElmConnectorCondition> pageSearchRequest){

		SortCondition sortCondition = pageSearchRequest.getSortCondition();
		Sort sort   = null;
		if(sortCondition!=null){
			sort = new Sort(Sort.Direction.fromStringOrNull(sortCondition.getDirection().toString()), sortCondition.getProperty());
		}
		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		pageRequest.setSort(sort);
		Page<DevpSysElmConnector> page = devpSysElmConnectorService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<DevpSysElmConnectorVO> voList = new ArrayList<>();
		for(DevpSysElmConnector devpSysElmConnector : page.getContent()){
			voList.add(initViewProperty(devpSysElmConnector));
		}

		PageContent<DevpSysElmConnectorVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	private DevpSysElmConnectorVO initViewProperty(DevpSysElmConnector devpSysElmConnector){
	    DevpSysElmConnectorVO vo = new DevpSysElmConnectorVO();

        BeanUtils.copyProperties(devpSysElmConnector, vo);

	    //初始化其他对象
        return vo;
	}


}
