package com.gunjan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


class Result
{
    public static void main(String[] args)
    {
        
        //Arrays.fill(matrix[0],1);
         {
             int[][] matrix = new int[10000][16];
             for (int[] row: matrix)
                 Arrays.fill(row, 2);
             long start = System.currentTimeMillis();
             long count = 0;
             for(int r = 0; r < matrix.length ; r++){
                 for(int c = 0 ; c < matrix[0].length ; c++){
                     count = count + matrix[r][c];
                     //System.out.println(matrix[r][j]);
                 }
             }
             long end = System.currentTimeMillis();
             System.out.println(end-start);
             System.out.println("count="+count);
         }
        {
            int[][] matrix = new int[10000][16];
            for (int[] row: matrix)
                Arrays.fill(row, 2);
            long start = System.currentTimeMillis();
            long count = 0;
            for(int c = 0; c < matrix[0].length ; c++){
                for(int r = 0 ; r < matrix.length ; r++){
                    count = count + matrix[r][c];
                    //System.out.println(matrix[i][r]);
                }
            }
            long end = System.currentTimeMillis();
            System.out.println(end-start);
            System.out.println("count="+count);
        }
        
        
    }
}