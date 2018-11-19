package net.aicoder.speedcloud.business.pipeline.controller;

import com.alibaba.fastjson.JSONArray;
import com.yunkang.saas.common.framework.spring.DateConverter;
import com.yunkang.saas.common.framework.web.ExcelUtil;
import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageRequest;
import com.yunkang.saas.common.framework.web.data.PageRequestConvert;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.aicoder.speedcloud.business.app.domain.CodeRepository;
import net.aicoder.speedcloud.business.app.service.CodeRepositoryService;
import net.aicoder.speedcloud.business.app.vo.CodeRepositoryVO;
import net.aicoder.speedcloud.business.pipeline.domain.Pipeline;
import net.aicoder.speedcloud.business.pipeline.domain.PipelineCodeRepositoryRelation;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineCodeRepositoryRelationAddDto;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineCodeRepositoryRelationCondition;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineCodeRepositoryRelationEditDto;
import net.aicoder.speedcloud.business.pipeline.service.PipelineCodeRepositoryRelationService;
import net.aicoder.speedcloud.business.pipeline.service.PipelineService;
import net.aicoder.speedcloud.business.pipeline.valid.PipelineCodeRepositoryRelationValidator;
import net.aicoder.speedcloud.business.pipeline.vo.PipelineCodeRepositoryRelationVO;
import net.aicoder.speedcloud.business.pipeline.vo.PipelineVO;
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
 * 管理流水线代码库关联
 * @author icode
 */
@Api(description = "流水线代码库关联", tags = "PipelineCodeRepositoryRelation")
@RestController
@RequestMapping(value = "/speedcloud/pipeline/pipelinecoderepositoryrelation")
public class PipelineCodeRepositoryRelationController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineCodeRepositoryRelationController.class);


	@Autowired
	private PipelineCodeRepositoryRelationService pipelineCodeRepositoryRelationService;

	@Autowired
	private CodeRepositoryService codeRepositoryService;
	@Autowired
	private PipelineService pipelineService;


	@Autowired
	private PipelineCodeRepositoryRelationValidator pipelineCodeRepositoryRelationValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(pipelineCodeRepositoryRelationValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增流水线代码库关联
	 * @param pipelineCodeRepositoryRelationAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增流水线代码库关联", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public PipelineCodeRepositoryRelationVO add(@RequestBody @Valid PipelineCodeRepositoryRelationAddDto pipelineCodeRepositoryRelationAddDto){
		PipelineCodeRepositoryRelation pipelineCodeRepositoryRelation = new PipelineCodeRepositoryRelation();
		BeanUtils.copyProperties(pipelineCodeRepositoryRelationAddDto, pipelineCodeRepositoryRelation);

		pipelineCodeRepositoryRelationService.add(pipelineCodeRepositoryRelation);

		return  initViewProperty(pipelineCodeRepositoryRelation);
	}

	/**
	 * 删除流水线代码库关联,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除流水线代码库关联", httpMethod = "DELETE")
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete pipelineCodeRepositoryRelation :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			pipelineCodeRepositoryRelationService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新流水线代码库关联
	 * @param pipelineCodeRepositoryRelationEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改产流水线代码库关联(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(value="/{id}")
	public	PipelineCodeRepositoryRelationVO update(@RequestBody @Valid PipelineCodeRepositoryRelationEditDto pipelineCodeRepositoryRelationEditDto, @PathVariable Long id){
		PipelineCodeRepositoryRelation pipelineCodeRepositoryRelation = new PipelineCodeRepositoryRelation();
		BeanUtils.copyProperties(pipelineCodeRepositoryRelationEditDto, pipelineCodeRepositoryRelation);
		pipelineCodeRepositoryRelation.setId(id);
		pipelineCodeRepositoryRelationService.merge(pipelineCodeRepositoryRelation);

		PipelineCodeRepositoryRelationVO vo = initViewProperty(pipelineCodeRepositoryRelation);
		return  vo;
	}

	/**
	 * 根据ID查询流水线代码库关联
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据ID查询流水线代码库关联", httpMethod = "GET")
	@GetMapping(value="/{id}")
	public  PipelineCodeRepositoryRelationVO get(@PathVariable Long id) {

		PipelineCodeRepositoryRelation pipelineCodeRepositoryRelation = pipelineCodeRepositoryRelationService.find(id);

		PipelineCodeRepositoryRelationVO vo = initViewProperty(pipelineCodeRepositoryRelation);
		return vo;
	}

	/**
	 * 查询流水线代码库关联列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询流水线代码库关联列表", httpMethod = "POST")
	@PostMapping("/list")
	public PageContent<PipelineCodeRepositoryRelationVO> list(@RequestBody PageSearchRequest<PipelineCodeRepositoryRelationCondition> pageSearchRequest){

		PageRequest pageRequest = PageRequestConvert.convert(pageSearchRequest);

		Page<PipelineCodeRepositoryRelation> page = pipelineCodeRepositoryRelationService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<PipelineCodeRepositoryRelationVO> voList = new ArrayList<>();
		for(PipelineCodeRepositoryRelation pipelineCodeRepositoryRelation : page.getContent()){
			voList.add(initViewProperty(pipelineCodeRepositoryRelation));
		}

		PageContent<PipelineCodeRepositoryRelationVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出流水线代码库关联列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出流水线代码库关联列表", httpMethod = "POST")
    @RequestMapping("/export")
    public void export(PipelineCodeRepositoryRelationCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<PipelineCodeRepositoryRelationCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<PipelineCodeRepositoryRelationVO> content = this.list(pageSearchRequest);

        List<PipelineCodeRepositoryRelationVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(PipelineCodeRepositoryRelationVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<String,String>();

            headMap.put("codeRepository" ,"代码库");
            headMap.put("pipeline" ,"流水线");
            headMap.put("autoStart" ,"提交代码触发流水线");

        String title = new String("流水线代码库关联");
        String fileName = new String(("流水线代码库关联_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private PipelineCodeRepositoryRelationVO initViewProperty(PipelineCodeRepositoryRelation pipelineCodeRepositoryRelation){

	    PipelineCodeRepositoryRelationVO vo = new PipelineCodeRepositoryRelationVO();
        BeanUtils.copyProperties(pipelineCodeRepositoryRelation, vo);


	    //初始化其他对象
	    initCodeRepositoryPropertyGroup(vo, pipelineCodeRepositoryRelation);
	    initPipelinePropertyGroup(vo, pipelineCodeRepositoryRelation);
        return vo;

	}


	private void initCodeRepositoryPropertyGroup(PipelineCodeRepositoryRelationVO pipelineCodeRepositoryRelationVO, PipelineCodeRepositoryRelation pipelineCodeRepositoryRelation){
	
		CodeRepository codeRepository = codeRepositoryService.find(pipelineCodeRepositoryRelation.getCodeRepository());
		if(codeRepository == null){
			return;
		}
		CodeRepositoryVO codeRepositoryVO = new CodeRepositoryVO();
		BeanUtils.copyProperties(codeRepository, codeRepositoryVO);

		pipelineCodeRepositoryRelationVO.setCodeRepositoryVO(codeRepositoryVO);

	}


	private void initPipelinePropertyGroup(PipelineCodeRepositoryRelationVO pipelineCodeRepositoryRelationVO, PipelineCodeRepositoryRelation pipelineCodeRepositoryRelation){
	
		Pipeline pipeline = pipelineService.find(pipelineCodeRepositoryRelation.getPipeline());
		if(pipeline == null){
			return;
		}
		PipelineVO pipelineVO = new PipelineVO();
		BeanUtils.copyProperties(pipeline, pipelineVO);

		pipelineCodeRepositoryRelationVO.setPipelineVO(pipelineVO);

	}


}

