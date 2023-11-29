// Import necessary Java Swing packages
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Definition of the BankAccount class
class BankAccount {
    private double balance;

    // Constructor to initialize the BankAccount with an initial balance
    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    // Getter method to retrieve the current balance
    public double getBalance() {
        return balance;
    }

    // Method to deposit funds into the account
    public void deposit(double amount) {
        balance += amount;
    }

    // Method to withdraw funds from the account, returns true if successful, false if insufficient funds
    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        } else {
            return false; // Insufficient funds
        }
    }
}

// Definition of the ATM class
class ATM {
    private BankAccount account;

    // Constructor to initialize the ATM with a BankAccount
    public ATM(BankAccount account) {
        this.account = account;
    }

    // Method to withdraw funds from the account, returns true if successful
    public boolean withdraw(double amount) {
        return account.withdraw(amount);
    }

    // Method to deposit funds into the account
    public void deposit(double amount) {
        account.deposit(amount);
    }

    // Method to check the current balance of the account
    public double checkBalance() {
        return account.getBalance();
    }
}

// Definition of the ATMGUI class
public class ATM_System {
    private ATM atm;

    // Constructor to initialize the ATMGUI with an ATM instance
    public ATM_System(ATM atm) {
        this.atm = atm;

        // Create a JFrame for the GUI
        JFrame frame = new JFrame("ATM Interface");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);

        // Create a JPanel with a GridLayout to organize the components
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));

        // Create buttons for Withdraw, Deposit, and Check Balance
        JButton withdrawButton = new JButton("Withdraw");
        JButton depositButton = new JButton("Deposit");
        JButton balanceButton = new JButton("Check Balance");

        // Create a JLabel to display results or messages
        JLabel resultLabel = new JLabel("");

        // Add buttons and resultLabel to the panel
        panel.add(withdrawButton);
        panel.add(depositButton);
        panel.add(balanceButton);
        panel.add(resultLabel);

        // ActionListener for Withdraw button
        withdrawButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Prompt the user for the amount to withdraw
                String amountString = JOptionPane.showInputDialog("Enter amount to withdraw:");
                try {
                    double amount = Double.parseDouble(amountString);

                    // Check if withdrawal amount is greater than available balance
                    if (amount > atm.checkBalance()) {
                        resultLabel.setText("Error: Withdrawal amount exceeds available balance.");
                    } else {
                        // Attempt to withdraw from the ATM and display the result
                        boolean success = atm.withdraw(amount);
                        if (success) {
                            resultLabel.setText("Withdrawal successful. New balance: " + atm.checkBalance());
                        } else {
                            resultLabel.setText("Insufficient funds. Current balance: " + atm.checkBalance());
                        }
                    }
                } catch (NumberFormatException ex) {
                    resultLabel.setText("Invalid input. Please enter a valid number.");
                }
            }
        });

        // ActionListener for Deposit button
        depositButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Prompt the user for the amount to deposit
                String amountString = JOptionPane.showInputDialog("Enter amount to deposit:");
                try {
                    double amount = Double.parseDouble(amountString);
                    // Deposit into the ATM and display the result
                    atm.deposit(amount);
                    resultLabel.setText("Deposit successful. New balance: " + atm.checkBalance());
                } catch (NumberFormatException ex) {
                    resultLabel.setText("Invalid input. Please enter a valid number.");
                }
            }
        });

        // ActionListener for Check Balance button
        balanceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Display the current balance
                resultLabel.setText("Current balance: " + atm.checkBalance());
            }
        });

        // Add the panel to the frame and make it visible
        frame.add(panel);
        frame.setVisible(true);
    }

    // Main method to create a BankAccount, ATM, and ATMGUI instance
    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000); // Initial balance of 1000
        ATM atm = new ATM(account);
        new ATM_System(atm);
    }
}
