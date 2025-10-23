package registerationlogin.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import registerationlogin.entity.Customer;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomerDTO {
    private Long id;
    
    @NotEmpty(message = "Name should not be empty")
    private String Name;
    
    @NotEmpty(message = "Address should not be empty")
    private String Address;
    
    @NotEmpty(message = "Contact should not be empty")
    private String Contact;
    
    @NotEmpty(message = "Email should not be empty")
    private String Email;
    
	public CustomerDTO(Customer qtt) {
		// TODO Auto-generated constructor stub
		this.id = qtt.getId();
		this.Name = qtt.getName();
		this.Address = qtt.getAddress();
		this.Contact = qtt.getContact();
		this.Email = qtt.getEmail();
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
}
