
public class Cipher {
    private int key = 1;

    public void setKey(int key) {
        this.key = key;
    }

    public String encrypt(String in) {
        String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        String out = "";
        for (int i=0; i<in.length(); ++i) {
            char c = in.charAt(i);
            int pos = alpha.indexOf(c);
            pos = (pos + key) % alpha.length();
            c = alpha.charAt(pos);
            out += c;
        }
        return out;
    }

}
