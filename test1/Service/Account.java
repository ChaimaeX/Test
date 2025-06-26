package Service;
import java.text.SimpleDateFormat;
import java.util.Date;

import Model.AddInfo;
import Model.Compte;


public class Account implements AccountService {

    private final Compte compte;

    public Account(Compte compte) {
        this.compte = compte;
    }

    @Override
    public void deposit(int amount) {

       if (amount <= 0) {
        throw new IllegalArgumentException("Montant invalide: " + amount);
        }
       int currentBalance = compte.getCurrentBalance();
       int newBalance = currentBalance + amount;
    
       AddInfo transaction = new AddInfo(new Date(), amount, newBalance);
       compte.add(transaction);
       
        System.out.println("Montant ajoute avec succes");
        
    }

    @Override
    public void withdraw(int amount) {
        // Validation
        if (amount <= 0) throw new IllegalArgumentException("Montant invalide");
        
        int currentBalance = compte.getCurrentBalance();
        
        if (amount > currentBalance) {
           throw new IllegalArgumentException(
                "Solde insuffisant. Solde actuel: " +currentBalance
            );
        }
        
       
        AddInfo transaction = new AddInfo(
            new Date(),
            -amount,
            currentBalance - amount
        );
        
        
        compte.add(transaction);
    } 

    @Override
    public void printStatement() {

        System.err.println( " Date      || Amount || Balence");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        for (AddInfo transaction : compte.getInformations()) {
            
            String dateStr = sdf.format(transaction.getDate());
            System.out.printf("%-10s || %6d || %7d%n", 
                          dateStr, 
                          transaction.getAmount(), 
                          transaction.getBalance());
        }
        
    }
    
}
