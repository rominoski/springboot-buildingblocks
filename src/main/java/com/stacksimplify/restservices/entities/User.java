package com.stacksimplify.restservices.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

//Entity 
@Entity
@Table(name="userss")
public class User {
	@Id
	@GeneratedValue
	private Long id;
	@NotEmpty (message = "Username is Mandatory field. Please provide username")
	@Column (name="USER_NAME", length=50, nullable=false, unique=true)
	private String username;
	@Size(min=2, message= "FirstName should have atleast 2 characters")
	@Column (name="FIRST_NAME", length=50, nullable=false)
	private String fisrtname;
	@Column (name="LAST_NAME", length=50, nullable=false)
	private String lastname;
	@Column (name="EMAIL_ADDRESS", length=50, nullable=false)
	private String email;
	@Column (name="ROLE", length=50, nullable=false)
	private String role;
	@Column (name="SSN", length=50, nullable=false, unique=true)
	private String ssn;
	
	@OneToMany(mappedBy="user")
	private List<Order> orders;
	
	//No arguments constructor
	public User() {
		
	}
	//Fields constructor
	public User(Long id, String username, String fisrtname, String lastname, String email, String role, String ssn) {
		this.id = id;
		this.username = username;
		this.fisrtname = fisrtname;
		this.lastname = lastname;
		this.email = email;
		this.role = role;
		this.ssn = ssn;
	}
	
	//Getter and Setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFisrtname() {
		return fisrtname;
	}
	public void setFisrtname(String fisrtname) {
		this.fisrtname = fisrtname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}	
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> order) {
		this.orders = order;
	}
	//To String
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", fisrtname=" + fisrtname + ", lastname=" + lastname
				+ ", email=" + email + ", role=" + role + ", ssn=" + ssn + "]";
	}
}
