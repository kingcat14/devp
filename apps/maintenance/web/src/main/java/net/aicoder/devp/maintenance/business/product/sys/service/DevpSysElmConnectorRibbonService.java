package net.aicoder.devp.maintenance.business.product.sys.service;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.product.client.sys.DevpSysElmConnectorRibbon;
import net.aicoder.devp.product.client.sys.result.DevpSysElmConnectorPageResult;
import net.aicoder.devp.product.client.sys.result.DevpSysElmConnectorResult;
import net.aicoder.devp.product.business.sys.dto.DevpSysElmConnectorCondition;
import net.aicoder.devp.product.business.sys.dto.DevpSysElmConnectorAddDto;
import net.aicoder.devp.product.business.sys.dto.DevpSysElmConnectorEditDto;
import net.aicoder.devp.product.business.sys.vo.DevpSysElmConnectorVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("devpSysElmConnectorRibbonService")
public class DevpSysElmConnectorRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysElmConnectorRibbonService.class);


	@Autowired
	private DevpSysElmConnectorRibbon devpSysElmConnectorRibbon;


	public DevpSysElmConnectorVO add(DevpSysElmConnectorAddDto addDto){
		DevpSysElmConnectorResult result = devpSysElmConnectorRibbon.add(addDto);
		return result.getData();
	}

	public void delete(Long id){
		if(null == id){
			LOGGER.warn("try delete T by empty id. Code need check");
			return ;
		}
		LOGGER.debug("delete t:{}", id);
		DevpSysElmConnectorResult result = devpSysElmConnectorRibbon.delete(id);
	}
	public DevpSysElmConnectorVO merge(Long id, DevpSysElmConnectorEditDto editDto){
		DevpSysElmConnectorResult result = devpSysElmConnectorRibbon.update(id, editDto);
		return result.getData();
	}
	public DevpSysElmConnectorVO find(Long id){
		DevpSysElmConnectorResult result = devpSysElmConnectorRibbon.get(id);
		return result.getData();
	}

	public PageContent<DevpSysElmConnectorVO> list(PageSearchRequest<DevpSysElmConnectorCondition> pageSearchRequest) {
		DevpSysElmConnectorPageResult result = devpSysElmConnectorRibbon.list(pageSearchRequest);
		return result.getData();
	}
}
