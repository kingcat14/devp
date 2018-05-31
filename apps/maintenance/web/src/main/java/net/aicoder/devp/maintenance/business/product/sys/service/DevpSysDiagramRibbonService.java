package net.aicoder.devp.maintenance.business.product.sys.service;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.product.client.sys.DevpSysDiagramRibbon;
import net.aicoder.devp.product.client.sys.result.DevpSysDiagramPageResult;
import net.aicoder.devp.product.client.sys.result.DevpSysDiagramResult;
import net.aicoder.devp.product.business.sys.dto.DevpSysDiagramCondition;
import net.aicoder.devp.product.business.sys.dto.DevpSysDiagramAddDto;
import net.aicoder.devp.product.business.sys.dto.DevpSysDiagramEditDto;
import net.aicoder.devp.product.business.sys.vo.DevpSysDiagramVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("devpSysDiagramRibbonService")
public class DevpSysDiagramRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysDiagramRibbonService.class);


	@Autowired
	private DevpSysDiagramRibbon devpSysDiagramRibbon;


	public DevpSysDiagramVO add(DevpSysDiagramAddDto addDto){
		DevpSysDiagramResult result = devpSysDiagramRibbon.add(addDto);
		return result.getData();
	}

	public void delete(Long id){
		if(null == id){
			LOGGER.warn("try delete T by empty id. Code need check");
			return ;
		}
		LOGGER.debug("delete t:{}", id);
		DevpSysDiagramResult result = devpSysDiagramRibbon.delete(id);
	}
	public DevpSysDiagramVO merge(Long id, DevpSysDiagramEditDto editDto){
		DevpSysDiagramResult result = devpSysDiagramRibbon.update(id, editDto);
		return result.getData();
	}
	public DevpSysDiagramVO find(Long id){
		DevpSysDiagramResult result = devpSysDiagramRibbon.get(id);
		return result.getData();
	}

	public PageContent<DevpSysDiagramVO> list(PageSearchRequest<DevpSysDiagramCondition> pageSearchRequest) {
		DevpSysDiagramPageResult result = devpSysDiagramRibbon.list(pageSearchRequest);
		return result.getData();
	}
}
