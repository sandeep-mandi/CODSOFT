class BankAccount {

    double balance = 1000;

    public void deposit(double depositAmount) {
        balance = balance + depositAmount;
    }

    public boolean withdraw(double withdrawAmount) {
        if (withdrawAmount > balance) {
            return false;
        }

        balance = balance - withdrawAmount;
        return true;
    }

    public double checkBalance() {
        return balance;
    }
}