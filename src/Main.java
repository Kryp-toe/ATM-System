import java.util.Scanner;

import com.MyBank.ATM;
import com.MyBank.Account;
import com.MyBank.Bank;
import com.MyBank.User;

public class Main {

	public static void main(String[] args) {
			
			Scanner scanner = new Scanner(System.in);
			
			Bank theBank = new Bank("Royal Bank of Poverty");
			
			User aUser = theBank.addUser("John", "Die", "1234");
			
			Account newAccount = new Account("Checking", aUser, theBank);
			aUser.addAccount(newAccount);
			theBank.addAccount(newAccount);
			
			User curUser;
			while (true) {
				curUser = ATM.mainMenuPrompt(theBank, scanner);
				
				ATM.printUserMenu(curUser, scanner);
			}
		}
	}