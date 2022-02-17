package week2;

public class HasSameOccurences {
    public static int letterCount(String str, char c) {
        int count = 0;
        for(int i = 0; i < str.length(); i++) {
            if(str.charAt(i) == c) {
                count += 1;
           }
        }
        return count;
    }
    public static boolean hasSameOccurrence(String str) {
        int cur, prev = letterCount(str, str.charAt(0));
        boolean flag = true;

        for(int i = 1; i < str.length(); i++) {
           cur = letterCount(str, str.charAt(i));
           if (prev != cur) { flag = false; break; }
           prev = cur;
        }
        return flag;
    }
}
