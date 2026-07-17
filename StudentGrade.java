import javax.swing.*;
import java.awt.event.*;

public class StudentGrade extends JFrame implements ActionListener {

    JLabel javaLabel, dsaLabel, dbmsLabel, osLabel, cnLabel;
    JLabel javaResult, dsaResult, dbmsResult, osResult, cnResult;
    JLabel totalResult, averageResult, overallResult, finalResult;

    JTextField javaField, dsaField, dbmsField, osField, cnField;

    JButton calculateButton, resetButton;

    public StudentGrade() {

        setTitle("STUDENT GRADE CALCULATOR");
        setSize(600, 650);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        javaLabel = new JLabel("JAVA");
        javaLabel.setBounds(100, 50, 100, 30);
        add(javaLabel);

        javaField = new JTextField();
        javaField.setBounds(250, 50, 120, 30);
        add(javaField);

        dsaLabel = new JLabel("DSA");
        dsaLabel.setBounds(100, 100, 100, 30);
        add(dsaLabel);

        dsaField = new JTextField();
        dsaField.setBounds(250, 100, 120, 30);
        add(dsaField);

        dbmsLabel = new JLabel("DBMS");
        dbmsLabel.setBounds(100, 150, 100, 30);
        add(dbmsLabel);

        dbmsField = new JTextField();
        dbmsField.setBounds(250, 150, 120, 30);
        add(dbmsField);

        osLabel = new JLabel("OS");
        osLabel.setBounds(100, 200, 100, 30);
        add(osLabel);

        osField = new JTextField();
        osField.setBounds(250, 200, 120, 30);
        add(osField);

        cnLabel = new JLabel("CN");
        cnLabel.setBounds(100, 250, 100, 30);
        add(cnLabel);

        cnField = new JTextField();
        cnField.setBounds(250, 250, 120, 30);
        add(cnField);

        calculateButton = new JButton("CALCULATE");
        calculateButton.setBounds(100, 320, 150, 40);
        calculateButton.addActionListener(this);
        add(calculateButton);

        resetButton = new JButton("RESET");
        resetButton.setBounds(280, 320, 150, 40);
        resetButton.addActionListener(this);
        add(resetButton);

        javaResult = new JLabel("");
        javaResult.setBounds(100, 390, 400, 30);
        add(javaResult);

        dsaResult = new JLabel("");
        dsaResult.setBounds(100, 420, 400, 30);
        add(dsaResult);

        dbmsResult = new JLabel("");
        dbmsResult.setBounds(100, 450, 400, 30);
        add(dbmsResult);

        osResult = new JLabel("");
        osResult.setBounds(100, 480, 400, 30);
        add(osResult);

        cnResult = new JLabel("");
        cnResult.setBounds(100, 510, 400, 30);
        add(cnResult);

        totalResult = new JLabel("");
        totalResult.setBounds(100, 540, 400, 30);
        add(totalResult);

        averageResult = new JLabel("");
        averageResult.setBounds(100, 570, 400, 30);
        add(averageResult);

        overallResult = new JLabel("");
        overallResult.setBounds(100, 600, 400, 30);
        add(overallResult);

        finalResult = new JLabel("");
        finalResult.setBounds(100, 630, 400, 30);
        add(finalResult);

        setVisible(true);
    }

    public String findGrade(int marks) {

        if (marks >= 90) {
            return "O";
        } else if (marks >= 80) {
            return "A+";
        } else if (marks >= 70) {
            return "A";
        } else if (marks >= 60) {
            return "B+";
        } else if (marks >= 50) {
            return "B";
        } else if (marks >= 40) {
            return "C";
        } else if (marks >= 35) {
            return "PASS";
        } else {
            return "FAIL";
        }
    }

    public void actionPerformed(ActionEvent event) {

        if (event.getSource() == calculateButton) {

            try {

                if (javaField.getText().equals("") ||
                    dsaField.getText().equals("") ||
                    dbmsField.getText().equals("") ||
                    osField.getText().equals("") ||
                    cnField.getText().equals("")) {

                    JOptionPane.showMessageDialog(this, "All fields are required");
                    return;
                }

                int javaMarks = Integer.parseInt(javaField.getText());
                int dsaMarks = Integer.parseInt(dsaField.getText());
                int dbmsMarks = Integer.parseInt(dbmsField.getText());
                int osMarks = Integer.parseInt(osField.getText());
                int cnMarks = Integer.parseInt(cnField.getText());

                if (javaMarks < 0 || javaMarks > 100 ||
                    dsaMarks < 0 || dsaMarks > 100 ||
                    dbmsMarks < 0 || dbmsMarks > 100 ||
                    osMarks < 0 || osMarks > 100 ||
                    cnMarks < 0 || cnMarks > 100) {

                    JOptionPane.showMessageDialog(this, "Marks should be between 0 and 100");
                    return;
                }

                String javaGrade = findGrade(javaMarks);
                String dsaGrade = findGrade(dsaMarks);
                String dbmsGrade = findGrade(dbmsMarks);
                String osGrade = findGrade(osMarks);
                String cnGrade = findGrade(cnMarks);

                int totalMarks = javaMarks + dsaMarks + dbmsMarks + osMarks + cnMarks;
                double averageMarks = totalMarks / 5.0;

                String overallGrade;

                if (javaMarks < 35 || dsaMarks < 35 || dbmsMarks < 35 || osMarks < 35 || cnMarks < 35) {
                    overallGrade = "FAIL";
                } else {
                    overallGrade = findGrade((int) averageMarks);
                }

                javaResult.setText("JAVA Grade : " + javaGrade);
                dsaResult.setText("DSA Grade : " + dsaGrade);
                dbmsResult.setText("DBMS Grade : " + dbmsGrade);
                osResult.setText("OS Grade : " + osGrade);
                cnResult.setText("CN Grade : " + cnGrade);
                totalResult.setText("Total Marks : " + totalMarks);
                averageResult.setText("Average Percentage : " + averageMarks + "%");
                overallResult.setText("Overall Grade : " + overallGrade);

                if (overallGrade.equals("FAIL")) {
                    finalResult.setText("Result : FAIL");
                } else {
                    finalResult.setText("Result : PASS");
                }

            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(this, "Please enter numbers only");
            } catch (NullPointerException exception) {
                JOptionPane.showMessageDialog(this, "Null value found");
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(this, "Invalid Input");
            }
        }

        if (event.getSource() == resetButton) {

            javaField.setText("");
            dsaField.setText("");
            dbmsField.setText("");
            osField.setText("");
            cnField.setText("");

            javaResult.setText("");
            dsaResult.setText("");
            dbmsResult.setText("");
            osResult.setText("");
            cnResult.setText("");
            totalResult.setText("");
            averageResult.setText("");
            overallResult.setText("");
            finalResult.setText("");
        }
    }

    public static void main(String[] args) {
        new StudentGrade();
    }
}