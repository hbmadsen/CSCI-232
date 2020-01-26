/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * taken from Algorithms, 4th edition by Sedgewick and Wayne
 */
/******************************************************************************
 *  Compilation:  javac Graph.java
 *  Execution:    java Graph input.txt
 *  Dependencies: Bag.java Stack.java In.java StdOut.java
 *  Data files:   https://algs4.cs.princeton.edu/41graph/tinyG.txt
 *                https://algs4.cs.princeton.edu/41graph/mediumG.txt
 *                https://algs4.cs.princeton.edu/41graph/largeG.txt
 *
 *  A graph, implemented using an array of sets.
 *  Parallel edges and self-loops allowed.
 *
 ******************************************************************************/


import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import program3.*;

/**
 *  The {@code Graph} class represents an undirected graph of vertices
 *  named 0 through <em>V</em> – 1.
 *  It supports the following two primary operations: add an edge to the graph,
 *  iterate over all of the vertices adjacent to a vertex. It also provides
 *  methods for returning the number of vertices <em>V</em> and the number
 *  of edges <em>E</em>. Parallel edges and self-loops are permitted.
 *  By convention, a self-loop <em>v</em>-<em>v</em> appears in the
 *  adjacency list of <em>v</em> twice and contributes two to the degree
 *  of <em>v</em>.
 *  <p>
 *  This implementation uses an adjacency-lists representation, which
 *  is a vertex-indexed array of {@link Bag} objects.
 *  All operations take constant time (in the worst case) except
 *  iterating over the vertices adjacent to a given vertex, which takes
 *  time proportional to the number of such vertices.
 *  <p>
 *  For additional documentation, see <a href="https://algs4.cs.princeton.edu/41graph">Section 4.1</a>
 *  of <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class Graph {
    private static final String NEWLINE = System.getProperty("line.separator");

    private final int V;
    private int E;
    private Bag<Courses>[] adj;

    /**
     * Initializes an empty graph with {@code V} vertices and 0 edges.
     * param V the number of vertices
     *
     * @param  V number of vertices
     * @throws IllegalArgumentException if {@code V < 0}
     */
    public Graph(int V) {
        if (V < 0) throw new IllegalArgumentException("Number of vertices must be nonnegative");
        this.V = V;
        this.E = 0;
        adj = (Bag<Courses>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<Courses>();
        }
    }

    /**
     * initializes the number of vertices to be the number of distinct Courses
     * creates and array of bags that store the edges
     * an edge is created when a courses is taught by the same professor
     * @param c is the array of Courses objects
     */
    public Graph(Courses[] c) {
        try {
            this.V = c.length;
            if (V < 0) throw new IllegalArgumentException("number of vertices in a Graph must be nonnegative");
            adj = (Bag<Courses>[]) new Bag[V];
            for (int v = 0; v < V; v++) {
                adj[v] = new Bag<Courses>();
            }
            int E = 0;
            if (E < 0) throw new IllegalArgumentException("number of edges in a Graph must be nonnegative");
            for (int i = 0; i <= V - 2; i++) {
                Courses v = c[i];
                for(int j = i + 1; j < c.length - 1; j++ ){ //loop through each element to see if related by professor
                    Courses w = c[j];
                    if(v.prof.equalsIgnoreCase(w.prof)) { //adds edge if each Courses has the same professor
                        addEdge(v, w, i, j);
                    }
                }

            }
        }

        catch (NoSuchElementException e) {
            throw new IllegalArgumentException("invalid input format in Graph constructor", e);
        }
    }

    /**
     * Initializes a new graph that is a deep copy of {@code G}.
     *
     * @param  G the graph to copy
     */
    public Graph(Graph G) {
        this(G.V());
        this.E = G.E();
        for (int v = 0; v < G.V(); v++) {
            // reverse so that adjacency list is in same order as original
            Stack<Courses> reverse = new Stack<Courses>();
            for (Courses c : G.adj[v]) {
                reverse.push(c);
            }
            for (Courses c : reverse) {
                adj[v].add(c);
            }
        }
    }

    /**
     * Returns the number of vertices in this graph.
     *
     * @return the number of vertices in this graph
     */
    public int V() {
        return V;
    }

    /**
     * Returns the number of edges in this graph.
     *
     * @return the number of edges in this graph
     */
    public int E() {
        return E;
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

    /**
     * Adds the undirected edge v-w to this graph.
     *
     * @param  v one vertex in the edge
     * @param  w the other vertex in the edge
     */
    public void addEdge(Courses v, Courses w, int i, int j) {
        E++; //increments the number of edges
        adj[i].add(w);
        adj[j].add(v);
    }


    /**
     * Returns the vertices adjacent to vertex {@code v}.
     *
     * @param  v the vertex
     * @return the vertices adjacent to vertex {@code v}, as an iterable
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public Iterable<Courses> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    /**
     * Returns the degree of vertex {@code v}.
     *
     * @param  v the vertex
     * @return the degree of vertex {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public int degree(int v) {
        validateVertex(v);
        return adj[v].size();
    }


    /**
     * Returns a string representation of this graph.
     *
     * @return the number of vertices <em>V</em>, followed by the number of edges <em>E</em>,
     *         followed by the <em>V</em> adjacency lists
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " vertices, " + E + " edges " + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (Courses w : adj[v]) {
                s.append(w + " ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }

    // maximum degree
    public static int maxDegree(Graph G) {
        int max = 0;
        for (int v = 0; v < G.V(); v++)
            if (G.degree(v) > max)
                max = G.degree(v);
        return max;
    }

    // average degree
    public static int avgDegree(Graph G) {
        // each edge incident on two vertices
        return 2 * G.E() / G.V();
    }

    // number of self-loops
    public static int numberOfSelfLoops(Graph G) {
        int count = 0;
        for (int v = 0; v < G.V(); v++)
            for (Courses c : G.adj(v))
                if (G.adj[v].equals(c)) count++;
        return count/2;   // self loop appears in adjacency list twice
    }


    /**
     * instantiates a Graph object out of the array of Courses
     * prints out the Courses and determines relationships based on edges in the graph
     * @param args the command-line arguments
     */

    public static void main(String[] args) throws FileNotFoundException {

        File file = new File(args[0]);
        int i = 0;

        Scanner scanner = new Scanner(file);

        Courses[] elements = new Courses[23];

        /*
        while loop takes in each line of the csv file and puts its elements into a string array
        the string array elements are then used to create Courses objects that will be our values for each vertex
         */

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] fields = line.split(",");
            Courses c = new Courses(fields);

            elements[i] = c;
            i++;

        }

        //instantiation of Graph class when G is created
        Graph G = new Graph(elements);

        System.out.println(G.V() + " vertices, " + G.E() + " edges");
         for(int v = 0; v <= G.V() - 1; v++){
             System.out.print(elements[v].id);
             if(!G.adj[v].isEmpty()){ //checking to see if there are edges
                 System.out.print(", " + elements[v].prof + " also teaches ");
                 for(Courses c: G.adj[v]){
                     System.out.print(c.id + " "); //printing out all edges for a given vertex
                 }
                 System.out.println();
             }else{
                 System.out.println("," + elements[v].prof + " teaches only this course");
             }

         }

    }

}
