package com.expensetracker;

import java.util.List;

import com.expensetracker.model.Expense;
import com.expensetracker.model.ExpenseCategory;
import com.expensetracker.service.ExpenseCategoryManager;
import com.expensetracker.service.ExpenseManager;

public class Application {

	public static void main(String[] args) {
		ExpenseCategoryManager expenceCategoryService = new ExpenseCategoryManager();
		System.out.println(expenceCategoryService.getAllCategories());
		System.out.println(expenceCategoryService.getAllCategories());
		ExpenseCategory category = new ExpenseCategory("FOOD");
		expenceCategoryService.addCategory("FOOD");
		ExpenseManager eManager = new ExpenseManager();
		String response = eManager.addExpense(20.0, category, "burger");
		List<Expense> res= eManager.getAllExpenses();
	
		System.out.println(response);
		System.out.println(res);
	}

}
