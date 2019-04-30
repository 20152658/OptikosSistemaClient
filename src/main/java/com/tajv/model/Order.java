package com.tajv.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/*
create table Uzsakymas
(
   Uzsakymo_ID          int not null,
   Uzsakovo_ID          int,
   Uzsakymo_data        timestamp not null,
   Pagaminimo_data      date,
   Uzsakymo_kaina       float(8,2) not null,
   Avansas              float(8,2) not null,
   Lesio_rusis          varchar(50),
   Uzsakymas_gaminamas  bool not null,
   Uzsakymas_atiduotas  bool not null,
   primary key (Uzsakymo_ID)
);
*/
@Entity
@Table(name = "Uzsakymas")
public class Order {

	@Id
	@GeneratedValue
	@Column(name = "Uzsakymo_ID")
	private int id;

	@Column(name = "Uzsakovo_ID")
	private int clientId;

	@Column(name = "Pagaminimo_data")
	private String estimatedDate;

	@Column(name = "Uzsakymo_kaina")
	private double orderPrice;

	@Column(name = "Avansas")
	private double deposit;

	@Column(name = "Lesio_rusis")
	private String lensType;

	@Column(name = "Uzsakymas_gaminamas")
	private boolean inProgress;

	@Column(name = "Uzsakymas_atiduotas")
	private boolean completed;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public String getEstimatedDate() {
		return estimatedDate;
	}

	public void setEstimatedDate(String estimatedDate) {
		this.estimatedDate = estimatedDate;
	}

	public double getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(double orderPrice) {
		this.orderPrice = orderPrice;
	}

	public double getDeposit() {
		return deposit;
	}

	public void setDeposit(double deposit) {
		this.deposit = deposit;
	}

	public String getLensType() {
		return lensType;
	}

	public void setLensType(String lensType) {
		this.lensType = lensType;
	}

	public boolean isInProgress() {
		return inProgress;
	}

	public void setInProgress(boolean inProgress) {
		this.inProgress = inProgress;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

}
