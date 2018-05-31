package net.aicoder.devp.product.business.sys.controller;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.common.framework.web.data.SortCondition;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.devp.product.business.sys.domain.DevpSysElement;
import net.aicoder.devp.product.business.sys.dto.DevpSysElementCondition;
import net.aicoder.devp.product.business.sys.dto.DevpSysElementAddDto;
import net.aicoder.devp.product.business.sys.dto.DevpSysElementEditDto;
import net.aicoder.devp.product.business.sys.service.DevpSysElementService;
import net.aicoder.devp.product.business.sys.valid.DevpSysElementValidator;
import net.aicoder.devp.product.business.sys.vo.DevpSysElementVO;

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
 * 管理系统元素
 * @author icode
 */
@Api(description = "系统元素", tags = "DevpSysElement")
@RestController
@RequestMapping(value = "/sys/devpSysElement")
public class DevpSysElementController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysElementController.class);


	@Autowired
	private DevpSysElementService devpSysElementService;


	@Autowired
	private DevpSysElementValidator devpSysElementValidator;

    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpSysElementValidator);
	}

	/**
	 * 新增系统元素
	 * @param devpSysElementAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增系统元素", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpSysElementVO add(@RequestBody @Valid DevpSysElementAddDto devpSysElementAddDto){
		DevpSysElement devpSysElement = new DevpSysElement();
		BeanUtils.copyProperties(devpSysElementAddDto, devpSysElement);

		devpSysElementService.add(devpSysElement);

		return  initViewProperty(devpSysElement);
	}

	/**
	 * 删除系统元素,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除系统元素", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpSysElement :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpSysElementService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新系统元素
	 * @param devpSysElementEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产系统元素(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	DevpSysElementVO update(@RequestBody @Valid DevpSysElementEditDto devpSysElementEditDto, @PathVariable Long id){
		DevpSysElement devpSysElement = new DevpSysElement();
		BeanUtils.copyProperties(devpSysElementEditDto, devpSysElement);
		devpSysElement.setId(id);
		devpSysElementService.merge(devpSysElement);

		DevpSysElementVO vo = initViewProperty(devpSysElement);
		return  vo;
	}

	/**
	 * 根据ID查询系统元素
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询系统元素", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  DevpSysElementVO get(@PathVariable Long id) {

		DevpSysElement devpSysElement = devpSysElementService.find(id);

		DevpSysElementVO vo = initViewProperty(devpSysElement);
		return vo;
	}

	/**
	 * 查询系统元素列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询系统元素列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpSysElementVO> list(@RequestBody PageSearchRequest<DevpSysElementCondition> pageSearchRequest){

		SortCondition sortCondition = pageSearchRequest.getSortCondition();
		Sort sort   = null;
		if(sortCondition!=null){
			sort = new Sort(Sort.Direction.fromStringOrNull(sortCondition.getDirection().toString()), sortCondition.getProperty());
		}
		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		pageRequest.setSort(sort);
		Page<DevpSysElement> page = devpSysElementService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<DevpSysElementVO> voList = new ArrayList<>();
		for(DevpSysElement devpSysElement : page.getContent()){
			voList.add(initViewProperty(devpSysElement));
		}

		PageContent<DevpSysElementVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	private DevpSysElementVO initViewProperty(DevpSysElement devpSysElement){
	    DevpSysElementVO vo = new DevpSysElementVO();

        BeanUtils.copyProperties(devpSysElement, vo);

	    //初始化其他对象
        return vo;
	}


}
