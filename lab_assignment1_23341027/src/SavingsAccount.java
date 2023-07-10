import java.util.ArrayList;
import java.util.Scanner;

public class SavingsAccount  extends BankAccount{
    private static String studentInterestRate="0%";
    private static String regularInterestRate="1.5%";
    private static SavingsAccount savingsAccount;
    private  static  ArrayList<Transaction> transactions;
    private static Scanner scanner;
    CheckingAccount checkingAccount;
    private SavingsAccount(){
        scanner=new Scanner(System.in);
        checkingAccount=CheckingAccount.getInstance();
    }
    public static SavingsAccount getInstance(){
        if (savingsAccount==null){
            savingsAccount=new SavingsAccount();

        }
         return savingsAccount;
    }

    public BankAccount createAccount(int accountNumber, String accountName){
        BankAccount bankAccount=new BankAccount(accountNumber,accountName);
        System.out.println("Congratulations "+accountName+"! you account has been created");
        System.out.println();
        return bankAccount;
    }

    public Double getBalance(){
        return savingsAccount.getBalance();
    }
    public void banner(){
        System.out.println("1.Student account with interest rate of "+studentInterestRate+".\n"+
                "2.Regular account with interest rate of "+ regularInterestRate+".");


    }

    public void withdraw(double amount, BankAccount userExistence){
        if (checkingAccount.overDraftLimit(amount,userExistence)){
            double new_balance=userExistence.getBalance()-amount;
            userExistence.setBalance(new_balance);
            Transaction transaction=new Transaction("Today","withdraw",amount,new_balance);
            ArrayList<Transaction> newTransactions= userExistence.getTransactions();
            newTransactions.add(transaction);
            userExistence.setTransactions(newTransactions);
        }
        else{
            System.out.println("Your entered amount that breaches your current balance limit");
        }

    }

    public Double  deposit(double amount, BankAccount userExistence){
        double new_balance=userExistence.getBalance()+amount;
        userExistence.setBalance(new_balance);
        Transaction transaction=new Transaction("Today","deposit",amount,new_balance);
        ArrayList<Transaction> newTransactions= userExistence.getTransactions();
        newTransactions.add(transaction);
        userExistence.setTransactions(newTransactions);
        return new_balance;
    }

    public void printStatement(ArrayList<BankAccount> details) {
        System.out.println("Enter your account number");
        int accNum= scanner.nextInt();
        BankAccount user= checkingAccount.getExistedUser(accNum,details);
        if (user!=null){
                transactions=user.getTransactions();
                for(Transaction t: transactions){
                    System.out.println("==========================================");
                    System.out.println(t.toString());
                }
            }
        else{
            System.out.println("User do not exist");
        }

    }
}
