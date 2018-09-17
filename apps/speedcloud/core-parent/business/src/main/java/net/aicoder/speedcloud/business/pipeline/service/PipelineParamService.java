package net.aicoder.speedcloud.business.pipeline.service;


import com.yunkang.saas.common.jpa.GenericCrudService;
import net.aicoder.speedcloud.business.pipeline.dao.PipelineParamDao;
import net.aicoder.speedcloud.business.pipeline.dao.PipelineParamSpecification;
import net.aicoder.speedcloud.business.pipeline.domain.PipelineParam;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineParamAddDto;
import net.aicoder.speedcloud.business.pipeline.dto.PipelineParamCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("pipelineParamService")
public class PipelineParamService  extends GenericCrudService<PipelineParam, Long, PipelineParamCondition, PipelineParamDao> {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipelineParamService.class);


	public void create(List<PipelineParamAddDto> pipelineParamAddDtoList){

		if(CollectionUtils.isEmpty(pipelineParamAddDtoList)){
			return;
		}
		PipelineParam param = null;
		for(PipelineParamAddDto addDto:pipelineParamAddDtoList){
			param = new PipelineParam();
			BeanUtils.copyProperties(addDto, param);
			this.add(param);
		}
	}

	public int deleteForPipeline(Long pipelineId){
		return dao.deleteByPipeline(pipelineId);
	}

	@Override
	public Specification<PipelineParam> getSpecification(PipelineParamCondition condition) {
		return new PipelineParamSpecification(condition);
	}

	public Sort getDefaultSort(){

		Sort sort = new Sort(Sort.Direction.DESC, PipelineParam.PROPERTY_PIPELINE);
		return sort;
	}
}