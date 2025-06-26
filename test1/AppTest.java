import Exception.InsufficientFundsException;
import Model.Compte;
import Service.Account;

public class AppTest {
   
    public static void main(String[] args) {
        Compte compte = new Compte();
        Account account = new Account(compte);
        
        // Dépôts
        System.out.println("=== TEST DES DEPOTS ===");
        try {
            account.deposit(1000);
            account.deposit(500);
            account.deposit(-100);
        } catch (IllegalArgumentException e) {
            System.out.println("Erreur attendue: " + e.getMessage());
        }
        account.printStatement();
        
        // Retraits
        System.out.println("\n=== TEST DES RETRAITS ===");
        try {
            account.withdraw(200);
            account.withdraw(300);
            account.withdraw(2000);
        } catch (InsufficientFundsException | IllegalArgumentException e) {
            System.out.println("Erreur attendue: " + e.getMessage());
        }
        account.printStatement();
    }

}
