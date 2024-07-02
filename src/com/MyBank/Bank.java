package com.MyBank;
import java.util.ArrayList;
import java.util.Random;

public class Bank {
	private String nameString;
	private ArrayList<User> users;
	private ArrayList<Account> accounts;
	
	/**
	 * Create the Bank
	 * @param nameString - the name of the bank
	 */
	public Bank(String nameString) {
		this.nameString = nameString;
		this.accounts = new ArrayList<Account>();
		this.users = new ArrayList<User>();
	}
	
	/**
	 * generate a new Universal Unique ID
	 * @return the UUID
	 */
	public String getNewUserUUID()
	{
		String uuidString;
		Random rngRandom = new Random();
		int len = 6;
		boolean nonUnique = false;
		
		//loop until a unique ID is created
		do {
			
			uuidString = "";
			nonUnique = false;
			
			//generate the number (6 times)
			for (int c = 0; c < len; c++)
			{
				//a random number between 0 and 10 is added to the UUID String
				uuidString += ((Integer)rngRandom.nextInt(10)).toString();
			}
			
			//ensure the UUID is unique
			for (User u : this.users)
			{
				if (uuidString.compareTo(u.getUUID()) == 0)
				{
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
		
		String uuidString;
		Random rngRandom = new Random();
		int len = 10;
		boolean nonUnique = false;
		
		//loop until the UUID is unique
		do {
			uuidString = "";
			nonUnique = false;
			
			//generate a number (10 times)
			for (int c = 0; c < len; c++)
			{
				//a random number between 0 and 10 is added to the UUID String
				uuidString += ((Integer)rngRandom.nextInt(10)).toString();
			}
			
			//ensure the UUID is unique
			for (Account a : this.accounts)
			{
				if (uuidString.compareTo(a.getUUID()) == 0)
				{
					nonUnique = true;
					break;
				}
			}
		} while (nonUnique);
		
		return uuidString;
	}
	
	/**
	 * Adds an existing account for a specific user
	 * @param account - the account to add
	 */
	public void addAccount(Account account)
	{
		this.accounts.add(account);
	}
	
	/**
	 * Create a new user for the bank
	 * @param firstName - users first name
	 * @param lastName - users last name
	 * @param pin - users pin
	 * @return a new user object
	 */
	public User addUser(String firstName, String lastName, String pin)
	{
		//create a new user and add it to the list
		User newUser = new User(firstName, lastName, pin, this);
		this.users.add(newUser);
		
		//create a savings account for the user and add it to the list of accounts for the user
		Account newAccount = new Account("Savings", newUser, this);
		newUser.addAccount(newAccount);
		this.addAccount(newAccount);
		
		return newUser;
	}
	
	/**
	 * Get the user object for the specific userID and PIN
	 * @param userID - user login ID
	 * @param pin - user pin
	 * @return user object or null if unsuccessful login
	 */
	public User userLogin(String userID, String pin)
	{
		//for each user
		for(User u: this.users)
		{
			//user ID and PIN is correct
			if(u.getUUID().compareTo(userID) == 0 && u.validatePin(pin))
			{
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




















