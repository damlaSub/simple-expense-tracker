package com.expensetracker.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import com.expensetracker.model.Expense;
import com.expensetracker.model.ExpenseCategory;

public class ExpenseManager {
	
	private Set<Expense> expenses;
	
	public ExpenseManager() {
		//LinkedHashSet keeps the order of insertion
		this.expenses = new LinkedHashSet<>();
	}
	
	public String addExpense(Double amount, ExpenseCategory category, String description) {
		if (amount == null || amount <= 0) {
            return "Amount must be greater than zero.";
        }
        if (category == null) {
            return "Category is required.";
        }
        
        if (description != null && description.length() > 100) { 
            return "Description is too long.";
        }
        
        Expense expense = new Expense(amount, LocalDate.now(), category, description);
        expenses.add(expense);

         return String.format("Expense %.02f for %s added successfully!", amount, category.getName());
	}
	
	public void deleteExpense(UUID id) {
		expenses.removeIf(e -> e.getId().equals(id));
	}
	
	public Set<Expense> getAllExpenses(){
		return expenses;
	}
	
	public String updateExpense(UUID id, Double amount, String description) {
		Expense expense = expenses.stream().filter(e -> e.getId().equals(id)).findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Expense not found."));
		
	    if (amount != null && amount <= 0) {
	        return "Amount must be greater than zero.";
	    }
	    if (description != null && description.length() > 100) {
	        return "Description is too long.";
	    }

	    if (amount != null) {
	        expense.setAmount(amount);
	    }

	    if (description != null) {
	        expense.setDescription(description);
	    }

	    return String.format("Expense has been updated successfully.");
	}
	
	public double calculateMontlyTotal(int month, int year) {
		return expenses.stream().filter(e -> e.getDate().getYear() == year && e.getDate().getMonthValue() == month)
			.mapToDouble(Expense::getAmount)
			.sum();
	}
	public Map<String, Double> calculateSumByCategory(int month, int year) {
		return expenses.stream().filter(e -> e.getDate().getYear() == year && e.getDate().getMonthValue() == month)
			.collect(Collectors.groupingBy(e -> e.getCategory().getName(), Collectors.summingDouble(Expense::getAmount)));
	}

}
