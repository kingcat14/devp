package net.aicoder.speedcloud.business.pipeline.jenkins.dao;

import net.aicoder.speedcloud.business.pipeline.jenkins.domain.JenkinsAdapter;
import net.aicoder.speedcloud.business.pipeline.jenkins.dto.JenkinsAdapterCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class JenkinsAdapterSpecification implements Specification<JenkinsAdapter>{

	private JenkinsAdapterCondition condition;

	public JenkinsAdapterSpecification(JenkinsAdapterCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<JenkinsAdapter> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddTidPredicate(predicateList, root, cb);
		tryAddProjectPredicate(predicateList, root, cb);
		tryAddEnvPredicate(predicateList, root, cb);
		tryAddPortPredicate(predicateList, root, cb);
		tryAddHostPredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddTidPredicate(List<Predicate> predicateList, Root<JenkinsAdapter> root, CriteriaBuilder cb){
        if (null != condition.getTid() ) {
            predicateList.add(cb.equal(root.get(JenkinsAdapter.PROPERTY_TID).as(Long.class), condition.getTid()));
        }  
	}
	private void tryAddProjectPredicate(List<Predicate> predicateList, Root<JenkinsAdapter> root, CriteriaBuilder cb){
	    if (null != condition.getProject() ) {
            predicateList.add(cb.equal(root.get(JenkinsAdapter.PROPERTY_PROJECT).as(String.class), condition.getProject()));
        }
	}
	private void tryAddEnvPredicate(List<Predicate> predicateList, Root<JenkinsAdapter> root, CriteriaBuilder cb){
	    if (null != condition.getEnv() ) {
            predicateList.add(cb.equal(root.get(JenkinsAdapter.PROPERTY_ENV).as(String.class), condition.getEnv()));
        }
	}
	private void tryAddPortPredicate(List<Predicate> predicateList, Root<JenkinsAdapter> root, CriteriaBuilder cb){

		if (null != condition.getPort() ) {
			predicateList.add(cb.equal(root.get(JenkinsAdapter.PROPERTY_PORT).as(Integer.class), condition.getPort()));
		}

		if (null != condition.getPortMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(JenkinsAdapter.PROPERTY_PORT).as(Integer.class), condition.getPortMax()));
		}

		if (null != condition.getPortMin() ) {
			predicateList.add(cb.lessThan(root.get(JenkinsAdapter.PROPERTY_PORT).as(Integer.class), condition.getPortMin()));
		}
	}
	private void tryAddHostPredicate(List<Predicate> predicateList, Root<JenkinsAdapter> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getHost())){
			predicateList.add(cb.like(root.get(JenkinsAdapter.PROPERTY_HOST).as(String.class), "%"+condition.getHost()+"%"));
		}
	}
}


