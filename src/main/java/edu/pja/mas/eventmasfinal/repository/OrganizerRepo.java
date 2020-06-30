package edu.pja.mas.eventmasfinal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import edu.pja.mas.eventmasfinal.entity.Organizer;

public interface OrganizerRepo  extends CrudRepository<Organizer, Integer> {


	@Query("select o from Organizer o where o.phoneNumber=:phoneNumber ") 
	List<Organizer> findByPhoneNumber(@Param("phoneNumber") String phoneNumber);
}
