package edu.pja.mas.eventmasfinal.repository;


import java.util.List;
 
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import edu.pja.mas.eventmasfinal.entity.OrganizersGroup;

public interface OrganizersGroupRepo extends CrudRepository<OrganizersGroup, Integer> {

	 
	List<OrganizersGroup>  findByCapacity(int capacity);
/*	
	@Query(value= "SELECT o from OrganizersGroup o LEFT JOIN FETCH o.orgs WHERE o.id=:id")
	Optional<OrganizersGroup> findById(@Param("id")Integer id);
	*/	
}
