package edu.pja.mas.eventmasfinal.entity;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class Sponsor extends Person{
	
	
	
	// ----- qualifier -----
	@NotBlank(message = "taxNumber is required")
    @Column(unique = true)
    private String taxNumber;
	
	@OneToOne(mappedBy = "sponsor", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
	private Support support;

	
	public Sponsor(String firstName, String lastName, int pesel, String phoneNumber, @NotBlank String taxNumber) {
		super( firstName, lastName, pesel, phoneNumber);
		setTaxNumber(taxNumber);
		
		
	}
	// ------------ qualified association ---------------------
	public Support getSupport() {
		return support;
	}
	public void setSupport(Support support) 
	{
		if (support == null) {
			throw new IllegalArgumentException("Support cant be null");
		}
		this.support = support;
		support.setSponsor(this);
//        if(!support.getSponsor().equals(this)){
//        	support.setSponsor(this);
//        }
	}
	//----------------------------------------------------------
	
	public String getTaxNumber() {
		return taxNumber;
	}

	public void setTaxNumber(String taxNumber) {
		this.taxNumber = taxNumber;
	}
	
	
	@Override
	public String toString() {
		return " Name :" + this.getFirstName() + " " + this.getLastName() + " number : " + this.getPhoneNumber() + " Pesel : " + this.getPesel() + "tax number :"  + taxNumber;
	}
}

