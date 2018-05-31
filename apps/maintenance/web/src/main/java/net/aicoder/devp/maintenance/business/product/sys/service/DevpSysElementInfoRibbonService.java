package net.aicoder.devp.maintenance.business.product.sys.service;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.product.client.sys.DevpSysElementInfoRibbon;
import net.aicoder.devp.product.client.sys.result.DevpSysElementInfoPageResult;
import net.aicoder.devp.product.client.sys.result.DevpSysElementInfoResult;
import net.aicoder.devp.product.business.sys.dto.DevpSysElementInfoCondition;
import net.aicoder.devp.product.business.sys.dto.DevpSysElementInfoAddDto;
import net.aicoder.devp.product.business.sys.dto.DevpSysElementInfoEditDto;
import net.aicoder.devp.product.business.sys.vo.DevpSysElementInfoVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("devpSysElementInfoRibbonService")
public class DevpSysElementInfoRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysElementInfoRibbonService.class);


	@Autowired
	private DevpSysElementInfoRibbon devpSysElementInfoRibbon;


	public DevpSysElementInfoVO add(DevpSysElementInfoAddDto addDto){
		DevpSysElementInfoResult result = devpSysElementInfoRibbon.add(addDto);
		return result.getData();
	}

	public void delete(Long id){
		if(null == id){
			LOGGER.warn("try delete T by empty id. Code need check");
			return ;
		}
		LOGGER.debug("delete t:{}", id);
		DevpSysElementInfoResult result = devpSysElementInfoRibbon.delete(id);
	}
	public DevpSysElementInfoVO merge(Long id, DevpSysElementInfoEditDto editDto){
		DevpSysElementInfoResult result = devpSysElementInfoRibbon.update(id, editDto);
		return result.getData();
	}
	public DevpSysElementInfoVO find(Long id){
		DevpSysElementInfoResult result = devpSysElementInfoRibbon.get(id);
		return result.getData();
	}

	public PageContent<DevpSysElementInfoVO> list(PageSearchRequest<DevpSysElementInfoCondition> pageSearchRequest) {
		DevpSysElementInfoPageResult result = devpSysElementInfoRibbon.list(pageSearchRequest);
		return result.getData();
	}
}
