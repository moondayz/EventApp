package edu.pja.mas.eventmasfinal.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Activity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idActivity;
	
	@NotBlank(message = "Activity name cant be null or empty")
    private String nameActivity;
	

	@OneToOne
    @JoinColumn(name = "idEvent", nullable = false)
    private Event event;

	
	public Activity() {
		
	}

	public Activity(@NotBlank(message = "Activity name cant be null or empty") String nameActivity) {
	//	this.event = event;
		this.nameActivity = nameActivity;
	}


	public Event getEvent() {
        return event;
    }
	public Long getIdActivity() {
		return idActivity;
	}

	public void setIdActivity(Long idActivity) {
		this.idActivity = idActivity;
	}

	public String getNameActivity() {
		return nameActivity;
	}

	public void setNameActivity(String nameActivity) {
		this.nameActivity = nameActivity;
	}

	@Override
	public String toString() {
		return "Activity [idActivity=" + idActivity + ", nameActivity=" + nameActivity + "]";
	}
	
}
