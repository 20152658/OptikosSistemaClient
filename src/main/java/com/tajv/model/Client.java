package com.tajv.model;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Client {

	@JsonProperty("id")
	private int id;

	@JsonProperty("name")
	@Size(min = 2, max = 20, message = "Name must be between 2 and 20 characters")
	private String name;

	@JsonProperty("surname")
	@Size(min = 2, max = 30, message = "Surname must be between 2 and 30 characters")
	private String surname;

	@JsonProperty("email")
	@NotBlank(message = "Please enter email")
	@Size(min = 2, max = 50, message = "Email must be between 2 and 50 characters")
	@Email(message = "Please enter valid email.")
	private String email;

	@JsonProperty("password")
	@NotBlank(message = "Please enter password")
	private String password;

	private String newPassword;

	private String repeatPassword;

	@JsonProperty("dateOfBirth")
	@NotBlank(message = "Please enter password")
	private String dateOfBirth;

	@JsonProperty("phone")
	@Size(max = 11, message = "Phone number must be up to 11 characters")
	private String phone;

	@JsonProperty("height")
	private double height;

	@JsonProperty("weight")
	private double weight;

	@JsonProperty("gender")
	@NotBlank(message = "Please enter password")
	private String gender;

	private List<File> clientFiles;

	public List<File> getClientFiles() {
		return clientFiles;
	}

	public void setClientFiles(List<File> clientFiles) {
		this.clientFiles = clientFiles;
	}

	public int getId() {
		return id;
	}

	public Client() {
	}

	public Client(String email, String password) {
		this.email = email;
		this.password = password;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getRepeatPassword() {
		return repeatPassword;
	}

	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

}
