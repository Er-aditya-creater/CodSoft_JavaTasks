import java.util.*;

class BankAccount
{
    private double balance;

    public BankAccount(double initialBalance)
    {
        this.balance = initialBalance;
    }

//    public double getBalance()
//    {
//        return balance;
//    }

    public void deposit(double amount)
    {
    	 if (amount > 0) 
    	 {
             this.balance += amount;
             System.out.println("Deposit successful. Current balance: " + balance);
         } else {
             System.out.println("Invalid amount. Deposit amount should be greater than 0.");
         }
    }

    public void withdraw(double amount)
    {
        if (balance >= amount)
        {
            balance -= amount;
            System.out.println("Withdrawal successful. Current balance: $" + balance);
        }
        else
        {
            System.out.println("Insufficient balance. Current balance: $" + balance);
        }
    }
    
    public void checkBalance()
    {
    	if(balance>0)
    		{
    		    System.out.println("Current balance: $" + balance);
    		}
    	else
    	{
    		System.out.println("Your Account is Empty.");
    	}
    }
}

class ATM
{
    private BankAccount bankAccount;
    private Scanner scanner;

    public ATM(BankAccount bankAccount) 
    {
        this.bankAccount = bankAccount;
        this.scanner = new Scanner(System.in);
    }

    public void displayOptions()
    {
        System.out.println("----- ATM Menu -----");
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Check Balance");
        System.out.println("4. Exit");
    }

    public void processOption(int option) 
    {
        switch (option) 
        {
            case 1:
                System.out.print("Enter withdrawal amount: $");
                double withdrawAmount = scanner.nextDouble();
                bankAccount.withdraw(withdrawAmount);
                break;
            case 2:
                System.out.print("Enter deposit amount: $");
                double depositAmount = scanner.nextDouble();
                bankAccount.deposit(depositAmount);
                break;
            case 3:
            	bankAccount.checkBalance();
                break;
            case 4:
                System.out.println("Thank you for using the ATM. Goodbye!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    public void run()
    {
        while (true) 
        {
        	System.out.println("Welcome to the ATM!");
            displayOptions();
            System.out.print("Enter option: ");
            int option = scanner.nextInt();
            processOption(option);
            System.out.println();
        }
    }
}


public class Atmsystem_4
{
    public static void main(String[] args)
    {
    	System.out.print("When you open Bankaccount first time you want to invest some money :$ ");
    	double inputmoney;
   	    Scanner sc;
   	    sc=new Scanner(System.in);
	    inputmoney=sc.nextDouble();
	    BankAccount bankAccount = new BankAccount(inputmoney); 
	    ATM atm = new ATM(bankAccount);
        atm.run();
    }
}