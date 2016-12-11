package org.launchcode.refeval.models.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.launchcode.refeval.models.Official;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface OfficialDao extends CrudRepository<Official, Integer>{
	
	List<Official> findAll();
	Official findByUid(int uid);
	Official findByUsername(String username);
	Official findByFirstName(String firstName);
	

}
