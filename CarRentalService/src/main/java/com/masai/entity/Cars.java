package com.masai.entity;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Cars_table")
public class Cars { // owing side
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int carId;

//	private String brand;

	@Column(name = "car_model", nullable = false)
	private String model;

	@Column(name = "manufacturing_year", nullable = false)
	private int year;

	private int mileage;

	private boolean availability;

	@ManyToOne
	@JoinColumn(name = "car_company_id")
	private CarCompany carCompany;

	public Cars() {
		super();
	}

	public Cars(String model, int year, int mileage, boolean availability, CarCompany carCompany) {
		super();
		this.model = model;
		this.year = year;
		this.mileage = mileage;
		this.availability = availability;
		this.carCompany = carCompany;
	}

	public int getCarId() {
		return carId;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMileage() {
		return mileage;
	}

	public void setMileage(int mileage) {
		this.mileage = mileage;
	}

	public boolean isAvailability() {
		return availability;
	}

	public void setAvailability(boolean availability) {
		this.availability = availability;
	}

	public CarCompany getCarCompany() {
		return carCompany;
	}

	public void setCarCompany(CarCompany carCompany) {
		this.carCompany = carCompany;
	}

	@Override
	public int hashCode() {
		return Objects.hash(availability, carId, mileage, model, year);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cars other = (Cars) obj;
		return availability == other.availability && carId == other.carId && mileage == other.mileage
				&& Objects.equals(model, other.model) && year == other.year;
	}

	@Override
	public String toString() {
		return "Cars [carId=" + carId + ", model=" + model + ", year=" + year + ", mileage=" + mileage
				+ ", availability=" + availability + "]";
	}

}
