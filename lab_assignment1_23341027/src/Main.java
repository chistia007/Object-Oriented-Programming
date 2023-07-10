import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<BankAccount> details;
    private static Scanner scanner;
    static SavingsAccount savingsAccount;
    private static CheckingAccount checkingAccount;
    public static void main(String[] args) {
        details= new ArrayList<>();
        savingsAccount=SavingsAccount.getInstance();
        initializer();
    }
    private static void initializer(){
        System.out.println();
        System.out.println("1. Create a new bank account.\n" +
                "2. Deposit money into an existing account.\n" +
                "3. Withdraw money from an existing account.\n" +
                "4. Check the balance of an existing account.\n" +
                "5. Print a statement of all transactions for a specific account.\n"+
                "6. Exit");

        scanner=new Scanner(System.in);
        System.out.println("Enter : ");
        int choice= scanner.nextInt();
        switch(choice){
            case 1:
                savingsAccount.banner();
                int accntType= scanner.nextInt();
                if (accntType==1||accntType==2){
                    System.out.println("Enter your new account number");
                    System.out.println("Or enter 0 to go Home");
                    int accountNumber= scanner.nextInt();
                    if(accountNumber==0){
                        initializer();
                    }
                    System.out.println("Enter your full name");
                    scanner.nextLine();
                    String accountName= scanner.nextLine();
                    if(accountName.isEmpty() ){
                        System.out.println("You can not leave any filed empty");
                        initializer();
                    }
                    BankAccount b= savingsAccount.createAccount(accountNumber,accountName);
                    details.add(b);
                    initializer();
                }
                else{
                    initializer();
                }
                break;


            case 2:
                System.out.println("Depositing money.....");
                System.out.println("Enter account number :");
                System.out.println("Or enter 0 to go Home");
                int accountNum= scanner.nextInt();
                if(accountNum==0){
                    initializer();
                }
                checkingAccount=CheckingAccount.getInstance();
                BankAccount userExistence=checkingAccount.getExistedUser(accountNum,details);
                if(userExistence!=null){
                    System.out.println("Enter amount :");
                    double amount= scanner.nextDouble();
                    double new_balance= savingsAccount.deposit(amount,userExistence);
                    initializer();
                }
                else{
                    System.out.println("This account do not exist");
                    initializer();
                }

                break;


            case 3:
                System.out.println("Withdrawing money.....");
                System.out.println("Enter account number :");
                System.out.println("Or enter 0 to go Home");
                accountNum= scanner.nextInt();
                if(accountNum==0){
                    initializer();
                }
                checkingAccount=CheckingAccount.getInstance();
                userExistence=checkingAccount.getExistedUser(accountNum,details);
                if(userExistence!=null){
                    System.out.println("Enter amount :");
                    double amount= scanner.nextDouble();
                    savingsAccount.withdraw(amount,userExistence);
                    initializer();
                }
                else{
                    System.out.println("This account do not exist");
                    initializer();
                }
                break;


            case 4:
                System.out.println("Checking Balance.........");
                System.out.println("Enter your account number");
                System.out.println("Or enter 0 to go Home");
                scanner.nextLine();
                int accntNum= scanner.nextInt();
                if(accntNum==0){
                    initializer();
                }
                checkingAccount=CheckingAccount.getInstance();
                checkingAccount.balanceOfExistedUser(accntNum,details);
                initializer();
                break;


            case 5:
                savingsAccount.printStatement(details);
                initializer();
                break;
            default:
                System.out.println("Thank you for being with us!");
                break;
        }
    }
}
