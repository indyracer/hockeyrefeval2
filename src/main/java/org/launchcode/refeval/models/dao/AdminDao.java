package org.launchcode.refeval.models.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.launchcode.refeval.models.Admin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface AdminDao extends CrudRepository<Admin, Integer>{
	
	List<Admin> findAll();
	List<Admin> findByUid(int uid);

}
