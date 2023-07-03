import java.util.Scanner;


class BankAccount {
    private double balance;

    public BankAccount(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        balance -= amount;
    }
}


class ATM {
    private BankAccount bankAccount;
    private Scanner scanner;

    public ATM(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Welcome to the ATM!");

        while (true) {
            System.out.println("Please select an option:");
            System.out.println("1. Withdraw");
            System.out.println("2. Deposit");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    withdraw();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    checkBalance();
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM!");
                    return;
                default:
                    System.out.println("Invalid option! Please try again.");
                    break;
            }
        }
    }

    private void withdraw() {
        System.out.println("Enter the amount to withdraw:");
        double amount = scanner.nextDouble();

        if (amount > 0 && amount <= bankAccount.getBalance()) {
            bankAccount.withdraw(amount);
            System.out.println("Amount withdrawn: " + amount);
        } else {
            System.out.println("Invalid amount or insufficient balance!");
        }
    }

    private void deposit() {
        System.out.println("Enter the amount to deposit:");
        double amount = scanner.nextDouble();

        if (amount > 0) {
            bankAccount.deposit(amount);
            System.out.println("Amount deposited: " + amount);
        } else {
            System.out.println("Invalid amount!");
        }
    }

    private void checkBalance() {
        double balance = bankAccount.getBalance();
        System.out.println("Your account balance: " + balance);
    }
}


public class task4_codsoft {
    public static void main(String[] args) {
        
        BankAccount bankAccount = new BankAccount(1000);

        
        ATM atm = new ATM(bankAccount);

        
        atm.start();
    }
}
