package registerationlogin.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import registerationlogin.dto.ProductDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="product")

public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Name should not be empty")
    private String Name;
    @NotEmpty(message = "Description should not be empty")
    private String Description;
    @NotEmpty(message = "Price should not be empty")
    
    public Product(ProductDTO dto) {
    	this.Name = dto.getName();
    	this.Description = dto.getDescription();
    }
    private double Price;
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
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
}
