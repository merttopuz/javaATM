import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TransferMoney extends Account {
    public void content(){
        System.out.println("Transfer Money");
    }
    public static void window(Customer customer){
        // Header
        JLabel headerImg = new JLabel(new ImageIcon("assets/images/icons/TransferMoney.png"));
        headerImg.setBounds(220,94,36,36);
        account.add(headerImg);

        JLabel footerImg = new JLabel(new ImageIcon("assets/images/TransferMoneyFooter.png"));
        footerImg.setBounds(350,510,536,294);
        account.add(footerImg);

        JLabel subtitle = new JLabel("Transfer Money");
        subtitle.setBounds(200,80,824,64);
        subtitle.setBackground(secondColor);
        subtitle.setForeground(Color.WHITE);
        subtitle.setFont(new Font("Calibri", Font.BOLD,18));
        subtitle.setBorder(new EmptyBorder(0,70,0,20));
        subtitle.setOpaque(true);
        account.add(subtitle);

        JLabel question = new JLabel("How Much Money Do You Want to Send?");
        question.setBounds(200,180,824,36);
        question.setFont(new Font("Calibri", Font.PLAIN, 18));
        question.setHorizontalAlignment(SwingConstants.CENTER);
        account.add(question);

        Currency currency = new Currency();
        String currentBalance = currency.money(customer.getCurrentBalance(), "USD", true);
        JLabel questionInf = new JLabel("There's $"+currentBalance+" in your account at the moment.");
        questionInf.setBounds(200,205,824,36);
        questionInf.setFont(new Font("Calibri", Font.PLAIN, 14));
        questionInf.setHorizontalAlignment(SwingConstants.CENTER);
        account.add(questionInf);

        JTextField sendMoney = new JTextField();
        sendMoney.setBounds(400,250,424,48);
        sendMoney.setBackground(secondColor);
        sendMoney.setForeground(Color.WHITE);
        sendMoney.setBorder(new EmptyBorder(0,0,0,0));
        sendMoney.setHorizontalAlignment(SwingConstants.CENTER);
        sendMoney.setFont(new Font("Calibri",Font.BOLD,24));
        account.add(sendMoney);

        JLabel question2 = new JLabel("Which Account Number Do You Want to Send?");
        question2.setBounds(200,320,824,36);
        question2.setFont(new Font("Calibri", Font.PLAIN, 18));
        question2.setHorizontalAlignment(SwingConstants.CENTER);
        account.add(question2);

        JLabel warning = new JLabel("Please enter the correct account number.");
        warning.setBounds(200,345,824,36);
        warning.setFont(new Font("Calibri", Font.PLAIN, 14));
        warning.setHorizontalAlignment(SwingConstants.CENTER);
        account.add(warning);

        JTextField accountNumber = new JTextField();
        accountNumber.setBounds(400,390,424,48);
        accountNumber.setBackground(secondColor);
        accountNumber.setForeground(Color.WHITE);
        accountNumber.setBorder(new EmptyBorder(0,0,0,0));
        accountNumber.setHorizontalAlignment(SwingConstants.CENTER);
        accountNumber.setFont(new Font("Calibri",Font.BOLD,24));
        account.add(accountNumber);

        JButton transferMoneyButton = new JButton("Transfer The Money");
        transferMoneyButton.setBounds(500, 460,224,48);
        transferMoneyButton.setBackground(bankColor);
        transferMoneyButton.setForeground(Color.WHITE);
        transferMoneyButton.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        transferMoneyButton.setFocusable(false);
        transferMoneyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Customer c = new Customer();
                if (isInt(sendMoney.getText())){
                    if (isInt(accountNumber.getText())) {

                        Double sendM = Double.parseDouble(sendMoney.getText());
                        int accountA = Integer.parseInt(accountNumber.getText());

                        if (sendM <= customer.getCurrentBalance()) {
                            try {
                                if (c.search(accountA)) {

                                    if (c.getCardNo() != customer.getCardNo()) {

                                        customer.transferMoney(sendM,c.getCardNo());

                                        int tempCardNo = customer.getCardNo();
                                        Customer refreshCustomer = null;
                                        try {
                                            refreshCustomer = new Customer(tempCardNo);
                                        } catch (FileNotFoundException e1) {
                                            e1.printStackTrace();
                                        }
                                        Customer customerTO = new Customer(accountA);
                                        JOptionPane.showMessageDialog(account, "The money was successfully sent to "+customerTO.getName()+" ("+accountA+").", "Success", JOptionPane.INFORMATION_MESSAGE);
                                        accountNumber.setText("");
                                        account.getContentPane().removeAll();
                                        account.repaint();
                                        account.add(heading);
                                        ATMmenu menu = new ATMmenu();
                                        menu.window(refreshCustomer);
                                        window(refreshCustomer);
                                    } else {
                                        JOptionPane.showMessageDialog(account, "You can not send money on your own account.\nPlease check.", "Error!", JOptionPane.ERROR_MESSAGE);
                                        accountNumber.setText("");
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(account, "Any person is not the found for this account number.\nPlease check.", "Error!",  JOptionPane.ERROR_MESSAGE);
                                    accountNumber.setText("");
                                }
                            } catch (FileNotFoundException e1) {
                                e1.printStackTrace();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        } else {
                            JOptionPane.showMessageDialog(account, "There is not that much money in your account.\nPlease check.", "Error!",  JOptionPane.ERROR_MESSAGE);
                            sendMoney.setText("");
                        }
                    } else {
                        JOptionPane.showMessageDialog(account, "You must use only numeric variables for account number field.\nPlease check.", "Error!",  JOptionPane.ERROR_MESSAGE);
                        accountNumber.setText("");
                    }
                } else {
                    JOptionPane.showMessageDialog(account, "You must use only numeric variables for send money field.\nPlease check.", "Error!",  JOptionPane.ERROR_MESSAGE);
                    sendMoney.setText("");
                }

            }
        });
        account.add(transferMoneyButton);
    }
}