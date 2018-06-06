import java.io.*;
import java.sql.Ref;
import java.util.ArrayList;
import java.util.Scanner;

public class Customer {
    private String name;
    private int cardNo;
    private String password;
    private String expires;
    private double currentBalance; // always dollar

    public Customer(){
        setName("");
        setPassword("");
        setExpires("");
        setCurrentBalance(-1);
        setCardNo(-1);
    }
    public Customer(int customerID) throws FileNotFoundException {
        Scanner f = new Scanner(new File("assets/datas/bank-accounts.txt"));
        while (f.hasNextLine()) {
            String line = f.nextLine();
            String[] inf = line.split(",");
            if (Integer.parseInt(inf[0]) == customerID) {
                setName(inf[2]);
                setPassword(inf[1]);
                setExpires(inf[3]);
                setCurrentBalance(Double.parseDouble(inf[4]));
                setCardNo(customerID);
            }
        }
    }
    public boolean search(int accountID) throws FileNotFoundException {
        Scanner f = new Scanner(new File("assets/datas/bank-accounts.txt"));
        while (f.hasNextLine()) {
            String line = f.nextLine();
            String[] inf = line.split(",");
            if (Integer.parseInt(inf[0]) == accountID) {
                setName(inf[2]);
                setPassword(inf[1]);
                setExpires(inf[3]);
                setCurrentBalance(Double.parseDouble(inf[4]));
                setCardNo(accountID);
                return true;
            }
        }
        return false;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getCardNo() {
        return cardNo;
    }
    public void setCardNo(int cardNo) {
        this.cardNo = cardNo;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getExpires() {
        return expires;
    }
    public void setExpires(String expires) {
        this.expires = expires;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }
    public void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }

    public String getFirstName() {
        String[] n = name.split(" ");
        if (n.length == 2) {
            return n[0];
        } else {
            return n[0].substring(0,1)+". "+n[1];
        }
    }

    public int[] cardNumbers(){
        int cardNos = this.cardNo;
        int[] cardNumbers = new int[5];
        for (int i = 4; i >= 0; i--) {
            cardNumbers[i] = cardNos%10;
            cardNos /= 10;
        }
        return cardNumbers;
    }

    public ArrayList<ArrayList<String>> activities(){
        Scanner activities = null;
        try {
            activities = new Scanner(new File("assets/datas/accountsActivities.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ArrayList<ArrayList<String>> activitiesList = new ArrayList<>();
        while(activities.hasNextLine()) {
            String[] acControl = activities.nextLine().split(":::");
            if (Integer.parseInt(acControl[0]) == getCardNo()) {
                String[] activityInf = acControl[1].split(",");
                ArrayList<String> activityList = new ArrayList<>();
                activityList.add(activityInf[0]);
                activityList.add(activityInf[1]);
                activityList.add(activityInf[2]);
                activitiesList.add(0,activityList);
            }
        }
        return activitiesList;
    }

    public void updateMoney(double newMoney, int addORremove) throws IOException { // 0-> remove 1-> add
        Scanner s = new Scanner(new File("assets/datas/bank-accounts.txt"));
        String Refresh = "";
        String event = "";
        while(s.hasNextLine()) {
            String line = s.nextLine();
            String[] inf = line.split(",");
            if (Integer.parseInt(inf[0]) == cardNo) {
                double temp = 0;
                if (addORremove == 1) { // ADD
                    temp = Double.parseDouble(inf[4])+newMoney;
                    event = "The money was deposited.";
                } else {
                    temp = Double.parseDouble(inf[4])-newMoney;
                    event = "The money was taken.";
                }
                String newLine = "";
                for (int i = 0; i < 4; i++) {
                    newLine += inf[i]+",";
                }
                newLine += temp;
                Refresh += newLine+"\n";
            } else {
                Refresh += line+"\n";
            }
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter("assets/datas/bank-accounts.txt"));
        writer.write(Refresh);
        writer.close();

        mCal cal = new mCal();

        int finalMoney = (int) newMoney;
        String activities = "\n"+cardNo+":::"+cal.currentDate()+","+cal.currentTime()+","+event+","+finalMoney;
        BufferedWriter writerActivities = new BufferedWriter(new FileWriter("assets/datas/accountsActivities.txt", true));
        writerActivities.write(activities);
        writerActivities.close();
    }


    public void transferMoney(double newMoney, int account2) throws IOException {
        Scanner s = new Scanner(new File("assets/datas/bank-accounts.txt"));
        String Refresh = "";
        String event = "";
        while(s.hasNextLine()) {
            String line = s.nextLine();
            String[] inf = line.split(",");
            if (Integer.parseInt(inf[0]) == cardNo) {
                double temp = 0;
                Customer customerTO = new Customer(account2);
                    temp = Double.parseDouble(inf[4])-newMoney;
                    event = "The money was sent to "+customerTO.getName()+".";
                String newLine = "";
                for (int i = 0; i < 4; i++) {
                    newLine += inf[i]+",";
                }
                newLine += temp;
                Refresh += newLine+"\n";
            } else {
                Refresh += line+"\n";
            }
        }

        Scanner s2 = new Scanner(Refresh);
        String RefreshFinal = "";
        while(s2.hasNextLine()) {
            String line = s2.nextLine();
            String[] inf = line.split(",");
            if (Integer.parseInt(inf[0]) == account2) {
                double temp = 0;
                temp = Double.parseDouble(inf[4])+newMoney;
                String newLine = "";
                for (int i = 0; i < 4; i++) {
                    newLine += inf[i]+",";
                }
                newLine += temp;
                RefreshFinal += newLine+"\n";
            } else {
                RefreshFinal += line+"\n";
            }
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter("assets/datas/bank-accounts.txt"));
        writer.write(RefreshFinal);
        writer.close();

        mCal cal = new mCal();

        int finalMoney = (int) newMoney;
        String activities = "\n"+cardNo+":::"+cal.currentDate()+","+cal.currentTime()+","+event+","+finalMoney;
        BufferedWriter writerActivities = new BufferedWriter(new FileWriter("assets/datas/accountsActivities.txt", true));
        writerActivities.write(activities);
        writerActivities.close();
    }
}
