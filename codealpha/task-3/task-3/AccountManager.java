package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AccountManager {
	Connection connection;
	Scanner scanner;
	
	public AccountManager() {
		super();
	}

	public AccountManager(Connection connection, Scanner scanner) {
		super();
		this.connection = connection;
		this.scanner = scanner;
	}
	 
//	Check balance method
	public  void checkBalance(long accountNumber) {
		String query = "SELECT * FROM accounts WHERE account_num = ?";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setLong(1, accountNumber);
			ResultSet rs = ps.executeQuery();
			rs.next();
			double balance = rs.getDouble("balance");
			System.out.println("Balance: " + balance);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
//	Deposit money method
	public void depostiteMoney(long account_num) {
		System.out.println("Enter the amount: ");
		int amount = scanner.nextInt();
		System.out.println("Enter security pin: ");
		String security_pin = scanner.next();
		
		String query = "SELECT * FROM accounts WHERE account_num = ? and security_pin = ?";
		try {
			connection.setAutoCommit(false);
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setLong(1, account_num);
			ps.setString(2, security_pin);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String query1 = "UPDATE accounts SET balance = balance + ? WHERE account_num = ?";
				PreparedStatement ps1 = connection.prepareStatement(query1);
				ps1.setDouble(1, amount);
				ps1.setLong(2, account_num);
				int i = ps1.executeUpdate();
				if(i>0) {
					System.out.println(amount+" Deposited successfully!");
					connection.commit();
					connection.setAutoCommit(true);
					return;
				}else {
					System.out.println("Transaction failed!");
					connection.rollback();
					connection.setAutoCommit(true);
				}
				
			}else {
				System.out.println("Incorrect security pin!");
				connection.commit();
				connection.setAutoCommit(true);
				
			}
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
//	Withdraw money method
	public void withdrawMoney(long account_num) {
		System.out.println("Enter the amount: ");
		int amount = scanner.nextInt();
		System.out.println("Enter security pin: ");
		String security_pin = scanner.next();
		
		String query = "SELECT * FROM accounts WHERE account_num = ? and security_pin = ?";
		try {
			connection.setAutoCommit(false);
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setLong(1, account_num);
			ps.setString(2, security_pin);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String query1 = "UPDATE accounts SET balance = balance - ? WHERE account_num = ?";
				PreparedStatement ps1 = connection.prepareStatement(query1);
				ps1.setDouble(1, amount);
				ps1.setLong(2, account_num);
				int i = ps1.executeUpdate();
				if(i>0) {
					System.out.println(amount+" withdraw successfully!");
					connection.commit();
					connection.setAutoCommit(true);
					return;
				}else {
					System.out.println("Transaction failed!");
					connection.rollback();
					connection.setAutoCommit(true);
				}
				
			}else {
				System.out.println("Incorrect security pin!");
				connection.commit();
				connection.setAutoCommit(true);
				
			}
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
