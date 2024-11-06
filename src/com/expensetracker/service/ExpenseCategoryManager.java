package com.expensetracker.service;

import com.expensetracker.model.DefaultExpenseCategory;
import com.expensetracker.model.ExpenseCategory;

import java.util.ArrayList;
import java.util.List;

public class ExpenseCategoryManager {

    private List<ExpenseCategory> categories;

    public ExpenseCategoryManager() {
        this.categories = new ArrayList<>();
        initializeDefaultCategories();
    }

    private void initializeDefaultCategories() {
        for (DefaultExpenseCategory category : DefaultExpenseCategory.values()) {
            categories.add(new ExpenseCategory(category.name()));  // Adding enum-based categories
        }
    }

    public String addCategory(String name) {
    	if(isExistingCategory(name)) {
    		return String.format("Category '%s' already exists.", name);
    	} else {
    		categories.add(new ExpenseCategory(name.toUpperCase()));
    		return String.format("Category '%s' has been added.", name);
    	}
    }

    public List<ExpenseCategory> getAllCategories() {
        return categories;
    }

    Boolean isExistingCategory(String categoryName) {
        return categories.stream().anyMatch(c -> c.getName().equalsIgnoreCase(categoryName));
    }
}
