package edu.pja.mas.eventmasfinal.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;

@Entity
public class Support {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idSupport;

	// --- XOR ------------------
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idFinancialSupport", nullable = true)
	private FinancialSupport financialSupport;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idGoodsSupport", nullable = true)
	private GoodsSupport goodsSupport;
	
//-------------------qualified association ------------------------------
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id")
    private Sponsor sponsor;
	
	public Support() {	
	}
	
	// constructor also checking for typeSupport - only one type is available other will be null
	public Support(FinancialSupport financialSupport, GoodsSupport goodsSupport) {
		

		 if(financialSupport == null && goodsSupport == null){
	            throw new IllegalArgumentException("Support cant be null.");
	        }
		// Check the support type and add it to the defined type
		 if(goodsSupport != null && financialSupport != null){
	            throw new IllegalArgumentException("Support can have only one type, financial or goods");
	        }
	        if(goodsSupport != null){
	            setGoodsSupport(goodsSupport);
	        }
	        if(financialSupport != null){
	        	setFinancialSupport(financialSupport);
	        }

	}
	
// --------------------- XOR ----------------------------

	public GoodsSupport getGoodsSupport() {
		return goodsSupport;
	}

	public void setGoodsSupport(GoodsSupport goodsSupport) {
		 if(goodsSupport == null){
	            throw new IllegalArgumentException("Goods suppport cant be null");
	        }
	        this.goodsSupport = goodsSupport;
	        this.financialSupport = null;
	        goodsSupport.setSupport(this);
	}

	public FinancialSupport getFinancialSupport() {
		return financialSupport;
	}

	public void setFinancialSupport(FinancialSupport financialSupport) {
		if(financialSupport == null){
            throw new IllegalArgumentException("Financial suppport cant be null");
        }
        this.financialSupport = financialSupport;
        this.goodsSupport = null;
        financialSupport.setSupport(this);

	}
	
	//------------- qualified association ---------------------
	public Sponsor getSponsor() {
		return sponsor;
	}

	public void setSponsor(Sponsor sponsor) {
		 if(sponsor == null){
	            throw new IllegalArgumentException("Sponsor cant be null");
	        }
		this.sponsor = sponsor;

	}
	//-------------------------------------------------------------
	

	public void setIdSupport(int idSupport) {
		this.idSupport = idSupport;
	}


	public int getIdSupport() {
		return idSupport;
	}

	@Override
	public String toString() {
		
		return "id Support" + idSupport;
	}

	
}
