package edu.pja.mas.eventmasfinal.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Entity
public class Volunteer extends Person{

	@NotNull(message = "Certificate no cannot be null") // mandatory
	private int certificateNo;
	

	public static List<Volunteer> extent = new ArrayList<Volunteer>();
	
	public Volunteer(  String firstName, String lastName, int pesel, String phoneNumber, @NotBlank int certificateNo) {
		super( firstName, lastName, pesel, phoneNumber);
		setCertificateNo(certificateNo);
		extent.add(this);
		
	}
/*	
	// --------------- Dynamic inheritance ---------------------------------------------------
		public void switchSponsor(int taxNumber) {
			
			Person sp1 = new Sponsor(this.getFirstName(), this.getLastName(), this.getPesel(), this.getPhoneNumber(), taxNumber);
			//remove old status from extent
			extent.remove(this);
			
		}
		
		public void switchOrganizer(int spendOnEvent) {
			
			Person org1 = new Organizer(this.getFirstName(), this.getLastName(), this.getPesel(), this.getPhoneNumber(), spendOnEvent);
			//remove old status from extent
			extent.remove(this);
			
		}
		
		//---------------------------------------------------------------------------------------
*/
	public int getCertificateNo() {
		return certificateNo;
	}

	public void setCertificateNo(int certificateNo) {
		this.certificateNo = certificateNo;
	}
	
	public static ArrayList<Volunteer> getExtent() {
		return new ArrayList<Volunteer>(extent);
	}
	
	@Override
	public String toString() {
		return " Name :" + this.getFirstName() + " " + this.getLastName() + " number : " + this.getPhoneNumber() + " Pesel : " + this.getPesel() + "certificate No :"  + certificateNo;
	}
}
