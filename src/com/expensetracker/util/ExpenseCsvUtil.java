package com.expensetracker.util;

import java.time.LocalDate;
import java.util.UUID;

import com.expensetracker.model.Expense;
import com.expensetracker.model.ExpenseCategory;

public class ExpenseCsvUtil {

	
	public static String toCsvLine(Expense expense) {
        return String.format("%s,%f,%s,%s,%s",
                expense.getId().toString(),
                expense.getAmount(),
                expense.getDate().toString(),
                expense.getCategory().getName(),
                expense.getDescription() != null ? expense.getDescription() : "");
    }

	public static Expense fromCsvLine(String line) {
        String[] fields = line.split(",", -1);
        UUID id = UUID.fromString(fields[0]);
        Double amount = Double.parseDouble(fields[1]);
        LocalDate date = LocalDate.parse(fields[2]);
        ExpenseCategory category = new ExpenseCategory(fields[3]);
        String description = fields[4].isEmpty() ? null : fields[4];
        
        return new Expense(id, amount, date, category, description);
	}

}
	
	

