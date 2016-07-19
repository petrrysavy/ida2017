package cz.cvut.fel.ida.reads.readsIDA2016.util;

import java.util.Arrays;

/**
 * Utility class for manipulating arrays.
 *
 * @author Petr Ryšavý
 */
public final class ArrayUtils {

    /** Do not let anybody to instantiate the class. */
    private ArrayUtils() {
    }

    /**
     * Creates new double array that contains {@code n}-times {@code value}.
     *
     * @param value The value that should be in the array.
     * @param n     Length of the desired array.
     * @return Array that contains {@code n}-times {@code value}.
     */
    public static double[] nTimes(double value, int n) {
        final double[] arr = new double[n];
        Arrays.fill(arr, value);
        return arr;
    }
}
