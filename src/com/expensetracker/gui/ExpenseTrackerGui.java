package com.expensetracker.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ExpenseTrackerGui extends JFrame {

	public ExpenseTrackerGui() {
		setTitle("Expense Tracker");
		setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        
        JLabel amountLabel = new JLabel("Amount:");
        JTextField amountField = new JTextField(10);

        JLabel categoryLabel = new JLabel("Category:");
        JTextField categoryField = new JTextField(10);

        JLabel descriptionLabel = new JLabel("Description:");
        JTextField descriptionField = new JTextField(15);

        JButton addButton = new JButton("Add Expense");
        addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String amount = amountField.getText();
				String category = categoryField.getText();
				String description = descriptionField.getText();
                System.out.println("Expense Added: " + amount + ", " + category + ", " + description);
				
			}
		});
        add(amountLabel);
        add(amountField);
        add(categoryLabel);
        add(categoryField);
        add(descriptionLabel);
        add(descriptionField);
        add(addButton);

        setVisible(true);
        
        
	}
}
