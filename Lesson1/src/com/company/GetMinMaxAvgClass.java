package com.company;

import java.util.Arrays;

public class GetMinMaxAvgClass
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

    public static void GetMinMaxAvg(double[] arr)
    {
        double min = arr[0], max = arr[0], avg = arr[0];
        for(int i = 1; i < arr.length; i++)
        {
            avg += arr[i];
            if (arr[i] <= min)
            {
                min = arr[i];
            }
            if (arr[i]>= max)
            {
                max = arr[i];
            }
        }
        System.out.println("Min = " + min);
        System.out.println("Max = " + max);
        System.out.println("Avg = " + avg/arr.length);
    }

    public static void main(String[] args)
    {
        int size = 3;
        double[] testArray = CreateRandomArray(size);
        GetMinMaxAvg(testArray);
        System.out.println(Arrays.toString(testArray));
    }
}