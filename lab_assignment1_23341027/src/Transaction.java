import java.util.ArrayList;

public class Transaction {
    private String date;
    private String type;
    private Double amount;
    private  Double currentBalance;

    public Transaction(){

    }
    public Transaction(String date,String type, Double amount, Double currentBalance){
        this.date=date;
        this.type=type;
        this.amount=amount;
        this.currentBalance=currentBalance;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(Double currentBalance) {
        this.currentBalance = currentBalance;
    }


    @Override
    public String toString() {
        return  "Transaction date=" + date+"\n"
               + "Transaction type=" + type + "\n"
               + "Transaction amount=" + amount +"\n"
               + "Current Balance=" + currentBalance ;
    }
}
