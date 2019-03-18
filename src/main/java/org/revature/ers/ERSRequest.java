package org.revature.ers;

import java.util.ArrayList;

public class ERSRequest {

	private Integer id;
	private Integer employeeId;
	private String status;
	@Override
	public String toString() {
		return "Reimbursement [id=" + id + ", employeeId=" + employeeId + ", status=" + status + ", image=" + image
				+ ", email=" + email + ", password=" + password + ", ManagerId=" + ManagerId + ", amount=" + amount
				+ ", employeeName=" + employeeName + ", managerEmail=" + managerEmail + ", managerPassword="
				+ managerPassword + "]";
	}
	private String image;
	private String email;
	private String password;
	private Integer ManagerId;
	private Integer amount;
	private String employeeName;
	private String managerEmail;
	private String managerPassword;
	
	public ERSRequest() {
		
	}
	
	
	public ERSRequest(Integer id, String status, Integer ManagerId, String managerEmail, String managerPassword ) {
		
		this.id = id;
		this.status = status;
		this.ManagerId = ManagerId;
		this.managerEmail = managerEmail;
		this.managerPassword = managerPassword;
	}
	
	public ERSRequest(Integer id, String status, Integer amount, Integer employeeId, String employeeName, String email, String password, Integer ManagerId, String managerEmail, String managerPassword) {
		this.id=id;
		this.status=status;
	
		this.amount=amount;
		this.employeeId=employeeId;
		this.employeeName=employeeName;
		this.email=email;
		this.password=password;
		this.ManagerId=ManagerId;
		this.managerEmail=managerEmail;
		this.managerPassword=managerPassword;
		
	}
	public void setManagerEmail(String managerEmail) {
		this.managerEmail=managerEmail;
	}
	public String getManagerEmail() {
		return managerEmail;
	}
	public void setManagerPassword(String managerPassword) {
		this.managerPassword=managerPassword;
	}
	public String getManagerPassword() {
		return managerPassword;
	}

	public void setId(Integer id) {
			this.id = id;
	}
	public Integer getId() {
		return id;
	}

	public void setStatus(String status) {
		this.status=status;
	}

	public void setImage(String image) {
		this.image = image;
		
	}

	public void setEmail(String email) {
		this.email=email;
		
	}

	public void setPassword(String password) {
		this.password=password;
		
	}

	public void setManagerId(int ManagerId) {
		this.ManagerId=ManagerId;
		
	}
	
	
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public Integer getManagerId() {
		return ManagerId;
	}
	public void setManagerId(Integer ManagerId) {
		this.ManagerId = ManagerId;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public String getStatus() {
		return status;
	}
	public String getImage() {
		return image;
	}
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}



}
