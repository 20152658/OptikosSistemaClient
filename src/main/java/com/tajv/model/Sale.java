package com.tajv.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/*
create table Pardavimas
(
   Pardavimo_ID         int not null,
   Suma                 float(8,2) not null,
   Pardavimo_data       timestamp not null,
   Prekes				varchar(100),
   Uzsakymai			varchar(100),
   primary key (Pardavimo_ID)
);
*/
@Entity
@Table(name = "Pardavimas")
public class Sale {

	@Id
	@GeneratedValue
	@Column(name = "Pardavimo_ID")
	private int id;

	@Column(name = "Suma")
	private double sum;

	@Column(name = "Prekes")
	private String items;

	@Column(name = "Uzsakymai")
	private String orders;

	@Column(name = "Pardavimo_data")
	private String date;

	@Column(name = "pardavejas")
	private String seller;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}

	public String getItems() {
		return items;
	}

	public void setItems(String items) {
		this.items = items;
	}

	public String getOrders() {
		return orders;
	}

	public void setOrders(String orders) {
		this.orders = orders;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getSeller() {
		return seller;
	}

	public void setSeller(String seller) {
		this.seller = seller;
	}

}
