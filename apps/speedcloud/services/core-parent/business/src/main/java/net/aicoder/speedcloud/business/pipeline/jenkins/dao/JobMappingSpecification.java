package net.aicoder.speedcloud.business.pipeline.jenkins.dao;

import net.aicoder.speedcloud.business.pipeline.jenkins.domain.JobMapping;
import net.aicoder.speedcloud.business.pipeline.jenkins.dto.JobMappingCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class JobMappingSpecification implements Specification<JobMapping>{

	private JobMappingCondition condition;

	public JobMappingSpecification(JobMappingCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<JobMapping> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddTidPredicate(predicateList, root, cb);
		tryAddTaskTypePredicate(predicateList, root, cb);
		tryAddJobInPlatformPredicate(predicateList, root, cb);
		tryAddJobInPlatformNamePredicate(predicateList, root, cb);
		tryAddJobInJenkinsNamePredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddTidPredicate(List<Predicate> predicateList, Root<JobMapping> root, CriteriaBuilder cb){
        if (null != condition.getTid() ) {
            predicateList.add(cb.equal(root.get(JobMapping.PROPERTY_TID).as(Long.class), condition.getTid()));
        }  
	}
	private void tryAddTaskTypePredicate(List<Predicate> predicateList, Root<JobMapping> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getTaskType())){
			predicateList.add(cb.like(root.get(JobMapping.PROPERTY_TASK_TYPE).as(String.class), "%"+condition.getTaskType()+"%"));
		}
	}
	private void tryAddJobInPlatformPredicate(List<Predicate> predicateList, Root<JobMapping> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getJobInPlatform())){
			predicateList.add(cb.like(root.get(JobMapping.PROPERTY_JOB_IN_PLATFORM).as(String.class), "%"+condition.getJobInPlatform()+"%"));
		}
	}
	private void tryAddJobInPlatformNamePredicate(List<Predicate> predicateList, Root<JobMapping> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getJobInPlatformName())){
			predicateList.add(cb.like(root.get(JobMapping.PROPERTY_JOB_IN_PLATFORM_NAME).as(String.class), "%"+condition.getJobInPlatformName()+"%"));
		}
	}
	private void tryAddJobInJenkinsNamePredicate(List<Predicate> predicateList, Root<JobMapping> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getJobInJenkinsName())){
			predicateList.add(cb.like(root.get(JobMapping.PROPERTY_JOB_IN_JENKINS_NAME).as(String.class), "%"+condition.getJobInJenkinsName()+"%"));
		}
	}
}


