
public class Cipher {
    private int key = 1;

    private final String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final int alphaLength = alpha.length();

    public void setKey(int key) {
        this.key = (key % alphaLength) + alphaLength;;
    }

    public String encrypt(String in) {
        // StringBuilder zamiast String: wyeliminowanie konkatenacji w pętli
        StringBuilder out = new StringBuilder();
        for (int i=0; i<in.length(); ++i) {
            int pos = alpha.indexOf(in.charAt(i));
            pos = (pos + key) % alphaLength;
            out.append(alpha.charAt(pos));
        }
        return out.toString();
    }

}
