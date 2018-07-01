package com.example;

public class Car {

    private String VIN;
    private String color;
    private Integer miles;
    private Name[] name;
    
	public Name[] getName() {
		return name;
	}
	public void setName(Name[] name) {
		this.name = name;
	}
	public String getVIN() {
		return VIN;
	}
	public void setVIN(String vIN) {
		VIN = vIN;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Integer getMiles() {
		return miles;
	}
	public void setMiles(Integer miles) {
		this.miles = miles;
	}

     
}