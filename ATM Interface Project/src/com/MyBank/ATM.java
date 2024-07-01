package com.MyBank;
import java.util.Scanner;

public class ATM {

	/**
	 * Print ATM menu for user action
	 * @param curUser - the User object that is logged in
	 * @param scanner - the scanner object to read user input
	 */
	public static void printUserMenu(User curUser, Scanner scanner) {
		
		//print a summary of the users accounts
		curUser.printAccSummary();
		
		int choice;
		
		//user menu
		do {
			System.out.printf("Welcome %s, what would you like to do? \n", curUser.getFirstname());
			System.out.println(" 1) Transaction History");
			System.out.println(" 2) Withdraw");
			System.out.println(" 3) Deposit");
			System.out.println(" 4) Transfer");
			System.out.println(" 5) Quit");
			System.out.printf("Enter choice: ");
			choice = scanner.nextInt();
			
			//validate choice
			if(choice < 1 || choice > 5) {
				System.out.println("Invalid choice. Please choose 1-5");
			}
			
		}while(choice < 1 || choice > 5);
		
		//process choice
		switch (choice) {
			case 1: {
				ATM.showTransactionHistory(curUser, scanner);
				break;
			}
			case 2: {
				ATM.withdraw(curUser, scanner);
				break;
			}
			case 3: {
				ATM.deposit(curUser, scanner);
				break;
			}
			case 4: {
				ATM.transfer(curUser, scanner);
				break;
			}
			case 5: {
				scanner.nextLine(); //ensure there is no input left
				break;
			}
		}
		
		//redispla the menu unless user quits
		if(choice != 5) {
			ATM.printUserMenu(curUser, scanner);
		}
	}

	/**
	 * Tranfering funds from one account to another
	 * @param curUser - the User object that is logged in
	 * @param scanner - the scanner object to read user input
	 */
	private static void transfer(User curUser, Scanner scanner) {
		
		int fromAcc;
		int toAcc;
		double amount;
		double accBal;
		
		//get account to transfer from
		do {
			System.out.printf("Enter the number (1-%d) of the account to tranfer from: ", curUser.numAccounts());
			fromAcc = scanner.nextInt() - 1;
			
			//validate account
			if(fromAcc < 0 || fromAcc >= curUser.numAccounts()) {
				System.out.println("Invalid account. Try again.");
			}
		} while (fromAcc < 0 || fromAcc >= curUser.numAccounts());
		
		//get balance of account transferring from
		accBal = curUser.getAccBal(fromAcc);
		
		//get account to transfer to
		do {
			System.out.printf("Enter the number (1-%d) of the account to tranfer to: ", curUser.numAccounts());
			toAcc = scanner.nextInt() - 1;
			
			//validate account
			if(toAcc < 0 || toAcc >= curUser.numAccounts()) {
				System.out.println("Invalid account. Try again.");
			}
		} while (toAcc < 0 || toAcc >= curUser.numAccounts());
		
		//get amount user wants to transfer
		do {
			System.out.printf("Enter the amount to tranfer (max R%.02f): R", accBal);
			amount = scanner.nextDouble();
			
			//validate amount
			if (amount < 1) {
				System.out.println("Amount cannot be less than R1");
			}else if (amount > accBal) {
				System.out.printf("Insufficient funds (Balance: R%.02f). \n", accBal);
			}
		} while (amount < 1 || amount > accBal);
		
		//do the transfer - add a transaction to each account involved, 1 to remove the funds and 1 to add the funds
		curUser.addAccTransaction(fromAcc, -1*amount, String.format("Tranfer to account %s", curUser.getAccUUID(toAcc)));
		curUser.addAccTransaction(toAcc, amount, String.format("Tranfer from account %s", curUser.getAccUUID(fromAcc)));
	}

	/**
	 * Deposit funds into an account
	 * @param curUser - the User object that is logged in
	 * @param scanner - the scanner object to read user input
	 */
	private static void deposit(User curUser, Scanner scanner) {
		
		int toAcc;
		double amount;
		double accBal;
		String memoString;
		
		do {
			System.out.printf("Enter the number (1-%d) of the account to deposit into: ", curUser.numAccounts());
			toAcc = scanner.nextInt()-1;
			if(toAcc < 0 || toAcc >= curUser.numAccounts()) {
				System.out.println("Invalid account. Try again.");
			}
		} while (toAcc < 0 || toAcc >= curUser.numAccounts());
		
		accBal = curUser.getAccBal(toAcc);
		
		do {
			System.out.printf("Enter the amount to deposit: R", accBal);
			amount = scanner.nextDouble();
			if (amount < 1) {
				System.out.println("Amount cannot be less than R1");
			}
		} while (amount < 1);
		
		scanner.nextLine();
		
		System.out.printf("Enter the transaction memo: ");
		memoString = scanner.nextLine();
		
		curUser.addAccTransaction(toAcc, amount, memoString);
	}

	/**
	 * Withdraw funds from an account
	 * @param curUser - the User object that is logged in
	 * @param scanner - the scanner object to read user input
	 */
	private static void withdraw(User curUser, Scanner scanner) {
		
		int fromAcc;
		double amount;
		double accBal;
		String memoString;
		
		//get the account to withdraw from
		do {
			System.out.printf("Enter the number (1-%d) of the account to withdraw from: ", curUser.numAccounts());
			fromAcc = scanner.nextInt()-1;
			
			//validate account
			if(fromAcc < 0 || fromAcc >= curUser.numAccounts()) {
				System.out.println("Invalid account. Try again.");
			}
		} while (fromAcc < 0 || fromAcc >= curUser.numAccounts());
		
		//get balance of the account
		accBal = curUser.getAccBal(fromAcc);
		
		//get the amount to withdraw
		do {
			System.out.printf("Enter the amount to withdraw (max R%.02f): R", accBal);
			amount = scanner.nextDouble();
			
			//validate amount
			if (amount < 1) {
				System.out.println("Amount cannot be less than R1");
			}else if (amount > accBal) {
				System.out.printf("Insufficient funds (Balance: R%.02f. \n", accBal);
			}
		} while (amount < 1 || amount > accBal);
		
		//ensure there is no input left
		scanner.nextLine();
		
		//get the transaction memo
		System.out.printf("Enter the transaction memo: ");
		memoString = scanner.nextLine();
		
		//do the withdrawl - remove the funds from the account
		curUser.addAccTransaction(fromAcc, -1*amount, memoString);
	}

	/**
	 * @param curUser - the User object that is logged in
	 * @param scanner - the scanner object to read user input
	 */
	private static void showTransactionHistory(User curUser, Scanner scanner) {
		// TODO Auto-generated method stub
		int theAcc;
		do {
			System.out.printf("Enter the account number (1 - %d) of the account you want to view: ", curUser.numAccounts());
			theAcc = scanner.nextInt() - 1;
			if (theAcc < 0 || theAcc >= curUser.numAccounts()) {
				System.out.println("Invalid account. Try again.");
			}
		} while (theAcc < 0 || theAcc >= curUser.numAccounts());
		curUser.printAccTransactionHistory(theAcc);
	}

	/**
	 * Print ATM login menu
	 * @param theBank - Bank object whose account is being used
	 * @param scanner - Scanner object for user input
	 * @return authUser - the verified User object
	 */
	public static User mainMenuPrompt(Bank theBank, Scanner scanner) {
		
		String userIDString;
		String pinString;
		User authUser;
		
		//prompt user for ID and PIN until correct one is entered
		do {
			System.out.printf("Welcome to %s\n\n", theBank.getNameString());
			
			System.out.println("Enter your User ID: ");
			userIDString = scanner.nextLine();
			
			System.out.println("Enter your Pin: ");
			pinString = scanner.nextLine();
			
			//validate user ID and PIN and return the corresponding User object
			authUser = theBank.userLogin(userIDString, pinString);
			if(authUser == null) {
				System.out.println("Incorrect User ID or Pin. Please try again.");
			}
			
		} while (authUser == null);
		
		return authUser;
	}
}
