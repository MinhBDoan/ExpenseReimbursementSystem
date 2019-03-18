package org.revature.ers;

public class Employee {
	private Integer id;
	private String fullname;
	private String email;
	private String password;
	private String position;
	
	public Employee() {
		
	}
	public Employee(String fullname, String email, String password, String position) {
		this.fullname = fullname;
		this.email = email;
		this.password= password;
		this.position=position;
	}
	public Employee(Integer id, String fullname, String email, String password) {
		this.id = id;
		this.fullname = fullname;
		this.email = email;
		this.password= password;
		
	}
	public void setId(Integer id) {
		this.id=id;
	}
	public Integer getId() {
		return id;
	}
	public void setFullName(String fullname) {
		this.fullname = fullname;
	}
	public String getFullName() {
		return fullname;
	}
	public void setEmail(String email) {
		this.email=email;
	}
	public String getEmail() {
		return email;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return password;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getPosition() {
		return position;
	}
	@Override
    public String toString() {
        return "Employee{" + "id=" + id + ", fullname=" + fullname + ", email=" + email + ", password=" + password + ", position=" + position +'}';
    }
}
