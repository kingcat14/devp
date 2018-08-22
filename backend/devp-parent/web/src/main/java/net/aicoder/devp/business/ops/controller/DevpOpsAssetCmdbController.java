package net.aicoder.devp.business.ops.controller;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.common.framework.web.data.SortCondition;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.devp.business.ops.domain.DevpOpsAssetCmdb;
import net.aicoder.devp.business.ops.dto.DevpOpsAssetCmdbAddDto;
import net.aicoder.devp.business.ops.dto.DevpOpsAssetCmdbCondition;
import net.aicoder.devp.business.ops.dto.DevpOpsAssetCmdbEditDto;
import net.aicoder.devp.business.ops.service.DevpOpsAssetCmdbService;
import net.aicoder.devp.business.ops.valid.DevpOpsAssetCmdbValidator;
import net.aicoder.devp.business.ops.vo.DevpOpsAssetCmdbVO;

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
 * 管理资产定义
 * @author icode
 */
@Api(description = "资产定义", tags = "DevpOpsAssetCmdb")
@RestController
@RequestMapping(value = "/ops/devpOpsAssetCmdb")
public class DevpOpsAssetCmdbController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpOpsAssetCmdbController.class);


	@Autowired
	private DevpOpsAssetCmdbService devpOpsAssetCmdbService;


	@Autowired
	private DevpOpsAssetCmdbValidator devpOpsAssetCmdbValidator;

    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpOpsAssetCmdbValidator);
	}

	/**
	 * 新增资产定义
	 * @param devpOpsAssetCmdbAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增资产定义", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpOpsAssetCmdbVO add(@RequestBody @Valid DevpOpsAssetCmdbAddDto devpOpsAssetCmdbAddDto){
		DevpOpsAssetCmdb devpOpsAssetCmdb = new DevpOpsAssetCmdb();
		BeanUtils.copyProperties(devpOpsAssetCmdbAddDto, devpOpsAssetCmdb);

		devpOpsAssetCmdbService.add(devpOpsAssetCmdb);

		return  initViewProperty(devpOpsAssetCmdb);
	}

	/**
	 * 删除资产定义,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除资产定义", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpOpsAssetCmdb :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpOpsAssetCmdbService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新资产定义
	 * @param devpOpsAssetCmdbEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产资产定义(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	DevpOpsAssetCmdbVO update(@RequestBody @Valid DevpOpsAssetCmdbEditDto devpOpsAssetCmdbEditDto, @PathVariable Long id){
		DevpOpsAssetCmdb devpOpsAssetCmdb = new DevpOpsAssetCmdb();
		BeanUtils.copyProperties(devpOpsAssetCmdbEditDto, devpOpsAssetCmdb);
		devpOpsAssetCmdb.setId(id);
		devpOpsAssetCmdbService.merge(devpOpsAssetCmdb);

		DevpOpsAssetCmdbVO vo = initViewProperty(devpOpsAssetCmdb);
		return  vo;
	}

	/**
	 * 根据ID查询资产定义
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询资产定义", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  DevpOpsAssetCmdbVO get(@PathVariable Long id) {

		DevpOpsAssetCmdb devpOpsAssetCmdb = devpOpsAssetCmdbService.find(id);

		DevpOpsAssetCmdbVO vo = initViewProperty(devpOpsAssetCmdb);
		return vo;
	}

	/**
	 * 查询资产定义列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询资产定义列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpOpsAssetCmdbVO> list(@RequestBody PageSearchRequest<DevpOpsAssetCmdbCondition> pageSearchRequest){

		SortCondition sortCondition = pageSearchRequest.getSortCondition();
		Sort sort   = null;
		if(sortCondition!=null){
			sort = new Sort(Sort.Direction.fromStringOrNull(sortCondition.getDirection().toString()), sortCondition.getProperty());
		}
		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		pageRequest.setSort(sort);
		Page<DevpOpsAssetCmdb> page = devpOpsAssetCmdbService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<DevpOpsAssetCmdbVO> voList = new ArrayList<>();
		for(DevpOpsAssetCmdb devpOpsAssetCmdb : page.getContent()){
			voList.add(initViewProperty(devpOpsAssetCmdb));
		}

		PageContent<DevpOpsAssetCmdbVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	private DevpOpsAssetCmdbVO initViewProperty(DevpOpsAssetCmdb devpOpsAssetCmdb){
	    DevpOpsAssetCmdbVO vo = new DevpOpsAssetCmdbVO();

        BeanUtils.copyProperties(devpOpsAssetCmdb, vo);

	    //初始化其他对象
        return vo;
	}


}
