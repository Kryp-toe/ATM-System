package com.MyBank;
import java.util.ArrayList;
import java.util.Random;

public class Bank {
	private String nameString;
	private ArrayList<User> users;
	private ArrayList<Account> accounts;
	
	/**
	 * @param nameString
	 */
	public Bank(String nameString) {
		this.nameString = nameString;
		this.accounts = new ArrayList<Account>();
		this.users = new ArrayList<User>();
	}
	
	/**
	 * @return
	 */
	public String getNewUserUUID() {
		// TODO Auto-generated method stub
		String uuidString;
		Random rngRandom = new Random();
		int len = 6;
		boolean nonUnique = false;
		
		do {
			uuidString = "";
			nonUnique = false;
			for (int c = 0; c < len; c++) {
				uuidString += ((Integer)rngRandom.nextInt(10)).toString();
			}
			for (User u : this.users) {
				if (uuidString.compareTo(u.getUUID()) == 0) {
					nonUnique = true;
					break;
				}
			}
		} while (nonUnique);
		
		return uuidString;
	}
	
	/**
	 * @return
	 */
	public String getNewAccountUUID() {
		// TODO Auto-generated method stub
		String uuidString;
		Random rngRandom = new Random();
		int len = 6;
		boolean nonUnique = false;
		
		do {
			uuidString = "";
			nonUnique = false;
			for (int c = 0; c < len; c++) {
				uuidString += ((Integer)rngRandom.nextInt(10)).toString();
			}
			for (Account a : this.accounts) {
				if (uuidString.compareTo(a.getUUID()) == 0) {
					nonUnique = true;
					break;
				}
			}
		} while (nonUnique);
		
		return uuidString;
	}
	
	/**
	 * @param account
	 */
	public void addAccount(Account account) {
		// TODO Auto-generated method stub
		this.accounts.add(account);
	}
	
	/**
	 * @param firstName
	 * @param lastName
	 * @param pin
	 * @return
	 */
	public User addUser(String firstName, String lastName, String pin)
	{
		User newUser = new User(firstName, lastName, pin, this);
		this.users.add(newUser);
		
		Account newAccount = new Account("Savings", newUser, this);
		newUser.addAccount(newAccount);
		this.addAccount(newAccount);
		
		return newUser;
	}
	
	/**
	 * @param userID
	 * @param pin
	 * @return
	 */
	public User userLogin(String userID, String pin) {
		for(User u: this.users) {
			if(u.getUUID().compareTo(userID) == 0 && u.validatePin(pin)) {
				return u;
			}
		}
		return null;
	}
	
	/**
	 * @return the nameString
	 */
	public String getNameString() {
		return nameString;
	}

	/**
	 * @param nameString the nameString to set
	 */
	public void setNameString(String nameString) {
		this.nameString = nameString;
	}
	
}




















