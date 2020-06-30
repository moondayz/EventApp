package edu.pja.mas.eventmasfinal.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class PriceType implements IFreeEvent , IPaidEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPriceType;
    @NotBlank(message = "Type name is required")
    private String typeName;

    //------- dynamic inheritance ------
    private String promoCode; // freeEvent
    private int ticketPrice;  // paidEvent

    private boolean isFree;


    @OneToOne
    @JoinColumn(name = "idEvent", nullable = false)
    private Event event;

    public PriceType(){

    };

    public PriceType( @NotBlank(message = "Type name is required") String typeName,
                     String promoCode, int ticketPrice, boolean isFree, Status status) {
        this.typeName = typeName;

        if(status.equals(PriceType.Status.FREE)) {
            setPromoCode(promoCode);
        }
        else {
            setTicketPrice(ticketPrice);
        }
    }

    private PriceType.Status status;
    public enum Status {
        FREE,
        PAID
    }

    public Event getEvent() {
        return event;
    }

    // ---- overriding methods (getter- setter) from interfaces for free-paid event

    @Override
    public void setPromoCode(String promoCode) {
        if(!status.equals(PriceType.Status.FREE)){
            throw new IllegalArgumentException("Sorry this this is for free.");
        }
        this.promoCode = promoCode;
    }

    @Override
    public String getPromoCode() {
        if(!status.equals(PriceType.Status.FREE)){
            throw new IllegalArgumentException("Sorry this this is for free.");
        }
        return this.promoCode;
    }

    @Override
    public void setTicketPrice(int ticketPRice) {
        if(!status.equals(PriceType.Status.PAID)){
            throw new IllegalArgumentException("Sorry this this is for paid.");
        }
        this.ticketPrice = ticketPRice;
    }

    @Override
    public int getTicketPrice() {
        if(!status.equals(PriceType.Status.PAID)){
            throw new IllegalArgumentException("Sorry this this is for paid.");
        }
        return this.ticketPrice;
    }

    // -----------------
    public boolean isFree() {
        return isFree;
    }

    public void setIsFree() {
        if(status == PriceType.Status.FREE){
            this.isFree = true;
        } else {
            this.isFree = false;
        }
    }

    //------------------------------dynamic -inheritance -- switch --------------------------------------------------

    public void beFree(String promoCode) {
        status = PriceType.Status.FREE;
        setPromoCode(promoCode);
        this.promoCode = null;
    }

    public void bePaid(int  ticketPrice) {
        status = PriceType.Status.PAID;
        setTicketPrice(ticketPrice);
        this.promoCode = null;
    }
    //------------------------------------------------------------

    @Override
    public String toString() {
        return "PriceType{" +
                "idPriceType=" + idPriceType +
                ", typeName='" + typeName + '\'' +
                ", promoCode='" + promoCode + '\'' +
                ", ticketPrice=" + ticketPrice +
                ", isFree=" + isFree +
                ", status=" + status +
                '}';
    }
}
