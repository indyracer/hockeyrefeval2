package org.launchcode.refeval.models;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import org.springframework.context.annotation.Primary;

@MappedSuperclass
public class AbstractUser {

	private int uid;
	
	@Id
	@GeneratedValue
	@NotNull
	@Column(name = "uid", unique = true)
	@Primary
	public int getUid(){
		return this.uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	
	
}