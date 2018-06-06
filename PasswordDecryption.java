public class PasswordDecryption {
    private String password;
    private String passwordDecrypt = "";
    public PasswordDecryption(){
        this("");
    }
    public PasswordDecryption(String password) {
        this.password = password;
        encrypt(password);
    }
    // a 97 - z 122
    public void encrypt(String password) { // SM3RT2
        for (int i = 0; i < password.length(); i++) {
            if (isInt(password.toLowerCase().charAt(i))) {
                passwordDecrypt+=password.toLowerCase().charAt(i);
            } else {
                int ascii = (int) password.toLowerCase().charAt(i);
                if (ascii < 100) {
                    if (ascii == 99) {ascii = 122;}
                    if (ascii == 98) {ascii = 121;}
                    if (ascii == 97) {ascii = 120;}
                } else {
                    ascii-= 3;
                }
                char newCharacter = (char) ascii;
                passwordDecrypt += newCharacter;
            }
        }
    }
    public String getPassword(){
        return passwordDecrypt;
    }

    public static boolean isInt(char s) {
        return Character.isDigit(s);
    }
}
