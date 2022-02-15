package week2;

public class insertDashClass {
    public static String insertDash(String str) {
        String temp = str.strip();
        String[] tmp = temp.split(" ");

        String result = "";
        for (int i = 0; i < tmp.length; i++) {
            result += tmp[i] + "-";
        }
        return result.substring(0, result.length() - 1);
    }
}
