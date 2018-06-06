import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Dashboard extends Account {

    public void window(Customer customer){
        // Card Informations
        int[] cardNumbers = customer.cardNumbers();
        for (int i = 0; i < 5; i++) {
            JLabel cardN = new JLabel();
            cardN.setText(String.valueOf(cardNumbers[i]));
            cardN.setBounds(448+(i*72),260,53,45);
            cardN.setFont(new Font("Calibri", Font.BOLD, 44));
            cardN.setForeground(Color.WHITE);
            cardN.setHorizontalAlignment(SwingConstants.CENTER);
            account.add(cardN);
        }
        JLabel cardHolder = new JLabel(customer.getName());
        cardHolder.setBounds(422,364,320,20);
        cardHolder.setFont(new Font("Calibri", Font.BOLD, 18));
        cardHolder.setForeground(Color.WHITE);
        cardHolder.setHorizontalAlignment(SwingConstants.LEFT);
        account.add(cardHolder);

        JLabel expires = new JLabel(customer.getExpires());
        expires.setBounds(718,364,100,20);
        expires.setFont(new Font("Calibri", Font.BOLD, 18));
        expires.setForeground(Color.WHITE);
        expires.setHorizontalAlignment(SwingConstants.RIGHT);
        account.add(expires);

        JLabel card = new JLabel(new ImageIcon("assets/images/card.png"));
        card.setBounds(contentCenter(451),166,451,237);
        account.add(card);

        JLabel cardBG = new JLabel(new ImageIcon("assets/images/cardBG.jpg"));
        cardBG.setBounds(200,80,824,410);
        account.add(cardBG);

        // Money in your account
        String curr = "USD";
        Currency currency = new Currency();

        JLabel bH = new JLabel(new ImageIcon("assets/images/icons/MoneyInYourAccounts.png"));
        bH.setBounds(548,516,36,36);
        account.add(bH);

        JLabel boxH = new JLabel("Money In Your Account");
        boxH.setBounds(220,510,382,48);
        boxH.setForeground(Color.WHITE);
        boxH.setBackground(secondColor);
        boxH.setBorder(new EmptyBorder(10,20,10,20));
        boxH.setFont(new Font("Calibri", Font.BOLD, 16));
        boxH.setOpaque(true);
        account.add(boxH);

        JLabel b = new JLabel();
        try {
            b.setText(currency.money(customer.getCurrentBalance(), curr));
        } catch (IOException e) {
            e.printStackTrace();
        }
        b.setBounds(220,548,382,190);
        b.setFont(new Font("Calibri", Font.PLAIN, 60));
        b.setHorizontalAlignment(SwingConstants.CENTER);
        account.add(b);

        JLabel b2 = new JLabel(curr);
        b2.setBounds(220,670,382,30);
        b2.setFont(new Font("Calibri", Font.PLAIN, 20));
        b2.setHorizontalAlignment(SwingConstants.CENTER);
        account.add(b2);

        JPanel box = new JPanel();
        box.setBounds(220,510,382,238);
        box.setBackground(contentH);
        account.add(box);


        // Last Account Activities
        JLabel accountActivitiesH = new JLabel(new ImageIcon("assets/images/icons/AccountActivities.png"));
        accountActivitiesH.setBounds(960,516,36,36);
        account.add(accountActivitiesH);

        JLabel accountActivitiesBoxH = new JLabel("Last Account Activities");
        accountActivitiesBoxH.setBounds(622,510,382,48);
        accountActivitiesBoxH.setForeground(Color.WHITE);
        accountActivitiesBoxH.setBackground(secondColor);
        accountActivitiesBoxH.setBorder(new EmptyBorder(10,20,10,20));
        accountActivitiesBoxH.setFont(new Font("Calibri", Font.BOLD, 16));
        accountActivitiesBoxH.setOpaque(true);
        account.add(accountActivitiesBoxH);

        Scanner aa = null;
        try {
            aa = new Scanner(new File("assets/datas/accountsActivities.txt"));
            ArrayList<String> accountsActivities = new ArrayList<>();
            while(aa.hasNextLine()) {
                String line = aa.nextLine();
                String[] lines = line.split(":::");
                if (Double.parseDouble(lines[0]) == customer.getCardNo()) {
                    accountsActivities.add(line);
                }
            }
            int size = accountsActivities.size();
            int first = 0;
            if (accountsActivities.size() >= 3) {
                first = accountsActivities.size()-3;
            }
            int order = 1;
            for (int i = first; i < size; i++) {
                String line = accountsActivities.get(size-order);
                String[] inf = line.split(":::");
                inf = inf[1].split(",");
                JLabel sT = new JLabel(inf[0]+" - "+inf[1]);
                sT.setBounds(642,568+((i-first)*56),362,36);
                sT.setFont(new Font("Calibri", Font.BOLD, 16));
                account.add(sT);

                JLabel sH = new JLabel(inf[2]);
                sH.setBounds(642,592+((i-first)*56),362,28);
                sH.setFont(new Font("Calibri", Font.PLAIN, 16));
                account.add(sH);

                order++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Activities Content
        JPanel accountActivitiesBox = new JPanel();
        accountActivitiesBox.setBounds(622,510,382,238);
        accountActivitiesBox.setBackground(contentH);
        account.add(accountActivitiesBox);

    }

}
