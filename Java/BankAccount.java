import java.util.HashMap;
import java.util.Map;

public class BankAccount {
    private String name;
    private String number;
    private String type;
    private double balance;
    private static final Map<String, Double> MIN_OPEN_BALANCE = new HashMap<>();
    private static final Map<String, Double> MIN_WITHDRAWAL_BALANCE = new HashMap<>();

    static {
        // Minimum balance to open an account
        MIN_OPEN_BALANCE.put("saving", 50.0);
        MIN_OPEN_BALANCE.put("current", 60.0);
        MIN_OPEN_BALANCE.put("salary", 20.0);

        // Minimum balance before withdrawing an amount
        MIN_WITHDRAWAL_BALANCE.put("saving", 20.0);
        MIN_WITHDRAWAL_BALANCE.put("current", 10.0);
        MIN_WITHDRAWAL_BALANCE.put("salary", 20.0);
    }

    public BankAccount(String name, String number, String type, double initialBalance) {
        this.name = name;
        this.number = number;
        this.type = type;
        this.balance = initialBalance;
    }

    public String getAccountNumber() {
        return number;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountType() {
        return type;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Account Number: " + number + ", Type: " + type + ", Balance: $" + balance;
    }

    public static double getMinOpenBalance(String accountType) {
        return MIN_OPEN_BALANCE.getOrDefault(accountType, 0.0);
    }

    public static double getMinWithdrawalBalance(String accountType) {
        return MIN_WITHDRAWAL_BALANCE.getOrDefault(accountType, 0.0);
    }
}
