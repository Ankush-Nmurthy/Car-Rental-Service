package com.masai.entity;

import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private LocalDateTime transactiondata;
	private double totalAmount;

	@OneToOne
	@JoinColumn(name = "booking_id", nullable = false)
	private Bookings bookings;

	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Transaction(LocalDateTime transactiondata, double totalAmount, Bookings bookings) {
		super();
		this.transactiondata = transactiondata;
		this.totalAmount = totalAmount;
		this.bookings = bookings;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getTransactiondata() {
		return transactiondata;
	}

	public void setTransactiondata(LocalDateTime transactiondata) {
		this.transactiondata = transactiondata;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Bookings getBookings() {
		return bookings;
	}

	public void setBookings(Bookings bookings) {
		this.bookings = bookings;
	}

}
