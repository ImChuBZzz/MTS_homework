package week2;

public class ConcatenationClass {
    public static String concatenation(String[] str) {
        String result = new String();
        for(int i = 0; i < str.length; i++) { result += str[i]; }
        return result;
    }
}
