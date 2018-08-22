package net.aicoder.devp.business.ops.controller;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.common.framework.web.data.SortCondition;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.devp.business.ops.domain.DevpOpsParasDefine;
import net.aicoder.devp.business.ops.dto.DevpOpsParasDefineAddDto;
import net.aicoder.devp.business.ops.dto.DevpOpsParasDefineCondition;
import net.aicoder.devp.business.ops.dto.DevpOpsParasDefineEditDto;
import net.aicoder.devp.business.ops.service.DevpOpsParasDefineService;
import net.aicoder.devp.business.ops.valid.DevpOpsParasDefineValidator;
import net.aicoder.devp.business.ops.vo.DevpOpsParasDefineVO;

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
 * 管理运维元素扩充信息
 * @author icode
 */
@Api(description = "运维元素扩充信息", tags = "DevpOpsParasDefine")
@RestController
@RequestMapping(value = "/ops/devpOpsParasDefine")
public class DevpOpsParasDefineController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpOpsParasDefineController.class);


	@Autowired
	private DevpOpsParasDefineService devpOpsParasDefineService;


	@Autowired
	private DevpOpsParasDefineValidator devpOpsParasDefineValidator;

    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpOpsParasDefineValidator);
	}

	/**
	 * 新增运维元素扩充信息
	 * @param devpOpsParasDefineAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增运维元素扩充信息", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpOpsParasDefineVO add(@RequestBody @Valid DevpOpsParasDefineAddDto devpOpsParasDefineAddDto){
		DevpOpsParasDefine devpOpsParasDefine = new DevpOpsParasDefine();
		BeanUtils.copyProperties(devpOpsParasDefineAddDto, devpOpsParasDefine);

		devpOpsParasDefineService.add(devpOpsParasDefine);

		return  initViewProperty(devpOpsParasDefine);
	}

	/**
	 * 删除运维元素扩充信息,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除运维元素扩充信息", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpOpsParasDefine :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpOpsParasDefineService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新运维元素扩充信息
	 * @param devpOpsParasDefineEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产运维元素扩充信息(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	DevpOpsParasDefineVO update(@RequestBody @Valid DevpOpsParasDefineEditDto devpOpsParasDefineEditDto, @PathVariable Long id){
		DevpOpsParasDefine devpOpsParasDefine = new DevpOpsParasDefine();
		BeanUtils.copyProperties(devpOpsParasDefineEditDto, devpOpsParasDefine);
		devpOpsParasDefine.setId(id);
		devpOpsParasDefineService.merge(devpOpsParasDefine);

		DevpOpsParasDefineVO vo = initViewProperty(devpOpsParasDefine);
		return  vo;
	}

	/**
	 * 根据ID查询运维元素扩充信息
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询运维元素扩充信息", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  DevpOpsParasDefineVO get(@PathVariable Long id) {

		DevpOpsParasDefine devpOpsParasDefine = devpOpsParasDefineService.find(id);

		DevpOpsParasDefineVO vo = initViewProperty(devpOpsParasDefine);
		return vo;
	}

	/**
	 * 查询运维元素扩充信息列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询运维元素扩充信息列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpOpsParasDefineVO> list(@RequestBody PageSearchRequest<DevpOpsParasDefineCondition> pageSearchRequest){

		SortCondition sortCondition = pageSearchRequest.getSortCondition();
		Sort sort   = null;
		if(sortCondition!=null){
			sort = new Sort(Sort.Direction.fromStringOrNull(sortCondition.getDirection().toString()), sortCondition.getProperty());
		}
		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		pageRequest.setSort(sort);
		Page<DevpOpsParasDefine> page = devpOpsParasDefineService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<DevpOpsParasDefineVO> voList = new ArrayList<>();
		for(DevpOpsParasDefine devpOpsParasDefine : page.getContent()){
			voList.add(initViewProperty(devpOpsParasDefine));
		}

		PageContent<DevpOpsParasDefineVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	private DevpOpsParasDefineVO initViewProperty(DevpOpsParasDefine devpOpsParasDefine){
	    DevpOpsParasDefineVO vo = new DevpOpsParasDefineVO();

        BeanUtils.copyProperties(devpOpsParasDefine, vo);

	    //初始化其他对象
        return vo;
	}


}
