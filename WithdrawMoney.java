import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;

public class WithdrawMoney extends Account {

    static JLabel[] bankNotes_x = {new JLabel(),new JLabel(),new JLabel(),new JLabel(),new JLabel(),new JLabel()};
    static JLabel[] bankNotesInf = {new JLabel(),new JLabel(),new JLabel(),new JLabel(),new JLabel(),new JLabel()};
    static JLabel[] bankNotes = {
            new JLabel(new ImageIcon("assets/images/bankNotes/100.png")),
            new JLabel(new ImageIcon("assets/images/bankNotes/50.png")),
            new JLabel(new ImageIcon("assets/images/bankNotes/20.png")),
            new JLabel(new ImageIcon("assets/images/bankNotes/10.png")),
            new JLabel(new ImageIcon("assets/images/bankNotes/5.png")),
            new JLabel(new ImageIcon("assets/images/bankNotes/1.png"))
    };
    static JLabel[] bankNotes2 = {
            new JLabel(new ImageIcon("assets/images/bankNotes/100_2.png")),
            new JLabel(new ImageIcon("assets/images/bankNotes/50_2.png")),
            new JLabel(new ImageIcon("assets/images/bankNotes/20_2.png")),
            new JLabel(new ImageIcon("assets/images/bankNotes/10_2.png")),
            new JLabel(new ImageIcon("assets/images/bankNotes/5_2.png")),
            new JLabel(new ImageIcon("assets/images/bankNotes/1_2.png"))
    };

    public static void window(Customer customer){
        // Header
        JLabel headerImg = new JLabel(new ImageIcon("assets/images/icons/WithdrawMoney.png"));
        headerImg.setBounds(220,94,36,36);
        account.add(headerImg);

        JLabel subtitle = new JLabel("Withdraw Money");
        subtitle.setBounds(200,80,824,64);
        subtitle.setBackground(secondColor);
        subtitle.setForeground(Color.WHITE);
        subtitle.setFont(new Font("Calibri", Font.BOLD,18));
        subtitle.setBorder(new EmptyBorder(0,70,0,20));
        subtitle.setOpaque(true);
        account.add(subtitle);

        JLabel question = new JLabel("How Much Money Do You Want?");
        question.setBounds(200,180,824,36);
        question.setFont(new Font("Calibri", Font.PLAIN, 18));
        question.setHorizontalAlignment(SwingConstants.CENTER);
        account.add(question);

        Currency currency = new Currency();
        String currentBalance = currency.money(customer.getCurrentBalance(), "USD", true);
        JLabel questionInf = new JLabel("You can receive a maximum of "+currentBalance);
        questionInf.setBounds(200,205,824,36);
        questionInf.setFont(new Font("Calibri", Font.PLAIN, 14));
        questionInf.setHorizontalAlignment(SwingConstants.CENTER);
        account.add(questionInf);

        JTextField money = new JTextField();
        money.setBounds(400,250,424,48);
        money.setBackground(secondColor);
        money.setForeground(Color.WHITE);
        money.setBorder(new EmptyBorder(0,0,0,0));
        money.setHorizontalAlignment(SwingConstants.CENTER);
        money.setFont(new Font("Calibri",Font.BOLD,24));
        account.add(money);

        banknote(0,0,-220,380);
        banknote(1,0,-220,380);
        banknote(2,0,-220,380);
        banknote(3,0,-220,380);
        banknote(4,0,-220,380);
        banknote(5,0,-220,380);
        for (int i = 0; i < 6; i++) {
            account.add(bankNotesInf[i]);
        }

        JButton withdrawMoneyButton = new JButton("Get The Money");
        withdrawMoneyButton.setBounds(500, 310,224,48);
        withdrawMoneyButton.setBackground(bankColor);
        withdrawMoneyButton.setForeground(Color.WHITE);
        withdrawMoneyButton.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        withdrawMoneyButton.setFocusable(false);
        withdrawMoneyButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if(!isInt(money.getText())){
                    JOptionPane.showMessageDialog(account, "You must use only numeric variables.\nPlease check.", "Error!",  JOptionPane.ERROR_MESSAGE);
                } else if (money.getText().isEmpty() || Double.parseDouble(money.getText()) > customer.getCurrentBalance()) {
                    JOptionPane.showMessageDialog(account, "There is not that much money in your account.\nPlease try again.", "You can not get money!",  JOptionPane.ERROR_MESSAGE);
                    money.setText("");
                } else if (Double.parseDouble(money.getText()) <= 0) {
                    JOptionPane.showMessageDialog(account, "You can not get $0 or less.\nPlease try again.", "Error!",  JOptionPane.ERROR_MESSAGE);
                    money.setText("");
                } else {
                    for (int i = 0; i < 6; i++) {
                        bankNotes[i].setBounds(-220,0,0,0);
                        bankNotes2[i].setBounds(-220,0,0,0);
                        bankNotes_x[i].setBounds(-220,0,0,0);
                        bankNotesInf[i].setBounds(-220,0,0,0);
                    }
                    Double getMoney = Double.parseDouble(money.getText());
                    int[] banknotes = {0,0,0,0,0,0}; // 1-5-10-20-50-100
                    double tempMoney = getMoney;
                    for (int i = 5; i >= 0; i--) {
                        int controlBanknot = 0;
                        if (i==5) {controlBanknot = 100;}
                        else if(i==4) {controlBanknot = 50;}
                        else if(i==3) {controlBanknot = 20;}
                        else if(i==2) {controlBanknot = 10;}
                        else if(i==1) {controlBanknot = 5;}
                        else if(i==0) {controlBanknot = 1;}

                        int temp = (int) Math.floor(tempMoney/controlBanknot);
                        banknotes[i] = temp;
                        tempMoney -= temp*controlBanknot;
                    }
                    if (banknotes[5] > 0) {
                        bankNotes[0].setBounds(220,380,238,100);
                        bankNotes_x[0].setBounds(410,390,36,36);
                        bankNotes_x[0].setText("x"+banknotes[5]);
                    } else {
                        bankNotes2[0].setBounds(220,380,238,100);
                    }
                    if (banknotes[4] > 0) {
                        bankNotes[1].setBounds(220,490,238,100);
                        bankNotes_x[1].setBounds(410,500,36,36);
                        bankNotes_x[1].setText("x"+banknotes[4]);
                    } else {
                        bankNotes2[1].setBounds(220,490,238,100);
                    }
                    if (banknotes[3] > 0) {
                        bankNotes[2].setBounds(220,600,238,100);
                        bankNotes_x[2].setBounds(410,610,36,36);
                        bankNotes_x[2].setText("x"+banknotes[3]);
                    } else {
                        bankNotes2[2].setBounds(220,600,238,100);
                    }
                    if (banknotes[2] > 0) {
                        bankNotes[3].setBounds(468,380,238,100);
                        bankNotes_x[3].setBounds(658,390,36,36);
                        bankNotes_x[3].setText("x"+banknotes[2]);
                    } else {
                        bankNotes2[3].setBounds(468,380,238,100);
                    }
                    if (banknotes[1] > 0) {
                        bankNotes[4].setBounds(468,490,238,100);
                        bankNotes_x[4].setBounds(658,500,36,36);
                        bankNotes_x[4].setText("x"+banknotes[1]);
                    } else {
                        bankNotes2[4].setBounds(468,490,238,100);
                    }
                    if (banknotes[0] > 0) {
                        bankNotes[5].setBounds(468,600,238,100);
                        bankNotes_x[5].setBounds(658,610,36,36);
                        bankNotes_x[5].setText("x"+banknotes[0]);
                    } else {
                        bankNotes2[5].setBounds(468,600,238,100);
                    }

                    int order = 0;
                    for (int i = 5; i >= 0; i--) {
                        if (banknotes[i] > 0) {
                            int activeMoney = 0;
                            if (i == 5) {activeMoney = 100;}
                            else if (i == 4) {activeMoney = 50;}
                            else if (i == 3) {activeMoney = 20;}
                            else if (i == 2) {activeMoney = 10;}
                            else if (i == 1) {activeMoney = 5;}
                            else if (i == 0) {activeMoney = 1;}
                            bankNotesInf[i].setBounds(716,380+order*40,298,36);
                            bankNotesInf[i].setBackground(moneyColor);
                            bankNotesInf[i].setForeground(Color.WHITE);
                            bankNotesInf[i].setOpaque(true);
                            bankNotesInf[i].setFont(new Font("Calibri", Font.PLAIN, 14));
                            bankNotesInf[i].setHorizontalAlignment(SwingConstants.LEFT);
                            bankNotesInf[i].setBorder(new EmptyBorder(10,10,10,10));
                            String banknoteText = "Banknotes";
                            if (banknotes[i] == 1) {
                                banknoteText = "Banknote";
                            }
                            bankNotesInf[i].setText(banknotes[i]+" x $"+activeMoney+" "+banknoteText);
                            order++;
                        }
                    }

                    JLabel lastControl = new JLabel("Do You Confirm?");
                    lastControl.setBounds(716,632,298,24);
                    lastControl.setFont(new Font("Calibri", Font.BOLD, 18));
                    account.add(lastControl);

                    JButton checkButton = new JButton("Yes, give me the money");
                    checkButton.setBounds(716,656,298,44);
                    checkButton.setBackground(bankColor);
                    checkButton.setForeground(Color.WHITE);
                    checkButton.setBorder(new EmptyBorder(0,0,0,0));
                    checkButton.setFocusable(false);
                    checkButton.setFont(new Font("Calibri", Font.BOLD, 16));
                    checkButton.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            Double getMoney = Double.parseDouble(money.getText());
                            Customer refreshCustomer = null;
                            int tempCardNo = customer.getCardNo();

                            try {
                                customer.updateMoney(getMoney,0);
                                tempCardNo = customer.getCardNo();
                            } catch (java.io.IOException e1) {
                                e1.printStackTrace();
                            }

                            try {
                                refreshCustomer = new Customer(tempCardNo);
                            } catch (FileNotFoundException e1) {
                                e1.printStackTrace();
                            }

                            JOptionPane.showMessageDialog(account, "You can take your money.", "Take Your Money",  JOptionPane.INFORMATION_MESSAGE);

                            for (int i = 0; i < 6; i++) {
                                bankNotesInf[i].setBounds(-220,0,0,0);
                            }

                            account.getContentPane().removeAll();
                            account.repaint();
                            account.add(heading);
                            ATMmenu menu = new ATMmenu();
                            menu.window(refreshCustomer);
                            window(refreshCustomer);
                        }
                    });
                    account.add(checkButton);

                }
            }
        });

        account.add(withdrawMoneyButton);
    }

    public static void banknote(int order, int x, int banknote_x, int banknote_y){
        bankNotes_x[order].setBounds(banknote_x+190,banknote_y+10,36,56);
        bankNotes_x[order].setFont(new Font("Calibri", Font.BOLD, 16));
        bankNotes_x[order].setHorizontalAlignment(SwingConstants.CENTER);
        bankNotes_x[order].setForeground(Color.WHITE);
        account.add(bankNotes_x[order]);

        bankNotes[order].setBounds(banknote_x,banknote_y,238,100);
        account.add(bankNotes[order]);
        bankNotes2[order].setBounds(banknote_x,banknote_y,238,100);
        account.add(bankNotes2[order]);
    }
}