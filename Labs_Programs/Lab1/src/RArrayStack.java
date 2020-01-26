/*
Lab #1 CSCI 232
Hannah Madsen
partners: Taylor Rentschler & Derek Wham
*/

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class RArrayStack<T> implements Iterable<T> {
    private T[] a;  //array that storms the items of generic type T
    private int N;  //number of items in stack

    //an empty stack of generic type T is created
    public RArrayStack() {
        a = (T[]) new Object[8];
        N = 0;
    }

    public boolean isEmpty() {

        return N == 0;
    }

    public boolean isFull() {

        return N == a.length;
    }

    public void push(T item) {

        //check first to see if the stack is full
        //calls resize() method if stack is full

        if (!this.isFull()) {

            a[N++] = item;

        } else {

            this.resize(2*N);
            a[N++] = item;

        }

    }

    public T pop() {

        return a[--N];
    }


    private void resize(int capacity) {

        //an empty array of type T is created with twice the capacity as the previous, full array
        T[] rArray = (T[]) new Object[capacity];

        int i = 0;

        //copy elements from the full array into the new array
        for (T s : a) {

            rArray[i] = s;
            i++;

        }

        //replace the old full array with the new array that is twice the size
        a = rArray;
    }

    public Iterator<T> iterator() {

        return new ReverseArrayIterator();
    }

    public class ReverseArrayIterator implements Iterator<T> {

        private int i = N - 1;

        public boolean hasNext() {

            return i >= 0;
        }

        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            return a[i--];
        }

        public void remove() {

            throw new UnsupportedOperationException();
        }
    }


    public static void main(String[] args) throws FileNotFoundException{

        System.out.println("Output from RArrayStack class: ");


        //declares and instantiates a RArrayStack called aStack
        RArrayStack aStack = new RArrayStack();

        //locates and reads in the file from the program arguments
        File file = new File(args[0]);
        Scanner inpFile = new Scanner(file);

        //adds elements from file to aStack
        while(inpFile.hasNext()){

            aStack.push(inpFile.next());

        }

        int count = 0;

        //prints elements of RArrayStack in reverse by popping them off
        while( !aStack.isEmpty()){

            System.out.print(aStack.pop() + " ");
            count++;

            //skips a line every 8 words to make output more readable
            if(count % 8 == 0){ System.out.println();}

        }

    }

}
