package br.com.api.lab03.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.api.lab03.model.UserSystem;

@Repository
public interface UserSystemRepository extends JpaRepository<UserSystem, Integer>{
	
	@Query(value="Select u from UserSystem u where u.password=:ppassword ")
	public UserSystem searchUserToPassword(@Param("ppassword") String password);

	@Query(value="Select u from UserSystem u where u.email=:pemail ")
	public UserSystem searchUserToEmail(@Param("pemail")String email);

}
