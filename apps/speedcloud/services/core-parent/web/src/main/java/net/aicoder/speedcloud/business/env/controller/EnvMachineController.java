package net.aicoder.speedcloud.business.env.controller;

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
import net.aicoder.speedcloud.business.env.domain.AppEnvConfig;
import net.aicoder.speedcloud.business.env.domain.EnvMachine;
import net.aicoder.speedcloud.business.env.domain.Machine;
import net.aicoder.speedcloud.business.env.dto.EnvMachineAddDto;
import net.aicoder.speedcloud.business.env.dto.EnvMachineCondition;
import net.aicoder.speedcloud.business.env.dto.EnvMachineEditDto;
import net.aicoder.speedcloud.business.env.service.AppEnvConfigService;
import net.aicoder.speedcloud.business.env.service.EnvMachineService;
import net.aicoder.speedcloud.business.env.service.MachineService;
import net.aicoder.speedcloud.business.env.valid.EnvMachineValidator;
import net.aicoder.speedcloud.business.env.vo.AppEnvConfigVO;
import net.aicoder.speedcloud.business.env.vo.EnvMachineVO;
import net.aicoder.speedcloud.business.env.vo.MachineVO;
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
 * 管理环境设备关联
 * @author icode
 */
@Api(description = "环境设备关联", tags = "EnvMachine")
@RestController
@RequestMapping(value = "/speedcloud/env/envmachine")
public class EnvMachineController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EnvMachineController.class);


	@Autowired
	private EnvMachineService envMachineService;

	@Autowired
	private AppEnvConfigService appEnvConfigService;
	@Autowired
	private MachineService machineService;


	@Autowired
	private EnvMachineValidator envMachineValidator;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(envMachineValidator);
		webDataBinder.registerCustomEditor(Date.class, new DateConverter());
	}

	/**
	 * 新增环境设备关联
	 * @param envMachineAddDto
	 * @return
	 */
	@ApiOperation(value = "新增", notes = "新增环境设备关联", httpMethod = "POST")
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
  	@BusinessFuncMonitor(value = "speedcloud.env.envmachine.add", count = true)
	public EnvMachineVO add(@RequestBody @Valid EnvMachineAddDto envMachineAddDto){
		EnvMachine envMachine = new EnvMachine();
		BeanUtils.copyProperties(envMachineAddDto, envMachine);

		envMachineService.add(envMachine);

		return  initViewProperty(envMachine);
	}

	/**
	 * 删除环境设备关联,id以逗号分隔
	 * @param idArray
	 */
	@ApiOperation(value = "删除", notes = "删除环境设备关联", httpMethod = "DELETE")
	@DeleteMapping(path="/{idArray}")
  	@BusinessFuncMonitor(value = "speedcloud.env.envmachine.delete", count = true)
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete envMachine :{}", idArray);

		String[] ids = idArray.split(",");
		for (String id : ids ){
			envMachineService.delete(Long.parseLong(id));
		}

	}

	/**
	 * 更新环境设备关联
	 * @param envMachineEditDto
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改", notes = "修改环境设备关联(修改全部字段,未传入置空)", httpMethod = "PUT")
	@PutMapping(path="/{id}")
  	@BusinessFuncMonitor(value = "speedcloud.env.envmachine.update", count = true)
	public	EnvMachineVO update(@RequestBody @Valid EnvMachineEditDto envMachineEditDto, @PathVariable Long id){
		EnvMachine envMachine = envMachineService.find(id);
		BeanUtils.copyProperties(envMachineEditDto, envMachine);
		envMachine.setId(id);
		envMachineService.merge(envMachine);

		EnvMachineVO vo = initViewProperty(envMachine);
		return  vo;
	}

	/**
	 * 根据ID查询环境设备关联
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "根据ID查询", notes = "根据ID查询环境设备关联", httpMethod = "GET")
	@GetMapping(path="/{id}")
  	@BusinessFuncMonitor(value = "speedcloud.env.envmachine.get")
	public  EnvMachineVO get(@PathVariable Long id) {

		EnvMachine envMachine = envMachineService.find(id);
		if(envMachine == null){
			throw new ResourceNotFoundException("找不到指定的环境设备关联，请检查ID");
		}
		EnvMachineVO vo = initViewProperty(envMachine);
		return vo;
	}

	/**
	 * 查询环境设备关联列表
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询环境设备关联列表", httpMethod = "POST")
	@PostMapping(path="/list")
	@BusinessFuncMonitor(value = "speedcloud.env.envmachine.list")
	public PageContent<EnvMachineVO> list(@RequestBody PageSearchRequest<EnvMachineCondition> pageSearchRequest){

		PageRequest pageRequest = PageRequestConvert.convert(pageSearchRequest);
      
		Page<EnvMachine> page = envMachineService.find(pageSearchRequest.getSearchCondition(), pageRequest);

		List<EnvMachineVO> voList = new ArrayList<>();
		for(EnvMachine envMachine : page.getContent()){
			voList.add(initViewProperty(envMachine));
		}

		PageContent<EnvMachineVO> pageContent = new PageContent<>(voList, page.getTotalElements());
		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	/**
     * 导出环境设备关联列表
     * @param condition
     * @param response
     */
    @ApiOperation(value = "导出", notes = "根据条件导出环境设备关联列表", httpMethod = "POST")
    @RequestMapping(path="/export")
    public void export(EnvMachineCondition condition, HttpServletResponse response) throws UnsupportedEncodingException {

        PageSearchRequest<EnvMachineCondition> pageSearchRequest = new PageSearchRequest<>();
        pageSearchRequest.setPage(0);
        pageSearchRequest.setLimit(Integer.MAX_VALUE);
        pageSearchRequest.setSearchCondition(condition);

        PageContent<EnvMachineVO> content = this.list(pageSearchRequest);

        List<EnvMachineVO> voList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(content.getContent())){
            voList.addAll(content.getContent());
        }

        JSONArray jsonArray = new JSONArray();
        for(EnvMachineVO vo : voList){
            jsonArray.add(vo);
        }

        Map<String,String> headMap = new LinkedHashMap<>();

        headMap.put("evn" ,"环境");
        headMap.put("machine" ,"机器");

        String title = new String("环境设备关联");
        String fileName = new String(("环境设备关联_"+ DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(new Date())).getBytes("UTF-8"), "ISO-8859-1");
        ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response, fileName);
    }

	private EnvMachineVO initViewProperty(EnvMachine envMachine){

	    EnvMachineVO vo = new EnvMachineVO();
        BeanUtils.copyProperties(envMachine, vo);


	    //初始化其他对象
	    initEvnPropertyGroup(vo, envMachine);
	    initMachinePropertyGroup(vo, envMachine);
        return vo;

	}

	private void initEvnPropertyGroup(EnvMachineVO envMachineVO, EnvMachine envMachine){
	
		AppEnvConfig evn = appEnvConfigService.find(envMachine.getEvn());
		if(evn == null){
			return;
		}
		AppEnvConfigVO evnVO = new AppEnvConfigVO();
		BeanUtils.copyProperties(evn, evnVO);

		envMachineVO.setEvnVO(evnVO);

	}
	private void initMachinePropertyGroup(EnvMachineVO envMachineVO, EnvMachine envMachine){
	
		Machine machine = machineService.find(envMachine.getMachine());
		if(machine == null){
			return;
		}
		MachineVO machineVO = new MachineVO();
		BeanUtils.copyProperties(machine, machineVO);

		envMachineVO.setMachineVO(machineVO);

	}

}

