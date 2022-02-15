package com.company;

import java.util.Arrays;

public class CreateRndArray
{
    public static double[] CreateRandomArray(int n)
    {
        double[] rndArray = new double[n];
        for(int i = 0; i < n; i++)
        {
            rndArray[i] = Math.random();
        }
        return rndArray;
    }

    public static void main(String[] args)
    {
        int size = 6;
        double[] arr1 = CreateRandomArray(size);
        System.out.println(Arrays.toString(arr1));
    }
}