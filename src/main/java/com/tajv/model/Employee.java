package com.tajv.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/*
create table Darbuotojas
(
   Darbuotojo_ID        int not null,
   Darbuotojo_vardas	varchar(30) not null,
   Darbuotojo_tipas		varchar(30) not null,
   Prisijungimo_vardas	varchar(30) not null,
   Slaptazodis			varchar(30) not null,
   
   primary key (Darbuotojo_ID)
);
*/
@Entity
@Table(name = "Darbuotojas")
public class Employee {

	@Id
	@GeneratedValue
	@Column(name = "Darbuotojo_ID")
	private int id;

	@Column(name = "Darbuotojo_vardas")
	private String name;

	@Column(name = "Darbutojo_tipas")
	private String type;

	@Column(name = "Prisijungimo_vardas")
	private String nickname;

	@Column(name = "Slaptazodis")
	private String password;

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
