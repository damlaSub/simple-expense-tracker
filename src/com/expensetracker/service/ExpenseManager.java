package com.expensetracker.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import com.expensetracker.model.Expense;
import com.expensetracker.model.ExpenseCategory;
import com.expensetracker.util.ExpenseCsvUtil;

import exceptions.ExpenseNotFoundException;
import exceptions.InvalidExpenseException;

public class ExpenseManager {
	
	private static final String FILE_PATH = "expense.csv";
	private Set<Expense> expenses;
	private Double spendingLimit;
	private static Double alertThresholdPercentage = 80.0;
	
	public ExpenseManager() {
		//LinkedHashSet keeps the order of insertion
		this.expenses = new LinkedHashSet<>();
		this.spendingLimit = null;
	}
	
	public void setSpendingLimit(Double limit) {
		this.spendingLimit = limit;
	}
	
	public void setAlertThresholdPercentage(Double percentage) {
		alertThresholdPercentage = percentage;
	}
	
	public String addExpense(Double amount, ExpenseCategory category, String description) throws InvalidExpenseException, IOException {
		if (amount == null || amount <= 0) {
			throw new InvalidExpenseException("Amount must be greater than zero.");
        }
        if (category == null) {
        	throw new InvalidExpenseException("Category is required.");
        }
        
        if (description != null && description.length() > 100) { 
        	throw new InvalidExpenseException("Description is too long.");
        }
        
        Expense expense = new Expense(amount, LocalDate.now(), category, description);
        expenses.add(expense);

        saveExpensesToCsv();
        
         return String.format("Expense %.02f for %s added successfully!", amount, category.getName());
	}
	
	public void deleteExpense(UUID id) throws ExpenseNotFoundException, IOException {
		boolean removed = expenses.removeIf(e -> e.getId().equals(id));
        if (!removed) {
            throw new ExpenseNotFoundException("Expense not found.");
        }
        saveExpensesToCsv(); 
	}
	
	public Set<Expense> getAllExpenses(){
		return expenses;
	}
	
	public String updateExpense(UUID id, Double amount, String description) throws ExpenseNotFoundException, IOException  {
		Expense expense = expenses.stream().filter(e -> e.getId().equals(id)).findFirst()
				.orElseThrow(() -> new ExpenseNotFoundException("Expense not found"));
		
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

	    saveExpensesToCsv();
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
	
	public double getTotalExpensesForCurrentMonth(LocalDate date) {
		return expenses.stream().filter(e -> e.getDate().getYear() == date.getYear() && e.getDate().getMonthValue() == date.getMonthValue())
			.mapToDouble(Expense::getAmount)
			.sum();
	}

	public String checkExpenseLimit(LocalDate month) {
		 if (spendingLimit == null) {
	            return "Spending limit has not been set.";
	        }
		 
		double totalExpenses = getTotalExpensesForCurrentMonth(month);
        double remainingAmount = spendingLimit - totalExpenses;
        double alertThreshold = spendingLimit * (alertThresholdPercentage / 100);
        
        if (totalExpenses > spendingLimit) {
            return String.format("Warning: You have exceeded your spending limit of %.02f for this month. Total spent: %.02f", spendingLimit, totalExpenses);
        } else if (totalExpenses >= alertThreshold) {
            return String.format("Alert: You are approaching your spending limit of %.02f. Total spent: %.02f. You have %.02f remaining.", spendingLimit, totalExpenses, remainingAmount);
        } else {
            return String.format("You have %.02f remaining in your budget for the month.", remainingAmount);
        }
	}
	
	public void saveExpensesToCsv() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Expense expense : expenses) {
                writer.write(ExpenseCsvUtil.toCsvLine(expense));
                writer.newLine();
            }
        }
    }

    // Load expenses from CSV at startup
    public void loadExpensesFromCsv() throws IOException {
        File file = new File(FILE_PATH);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    Expense expense = ExpenseCsvUtil.fromCsvLine(line);
                    expenses.add(expense);
                }
            }
        }
    }
    
	
}
