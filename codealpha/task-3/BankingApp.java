package com.Banking_App;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import com.DAO.AccountManager;
import com.DAO.Accounts;

public class BankingApp {

	private final static String url = "jdbc:mysql://localhost:3306/database_name";
	private final static String un = "username";
	private final static String pw = "password";

	public static void main(String[] args) {
		String mail;
		long account_num;
		Connection connection = null;
		Scanner scanner = null;
		try {
//			Loading drivers
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
//			Establishing connection
			connection = DriverManager.getConnection(url, un, pw);
			scanner = new Scanner(System.in);
//			Creating object of accounts class
			Accounts account1 = new Accounts(connection, scanner);
//			Creating object of accountsManager class
			AccountManager accountManager = new AccountManager(connection, scanner);

			while (true) {
				System.out.println();
				System.out.println("=====Wellcom to Banking System=====");
				System.out.println("1.Login");
				System.out.println("2.Create Account");
				System.out.println("3.Exit");
				System.out.println();
				System.out.println("================");
				System.out.print("Enter your option: ");
				int choise = scanner.nextInt();

				switch (choise) {
				case 1:
					String login = account1.login();
					if (login != null) {
						mail = login;
						account_num = account1.getAccountNum(mail);
						int option = 0;
						while (option != 4) {
							System.out.println();
							System.out.println("+++++Wellcome to the Banking menu+++++");
							System.out.println("1.Check Balance");
							System.out.println("2.Deposite Money");
							System.out.println("3.Withdraw Money");
							System.out.println("4.Exit");
							System.out.println("================");
							System.out.print("Enter you option: ");
							option = scanner.nextInt();

							switch (option) {
							case 1:
//								calling checkBalance method
								accountManager.checkBalance(account_num);
								break;
							case 2:
//								calling depositeMoney method
								accountManager.depostiteMoney(account_num);
								break;
							case 3:
//								calling withdrawMoney method
								accountManager.withdrawMoney(account_num);
								break;
							case 4:
								System.out.println("Exiting menu...");
								break;
							default:
								System.out.println("Invalid option!, try again.");
								break;
							}
						}
					} else {
						break;
					}
					break;
				case 2:
//					calling createAccoutn method
					account1.createAccount();
					break;
				case 3:
//					Exiting message
					System.out.println("Thanks for using Banking system");
					System.out.println("Exiting system...");
					return;
				default:
					System.out.println("Ente a valid option");
					break;
				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
//		closing resources
		try {
			connection.close();
			scanner.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
