package org.launchcode.refeval.models.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.launchcode.refeval.models.EvaluationInput;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface EvaluationInputDao extends CrudRepository<EvaluationInput, Integer>{
	
	List<EvaluationInput> findAll();
	EvaluationInput findByUid(int uid);
	EvaluationInput findByEvaluationDate(String evaluationDate);
	EvaluationInput findByOfficialFirstName(String officialFirstName);
	EvaluationInput findByOfficialLastName(String officialLastName);
	List<EvaluationInput> findByOffUid(int offUid);

}
