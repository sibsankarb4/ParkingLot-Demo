package org.parking.lot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "parking_lot")
public class ParkingLot {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "parking_id")
	private Long parkingId;
	
	@Column(name = "car_id")
	private int carId;
	
	@Column(name = "ticket")
	private int ticket;
	
	public Long getParkingId() {
		return parkingId;
	}
	public void setParkingId(Long parkingId) {
		this.parkingId = parkingId;
	}
	public int getCarId() {
		return carId;
	}
	public void setCarId(int carId) {
		this.carId = carId;
	}
	public int getTicket() {
		return ticket;
	}
	public void setTicket(int ticket) {
		this.ticket = ticket;
	}
	@Override
	public String toString() {
		return "ParkingLot [parkingId=" + parkingId + ", carId=" + carId + ", ticket=" + ticket + "]";
	}
	
	

}
