package com.expensetracker.model;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class Expense {

	private UUID id;
	private Double amount;
	private LocalDate date;
	private ExpenseCategory category;
	private String description;
	
	 public Expense(Double amount, LocalDate date, ExpenseCategory category, String description) {
	        this.id = UUID.randomUUID(); 
	        this.amount = amount;
	        this.date = date;
	        this.category = category;
	        this.description = description;
	    }

	public UUID getId() {
		return id;
	}


	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public ExpenseCategory getCategory() {
		return category;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Expense [id=" + id + ", amount=" + amount + ", date=" + date + ", category=" + category
				+ ", description=" + description + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Expense other = (Expense) obj;
		return Objects.equals(id, other.id);
	}
	

	 
}
