/**
 * Hannah Madsen & Hannah Cebulla
 * Lab 2
 */


import java.util.Random;

import com.hannah.stopwatch.*;

    public class FourSum{

        /**
         * Returns the number of groups of four (i, j, k, l) with {@code i < j < k < l}
         * such that {@code a[i] + a[j] + a[k] + a[l] ==0}
         *
         * @param a the array of integers
         * @return the number of groups of four (i, j, k, l)with {@code i < j < k}
         *          such that {@code a[i] + a[j] + a[k] + a[l] == 0}
         */

        public static int count(int[] a) {
            int n = a.length;
            int count = 0;
            for (int i = 0; i < n; i++) {
                for (int j = i+1; j < n; j++) {
                    for (int k = j+1; k < n; k++) {
                        for (int l = k+1; l < n; l++){
                            if (a[i] + a[j] + a[k] + a[l] == 0) {
                                count++;
                            }
                        }
                    }
                }
            }
            return count;
        }

        /**
         * Array a is created with space for 1000 elements;
         * 1000 random integers from -4000 to 4000 are assigned to the elements of array a;
         * counts the number of groups of four sum to exactly zero; prints out the time to perform
         * the computation.
         *
         * @param args the command-line arguments
         */

        public static void main(String[] args){


            int [] a = new int[1000];
            int i = 0;

            Random random = new Random();

            while (i < a.length) {
                a[i++] = random.nextInt(4000 + 4000) - 4000;
            }

            StopWatch timer = new StopWatch();
            int count = count(a);
            System.out.println("elapsed time = " + timer.elapsedTime());
            System.out.println(count);
        }
    }

