package com.expensetracker;

import java.io.IOException;

import com.expensetracker.service.ExpenseManager;

import exceptions.InvalidExpenseException;

public class Application {

	public static void main(String[] args) throws InvalidExpenseException, IOException {
		
		ExpenseManager manager = new ExpenseManager();
		manager.loadExpensesFromCsv();
		manager.getAllExpenses().forEach(System.out::println);
		
	}

}
