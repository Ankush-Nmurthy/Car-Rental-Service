package com.masai.entity;

import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class CarCompany { // inversing side / reference
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String companyName;

	@OneToMany(mappedBy = "carCompany", cascade = CascadeType.ALL) // always contain the owing side variable
	private Set<Cars> cars;

	public CarCompany() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CarCompany(String companyName, Set<Cars> cars) {
		super();
		this.companyName = companyName;
		this.cars = cars;
	}

	public int getId() {
		return id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Set<Cars> getCars() {
		return cars;
	}

	public void setCars(Set<Cars> cars) {
		this.cars = cars;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cars, companyName, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CarCompany other = (CarCompany) obj;
		return Objects.equals(cars, other.cars) && Objects.equals(companyName, other.companyName) && id == other.id;
	}
	

}
