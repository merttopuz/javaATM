import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;

public class DepositMoney extends Account {
    public void content(){
        System.out.println("Deposit Money");
    }
    public static void window(Customer customer){
        // Header
        JLabel headerImg = new JLabel(new ImageIcon("assets/images/icons/DepositMoney.png"));
        headerImg.setBounds(220,94,36,36);
        account.add(headerImg);

        JLabel subtitle = new JLabel("Deposit Money");
        subtitle.setBounds(200,80,824,64);
        subtitle.setBackground(secondColor);
        subtitle.setForeground(Color.WHITE);
        subtitle.setFont(new Font("Calibri", Font.BOLD,18));
        subtitle.setBorder(new EmptyBorder(0,70,0,20));
        subtitle.setOpaque(true);
        account.add(subtitle);

        JLabel question = new JLabel("How Much Money Will You Deposit to Your Account?");
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

        JTextField depositMoney = new JTextField();
        depositMoney.setBounds(400,250,424,48);
        depositMoney.setBackground(secondColor);
        depositMoney.setForeground(Color.WHITE);
        depositMoney.setBorder(new EmptyBorder(0,0,0,0));
        depositMoney.setHorizontalAlignment(SwingConstants.CENTER);
        depositMoney.setFont(new Font("Calibri",Font.BOLD,24));
        account.add(depositMoney);

        JButton depositMoneyButton = new JButton("Deposit The Money");
        depositMoneyButton.setBounds(500, 310,224,48);
        depositMoneyButton.setBackground(bankColor);
        depositMoneyButton.setForeground(Color.WHITE);
        depositMoneyButton.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        depositMoneyButton.setFocusable(false);
        depositMoneyButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if(!isInt(depositMoney.getText())){
                    JOptionPane.showMessageDialog(account, "You must use only numeric variables.\nPlease check.", "Error!",  JOptionPane.ERROR_MESSAGE);
                    depositMoney.setText("");
                } else if (Double.parseDouble(depositMoney.getText()) <= 0) {
                    JOptionPane.showMessageDialog(account, "You can not get $0 or less.\nPlease try again.", "Error!",  JOptionPane.ERROR_MESSAGE);
                    depositMoney.setText("");
                } else {
                    double deposit = Double.parseDouble(depositMoney.getText());

                    Customer refreshCustomer = null;
                    int tempCardNo = customer.getCardNo();

                    try {
                        customer.updateMoney(deposit,1);
                        tempCardNo = customer.getCardNo();
                    } catch (java.io.IOException e1) {
                        e1.printStackTrace();
                    }

                    try {
                        refreshCustomer = new Customer(tempCardNo);
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    }

                    JOptionPane.showMessageDialog(account, "Your money is deposited.", "Successful!",  JOptionPane.INFORMATION_MESSAGE);

                    account.getContentPane().removeAll();
                    account.repaint();
                    account.add(heading);
                    ATMmenu menu = new ATMmenu();
                    menu.window(refreshCustomer);
                    window(refreshCustomer);

                }
            }
        });
        account.add(depositMoneyButton);

        JLabel depositMoneyIcon = new JLabel(new ImageIcon("assets/images/depositmoney_icon.png"));
        depositMoneyIcon.setBounds(415,375,404,389);
        account.add(depositMoneyIcon);

    }
}