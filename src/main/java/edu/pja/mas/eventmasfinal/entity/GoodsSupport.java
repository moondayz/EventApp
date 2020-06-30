package edu.pja.mas.eventmasfinal.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "idGoodsSupport")
public class GoodsSupport {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idGoodsSupport;
	@NotNull
	private int amount;
	@NotEmpty(message = "Goods name cant be null")
	private String goodsName;

	// ---- one to one XOR connection----------------
	@OneToOne(mappedBy = "goodsSupport")
	private Support support;


	// event to support
		@OneToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH })
		@JoinColumn(name = "idEvent", nullable = false)
		private Event event;

	public GoodsSupport() {

	}

	public GoodsSupport(@NotNull int amount, @NotEmpty(message = "Goods name cant be null") String goodsName) {
		
		this.amount = amount;
		this.goodsName = goodsName;
		
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
			event.addGoodsSupport(this);
		}

	public int getIdGoodsSupport() {
		return idGoodsSupport;
	}

	public void setIdGoodsSupport(int idGoodsSupport) {
		this.idGoodsSupport = idGoodsSupport;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public Support getSupport() {
		return support;
	}

	public void setSupport(Support support) {
		this.support = support;
		if (!support.getGoodsSupport().equals(this)) {
			support.setGoodsSupport(this);
		}
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(@NotNull int amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "id : " + idGoodsSupport + " Goods Name : " + goodsName + " amount : " + amount; 
	}
}
