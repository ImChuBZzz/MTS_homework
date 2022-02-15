package com.company;

public class ABCreverse
{
    public static void printAlphabetReverse()
    {
        char []abc = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        for (int i = abc.length-1; i >= 0 ; i--)
        {
            System.out.print(abc[i] + " ");
        }
    }
}
  