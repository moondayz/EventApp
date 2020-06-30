package edu.pja.mas.eventmasfinal.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class OrganizesWhen {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idOrganizesWhen;
	
	@NotNull(message = "Event date is required")
    private LocalDate dateEvent;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEvent",  nullable = false)
    private Event event;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPerson",  nullable = false)
    private Organizer organizer;
	
	public OrganizesWhen() {
		
	}
	

	public OrganizesWhen(@NotNull(message = "Event date is required") LocalDate dateEvent, Event event,
			Organizer organizer) {
		setDateEvent(dateEvent);
		setEvent(event);
		setOrganizer(organizer);
	}


	public int getIdOrganizesWhen() {
		return idOrganizesWhen;
	}

	public void setIdOrganizesWhen(int idOrganizesWhen) {
		this.idOrganizesWhen = idOrganizesWhen;
	}

	public LocalDate getDateEvent() {
		return dateEvent;
	}

	public void setDateEvent(LocalDate dateEvent) {
		this.dateEvent = dateEvent;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
		event.setIsOrganized(true);
		event.addOrganizesWhen(this);
	}

	public Organizer getOrganizer() {
		return organizer;
	}

	public void setOrganizer(Organizer organizer) {
		this.organizer = organizer;
		organizer.addOrganizesWhen(this);
	}

	@Override
	public String toString() {
		return "OrganizesWhen [idOrganizesWhen=" + idOrganizesWhen + ", dateEvent=" + dateEvent + ", event=" + event
				+ ", organizer=" + organizer + "]";
	}
	
	

}
