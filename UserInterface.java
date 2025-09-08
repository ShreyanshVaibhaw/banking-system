import java.util.Scanner;

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
            System.out.println("Deposit successful. New balance: " + balance);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawal successful. New balance: " + balance);
        } else if (amount > balance) {
            System.out.println("Insufficient balance.");
        } else {
            System.out.println("Withdrawal amount must be positive.");
        }
    }

    public void displayAccountDetails() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolderName);
        System.out.println("Balance: " + balance);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phoneNumber);
    }

    public void updateContactDetails(String email, String phoneNumber) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        System.out.println("Contact details updated.");
    }

    public int getAccountNumber() {
        return accountNumber;
    }
}

public class UserInterface {
    private Account[] accounts = new Account[100];
    private int accountCount = 0;
    private int nextAccountNumber = 1001;
    private Scanner scanner = new Scanner(System.in);

    public void createAccount() {
        System.out.print("Enter account holder name: ");
        String name = scanner.nextLine();

        System.out.print("Enter initial deposit: ");
        double initialBalance = Double.parseDouble(scanner.nextLine());

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        System.out.print("Enter phone number: ");
        String phone = scanner.nextLine();

        accounts[accountCount] = new Account(nextAccountNumber, name, initialBalance, email, phone);
        System.out.println("Account created successfully with Account Number: " + nextAccountNumber);
        accountCount++;
        nextAccountNumber++;
    }

    public void performDeposit() {
        System.out.print("Enter account number: ");
        int accNum = Integer.parseInt(scanner.nextLine());

        Account acc = findAccount(accNum);
        if (acc != null) {
            System.out.print("Enter deposit amount: ");
            double amount = Double.parseDouble(scanner.nextLine());
            acc.deposit(amount);
        } else {
            System.out.println("Account not found.");
        }
    }

    public void performWithdrawal() {
        System.out.print("Enter account number: ");
        int accNum = Integer.parseInt(scanner.nextLine());

        Account acc = findAccount(accNum);
        if (acc != null) {
            System.out.print("Enter withdrawal amount: ");
            double amount = Double.parseDouble(scanner.nextLine());
            acc.withdraw(amount);
        } else {
            System.out.println("Account not found.");
        }
    }

    public void showAccountDetails() {
        System.out.print("Enter account number: ");
        int accNum = Integer.parseInt(scanner.nextLine());

        Account acc = findAccount(accNum);
        if (acc != null) {
            acc.displayAccountDetails();
        } else {
            System.out.println("Account not found.");
        }
    }

    public void updateContact() {
        System.out.print("Enter account number: ");
        int accNum = Integer.parseInt(scanner.nextLine());

        Account acc = findAccount(accNum);
        if (acc != null) {
            System.out.print("Enter new email: ");
            String newEmail = scanner.nextLine();

            System.out.print("Enter new phone number: ");
            String newPhone = scanner.nextLine();

            acc.updateContactDetails(newEmail, newPhone);
        } else {
            System.out.println("Account not found.");
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
            System.out.println("\n--- Banking Application ---");
            System.out.println("1. Create a new account");
            System.out.println("2. Deposit money");
            System.out.println("3. Withdraw money");
            System.out.println("4. View account details");
            System.out.println("5. Update contact details");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice.");
                continue;
            }

            switch (choice) {
                case 1 -> createAccount();
                case 2 -> performDeposit();
                case 3 -> performWithdrawal();
                case 4 -> showAccountDetails();
                case 5 -> updateContact();
                case 6 -> {
                    exit = true;
                    System.out.println("Goodbye!");
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        ui.mainMenu();
    }
}
