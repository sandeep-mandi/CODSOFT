import javax.swing.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;

public class CurrencyConverter extends JFrame implements ActionListener {

    JLabel amountLabel, fromLabel, toLabel, resultLabel;
    JTextField amountField;
    JComboBox<String> fromCurrencyBox, toCurrencyBox;
    JButton convertButton, clearButton;
    JTextArea resultArea;
    JScrollPane scrollPane;

    String currencies[] = {"USD", "INR", "EUR", "GBP", "JPY", "AUD", "CAD"};

    public CurrencyConverter() {

        setTitle("CURRENCY CONVERTER");
        setSize(600, 500);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        amountLabel = new JLabel("Enter Amount");
        amountLabel.setBounds(70, 40, 120, 30);
        add(amountLabel);

        amountField = new JTextField();
        amountField.setBounds(220, 40, 180, 30);
        add(amountField);

        fromLabel = new JLabel("From Currency");
        fromLabel.setBounds(70, 100, 120, 30);
        add(fromLabel);

        fromCurrencyBox = new JComboBox<String>(currencies);
        fromCurrencyBox.setBounds(220, 100, 180, 30);
        add(fromCurrencyBox);

        toLabel = new JLabel("To Currency");
        toLabel.setBounds(70, 160, 120, 30);
        add(toLabel);

        toCurrencyBox = new JComboBox<String>(currencies);
        toCurrencyBox.setBounds(220, 160, 180, 30);
        add(toCurrencyBox);

        convertButton = new JButton("CONVERT");
        convertButton.setBounds(120, 230, 140, 40);
        convertButton.addActionListener(this);
        add(convertButton);

        clearButton = new JButton("CLEAR");
        clearButton.setBounds(300, 230, 140, 40);
        clearButton.addActionListener(this);
        add(clearButton);

        resultLabel = new JLabel("Conversion Result");
        resultLabel.setBounds(200, 290, 200, 30);
        add(resultLabel);

        resultArea = new JTextArea();
        resultArea.setEditable(false);

        scrollPane = new JScrollPane(resultArea);
        scrollPane.setBounds(80, 330, 420, 100);
        add(scrollPane);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent event) {

        if (event.getSource() == convertButton) {

            try {

                if (amountField.getText().equals("")) {
                    JOptionPane.showMessageDialog(this, "Please enter amount");
                    return;
                }

                double amount = Double.parseDouble(amountField.getText());

                if (amount <= 0) {
                    JOptionPane.showMessageDialog(this, "Amount should be greater than 0");
                    amountField.setText("");
                    return;
                }

                if (amount > 1000000) {
                    JOptionPane.showMessageDialog(this, "Amount too large");
                    amountField.setText("");
                    return;
                }

                String fromCurrency = fromCurrencyBox.getSelectedItem().toString();
                String toCurrency = toCurrencyBox.getSelectedItem().toString();

                if (fromCurrency.equals(toCurrency)) {
                    JOptionPane.showMessageDialog(this, "Both currencies cannot be same");
                    return;
                }

                String apiLink = "https://api.frankfurter.app/latest?from=" + fromCurrency + "&to=" + toCurrency;

                URL apiURL = new URL(apiLink);
                HttpURLConnection connection = (HttpURLConnection) apiURL.openConnection();

                connection.setRequestMethod("GET");

                int responseCode = connection.getResponseCode();

                if (responseCode != 200) {
                    JOptionPane.showMessageDialog(this, "API Connection Failed");
                    return;
                }

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                String line;
                String responseData = "";

                while ((line = reader.readLine()) != null) {
                    responseData = responseData + line;
                }

                reader.close();

                int startIndex = responseData.indexOf(toCurrency);
                int colonIndex = responseData.indexOf(":", startIndex);
                int endIndex = responseData.indexOf("}", colonIndex);

                String rateString = responseData.substring(colonIndex + 1, endIndex);

                double exchangeRate = Double.parseDouble(rateString);
                double convertedAmount = amount * exchangeRate;

                resultArea.setText(
                        "From Currency : " + fromCurrency +
                        "\nTo Currency : " + toCurrency +
                        "\nExchange Rate : " + exchangeRate +
                        "\nEntered Amount : " + amount +
                        "\nConverted Amount : " + convertedAmount);

            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(this, "Please enter valid numbers only");
                amountField.setText("");
            } catch (MalformedURLException exception) {
                JOptionPane.showMessageDialog(this, "Invalid URL");
            } catch (UnknownHostException exception) {
                JOptionPane.showMessageDialog(this, "No Internet Connection");
            } catch (IOException exception) {
                JOptionPane.showMessageDialog(this, "Error while connecting to API");
            } catch (NullPointerException exception) {
                JOptionPane.showMessageDialog(this, "Null value found");
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(this, "Unexpected Error");
            }
        }

        if (event.getSource() == clearButton) {
            amountField.setText("");
            resultArea.setText("");
            fromCurrencyBox.setSelectedIndex(0);
            toCurrencyBox.setSelectedIndex(1);
        }
    }

    public static void main(String[] args) {
        new CurrencyConverter();
    }
}