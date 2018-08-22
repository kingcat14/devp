package net.aicoder.devp.business.ops.controller;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.common.framework.web.data.SortCondition;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.devp.business.ops.domain.DevpOpsElementInfo;
import net.aicoder.devp.business.ops.dto.DevpOpsElementInfoAddDto;
import net.aicoder.devp.business.ops.dto.DevpOpsElementInfoCondition;
import net.aicoder.devp.business.ops.dto.DevpOpsElementInfoEditDto;
import net.aicoder.devp.business.ops.service.DevpOpsElementInfoService;
import net.aicoder.devp.business.ops.valid.DevpOpsElementInfoValidator;
import net.aicoder.devp.business.ops.vo.DevpOpsElementInfoVO;

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
 * 管理系统元素扩充信息
 * @author icode
 */
@Api(description = "系统元素扩充信息", tags = "DevpOpsElementInfo")
@RestController
@RequestMapping(value = "/ops/devpOpsElementInfo")
public class DevpOpsElementInfoController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpOpsElementInfoController.class);


	@Autowired
	private DevpOpsElementInfoService devpOpsElementInfoService;


	@Autowired
	private DevpOpsElementInfoValidator devpOpsElementInfoValidator;

    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpOpsElementInfoValidator);
	}

	/**
	 * 新增系统元素扩充信息
	 * @param devpOpsElementInfoAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增系统元素扩充信息", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpOpsElementInfoVO add(@RequestBody @Valid DevpOpsElementInfoAddDto devpOpsElementInfoAddDto){
		DevpOpsElementInfo devpOpsElementInfo = new DevpOpsElementInfo();
		BeanUtils.copyProperties(devpOpsElementInfoAddDto, devpOpsElementInfo);

		devpOpsElementInfoService.add(devpOpsElementInfo);

		return  initViewProperty(devpOpsElementInfo);
	}

	/**
	 * 删除系统元素扩充信息,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除系统元素扩充信息", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpOpsElementInfo :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpOpsElementInfoService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新系统元素扩充信息
	 * @param devpOpsElementInfoEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产系统元素扩充信息(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	DevpOpsElementInfoVO update(@RequestBody @Valid DevpOpsElementInfoEditDto devpOpsElementInfoEditDto, @PathVariable Long id){
		DevpOpsElementInfo devpOpsElementInfo = new DevpOpsElementInfo();
		BeanUtils.copyProperties(devpOpsElementInfoEditDto, devpOpsElementInfo);
		devpOpsElementInfo.setId(id);
		devpOpsElementInfoService.merge(devpOpsElementInfo);

		DevpOpsElementInfoVO vo = initViewProperty(devpOpsElementInfo);
		return  vo;
	}

	/**
	 * 根据ID查询系统元素扩充信息
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询系统元素扩充信息", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  DevpOpsElementInfoVO get(@PathVariable Long id) {

		DevpOpsElementInfo devpOpsElementInfo = devpOpsElementInfoService.find(id);

		DevpOpsElementInfoVO vo = initViewProperty(devpOpsElementInfo);
		return vo;
	}

	/**
	 * 查询系统元素扩充信息列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询系统元素扩充信息列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpOpsElementInfoVO> list(@RequestBody PageSearchRequest<DevpOpsElementInfoCondition> pageSearchRequest){

		SortCondition sortCondition = pageSearchRequest.getSortCondition();
		Sort sort   = null;
		if(sortCondition!=null){
			sort = new Sort(Sort.Direction.fromStringOrNull(sortCondition.getDirection().toString()), sortCondition.getProperty());
		}
		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		pageRequest.setSort(sort);
		Page<DevpOpsElementInfo> page = devpOpsElementInfoService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<DevpOpsElementInfoVO> voList = new ArrayList<>();
		for(DevpOpsElementInfo devpOpsElementInfo : page.getContent()){
			voList.add(initViewProperty(devpOpsElementInfo));
		}

		PageContent<DevpOpsElementInfoVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	private DevpOpsElementInfoVO initViewProperty(DevpOpsElementInfo devpOpsElementInfo){
	    DevpOpsElementInfoVO vo = new DevpOpsElementInfoVO();

        BeanUtils.copyProperties(devpOpsElementInfo, vo);

	    //初始化其他对象
        return vo;
	}


}
