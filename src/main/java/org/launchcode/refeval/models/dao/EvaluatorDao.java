package org.launchcode.refeval.models.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.launchcode.refeval.models.Evaluator;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Transactional
@Repository
public interface EvaluatorDao extends CrudRepository<Evaluator, Integer>{
	
	List<Evaluator> findAll();
	List<Evaluator> findByUid(int uid);

}
