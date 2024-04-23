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
	
	@Column(name = "phone_number")
	private Integer phoneNumber;
	
	@Column(name = "country")
	private String country;
	
	@NotNull
	@Column(name = "date_born")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateBorn;
	
	@Column(name = "photo")
	private String photo;
	
	

	public Client() {
		
	}



	public Client(@NotEmpty String name, @NotEmpty String lastName, @NotEmpty @Email String email, Integer phoneNumber,
			String country, @NotNull Date dateBorn, String photo) {
		super();
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.country = country;
		this.dateBorn = dateBorn;
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



	public Integer getPhoneNumber() {
		return phoneNumber;
	}



	public void setPhoneNumber(Integer phoneNumber) {
		this.phoneNumber = phoneNumber;
	}



	public String getCountry() {
		return country;
	}



	public void setCountry(String country) {
		this.country = country;
	}



	public Date getDateBorn() {
		return dateBorn;
	}



	public void setDateBorn(Date dateBorn) {
		this.dateBorn = dateBorn;
	}



	public String getPhoto() {
		return photo;
	}



	public void setPhoto(String photo) {
		this.photo = photo;
	}
	


	@Override
	public String toString() {
		return "Client [id=" + id + ", name=" + name + ", lastName=" + lastName + ", email=" + email + ", phoneNumber="
				+ phoneNumber + ", country=" + country + ", dateBorn=" + dateBorn + ", photo=" + photo + "]";
	}

	
	
	
	

}
