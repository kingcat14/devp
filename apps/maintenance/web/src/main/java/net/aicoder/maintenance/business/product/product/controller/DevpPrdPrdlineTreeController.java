package net.aicoder.maintenance.business.product.product.controller;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.aicoder.devp.business.product.dto.DevpPrdLinePrdCondition;
import net.aicoder.devp.business.product.dto.DevpPrdPrdlineAddDto;
import net.aicoder.devp.business.product.dto.DevpPrdPrdlineCondition;
import net.aicoder.devp.business.product.dto.DevpPrdPrdlineEditDto;
import net.aicoder.devp.business.product.vo.DevpPrdLinePrdVO;
import net.aicoder.devp.business.product.vo.DevpPrdPrdlineVO;
import net.aicoder.devp.business.product.vo.DevpPrdProductVO;
import net.aicoder.maintenance.business.product.product.service.DevpPrdLinePrdRibbonService;
import net.aicoder.maintenance.business.product.product.service.DevpPrdPrdlineRibbonService;
import net.aicoder.maintenance.business.product.product.service.DevpPrdProductRibbonService;
import net.aicoder.maintenance.business.product.product.valid.DevpPrdPrdlineValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 管理产品线定义
 * @author icode
 */
@Api(description = "产品线定义", tags = "DevpPrdPrdline")
@RestController
@RequestMapping(value = "/product/devpPrdPrdline")
public class DevpPrdPrdlineTreeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpPrdPrdlineTreeController.class);


	@Autowired
	private DevpPrdPrdlineRibbonService devpPrdPrdlineRibbonService;

	@Autowired
	private DevpPrdProductRibbonService devpPrdProductRibbonService;

	@Autowired
	private DevpPrdLinePrdRibbonService devpPrdLinePrdRibbonService;

	@Autowired
	DevpPrdPrdlineValidator devpPrdPrdlineValidator;

    @InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(devpPrdPrdlineValidator);
	}

	/**
	 * 查询产品线
	 * @param pageSearchRequest
	 * @return
	 */
	@ApiOperation(value = "查询", notes = "根据条件查询产品线及包含的产品", httpMethod = "POST")
	@PostMapping("/tree")
	public PageContent<DevpPrdPrdlineVO> tree(@RequestBody PageSearchRequest<DevpPrdPrdlineCondition> pageSearchRequest){

		PageContent<DevpPrdPrdlineVO> pageContent = devpPrdPrdlineRibbonService.list(pageSearchRequest);

		for(DevpPrdPrdlineVO lineVo : pageContent.getContent()){
			List<DevpPrdProductVO> productVOList = this.findProductListForLine(lineVo.getId());
			lineVo.setProductList(productVOList);
		}


		LOGGER.debug("page Size :{}, total:{}", pageContent.getContent().size() ,pageContent.getTotal());
		return pageContent;

	}

	private List<DevpPrdProductVO> findProductListForLine(Long lineId){
		DevpPrdLinePrdCondition condition = new DevpPrdLinePrdCondition();
		condition.setLineRid(lineId);

		PageSearchRequest<DevpPrdLinePrdCondition> productCondition = new PageSearchRequest<>();
		productCondition.setSearchCondition(condition);
		productCondition.setLimit(10000);
		productCondition.setPage(0);

		PageContent<DevpPrdLinePrdVO>  devpPrdLinePrdVOPageContent = this.devpPrdLinePrdRibbonService.list(productCondition);

		List<DevpPrdProductVO> result = new ArrayList<>();
		for(DevpPrdLinePrdVO vo : devpPrdLinePrdVOPageContent.getContent()){
			DevpPrdProductVO productVO = this.devpPrdProductRibbonService.find(vo.getPrdRid());
			result.add(productVO);
		}

		return result;
	}
}
