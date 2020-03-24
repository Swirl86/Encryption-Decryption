public class Unicode {
	
    public static String encode(String text, int key) {
        return process(text, key);
	}
    
    public static String decode(String text, int key) {
        return process(text, key * -1);
	}
    
    public static String process(String text, int key) {
    	String chipherStr = "";
    	for (char c : text.toCharArray()) {
    		String letter = toUnicode(c, key);
    		Integer code = Integer.parseInt(letter.substring(2), 16); 
    		char ch = Character.toChars(code)[0]; 
			chipherStr += ch;
		}
        return chipherStr;
	}
    	
	private static String toUnicode(char ch, int key) {
	    return String.format("\\u%04x", (int) ch + key);
	}

}