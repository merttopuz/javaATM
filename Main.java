import java.awt.*;

public class Main {
    public static final int windowWidth = 1040;
    public static final int windowHeight = 807;

    public static final Color bankColor      = new Color(0,120,215);
    public static final Color bankHover     = new Color(0,110,195);
    public static final Color bankFocus     = new Color(0,90,165);
    public static final Color contentH        = new Color(228,228,228);
    public static final Color secondColor  = new Color(141,141,141);
    public static final Color moneyColor   = new Color(0,146,123);

    public static void main(String[] args) {
        Login login = new Login();
        login.window();
    }

    public static boolean isInt(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}