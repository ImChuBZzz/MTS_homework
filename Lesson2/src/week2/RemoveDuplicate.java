package week2;

public class RemoveDuplicate {
    public static String removeDuplicateWords(String str) {
        String[] words = str.split(" ");
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < words.length; i++) {
            for(int j = i+1; j < words.length; j++) {
                if (words[i].equals(words[j])) { words[j] = "" ; }
            }
        }

        for(String __: words) {
            if (!__.equals("")) { sb.append(__).append(" "); }
        }
        return sb.toString();
    }
}
