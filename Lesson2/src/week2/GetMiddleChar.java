package week2;

public class GetMiddleChar {
    public static String getMidChar(String str) {
        int mid = str.length() / 2;
        if (str.length() % 2 == 0) { return str.substring(mid-1, mid+1); }
        else { return str.substring(mid, mid+1); }
    }
}
