package edu.pja.mas.eventmasfinal.entity;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotBlank;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "idActivity")
public class OutdoorActivity extends Activity {

	@Basic(optional = true)
	private String equipmentName;

	// composition
	@OneToOne
	@JoinColumn(name = "idEvent", nullable = false)
	private Event event;

	public OutdoorActivity() {
		super();

	}

	private OutdoorActivity(@NotBlank(message = "Activity name cant be null or empty") String nameActivity,
			String equipmentName, Event event) {
		super(nameActivity);
		this.event = event;
		this.equipmentName = equipmentName;
	}

	// Composition -------------------------------------
	public static void createOutdoorActivity(Event event, String nameActivity, String equipmentName) {
		if (event == null || nameActivity == null) {
			throw new IllegalArgumentException("Event or activity name cant be empty");
		}
		OutdoorActivity newOutActivity = new OutdoorActivity(nameActivity, equipmentName, event);
		event.addOutdoorActivity(newOutActivity);

	}

	public Event getEvent() {
		return event;
	}

	public String getEquipmentName() {
		return equipmentName;
	}

	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}

	@Override
	public String toString() {
		return "OutdoorActivity [equipmentName=" + equipmentName + " Activity name : " + this.getNameActivity() + "]";
	}

}
