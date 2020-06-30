package edu.pja.mas.eventmasfinal.entity;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "idEvent")
public class Event implements IConference, IReunion, IParty, IPremiere {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idEvent;
	@NotBlank(message = "Event name is required")
	private String nameEvent;
	@NotBlank(message = "Address is required")
	private String address;
	@NotNull
	private int budget;
	private int ticketPrice;

	@Enumerated(EnumType.STRING)
	@ElementCollection
	private Set<EventType> types = new HashSet<>();

	private String typeReunion;
	private int amountWorkshop;
	private String theme;
	private String nameDJ;
	private double showTime;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "event", cascade = { CascadeType.MERGE, CascadeType.REFRESH })
	private Set<FinancialSupport> fSupport = new HashSet<>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "event", cascade = { CascadeType.MERGE, CascadeType.REFRESH })
	private Set<GoodsSupport> gSupport = new HashSet<>();

	public enum EventType {
		Conference, Premiere, Party, Reunion
	}

	// Bag
	private Boolean isOrganized;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "event", cascade = { CascadeType.DETACH, CascadeType.MERGE,
			CascadeType.REFRESH })
	private Set<OrganizesWhen> organizesWhen = new HashSet<>();

	// Composition ---Indoor/Outdoor group--------------- orphanRemoval = true ----------------
		@OneToMany(fetch = FetchType.LAZY, mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
		@Basic(optional = true)
		private Set<IndoorActivity> indoorActivity = new HashSet<>();

		@OneToMany(fetch = FetchType.LAZY, mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
		@Basic(optional = true)
		private Set<OutdoorActivity> outdoorActivity = new HashSet<>();

	// Composition ----- Free/Paid Event ------------- orphanRemoval = true ----------------
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
	@Basic(optional = true)
	private Set<PriceType> freeEvent = new HashSet<>();



	public Event() {
	}

	public Event(String nameEvent, Set<EventType> types, String typeReunion) {

	}
	// Constructor for each event type

	public Event(@NotBlank(message = "Event name is required") String nameEvent,
			@NotBlank(message = "Address is required") String address, @NotNull int budget, int ticketPrice,
			Set<EventType> types, String nameDJ, String theme) {

		setNameEvent(nameEvent);
		setAddress(address);
		setBudget(budget);
		setTicketPrice(ticketPrice);
		setTypes(types);
		isOrganized = false;
		if (types.contains(EventType.Party)) {
			setNameDJ(nameDJ);
			setTheme(theme);
		}
	}

	public Event(@NotBlank(message = "Event name is required") String nameEvent,
			@NotBlank(message = "Address is required") String address, @NotNull int budget, int ticketPrice,
			Set<EventType> types, int amountWorkshop) {

		setNameEvent(nameEvent);
		setAddress(address);
		setBudget(budget);
		setTicketPrice(ticketPrice);
		setTypes(types);
		isOrganized = false;
		if (types.contains(EventType.Conference)) {
			setAmountWorkshop(amountWorkshop);

		}
	}

	public Event(@NotBlank(message = "Event name is required") String nameEvent,
			@NotBlank(message = "Address is required") String address, @NotNull int budget, int ticketPrice,
			Set<EventType> types, double showTime) {

		setNameEvent(nameEvent);
		setAddress(address);
		setBudget(budget);
		setTicketPrice(ticketPrice);
		setTypes(types);
		isOrganized = false;
		if (types.contains(EventType.Premiere)) {
			setShowTime(showTime);

		}
	}

	public Event(@NotBlank(message = "Event name is required") String nameEvent,
			@NotBlank(message = "Address is required") String address, @NotNull int budget, int ticketPrice,
			Set<EventType> types, String typeReunion) {

		setNameEvent(nameEvent);
		setAddress(address);
		setBudget(budget);
		setTicketPrice(ticketPrice);
		setTypes(types);
		isOrganized = false;
		if (types.contains(EventType.Reunion)) {
			setTypeReunion(typeReunion);
		}
	}
	// --- Composition for indoor activity --------------------------------
	 public Set<IndoorActivity> getIndoorActivity() {
	        return Collections.unmodifiableSet(indoorActivity);
	    }

	    public void addIndoorActivity(IndoorActivity indoorActivity){
	        if(indoorActivity == null){
	            throw new IllegalArgumentException("Indoor Activity cant be null");
	        }
	        if(!this.indoorActivity.contains(indoorActivity)){
	            this.indoorActivity.add(indoorActivity);
	        }
	    }

	    public void removeIndoorActivity(IndoorActivity indoorActivity){
	        if(indoorActivity == null){
	            throw new IllegalArgumentException("Indoor Activity cant be null");
	        }
	        if(this.indoorActivity.contains(indoorActivity)){
	            this.indoorActivity.remove(indoorActivity);
	        }
	    }
	    
	 // --- Composition for outdoor activity --------------------------------
		 public Set<OutdoorActivity> getOutdoorActivity() {
		        return Collections.unmodifiableSet(outdoorActivity);
		    }

		    public void addOutdoorActivity(OutdoorActivity outdoorActivity){
		        if(outdoorActivity == null){
		            throw new IllegalArgumentException("Outdoor Activity cant be null");
		        }
		        if(!this.outdoorActivity.contains(outdoorActivity)){
		            this.outdoorActivity.add(outdoorActivity);
		        }
		    }

		    public void removeOutdoorActivity(OutdoorActivity outdoorActivity){
		        if(outdoorActivity == null){
		            throw new IllegalArgumentException("Outdoor Activity cant be null");
		        }
		        if(this.outdoorActivity.contains(outdoorActivity)){
		            this.outdoorActivity.remove(outdoorActivity);
		        }
		    }


	// overlapping can be only for reunion and party
	public Event(@NotBlank(message = "Event name is required") String nameEvent,
			@NotBlank(message = "Address is required") String address, @NotNull int budget, int ticketPrice,
			Set<EventType> types, String typeReunion, String nameDJ, String theme) {

		setNameEvent(nameEvent);
		setAddress(address);
		setBudget(budget);
		setTicketPrice(ticketPrice);
		setTypes(types);
		isOrganized = false;
		if (types.contains(EventType.Reunion)) {
			setTypeReunion(typeReunion);
		}

		if (types.contains(EventType.Party)) {
			setNameDJ(nameDJ);
			setTheme(theme);
		}

	}

	// bag
	public Boolean getIsOrganized() {
		return isOrganized;
	}

	public void setIsOrganized(Boolean isOrganized) {
		this.isOrganized = isOrganized;
	}

	// reverse
	public void addOrganizesWhen(OrganizesWhen organizesWhen) {
		if (organizesWhen == null) {
			throw new IllegalArgumentException("Organizes when cant be null");
		}
		// if(organizesWhen.getEvent().equals(this)){
		if (!this.organizesWhen.contains(organizesWhen)) {
			setIsOrganized(true);
			this.organizesWhen.add(organizesWhen);

		}
		// }
	}

	public void removeOrganizesWhen(OrganizesWhen organizesWhen) {
		if (organizesWhen.getEvent().equals(this)) {
			if (this.organizesWhen.contains(organizesWhen)) {
				this.organizesWhen.remove(organizesWhen);
				setIsOrganized(false);
			}
		}
	}


	// Financial support - adding
	public Set<FinancialSupport> getFinancialSupport() {
		return Collections.unmodifiableSet(fSupport);
	}

	public void addFinancialSupport(FinancialSupport financialSupport) {
		if (financialSupport == null) {
			throw new IllegalArgumentException("Financial support cant be null");
		}
		if (!fSupport.contains(financialSupport)) {
			fSupport.add(financialSupport);
			financialSupport.setEvent(this);
		}
	}

	// Goods support - adding
	public Set<GoodsSupport> getGoodsSupport() {
		return Collections.unmodifiableSet(gSupport);
	}

	public void addGoodsSupport(GoodsSupport goodsSupport) {
		if (goodsSupport == null) {
			throw new IllegalArgumentException("Goods support cant be null");
		}
		if (!gSupport.contains(goodsSupport)) {
			gSupport.add(goodsSupport);
			goodsSupport.setEvent(this);
		}
	}

	@Override
	public void setAmountWorkshop(int amountWorkshop) {
		if (!types.contains(EventType.Conference)) {
			throw new IllegalArgumentException("Only Conference can have workshop");
		}
		this.amountWorkshop = amountWorkshop;

	}

	@Override
	public int getAmountWorkshop() {
		if (!types.contains(EventType.Conference)) {
			throw new IllegalArgumentException("Only Conference can have workshop");
		}
		return this.amountWorkshop;
	}

	@Override
	public String getTheme() {
		if (!types.contains(EventType.Party)) {
			throw new IllegalArgumentException("Only Conference can have speaker");
		}
		return theme;
	}

	@Override
	public void setTheme(String theme) {
		if (!types.contains(EventType.Party)) {
			throw new IllegalArgumentException("Only Conference can have speaker");
		}
		this.theme = theme;
	}

	@Override
	public void setShowTime(double showTime) {
		if (!types.contains(EventType.Premiere)) {
			throw new IllegalArgumentException("Only Premiere can have show time");
		}
		this.showTime = showTime;

	}

	@Override
	public double getShowTime() {
		if (!types.contains(EventType.Premiere)) {
			throw new IllegalArgumentException("Only Premiere can have show time");
		}
		return showTime;
	}

	@Override
	public void setNameDJ(String nameDJ) {
		if (!types.contains(EventType.Party)) {
			throw new IllegalArgumentException("Only Party can have DJ");
		}
		this.nameDJ = nameDJ;

	}

	@Override
	public String getNameDJ() {
		if (!types.contains(EventType.Party)) {
			throw new IllegalArgumentException("Only Party can have DJ");
		}
		return nameDJ;
	}

	@Override
	public void setTypeReunion(String typeReunion) {
		if (!types.contains(EventType.Reunion)) {
			throw new IllegalArgumentException("Only Reunion can have type");
		}
		this.typeReunion = typeReunion;
	}

	@Override
	public String getTypeReunion() {
		if (!types.contains(EventType.Reunion)) {
			throw new IllegalArgumentException("Only Reunion can have type");
		}
		return typeReunion;
	}

	public int getIdEvent() {
		return idEvent;
	}

	public void setIdEvent(int idEvent) {
		this.idEvent = idEvent;
	}

	public String getNameEvent() {
		return nameEvent;
	}

	public void setNameEvent(String nameEvent) {
		this.nameEvent = nameEvent;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getBudget() {
		return budget;
	}

	public void setBudget(int budget) {
		this.budget = budget;
	}

	public int getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(int ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public Set<EventType> getTypes() {
		return types;
	}

	public void setTypes(Set<EventType> types) {
		if (types == null || types.isEmpty()) {
			throw new IllegalArgumentException("Types cant be empty or null");
		}
		this.types = types;
	}

	@Override
	public String toString() {
		return "Event [idEvent=" + idEvent + ", nameEvent=" + nameEvent + ", address=" + address + ", budget=" + budget
				+ ", ticketPrice=" + ticketPrice + ", typeReunion=" + typeReunion
				+ ", amountWorkshop=" + amountWorkshop + ", theme=" + theme + ", nameDJ=" + nameDJ + ", showTime="
				+ showTime + ", isOrganized=" + isOrganized + "]";
	}

}
