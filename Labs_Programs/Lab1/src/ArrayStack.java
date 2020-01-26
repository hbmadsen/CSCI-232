/*
Lab #1 CSCI 232
Hannah Madsen
partners: Taylor Rentschler and Derek Wham
 */

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;



public class ArrayStack<T> implements Iterable<T> {
    private T[] a;  // array that stores the items of generic type
    private int N;  // number of items in stack

    // an empty stack of generic type T is created with given capacity
    public ArrayStack(int capacity) {
        a = (T[])new Object[capacity];
        N = 0;
    }

    public boolean isEmpty() {

        return N == 0;
    }


    public void push(T item) {

            a[N++] = item;

        }

    public T pop() {

        return a[--N];
    }


    public Iterator<T> iterator() {

        return new ReverseArrayIterator();
    }

    public class ReverseArrayIterator implements Iterator<T> {

        private int i = N-1;

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


    public static void main(String[] args)throws FileNotFoundException{

        System.out.println("Output from ArrayStack class:");

        //declares and instantiates an ArrayStack object and names it aStack
        //gives a capacity of 100
        ArrayStack aStack = new ArrayStack(100);

        //locates and reads in the file from the program arguments
        File file = new File(args[1]);
        Scanner inpFile = new Scanner(file);


        //adds elements of file to the ArrayStack
        while(inpFile.hasNext()){

            aStack.push(inpFile.next());

        }

        int count = 0;

        //prints elements of ArrayStack in reverse by popping them off
        while( !aStack.isEmpty()){

            System.out.print(aStack.pop() + " ");

            count++;

            //skips a line every 8 words to make output more readable
            if(count % 8 == 0){
                System.out.println();
            }

        }

    }

}