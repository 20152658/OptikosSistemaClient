package com.tajv.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/*
create table Uzsakovas
(
   Uzsakovo_ID          int not null,
   Vardas               varchar(20) not null,
   Pavarde              varchar(20),
   Telefono_numeris   varchar(15),
   El_pastas            varchar(50),
   primary key (Uzsakovo_ID)
);
*/
@Entity
@Table(name = "Uzsakovas")
public class Client {

	@Id
	@GeneratedValue
	@Column(name = "Uzsakovo_ID")
	private int id;

	@Column(name = "Vardas")
	private String name;

	@Column(name = "Pavarde")
	private String surname;

	@Column(name = "Telefono_numeris")
	private String phoneNumber;

	@Column(name = "El_pastas")
	private String email;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
