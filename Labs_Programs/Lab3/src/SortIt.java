/**
 * Lab 3
 * Hannah Cebulla & Hannah Madsen
 */

import java.util.Random;
import com.hannah.*;
public class SortIt {

    SortIt(){ } //object is never instantiated

    public static void main(String[] args){

        //create three arrays of comparables each with 5000 integers

        Comparable [] randNums1 = new Comparable[5000];
        Comparable [] randNums2 = new Comparable[5000];
        Comparable [] randNums3 = new Comparable[5000];


        Random random = new Random();

        //fill first array with 5000 random integers

        for(int i = 0; i < 5000; i++){
            randNums1[i] = random.nextInt(100);
        }

        //copy elements from first array into second
        for(int j = 0; j < 5000; j++){
            randNums2[j] = randNums1[j];
        }

        //copy elements from first array into third
        for(int k = 0; k < 5000; k++){
            randNums3[k] = randNums1[k];
        }


        //start stopwatch the measure runtime & call SelectionSort method
        //print resulting runtime
        StopWatch timer1 = new StopWatch();
        SelectionSort.sort(randNums1);
        System.out.println("elapsed time for selection sort " + timer1.elapsedTime());

        //start stopwatch the measure runtime & call InsertionSort method
        //print resulting runtime
        StopWatch timer2 = new StopWatch();
        InsertionSort.sort(randNums2);
        System.out.println("elapsed time for insertion sort " + timer2.elapsedTime());

        //start stopwatch the measure runtime & call SelectionSort method
        //print resulting runtime
        StopWatch timer3 = new StopWatch();
        ShellSort.sort(randNums3);
        System.out.println("elapsed time for shell sort " + timer3.elapsedTime());



    }
}
