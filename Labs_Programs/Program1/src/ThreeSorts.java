import java.util.Random;
import stopwatch.*;
import java.util.Arrays;

/*
Program 1: Hannah Madsen
The following program implements Bubble, Radix, and Cocktail sorts to
sort the same original set of random integers in ascending order.
 */

public class ThreeSorts {

    ThreeSorts(){ } //class is never instantiated


    //verifies is v is less than w
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    //exchanges the objects at a[i] and a[j]
    private static void swap(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    //finds the max value in array a
    private static int findMax( int[] a){

        int max = 0;

        for(int i = 1; i < a.length; i++){
            if( a[i] > max){
                max = a[i];
            }
        }
        return max;
    }

    /**
     * sorts the array in ascending order, using significant digit place compares
     * @param a the array being sorted
     * @param place the significant digit being compared
     */

    private static void sortR(int[] a, int place){

        int[] b = new int[a.length];
        int[] bucket = new int[10];

        for(int i = 0; i < a.length; i++){
            bucket[(a[i]/place)%10]++; //keeps count of the number of integers in each bucket
        }

        for(int j = 1; j < 10; j++){
            bucket[j] += bucket[j-1]; //make the bucket at index i hold the index of where that bucket will start in sorted array b
        }

        for(int i = a.length - 1 ; i >= 0; i--){
            b[bucket[(a[i]/place)%10] - 1] = a[i];
            bucket[(a[i]/place)%10]--;
        }

        for(int i =0; i < a.length; i++){
            a[i] = b[i];
        }



    }

    /**
     * verifies if the array is sorted
     * @param a the array being verified as sorted
     * @return true if array is sorted
     */

    private static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i - 1])) return false;
        return true;
    }

    /**
     * verifies if the array is sorted
     * @param a the array being verified as sorted
     * @return true if array is sorted
     */

    private static boolean isSorted(int[] a) {
        for (int i = 1; i < a.length; i++)
            if (a[i] < a[i-1]) return false;
        return true;
    }

    /**
     * compares the values in two arrays
     * @param a comparable array
     * @param b integer array that is compared to a
     * @return true if a and b are arrays with the same values
     */

    public static boolean isEquivalent(Comparable[] a, int[] b){

        boolean isE = true;

        for(int i = 0; i < a.length; i++){
            if((Integer)a[i] != b[i]){
                isE = false;
            }
        }
        return isE;
    }

    /**
     * compares the values in two array
     * @param a comparable array
     * @param b comparable array
     * @return true if a and b arrays have the same values
     */

    public static boolean isEquivalent(Comparable[] a, Comparable[] b){

        boolean isE = true;

        for(int i = 0; i < a.length; i++){
            if(!Arrays.deepEquals(a,b)){
                isE = false;
            }
        }

        return isE;
    }

    /**
     * sorts arrays into ascending order by moving max value to last index
     * @param a the array being sorted
     */


    public static void BubbleSort( Comparable[] a){

        for( int i = 0; i < a.length - 1; i++){
            for( int j = 0; j < a.length - 1 - i; j++){
                if (!less(a[j] , a[j+1])){
                    swap(a, j , j+1);
                }
            }
        }

        assert isSorted(a);
    }

    /**
     * sorts the array by using significant digits to determine ascending order
     * loops through all the significant digit places had in max value
     * @param a integer array being sorted into ascending order
     */

    public static void RadixSort( int[] a){

        int max = findMax(a);

        for(int place = 1; max/place > 0; place *= 10){

            sortR(a,place);

        }
        assert isSorted(a);

    }

    /**
     * sorts array by traversing from left to right and putting max value in largest index
     * then traverses back from right to left and puts minimum in smallest index
     * @param a array being sorted into ascending order
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

    public static void main(String[] args){

        //two comparable arrays are created with size 100,000
        //one integer array is created with size 100,000

        Comparable [] randNums1 = new Comparable[100000];
        int [] randNums2 = new int[100000];
        Comparable [] randNums3 = new Comparable[100000];


        //random integers from 1 to 500,000 are put in array randNums1
        
        Random random = new Random();

        for(int i = 0; i < 100000; i++){
            randNums1[i] = random.nextInt(500000) + 1; //inserts a random integer in range [1, 500000]
        }

        //values in randNums1 are copied into RandNums2 and RandNums3

        for(int j = 0; j < 100000; j++){
            randNums2[j] = (Integer)randNums1[j];
        }
        for(int k = 0; k < 100000; k++){
            randNums3[k] = randNums1[k];
        }

        //three sorts are run and timed

        StopWatch timer1 = new StopWatch();
        BubbleSort(randNums1);
        System.out.println("elapsed time for bubble sort " + timer1.elapsedTime());

        StopWatch timer2 = new StopWatch();
        RadixSort(randNums2);
        System.out.println("elapsed time for radix sort " + timer2.elapsedTime());

        StopWatch timer3 = new StopWatch();
        CocktailSort(randNums3);
        System.out.println("elapsed time for cocktail sort " + timer3.elapsedTime());

        //checking whether arrays are equivalent
        if(isEquivalent(randNums1,randNums2) && isEquivalent(randNums1, randNums3) && isEquivalent(randNums3, randNums2)){
            System.out.println("BubbleSort, RadixSort, and CocktailSort all produce the same sorted arrays.");
        }

        if(!isEquivalent(randNums1,randNums2) || !isEquivalent(randNums1, randNums3) || !isEquivalent(randNums3, randNums2)){
            System.out.println("not");
        }

    }

}
