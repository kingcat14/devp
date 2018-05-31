package net.aicoder.devp.product.business.sys.controller;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.common.framework.web.data.SortCondition;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.devp.product.business.sys.domain.DevpSysElmInstance;
import net.aicoder.devp.product.business.sys.dto.DevpSysElmInstanceCondition;
import net.aicoder.devp.product.business.sys.dto.DevpSysElmInstanceAddDto;
import net.aicoder.devp.product.business.sys.dto.DevpSysElmInstanceEditDto;
import net.aicoder.devp.product.business.sys.service.DevpSysElmInstanceService;
import net.aicoder.devp.product.business.sys.valid.DevpSysElmInstanceValidator;
import net.aicoder.devp.product.business.sys.vo.DevpSysElmInstanceVO;

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
 * 管理系统元素实例
 * @author icode
 */
@Api(description = "系统元素实例", tags = "DevpSysElmInstance")
@RestController
@RequestMapping(value = "/sys/devpSysElmInstance")
public class DevpSysElmInstanceController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysElmInstanceController.class);


	@Autowired
	private DevpSysElmInstanceService devpSysElmInstanceService;


	@Autowired
	private DevpSysElmInstanceValidator devpSysElmInstanceValidator;

    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpSysElmInstanceValidator);
	}

	/**
	 * 新增系统元素实例
	 * @param devpSysElmInstanceAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增系统元素实例", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpSysElmInstanceVO add(@RequestBody @Valid DevpSysElmInstanceAddDto devpSysElmInstanceAddDto){
		DevpSysElmInstance devpSysElmInstance = new DevpSysElmInstance();
		BeanUtils.copyProperties(devpSysElmInstanceAddDto, devpSysElmInstance);

		devpSysElmInstanceService.add(devpSysElmInstance);

		return  initViewProperty(devpSysElmInstance);
	}

	/**
	 * 删除系统元素实例,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除系统元素实例", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpSysElmInstance :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpSysElmInstanceService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新系统元素实例
	 * @param devpSysElmInstanceEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产系统元素实例(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	DevpSysElmInstanceVO update(@RequestBody @Valid DevpSysElmInstanceEditDto devpSysElmInstanceEditDto, @PathVariable Long id){
		DevpSysElmInstance devpSysElmInstance = new DevpSysElmInstance();
		BeanUtils.copyProperties(devpSysElmInstanceEditDto, devpSysElmInstance);
		devpSysElmInstance.setId(id);
		devpSysElmInstanceService.merge(devpSysElmInstance);

		DevpSysElmInstanceVO vo = initViewProperty(devpSysElmInstance);
		return  vo;
	}

	/**
	 * 根据ID查询系统元素实例
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询系统元素实例", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  DevpSysElmInstanceVO get(@PathVariable Long id) {

		DevpSysElmInstance devpSysElmInstance = devpSysElmInstanceService.find(id);

		DevpSysElmInstanceVO vo = initViewProperty(devpSysElmInstance);
		return vo;
	}

	/**
	 * 查询系统元素实例列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询系统元素实例列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpSysElmInstanceVO> list(@RequestBody PageSearchRequest<DevpSysElmInstanceCondition> pageSearchRequest){

		SortCondition sortCondition = pageSearchRequest.getSortCondition();
		Sort sort   = null;
		if(sortCondition!=null){
			sort = new Sort(Sort.Direction.fromStringOrNull(sortCondition.getDirection().toString()), sortCondition.getProperty());
		}
		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		pageRequest.setSort(sort);
		Page<DevpSysElmInstance> page = devpSysElmInstanceService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<DevpSysElmInstanceVO> voList = new ArrayList<>();
		for(DevpSysElmInstance devpSysElmInstance : page.getContent()){
			voList.add(initViewProperty(devpSysElmInstance));
		}

		PageContent<DevpSysElmInstanceVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	private DevpSysElmInstanceVO initViewProperty(DevpSysElmInstance devpSysElmInstance){
	    DevpSysElmInstanceVO vo = new DevpSysElmInstanceVO();

        BeanUtils.copyProperties(devpSysElmInstance, vo);

	    //初始化其他对象
        return vo;
	}


}
