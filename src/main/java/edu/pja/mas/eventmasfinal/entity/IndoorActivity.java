package edu.pja.mas.eventmasfinal.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "idActivity")
public class IndoorActivity extends Activity {

	// composition
	@OneToOne
    @JoinColumn(name = "idEvent", nullable = false)
    private Event event;

	
	@NotNull
	private int occupiedSpace;

	public IndoorActivity() {
		super();
		
	}

	private IndoorActivity(@NotBlank(message = "Activity name cant be null or empty") String nameActivity, Event event, int occupiedSpace) {
		super(nameActivity);
		this.event = event;
		setOccupiedSpace(occupiedSpace);
	}

	// Composition  -------------------------------------
		public static void createIndoorActivity(Event event, String nameActivity, int occupiedSpace){
	        if(event == null || nameActivity == null ){
	            throw new IllegalArgumentException("Event or activity name cant be empty");
	        }
	        IndoorActivity newInActivity = new IndoorActivity(nameActivity, event, occupiedSpace);
	        event.addIndoorActivity(newInActivity);

	    }

		public Event getEvent() {
	        return event;
	    }
		
	public int getOccupiedSpace() {
		return occupiedSpace;
	}

	public void setOccupiedSpace(int occupiedSpace) {
		this.occupiedSpace = occupiedSpace;
	}


	@Override
	public String toString() {
		return "IndoorActivity [event=" + event + ", occupiedSpace=" + occupiedSpace + " Activity name : " + super.getNameActivity() + "]";
	}
	
}
