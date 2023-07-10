import java.util.ArrayList;

public class BankAccount {
    private int accountNumber;
    private String accountName;
    private Double balance=0.0;
    private ArrayList<Transaction> transactions;
    public BankAccount() {

    }
    public BankAccount(int accountNumber,String accountName) {
            this.accountNumber=accountNumber;
            this.accountName=accountName;
            this.transactions=new ArrayList<>();
    }


    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public ArrayList<Transaction> getTransactions() {
        if (transactions==null){
            transactions=new ArrayList<>();
        }
        return transactions;
    }

    public void setTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }


}
