package com.MyBank;
import java.util.ArrayList;

public class Account {
	private String nameString;
	private String uuidString;
	private User holderUser;
	private ArrayList<Transaction> transactions;
	
	/**
	 * Create account instance
	 * @param name - the name of the account
	 * @param holder - user object that holds the account
	 * @param theBank - bank object that issued the account
	 */
	public Account(String name, User holder, Bank theBank) {
		this.nameString = name;
		this.holderUser = holder;
		
		//get a new UUID
		this.uuidString = theBank.getNewAccountUUID();
		
		//create a list of transactions
		this.transactions = new ArrayList<Transaction>();
	}

	/**
	 * @return UUID
	 */
	public String getUUID() {
		// TODO Auto-generated method stub
		return this.uuidString;
	}
	
	/**
	 * @return summary line of account (Balance and Account Number)
	 */
	public String getSummaryLine() {
		
		//get the account balance
		double balance = this.getBalance();
		
		//if balance is + or - display appropriate symbol with it
		if(balance >= 0) {
			return String.format("%s : R%.02f : %s", this.uuidString, balance, this.nameString);
		}else {
			return String.format("%s : R(%.02f) : %s", this.uuidString, balance, this.nameString);
		}
	}

	/**
	 * @return balance of account
	 */
	public double getBalance() {
		
		double bal = 0;
		
		//for each transaction
		for(Transaction t : this.transactions) {
			
			//add the transaction to the balance
			bal += t.getAmount();
		}
		return bal;
	}

	/**
	 * Print transaction history for account
	 */
	public void printTransactionHistory()
	{
		System.out.printf("\nTransaction history for account %s\n", this.uuidString);
		
		for (int t = this.transactions.size()-1; t >= 0; t--) 
		{
			System.out.println(this.transactions.get(t).getSummaryLine());
		}
		
		System.out.println();
	}
	
	/**
	 * Add a new transaction in this account.
	 * @param amount	the amount transacted
	 * @param memo		the transaction memo
	 */
	public void addTransaction(double amount, String memo)
	{
		//crreate an instance of the transaction
		Transaction newTransaction = new Transaction(amount, memo, this);
		
		//add the transaction to the list
		this.transactions.add(newTransaction);
	}
	
}
