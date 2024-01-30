import java.util.ArrayList;
import java.util.Scanner;



public class BankingApplication {
    private static ArrayList<BankAccount> accounts = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            displayMenu();
            System.out.print("Enter your choice (1-8): ");
            choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    displayAllAccounts();
                    break;
                case 3:
                    updateAccount();
                    break;
                case 4:
                    deleteAccount();
                    break;
                case 5:
                    deposit();
                    break;
                case 6:
                    withdraw();
                    break;
                case 7:
                    searchAccount();
                    break;
                case 8:
                    System.out.println("Exiting the application. Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 8.");
            }
        } while (choice != 8);
    }

    private static void displayMenu() {
        System.out.println("\n1. Create a new account");
        System.out.println("2. Display all accounts");
        System.out.println("3. Update an account");
        System.out.println("4. Delete an account");
        System.out.println("5. Deposit an amount into your account");
        System.out.println("6. Withdraw an amount from your account");
        System.out.println("7. Search for an account");
        System.out.println("8. Exit");
    }

    private static void createAccount() {
        System.out.print("Enter account holder's name: ");
        String name = scanner.nextLine();

        System.out.print("Enter account type (e.g., saving, current, salary): ");
        String type = scanner.nextLine();

        double initialBalance;
        do {
            System.out.print("Enter initial balance: ");
            initialBalance = scanner.nextDouble();
            if (initialBalance < BankAccount.getMinOpenBalance(type)) {
                System.out.println("Initial balance should be at least $" + BankAccount.getMinOpenBalance(type));
            }
        } while (initialBalance < BankAccount.getMinOpenBalance(type));

        BankAccount newAccount = new BankAccount(name, generateAccountNumber(), type, initialBalance);
        accounts.add(newAccount);

        System.out.println("Account created successfully!");
    }

    private static void displayAllAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts available.");
        } else {
            System.out.println("List of all accounts:");
            for (BankAccount account : accounts) {
                System.out.println(account);
            }
        }
    }

    private static void updateAccount() {
        System.out.print("Enter account number to update: ");
        String accountNumber = scanner.nextLine();

        BankAccount accountToUpdate = findAccountByNumber(accountNumber);

        if (accountToUpdate != null) {
            double newBalance;
            do {
                System.out.print("Enter new balance: ");
                newBalance = scanner.nextDouble();
                if (newBalance < BankAccount.getMinWithdrawalBalance(accountToUpdate.getAccountType())) {
                    System.out.println("New balance should be at least $" + BankAccount.getMinWithdrawalBalance(accountToUpdate.getAccountType()));
                }
            } while (newBalance < BankAccount.getMinWithdrawalBalance(accountToUpdate.getAccountType()));

            accountToUpdate.setBalance(newBalance);
            System.out.println("Account updated successfully!");
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void deleteAccount() {
        System.out.print("Enter account number to delete: ");
        String accountNumber = scanner.nextLine();

        BankAccount accountToDelete = findAccountByNumber(accountNumber);

        if (accountToDelete != null) {
            accounts.remove(accountToDelete);
            System.out.println("Account deleted successfully!");
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void deposit() {
        System.out.print("Enter account number to deposit into: ");
        String accountNumber = scanner.nextLine();

        BankAccount accountToDepositInto = findAccountByNumber(accountNumber);

        if (accountToDepositInto != null) {
            System.out.print("Enter deposit amount: ");
            double depositAmount = scanner.nextDouble();
            accountToDepositInto.setBalance(accountToDepositInto.getBalance() + depositAmount);
            System.out.println("Deposit successful. New balance: $" + accountToDepositInto.getBalance());
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void withdraw() {
        System.out.print("Enter account number to withdraw from: ");
        String accountNumber = scanner.nextLine();

        BankAccount accountToWithdrawFrom = findAccountByNumber(accountNumber);

        if (accountToWithdrawFrom != null) {
            double withdrawalAmount;
            do {
                System.out.print("Enter withdrawal amount: ");
                withdrawalAmount = scanner.nextDouble();
                if (withdrawalAmount < BankAccount.getMinWithdrawalBalance(accountToWithdrawFrom.getAccountType())) {
                    System.out.println("Withdrawal amount should be at least $" + BankAccount.getMinWithdrawalBalance(accountToWithdrawFrom.getAccountType()));
                }
            } while (withdrawalAmount < BankAccount.getMinWithdrawalBalance(accountToWithdrawFrom.getAccountType()));

            if (accountToWithdrawFrom.getBalance() - withdrawalAmount >= BankAccount.getMinWithdrawalBalance(accountToWithdrawFrom.getAccountType())) {
                accountToWithdrawFrom.setBalance(accountToWithdrawFrom.getBalance() - withdrawalAmount);
                System.out.println("Withdrawal successful. New balance: $" + accountToWithdrawFrom.getBalance());
            } else {
                System.out.println("Insufficient funds for withdrawal.");
            }
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void searchAccount() {
        System.out.print("Enter account number to search: ");
        String accountNumber = scanner.nextLine();

        BankAccount searchedAccount = findAccountByNumber(accountNumber);

        if (searchedAccount != null) {
            System.out.println("Account details: " + searchedAccount);
        } else {
            System.out.println("Account not found.");
        }
    }

    private static BankAccount findAccountByNumber(String accountNumber) {
        for (BankAccount account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    private static String generateAccountNumber() {
        return "ACC" + (1000 + accounts.size());
    }
}
