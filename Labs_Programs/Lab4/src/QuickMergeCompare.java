import java.util.Random;
import com.hannah.*;

public class QuickMergeCompare {

    public QuickMergeCompare(){ } // never instantiated

    public static void main(String[] args){


        Comparable [] randNums1 = new Comparable[100000];
        Comparable [] randNums2 = new Comparable[100000];



        Random random = new Random();

        //fill first array with 100000 random numbers

        for(int i = 0; i < 100000; i++){
            randNums1[i] = random.nextDouble();
        }

        //copy first array numbers into another

        for(int j = 0; j < 100000; j++){
            randNums2[j] = randNums1[j];
        }

        //start stopwatch the measure runtime & call QuickSort sort method
        //print resulting runtime
        StopWatch timer1 = new StopWatch();
        QuickSort.sort(randNums1);
        System.out.println("elapsed time for quick sort " + timer1.elapsedTime());

        //start stopwatch the measure runtime & call MergeSort sort method
        //print resulting runtime
        StopWatch timer2 = new StopWatch();
        MergeSort.sort(randNums2);
        System.out.println("elapsed time for merge sort " + timer2.elapsedTime());

    }
}
