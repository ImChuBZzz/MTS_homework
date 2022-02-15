package week2;

public class CapitalizeEachWord {
    public static String capitalizeEachWord(String str) {
        String[] words = str.split(" ");
        StringBuilder finalStr = new StringBuilder();
        for(int i = 0; i < words.length; i++) {
            finalStr.append(words[i].substring(0, 1).toUpperCase()).append(words[i].substring(1)).append(" ");
        }
        return finalStr.toString().strip();
    }
}
