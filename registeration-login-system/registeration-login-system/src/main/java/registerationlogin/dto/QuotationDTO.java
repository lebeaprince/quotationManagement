package registerationlogin.dto;

import java.sql.Date;
import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import registerationlogin.entity.Comment;
import registerationlogin.entity.Product;
import registerationlogin.entity.Quotation;
import registerationlogin.entity.Enums.QuoatationState;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class QuotationDTO {
    private Long id;
    
    @NotEmpty(message = "CustomerId should not be empty")
    private Long CustomerId;
    
    private Date DateCreated;
    
    private Date ValidUntil;
    @NotEmpty(message = "Total should not be empty")
    private double Total;
    private double Discount;
    private double VAT;
    private String Currency;
    private String CreatedBy;
    private List<Product> Products;
    private List<Comment> Comments;
    private QuoatationState State;
    
    public QuotationDTO(Quotation qt) {
    	this.id = qt.getId();
    	this.CustomerId = qt.getCustomerId();
    	this.DateCreated = qt.getDateCreated();
    	this.ValidUntil = qt.getValidUntil();
    	this.Total = qt.getTotal();
    	this.Discount = qt.getDiscount();
    	this.VAT = qt.getVAT();
    	this.Currency = qt.getCurrency();
    	this.CreatedBy = qt.getCreatedBy();
    	this.Products = qt.getProducts();
    	this.Comments = qt.getComments();
    	this.State = qt.getState();
    }
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the customerId
	 */
	public Long getCustomerId() {
		return CustomerId;
	}
	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(Long customerId) {
		CustomerId = customerId;
	}
	/**
	 * @return the dateCreated
	 */
	public Date getDateCreated() {
		return DateCreated;
	}
	/**
	 * @param dateCreated the dateCreated to set
	 */
	public void setDateCreated(Date dateCreated) {
		DateCreated = dateCreated;
	}
	/**
	 * @return the validUntil
	 */
	public Date getValidUntil() {
		return ValidUntil;
	}
	/**
	 * @param validUntil the validUntil to set
	 */
	public void setValidUntil(Date validUntil) {
		ValidUntil = validUntil;
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
}
