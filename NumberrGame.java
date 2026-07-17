import java.util.Random;
import javax.swing.*;
import java.awt.event.*;

public class NumberrGame extends JFrame implements ActionListener {

    JTextField inputField;
    JButton guessButton, playAgainButton;
    JLabel titleLabel, attemptsLabel, resultLabel;

    Random randomGenerator = new Random();

    int currentScore = 0;
    int randomNumber;
    int attemptCount = 0;
    int maximumAttempts = 5;
    boolean gameWon = false;

    public NumberrGame() {

        setTitle("NUMBER GUESSING GAME");
        setSize(400, 300);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        titleLabel = new JLabel("Guess the number between 1 and 100");
        titleLabel.setBounds(70, 30, 250, 30);
        add(titleLabel);

        attemptsLabel = new JLabel("You have " + maximumAttempts + " attempts.");
        attemptsLabel.setBounds(110, 60, 200, 30);
        add(attemptsLabel);

        inputField = new JTextField();
        inputField.setBounds(120, 100, 150, 30);
        add(inputField);

        guessButton = new JButton("Guess");
        guessButton.setBounds(140, 140, 100, 30);
        guessButton.addActionListener(this);
        add(guessButton);

        resultLabel = new JLabel("");
        resultLabel.setBounds(50, 180, 300, 30);
        add(resultLabel);

        playAgainButton = new JButton("Play Again");
        playAgainButton.setBounds(120, 220, 140, 30);
        playAgainButton.addActionListener(this);
        playAgainButton.setVisible(false);
        add(playAgainButton);

        randomNumber = randomGenerator.nextInt(100) + 1;

        setVisible(true);
    }

    public void actionPerformed(ActionEvent event) {

        if (event.getSource() == guessButton) {

            if (inputField.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Please enter a number");
                return;
            }

            int guessedNumber;

            try {
                guessedNumber = Integer.parseInt(inputField.getText());
            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(this, "Please enter numbers only");
                inputField.setText("");
                return;
            }

            if (guessedNumber < 1 || guessedNumber > 100) {
                JOptionPane.showMessageDialog(this, "Enter number between 1 and 100");
                inputField.setText("");
                return;
            }

            attemptCount++;

            if (guessedNumber == randomNumber) {

                resultLabel.setText("Correct! You guessed the number.");
                currentScore++;
                gameWon = true;

                guessButton.setEnabled(false);
                playAgainButton.setVisible(true);

                JOptionPane.showMessageDialog(this,
                        "Attempts used: " + attemptCount +
                        "\nCurrent Score: " + currentScore);

            } else if (guessedNumber > randomNumber) {

                resultLabel.setText("Too high!");

            } else {

                resultLabel.setText("Too low!");
            }

            if (!gameWon) {

                int remainingAttempts = maximumAttempts - attemptCount;

                attemptsLabel.setText("Remaining attempts: " + remainingAttempts);

                if (attemptCount >= maximumAttempts) {

                    resultLabel.setText("You lost! Number was: " + randomNumber);

                    guessButton.setEnabled(false);
                    playAgainButton.setVisible(true);

                    JOptionPane.showMessageDialog(this, "Game Over!");
                }
            }

            inputField.setText("");
        }

        if (event.getSource() == playAgainButton) {

            randomNumber = randomGenerator.nextInt(100) + 1;
            attemptCount = 0;
            gameWon = false;

            attemptsLabel.setText("You have " + maximumAttempts + " attempts.");
            resultLabel.setText("");
            inputField.setText("");

            guessButton.setEnabled(true);
            playAgainButton.setVisible(false);
        }
    }

    public static void main(String[] args) {
        new NumberrGame();
    }
}