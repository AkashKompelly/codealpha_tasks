package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class Accounts {
	Connection connection;
	Scanner scanner;

	Accounts() {
	}

//	Parameterized constructor
	public Accounts(Connection connection, Scanner scanner) {
		super();
		this.connection = connection;
		this.scanner = scanner;
	}
	
//	Create account
	public void createAccount() {
		scanner.nextLine();
		System.out.println("Enter your name:");
		String name = scanner.nextLine();
		System.out.println("Enter your email");
		String email = scanner.next();
		System.out.println("Enter Security pin");
		String sPin = scanner.next();
		
		if(accountExist(email)) {
			System.out.println("Email already used to another account.");
			return;
		}
		long accountNum = genarateAccNo();
		String query = "INSERT INTO accounts(account_num, name, email, security_pin) VALUES (?, ?, ?, ?)";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setLong(1, accountNum);
			ps.setString(2,name);
			ps.setString(3, email);
			ps.setString(4, sPin);
			int i = ps.executeUpdate();
			if(i>0) {
				System.out.println("Account creation successfull!");
				System.out.println("Your account Number: " + accountNum);
				return;
			}
			else {
				System.out.println("Accoutn creation failed!");
				return;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

//	Generate account number
	private long genarateAccNo() {
		String query = "SELECT * FROM accounts ORDER BY account_num DESC LIMIT 1";
		try {
			Statement cs = connection.createStatement();
			ResultSet res = cs.executeQuery(query);
			if(res.next()) {
				return res.getLong("account_num")+1;
			}else {
				return 121101001;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	
//	Account existence
	private boolean accountExist(String email) {
		String query = "SELECT * FROM accounts WHERE email = ?";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return true;
			}
			else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
//	Login method
	public String login() {
		System.out.println("Enter mail: ");
		String mail = scanner.next();
		System.out.println("Enter security pin: ");
		String securityPin = scanner.next();
		String query = "SELECT * FROM accounts WHERE email = ? AND security_pin = ?";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, mail);
			ps.setString(2, securityPin);
			 ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				System.out.println("Login successfull!");
				return mail;
			}
			else {
				System.out.println("Login failed! plese check your detais and try again");
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
//	Get account number method
	public long getAccountNum(String email) {
		String query = "SELECT * FROM accounts WHERE email = ?";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getLong("account_num");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		throw new RuntimeException("Account Number Doesn't Exist!");
	}

}
