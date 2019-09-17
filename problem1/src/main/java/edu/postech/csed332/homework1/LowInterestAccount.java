package edu.postech.csed332.homework1;

/**
 * An account with a low interest rate. The rate is 0.5% per day.
 * E.g., if the balance is initially 100, after 10 days (on the 11th day)
 * the balance will be 100*(1.005)^10.
 */
class LowInterestAccount implements Account {
    //TODO implement this
    int accNum;
    String ownerName;
    double balance;

    public LowInterestAccount(int accNum, String name, double initial){
        this.accNum = accNum;
        this.ownerName = name;
        this.balance = initial;
    }

    public int getAccountNumber() {
        //TODO implement this
        return this.accNum;
    }

    public double getBalance() {
        //TODO implement this
        return this.balance;
    }

    public String getOwner() {
        //TODO implement this
        return this.ownerName;
    }

    public void updateBalance(int elapsedDate) {
        //TODO implement this
        for(int i = 0; i < elapsedDate; i++){
            this.balance = this.balance * 1.005;
        }
    }

    public void deposit(double amount) {
        //TODO implement this
        this.balance += amount;
    }

    public void withdraw(double amount) throws IllegalOperationException {
        //TODO implement this
        if (this.balance - amount < 0) {
            throw new IllegalOperationException("There is not much money to withdraw");
        }
        this.balance -= amount;
    }
}
