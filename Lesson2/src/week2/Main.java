package week2;

public class Main {
    public static void main(String[] args) {
        String e1 = "12 + 12";
        String e2 = "12 * 12";
        String e3 = "12 - 24";
        String e4 = "12 / 4";
        System.out.println("первая задача:");
        System.out.println(Calc.calc(e1));
        System.out.println(Calc.calc(e2));
        System.out.println(Calc.calc(e3));
        System.out.println(Calc.calc(e4));
        System.out.println();

        String s = "Каждый охотник желает знать где сидит фазан";
        System.out.println("вторая задача:");
        System.out.println(CapitalizeEachWord.capitalizeEachWord(s));
        System.out.println();

        String s1 = "abba";
        String s2 = "abcba";
        String s3 = "ab";
        String s4 = "a";
        System.out.println("третья задача:");
        System.out.println(GetMiddleChar.getMidChar(s1));
        System.out.println(GetMiddleChar.getMidChar(s2));
        System.out.println(GetMiddleChar.getMidChar(s3));
        System.out.println(GetMiddleChar.getMidChar(s4));
        System.out.println();

        System.out.println("четвертая задача:");
        String example = "один один три два два два три два четыре";
        System.out.println(RemoveDuplicate.removeDuplicateWords(example));
        System.out.println();

        System.out.println("пятая задача:");
        System.out.println(HasSameOccurences.hasSameOccurrence("abcabcabcddd"));
        System.out.println(HasSameOccurences.hasSameOccurrence("abc"));
        System.out.println(HasSameOccurences.hasSameOccurrence("abcab"));
        System.out.println(HasSameOccurences.hasSameOccurrence("aabbba"));
        System.out.println();

    }
}
