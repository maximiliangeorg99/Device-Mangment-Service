package c24.thriftshop.stripe.demo.domain;

public class Balance {
    int balance;

    public Balance(final int balance) {
        this.balance = balance;
    }

    public int getBalanceAsInt() {
        return balance;
    }

    public boolean isPositiveBalance() {
        return balance >= 0;
    }
}
