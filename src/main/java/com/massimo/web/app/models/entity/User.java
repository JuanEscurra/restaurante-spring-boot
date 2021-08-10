package com.massimo.web.app.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

import javax.persistence.JoinColumn;

@Entity
@Data
@Table(name = "users")
public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 45)
	@Size(min = 3)
	private String name;
	
	@Column(name = "last_name")
	@Size(min = 3)
	private String lastName;
	
	@Email
	@NotEmpty
	private String email;
	
	@Size(min = 7)
	private String password;
	
	@Pattern(regexp = "[0-9]{8}")
	private String dni;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "role_id", referencedColumnName = "id")
	private Role role;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
