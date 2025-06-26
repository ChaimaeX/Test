package test2.model;


public class User {
   
    private Long id;
    private double balance;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id);
    }

    //constructor
    public User(Long id,double balance){
        this.id = id;
        this.balance=balance;
    }


    //geters and seters 
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    } 
}
