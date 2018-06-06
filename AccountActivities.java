import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class AccountActivities extends Account {
    public static void window(Customer customer) {
        // Header
        JLabel headerImg = new JLabel(new ImageIcon("assets/images/icons/AccountActivities.png"));
        headerImg.setBounds(220,94,36,36);
        account.add(headerImg);

        JLabel subtitle = new JLabel("Last 7 Account Activities");
        subtitle.setBounds(200,80,824,64);
        subtitle.setBackground(secondColor);
        subtitle.setForeground(Color.WHITE);
        subtitle.setFont(new Font("Calibri", Font.BOLD,18));
        subtitle.setBorder(new EmptyBorder(0,70,0,20));
        subtitle.setOpaque(true);
        account.add(subtitle);

        Scanner activities = null;
        try {
            activities = new Scanner(new File("assets/datas/accountsActivities.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ArrayList<String> activitiesList = new ArrayList<>();
        while(activities.hasNextLine()) {
            String line = activities.nextLine();
            String[] lineControl = line.split(":::");
            if (Double.parseDouble(lineControl[0]) == customer.getCardNo()) {
                activitiesList.add(0,lineControl[1]);
            }
        }
        int order = 0;
        int last = 0;
        if (activitiesList.size() < 7) {last = activitiesList.size();}
        else {last = 7;}
        for (int i = 0; i < last; i++) {
            line(activitiesList.get(i),order);
            order++;
        }
    }

    public static void line(String line, int order) {
        String[] inf = line.split(",");
        String date = inf[0];
        String time = inf[1];
        String information = inf[2];
        String money = inf[3];
        String status = "";

        if (information.indexOf("taken") >= 0) {status = "takemoney";}
        else if (information.indexOf("deposit") >= 0) {status = "depositmoney";}
        else if (information.indexOf("sent") >= 0) {status = "sendmoney";}

        JLabel icon = new JLabel(new ImageIcon("assets/images/icons/aa_"+status+".png"));
        icon.setBounds(220,164+(order*82),70,62);
        account.add(icon);

        JLabel statusText = new JLabel(information);
        statusText.setBounds(310,166+(order*82),710,36);
        statusText.setFont(new Font("Calibri", Font.BOLD, 18));
        account.add(statusText);

        if (status.equals("depositmoney")) {
            JLabel balance = new JLabel("$"+money);
            balance.setBounds(310,192+(order*82),710,26);
            balance.setFont(new Font("Calibri", Font.BOLD, 16));
            balance.setForeground(moneyColor);
            account.add(balance);
        } else {
            JLabel balance = new JLabel("- $"+money);
            balance.setBounds(310,192+(order*82),710,26);
            balance.setFont(new Font("Calibri", Font.BOLD, 16));
            balance.setForeground(new Color(231,76,60));
            account.add(balance);
        }

        JLabel jdate = new JLabel(date);
        jdate.setBounds(850, 166 + (order * 82), 150, 36);
        jdate.setFont(new Font("Calibri", Font.PLAIN,16));
        jdate.setHorizontalAlignment(SwingConstants.RIGHT);
        account.add(jdate);

        JLabel jtime = new JLabel(time);
        jtime.setBounds(850, 186 + (order * 82), 150, 36);
        jtime.setFont(new Font("Calibri", Font.PLAIN,16));
        jtime.setHorizontalAlignment(SwingConstants.RIGHT);
        account.add(jtime);
    }
}