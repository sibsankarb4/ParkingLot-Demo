package org.parking.lot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "car")
public class Car {
	
	@Id
	@Column(name = "car_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer carId;
	
	@Column(name = "reg_no")
	private String regNo;
	
	@Column(name = "colour")
	private String colour;
	
	@Column(name = "slot")
	private int slot;

	
	public Integer getCarId() {
		return carId;
	}
	public void setCarId(Integer carId) {
		this.carId = carId;
	}
	public String getRegNo() {
		return regNo;
	}
	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}
	public String getColour() {
		return colour;
	}
	public void setColour(String colour) {
		this.colour = colour;
	}
	public int getSlot() {
		return slot;
	}
	public void setSlot(int slot) {
		this.slot = slot;
	}
	@Override
	public String toString() {
		return "Car [regNo=" + regNo + ", colour=" + colour + ", slot=" + slot + ", carId=" + carId + "]";
	}
	
	
	
	

}
