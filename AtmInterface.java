import javax.swing.*;
import java.awt.event.*;

public class AtmInterface extends JFrame implements ActionListener {

    JLabel amountLabel, balanceLabel;
    JTextField amountField;
    JTextArea outputArea;
    JScrollPane scrollPane;
    JButton depositButton, withdrawButton, balanceButton, exitButton;
    BankAccount account;

    public AtmInterface() {

        account = new BankAccount();

        setTitle("ATM INTERFACE");
        setSize(550, 500);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        amountLabel = new JLabel("Enter Amount");
        amountLabel.setBounds(80, 40, 150, 30);
        add(amountLabel);

        amountField = new JTextField();
        amountField.setBounds(220, 40, 150, 30);
        add(amountField);

        depositButton = new JButton("DEPOSIT");
        depositButton.setBounds(30, 110, 120, 40);
        depositButton.addActionListener(this);
        add(depositButton);

        withdrawButton = new JButton("WITHDRAW");
        withdrawButton.setBounds(160, 110, 120, 40);
        withdrawButton.addActionListener(this);
        add(withdrawButton);

        balanceButton = new JButton("CHECK BALANCE");
        balanceButton.setBounds(290, 110, 160, 40);
        balanceButton.addActionListener(this);
        add(balanceButton);

        exitButton = new JButton("EXIT");
        exitButton.setBounds(180, 180, 120, 40);
        exitButton.addActionListener(this);
        add(exitButton);

        balanceLabel = new JLabel("Current Balance : Rs. " + account.checkBalance());
        balanceLabel.setBounds(140, 250, 250, 30);
        add(balanceLabel);

        outputArea = new JTextArea();
        outputArea.setEditable(false);

        scrollPane = new JScrollPane(outputArea);
        scrollPane.setBounds(70, 300, 380, 120);
        add(scrollPane);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent event) {

        if (event.getSource() == depositButton) {
            try {

                if (amountField.getText().equals("")) {
                    JOptionPane.showMessageDialog(this, "Please enter amount");
                    return;
                }

                double depositAmount = Double.parseDouble(amountField.getText());

                if (depositAmount <= 0) {
                    JOptionPane.showMessageDialog(this, "Amount should be greater than 0");
                    amountField.setText("");
                    return;
                }

                if (depositAmount > 100000) {
                    JOptionPane.showMessageDialog(this, "Amount limit exceeded");
                    amountField.setText("");
                    return;
                }

                account.deposit(depositAmount);

                balanceLabel.setText("Current Balance : Rs. " + account.checkBalance());
                outputArea.setText("Amount Deposited : Rs. " + depositAmount +
                        "\nUpdated Balance : Rs. " + account.checkBalance());

                amountField.setText("");

            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(this, "Please enter numbers only");
                amountField.setText("");
            } catch (NullPointerException exception) {
                JOptionPane.showMessageDialog(this, "Null value found");
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(this, "Invalid Input");
            }
        }

        if (event.getSource() == withdrawButton) {
            try {

                if (amountField.getText().equals("")) {
                    JOptionPane.showMessageDialog(this, "Please enter amount");
                    return;
                }

                double withdrawAmount = Double.parseDouble(amountField.getText());

                if (withdrawAmount <= 0) {
                    JOptionPane.showMessageDialog(this, "Amount should be greater than 0");
                    amountField.setText("");
                    return;
                }

                if (withdrawAmount > 100000) {
                    JOptionPane.showMessageDialog(this, "Amount limit exceeded");
                    amountField.setText("");
                    return;
                }

                boolean status = account.withdraw(withdrawAmount);

                if (!status) {
                    JOptionPane.showMessageDialog(this, "Insufficient Balance");
                    amountField.setText("");
                    return;
                }

                balanceLabel.setText("Current Balance : Rs. " + account.checkBalance());
                outputArea.setText("Amount Withdrawn : Rs. " + withdrawAmount +
                        "\nUpdated Balance : Rs. " + account.checkBalance());

                amountField.setText("");

            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(this, "Please enter numbers only");
                amountField.setText("");
            } catch (NullPointerException exception) {
                JOptionPane.showMessageDialog(this, "Null value found");
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(this, "Invalid Input");
            }
        }

        if (event.getSource() == balanceButton) {
            outputArea.setText("Available Balance : Rs. " + account.checkBalance());
        }

        if (event.getSource() == exitButton) {
            int choice = JOptionPane.showConfirmDialog(this, "Do you want to exit ?");

            if (choice == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }
    }

    public static void main(String[] args) {
        new AtmInterface();
    }
}