
class Account {
    private int accountNumber;
    private String accountHolderName;
    private double balance;
    private String email;
    private String phoneNumber;

    public Account(int accountNumber, String accountHolderName, double initialBalance, String email, String phoneNumber) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = initialBalance;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful! Your new balance is: " + balance);
        } else {
            System.out.println("Oops! Deposit amount must be positive.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0) {
            if (amount <= balance) {
                balance -= amount;
                System.out.println("Withdrawal successful! Your new balance is: " + balance);
            } else {
                System.out.println("Sorry, you don't have enough balance.");
            }
        } else {
            System.out.println("Oops! Withdrawal amount must be positive.");
        }
    }

    public void displayAccountDetails() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolderName);
        System.out.println("Current Balance: " + balance);
        System.out.println("Email: " + email);
        System.out.println("Phone Number: " + phoneNumber);
    }

    public void updateContactDetails(String newEmail, String newPhoneNumber) {
        this.email = newEmail;
        this.phoneNumber = newPhoneNumber;
        System.out.println("Contact details updated successfully!");
    }

    public int getAccountNumber() {
        return accountNumber;
    }
}

// Public UserInterface class (this must be public, and the file is named after it)
import java.util.Scanner;

public class UserInterface {
    private Account[] accounts = new Account;
    private int accountCount = 0;
    private Scanner scanner = new Scanner(System.in);
    private int nextAccountNumber = 1001;

    public void createAccount() {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter initial deposit amount (must be 0 or more): ");
        double initialBalance = scanner.nextDouble();
        scanner.nextLine();
        
        if (initialBalance < 0) {
            System.out.println("Initial deposit can't be negative. Try again.");
            return;
        }
        
        System.out.print("Enter your email address: ");
        String email = scanner.nextLine();
        
        System.out.print("Enter your phone number: ");
        String phoneNumber = scanner.nextLine();
        
        Account newAccount = new Account(nextAccountNumber, name, initialBalance, email, phoneNumber);
        accounts[accountCount] = newAccount;
        accountCount++;
        nextAccountNumber++;
        
        System.out.println("Great! Account created with Account Number: " + newAccount.getAccountNumber());
    }

    public void performDeposit() {
        System.out.print("Enter account number: ");
        int accNum = scanner.nextInt();
        scanner.nextLine();
        
        Account account = findAccount(accNum);
        if (account != null) {
            System.out.print("Enter amount to deposit: ");
            double amount = scanner.nextDouble();
            scanner.nextLine();
            account.deposit(amount);
        } else {
            System.out.println("Account not found. Please check the number.");
        }
    }

    public void performWithdrawal() {
        System.out.print("Enter account number: ");
        int accNum = scanner.nextInt();
        scanner.nextLine();
        
        Account account = findAccount(accNum);
        if (account != null) {
            System.out.print("Enter amount to withdraw: ");
            double amount = scanner.nextDouble();
            scanner.nextLine();
            account.withdraw(amount);
        } else {
            System.out.println("Account not found. Please check the number.");
        }
    }

    public void showAccountDetails() {
        System.out.print("Enter account number: ");
        int accNum = scanner.nextInt();
        scanner.nextLine();
        
        Account account = findAccount(accNum);
        if (account != null) {
            account.displayAccountDetails();
        } else {
            System.out.println("Account not found. Please check the number.");
        }
    }

    public void updateContact() {
        System.out.print("Enter account number: ");
        int accNum = scanner.nextInt();
        scanner.nextLine();
        
        Account account = findAccount(accNum);
        if (account != null) {
            System.out.print("Enter new email address: ");
            String newEmail = scanner.nextLine();
            
            System.out.print("Enter new phone number: ");
            String newPhone = scanner.nextLine();
            
            account.updateContactDetails(newEmail, newPhone);
        } else {
            System.out.println("Account not found. Please check the number.");
        }
    }

    private Account findAccount(int accNum) {
        for (int i = 0; i < accountCount; i++) {
            if (accounts[i].getAccountNumber() == accNum) {
                return accounts[i];
            }
        }
        return null;
    }

    public void mainMenu() {
        boolean exit = false;
        while (!exit) {
            System.out.println("\nWelcome to the Simple Banking App!");
            System.out.println("1. Create a new account");
            System.out.println("2. Deposit money");
            System.out.println("3. Withdraw money");
            System.out.println("4. View account details");
            System.out.println("5. Update contact details");
            System.out.println("6. Exit");
            System.out.print("What would you like to do? Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    performDeposit();
                    break;
                case 3:
                    performWithdrawal();
                    break;
                case 4:
                    showAccountDetails();
                    break;
                case 5:
                    updateContact();
                    break;
                case 6:
                    exit = true;
                    System.out.println("Thanks for using the app! Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please pick a number from 1 to 6.");
            }
        }
    }

    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        ui.mainMenu();
    }
}
