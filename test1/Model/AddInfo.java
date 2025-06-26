package Model;

import java.util.Date;


public class AddInfo {
    
    private Date Date ;
    private int Amount ;
    private int Balance ;

    public AddInfo(Date date, int amount, int balance) {
        this.Date = date;
        this.Amount = amount;
        this.Balance = balance;
    }

   
    public Date getDate() {
        return Date;
    }
    public void setDate(Date date) {
        Date = date;
    }
    public int getAmount() {
        return Amount;
    }
    public void setAmount(int amount) {
        Amount = amount;
    }
     public int getBalance() {
        return Balance;
    }
    public void setBalance(int balance) {
        Balance = balance;
    }


}
