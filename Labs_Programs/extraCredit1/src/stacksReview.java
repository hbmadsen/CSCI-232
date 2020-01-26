//Hannah Madsen CSCI 232
//Extra Credit Assignment #1

import java.util.Stack;
import java.lang.Math;

public class stacksReview {


        public static void main(String[] args) {

            Stack<Integer> st = new Stack<Integer>(); //initializing a new stack called st

            //looping through and pushing 20 random integers onto stack st
            for (int i = 0; i < 20; i++) {

                int n = (int) (Math.random() * 100 + 1);

                st.push(n);

            }

            System.out.println(st);

            System.out.println(st.pop());

            System.out.println(st);

            boolean isFifty = false;
            //looping through each integer in st
            //checking to see if 50 is present in st
            for (int j : st) {

                //if 50 is in st then the boolean isFifty is set to true
                if (j == 50) {
                    isFifty = true;
                }
            }

            System.out.println(isFifty);

            System.out.println(st.isEmpty());

            int total = 0;

            //looping through all of st and popping all integers
            //each iteration also adds the newly popped integer to the running total
            while (!st.isEmpty()) {

                total += st.pop();

            }

            //can calculate average by dividing the total by 19
            //in this program there will always be 19 integers that are popped and added to the total
            System.out.println(total / 19);
        }
}
