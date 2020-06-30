package edu.pja.mas.eventmasfinal.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import edu.pja.mas.eventmasfinal.entity.Event;

import java.util.List;
import java.util.Optional;

public interface EventRepo extends CrudRepository<Event, Integer> {


    @Query("SELECT e from Event e where e.nameEvent LIKE CONCAT('%', :nameEvent, '%') ORDER BY e.nameEvent")
    List<Event> findByNameEvent(String nameEvent);


    @Query("SELECT e from Event e where e.address LIKE CONCAT('%', :address, '%') ORDER BY e.address")
    List<Event> findByAddress(String address);

    @Query("SELECT e from Event e ORDER BY e.address")
    List<Event> findAllEvents();

    @Query("SELECT e from Event e LEFT JOIN FETCH e.fSupport where e.idEvent = :idEvent")
    Optional<Event> findByIdWithFinancialSupport(int idEvent);

    @Query("SELECT e from Event e LEFT JOIN FETCH e.gSupport where e.idEvent = :idEvent")
    Optional<Event> findByIdWithGoodsSupport(int idEvent);

}
