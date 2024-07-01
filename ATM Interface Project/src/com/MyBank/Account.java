package com.MyBank;
import java.util.ArrayList;

public class Account {
	private String nameString;
	private String uuidString;
	private User holderUser;
	private ArrayList<Transaction> transactions;
	
	/**
	 * @param name
	 * @param holder
	 * @param theBank
	 */
	public Account(String name, User holder, Bank theBank) {
		this.nameString = name;
		this.holderUser = holder;
		
		this.uuidString = theBank.getNewAccountUUID();
		
		this.transactions = new ArrayList<Transaction>();
	}

	/**
	 * @return
	 */
	public String getUUID() {
		// TODO Auto-generated method stub
		return this.uuidString;
	}

	/**
	 * @return
	 */
	public String getSummaryLine() {
		
		double balance = this.getBalance();
		
		if(balance >= 0) {
			return String.format("%s : R%.02f : %s", this.uuidString, balance, this.nameString);
		}else {
			return String.format("%s : R(%.02f) : %s", this.uuidString, balance, this.nameString);
		}
	}

	/**
	 * @return
	 */
	public double getBalance() {
		
		double bal = 0;
		for(Transaction t : this.transactions) {
			bal += t.getAmount();
		}
		return bal;
	}

	/**
	 * 
	 */
	public void printTransactionHistory() {
		System.out.printf("\nTransaction history for account %s\n", this.uuidString);
		for (int t = this.transactions.size()-1; t >= 0; t--) {
			System.out.println(this.transactions.get(t).getSummaryLine());
		}
		System.out.println();
	}

	public void addTransaction(double amount, String memo) {
		Transaction newTransaction = new Transaction(amount, memo, this);
		this.transactions.add(newTransaction);
	}
	
}
