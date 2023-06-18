package com.masai.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Bookings {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id", nullable = false)
	private Customer customer;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Car_id", nullable = false)
	private Cars car;

	@Column(name = "location" , nullable = false)
	private String location;

	@Column(name = "Start_date", nullable = false)
	private LocalDate startDate;

	@Column(name = "End_date", nullable = false)
	private LocalDate endDate;

	@Column(nullable = false)
	private String status;

	public Bookings() {
		super();
	}

	public Bookings(Customer customer, Cars car, String location, LocalDate startDate, LocalDate endDate,
			String status) {
		super();
		this.customer = customer;
		this.car = car;
		this.location = location;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Cars getCar() {
		return car;
	}

	public void setCar(Cars car) {
		this.car = car;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Bookings [id=" + id + ", customer=" + customer + ", car=" + car + ", location=" + location
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", status=" + status + "]";
	}
	
}
