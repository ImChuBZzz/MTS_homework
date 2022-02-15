package com.company;

import java.util.Arrays;

public class multiplyMatrix
{
    public static double[][] MatrixMultiply(double[][] a, double[][] b)
    {
        int col = a.length, row = b[0].length;
        double[][] c = new double[col][row];
        for(int i = 0; i < c[0].length; i++)
        {
            for(int j = 0; j < c.length; j++)
            {
                for(int k = 0; k < a[0].length; k++)
                {
                    c[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return c;
    }
    
    public static void main(String[] args)
    {
        double[][] a = {{1, 2, 3}, {1, 2, 3}, {1, 2, 3}};
        double[][] b = {{3, 2, 1}, {3, 2, 1}, {3, 2, 1}};
        double[][] c = MatrixMultiply(a, b);
        for(int i = 0; i < c.length; i++)
        {
            System.out.println(Arrays.toString(c[i]));
        }
    }
}
