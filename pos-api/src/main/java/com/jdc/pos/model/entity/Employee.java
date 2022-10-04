package com.jdc.pos.model.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Employee implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(unique = true, nullable = false)
	private String email;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String password;
	@Column(nullable = false)
	private LocalDate assign;
	private LocalDate retire;
	private boolean pending;

	@OneToMany(mappedBy = "employee", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
	private List<EmployeeRole> roles;

	public List<EmployeeRole> getRoles() {
		return roles;
	}

	public void setRoles(List<EmployeeRole> roles) {
		this.roles = roles;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDate getAssign() {
		return assign;
	}

	public void setAssign(LocalDate assign) {
		this.assign = assign;
	}

	public LocalDate getRetire() {
		return retire;
	}

	public void setRetire(LocalDate retire) {
		this.retire = retire;
	}

	public boolean isPending() {
		return pending;
	}

	public void setPending(boolean pending) {
		this.pending = pending;
	}

}