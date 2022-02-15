package week2;

public class Calc {
    public static double calc(String equation) {
        String[] data = equation.split(" ");
        double value1 = Double.parseDouble(data[0]);
        double value2 = Double.parseDouble(data[2]);
        double result = 0;
        String operator = data[1];
        switch (operator) {
            case "+":
                result =  value1 + value2;
                break;
            case  "-":
                result = value1 - value2;
                break;
            case "*":
                result = value1 * value2;
                break;
            case "/":
                result = value1 / value2;
                break;
        }
        return result;
    }
}
