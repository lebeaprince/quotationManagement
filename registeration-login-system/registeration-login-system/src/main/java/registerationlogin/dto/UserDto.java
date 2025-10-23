package registerationlogin.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {

    private Long id;
    
    @NotEmpty(message = "Name should not be empty")
    private String name;
    @NotEmpty(message = "Email should not be empty")
    @Email
    private String email;                               // As form data is stored in dto objects , therefore dto object fields should be validated.
    
    @NotEmpty(message = "Password should not be empty")
    private String password;
    
	public String getEmail() {
		// TODO Auto-generated method stub
		return this.email;
	}
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}
	public void setId(Long id2) {
		// TODO Auto-generated method stub
		this.id = id2;
	}
	public void setName(String name2) {
		// TODO Auto-generated method stub
		this.name = name2;
	}
	public void setEmail(String email2) {
		// TODO Auto-generated method stub
		this.email = email2;
	}

	public void setPassword(String email2) {
		// TODO Auto-generated method stub
		this.password = email2;
	}

}
