package week2;

public class StringSplit {

    public static String[] solution(String s) {
        StringBuilder sb = new StringBuilder(s);
        if (s.length() % 2 != 0) {
            sb.setLength(s.length());
            sb.append("_");
        }
        for(int i = 2; i < sb.length(); i += 3) { sb.insert(i, "#"); }
        return sb.toString().split("#");
    }

    public static void main(String[] args) {

    }
}
