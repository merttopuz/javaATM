import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Currency {
    public String money(double balance, String currency, boolean withCurrency) {
        try {
            return money(balance, "USD")+" "+currency;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
    public String money(double balance) throws IOException {
            return money(balance, "USD");
    }


    public static String money(double balance, String currency) throws IOException {
        return moneySplit(balance);
    }

    public static String moneySplit(double balance) {
        String money = String.valueOf(balance);
        String[] m = money.split("\\.");
        int[] digits = new int[m[0].length()];
        int tempBalance = Integer.parseInt(m[0]);

        for (int i = 0; i < digits.length; i++) {
            digits[i] = tempBalance%10;
            tempBalance /= 10;
        }

        String result = "";
        for (int i = 0; i < digits.length; i++) {
            if (i%3 == 0 && i != 0 && i != digits.length) {
                result = "."+result;
            }
            result = digits[i]+result;
        }

        return result+","+m[1];
    }
}
