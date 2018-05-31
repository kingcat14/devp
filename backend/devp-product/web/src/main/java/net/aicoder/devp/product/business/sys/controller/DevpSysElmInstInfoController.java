package net.aicoder.devp.product.business.sys.controller;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.common.framework.web.data.SortCondition;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.devp.product.business.sys.domain.DevpSysElmInstInfo;
import net.aicoder.devp.product.business.sys.dto.DevpSysElmInstInfoCondition;
import net.aicoder.devp.product.business.sys.dto.DevpSysElmInstInfoAddDto;
import net.aicoder.devp.product.business.sys.dto.DevpSysElmInstInfoEditDto;
import net.aicoder.devp.product.business.sys.service.DevpSysElmInstInfoService;
import net.aicoder.devp.product.business.sys.valid.DevpSysElmInstInfoValidator;
import net.aicoder.devp.product.business.sys.vo.DevpSysElmInstInfoVO;

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
@Api(description = "系统元素实例", tags = "DevpSysElmInstInfo")
@RestController
@RequestMapping(value = "/sys/devpSysElmInstInfo")
public class DevpSysElmInstInfoController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysElmInstInfoController.class);


	@Autowired
	private DevpSysElmInstInfoService devpSysElmInstInfoService;


	@Autowired
	private DevpSysElmInstInfoValidator devpSysElmInstInfoValidator;

    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpSysElmInstInfoValidator);
	}

	/**
	 * 新增系统元素实例
	 * @param devpSysElmInstInfoAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增系统元素实例", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpSysElmInstInfoVO add(@RequestBody @Valid DevpSysElmInstInfoAddDto devpSysElmInstInfoAddDto){
		DevpSysElmInstInfo devpSysElmInstInfo = new DevpSysElmInstInfo();
		BeanUtils.copyProperties(devpSysElmInstInfoAddDto, devpSysElmInstInfo);

		devpSysElmInstInfoService.add(devpSysElmInstInfo);

		return  initViewProperty(devpSysElmInstInfo);
	}

	/**
	 * 删除系统元素实例,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除系统元素实例", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpSysElmInstInfo :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpSysElmInstInfoService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新系统元素实例
	 * @param devpSysElmInstInfoEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产系统元素实例(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	DevpSysElmInstInfoVO update(@RequestBody @Valid DevpSysElmInstInfoEditDto devpSysElmInstInfoEditDto, @PathVariable Long id){
		DevpSysElmInstInfo devpSysElmInstInfo = new DevpSysElmInstInfo();
		BeanUtils.copyProperties(devpSysElmInstInfoEditDto, devpSysElmInstInfo);
		devpSysElmInstInfo.setId(id);
		devpSysElmInstInfoService.merge(devpSysElmInstInfo);

		DevpSysElmInstInfoVO vo = initViewProperty(devpSysElmInstInfo);
		return  vo;
	}

	/**
	 * 根据ID查询系统元素实例
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询系统元素实例", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  DevpSysElmInstInfoVO get(@PathVariable Long id) {

		DevpSysElmInstInfo devpSysElmInstInfo = devpSysElmInstInfoService.find(id);

		DevpSysElmInstInfoVO vo = initViewProperty(devpSysElmInstInfo);
		return vo;
	}

	/**
	 * 查询系统元素实例列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询系统元素实例列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpSysElmInstInfoVO> list(@RequestBody PageSearchRequest<DevpSysElmInstInfoCondition> pageSearchRequest){

		SortCondition sortCondition = pageSearchRequest.getSortCondition();
		Sort sort   = null;
		if(sortCondition!=null){
			sort = new Sort(Sort.Direction.fromStringOrNull(sortCondition.getDirection().toString()), sortCondition.getProperty());
		}
		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		pageRequest.setSort(sort);
		Page<DevpSysElmInstInfo> page = devpSysElmInstInfoService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<DevpSysElmInstInfoVO> voList = new ArrayList<>();
		for(DevpSysElmInstInfo devpSysElmInstInfo : page.getContent()){
			voList.add(initViewProperty(devpSysElmInstInfo));
		}

		PageContent<DevpSysElmInstInfoVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	private DevpSysElmInstInfoVO initViewProperty(DevpSysElmInstInfo devpSysElmInstInfo){
	    DevpSysElmInstInfoVO vo = new DevpSysElmInstInfoVO();

        BeanUtils.copyProperties(devpSysElmInstInfo, vo);

	    //初始化其他对象
        return vo;
	}


}
