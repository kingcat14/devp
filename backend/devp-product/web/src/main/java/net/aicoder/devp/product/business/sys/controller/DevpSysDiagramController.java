package net.aicoder.devp.product.business.sys.controller;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.common.framework.web.data.SortCondition;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.devp.product.business.sys.domain.DevpSysDiagram;
import net.aicoder.devp.product.business.sys.dto.DevpSysDiagramCondition;
import net.aicoder.devp.product.business.sys.dto.DevpSysDiagramAddDto;
import net.aicoder.devp.product.business.sys.dto.DevpSysDiagramEditDto;
import net.aicoder.devp.product.business.sys.service.DevpSysDiagramService;
import net.aicoder.devp.product.business.sys.valid.DevpSysDiagramValidator;
import net.aicoder.devp.product.business.sys.vo.DevpSysDiagramVO;

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
 * 管理UML图
 * @author icode
 */
@Api(description = "UML图", tags = "DevpSysDiagram")
@RestController
@RequestMapping(value = "/sys/devpSysDiagram")
public class DevpSysDiagramController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysDiagramController.class);


	@Autowired
	private DevpSysDiagramService devpSysDiagramService;


	@Autowired
	private DevpSysDiagramValidator devpSysDiagramValidator;

    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpSysDiagramValidator);
	}

	/**
	 * 新增UML图
	 * @param devpSysDiagramAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增UML图", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpSysDiagramVO add(@RequestBody @Valid DevpSysDiagramAddDto devpSysDiagramAddDto){
		DevpSysDiagram devpSysDiagram = new DevpSysDiagram();
		BeanUtils.copyProperties(devpSysDiagramAddDto, devpSysDiagram);

		devpSysDiagramService.add(devpSysDiagram);

		return  initViewProperty(devpSysDiagram);
	}

	/**
	 * 删除UML图,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除UML图", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpSysDiagram :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpSysDiagramService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新UML图
	 * @param devpSysDiagramEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产UML图(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	DevpSysDiagramVO update(@RequestBody @Valid DevpSysDiagramEditDto devpSysDiagramEditDto, @PathVariable Long id){
		DevpSysDiagram devpSysDiagram = new DevpSysDiagram();
		BeanUtils.copyProperties(devpSysDiagramEditDto, devpSysDiagram);
		devpSysDiagram.setId(id);
		devpSysDiagramService.merge(devpSysDiagram);

		DevpSysDiagramVO vo = initViewProperty(devpSysDiagram);
		return  vo;
	}

	/**
	 * 根据ID查询UML图
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询UML图", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  DevpSysDiagramVO get(@PathVariable Long id) {

		DevpSysDiagram devpSysDiagram = devpSysDiagramService.find(id);

		DevpSysDiagramVO vo = initViewProperty(devpSysDiagram);
		return vo;
	}

	/**
	 * 查询UML图列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询UML图列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpSysDiagramVO> list(@RequestBody PageSearchRequest<DevpSysDiagramCondition> pageSearchRequest){

		SortCondition sortCondition = pageSearchRequest.getSortCondition();
		Sort sort   = null;
		if(sortCondition!=null){
			sort = new Sort(Sort.Direction.fromStringOrNull(sortCondition.getDirection().toString()), sortCondition.getProperty());
		}
		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		pageRequest.setSort(sort);
		Page<DevpSysDiagram> page = devpSysDiagramService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<DevpSysDiagramVO> voList = new ArrayList<>();
		for(DevpSysDiagram devpSysDiagram : page.getContent()){
			voList.add(initViewProperty(devpSysDiagram));
		}

		PageContent<DevpSysDiagramVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	private DevpSysDiagramVO initViewProperty(DevpSysDiagram devpSysDiagram){
	    DevpSysDiagramVO vo = new DevpSysDiagramVO();

        BeanUtils.copyProperties(devpSysDiagram, vo);

	    //初始化其他对象
        return vo;
	}


}
