
public class Cipher {
    private int key = 1;

    private final String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final int alphaLength = alpha.length();

    public void setKey(int key) {
        // zabezpieczenie przed ujemnymi i dużymi kluczami
        this.key = (key % alphaLength) + alphaLength;;
    }

    public String encrypt(String in) {
        String out = "";
        for (int i=0; i<in.length(); ++i) {
            int pos = alpha.indexOf(in.charAt(i));
            pos = (pos + key) % alphaLength;
            // tu jest zgłaszany problem związany z konkatenacją w pętli;
            // zajmiemy się tym w kolejnym etapie
            out += alpha.charAt(pos);
        }
        return out;
    }

}
