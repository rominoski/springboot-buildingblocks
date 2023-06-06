package com.stacksimplify.restservices.entities;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="employee")
public class Employee extends RepresentationModel{
	
	@Id
	@GeneratedValue
	@JsonView(Views2.Normal.class)
	private Long empid;
	@NotEmpty (message = "Employee Name is Mandatory field. Please provide name")	
	@Column (name="EMPLOYEE_NAME", length=50, nullable=false, unique=true)
	@JsonView(Views2.Normal.class)
	private String empname;
	@Size(min=2, message= "Department should have atleast 2 characters")	
	@Column (name="EMPLOYEE_DEPARTMENT", length=50, nullable=false)
	@JsonView(Views2.Normal.class)
	private String empdepartment;
	@Column (name="LOGIN_TIME",length=50, nullable=false)
	@JsonView(Views2.Manager.class)
	private String logintime;
	@Column (name="LOGOUT_TIME",length=50, nullable=false)
	@JsonView(Views2.Manager.class)
	private String logouttime;
	@Column (name="SALARY", nullable=false)
	@JsonView(Views2.Hr.class)
	private Long salary;
	@Column (name="LAST_PROMOTION_DATE",length=50, nullable=false)
	@JsonView(Views2.Hr.class)
	private String lastpromotiondate;
	
	
	public Employee() {
	
	}
	public Employee(Long empid,
			@NotEmpty(message = "Employee Name is Mandatory field. Please provide name") String empname,
			@Size(min = 2, message = "Department should have atleast 2 characters") String empdepartment,
			String logintime, String logouttime, Long salary, String lastpromotiondate) {
		super();
		this.empid = empid;
		this.empname = empname;
		this.empdepartment = empdepartment;
		this.logintime = logintime;
		this.logouttime = logouttime;
		this.salary = salary;
		this.lastpromotiondate = lastpromotiondate;
	}
	public Long getEmpid() {
		return empid;
	}
	public void setEmpid(Long empid) {
		this.empid = empid;
	}
	public String getEmpname() {
		return empname;
	}
	public void setEmpname(String empname) {
		this.empname = empname;
	}
	public String getEmpdepartment() {
		return empdepartment;
	}
	public void setEmpdepartment(String empdepartment) {
		this.empdepartment = empdepartment;
	}
	public String getLogintime() {
		return logintime;
	}
	public void setLogintime(String logintime) {
		this.logintime = logintime;
	}
	public String getLogouttime() {
		return logouttime;
	}
	public void setLogouttime(String logouttime) {
		this.logouttime = logouttime;
	}
	public Long getSalary() {
		return salary;
	}
	public void setSalary(Long salary) {
		this.salary = salary;
	}
	public String getLastpromotiondate() {
		return lastpromotiondate;
	}
	public void setLastpromotiondate(String lastpromotiondate) {
		this.lastpromotiondate = lastpromotiondate;
	}
	@Override
	public String toString() {
		return "Employee [empid=" + empid + ", empname=" + empname + ", empdepartment=" + empdepartment + ", logintime="
				+ logintime + ", logouttime=" + logouttime + ", salary=" + salary + ", lastpromotiondate="
				+ lastpromotiondate + "]";
	}
	
	
}
