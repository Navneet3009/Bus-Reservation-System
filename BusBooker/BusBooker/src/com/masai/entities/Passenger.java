package com.masai.entities;

import java.io.Serializable;

public class Passenger implements Serializable{
	private double WalletBalance;
	private String name;
	private String pass;
	private String address;
	private String email;
	
	public Passenger() {
		super();
	}
	
	public Passenger(double balance, String name, String pass, String address, String email) {
		super();
		this.WalletBalance = balance;
		this.name = name;
		this.pass = pass;
		this.address = address;
		this.email = email;
	}
	
	public Passenger(String name, String password, String address) {
		this.name = name;
		this.pass = password;
		this.address = address;
	}
	
	public double getWalletBalance() {
		return WalletBalance;
	}
	public void setWalletBalance(double d) {
		this.WalletBalance = d;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "Passenger [WalletBalance : " + getWalletBalance() + ", Name : " + getName() + ", Password : " + getPass()
				+ ", Address : " + getAddress() + ", Email : " + getEmail() + "]";
	}

	
}
