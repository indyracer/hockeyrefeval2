package org.launchcode.refeval.models.dao;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.launchcode.refeval.models.EvaluationRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface RequestEvaluationDao extends CrudRepository<EvaluationRequest, Integer> {
	
	List<EvaluationRequest> findAll();
	EvaluationRequest findByRequestId(int requestId);
	EvaluationRequest findByDate(Date date);

}
