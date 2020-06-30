package edu.pja.mas.eventmasfinal.entity;
import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "idPerson")
public class Organizer extends Person {

	@NotNull(message = "Money spend on Event cannot be null")
	private int spendOnEvent;
	public static List<Organizer> extent = new ArrayList<Organizer>();

	// BAG
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organizer", cascade = { CascadeType.DETACH, CascadeType.MERGE,
			CascadeType.REFRESH })
	private Set<OrganizesWhen> organizesWhen = new HashSet<>();

	//----- SUBSET --------------------------------
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH} )
	@JoinColumn(name = "idGroup",  nullable = true)
	private OrganizersGroup organizersGroup;

	@OneToOne(mappedBy = "manager")
	private OrganizersGroup managerAt;


	public Organizer(String firstName, String lastName, int pesel, String phoneNumber, int spendOnEvent) {
		super(firstName, lastName, pesel, phoneNumber);
		setSpendOnEvent(spendOnEvent);
		extent.add(this);

	}

	public Organizer() {

	}

	// ------subset --------------------

	public OrganizersGroup getOrganizersGroup() {
		return organizersGroup;
	}

	public void setOrganizersGroup(OrganizersGroup organizersGroup) {
		if(this.organizersGroup != organizersGroup){
			if(this.organizersGroup != null){
				OrganizersGroup temp = this.organizersGroup;
				this.organizersGroup = null;
				if(temp.getOrganizers().contains(this)){
					temp.removeOrganizer(this);
				}

			}
			this.organizersGroup = organizersGroup;
			if(organizersGroup != null) {
				organizersGroup.addOrganizer(this);
			}
		}
	}

	public Optional<OrganizersGroup> getManagerAt() {
		return Optional.ofNullable(managerAt);
	}

	public void setManagerAt(OrganizersGroup managerAt) {
		this.managerAt = managerAt;
	}

	
	public void addOrganizesWhen(OrganizesWhen organizesWhen){
        if(organizesWhen == null){
            throw new IllegalArgumentException("OrganizesWhen cant be null");
        }
    //    if(borrowing.getRegisteredCustomer().equals(this)){
            if(!this.organizesWhen.contains(organizesWhen)){
            	this.organizesWhen.add(organizesWhen);
            }
      //  }
    }

    public void removeOrganizesWhen(OrganizesWhen organizesWhen){
        if(organizesWhen.getOrganizer().equals(this)){
            if(this.organizesWhen.contains(organizesWhen)){
            	this.organizesWhen.remove(organizesWhen);
            }
        }
    }

	public int getSpendOnEvent() {
		return spendOnEvent;
	}

	public void setSpendOnEvent(int spendOnEvent) {
		this.spendOnEvent = spendOnEvent;
	}

	public static ArrayList<Organizer> getExtent() {
		return new ArrayList<Organizer>(extent);
	}

	@Override
	public String toString() {
		return "id " + super.getIdPerson() + " Name :" + this.getFirstName() + " " + this.getLastName() + " number : " + this.getPhoneNumber()
				+ " Pesel : " + this.getPesel() + "spend on event :" + spendOnEvent;
	}
}
