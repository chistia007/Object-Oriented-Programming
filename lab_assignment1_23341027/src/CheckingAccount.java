import java.util.ArrayList;

public class CheckingAccount extends BankAccount{
    private double overdraftLimit=0.0;
    private static CheckingAccount checkingAccount;
    //public static ArrayList<BankAccount> details;
    private CheckingAccount(){

    }
    public static CheckingAccount getInstance(){
        if (checkingAccount==null){
            checkingAccount=new CheckingAccount();
        }
        return checkingAccount;
    }
    public void balanceOfExistedUser(int accntNum,ArrayList<BankAccount> details) {
        boolean doesExist=false;
        for(BankAccount b:details){
            if(b.getAccountNumber()==accntNum){
                System.out.println("You current balance is "+ b.getBalance()+"/=");
                doesExist=true;
            }
        }
        if(!doesExist){
            System.out.println("Account do not exist");
        }

    }

    public BankAccount getExistedUser(int accountNum,ArrayList<BankAccount> details) {
        for(BankAccount b:details){
            if(b.getAccountNumber()==accountNum){
                return b;
            }
        }
        return null;
    }

    public Boolean overDraftLimit(Double withdrawAmount, BankAccount userExistence){
        return userExistence.getBalance()-withdrawAmount >= 0;
    }
}
