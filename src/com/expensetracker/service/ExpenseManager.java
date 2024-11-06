package com.expensetracker.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.expensetracker.model.Expense;
import com.expensetracker.model.ExpenseCategory;

public class ExpenseManager {
	
	private List<Expense> expenses;
	
	public ExpenseManager() {
		this.expenses = new ArrayList<>();
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
        
        LocalDate date = LocalDate.now();
        
        Expense expense = new Expense(amount, date, category, description);
        expenses.add(expense);

         return String.format("Expense %.02f for %s added successfully!", amount, category.getName());
	}
	
	public void deleteExpense(UUID id) {
		expenses.removeIf(e -> e.getId().equals(id));
	}
	
	public List<Expense> getAllExpenses(){
		return expenses;
	}
	

}
