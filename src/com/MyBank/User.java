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
	 * @param firstname - users first name
	 * @param lastname - users second name
	 * @param pin - users account oin (as string)
	 * @param theBank - the bank the User is using
	 */
	public User(String firstname, String lastname, String pin, Bank theBank)
	{
		this.firstname = firstname;
		this.lastname = lastname;
		
		//store the pins MD5 Hash instead of the actual code
		try {
			MessageDigest mDigest = MessageDigest.getInstance("MD5");
			this.pinHash = mDigest.digest(pin.getBytes());
		}catch (NoSuchAlgorithmException e) {
			System.err.println("ERROR! CAUGHT NoSuchAlgorithmException");
			e.printStackTrace();
			System.exit(1);
		}
		
		//get the users UUID
		this.uuid = theBank.getNewUserUUID();
		
		this.accounts = new ArrayList<Account>();
		
		//print log message
		System.out.printf("New user %s, %s with ID %s created. \n\n", this.lastname, this.firstname, this.uuid);
		
	}

	/**
	 * @param account - account to be added to list
	 */
	public void addAccount(Account account)
	{
		this.accounts.add(account);

	}

	/**
	 * @return users UUID
	 */
	public String getUUID() {
		return this.uuid;
	}

	/**
	 * Ensure the PIN is correct
	 * @param aPin - PIN entered
	 * @return true if the pin is correct
	 */
	public boolean validatePin(String aPin)
	{
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
	 * @param firstname - the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * @param lastname - the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * @param accounts - the accounts to set
	 */
	public void setAccounts(ArrayList<Account> accounts) {
		this.accounts = accounts;
	}

	/**
	 * Print summaries for the accounts the user owns
	 */
	public void printAccSummary() {

		System.out.printf("\n\nAccounts Summary for %s (%s) \n", this.firstname, this.uuid);
		int count = 0;
		
		for (Account a : this.accounts)
		{
			count++;
			System.out.printf(" %d) %s\n", count, a.getSummaryLine());
		}
		
		System.out.println();
	}
	
	/**
	 * @return number of accounts the user owns
	 */
	public int numAccounts() {
		return this.accounts.size();
	}

	/**
	 * Print account history for a particular account
	 * @param accIndex - the specific account
	 */
	public void printAccTransactionHistory(int accIndex) {
		this.accounts.get(accIndex).printTransactionHistory();
	}
	
	/**
	 * Get the balance of a particular account.
	 * @param accIndex - the index of the account to use
	 * @return the balance of the account
	 */
	public double getAccBal(int accIndex) {
		return this.accounts.get(accIndex).getBalance();
	}
	
	/**
	 * Get the UUID of a particular account.
	 * @param accIndex - the index of the account to use
	 * @return the UUID of the account
	 */
	public String getAccUUID(int accIndex) {
		return this.accounts.get(accIndex).getUUID();
	}
	
	/**
	 * Add a transaction to a particular account.
	 * @param accIndex - the index of the account
	 * @param amount - the amount of the transaction
	 * @param memo - the memo of the transaction
	 */
	public void addAccTransaction(int accIndex, double amount, String memo) {
		this.accounts.get(accIndex).addTransaction(amount, memo);
	}
}
