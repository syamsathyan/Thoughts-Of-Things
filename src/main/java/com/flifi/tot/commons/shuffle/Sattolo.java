package com.flifi.tot.commons.shuffle;

/******************************************************************************
 * Created by sathyasy on 9/14/15.
 * <p/>
 * Compilation:  javac Sattolo.java
 * Execution:    java Sattolo < list.txt
 * <p/>
 * Reads in a list of strings and prints a uniformly random cycle
 * using Sattolo's algorithm under the assumption that Math.random()
 * generates independent and uniformly distributed numbers between
 * 0 and 1.
 * <p/>
 * %  echo 0 1 2 3 4 | java Sattolo
 * 1
 * 2
 * 4
 * 0
 * 3
 ******************************************************************************/
/******************************************************************************
 *  Compilation:  javac Sattolo.java
 *  Execution:    java Sattolo < list.txt
 *
 *  Reads in a list of strings and prints a uniformly random cycle
 *  using Sattolo's algorithm under the assumption that Math.random()
 *  generates independent and uniformly distributed numbers between
 *  0 and 1.
 *
 *  %  echo 0 1 2 3 4 | java Sattolo
 *  1
 *  2
 *  4
 *  0
 *  3
 *
 ******************************************************************************/

import java.util.Arrays;

/**
 * The <tt>Sattolo</tt> class provides a client for reading in a
 * sequence of <em>N</em> strings and computing a <em>unifomly random cycle</em>
 * if length <em>N</em> using the Sattolo's algorithm.
 * This algorithm guarantees to produce a unifomly random cycle under
 * the assumption that Math.random() generates independent and
 * uniformly distributed numbers between 0 and 1.
 * <p>
 * For additional documentation, see <a href="http://algs4.cs.princeton.edu/11model">Section 1.1</a> of
 * <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
public class Sattolo {

    // this class should not be instantiated
    private Sattolo() {
    }

    /**
     * Rearranges an array of objects to be a uniformly random cycle
     * (under the assumption that <tt>Math.random()</tt> generates independent
     * and uniformly distributed numbers between 0 and 1).
     *
     * @param a the array to be rearranged
     */
    public static void cycle(Object[] a) {
        int N = a.length;
        for (int i = N; i > 1; i--) {
            // choose index uniformly in [0, i-1)
            int r = (int) (Math.random() * (i - 1));
            Object swap = a[r];
            a[r] = a[i - 1];
            a[i - 1] = swap;
        }
    }
}
