package net.aicoder.speedcloud.business.config.controller;

import com.alibaba.fastjson.JSONArray;
import com.yunkang.saas.bootstrap.monitor.annotation.BusinessFuncMonitor;
import com.yunkang.saas.common.framework.exception.ResourceNotFoundException;
import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageRequestConvert;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.aicoder.speedcloud.business.config.domain.PipelineTaskType;
import net.aicoder.speedcloud.business.config.dto.PipelineTaskTypeAddDto;
import net.aicoder.speedcloud.business.config.dto.PipelineTaskTypeCondition;
import net.aicoder.speedcloud.business.config.dto.PipelineTaskTypeEditDto;
import net.aicoder.speedcloud.business.config.service.PipelineTaskTypeService;
import net.aicoder.speedcloud.business.config.valid.PipelineTaskTypeValidator;
import net.aicoder.speedcloud.business.config.vo.PipelineTaskTypeVO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * 管理任务类型
 * @author icode
 */
@Api(description = "任务类型", tags = "PipelineTaskType")
@RestController
@RequestMapping(value = "/speedcloud/config/pipelinetasktype")
public class PipelineTaskTypeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineTaskTypeController.class);


	@Autowired
	private PipelineTaskTypeService pipelineTaskTypeService;



	@Autowired
	private PipelineTaskTypeValidator pipelineTaskTypeValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(pipelineTaskTypeValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增任务类型
	 * @param pipelineTaskTypeAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增任务类型", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
  	@BusinessFuncMonitor(value = "speedcloud.config.pipelinetasktype.add", count = true)
	public PipelineTaskTypeVO add(@RequestBody @Valid PipelineTaskTypeAddDto pipelineTaskTypeAddDto){
		PipelineTaskType pipelineTaskType = new PipelineTaskType();
		BeanUtils.copyProperties(pipelineTaskTypeAddDto, pipelineTaskType);

		pipelineTaskTypeService.add(pipelineTaskType);

		return  initViewProperty(pipelineTaskType);
	}

	/**
	 * 删除任务类型,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除任务类型", httpMethod = "DELETE")
	@DeleteMapping(path="/{idArray}")
  	@BusinessFuncMonitor(value = "speedcloud.config.pipelinetasktype.delete", count = true)
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete pipelineTaskType :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			pipelineTaskTypeService.delete(id);
		}

	}

	/**
	 * 更新任务类型
	 * @param pipelineTaskTypeEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改任务类型(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(path="/{id}")
  	@BusinessFuncMonitor(value = "speedcloud.config.pipelinetasktype.update", count = true)
	public	PipelineTaskTypeVO update(@RequestBody @Valid PipelineTaskTypeEditDto pipelineTaskTypeEditDto, @PathVariable String id){
		PipelineTaskType pipelineTaskType = pipelineTaskTypeService.find(id);
		BeanUtils.copyProperties(pipelineTaskTypeEditDto, pipelineTaskType);
		pipelineTaskType.setId(id);
		pipelineTaskTypeService.merge(pipelineTaskType);

		PipelineTaskTypeVO vo = initViewProperty(pipelineTaskType);
		return  vo;
	}

	/**
	 * 根据ID查询任务类型
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "根据ID查询", notes = "根据ID查询任务类型", httpMethod = "GET")
	@GetMapping(path="/{id}")
  	@BusinessFuncMonitor(value = "speedcloud.config.pipelinetasktype.get")
	public  PipelineTaskTypeVO get(@PathVariable String id) {

		PipelineTaskType pipelineTaskType = pipelineTaskTypeService.find(id);
		if(pipelineTaskType == null){
			throw new ResourceNotFoundException("找不到指定的任务类型，请检查ID");
		}
		PipelineTaskTypeVO vo = initViewProperty(pipelineTaskType);
		return vo;
	}

	/**
	 * 查询任务类型列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询任务类型列表", httpMethod = "POST")
	@PostMapping(path="/list")
	@BusinessFuncMonitor(value = "speedcloud.config.pipelinetasktype.list")
	public PageContent<PipelineTaskTypeVO> list(@RequestBody PageSearchRequest<PipelineTaskTypeCondition> pageSearchRequest){

		PageRequest pageRequest = PageRequestConvert.convert(pageSearchRequest);
      
		Page<PipelineTaskType> page = pipelineTaskTypeService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<PipelineTaskTypeVO> voList = new ArrayList<>();
		for(PipelineTaskType pipelineTaskType : page.getContent()){
			voList.add(initViewProperty(pipelineTaskType));
		}

		PageContent<PipelineTaskTypeVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出任务类型列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出任务类型列表", httpMethod = "POST")
    @RequestMapping(path="/export")
    public void export(PipelineTaskTypeCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<PipelineTaskTypeCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<PipelineTaskTypeVO> content = this.list(pageSearchRequest);

        List<PipelineTaskTypeVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(PipelineTaskTypeVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<>();

        headMap.put("name" ,"名称");
        headMap.put("code" ,"代码");

        String title = new String("任务类型");
        String fileName = new String(("任务类型_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private PipelineTaskTypeVO initViewProperty(PipelineTaskType pipelineTaskType){

	    PipelineTaskTypeVO vo = new PipelineTaskTypeVO();
        BeanUtils.copyProperties(pipelineTaskType, vo);


	    //初始化其他对象
        return vo;

	}


}

