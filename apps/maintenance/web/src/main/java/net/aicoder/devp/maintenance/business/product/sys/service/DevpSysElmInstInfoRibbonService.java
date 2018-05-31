package net.aicoder.devp.maintenance.business.product.sys.service;

import com.yunkang.saas.common.framework.web.controller.PageContent;
import com.yunkang.saas.common.framework.web.data.PageSearchRequest;
import net.aicoder.devp.product.client.sys.DevpSysElmInstInfoRibbon;
import net.aicoder.devp.product.client.sys.result.DevpSysElmInstInfoPageResult;
import net.aicoder.devp.product.client.sys.result.DevpSysElmInstInfoResult;
import net.aicoder.devp.product.business.sys.dto.DevpSysElmInstInfoCondition;
import net.aicoder.devp.product.business.sys.dto.DevpSysElmInstInfoAddDto;
import net.aicoder.devp.product.business.sys.dto.DevpSysElmInstInfoEditDto;
import net.aicoder.devp.product.business.sys.vo.DevpSysElmInstInfoVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("devpSysElmInstInfoRibbonService")
public class DevpSysElmInstInfoRibbonService  {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevpSysElmInstInfoRibbonService.class);


	@Autowired
	private DevpSysElmInstInfoRibbon devpSysElmInstInfoRibbon;


	public DevpSysElmInstInfoVO add(DevpSysElmInstInfoAddDto addDto){
		DevpSysElmInstInfoResult result = devpSysElmInstInfoRibbon.add(addDto);
		return result.getData();
	}

	public void delete(Long id){
		if(null == id){
			LOGGER.warn("try delete T by empty id. Code need check");
			return ;
		}
		LOGGER.debug("delete t:{}", id);
		DevpSysElmInstInfoResult result = devpSysElmInstInfoRibbon.delete(id);
	}
	public DevpSysElmInstInfoVO merge(Long id, DevpSysElmInstInfoEditDto editDto){
		DevpSysElmInstInfoResult result = devpSysElmInstInfoRibbon.update(id, editDto);
		return result.getData();
	}
	public DevpSysElmInstInfoVO find(Long id){
		DevpSysElmInstInfoResult result = devpSysElmInstInfoRibbon.get(id);
		return result.getData();
	}

	public PageContent<DevpSysElmInstInfoVO> list(PageSearchRequest<DevpSysElmInstInfoCondition> pageSearchRequest) {
		DevpSysElmInstInfoPageResult result = devpSysElmInstInfoRibbon.list(pageSearchRequest);
		return result.getData();
	}
}
