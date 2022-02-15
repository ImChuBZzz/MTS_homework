package com.company;

import java.util.Arrays;


public class addMatrix
{
    public static double[][] MatrixAdd(double[][]a, double[][]b)
    {
        int row = a.length, col = a[0].length;
        double[][] c = new double[row][col];
        for(int i = 0; i < row; i++ )
        {
            for(int j = 0; j < col; j++)
            {
                c[i][j] = a[i][j] + b[i][j];
            }
        }
        return c;
    }
    
    public static void main(String[] args)
    {
        double[][] matrix1 = {{1, 2, 3}, {1, 2, 3}, {1, 2, 3}};
        double[][] matrix2 = {{1, 1, 1}, {3, 2, 1}, {3, 2, 1}};
        double[][] result = MatrixAdd(matrix1, matrix2);
        //System.out.println(Arrays.deepToString(result));
        for(int i = 0; i < result.length; i++)
        {
            System.out.println(Arrays.toString(result[i]));
        }
    }
}
