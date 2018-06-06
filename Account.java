import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.FileNotFoundException;

public class Account extends Main {
    public static JFrame account = new JFrame();
    public static JLabel heading = new JLabel();

    public void window(String accountID) throws FileNotFoundException {
        Customer customer = new Customer(Integer.parseInt(accountID));

        // Header
        heading.setText(new mCal().message()+" "+customer.getFirstName());
        heading.setBounds(200,0,824,80);
        heading.setBorder(new EmptyBorder(0,20,0,20));
        heading.setBackground(contentH);
        heading.setFont(new Font("Calibri",Font.BOLD,18));
        account.add(heading);

        // Content
        Dashboard dashboard = new Dashboard();
        dashboard.window(customer);

        // Menu
        ATMmenu menu = new ATMmenu();
        menu.window(customer);

        // Window Setting
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        account.setLocation(dim.width/2-windowWidth/2, dim.height/2-windowHeight/2);
        account.setSize(windowWidth,windowHeight);
        account.setResizable(false);
        account.setLayout(null);
        account.setVisible(true);
    }

    public static int contentCenter(int width){
        return (int) 200+(windowWidth-width-200)/2;
    }
}