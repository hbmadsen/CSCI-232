import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import stopwatch.*;

/**
 * TestSort uses the CockTail algorithm I wrote for program 1 & sorts the two provided files supplied
 * by Mary Ann. One is a file of strings and the other is of doubles
 */
public class TestSort {

    public TestSort(){ } //never instantiated

    /**
     * checks if the array is sorted
     * @param a an array of comparables
     * @return true if array is sorted
     */

    private static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i - 1])) return false;
        return true;
    }

    /**
     * determines if v is less that w
     * @param v a comparable value
     * @param w second comparable value
     * @return true if v is less than w
     */
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    /**
     * swaps the values in index i and index j of array a
     * @param a an object array
     * @param i the index of an object in array a
     * @param j the index of an object in array a
     */
    private static void swap(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    /**
     * sorts array by traversing from left to right and putting max value in largest index
     * then traverses back from right to left and puts minimum in smallest index
     * @param a the array being sorted into ascending order
     */
    public static void CocktailSort( Comparable[] a){

        boolean cSort = false;
        int min = 0;
        int max = a.length -1;

        while(!cSort) {

            for (int i = min; i < max - 1; ++i) {
                if (!less(a[i], a[i + 1])) {
                    swap(a, i, i + 1);
                }
            }

            if(isSorted(a)){
                cSort = true;
                break;

            }

            max -= 1;

            for(int j = max; j >= min; j--){
                if(!less(a[j], a[j + 1])){
                    swap(a, j, j + 1);
                }
            }

            if(isSorted(a)){
                cSort = true;
            }

            min +=1;
        }

        assert isSorted(a);
    }

    /**
     * gathers and stores data from two files and sorts them using Cocktail Sort
     * prints the time taken to perform each sort as well as the total number of objects in each file
     * @param args the command line arguments
     * @throws FileNotFoundException  thrown if no file is provided in command line argument
     */
    public static void main(String[] args) throws FileNotFoundException {

        File file = new File(args[0]);
        Scanner inpFile = new Scanner(file);

        int n = 0;
        while(inpFile.hasNext()){
            inpFile.next();
            n++;
        }

        Scanner inpFile2 = new Scanner(file);

        Comparable[] testSort = new Comparable[n];
        int i = 0;

        while (inpFile2.hasNext()){
            testSort[i] = inpFile2.next();
            i++;
        }

        File file2 = new File(args[1]);
        Scanner inpFile3 = new Scanner(file2);

        int k = 0;
        while(inpFile3.hasNext()){
            inpFile3.next();
            k++;
        }

        Scanner inpFile4 = new Scanner(file2);

        Comparable[] testSort2 = new Comparable[k];

        int j = 0;
        while(inpFile4.hasNext()){
            testSort2[j] = inpFile4.next();
            j++;
        }

        StopWatch timer1 = new StopWatch();
        CocktailSort(testSort);
        System.out.println("Elapsed time for cocktail sort " + timer1.elapsedTime() + ". Number of words in manifesto: " + n);

        StopWatch timer2 = new StopWatch();
        CocktailSort(testSort2);
        System.out.println("Elapsed time for cocktail sort: " + timer2.elapsedTime() + ". Amount of floating point numbers in reals: " + k);


    }
}

