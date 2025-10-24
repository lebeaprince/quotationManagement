package registerationlogin.entity;
import jakarta.persistence.*;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import registerationlogin.dto.QuotationDTO;
import registerationlogin.entity.Enums.QuoatationState;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="quotation")

public class Quotation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Long CustomerId;

    private Date DateCreated;
    
    @Column(nullable = false)
    private Date ValidUntil;
    
    @Column(nullable = false)
    private double Total;
    
    private double Discount;
    
    private double VAT;
    
    private String Currency;
    
    private String CreatedBy;
    
    @Transient
    private List<Product> Products;
    
    @Transient
    private List<Comment> Comments;
    
    @Enumerated(EnumType.STRING)
    private QuoatationState State;
    
    public Quotation(QuotationDTO dto) {
    	this.CustomerId = dto.getCustomerId();
    	this.setComments(dto.getComments());
    	this.CreatedBy = dto.getCreatedBy();
    	this.Currency = dto.getCurrency();
    	this.DateCreated = dto.getDateCreated();
    	this.Discount = dto.getDiscount();
    	this.Products = dto.getProducts();
    	this.State = dto.getState();
    	this.Total = dto.getTotal();
    	this.ValidUntil = dto.getValidUntil();
    	this.VAT = dto.getVAT();
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getCustomerId() {
		return CustomerId;
	}

	public void setCustomerId(Long id) {
		this.CustomerId = id;
	}
	
	public Date getDateCreated() {
		return DateCreated;
	}

	public void setDateCreated(Date id) {
		this.DateCreated = id;
	}
	
	public Date getValidUntil() {
		return ValidUntil;
	}

	public void setValidUntil(Date id) {
		this.ValidUntil = id;
	}

	/**
	 * @return the total
	 */
	public double getTotal() {
		return Total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(double total) {
		Total = total;
	}

	/**
	 * @return the discount
	 */
	public double getDiscount() {
		return Discount;
	}

	/**
	 * @param discount the discount to set
	 */
	public void setDiscount(double discount) {
		Discount = discount;
	}

	/**
	 * @return the vAT
	 */
	public double getVAT() {
		return VAT;
	}

	/**
	 * @param vAT the vAT to set
	 */
	public void setVAT(double vAT) {
		VAT = vAT;
	}

	/**
	 * @return the currency
	 */
	public String getCurrency() {
		return Currency;
	}

	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(String currency) {
		Currency = currency;
	}

	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return CreatedBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		CreatedBy = createdBy;
	}

	/**
	 * @return the products
	 */
	public List<Product> getProducts() {
		return Products;
	}

	/**
	 * @param products the products to set
	 */
	public void setProducts(List<Product> products) {
		Products = products;
	}

	/**
	 * @return the state
	 */
	public QuoatationState getState() {
		return State;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(QuoatationState state) {
		State = state;
	}

	/**
	 * @return the comments
	 */
	public List<Comment> getComments() {
		return Comments;
	}

	/**
	 * @param comments the comments to set
	 */
	public void setComments(List<Comment> comments) {
		Comments = comments;
	}
}
