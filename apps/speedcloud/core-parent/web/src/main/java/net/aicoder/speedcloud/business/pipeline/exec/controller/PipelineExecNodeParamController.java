package net.aicoder.speedcloud.business.pipeline.exec.controller;

import com.alibaba.fastjson.JSONArray;
import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import com.yunkang.saas.common.framework.web.data.SortCondition;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.aicoder.speedcloud.business.pipeline.exec.domain.PipelineExecNode;
import net.aicoder.speedcloud.business.pipeline.exec.domain.PipelineExecNodeParam;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecNodeParamAddDto;
import net.aicoder.speedcloud.business.pipeline.exec.dto.PipelineExecNodeParamCondition;
import net.aicoder.speedcloud.business.pipeline.exec.service.PipelineExecNodeParamService;
import net.aicoder.speedcloud.business.pipeline.exec.service.PipelineExecNodeService;
import net.aicoder.speedcloud.business.pipeline.exec.valid.PipelineExecNodeParamValidator;
import net.aicoder.speedcloud.business.pipeline.exec.vo.PipelineExecNodeParamVO;
import net.aicoder.speedcloud.business.pipeline.exec.vo.PipelineExecNodeVO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * 管理运行实例节点参数
 * @author icode
 */
@Api(description = "运行实例节点参数", tags = "PipelineExecNodeParam")
@RestController
@RequestMapping(value = "/speedcloud/pipeline/exec/pipelineexecnodeparam")
public class PipelineExecNodeParamController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineExecNodeParamController.class);


	@Autowired
	private PipelineExecNodeParamService pipelineExecNodeParamService;

	@Autowired
	private PipelineExecNodeService pipelineExecNodeService;

	@Autowired
	private PipelineExecNodeParamValidator pipelineExecNodeParamValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(pipelineExecNodeParamValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增运行实例节点参数
	 * @param pipelineExecNodeParamAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增运行实例节点参数", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public PipelineExecNodeParamVO add(@RequestBody @Valid PipelineExecNodeParamAddDto pipelineExecNodeParamAddDto){
		PipelineExecNodeParam pipelineExecNodeParam = new PipelineExecNodeParam();
		BeanUtils.copyProperties(pipelineExecNodeParamAddDto, pipelineExecNodeParam);

		pipelineExecNodeParamService.add(pipelineExecNodeParam);

		return  initViewProperty(pipelineExecNodeParam);
	}

	/**
	 * 删除运行实例节点参数,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除运行实例节点参数", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete pipelineExecNodeParam :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			pipelineExecNodeParamService.delete(Long.parseLong(id));
		}

	}



	/**
	 * 根据ID查询运行实例节点参数
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询运行实例节点参数", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  PipelineExecNodeParamVO get(@PathVariable Long id) {

		PipelineExecNodeParam pipelineExecNodeParam = pipelineExecNodeParamService.find(id);

		PipelineExecNodeParamVO vo = initViewProperty(pipelineExecNodeParam);
		return vo;
	}

	/**
	 * 查询运行实例节点参数列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询运行实例节点参数列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<PipelineExecNodeParamVO> list(@RequestBody PageSearchRequest<PipelineExecNodeParamCondition> pageSearchRequest){

		SortCondition sortCondition = pageSearchRequest.getSortCondition();
		Sort sort   = null;
		if(sortCondition!=null){
			sort = new Sort(Sort.Direction.fromStringOrNull(sortCondition.getDirection().toString()), sortCondition.getProperty());
		}
		PageRequest pageRequest = new PageRequest(pageSearchRequest.getPage(), pageSearchRequest.getLimit());
		pageRequest.setSort(sort);
		Page<PipelineExecNodeParam> page = pipelineExecNodeParamService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<PipelineExecNodeParamVO> voList = new ArrayList<>();
		for(PipelineExecNodeParam pipelineExecNodeParam : page.getContent()){
			voList.add(initViewProperty(pipelineExecNodeParam));
		}

		PageContent<PipelineExecNodeParamVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出运行实例节点参数列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出运行实例节点参数列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(PipelineExecNodeParamCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<PipelineExecNodeParamCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<PipelineExecNodeParamVO> content = this.list(pageSearchRequest);

        List<PipelineExecNodeParamVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(PipelineExecNodeParamVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

            headMap.put("node" ,"所属节点");
            headMap.put("name" ,"属性名称");
            headMap.put("code" ,"属性代码");
            headMap.put("value" ,"属性值");
            headMap.put("viewOrder" ,"排序");
            headMap.put("type" ,"类型");

        String title = new String("运行实例节点参数");
        String fileName = new String(("运行实例节点参数_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private PipelineExecNodeParamVO initViewProperty(PipelineExecNodeParam pipelineExecNodeParam){

	    PipelineExecNodeParamVO vo = new PipelineExecNodeParamVO();
        BeanUtils.copyProperties(pipelineExecNodeParam, vo);


	    //初始化其他对象
	    initNodePropertyGroup(vo, pipelineExecNodeParam);
        return vo;

	}


	private void initNodePropertyGroup(PipelineExecNodeParamVO pipelineExecNodeParamVO, PipelineExecNodeParam pipelineExecNodeParam){
	
		PipelineExecNode node = pipelineExecNodeService.find(pipelineExecNodeParam.getNode());
		if(node == null){
			return;
		}
		PipelineExecNodeVO nodeVO = new PipelineExecNodeVO();
		BeanUtils.copyProperties(node, nodeVO);

		pipelineExecNodeParamVO.setNodeVO(nodeVO);

	}


}

