package com.expensetracker;

import java.io.IOException;

import com.expensetracker.gui.ExpenseTrackerGui;
import com.expensetracker.model.ExpenseCategory;
import com.expensetracker.service.ExpenseManager;

import exceptions.InvalidExpenseException;

public class Application {

	public static void main(String[] args) throws InvalidExpenseException, IOException {
		
		ExpenseManager manager = new ExpenseManager();
		manager.addExpense(10.0, new ExpenseCategory("FOOD"), null);
		manager.getAllExpenses().forEach(System.out::println);
		manager.saveExpensesToCsv();
		new ExpenseTrackerGui();
	}

}
