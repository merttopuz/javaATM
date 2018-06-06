public class PasswordEncryption {
    private String password;
    private String passwordEncrypt = "";
    public PasswordEncryption(){
        this("");
    }
    public PasswordEncryption(String password) {
        this.password = password;
        encrypt(password);
    }
    // a 97 - z 122
    public void encrypt(String password) {
        for (int i = 0; i < password.length(); i++) {
            if (isInt(password.toLowerCase().charAt(i))) {
                passwordEncrypt+=password.toLowerCase().charAt(i);
            } else {
                int ascii = (int) password.toLowerCase().charAt(i);
                if (ascii > 119) {
                    if (ascii == 120) {ascii = 97;}
                    if (ascii == 121) {ascii = 98;}
                    if (ascii == 122) {ascii = 99;}
                } else {
                    ascii+= 3;
                }
                char newCharacter = (char) ascii;
                passwordEncrypt += newCharacter;
            }
        }
    }
    public String getPassword(){
        return passwordEncrypt;
    }

    public static boolean isInt(char s) {
        return Character.isDigit(s);
    }
}
