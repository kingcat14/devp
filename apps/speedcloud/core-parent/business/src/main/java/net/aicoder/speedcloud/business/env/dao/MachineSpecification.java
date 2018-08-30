package net.aicoder.speedcloud.business.env.dao;

import net.aicoder.speedcloud.business.env.dto.MachineCondition;
import net.aicoder.speedcloud.business.env.domain.Machine;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

public class MachineSpecification implements Specification<Machine>{

	private MachineCondition condition;

	public MachineSpecification(MachineCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<Machine> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddTidPredicate(predicateList, root, cb);
		tryAddNamePredicate(predicateList, root, cb);
		tryAddIpAddressPredicate(predicateList, root, cb);
		tryAddPortPredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddTidPredicate(List<Predicate> predicateList, Root<Machine> root, CriteriaBuilder cb){

		if (null != condition.getTid() ) {
			predicateList.add(cb.equal(root.get(Machine.PROPERTY_TID).as(Long.class), condition.getTid()));
		}

		if (null != condition.getTidMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(Machine.PROPERTY_TID).as(Long.class), condition.getTidMax()));
		}

		if (null != condition.getTidMin() ) {
			predicateList.add(cb.lessThan(root.get(Machine.PROPERTY_TID).as(Long.class), condition.getTidMin()));
		}
	}
	private void tryAddNamePredicate(List<Predicate> predicateList, Root<Machine> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getName())){
			predicateList.add(cb.like(root.get(Machine.PROPERTY_NAME).as(String.class), "%"+condition.getName()+"%"));
		}
	}
	private void tryAddIpAddressPredicate(List<Predicate> predicateList, Root<Machine> root, CriteriaBuilder cb){
		if(StringUtils.isNotEmpty(condition.getIpAddress())){
			predicateList.add(cb.like(root.get(Machine.PROPERTY_IP_ADDRESS).as(String.class), "%"+condition.getIpAddress()+"%"));
		}
	}
	private void tryAddPortPredicate(List<Predicate> predicateList, Root<Machine> root, CriteriaBuilder cb){

		if (null != condition.getPort() ) {
			predicateList.add(cb.equal(root.get(Machine.PROPERTY_PORT).as(Integer.class), condition.getPort()));
		}

		if (null != condition.getPortMax() ) {
			predicateList.add(cb.greaterThanOrEqualTo(root.get(Machine.PROPERTY_PORT).as(Integer.class), condition.getPortMax()));
		}

		if (null != condition.getPortMin() ) {
			predicateList.add(cb.lessThan(root.get(Machine.PROPERTY_PORT).as(Integer.class), condition.getPortMin()));
		}
	}
}


