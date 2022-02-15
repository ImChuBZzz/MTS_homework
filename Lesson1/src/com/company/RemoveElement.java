package com.company;

import java.util.Arrays;

public class RemoveElement
{
    public static int[] removeElement(int[] arr, int elem)
    {
        int[] newArray = new int[arr.length-1];
        for(int i = 0, j = 0; i < arr.length; i++)
        {
            if (arr[i] != elem)
            {
                newArray[j++] = arr[i];
            }
        }
        return newArray;
    } 
    public static void main(String[] args)
    {
        int[] test_array = {1,2,3,4,5,6,7,8};
        System.out.println(Arrays.toString(removeElement(test_array, 2)));
      
    }
}
