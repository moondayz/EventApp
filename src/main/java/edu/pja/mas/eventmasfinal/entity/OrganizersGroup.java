package edu.pja.mas.eventmasfinal.entity;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;




@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class OrganizersGroup implements IActiveGroup, IInactiveGroup {
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idGroup;

	@NotEmpty
	@Size(min = 2, message = "group name should be having at least 2 characters") // validation api
	private String groupName;

	//@Size(max = 15, message = " Capacity of the group is max 15")
	private int capacity;

	// ------Subset------------------------------

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizersGroup", cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH} )
	private Set<Organizer> organizers =  new HashSet<>();

	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name = "idManager", nullable = true)
	private Organizer manager;


	//------- dynamic inheritance ------
	private Boolean isAvailable; // active
	private LocalDate whenFinished;  //inactive
	
    private boolean isAbleToJoin;
	

	private Status status;
	public enum Status {
		ACTIVE,
		INACTIVE
	}
	
	public OrganizersGroup() {

	}

	public OrganizersGroup(@Size(min = 3, message = "Group Name should be having at least 3 characters") String groupName,
			@Size(max = 15, message = " Capacity of the group is max 15") int capacity, LocalDate whenFinished, Boolean isAvailable, Status status) {
		super();
		this.groupName = groupName;
		this.capacity = capacity;
		if(status.equals(Status.ACTIVE)) {
			setAvailable(isAvailable);
		}
		else {
			setWhenFinished(whenFinished);
		}
	}

	//------ subset ------ be member of group connection ------------

	public void addOrganizer(Organizer organizer){
		if(organizer== null){
			throw new IllegalArgumentException("Organizer cant be null");
		}
		if(!organizers.contains(organizer)){
			organizers.add(organizer);
			organizer.setOrganizersGroup(this);
		}
	}

	public void removeOrganizer(Organizer organizer){
		if(organizers.contains(organizer)){
			organizers.remove(organizer);
		}
		organizer.setOrganizersGroup(null);
	}
	public Set<Organizer> getOrganizers() {
		return Collections.unmodifiableSet(organizers);
	}

	// ----------- subset  --- be manager connection -----------------

	public Organizer getManager() {
		return manager;
	}

	public void setManager(Organizer manager) {
		if(manager == null ){
			throw new IllegalArgumentException("Manager can not be null");
		}
		// check if manager is a member of group
		if (!organizers.contains(manager)) {
			throw new IllegalArgumentException("Manager must be a member of the group");
		}
		this.manager = manager;
		manager.setManagerAt(this);
	}


	// ---- overriding methods (getter- setter) from interfaces for active-inactive group
		
	  @Override
	    public Boolean isAvailable() {
	        if(!status.equals(Status.ACTIVE)){
	            throw new IllegalArgumentException("Sorry this group is not active at the moment");
	        }
	        return this.isAvailable;
	    }

	    @Override
	    public void setAvailable(Boolean isAvailable) {
	        if(!status.equals(Status.INACTIVE)){
	            throw new IllegalArgumentException("Sorry this group is not active at the moment");
	        }
	        this.isAvailable = isAvailable;
	    }

	
	@Override
	public LocalDate getWhenFinished() {
		if(!status.equals(Status.ACTIVE)) {
			throw new IllegalArgumentException("This group is active.");
		}
		return this.whenFinished;
	}
	
	@Override
	public void setWhenFinished(LocalDate date) {
		if(!status.equals(Status.INACTIVE)) {
			throw new IllegalArgumentException("This group is active.");
		}
		this.whenFinished = date;
	}
	 // -----------------
	 public boolean isAbleToJoin() {
	        return isAbleToJoin;
	    }

	    public void setIsAbleToJoin() {
	        if(status == Status.ACTIVE){
	            this.isAbleToJoin = true;
	        } else {
	            this.isAbleToJoin = false;
	        }
	    }
	
	
	//------------------------------dynamic -inheritance -- switch --------------------------------------------------
	
	public void activate(Boolean isAvailable) {
		status = Status.ACTIVE;
		setAvailable(isAvailable);
		this.whenFinished = null;
	}
	
	public void inactivate(LocalDate whenFinished) {
		status = Status.INACTIVE;
		setWhenFinished(whenFinished);
		this.isAvailable = null;
	}

	public int getIdGroup() {
		return idGroup;
	}

	public void setIdGroup(int idGroup) {
		this.idGroup = idGroup;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public int getCapacity() {
	return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	@Override
	public String toString() {
		return "OrganizersGroup [idGroup=" + idGroup + ", groupName=" + groupName + ", capacity=" + capacity
				+ ", isAvailable=" + isAvailable + ", whenFinished=" + whenFinished + ", isAbleToJoin=" + isAbleToJoin
				+ ", status=" + status + "]";
	}
	
}

