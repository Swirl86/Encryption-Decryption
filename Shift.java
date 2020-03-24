
public class Shift {
    final static String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    final static String tebahpla = "zyxwvutsrqponmlkjihgfedcbaZYXWVUTSRQPONMLKJIHGFEDCBA";
    static String cipherStr = "";


    public static String shiftText(String text, int key) {

        for (int i = 0; i < text.length(); i++) {
            if(notLetter(text.charAt(i))) {
                cipherStr += text.charAt(i);
            } else {
                for (int j = 0; j < alphabet.length(); j++) {
                    if(text.charAt(i) == alphabet.charAt(j)) {
                        char letter = shiftChar(alphabet.charAt(j), key);
                        cipherStr += letter; //tebahpla.charAt(j);
                        break;
                    }
                }
            }
        }

        return cipherStr;
    }

    private static char shiftChar(char charAt, int shifts) {
        char letter = (char) (charAt + shifts);
        if (letter > 'z') {
            letter = (char) (letter - 26);
        } else if (letter < 'a') {
            letter = (char) (letter + 26);
        }
        return letter;
    }

    private static boolean notLetter(char ch) {
        return ((ch == ' ') || (ch =='\n') || (ch == '\t') || (ch =='!') || (ch == '?'));
    }
}
