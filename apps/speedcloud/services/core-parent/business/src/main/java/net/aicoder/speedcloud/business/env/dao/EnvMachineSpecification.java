package net.aicoder.speedcloud.business.env.dao;

import net.aicoder.speedcloud.business.env.domain.EnvMachine;
import net.aicoder.speedcloud.business.env.dto.EnvMachineCondition;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class EnvMachineSpecification implements Specification<EnvMachine>{

	private EnvMachineCondition condition;

	public EnvMachineSpecification(EnvMachineCondition condition){
		this.condition = condition;
	}

	@Override
	public Predicate toPredicate(Root<EnvMachine> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicateList = new ArrayList<>();

		if(condition==null){
			return null;
		}

		tryAddTidPredicate(predicateList, root, cb);
		tryAddEvnPredicate(predicateList, root, cb);
		tryAddMachinePredicate(predicateList, root, cb);


		Predicate[] pre = new Predicate[predicateList.size()];
		pre = predicateList.toArray(pre);
		return cb.and(pre);
    }


	private void tryAddTidPredicate(List<Predicate> predicateList, Root<EnvMachine> root, CriteriaBuilder cb){

		if (null != condition.getTid() ) {
			predicateList.add(cb.equal(root.get(EnvMachine.PROPERTY_TID).as(Long.class), condition.getTid()));
		}

	}
	private void tryAddEvnPredicate(List<Predicate> predicateList, Root<EnvMachine> root, CriteriaBuilder cb){
	    if (null != condition.getEvn() ) {
            predicateList.add(cb.equal(root.get(EnvMachine.PROPERTY_EVN).as(Long.class), condition.getEvn()));
        }
	}
	private void tryAddMachinePredicate(List<Predicate> predicateList, Root<EnvMachine> root, CriteriaBuilder cb){
	    if (null != condition.getMachine() ) {
            predicateList.add(cb.equal(root.get(EnvMachine.PROPERTY_MACHINE).as(Long.class), condition.getMachine()));
        }
	}
}


