import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Login extends Main {
        public JFrame login = new JFrame("Account Your Bank Account");

        public void window() {
            login.getContentPane().setBackground(bankColor);

            // Logo
            JLabel imgLabel = new JLabel(new ImageIcon("assets/images/logo.png"));
            imgLabel.setBounds(center(257),221,257,85);
            login.add(imgLabel);

            // Account Window
            JPanel loginBG = new JPanel();
            loginBG.setBounds(center(426),347,426,142);
            loginBG.setOpaque(true);
            loginBG.setBackground(Color.WHITE);
            login.add(loginBG);

            // Account Number
            JTextField accountNumber = new JTextField("Your Account Number");
            accountNumber.setBounds(center(386),367,386,46);
            accountNumber.setBackground(bankColor);
            accountNumber.setForeground(Color.WHITE);
            accountNumber.setBorder(new EmptyBorder(10,10,10,10));
            accountNumber.setText("Your Account Number");
            login.add(accountNumber);

            accountNumber.addFocusListener(new FocusListener(){
                @Override
                public void focusGained(FocusEvent e){
                    accountNumber.setText("");
                }

                @Override
                public void focusLost(FocusEvent e) {

                }
            });

            // Password
            JPasswordField accountPassword = new JPasswordField("Your Password");
            accountPassword.setBounds(center(386),423,386,46);
            accountPassword.setBackground(bankColor);
            accountPassword.setForeground(Color.WHITE);
            accountPassword.setBorder(javax.swing.BorderFactory.createEmptyBorder(10,10,10,10));
            accountPassword.setText("Your Password");
            login.add(accountPassword);

            accountPassword.addFocusListener(new FocusListener(){
                @Override
                public void focusGained(FocusEvent e){
                    accountPassword.setText("");
                }

                @Override
                public void focusLost(FocusEvent e) {

                }
            });

            // Account Button
            JButton loginButton = new JButton("LOGIN");
            loginButton.setBounds(521, 499,212,46);
            loginButton.setBackground(new Color(183, 223,255));
            loginButton.setForeground(bankColor);
            loginButton.setFocusable(false);
            loginButton.setBorder(new EmptyBorder(0,0,0,0));
            login.add(loginButton);

            // Control
            loginButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        if (isCustomer(String.valueOf(accountNumber.getText()), String.valueOf(accountPassword.getPassword()))) {
                            login.dispatchEvent(new WindowEvent(login, WindowEvent.WINDOW_CLOSING));
                            Account account = new Account();
                            account.window(String.valueOf(accountNumber.getText()));
                        } else {
                            JOptionPane.showMessageDialog(login, "Your login information is incorrect.\nPlease check your login information.", "Your login information is incorrect.",  JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    }
                }
            });

            // Window Setting
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            login.setLocation(dim.width/2-windowWidth/2, dim.height/2-windowHeight/2);
            login.setSize(windowWidth,windowHeight);
            login.setResizable(false);
            login.setLayout(null);
            login.setVisible(true);
        }

        public static int center(int width) {
            return (windowWidth-width)/2;
        }

        private static boolean isCustomer(String cardNumber, String password) throws FileNotFoundException { // 63310, 123456
            boolean amIValid = false;
            try {
                int cardNo = Integer.parseInt(cardNumber);
                Scanner ba = new Scanner(new File("assets/datas/bank-accounts.txt"));
                while(ba.hasNextLine()) {
                    String line = ba.nextLine();
                    String[] informations = line.split(",");
                    if (cardNo == Integer.parseInt(informations[0])){
                        PasswordEncryption encryptPassword = new PasswordEncryption(password);
                        String ep = encryptPassword.getPassword();
                        if (ep.equals(informations[1])) {
                            return true;
                        }
                    }
                }
            } catch (NumberFormatException e){
                return false;
            }
            return false;
        }

}