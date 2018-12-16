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

}
