package com.MyBank;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class User {
	private String firstname;
	private String lastname;
	private String uuid;
	private byte pinHash[];
	private ArrayList<Account> accounts;

	/**
	 * @param firstname
	 * @param lastname
	 * @param pin
	 * @param theBank
	 */
	public User(String firstname, String lastname, String pin, Bank theBank) {
		this.firstname = firstname;
		this.lastname = lastname;

		try {
			MessageDigest mDigest = MessageDigest.getInstance("MD5");
			this.pinHash = mDigest.digest(pin.getBytes());
		} catch (NoSuchAlgorithmException e) {
			System.err.println("ERROR! CAUGHT NoSuchAlgorithmException");
			e.printStackTrace();
			System.exit(1);
		}

		this.uuid = theBank.getNewUserUUID();

		this.accounts = new ArrayList<Account>();

		System.out.printf("New user %s, %s with ID %s created. \n\n", this.lastname, this.firstname, this.uuid);
		
	}

	/**
	 * @param account
	 */
	public void addAccount(Account account) {
		// TODO Auto-generated method stub
		this.accounts.add(account);

	}

	/**
	 * @return
	 */
	public String getUUID() {
		// TODO Auto-generated method stub
		return this.uuid;
	}

	/**
	 * @param aPin
	 * @return
	 */
	public boolean validatePin(String aPin) {

		try {
			MessageDigest mDigest = MessageDigest.getInstance("MD5");
			return MessageDigest.isEqual(mDigest.digest(aPin.getBytes()), this.pinHash);

		} catch (NoSuchAlgorithmException e) {
			System.err.println("ERROR! CAUGHT NoSuchAlgorithmException");
			e.printStackTrace();
			System.exit(1);
		}

		return false;
	}

	/**
	 * @return the firsdtname
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * @param firstname the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * @param lastname the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * @param accounts the accounts to set
	 */
	public void setAccounts(ArrayList<Account> accounts) {
		this.accounts = accounts;
	}

	/**
	 * 
	 */
	public void printAccSummary() {

		System.out.printf("\n\nAccounts Summary for %s (%s) \n", this.firstname, this.uuid);
		int count = 0;
		for (Account a : this.accounts) {
			count++;
			System.out.printf(" %d) %s\n", count, a.getSummaryLine());
		}
		System.out.println();
	}

	/**
	 * @return
	 */
	public int numAccounts() {
		return this.accounts.size();
	}

	/**
	 * @param accIndex
	 */
	public void printAccTransactionHistory(int accIndex) {
		this.accounts.get(accIndex).printTransactionHistory();
	}
	
	public double getAccBal(int accIndex) {
		return this.accounts.get(accIndex).getBalance();
	}
	
	public String getAccUUID(int accIndex) {
		return this.accounts.get(accIndex).getUUID();
	}

	public void addAccTransaction(int accIndex, double amount, String memo) {
		this.accounts.get(accIndex).addTransaction(amount, memo);
	}
}
