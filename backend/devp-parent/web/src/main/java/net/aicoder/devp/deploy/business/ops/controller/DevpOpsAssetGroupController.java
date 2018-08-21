package net.aicoder.devp.deploy.business.ops.controller;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.common.framework.web.data.SortCondition;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.devp.deploy.business.ops.domain.DevpOpsAssetGroup;
import net.aicoder.devp.deploy.business.ops.dto.DevpOpsAssetGroupCondition;
import net.aicoder.devp.deploy.business.ops.dto.DevpOpsAssetGroupAddDto;
import net.aicoder.devp.deploy.business.ops.dto.DevpOpsAssetGroupEditDto;
import net.aicoder.devp.deploy.business.ops.service.DevpOpsAssetGroupService;
import net.aicoder.devp.deploy.business.ops.valid.DevpOpsAssetGroupValidator;
import net.aicoder.devp.deploy.business.ops.vo.DevpOpsAssetGroupVO;

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
 * 管理资产分组
 * @author icode
 */
@Api(description = "资产分组", tags = "DevpOpsAssetGroup")
@RestController
@RequestMapping(value = "/ops/devpOpsAssetGroup")
public class DevpOpsAssetGroupController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpOpsAssetGroupController.class);


	@Autowired
	private DevpOpsAssetGroupService devpOpsAssetGroupService;


	@Autowired
	private DevpOpsAssetGroupValidator devpOpsAssetGroupValidator;

    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpOpsAssetGroupValidator);
	}

	/**
	 * 新增资产分组
	 * @param devpOpsAssetGroupAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增资产分组", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DevpOpsAssetGroupVO add(@RequestBody @Valid DevpOpsAssetGroupAddDto devpOpsAssetGroupAddDto){
		DevpOpsAssetGroup devpOpsAssetGroup = new DevpOpsAssetGroup();
		BeanUtils.copyProperties(devpOpsAssetGroupAddDto, devpOpsAssetGroup);

		devpOpsAssetGroupService.add(devpOpsAssetGroup);

		return  initViewProperty(devpOpsAssetGroup);
	}

	/**
	 * 删除资产分组,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除资产分组", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete devpOpsAssetGroup :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			devpOpsAssetGroupService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新资产分组
	 * @param devpOpsAssetGroupEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产资产分组(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	DevpOpsAssetGroupVO update(@RequestBody @Valid DevpOpsAssetGroupEditDto devpOpsAssetGroupEditDto, @PathVariable Long id){
		DevpOpsAssetGroup devpOpsAssetGroup = new DevpOpsAssetGroup();
		BeanUtils.copyProperties(devpOpsAssetGroupEditDto, devpOpsAssetGroup);
		devpOpsAssetGroup.setId(id);
		devpOpsAssetGroupService.merge(devpOpsAssetGroup);

		DevpOpsAssetGroupVO vo = initViewProperty(devpOpsAssetGroup);
		return  vo;
	}

	/**
	 * 根据ID查询资产分组
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询资产分组", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  DevpOpsAssetGroupVO get(@PathVariable Long id) {

		DevpOpsAssetGroup devpOpsAssetGroup = devpOpsAssetGroupService.find(id);

		DevpOpsAssetGroupVO vo = initViewProperty(devpOpsAssetGroup);
		return vo;
	}

	/**
	 * 查询资产分组列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询资产分组列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<DevpOpsAssetGroupVO> list(@RequestBody PageSearchRequest<DevpOpsAssetGroupCondition> pageSearchRequest){

		SortCondition sortCondition = pageSearchRequest.getSortCondition();
		Sort sort   = null;
		if(sortCondition!=null){
			sort = new Sort(Sort.Direction.fromStringOrNull(sortCondition.getDirection().toString()), sortCondition.getProperty());
		}
		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		pageRequest.setSort(sort);
		Page<DevpOpsAssetGroup> page = devpOpsAssetGroupService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<DevpOpsAssetGroupVO> voList = new ArrayList<>();
		for(DevpOpsAssetGroup devpOpsAssetGroup : page.getContent()){
			voList.add(initViewProperty(devpOpsAssetGroup));
		}

		PageContent<DevpOpsAssetGroupVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	private DevpOpsAssetGroupVO initViewProperty(DevpOpsAssetGroup devpOpsAssetGroup){
	    DevpOpsAssetGroupVO vo = new DevpOpsAssetGroupVO();

        BeanUtils.copyProperties(devpOpsAssetGroup, vo);

	    //初始化其他对象
        return vo;
	}


}
