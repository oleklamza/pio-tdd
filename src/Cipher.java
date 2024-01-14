
public class Cipher {

    public String encrypt(String in) {
        String out = "";
        for (int i=0; i<in.length(); ++i) {
            char c = in.charAt(i);
            c += 1;
            // najprostsza implementacja "zawinięcia" na końcu alfabetu
            if (c == '[') {
                c = 'A';
            }
            out += c;
        }
        return out;
    }

}
