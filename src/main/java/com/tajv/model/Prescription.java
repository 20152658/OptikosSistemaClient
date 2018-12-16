package com.tajv.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/*
create table Receptas
(
   Recepto_ID           int not null,
   Uzsakovo_ID          int,
   Desines_akies_sfera  decimal not null,
   Kaires_akies_sfera   decimal not null,
   Desines_akies_cilindras decimal,
   Kaires_akies_cilindras decimal,
   Desines_akies_asis   int,
   Kaires_akies_asis    int,
   Desines_akies_prizme decimal,
   Kaires_akies_prizme  decimal,
   Atstumas_tarp_vyzdziu_centru int not null,
   Paskirtis            varchar(25) not null,
   primary key (Recepto_ID)
);
*/
@Entity
@Table(name = "Receptas")
public class Prescription {

	@Id
	@GeneratedValue
	@Column(name = "Recepto_ID")
	private int id;

	@Column(name = "Uzsakovo_ID")
	private int clientId;

	@Column(name = "Desines_akies_sfera")
	private double rightEyeSphere;

	@Column(name = "Desines_akies_cilindras")
	private double rightEyeCylinder;

	@Column(name = "Desines_akies_asis")
	private int rightEyeAxis;

	@Column(name = "Desines_akies_prizme")
	private double rightEyePrism;

	@Column(name = "Kaires_akies_sfera")
	private double leftEyeSphere;

	@Column(name = "Kaires_akies_cilindras")
	private double leftEyeCylinder;

	@Column(name = "Kaires_akies_asis")
	private int leftEyeAxis;

	@Column(name = "Kaires_akies_prizme")
	private double leftEyePrism;

	@Column(name = "Atstumas_tarp_vyzdziu")
	private int distanceBetweenPupils;

	@Column(name = "Paskirtis")
	private String purpose;

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

	public double getRightEyeSphere() {
		return rightEyeSphere;
	}

	public void setRightEyeSphere(double rightEyeSphere) {
		this.rightEyeSphere = rightEyeSphere;
	}

	public double getRightEyeCylinder() {
		return rightEyeCylinder;
	}

	public void setRightEyeCylinder(double rightEyeCylinder) {
		this.rightEyeCylinder = rightEyeCylinder;
	}

	public int getRightEyeAxis() {
		return rightEyeAxis;
	}

	public void setRightEyeAxis(int rightEyeAxis) {
		this.rightEyeAxis = rightEyeAxis;
	}

	public double getRightEyePrism() {
		return rightEyePrism;
	}

	public void setRightEyePrism(double rightEyePrism) {
		this.rightEyePrism = rightEyePrism;
	}

	public double getLeftEyeSphere() {
		return leftEyeSphere;
	}

	public void setLeftEyeSphere(double leftEyeSphere) {
		this.leftEyeSphere = leftEyeSphere;
	}

	public double getLeftEyeCylinder() {
		return leftEyeCylinder;
	}

	public void setLeftEyeCylinder(double leftEyeCylinder) {
		this.leftEyeCylinder = leftEyeCylinder;
	}

	public int getLeftEyeAxis() {
		return leftEyeAxis;
	}

	public void setLeftEyeAxis(int leftEyeAxis) {
		this.leftEyeAxis = leftEyeAxis;
	}

	public double getLeftEyePrism() {
		return leftEyePrism;
	}

	public void setLeftEyePrism(double leftEyePrism) {
		this.leftEyePrism = leftEyePrism;
	}

	public int getDistanceBetweenPupils() {
		return distanceBetweenPupils;
	}

	public void setDistanceBetweenPupils(int distanceBetweenPupils) {
		this.distanceBetweenPupils = distanceBetweenPupils;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

}
