package com.tajv.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/*
create table Preke
(
   Prekes_ID            int not null auto_increment,
   Prekes_Tipas         varchar(20) not null,
   Prekes_Kaina         double not null,
   Prekes_Gamintojas    varchar(30) not null,
   Prekes_Pavadinimas   varchar(50) not null,
   Prekes_Kiekis        int not null default 0,
   Rezervuota           int not null default 0,
   primary key (Prekes_ID)
);
*/
@Entity
@Table(name = "Preke")
public class Item {

	@Id
	@GeneratedValue
	@Column(name = "Prekes_ID")
	private int id;

	@Column(name = "Prekes_Tipas")
	private String type;

	@Column(name = "Prekes_Kaina")
	private double price;

	@Column(name = "Prekes_Gamintojas")
	private String brand;

	@Column(name = "Prekes_Pavadinimas")
	private String title;

	@Column(name = "Prekes_Kiekis")
	private int amount;

	@Column(name = "Rezervuota")
	private int reserved;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getReserved() {
		return reserved;
	}

	public void setReserved(int reserved) {
		this.reserved = reserved;
	}

}
