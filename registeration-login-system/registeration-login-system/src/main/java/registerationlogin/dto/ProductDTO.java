package registerationlogin.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import registerationlogin.entity.Product;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductDTO {
    private Long id;
    
    @NotEmpty(message = "Name should not be empty")
    private String Name;
    
    @NotEmpty(message = "Description should not be empty")
    private String Description;
    
    @NotEmpty(message = "Price should not be empty")
    private double Price;
    
    public ProductDTO(Product pr) {
    	this.Description = pr.getDescription();
    	this.id = pr.getId();
    	this.Price = pr.getPrice();
    	this.Name = pr.getName();
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
	 * @return the name
	 */
	public String getName() {
		return Name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		Name = name;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return Description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		Description = description;
	}
	/**
	 * @return the price
	 */
	public double getPrice() {
		return Price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		Price = price;
	} 
}
