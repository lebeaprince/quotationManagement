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
import registerationlogin.dto.CustomerDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="customer")
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty(message = "Name should not be empty")
    private String Name;
    
    @NotEmpty(message = "Address should not be empty")
    private String Address;
    
    @NotEmpty(message = "Contact should not be empty")
    private String Contact;
    
    @NotEmpty(message = "Email should not be empty")
    private String Email;
	public Customer(CustomerDTO dto) {
		// TODO Auto-generated constructor stub
		this.Name = dto.getName();
		this.Address = dto.getAddress();
		this.Contact = dto.getContact();
		this.Email = dto.getEmail();
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
	 * @return the address
	 */
	public String getAddress() {
		return Address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		Address = address;
	}
	/**
	 * @return the contact
	 */
	public String getContact() {
		return Contact;
	}
	/**
	 * @param contact the contact to set
	 */
	public void setContact(String contact) {
		Contact = contact;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return Email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		Email = email;
	}
	public Long getId() {
		// TODO Auto-generated method stub
		return this.id;
	}
}
