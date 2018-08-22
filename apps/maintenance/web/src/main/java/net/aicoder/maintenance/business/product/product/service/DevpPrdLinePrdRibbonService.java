package net.aicoder.maintenance.business.product.product.service;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.client.product.DevpPrdLinePrdRibbon;
import net.aicoder.devp.client.product.result.DevpPrdLinePrdPageResult;
import net.aicoder.devp.client.product.result.DevpPrdLinePrdResult;
import net.aicoder.devp.business.product.dto.DevpPrdLinePrdCondition;
import net.aicoder.devp.business.product.dto.DevpPrdLinePrdAddDto;
import net.aicoder.devp.business.product.dto.DevpPrdLinePrdEditDto;
import net.aicoder.devp.business.product.vo.DevpPrdLinePrdVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("devpPrdLinePrdRibbonService")
public class DevpPrdLinePrdRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpPrdLinePrdRibbonService.class);


	@Autowired
	private DevpPrdLinePrdRibbon devpPrdLinePrdRibbon;


	public DevpPrdLinePrdVO add(DevpPrdLinePrdAddDto addDto){
		DevpPrdLinePrdResult result = devpPrdLinePrdRibbon.add(addDto);
		return result.getData();
	}

	public void delete(Long id){
		if(null == id){
			LOGGER.warn("try delete T by empty id. Code need check");
			return ;
		}
		LOGGER.debug("delete t:{}", id);
		DevpPrdLinePrdResult result = devpPrdLinePrdRibbon.delete(id);
	}
	public DevpPrdLinePrdVO merge(Long id, DevpPrdLinePrdEditDto editDto){
		DevpPrdLinePrdResult result = devpPrdLinePrdRibbon.update(id, editDto);
		return result.getData();
	}
	public DevpPrdLinePrdVO find(Long id){
		DevpPrdLinePrdResult result = devpPrdLinePrdRibbon.get(id);
		return result.getData();
	}

	public PageContent<DevpPrdLinePrdVO> list(PageSearchRequest<DevpPrdLinePrdCondition> pageSearchRequest) {
		DevpPrdLinePrdPageResult result = devpPrdLinePrdRibbon.list(pageSearchRequest);
		return result.getData();
	}
}
