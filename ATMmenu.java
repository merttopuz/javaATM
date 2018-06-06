import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;

public class ATMmenu extends Account {

    public static void window(Customer customer){
        // Logo
        JLabel imgLabel = new JLabel(new ImageIcon("assets/images/logo2.png"));
        imgLabel.setBounds(20,20,120,40);
        account.add(imgLabel);

        JPanel logoBG = new JPanel();
        logoBG.setBounds(0,0,200,80);
        logoBG.setBackground(bankHover);
        logoBG.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                account.getContentPane().removeAll();
                account.repaint();
                window(customer);
                account.add(heading);
                int tempCardNo = customer.getCardNo();
                Customer refreshCustomer = null;
                try {
                    refreshCustomer = new Customer(tempCardNo);
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
                Dashboard db = new Dashboard();
                db.window(refreshCustomer);
            }
        });
        account.add(logoBG);

        // Personal
        JLabel personImg = new JLabel(new ImageIcon("assets/images/person.png"));
        personImg.setBounds(55,105,90,98);
        account.add(personImg);

        JLabel personName = new JLabel(customer.getName(), JLabel.CENTER);
        personName.setBounds(0,200,200,36);
        personName.setFont(new Font("Calibri", Font.BOLD,18));
        personName.setForeground(Color.WHITE);
        account.add(personName);

        // Menu
        String[][] menus = new String[4][2];
        menus = new String[][]{
                {"WithdrawMoney", "Withdraw Money"},
                {"DepositMoney", "Deposit Money"},
                {"TransferMoney", "Transfer Money"},
                {"AccountActivities", "Account Activities"}
        };

        for (int i = 0; i < menus.length; i++) {
            JLabel menuImg = new JLabel(new ImageIcon("assets/images/icons/"+menus[i][0]+".png"));
            menuImg.setBounds(10,246+48*i,36,36);

            JButton menu = new JButton(menus[i][1]);
            menu.setBounds(50,240+48*i,150,48);
            menu.setBorder(new EmptyBorder(0,10,0,10));
            menu.setBackground(bankColor);
            menu.setForeground(Color.WHITE);
            menu.setFont(new Font("Calibri", Font.BOLD, 16));
            menu.setHorizontalAlignment(SwingConstants.LEFT);
            menu.setFocusable(false);
            menu.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    account.getContentPane().removeAll();
                    account.repaint();
                    window(customer);
                    account.add(heading);
                    int tempCardNo = customer.getCardNo();
                    if (menu.getText().equals("Withdraw Money")) {
                        Customer refreshCustomer = null;
                        try {
                            refreshCustomer = new Customer(tempCardNo);
                        } catch (FileNotFoundException e1) {
                            e1.printStackTrace();
                        }
                        WithdrawMoney wm = new WithdrawMoney();
                        wm.window(refreshCustomer);
                    } else if (menu.getText().equals("Deposit Money")) {
                        Customer refreshCustomer = null;
                        try {
                            refreshCustomer = new Customer(tempCardNo);
                        } catch (FileNotFoundException e1) {
                            e1.printStackTrace();
                        }
                        DepositMoney dm = new DepositMoney();
                        dm.window(refreshCustomer);
                    } else if (menu.getText().equals("Transfer Money")) {
                        Customer refreshCustomer = null;
                        try {
                            refreshCustomer = new Customer(tempCardNo);
                        } catch (FileNotFoundException e1) {
                            e1.printStackTrace();
                        }
                        TransferMoney tm = new TransferMoney();
                        tm.window(refreshCustomer);
                    } else if (menu.getText().equals("Account Activities")) {
                        Customer refreshCustomer = null;
                        try {
                            refreshCustomer = new Customer(tempCardNo);
                        } catch (FileNotFoundException e1) {
                            e1.printStackTrace();
                        }
                        AccountActivities aa = new AccountActivities();
                        aa.window(refreshCustomer);
                    }
                }
                @Override
                public void mouseEntered(MouseEvent e) {
                    if (menu.getBackground() != bankFocus) {
                        menu.setBackground(bankHover);
                    }
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    if (menu.getBackground() == bankHover) {
                        menu.setBackground(bankColor);
                    }
                }
            });
            account.add(menuImg);
            account.add(menu);
        }

        // Logout
        JLabel logoutImg = new JLabel(new ImageIcon("assets/images/icons/logout.png"));
        logoutImg.setBounds(10,720,36,36);

        JButton logout = new JButton("Logout");
        logout.setBounds(60,708,140,60);
        logout.setBackground(bankColor);
        logout.setForeground(Color.WHITE);
        logout.setFont(new Font("Calibri", Font.BOLD, 16));
        logout.setHorizontalAlignment(SwingConstants.LEFT);
        logout.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        logout.setFocusable(false);
        logout.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                account.dispatchEvent(new WindowEvent(account, WindowEvent.WINDOW_CLOSING));
                Login loginAgain = new Login();
                loginAgain.window();
            }
        });

        account.add(logoutImg);
        account.add(logout);

        // Menu Panel
        JPanel menu = new JPanel();
        menu.setBounds(0,0,200,windowHeight);
        menu.setBackground(bankColor);
        account.add(menu);
    }

}
