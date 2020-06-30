package edu.pja.mas.eventmasfinal.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotNull;


@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "idFinancialSupport")

public class FinancialSupport {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idFinancialSupport;
	@NotNull
	private int amount;

	// ---- one to one XOR connection----------------
	@OneToOne(mappedBy = "financialSupport")
	private Support support;


	// event to support
	@OneToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH })
	@JoinColumn(name = "idEvent", nullable = false)
	private Event event;

	public FinancialSupport(@NotNull int amount) {

		this.setAmount(amount);

	}

	public FinancialSupport() {

	}


	// Event to support

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		if (event == null) {
			throw new IllegalArgumentException("Event cant be null");
		}
		this.event = event;
		event.addFinancialSupport(this);
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(@NotNull int amount) {
		this.amount = amount;
	}

	public int getIdFinancialSupport() {
		return idFinancialSupport;
	}

	public void setIdFinancialSupport(int idFinancialSupport) {
		this.idFinancialSupport = idFinancialSupport;
	}

	public Support getSupport() {
		return support;
	}

	public void setSupport(Support support) {
		this.support = support;
		if (!support.getFinancialSupport().equals(this)) {
			support.setFinancialSupport(this);
		}

	}

	@Override
	public String toString() {
		return "id : " + idFinancialSupport + " amount : " + amount;
	}

}
