package registerationlogin.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_role", // Match existing DB join table name
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
    )
    private List<Role> roles = new ArrayList<>();   //Cascading is the option whenver we are changing any record of user, then respective record for role
//will also be changed.

	public String getEmail() {
		// TODO Auto-generated method stub
		return this.email;
	}

	public void setName(String name2) {
		// TODO Auto-generated method stub
		this.name = name2;
	}

	public void setEmail(String email2) {
		// TODO Auto-generated method stub
		this.email = email2;
	}

	public void setPassword(String encode) {
		// TODO Auto-generated method stub
		this.password = encode;
	}

	public void setRoles(List<Role> asList) {
		// TODO Auto-generated method stub
		this.roles = asList;
	}

	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}

	public List<Role> getRoles() {
		// TODO Auto-generated method stub
		return this.roles;
	}

	public Long getId() {
		// TODO Auto-generated method stub
		return this.id;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}
}
