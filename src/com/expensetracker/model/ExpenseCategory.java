package com.expensetracker.model;

import java.util.Objects;

public class ExpenseCategory {

	private String name;
	
	public ExpenseCategory(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setNname(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "ExpenseCategory [name=" + name + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExpenseCategory other = (ExpenseCategory) obj;
		return Objects.equals(name, other.name);
	}

	
}
