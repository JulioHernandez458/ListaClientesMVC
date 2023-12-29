package com.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="client")
public class Client implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	
	@NotEmpty
	@Column(name = "name")
	private String name;
	
	@NotEmpty
	@Column(name = "last_name")
	private String lastName;
	
	@NotEmpty
	@Email
	@Column(name = "email")
	private String email;
	
	@NotNull
	@Column(name = "create_at")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createAt;
	
	@Column(name = "photo")
	private String photo;
	
	

	public Client() {
		
	}

	public Client(String name, String lastName, String email, Date createAt, String photo) {
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.createAt = createAt;
		this.photo = photo;
	}

	public Client(String id, String name, String lastName, String email, Date createAt, String photo) {
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.createAt = createAt;
		this.photo = photo;
	}
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@Override
	public String toString() {
		return "Client [id= " + id + ", name= " + name + ", lastName= " + lastName + ", email= "
				+ email + ", createAt= "+ createAt + ", photo= " + photo + "]";
	}
	
	
	

}
